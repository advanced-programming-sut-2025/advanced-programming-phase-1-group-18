package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.List;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Controller.ClockController;
import io.github.group18.Controller.EnergyController;
import io.github.group18.Controller.GameController;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.*;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;

public class GameView {
    private final GameController gameController;
    private SpriteBatch batch;
    private TextureRegion[][] tileTextures;
    private Map<Object, TextureRegion> textures;
    private Map<String, TextureRegion> texturesOnDrug;
    private BitmapFont smallFont;
    private GlyphLayout layout = new GlyphLayout();
    private TextureAtlas playerAtlas;
    private final static ArrayList<Animation<TextureRegion>> playerAnimations = new ArrayList<>();
    private float stateTime = 0f;
    private int moveDirection = 0;
    private Texture pixel; // Add this
    private ClockController clock;
    private EnergyController energy;
    private float redFlashTimer = 0f;
    private boolean isFlashingRed = false;
    private boolean walking = false;

    private StoreUI BlackSmith;
    private StoreUI CarpentersShop;
    private StoreUI FishShop;
    private StoreUI JojaMart;
    private StoreUI MarniesRanch;
    private StoreUI PirresGeneralStore;
    private StoreUI TheStarDropSalooon;

    private NPCDialogView npcDialogView;

    private NPCView npcView;

    private boolean sebastian_dialog = false;
    private boolean abigail_dialog = false;
    private boolean harvey_dialog = false;
    private boolean leah_dialog = false;
    private boolean robin_dialog = false;

    private boolean sebastian_view = false;
    private boolean abigail_view = false;
    private boolean harvey_view = false;
    private boolean leah_view = false;
    private boolean robin_view = false;

    private OrthographicCamera camera;
    private boolean cameraInitialized = false;
    private boolean showPopup = false;
    private String popupMessage = "";
    private Rectangle popupRect = new Rectangle();
    private boolean watering = false;
    private float plantx, planty;
    private float waterTimer = 0f;

    private String pendingPlacementName;

    int startX;
    int startY;
    int endX;
    int endY;
    ArrayList<ArrayList<Kashi>> tiles;

    public GameView(GameController gameController) {
        this.gameController = gameController;
        batch = new SpriteBatch();
        clock = new ClockController();
        energy = new EnergyController();
        loadTextures();
        loadFont();
    }

    public void loadTextures() {
        textures = new HashMap<>();
        texturesOnDrug = new HashMap<>();

        playerAtlas = GameAssetManager.getGameAssetManager().getPlayerAtlas();

        for (int i = 14; i > 9; i--) {
            Array<TextureRegion> walkFrames = new Array<>();
            if (i == 14) {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + 13 + "_" + 0;
                    walkFrames.add(playerAtlas.findRegion(region));
                }
            } else {
                for (int j = 0; j < 4; j++) {
                    String region = "player_" + i + "_" + j;
                    walkFrames.add(playerAtlas.findRegion(region));
                }
            }
            playerAnimations.add(new Animation<>(0.15f, walkFrames, Animation.PlayMode.LOOP));
        }

