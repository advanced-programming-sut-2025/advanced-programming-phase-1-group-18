package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.utils.Array;
import io.github.group18.Controller.ClockController;
import io.github.group18.Model.App;
import io.github.group18.Model.DateTime;
import io.github.group18.Model.Game;
import io.github.group18.Model.Kashi;

public class GameView {

    private final Game game;
    private SpriteBatch batch;
    private TextureRegion[][] tileTextures;
    private Map<String, TextureRegion> textures;
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
    }

    private void loadTextures() {
        textures = new HashMap<>();

//        for (TileDescriptionId id : TileDescriptionId.values()) {
//            String path = id.getIconPath();
//            textures.put(id.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
//        }
//        for (ItemDescriptionId id : ItemDescriptionId.values()) {
//            String path = id.getIconPath();
//            textures.put(id.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
//        }
//        for (CarrotStages cs : CarrotStages.values()) {
//            String path = cs.getIconPath();
//            textures.put(cs.name(), new TextureRegion(new Texture(Gdx.files.internal(path))));
//        }

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


    public void render() {
        batch.setProjectionMatrix(game.getCamera().combined);
        batch.begin();
        renderClock();
        renderTiles();
        renderPlayer();
        renderBrightness();
        batch.end();
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


    private void renderTiles() {
//        ArrayList<ArrayList<Kashi>> tiles = game.getMap();
//
//        float camX = game.getCamera().position.x;
//        float camY = game.getCamera().position.y;
//        float viewportWidth = game.getCamera().viewportWidth;
//        float viewportHeight = game.getCamera().viewportHeight;
//
//        int tileSize = game.TILE_SIZE;
//
//        float cameraLeft = camX - viewportWidth / 2;
//        float cameraBottom = camY - viewportHeight / 2;
//
//        int startX = Math.max(0, (int) (cameraLeft / tileSize) - 2);
//        int startY = Math.max(0, (int) (cameraBottom / tileSize) - 2);
//        int endX = Math.min(tiles.size(), (int) ((camX + viewportWidth / 2) / tileSize) + 2);
//        int endY = Math.min(tiles.get(0).size(), (int) ((camY + viewportHeight / 2) / tileSize) + 2);
//
//        // Render base tiles
//        for (int x = startX; x < endX; x++) {
//            for (int y = startY; y < endY; y++) {
//                Kashi id = tiles.get(x).get(y);
//                if (id != null) {
//                    float drawX = x * tileSize - cameraLeft;
//                    float drawY = y * tileSize - cameraBottom;
//
////                    GrowingCrop crop = game.getGrowingCrops().get(new Point(x, y));
////                    if (crop != null && crop.watered()) {
////                        batch.setColor(0.7f, 0.7f, 0.7f, 1f);
////                    } else {
////                        batch.setColor(1f, 1f, 1f, 1f);
////                    }
//
//                    TextureRegion texture = textures.get(id.name());
//                    if (texture != null) {
//                        batch.draw(texture, drawX, drawY, tileSize, tileSize);
//                    }
//                }
//            }
//        }
//
//        // Render crops on top
//        for (Map.Entry<Point, GrowingCrop> entry : game.getGrowingCrops().entrySet()) {
//            Point point = entry.getKey();
//            GrowingCrop crop = entry.getValue();
//
//            int x = point.x;
//            int y = point.y;
//
//            if (x >= startX && x < endX && y >= startY && y < endY) {
//                float drawX = x * tileSize - cameraLeft;
//                float drawY = y * tileSize - cameraBottom;
//
//                int growth = crop.getGrowth();
//                CarrotStages cs;
//
//                if (growth < 2) cs = CarrotStages.CARROT_STAGE_1;
//                else if (growth < 4) cs = CarrotStages.CARROT_STAGE_2;
//                else if (growth < 6) cs = CarrotStages.CARROT_STAGE_3;
//                else cs = CarrotStages.CARROT_STAGE_4;
//
//                TextureRegion cropTexture = textures.get(cs.name());
//                if (cropTexture != null) {
//                    batch.setColor(1f, 1f, 1f, 1f);
//                    batch.draw(cropTexture, drawX, drawY, tileSize, tileSize);
//                }
//            }
//        }
//
//        batch.setColor(1f, 1f, 1f, 1f);
    }

    private void renderPlayer() {
        double first = game.getCurrentPlayer().getX();
        double second = game.getCurrentPlayer().getY();

        moveDirection = game.getCurrentPlayer().getMovingDirection();

        stateTime += Gdx.graphics.getDeltaTime();

        Animation<TextureRegion> currentAnimation = playerAnimations.get(moveDirection);
        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);

        batch.draw(currentFrame, (float) (first * game.TILE_SIZE), (float) (second * game.TILE_SIZE), game.TILE_SIZE, game.TILE_SIZE * 2);
//        renderInventory();
    }

//    private void renderInventory() {
//        Player player = game.getPlayer();
//        Map<ItemDescriptionId, Pair<Integer, Integer>> inventory = player.getInventory();
//        int selectedSlot = player.getSelectedSlot(); // Assuming you have this method
//
//        int screenWidth = Gdx.graphics.getWidth();
//        int slotSize = StardewMini.TILE_SIZE /2;
//        int numSlots = player.getMaxInventorySize();
//        int startX = (screenWidth - numSlots * slotSize) / 2;
//        int y = StardewMini.TILE_SIZE /2;
//
//        for (int i = 0; i < numSlots; i++) {
//            int x = startX + i * slotSize;
//
//            batch.draw(textures.get(TileDescriptionId.SLOT.name()), x, y, slotSize, slotSize);
//
//            String slotNum = String.valueOf(i + 1);
//            smallFont.draw(batch, slotNum, x + 2, y + slotSize - 2);
//        }
//
//        // Highlight selected slot
//        if (selectedSlot >= 0 && selectedSlot < numSlots) {
//            int highlightX = startX + selectedSlot * slotSize;
//            batch.draw(textures.get(TileDescriptionId.HIGHLIGHT.name()), highlightX, y, slotSize, slotSize);
//        }
//
//        for (Map.Entry<ItemDescriptionId, Pair<Integer, Integer>> entry : inventory.entrySet()) {
//            ItemDescriptionId id = entry.getKey();
//            int quantity = entry.getValue().first;
//            int index = entry.getValue().second;
//
//            if (index < 0 || index >= numSlots) continue;
//
//            TextureRegion itemTex = textures.get(id.name());
//            if (itemTex != null) {
//                int x = startX + index * slotSize;
//                batch.draw(itemTex, x, y, slotSize, slotSize);
//
//                // Draw item quantity at bottom-right corner
//                String count = String.valueOf(quantity);
//                layout.setText(smallFont, count);
//                smallFont.draw(batch, count, x + slotSize - layout.width - 2, y + layout.height + 2);
//            }
//        }
//    }

    public Batch getBatch() {
        return batch;
    }


    public Texture getPixel() {
        return pixel;
    }

}
