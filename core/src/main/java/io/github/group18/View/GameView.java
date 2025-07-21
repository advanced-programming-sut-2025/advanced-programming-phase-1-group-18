package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import io.github.group18.Controller.ClockController;
import io.github.group18.Controller.EnergyController;
import io.github.group18.Controller.LightningEffect;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Item;
import io.github.group18.Model.Items.Tool;

public class GameView {

    private final Game game;
    private SpriteBatch batch;
    private TextureRegion[][] tileTextures;
    private Map<Object, TextureRegion> textures;
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

    public GameView(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        clock = new ClockController();
        energy = new EnergyController();
        loadTextures();
        loadFont();
    }

    private void loadTextures() {
        textures = new HashMap<>();

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

                Kashi tile = tiles.get(x).get(y);
                if (tile == null) continue;

                Object inside = tile.getInside();
                if (!textures.containsKey(inside)) {
                    if (inside instanceof PictureModel pictureModel) {
                        try {
                            Texture tex = new Texture(Gdx.files.internal(pictureModel.getPath()));
                            if (inside instanceof GreenHouse greenHouse && !greenHouse.isStatus()) {
                                tex = GameAssetManager.getGameAssetManager().getGreenhouseBroken();
                            }
                            textures.put(inside, new TextureRegion(tex));
                        } catch (Exception e) {
                            System.out.println(inside.getClass().getSimpleName());
                            textures.put(inside, new TextureRegion(GameAssetManager.getGameAssetManager().getGrass()));
                        }
                    } else {
                        textures.put(inside, new TextureRegion(GameAssetManager.getGameAssetManager().getGrass()));
                    }
                }
            }
        }
    }

    private void loadInventoryItems() {
        for (Item item : game.getCurrentPlayer().getInventory().getItems().keySet()) {
            if (item instanceof PictureModel pictureModel) {
                String path = pictureModel.getPath();
                textures.put(item, new TextureRegion(new Texture(Gdx.files.internal(path))));
            } else {
                textures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getDefaultInventoryItem()));
            }
        }
    }

    public void render() {
        batch.setProjectionMatrix(game.getCamera().combined);
//        System.out.println(Game.getCurrentPlayer().getX() + " " + Game.getCurrentPlayer().getY());
        batch.begin();
        renderTiles();
        renderPlayer();
        renderInMyHandToolPlayer();
        renderInventory();
        renderClock();
        energy.render(batch);
        renderKalagEffect(batch);
        renderBrightness();
        walking = false;
        batch.end();
    }

    private void renderInMyHandToolPlayer() {
        Player player = game.getCurrentPlayer();
        Tool tool = player.getInMyHandTool();
        if (tool != null) {
            double first = player.getX();
            double second = player.getY();

            TextureRegion textureRegion = textures.get(tool);
            if (walking) {
                Random random = new Random();
                int x = 5 + random.nextInt(15);
                int y = 5 + random.nextInt(15);
                batch.draw(textureRegion, (float) (first * game.TILE_SIZE + game.TILE_SIZE / 2 + x), (float) (second * game.TILE_SIZE + game.TILE_SIZE / 4 + y), game.TILE_SIZE / 2, game.TILE_SIZE / 2);

            } else {
                batch.draw(textureRegion, (float) (first * game.TILE_SIZE + game.TILE_SIZE / 2), (float) (second * game.TILE_SIZE + game.TILE_SIZE / 4), game.TILE_SIZE / 2, game.TILE_SIZE / 2);
            }
        }
    }

    private void renderTiles() {
        ArrayList<ArrayList<Kashi>> tiles = game.getMap();
        OrthographicCamera cam = game.getCamera();
        int tileSize = game.TILE_SIZE;

        int startX = (int) ((cam.position.x - cam.viewportWidth / 2) / tileSize) - 2;
        int startY = (int) ((cam.position.y - cam.viewportHeight / 2) / tileSize) - 2;
        int endX = (int) ((cam.position.x + cam.viewportWidth / 2) / tileSize) + 2;
        int endY = (int) ((cam.position.y + cam.viewportHeight / 2) / tileSize) + 2;

        startX = Math.max(0, startX);
        startY = Math.max(0, startY);
        endX = Math.min(tiles.size() - 1, endX);
        endY = Math.min(tiles.get(0).size() - 1, endY);
        drawInitTiles(startX, startY, endX, endY, tiles);
        loadTiles(startX, startY, endX, endY, tiles);
        drawTiles(startX, startY, endX, endY, tiles);
    }

    private void drawInitTiles(int startX, int startY, int endX, int endY, ArrayList<ArrayList<Kashi>> tiles) {
        int tileSize = game.TILE_SIZE;
        TextureRegion texture = new TextureRegion(GameAssetManager.getGameAssetManager().getGrass());
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                float drawX = x * tileSize;
                float drawY = y * tileSize;
                batch.draw(texture, drawX, drawY, tileSize, tileSize);
            }
        }
    }

    private void drawTiles(int startX, int startY, int endX, int endY, ArrayList<ArrayList<Kashi>> tiles) {
        ArrayList<Pair<Integer, Integer>> alreadyRenderedTiles = new ArrayList<>();
        ArrayList<BottomLeft> bottomLeftTiles = new ArrayList<>();
        int tileSize = game.TILE_SIZE;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {

                getBottomLeftCorner(x, y, tiles.get(x).get(y), tiles, alreadyRenderedTiles, bottomLeftTiles);

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
                        Kashi tile = tiles.get(x).get(y);
                        Object inside = tile.getInside();
                        TextureRegion texture = textures.get(inside);
                        float drawX = x * tileSize;
                        float drawY = y * tileSize;
                        if (inside instanceof GreenHouse greenHouse) {
                            for (Player player : App.getCurrentGame().getPlayers()) {
//                                    System.out.println("yapperoni: " + player.getOwner().getUsername() + player.getMyFarm().getMyGreenHouse().isStatus() + " " + player.getMyFarm().getMyGreenHouse().hashCode());
                            }
//                                System.out.println(greenHouse.isStatus() + " " + greenHouse.hashCode());
                        }
                        if (inside instanceof GreenHouse greenHouse && greenHouse.isStatus()) {
//                                System.out.println("Probably not coming here?");
                            texture = new TextureRegion(GameAssetManager.getGameAssetManager().getGreenhouse());
                        }
                        batch.draw(texture, drawX, drawY, tileSize * bottomLeft.getWidth(), tileSize * bottomLeft.getHeight() / bottomLeft.getWidth());
                    } else {
                        //this is for normal blocks
                        Kashi tile = tiles.get(x).get(y);
                        Object inside = tile.getInside();

                        if (inside == null) {
                            if (tile.isShokhmZadeh()) {
                                float drawX = x * tileSize;
                                float drawY = y * tileSize;
                                batch.draw(new TextureRegion(GameAssetManager.getGameAssetManager().getSoilTexture()), drawX, drawY, tileSize, tileSize);
                                continue;
                            } else {
                                continue;
                            }
                        }


                        TextureRegion texture = textures.get(inside);

                        float drawX = x * tileSize;
                        float drawY = y * tileSize;
                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
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

        ArrayList<Kashi> row = tiles.get(x);
        if (row == null || y >= row.size()) {
            return;
        }

        Kashi tile = row.get(y);
        if (tile == null || tile.getInside() == null) {
            return;
        }

        Class<?> clazz = kashi.getInside().getClass();

        while (x >= 0) {
            Kashi currentTile = tiles.get(x).get(y);
            if (currentTile == null || currentTile.getInside() == null ||
                !currentTile.getInside().getClass().equals(clazz)) {
                break;
            }
            x--;
        }
        x++;

        while (y >= 0) {
            Kashi currentTile = tiles.get(x).get(y);
            if (currentTile == null || currentTile.getInside() == null ||
                !currentTile.getInside().getClass().equals(clazz)) {
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
            Kashi rowTile = tiles.get(i).get(startY);
            if (rowTile == null || rowTile.getInside() == null ||
                !rowTile.getInside().getClass().equals(clazz)) {
                break;
            }
            widthcounter++;

            for (currentY = startY; currentY < tiles.get(i).size(); currentY++) {
                Kashi colTile = tiles.get(i).get(currentY);
                if (colTile == null || colTile.getInside() == null ||
                    !colTile.getInside().getClass().equals(clazz)) {
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
                Kashi rowTile = tiles.get(i).get(startY);
                if (rowTile == null || rowTile.getInside() == null ||
                    !rowTile.getInside().getClass().equals(clazz)) {
                    break;
                }

                for (currentY = startY; currentY < tiles.get(i).size(); currentY++) {
                    if (i == startX && currentY == startY) continue;
                    Kashi colTile = tiles.get(i).get(currentY);
                    if (colTile == null || colTile.getInside() == null ||
                        !colTile.getInside().getClass().equals(clazz)) {
                        break;
                    }

                    Pair<Integer, Integer> coord = new Pair<>(i, currentY);
                    if (!alreadyRenderedTiles.contains(coord)) {
                        alreadyRenderedTiles.add(coord);
                    }
                }
            }
//            System.out.println("X: " + x + " Y: " + y + " width: " + widthcounter + " height: " + heightcounter);
        } else {
            bottomLeft = new BottomLeft(x, y, widthcounter, heightcounter, false);
        }
    }

    private void renderPlayer() {
        Player player = game.getCurrentPlayer();
        double first = player.getX();
        double second = player.getY();

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
        }

        batch.draw(currentFrame, (float) (first * game.TILE_SIZE), (float) (second * game.TILE_SIZE), game.TILE_SIZE, game.TILE_SIZE * 2);
    }

    private void renderBrightness() {
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
        if (App.getCurrentGame() != null) {
            DateTime time = App.getCurrentGame().getCurrentDateTime();
            if (time != null) {
                clock.render(batch, time, game.getCamera());
            }
        }
    }

    private void renderInventory() {
        if (game.getCurrentPlayer().isShowInventory()) {
            loadInventoryItems();
            Player player = game.getCurrentPlayer();
            Inventory playerInventory = player.getInventory();
            Map<Item, Pair<Integer, Integer>> inventory = player.getInventory().getItems();
            int selectedSlot = player.getInventory().getSelectedSlot();

            // Save the original projection matrix
            Matrix4 originalMatrix = batch.getProjectionMatrix();

            // Set up screen-space projection
            Matrix4 uiMatrix = new Matrix4().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            batch.setProjectionMatrix(uiMatrix);

            int slotSize = game.TILE_SIZE * 2;
            int numSlots = player.getInventory().getMaxQuantity();
            int startX = (Gdx.graphics.getWidth() - numSlots * slotSize) / 2;
            int y = game.TILE_SIZE * 2; // Distance from bottom of screen

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
        batch.begin();
        batch.draw(pixel,
            0, 0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight());
        batch.end();

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

    public static TextureRegion getPlayerFrontImage()
    {
        return playerAnimations.get(0).getKeyFrame(0, false);
    }
}
