package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    Texture bgCookingMenu = new Texture(Gdx.files.internal("cookingRecipes/cook menu.jpg"));
    Texture exitTexture = new Texture(Gdx.files.internal("craftingSpriteSheet/exit.png"));
    Texture defaultInventoryItem = new Texture(Gdx.files.internal("Tools/Gold_Pan.png"));
    Texture slotTexture = new Texture(Gdx.files.internal("game/tiles/slot.png"));
    Texture highlightTexture = new Texture(Gdx.files.internal("game/tiles/highlight.png"));
    Texture scythe = new Texture(Gdx.files.internal("inventory/tools/Scythe.png"));
    Texture axe = new Texture(Gdx.files.internal("inventory/tools/Axe.png"));
    Texture pickaxe = new Texture(Gdx.files.internal("inventory/tools/Pickaxe.png"));
    Texture inventoryIconTexture = new Texture(Gdx.files.internal("cookingRecipes/inventoIcon.png"));
    Texture refrigeratorIconTexture = new Texture(Gdx.files.internal("cookingRecipes/refrIcon.png"));
    Texture farmingTexture = new Texture(Gdx.files.internal("SkillsIcon/FarmingSkill.png"));
    Texture fishingTexture = new Texture(Gdx.files.internal("SkillsIcon/FishingSkill.png"));
    Texture foragingTexture = new Texture(Gdx.files.internal("SkillsIcon/ForagingSkill.png"));
    Texture miningTexture = new Texture(Gdx.files.internal("SkillsIcon/MiningSkill.png"));



    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("testSpriteSheet/clockAtlas.atlas"));
    TextureAtlas craftingAtlas = new TextureAtlas(Gdx.files.internal("craftingSpriteSheet/crafting.atlas"));
    TextureAtlas cookingAtlas = new TextureAtlas(Gdx.files.internal("cookingRecipes/cooking.atlas"));
    TextureAtlas skillAtlas = new TextureAtlas(Gdx.files.internal("SkillsIcon/SkillIcons.atlas"));
    TextureAtlas playerAtlas = new TextureAtlas(Gdx.files.internal("game/character/sprites_player.atlas"));

    TextureRegion grass = new TextureRegion(new Texture(Gdx.files.internal("game/tiles/grass.png")));
    TextureRegion greenhouseBroken = new TextureRegion(new Texture(Gdx.files.internal("Greenhouse/greenhouse-broken.png")));
    TextureRegion greenhouse = new TextureRegion(new Texture(Gdx.files.internal("Greenhouse/greenhouse.png")));
    TextureRegion soilTexture = new TextureRegion(new Texture(Gdx.files.internal("game/tiles/soil.png")));


    public static Texture defaultAvatar = new Texture(Gdx.files.internal("avatar.png"));

    private GameAssetManager() {
    }

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

    public Texture getBgCookingMenu() {
        return bgCookingMenu;
    }

    public void setBgCookingMenu(Texture bgCookingMenu) {
        this.bgCookingMenu = bgCookingMenu;
    }

    public TextureAtlas getCookingAtlas() {
        return cookingAtlas;
    }

    public void setCookingAtlas(TextureAtlas cookingAtlas) {
        this.cookingAtlas = cookingAtlas;
    }

    public Texture getInventoryIconTexture() {
        return inventoryIconTexture;
    }

    public Texture getRefrigeratorIconTexture() {
        return refrigeratorIconTexture;
    }

    public TextureAtlas getSkillAtlas() {
        return skillAtlas;
    }

    public void setSkillAtlas(TextureAtlas skillAtlas) {
        this.skillAtlas = skillAtlas;
    }

    public Texture getFarmingTexture() {
        return farmingTexture;
    }

    public void setFarmingTexture(Texture farmingTexture) {
        this.farmingTexture = farmingTexture;
    }

    public Texture getFishingTexture() {
        return fishingTexture;
    }

    public void setFishingTexture(Texture fishingTexture) {
        this.fishingTexture = fishingTexture;
    }

    public Texture getForagingTexture() {
        return foragingTexture;
    }

    public void setForagingTexture(Texture foragingTexture) {
        this.foragingTexture = foragingTexture;
    }

    public Texture getMiningTexture() {
        return miningTexture;
    }

    public void setMiningTexture(Texture miningTexture) {
        this.miningTexture = miningTexture;
    }

    public static void setGameAssetManager(GameAssetManager gameAssetManager) {
        GameAssetManager.gameAssetManager = gameAssetManager;
    }

    public static Texture getMenuBackground() {
        return menuBackground;
    }

    public static void setMenuBackground(Texture menuBackground) {
        GameAssetManager.menuBackground = menuBackground;
    }

    public TextureAtlas getPlayerAtlas() {
        return playerAtlas;
    }

    public void setPlayerAtlas(TextureAtlas playerAtlas) {
        this.playerAtlas = playerAtlas;
    }

    public static void setDefaultAvatar(Texture defaultAvatar) {
        GameAssetManager.defaultAvatar = defaultAvatar;
    }

    public TextureRegion getGreenhouseBroken() {
        return greenhouseBroken;
    }

    public void setGreenhouseBroken(TextureRegion greenhouseBroken) {
        this.greenhouseBroken = greenhouseBroken;
    }

    public TextureRegion getGrass() {
        return grass;
    }

    public void setGrass(TextureRegion grass) {
        this.grass = grass;
    }

    public Texture getDefaultInventoryItem() {
        return defaultInventoryItem;
    }

    public void setDefaultInventoryItem(Texture defaultInventoryItem) {
        this.defaultInventoryItem = defaultInventoryItem;
    }

    public TextureRegion getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(TextureRegion greenhouse) {
        this.greenhouse = greenhouse;
    }

    public Texture getHighlightTexture() {
        return highlightTexture;
    }

    public void setHighlightTexture(Texture highlightTexture) {
        this.highlightTexture = highlightTexture;
    }

    public Texture getSlotTexture() {
        return slotTexture;
    }

    public void setSlotTexture(Texture slotTexture) {
        this.slotTexture = slotTexture;
    }

    public TextureRegion getSoilTexture() {
        return soilTexture;
    }

    public void setSoilTexture(TextureRegion soilTexture) {
        this.soilTexture = soilTexture;
    }

    public Texture getPickaxe() {
        return pickaxe;
    }

    public Texture getScythe() {
        return scythe;
    }


    public Texture getAxe() {
        return axe;
    }
}