        Array<TextureRegion> faintFrames = new Array<>();
        TextureRegion frame;
        for (int i = 0; i < 7; i++) {
            frame = playerAtlas.findRegion("player_faint_" + i);
            if (frame == null) {
                Gdx.app.error("Animation", "Missing faint frame: player_faint_" + i);
            } else {
                faintFrames.add(frame);
            }
        }
        if (faintFrames.size == 7) {
            playerAnimations.add(new Animation<>(0.2f, faintFrames));
        } else {
            Gdx.app.error("Animation", "Missing faint frames! Loaded: " + faintFrames.size);
        }
        Array<TextureRegion> eatFrames = new Array<>();
        TextureRegion frames;
        for (int i = 0; i < 2; i++) {
            frame = playerAtlas.findRegion("player_6_" + i);
            if (frame == null) {
                Gdx.app.error("Animation", "Missing eat frame: 8_" + i);
            } else {
                eatFrames.add(frame);
            }
        }
        if (eatFrames.size == 2) {
            playerAnimations.add(new Animation<>(0.2f, eatFrames));
        } else {
            Gdx.app.error("Animation", "Missing eat frames! Loaded: " + eatFrames.size);
        }

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(0, 0, 0, 1);
        pixmap.fill();
        pixel = new Texture(pixmap);
        pixmap.dispose();
    }

    private void loadFont() {
        smallFont = new BitmapFont();
        smallFont.setColor(Color.WHITE);
        smallFont.getData().setScale(1.5f);
    }

    private void loadTiles(int startX, int startY, int endX, int endY, ArrayList<ArrayList<Kashi>> tiles) {
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                Kashi tile = tiles.get(x - startX).get(y - startY);
                if (tile == null) continue;

                Object inside = tile.getInside();
                if (!textures.containsKey(inside)) {
                    if (inside instanceof PictureModel pictureModel) {
                        try {
                            TextureRegion tex;
                            if (texturesOnDrug.containsKey(pictureModel.getPath())) {
                                tex = texturesOnDrug.get(pictureModel.getPath());
                            } else {
                                tex = new TextureRegion(new Texture(Gdx.files.internal(pictureModel.getPath())));
                                texturesOnDrug.put(pictureModel.getPath(), tex);
                            }
                            if (inside instanceof GreenHouse greenHouse && !greenHouse.isStatus()) {
                                tex = GameAssetManager.getGameAssetManager().getGreenhouseBroken();
                            }
                            textures.put(inside, tex);
                        } catch (Exception e) {
                            System.out.println(inside.getClass().getSimpleName());
                            textures.put(inside, GameAssetManager.getGameAssetManager().getGrass());
                        }
                    } else {
                        textures.put(inside, GameAssetManager.getGameAssetManager().getGrass());
                    }
                }
            }
        }
    }

    private void loadInventoryItems() {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;

        for (Item item : ClientModel.getPlayer().getInventory().getItems().keySet()) {
            if (item instanceof CraftingItem) {
                textures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getCraftingAtlas().
                    findRegion(((CraftingItem) item).getCraftingItem().name())));
            } else if (item instanceof PictureModel pictureModel) {
                String path = pictureModel.getPath();
                try {
                    textures.put(item, new TextureRegion(new Texture(Gdx.files.internal(path))));
                } catch (Exception e) {
                    textures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getDefaultInventoryItem()));
                }
            } else {
                textures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getDefaultInventoryItem()));
            }
        }
    }

    public void render() {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        renderTiles();
        renderPlayer();
        renderInMyHandToolPlayer();
        renderInventory();
        renderClock();
        energy.render(batch);
        renderMarkets();
        renderKalagEffect(batch);
        renderBrightness();
        walking = false;
        batch.end();
    }

    private void renderNPC(int startx, int starty, int endx, int endy) {

        GameAssetManager gameAssetManager = GameAssetManager.getGameAssetManager();
        Texture triangle = gameAssetManager.getUpsidedownredtriangle();
        Message send = new Message(new HashMap<>(), Message.Type.get_npc, Message.Menu.game);
        Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        Gson gson = new Gson();
        Object sebastianObj = response.getFromBody("sebastian");
        Object abigailObj = response.getFromBody("abigail");
        Object harveyObj = response.getFromBody("harvey");
        Object leahObj = response.getFromBody("leah");
        Object robinObj = response.getFromBody("robin");
        String sebastianSTR = gson.toJson(sebastianObj);
        String abigailSTR = gson.toJson(abigailObj);
        String harveySTR = gson.toJson(harveyObj);
        String leahSTR = gson.toJson(leahObj);
        String robinSTR = gson.toJson(robinObj);
        NPC sebastian = gson.fromJson(sebastianSTR, NPC.class);
        NPC abigail = gson.fromJson(abigailSTR, NPC.class);
        NPC harvey = gson.fromJson(harveySTR, NPC.class);
        NPC leah = gson.fromJson(leahSTR, NPC.class);
        NPC robin = gson.fromJson(robinSTR, NPC.class);

        if (sebastian.getX() >= startx && sebastian.getX() <= endx &&
            sebastian.getY() >= starty && sebastian.getY() <= endy) {
            Texture npc = gameAssetManager.getSebastian_NPC();
            batch.draw(npc, sebastian.getX() * ClientModel.TILE_SIZE, sebastian.getY() * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE * 2);
            batch.draw(triangle, sebastian.getX() * ClientModel.TILE_SIZE, sebastian.getY() * ClientModel.TILE_SIZE + 2 * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE);
        }

        if (abigail.getX() >= startx && abigail.getX() <= endx &&
            abigail.getY() >= starty && abigail.getY() <= endy) {
            Texture npc = gameAssetManager.getAbigail_NPC();
            batch.draw(npc, abigail.getX() * ClientModel.TILE_SIZE, abigail.getY() * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE * 2);
            batch.draw(triangle, abigail.getX() * ClientModel.TILE_SIZE, abigail.getY() * ClientModel.TILE_SIZE + 2 * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE);
        }

        if (harvey.getX() >= startx && harvey.getX() <= endx &&
            harvey.getY() >= starty && harvey.getY() <= endy) {
            Texture npc = gameAssetManager.getHarvey_NPC();
            batch.draw(npc, harvey.getX() * ClientModel.TILE_SIZE, harvey.getY() * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE * 2);
            batch.draw(triangle, harvey.getX() * ClientModel.TILE_SIZE, harvey.getY() * ClientModel.TILE_SIZE + 2 * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE);
        }

        if (leah.getX() >= startx && leah.getX() <= endx &&
            leah.getY() >= starty && leah.getY() <= endy) {
            Texture npc = gameAssetManager.getLeah_NPC();
            batch.draw(npc, leah.getX() * ClientModel.TILE_SIZE, leah.getY() * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE * 2);
            batch.draw(triangle, leah.getX() * ClientModel.TILE_SIZE, leah.getY() * ClientModel.TILE_SIZE + 2 * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE);
        }

        if (robin.getX() >= startx && robin.getX() <= endx &&
            robin.getY() >= starty && robin.getY() <= endy) {
            Texture npc = gameAssetManager.getRobin_NPC();
            batch.draw(npc, robin.getX() * ClientModel.TILE_SIZE, robin.getY() * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE * 2);
            batch.draw(triangle, robin.getX() * ClientModel.TILE_SIZE, robin.getY() * ClientModel.TILE_SIZE + 2 * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE);
        }

        if (isSebastian_dialog() || isAbigail_dialog()
            || isHarvey_dialog() || isLeah_dialog() || isRobin_dialog()) {
            npcDialogView.render();
        }

        if (isSebastian_view() || isHarvey_view() ||
            isLeah_view() || isRobin_view() || isAbigail_view()) {
            npcView.render();
        }
    }

    private void renderMarkets() {
        if (BlackSmith != null) {
            BlackSmith.render();
        }
        if (CarpentersShop != null) {
            CarpentersShop.render();
        }
        if (FishShop != null) {
            FishShop.render();
        }
        if (JojaMart != null) {
            JojaMart.render();
        }
        if (MarniesRanch != null) {
            MarniesRanch.render();
        }
        if (PirresGeneralStore != null) {
            PirresGeneralStore.render();
        }
        if (TheStarDropSalooon != null) {
            TheStarDropSalooon.render();
        }
    }

    private void renderInMyHandToolPlayer() {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        Player player = ClientModel.getPlayer();
        Tool tool = player.getInMyHandTool();
        if (tool != null) {
            double first = player.getX();
            double second = player.getY();

            TextureRegion textureRegion = textures.get(tool);
            if (textureRegion == null) {
                textureRegion = new TextureRegion(GameAssetManager.getGameAssetManager().getDefaultInventoryItem());
            }
            if (walking) {
                Random random = new Random();
                int x = 5 + random.nextInt(15);
                int y = 5 + random.nextInt(15);
                batch.draw(textureRegion, (float) (first * ClientModel.TILE_SIZE + ClientModel.TILE_SIZE / 2 + x), (float) (second * ClientModel.TILE_SIZE + ClientModel.TILE_SIZE / 4 + y), ClientModel.TILE_SIZE / 2, ClientModel.TILE_SIZE / 2);

            } else {
                batch.draw(textureRegion, (float) (first * ClientModel.TILE_SIZE + ClientModel.TILE_SIZE / 2), (float) (second * ClientModel.TILE_SIZE + ClientModel.TILE_SIZE / 4), ClientModel.TILE_SIZE / 2, ClientModel.TILE_SIZE / 2);
            }
        }
    }

    private void renderTiles() {

        OrthographicCamera cam = camera;
        int tileSize = ClientModel.TILE_SIZE;

        int newstartX = (int) ((cam.position.x - cam.viewportWidth / 2) / tileSize) - 2;
        int newstartY = (int) ((cam.position.y - cam.viewportHeight / 2) / tileSize) - 2;
        int newendX = (int) ((cam.position.x + cam.viewportWidth / 2) / tileSize) + 2;
        int newendY = (int) ((cam.position.y + cam.viewportHeight / 2) / tileSize) + 2;

        newstartX = Math.max(0, newstartX);
        newstartY = Math.max(0, newstartY);
        newendX = Math.min(ClientModel.mapWidth - 1, newendX);
        newendY = Math.min(ClientModel.mapHeight - 1, newendY);


        if (!(startX == newstartX && startY == newstartY && endX == newendX && endY == newendY)) {
            HashMap<String, Object> body = new HashMap<>();
            body.put("startX", newstartX);
            body.put("startY", newstartY);
            body.put("endX", newendX);
            body.put("endY", newendY);
            io.github.group18.Network.common.models.Message send = new io.github.group18.Network.common.models.Message(body, Message.Type.get_kashis_using_2x_2y, Message.Menu.game);
            Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
            Gson gson = new Gson();
            Object usersArraylistOBJ = response.getBody().get("kashis");
            String userArraylist = gson.toJson(usersArraylistOBJ);
            Type userListType = new TypeToken<ArrayList<Kashi>>() {
            }.getType();
            tiles = gson.fromJson(userArraylist, userListType);
            startX = newstartX;
            startY = newstartY;
            endX = newendX;
            endY = newendY;
        }

        drawInitTiles(startX, startY, endX, endY, tiles);
        loadTiles(startX, startY, endX, endY, tiles);
        drawTiles(startX, startY, endX, endY, tiles);
        renderNPC(startX, startY, endX, endY);
    }

    private void drawInitTiles(int startX, int startY, int endX, int endY, ArrayList<ArrayList<Kashi>> tiles) {
        int tileSize = ClientModel.TILE_SIZE;
        TextureRegion texture = new TextureRegion(GameAssetManager.getGameAssetManager().getGrass());
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                float drawX = x * tileSize;
                float drawY = y * tileSize;
                if (tiles.get(x - startX).get(y - startY).getInside() == null) {
                    if (ClientModel.getPlayer().isPlacingItem()) {
                        batch.setColor(0, 1, 0, 0.5f);
                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
                        batch.setColor(Color.WHITE);
                    } else {
                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
                    }
                } else {
                    batch.draw(texture, drawX, drawY, tileSize, tileSize);
                }
            }
        }
    }

    private void drawTiles(int startX, int startY, int endX, int endY, ArrayList<ArrayList<Kashi>> tiles) {
        ArrayList<Pair<Integer, Integer>> alreadyRenderedTiles = new ArrayList<>();
        ArrayList<BottomLeft> bottomLeftTiles = new ArrayList<>();
        int tileSize = ClientModel.TILE_SIZE;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                getBottomLeftCorner(x, y, tiles.get(x - startX).get(y - startY), tiles, alreadyRenderedTiles, bottomLeftTiles);

                if (alreadyRenderedTiles.contains(new Pair<>(x, y))) {
                    //this is for blocks that are covered with big pics so they dont draw anything
                } else {
                    boolean flag = false;
                    BottomLeft bottomLeft = null;
                    for (int i = 0; i < bottomLeftTiles.size(); i++) {
                        bottomLeft = bottomLeftTiles.get(i);
                        if (bottomLeft.getX() == x && bottomLeft.getY() == y && bottomLeft.isBalls()) {
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        //this is for bottom left corners which should draw a big pic
                        Kashi tile = tiles.get(x - startX).get(y - startY);
                        Object inside = tile.getInside();
                        TextureRegion texture = textures.get(inside);
                        float drawX = x * tileSize;
                        float drawY = y * tileSize;
                        if (inside instanceof GreenHouse greenHouse && greenHouse.isStatus()) {
                            texture = GameAssetManager.getGameAssetManager().getGreenhouse();
                        }
                        if (texture == null) {
                            texture = GameAssetManager.getGameAssetManager().getGrass();
                        }
                        batch.draw(texture, drawX, drawY, tileSize * bottomLeft.getWidth(),
                            tileSize * bottomLeft.getHeight() / bottomLeft.getWidth());
                    } else {
                        //this is for normal blocks
                        Kashi tile = tiles.get(x - startX).get(y - startY);
                        Object inside = tile.getInside();

                        if (inside == null) {
                            if (tile.isShokhmZadeh()) {
                                float drawX = x * tileSize;
                                float drawY = y * tileSize;
                                batch.draw(GameAssetManager.getGameAssetManager().getSoilTexture(), drawX, drawY, tileSize, tileSize);
                                continue;
                            } else {
                                continue;
                            }
                        }


                        TextureRegion texture = textures.get(inside);
                        float drawX = x * tileSize;
                        float drawY = y * tileSize;
                        if (texture == null) {
                            texture = GameAssetManager.getGameAssetManager().getGrass();
                        }
                        if (inside instanceof CraftingItem) {
                            texture = GameAssetManager.getGameAssetManager().getCraftingAtlas().
                                findRegion(((CraftingItem) inside).getCraftingItem().name());
                            texture.setRegionWidth(tileSize);
                            TextureRegion blueBar = new TextureRegion(GameAssetManager.getGameAssetManager().getBlueBarTexture());
                            TextureRegion ready = new TextureRegion(GameAssetManager.getGameAssetManager().getReadyTexture());
                            if (((CraftingItem) inside).isProcessing()) {
                                if (((CraftingItem) inside).getInsideArtisan() != null) {

                                    Message send = new Message(new HashMap<>(), Message.Type.get_dateTime, Message.Menu.game);
                                    Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
                                    Gson gson = new Gson();
                                    Object dateTimeObj = response.getFromBody("dateTime");
                                    String dateTimeStr = gson.toJson(dateTimeObj);
                                    DateTime dateTime = gson.fromJson(dateTimeStr, DateTime.class);


                                    if (!((CraftingItem) inside).getInsideArtisan().
                                        isProcessingDone(dateTime)) {
                                        blueBar.setRegionWidth((int) (tileSize * (((CraftingItem) inside).getInsideArtisan()
                                            .nesbatTime(dateTime))));
                                        batch.draw(blueBar, drawX, drawY + texture.getRegionHeight() + 10);
                                    } else {
                                        ((CraftingItem) inside).setReady(true);
                                        ((CraftingItem) inside).setProcessing(false);
                                    }
                                }
                            } else if (((CraftingItem) inside).isReady()) {
                                batch.draw(ready, drawX, drawY + texture.getRegionHeight() + 10, tileSize, tileSize);
                            }
                            batch.draw(texture, drawX, drawY);
//                            return;
                        } else {
                            batch.draw(texture, drawX, drawY, tileSize, tileSize);
                        }

                    }
                }
            }
        }
    }

    public void getBottomLeftCorner(int x, int y, Kashi kashi, ArrayList<ArrayList<Kashi>> tiles, ArrayList<Pair<Integer, Integer>> alreadyRenderedTiles, ArrayList<BottomLeft> bottomLeftTiles) {

        if (tiles == null || kashi == null || kashi.getInside() == null) {
            return;
        }

        if (x < 0 || y < 0 || x >= tiles.size()) {
            return;
        }

        ArrayList<Kashi> row = tiles.get(x - startX);
        if (row == null || y >= row.size()) {
            return;
        }

        Kashi tile = row.get(y - startY);
        if (tile == null || tile.getInside() == null) {
            return;
        }

        Class<?> clazz = kashi.getInside().getClass();
        if (kashi.getInside() instanceof ForagingTree || kashi.getInside() instanceof ForagingCrop ||
            kashi.getInside() instanceof AllTree || kashi.getInside() instanceof AllCrop) {
            return;
        }

        while (x >= 0) {
            Kashi currentTile = tiles.get(x - startX).get(y - startY);
            if (currentTile == null || currentTile.getInside() == null ||
                !currentTile.getInside().getClass().equals(clazz) && clazz != ForagingTree.class && clazz != ForagingCrop.class && clazz != AllTree.class && clazz != AllCrop.class) {
                break;
            }
            x--;
        }
        x++;

        while (y >= 0) {
            Kashi currentTile = tiles.get(x - startX).get(y - startY);
            if (currentTile == null || currentTile.getInside() == null ||
                !currentTile.getInside().getClass().equals(clazz) && clazz != ForagingTree.class && clazz != ForagingCrop.class && clazz != AllTree.class && clazz != AllCrop.class) {
                break;
            }
            y--;
        }
        y++;

        int startX = x;
        int startY = y;
        int currentY;
        int widthcounter = 0;
        int heightcounter = 0;

        for (int i = startX; i < tiles.size(); i++) {
            Kashi rowTile = tiles.get(i - startX).get(startY - startY);
            if (rowTile == null || rowTile.getInside() == null ||
                !rowTile.getInside().getClass().equals(clazz) && clazz != ForagingTree.class && clazz != ForagingCrop.class && clazz != AllTree.class && clazz != AllCrop.class) {
                break;
            }
            widthcounter++;

            for (currentY = startY; currentY < tiles.get(i - startX).size(); currentY++) {
                Kashi colTile = tiles.get(i).get(currentY - startY);
                if (colTile == null || colTile.getInside() == null ||
                    !colTile.getInside().getClass().equals(clazz) && clazz != ForagingTree.class && clazz != ForagingCrop.class && clazz != AllTree.class && clazz != AllCrop.class) {
                    break;
                }
                heightcounter++;
            }
        }

        BottomLeft bottomLeft;
        if (widthcounter > 1 || heightcounter > 1) {
            bottomLeft = new BottomLeft(x, y, widthcounter, heightcounter, true);
            bottomLeftTiles.add(bottomLeft);
            for (int i = startX; i < tiles.size(); i++) {
                Kashi rowTile = tiles.get(i - startX).get(startY - startY);
                if (rowTile == null || rowTile.getInside() == null ||
                    !rowTile.getInside().getClass().equals(clazz) && clazz != ForagingTree.class && clazz != ForagingCrop.class && clazz != AllTree.class && clazz != AllCrop.class) {
                    break;
                }

                for (currentY = startY; currentY < tiles.get(i - startX).size(); currentY++) {
                    if (i == startX && currentY == startY) continue;
                    Kashi colTile = tiles.get(i - startX).get(currentY - startY);
                    if (colTile == null || colTile.getInside() == null ||
                        !colTile.getInside().getClass().equals(clazz) && clazz != ForagingTree.class && clazz != ForagingCrop.class && clazz != AllTree.class && clazz != AllCrop.class) {
                        break;
                    }

                    Pair<Integer, Integer> coord = new Pair<>(i, currentY);
                    if (!alreadyRenderedTiles.contains(coord)) {
                        alreadyRenderedTiles.add(coord);
                    }
                }
            }
        } else {
            bottomLeft = new BottomLeft(x, y, widthcounter, heightcounter, false);
        }
    }

    private void renderPlayer() {
        Message send = new Message(new HashMap<>(), Message.Type.get_players, Message.Menu.game);
        Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        int numberOfPlayers = response.getIntFromBody("numberOfPlayers");
        ArrayList<Double> xs = new ArrayList<>();
        ArrayList<Double> ys = new ArrayList<>();
        int count = 1;
        for (int i = 0; i < numberOfPlayers; i++) {
            double x = response.getFromBody(String.valueOf(count));
            double y = response.getFromBody(String.valueOf(count + 1));
            xs.add(x);
            ys.add(y);
            count += 2;
        }


        for (int i = 0; i < numberOfPlayers; i++) {
            double first = xs.get(i);
            double second = ys.get(i);

            moveDirection = player.getMovingDirection();
            stateTime += Gdx.graphics.getDeltaTime();

            TextureRegion currentFrame = new TextureRegion();

            switch (player.getState()) {
                case Player.STATE_FAINTING:
                    currentFrame = playerAnimations.get(5)
                        .getKeyFrame(player.getFaintTimer(), false);
                    break;
                case Player.STATE_IDLE:
                    currentFrame = playerAnimations.get(player.getMovingDirection())
                        .getKeyFrame(stateTime, true);
                    break;
                case Player.EATING_STATE:
                    currentFrame = playerAnimations.get(6).getKeyFrame(player.getEatingTimer(), false);
                    Texture buff = showBuffEffect(player.getFoodBuff());
                    batch.draw(buff, (float) (first * ClientModel.TILE_SIZE), (float) (second * ClientModel.TILE_SIZE) + 60);
                    break;
            }

            batch.draw(currentFrame, (float) (first * ClientModel.TILE_SIZE), (float) (second * ClientModel.TILE_SIZE),
                ClientModel.TILE_SIZE, ClientModel.TILE_SIZE * 2);
        }

    }

    private void renderBrightness() {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        int currentHour = game.getCurrentDateTime().getHour();
        float brightness = 1f;

        // Calculate darkness intensity (6PM-6AM)
        if (currentHour >= 18 || currentHour <= 6) {
            float nightFactor;
            if (currentHour >= 18 && currentHour < 24) {
                nightFactor = (currentHour - 18) / 6f; // 6PM-12AM: 0 to 1
            } else {
                nightFactor = (6 - currentHour) / 6f; // 12AM-6AM: 1 to 0
            }
            brightness = 1f - (nightFactor * 0.7f); // More dramatic darkness
        }

        if (brightness < 1f) {
            // Save original batch state
            Matrix4 originalMatrix = batch.getProjectionMatrix();
            Color originalColor = new Color(batch.getColor());
            batch.setColor(0f, 0f, 0f, 1f - brightness);

            // Switch to screen coordinates
            batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

            // Draw full-screen overlay
            batch.draw(pixel,
                0, 0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());

            // Restore original state
            batch.setProjectionMatrix(originalMatrix);
            batch.setColor(originalColor);
        }
    }

    private void renderClock() {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        if (game != null) {
            DateTime time = game.getCurrentDateTime();
            if (time != null) {
                clock.render(batch, time, camera);
            }
        }
    }

    private void renderInventory() {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        if (ClientModel.getPlayer().isShowInventory()) {
            loadInventoryItems();
            Player player = ClientModel.getPlayer();
            Inventory playerInventory = player.getInventory();
            Map<Item, Pair<Integer, Integer>> inventory = player.getInventory().getItems();
            int selectedSlot = player.getInventory().getSelectedSlot();

            // Save the original projection matrix
            Matrix4 originalMatrix = batch.getProjectionMatrix();

            // Set up screen-space projection
            Matrix4 uiMatrix = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.setProjectionMatrix(uiMatrix);

            int slotSize = ClientModel.TILE_SIZE * 2;
            int numSlots = player.getInventory().getMaxQuantity();
            int startX = (Gdx.graphics.getWidth() - numSlots * slotSize) / 2;
            int y = ClientModel.TILE_SIZE * 2; // Distance from bottom of screen

            // Draw slots
            Texture slotTexture = new Texture(Gdx.files.internal("game/tiles/slot.png"));
            Texture highlightTexture = new Texture(Gdx.files.internal("game/tiles/highlight.png"));
            for (int i = 0; i < numSlots; i++) {
                int x = startX + i * slotSize;
                batch.draw(slotTexture, x, y, slotSize, slotSize);

                // Draw slot numbers (1-9)
                String slotNum = String.valueOf(i + 1);
                smallFont.draw(batch, slotNum, x + 2, y + slotSize - 2);
            }

            // Draw highlight for selected slot
            if (selectedSlot >= 0 && selectedSlot < numSlots) {
                int highlightX = startX + selectedSlot * slotSize;
                batch.draw(highlightTexture, highlightX, y, slotSize, slotSize);
            }

            // Draw items
            for (Map.Entry<Item, Pair<Integer, Integer>> entry : inventory.entrySet()) {
                Item item = entry.getKey();
                int quantity = entry.getValue().first;
                int index = entry.getValue().second;

                if (index < 0 || index >= numSlots) continue;

                TextureRegion itemTex = textures.get(item);
                if (itemTex != null) {
                    int x = startX + index * slotSize;
                    batch.draw(itemTex, x, y, slotSize, slotSize);

                    // Draw item count
                    String count = String.valueOf(quantity);
                    layout.setText(smallFont, count);
                    smallFont.draw(batch, count, x + slotSize - layout.width - 2, y + layout.height + 2);
                }
            }

            // Restore original projection matrix
            batch.setProjectionMatrix(originalMatrix);

            // Dispose temporary textures (or cache them if used frequently)
            slotTexture.dispose();
            highlightTexture.dispose();
        }
    }

    public void renderKalagEffect(SpriteBatch batch) {
        if (!isFlashingRed) return;

        // Update timer
        redFlashTimer -= Gdx.graphics.getDeltaTime();
        if (redFlashTimer <= 0) {
            isFlashingRed = false;
            return;
        }

        // Create single pixel texture (could optimize by creating this once)
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(1, 0, 0, 0.7f); // Red color with 70% opacity
        pixmap.fill();
        Texture pixel = new Texture(pixmap);
        pixmap.dispose();

        Matrix4 originalMatrix = batch.getProjectionMatrix();
        Color originalColor = new Color(batch.getColor());

        // Set up full-screen drawing
        batch.setProjectionMatrix(new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));

        // Draw red overlay

        batch.draw(pixel,
            0, 0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight());

        // Restore original state
        batch.setProjectionMatrix(originalMatrix);
        batch.setColor(originalColor);
        pixel.dispose();
    }

    public void startRedFlash() {
        redFlashTimer = 2f; // 2 seconds duration
        isFlashingRed = true;
    }

    public Batch getBatch() {
        return batch;
    }


    public Texture getPixel() {
        return pixel;
    }

    public Texture showBuffEffect(Buff buff) {
        int scale = 1;

        // لود عکس
        TextureRegion buffTexture = new TextureRegion();
        switch (buff.getBuffSkillType()) {
            case MiningSkill ->
                buffTexture = new TextureRegion(GameAssetManager.getGameAssetManager().getMiningTexture());
            case FarmingSkill ->
                buffTexture = new TextureRegion(GameAssetManager.getGameAssetManager().getFarmingTexture());
            case FishingSkill ->
                buffTexture = new TextureRegion(GameAssetManager.getGameAssetManager().getFishingTexture());
            case ForagingSkill ->
                buffTexture = new TextureRegion(GameAssetManager.getGameAssetManager().getForagingTexture());
            default -> buffTexture = new TextureRegion(GameAssetManager.getGameAssetManager().getSlotTexture());
        }
//        try {
//            buffTexture = GameAssetManager.getGameAssetManager().
//                getSkillAtlas().findRegion(buff.getBuffSkillType().name());
//        }catch (NullPointerException e) {
//            buffTexture = new TextureRegion(GameAssetManager.getGameAssetManager().getSlotTexture());
//        }
//        buffTexture.setMinSize(buffTexture.getMinWidth()*scale, buffTexture.getMinHeight()*scale);
        Texture buffTex = buffTexture.getTexture();
        Image buffImage = new Image(buffTexture);

        // تنظیم سایز و جایگاه دلخواه در صفحه (مثلا وسط)
        buffImage.setSize(100, 100);
        buffImage.setPosition(
            (Gdx.graphics.getWidth() - buffImage.getWidth()) / 2f,
            (Gdx.graphics.getHeight() - buffImage.getHeight()) / 2f + 50
        );

        // ابتدا شفافیت را صفر کن
        buffImage.getColor().a = 0f;

        // اضافه به stage
//        stage.addActor(buffImage);
//        Main.getBatch().draw(buffImage,Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        return buffTex;
        // اکشن fade in -> مکث -> fade out -> حذف
    }    // Other Screen methods

    public void update(float deltaTime) {
        if (!cameraInitialized) {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            camera.update();
            cameraInitialized = true;
            OrthographicCamera cam = camera;
            int tileSize = ClientModel.TILE_SIZE;

            startX = (int) ((cam.position.x - cam.viewportWidth / 2) / tileSize) - 2;
            startY = (int) ((cam.position.y - cam.viewportHeight / 2) / tileSize) - 2;
            endX = (int) ((cam.position.x + cam.viewportWidth / 2) / tileSize) + 2;
            endY = (int) ((cam.position.y + cam.viewportHeight / 2) / tileSize) + 2;

            startX = Math.max(0, startX);
            startY = Math.max(0, startY);
            endX = Math.min(ClientModel.mapWidth - 1, endX);
            endY = Math.min(ClientModel.mapHeight - 1, endY);

            HashMap<String, Object> body = new HashMap<>();
            body.put("startX", startX);
            body.put("startY", startY);
            body.put("endX", endX);
            body.put("endY", endY);
            io.github.group18.Network.common.models.Message send = new io.github.group18.Network.common.models.Message(body, Message.Type.get_kashis_using_2x_2y, Message.Menu.game);
            Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
            Gson gson = new Gson();
            Object usersArraylistOBJ = response.getBody().get("kashis");
            String userArraylist = gson.toJson(usersArraylistOBJ);
            Type userListType = new TypeToken<ArrayList<Kashi>>() {
            }.getType();
            tiles = gson.fromJson(userArraylist, userListType);
        }

        // Simple camera follow - no lerping, no prediction
        if (ClientModel.getPlayer() != null) {
            float playerX = (float) ClientModel.getPlayer().getX() * ClientModel.TILE_SIZE;
            float playerY = (float) ClientModel.getPlayer().getY() * ClientModel.TILE_SIZE;

            // Directly set camera position to player position
            camera.position.set(playerX, playerY, 0);

            // Clamp to map boundaries
            float halfWidth = camera.viewportWidth / 2;
            float halfHeight = camera.viewportHeight / 2;
            camera.position.x = Math.max(halfWidth, Math.min(playerX, ClientModel.mapWidth * ClientModel.TILE_SIZE - halfWidth));
            camera.position.y = Math.max(halfHeight, Math.min(playerY, ClientModel.mapHeight * ClientModel.TILE_SIZE - halfHeight));

            camera.update();
        }
    }

    public float getRedFlashTimer() {
        return redFlashTimer;
    }

    public void setRedFlashTimer(float redFlashTimer) {
        this.redFlashTimer = redFlashTimer;
    }

    public boolean isFlashingRed() {
        return isFlashingRed;
    }

    public void setFlashingRed(boolean flashingRed) {
        isFlashingRed = flashingRed;
    }

    public boolean isWalking() {
        return walking;
    }

    public void setWalking(boolean walking) {
        this.walking = walking;
    }

    public static TextureRegion getPlayerFrontImage() {
        return playerAnimations.get(0).getKeyFrame(0, false);
    }

    public StoreUI getBlackSmith() {
        return BlackSmith;
    }

    public void setBlackSmith(StoreUI blackSmith) {
        BlackSmith = blackSmith;
    }

    public StoreUI getCarpentersShop() {
        return CarpentersShop;
    }

    public void setCarpentersShop(StoreUI carpentersShop) {
        CarpentersShop = carpentersShop;
    }

    public StoreUI getFishShop() {
        return FishShop;
    }

    public void setFishShop(StoreUI fishShop) {
        FishShop = fishShop;
    }

    public StoreUI getJojaMart() {
        return JojaMart;
    }

    public void setJojaMart(StoreUI jojaMart) {
        JojaMart = jojaMart;
    }

    public StoreUI getMarniesRanch() {
        return MarniesRanch;
    }

    public void setMarniesRanch(StoreUI marniesRanch) {
        MarniesRanch = marniesRanch;
    }

    public StoreUI getPirresGeneralStore() {
        return PirresGeneralStore;
    }

    public void setPirresGeneralStore(StoreUI pirresGeneralStore) {
        PirresGeneralStore = pirresGeneralStore;
    }

    public StoreUI getTheStarDropSalooon() {
        return TheStarDropSalooon;
    }

    public void setTheStarDropSalooon(StoreUI theStarDropSalooon) {
        TheStarDropSalooon = theStarDropSalooon;
    }

    public NPCDialogView getNpcDialogView() {
        return npcDialogView;
    }

    public void setNpcDialogView(NPCDialogView npcDialogView) {
        this.npcDialogView = npcDialogView;
    }

    public NPCView getNpcView() {
        return npcView;
    }

    public void setNpcView(NPCView npcView) {
        this.npcView = npcView;
    }

    public GameController getGameController() {
        return gameController;
    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public TextureRegion[][] getTileTextures() {
        return tileTextures;
    }

    public void setTileTextures(TextureRegion[][] tileTextures) {
        this.tileTextures = tileTextures;
    }

    public Map<Object, TextureRegion> getTextures() {
        return textures;
    }

    public void setTextures(Map<Object, TextureRegion> textures) {
        this.textures = textures;
    }

    public Map<String, TextureRegion> getTexturesOnDrug() {
        return texturesOnDrug;
    }

    public void setTexturesOnDrug(Map<String, TextureRegion> texturesOnDrug) {
        this.texturesOnDrug = texturesOnDrug;
    }

    public BitmapFont getSmallFont() {
        return smallFont;
    }

    public void setSmallFont(BitmapFont smallFont) {
        this.smallFont = smallFont;
    }

    public GlyphLayout getLayout() {
        return layout;
    }

    public void setLayout(GlyphLayout layout) {
        this.layout = layout;
    }

    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }

    public void setPlayerAtlas(TextureAtlas playerAtlas) {
        this.playerAtlas = playerAtlas;
    }

    public float getStateTime() {
        return stateTime;
    }

    public void setStateTime(float stateTime) {
        this.stateTime = stateTime;
    }

    public int getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(int moveDirection) {
        this.moveDirection = moveDirection;
    }

    public void setPixel(Texture pixel) {
        this.pixel = pixel;
    }

    public ClockController getClock() {
        return clock;
    }

    public void setClock(ClockController clock) {
        this.clock = clock;
    }

    public EnergyController getEnergy() {
        return energy;
    }

    public void setEnergy(EnergyController energy) {
        this.energy = energy;
    }

    public boolean isSebastian_dialog() {
        return sebastian_dialog;
    }

    public void setSebastian_dialog(boolean sebastian_dialog) {
        this.sebastian_dialog = sebastian_dialog;
    }

    public boolean isAbigail_dialog() {
        return abigail_dialog;
    }

    public void setAbigail_dialog(boolean abigail_dialog) {
        this.abigail_dialog = abigail_dialog;
    }

    public boolean isHarvey_dialog() {
        return harvey_dialog;
    }

    public void setHarvey_dialog(boolean harvey_dialog) {
        this.harvey_dialog = harvey_dialog;
    }

    public boolean isLeah_dialog() {
        return leah_dialog;
    }

    public void setLeah_dialog(boolean leah_dialog) {
        this.leah_dialog = leah_dialog;
    }

    public boolean isRobin_dialog() {
        return robin_dialog;
    }

    public void setRobin_dialog(boolean robin_dialog) {
        this.robin_dialog = robin_dialog;
    }

    public boolean isSebastian_view() {
        return sebastian_view;
    }

    public void setSebastian_view(boolean sebastian_view) {
        this.sebastian_view = sebastian_view;
    }

    public boolean isAbigail_view() {
        return abigail_view;
    }

    public void setAbigail_view(boolean abigail_view) {
        this.abigail_view = abigail_view;
    }

    public boolean isHarvey_view() {
        return harvey_view;
    }

    public void setHarvey_view(boolean harvey_view) {
        this.harvey_view = harvey_view;
    }

    public boolean isLeah_view() {
        return leah_view;
    }

    public void setLeah_view(boolean leah_view) {
        this.leah_view = leah_view;
    }

    public boolean isRobin_view() {
        return robin_view;
    }

    public void setRobin_view(boolean robin_view) {
        this.robin_view = robin_view;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public void setCamera(OrthographicCamera camera) {
        this.camera = camera;
    }

    public boolean isCameraInitialized() {
        return cameraInitialized;
    }

    public boolean isShowPopup() {
        return showPopup;
    }

    public void setShowPopup(boolean showPopup) {
        this.showPopup = showPopup;
    }

    public String getPopupMessage() {
        return popupMessage;
    }

    public void setPopupMessage(String popupMessage) {
        this.popupMessage = popupMessage;
    }

    public Rectangle getPopupRect() {
        return popupRect;
    }

    public void setPopupRect(Rectangle popupRect) {
        this.popupRect = popupRect;
    }

    public boolean isWatering() {
        return watering;
    }

    public void setWatering(boolean watering) {
        this.watering = watering;
    }

    public float getPlantx() {
        return plantx;
    }

    public void setPlantx(float plantx) {
        this.plantx = plantx;
    }

    public float getPlanty() {
        return planty;
    }

    public void setPlanty(float planty) {
        this.planty = planty;
    }

    public float getWaterTimer() {
        return waterTimer;
    }

    public void setWaterTimer(float waterTimer) {
        this.waterTimer = waterTimer;
    }

    public String getPendingPlacementName() {
        return pendingPlacementName;
    }

    public void setPendingPlacementName(String pendingPlacementName) {
        this.pendingPlacementName = pendingPlacementName;
    }
}
