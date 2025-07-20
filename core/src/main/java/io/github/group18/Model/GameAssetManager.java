package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    private final Skin skinNormal = new Skin(Gdx.files.internal("ui/uiskin.json"));
    public static Texture menuBackground = new Texture(Gdx.files.internal("menu_bg.png"));

    Texture backgroundMapTexture = new Texture(Gdx.files.internal("map.png"));
    Texture map1Texture = new Texture(Gdx.files.internal("map1.png"));
    Texture map2Texture = new Texture(Gdx.files.internal("map2.png"));
    Texture map3Texture = new Texture(Gdx.files.internal("map3.png"));
    Texture map4Texture = new Texture(Gdx.files.internal("map4.png"));
    Texture mapDeafultTexture = new Texture(Gdx.files.internal("mapDeafult.png"));
    Texture barTexture = new Texture(Gdx.files.internal("EnergyBar/bar.png"));
    Texture greenBarTexture = new Texture(Gdx.files.internal("EnergyBar/green.png"));
    Texture bgGrass = new Texture(Gdx.files.internal("game/tiles/grass.png"));
    Texture bgCraftingMenu = new Texture(Gdx.files.internal("craftingSpriteSheet/craftingMenu.png"));
    Texture exitTexture = new Texture(Gdx.files.internal("craftingSpriteSheet/exit.png"));


    Texture scythe = new Texture(Gdx.files.internal("Tools/Scythe.png"));
    Texture axe = new Texture(Gdx.files.internal("Tools/Axe/Axe.png"));
    Texture pickaxe = new Texture(Gdx.files.internal("Tools/Pickaxe/Pickaxe.png"));

    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("testSpriteSheet/clockAtlas.atlas"));
    TextureAtlas craftingAtlas = new TextureAtlas(Gdx.files.internal("craftingSpriteSheet/crafting.atlas"));


    public static Texture defaultAvatar = new Texture(Gdx.files.internal("avatar.png"));

    private GameAssetManager() {}

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public Skin getSkinNormal() {
        return skinNormal;
    }

    public static Texture getBackground() {
        return menuBackground;
    }

    public Texture getBackgroundMapTexture() {
        return backgroundMapTexture;
    }

    public void setBackgroundMapTexture(Texture backgroundMapTexture) {
        this.backgroundMapTexture = backgroundMapTexture;
    }

    public Texture getMap1Texture() {
        return map1Texture;
    }

    public void setMap1Texture(Texture map1Texture) {
        this.map1Texture = map1Texture;
    }

    public Texture getMap2Texture() {
        return map2Texture;
    }

    public void setMap2Texture(Texture map2Texture) {
        this.map2Texture = map2Texture;
    }

    public Texture getMap3Texture() {
        return map3Texture;
    }

    public void setMap3Texture(Texture map3Texture) {
        this.map3Texture = map3Texture;
    }

    public Texture getMap4Texture() {
        return map4Texture;
    }

    public void setMap4Texture(Texture map4Texture) {
        this.map4Texture = map4Texture;
    }

    public Texture getMapDeafultTexture() {
        return mapDeafultTexture;
    }

    public void setMapDeafultTexture(Texture mapDeafultTexture) {
        this.mapDeafultTexture = mapDeafultTexture;
    }

    public static Texture getDefaultAvatar() {
        return defaultAvatar;
    }

    public static Texture getUserAvatar(String avatarPath) {
        try {
            // Handle both internal (assets/) and external (local) paths
            FileHandle file = avatarPath.startsWith("avatars/")
                ? Gdx.files.local(avatarPath)
                : Gdx.files.internal(avatarPath);

            if (file.exists()) {
                return new Texture(file);
            }
            Gdx.app.error("Avatar", "File not found: " + avatarPath);
        } catch (Exception e) {
            Gdx.app.error("Avatar", "Error loading: " + avatarPath, e);
        }
        return getDefaultAvatar(); // Fallback
    }

    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }

    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }

    public Texture getBgGrass() {
        return bgGrass;
    }

    public void setBgGrass(Texture bgGrass) {
        this.bgGrass = bgGrass;
    }

    public Texture getBarTexture() {
        return barTexture;
    }

    public void setBarTexture(Texture barTexture) {
        this.barTexture = barTexture;
    }

    public Texture getGreenBarTexture() {
        return greenBarTexture;
    }

    public void setGreenBarTexture(Texture greenBarTexture) {
        this.greenBarTexture = greenBarTexture;
    }

    public Texture getBgCraftingMenu() {
        return bgCraftingMenu;
    }

    public void setBgCraftingMenu(Texture bgCraftingMenu) {
        this.bgCraftingMenu = bgCraftingMenu;
    }

    public TextureAtlas getCraftingAtlas() {
        return craftingAtlas;
    }

    public void setCraftingAtlas(TextureAtlas craftingAtlas) {
        this.craftingAtlas = craftingAtlas;
    }

    public Texture getExitTexture() {
        return exitTexture;
    }

    public void setExitTexture(Texture exitTexture) {
        this.exitTexture = exitTexture;
    }


    public Texture getScythe() {
        return scythe;
    }

    public Texture getAxe() {
        return axe;
    }

    public Texture getPickaxe() {
        return pickaxe;
    }
}
