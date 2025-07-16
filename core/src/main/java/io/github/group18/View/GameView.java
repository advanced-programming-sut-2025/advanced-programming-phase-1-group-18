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

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;
import io.github.group18.Controller.ClockController;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Item;


public class GameView {

    private final Game game;
    private SpriteBatch batch;
    private TextureRegion[][] tileTextures;
    private Map<Object, TextureRegion> textures;
    private BitmapFont smallFont;
    private GlyphLayout layout = new GlyphLayout();
    private TextureAtlas playerAtlas;
    private final ArrayList<Animation<TextureRegion>> playerAnimations = new ArrayList<>();
    private float stateTime = 0f;
    private int moveDirection = 0;
    private Texture pixel; // Add this
    private ClockController clock;


    public GameView(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        clock = new ClockController();
        loadTextures();
        loadFont();
    }

    private void loadTextures() {
        textures = new HashMap<>();

        playerAtlas = new TextureAtlas(Gdx.files.internal("game/character/sprites_player.atlas"));

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

    public void render() {
        batch.setProjectionMatrix(game.getCamera().combined);
        batch.begin();
        renderTiles();
        renderPlayer();
        renderInventory();
        renderClock();
        renderBrightness();
        batch.end();
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
        loadTiles(startX, startY, endX, endY, tiles);
        drawtiles(startX, startY, endX, endY, tiles);
    }

    private void drawtiles(int startX, int startY, int endX, int endY, ArrayList<ArrayList<Kashi>> tiles) {
        int tileSize = game.TILE_SIZE;
        for (int x = startX; x <= endX; x++) {
            for (int y = startY; y <= endY; y++) {
                Kashi tile = tiles.get(x).get(y);
                if (tile == null) continue;

                Object inside = tile.getInside();
                TextureRegion texture = textures.get(inside);
                if (texture == null) {
                    System.out.println("this aint happenin");
                    texture = textures.get("game/tiles/grass.png");
                }

                float drawX = x * tileSize;
                float drawY = y * tileSize;
                batch.draw(texture, drawX, drawY, tileSize, tileSize);
            }
        }
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
                            textures.put(inside, new TextureRegion(tex));
                        } catch (Exception e) {
                            System.out.println(inside.getClass().getSimpleName());
                            textures.put(inside, new TextureRegion(new Texture(Gdx.files.internal("game/tiles/grass.png"))));
                        }
                    } else {
                        textures.put(inside, new TextureRegion(new Texture(Gdx.files.internal("game/tiles/grass.png"))));
                    }
                }
            }
        }
    }

    private void renderPlayer() {
        double first = game.getCurrentPlayer().getX();
        double second = game.getCurrentPlayer().getY();

        moveDirection = game.getCurrentPlayer().getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();

        Animation<TextureRegion> currentAnimation = playerAnimations.get(moveDirection);
        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, (float) (first * game.TILE_SIZE), (float) (second * game.TILE_SIZE), game.TILE_SIZE, game.TILE_SIZE * 2);
    }

    private void renderBrightness() {
        int currentHour = game.getCurrentDateTime().getHour();
        float brightness = 1f;

        if (currentHour >= 18 || currentHour <= 6) {
            float nightFactor;
            if (currentHour >= 18 && currentHour < 24) {
                nightFactor = (currentHour - 18) / 6f;
            } else {
                nightFactor = (6 - currentHour) / 6f;
            }
            brightness = 1f - (nightFactor * 0.5f);
        }

        if (brightness < 1f) {
            float overlayAlpha = 1f - brightness;

            // Get camera dimensions
            float camX = game.getCamera().position.x;
            float camY = game.getCamera().position.y;
            float viewportWidth = game.getCamera().viewportWidth;
            float viewportHeight = game.getCamera().viewportHeight;

            // Calculate world-space rectangle that covers the entire viewport
            float left = camX - viewportWidth / 2;
            float bottom = camY - viewportHeight / 2;

            batch.setColor(0f, 0f, 0f, overlayAlpha);
            batch.draw(pixel,
                left, bottom,           // Bottom-left corner
                viewportWidth,          // Width
                viewportHeight);        // Height
            batch.setColor(1f, 1f, 1f, 1f);
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

            int slotSize = game.TILE_SIZE / 2;
            int numSlots = player.getInventory().getMaxQuantity();
            int startX = (Gdx.graphics.getWidth() - numSlots * slotSize) / 2;
            int y = game.TILE_SIZE / 2; // Distance from bottom of screen

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

    private void loadInventoryItems() {
        for (Item item : game.getCurrentPlayer().getInventory().getItems().keySet()) {
            if (item instanceof PictureModel pictureModel) {
                String path = pictureModel.getPath();
                textures.put(item, new TextureRegion(new Texture(Gdx.files.internal(path))));
            } else {
                textures.put(item, new TextureRegion(new Texture(Gdx.files.internal("Tools/Gold_Pan.png"))));
            }
        }
    }

    public Batch getBatch() {
        return batch;
    }


    public Texture getPixel() {
        return pixel;
    }

}
