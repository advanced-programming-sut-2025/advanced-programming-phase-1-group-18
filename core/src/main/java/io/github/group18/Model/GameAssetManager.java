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
    public static Texture menuBackground = new Texture(Gdx.files.internal("menu_bg.png"));

    Texture backgroundMapTexture = new Texture(Gdx.files.internal("map.png"));
    Texture map1Texture = new Texture(Gdx.files.internal("map1.png"));
    Texture map2Texture = new Texture(Gdx.files.internal("map2.png"));
    Texture map3Texture = new Texture(Gdx.files.internal("map3.png"));
    Texture map4Texture = new Texture(Gdx.files.internal("map4.png"));
    Texture mapDeafultTexture = new Texture(Gdx.files.internal("mapDeafult.png"));
    Texture bgGrass = new Texture(Gdx.files.internal("game/tiles/grass.png"));



    TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("testSpriteSheet/clockAtlas.atlas"));


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
}

/*07/16/2025  02:38 PM               342 Autumn%27s_Bounty.png
07/16/2025  02:38 PM             1,815 Basic_Fertilizer.png
07/16/2025  02:38 PM             2,061 Blackberry.png
07/16/2025  02:38 PM             1,809 Blackberry_Cobbler.png
07/16/2025  02:38 PM             1,793 Blue_Dried_Fruit.png
07/16/2025  02:38 PM             1,586 Blue_Jelly.png
07/16/2025  02:38 PM             1,685 Blue_Wine.png
07/16/2025  02:38 PM             1,744 Botanist.png
07/16/2025  02:38 PM             1,141 Bread.png
07/16/2025  02:38 PM             1,920 Brown_Dried_Fruit.png
07/16/2025  02:38 PM             1,570 Brown_Jelly.png
07/16/2025  02:38 PM             1,799 Brown_Juice.png
07/16/2025  02:38 PM             1,706 Brown_Pickles.png
07/16/2025  02:38 PM             1,666 Brown_Wine.png
07/16/2025  02:38 PM             1,784 Bundle_Blue.png
07/16/2025  02:38 PM             1,796 Bundle_Green.png
07/16/2025  02:38 PM             1,755 Bundle_Orange.png
07/16/2025  02:38 PM             1,793 Bundle_Purple.png
07/16/2025  02:38 PM             1,746 Bundle_Red.png
07/16/2025  02:38 PM             1,798 Bundle_Teal.png
07/16/2025  02:38 PM             1,784 Bundle_Yellow.png
07/16/2025  02:38 PM             1,857 Cactus_Fruit.png
07/16/2025  02:38 PM             1,750 Cave_Carrot.png
07/16/2025  02:38 PM             1,553 Chanterelle.png
07/16/2025  02:38 PM             1,859 Charcoal_Kiln.png
07/16/2025  02:38 PM             1,662 Chowder.png
07/16/2025  02:38 PM             1,786 Clam.png
07/16/2025  02:38 PM             1,839 Cockle.png
07/16/2025  02:38 PM             1,780 Coconut.png
07/16/2025  02:38 PM             2,415 CommonMushroom.png
07/16/2025  02:38 PM             1,509 Common_Mushroom.png
07/16/2025  02:38 PM             2,029 Cooking_Channel.png
07/16/2025  02:38 PM             1,613 Cookout_Kit.png
07/16/2025  02:38 PM             2,223 Coral.png
07/16/2025  02:38 PM             2,010 Crocus.png
07/16/2025  02:38 PM             1,980 CrystalFruit.png
07/16/2025  02:38 PM             2,122 Daffodil.png
07/16/2025  02:38 PM             2,044 Dandelion.png
07/16/2025  02:38 PM             1,577 Dark_Blue_Dried_Fruit.png
07/16/2025  02:38 PM             1,516 Dark_Blue_Jelly.png
07/16/2025  02:38 PM             1,660 Dark_Blue_Wine.png
07/16/2025  02:38 PM             1,749 Dark_Pink_Pickles.png
07/16/2025  02:38 PM             1,594 Dark_Purple_Jelly.png
07/16/2025  02:38 PM             1,701 Dark_Purple_Pickles.png
07/16/2025  02:38 PM             1,698 Dark_Purple_Wine.png
07/16/2025  02:38 PM             1,542 Defense.png
07/16/2025  02:38 PM             1,937 Dried_Chanterelles.png
07/16/2025  02:38 PM             1,935 Dried_Common_Mushrooms.png
07/16/2025  02:38 PM             1,942 Dried_Magma_Caps.png
07/16/2025  02:38 PM             1,916 Dried_Morels.png
07/16/2025  02:38 PM             1,942 Dried_Purple_Mushrooms.png
07/16/2025  02:38 PM             1,012 Egg.png
07/16/2025  02:38 PM             1,620 Eggplant.png
07/16/2025  02:38 PM               996 Energy.png
07/16/2025  02:38 PM             1,661 Fall_Seeds.png
07/16/2025  02:38 PM             1,654 Fiber_Seeds.png
07/16/2025  02:38 PM             1,993 FiddleheadFern.png
07/16/2025  02:38 PM             1,754 Fiddlehead_Risotto.png
07/16/2025  02:38 PM             1,726 Field_Snack.png
07/16/2025  02:38 PM             2,620 Fish_Pond.png
07/16/2025  02:38 PM             1,650 Fish_Stew.png
07/16/2025  02:38 PM             1,620 Foraging.png
07/16/2025  02:38 PM             1,537 Forester.png
07/16/2025  02:38 PM             2,115 Fried_Mushroom.png
07/16/2025  02:38 PM             1,894 Gatherer.png
07/16/2025  02:38 PM             1,741 Ginger.png
07/16/2025  02:38 PM             1,696 Ginger_Ale.png
07/16/2025  02:38 PM             2,187 Grape.png
07/16/2025  02:38 PM             1,780 Green_Dried_Fruit.png
07/16/2025  02:38 PM             1,552 Green_Jelly.png
07/16/2025  02:38 PM             1,801 Green_Juice.png
07/16/2025  02:38 PM             1,667 Green_Pickles.png
07/16/2025  02:38 PM             1,652 Green_Wine.png
07/16/2025  02:38 PM             1,604 Hazelnut.png
07/16/2025  02:38 PM             1,824 Holly.png
07/16/2025  02:38 PM             1,373 Hot_Pepper.png
07/16/2025  02:38 PM               256 Krobus_Icon.png
07/16/2025  02:38 PM             1,988 Leek.png
07/16/2025  02:38 PM             1,861 Life_Elixir.png
07/16/2025  02:38 PM             2,011 Lightning_Rod.png
07/16/2025  02:38 PM               294 Lost_Book.png
07/16/2025  02:38 PM             1,656 Lumberjack.png
07/16/2025  02:38 PM             1,790 Magma_Cap.png
07/16/2025  02:38 PM             1,493 Maki_Roll.png
07/16/2025  02:38 PM             1,765 Miner%27s_Treat.png
07/16/2025  02:38 PM             1,798 Morel.png
07/16/2025  02:38 PM             1,823 Moss_Soup.png
07/16/2025  02:38 PM             1,231 Mussel.png
07/16/2025  02:38 PM             1,448 Nauseated.png
07/16/2025  02:38 PM             1,983 Nautilus_Shell.png
07/16/2025  02:38 PM             1,745 Orange_Dried_Fruit.png
07/16/2025  02:38 PM             1,594 Orange_Jelly.png
07/16/2025  02:38 PM             1,831 Orange_Juice.png
07/16/2025  02:38 PM             1,718 Orange_Pickles.png
07/16/2025  02:38 PM             1,650 Orange_Wine.png
07/16/2025  02:38 PM             1,413 Oyster.png
07/16/2025  02:38 PM               408 Pancakes.png
07/16/2025  02:38 PM             2,032 Pineapple.png
07/16/2025  02:38 PM             1,565 Plum_Pudding.png
07/16/2025  02:38 PM             1,373 Poison.png
07/16/2025  02:38 PM             1,767 Pumpkin.png
07/16/2025  02:38 PM             1,783 PurpleMushroom.png
07/16/2025  02:38 PM             1,725 Purple_Dried_Fruit.png
07/16/2025  02:38 PM             1,606 Purple_Jelly.png
07/16/2025  02:38 PM             1,689 Purple_Wine.png
07/16/2025  02:38 PM             1,834 Quality_Bobber.png
07/16/2025  02:38 PM             2,075 Rainbow_Shell.png
07/16/2025  02:38 PM             1,984 Rain_Totem.png
07/16/2025  02:38 PM             1,786 Raisins.png
07/16/2025  02:38 PM             1,776 RedMushroom.png
07/16/2025  02:38 PM             1,806 Red_Dried_Fruit.png
07/16/2025  02:38 PM             1,556 Red_Jelly.png
07/16/2025  02:38 PM             1,636 Red_Wine.png
07/16/2025  02:38 PM             1,786 Roasted_Hazelnuts.png
07/16/2025  02:38 PM             2,128 Roots_Platter.png
07/16/2025  02:38 PM             2,112 Salad.png
07/16/2025  02:38 PM             1,818 Salmonberry.png
07/16/2025  02:38 PM               210 Sap.png
07/16/2025  02:38 PM             1,937 Seaweed.png
07/16/2025  02:38 PM             1,926 Sea_Urchin.png
07/16/2025  02:38 PM             1,890 Shrimp_Cocktail.png
07/16/2025  02:38 PM             1,415 SnowYam.png
07/16/2025  02:38 PM             1,977 SpiceBerry.png
07/16/2025  02:38 PM             1,666 SpringOnion.png
07/16/2025  02:38 PM             1,694 Spring_Seeds.png
07/16/2025  02:38 PM             2,985 Statue_Of_Blessings.png
07/16/2025  02:38 PM             1,760 Stir_Fry.png
07/16/2025  02:38 PM             1,879 Stuffing.png
07/16/2025  02:38 PM             1,531 Summer.png
07/16/2025  02:38 PM             1,698 Summer_Seeds.png
07/16/2025  02:38 PM             2,055 Survival_Burger.png
07/16/2025  02:38 PM             1,864 SweetPea.png
07/16/2025  02:38 PM             2,323 Tapper.png
07/16/2025  02:38 PM             1,653 Tapper_Icon.png
07/16/2025  02:38 PM             1,798 Tent_Kit.png
07/16/2025  02:38 PM             1,502 Time_Icon.png
07/16/2025  02:38 PM             1,881 Tom_Kha_Soup.png
07/16/2025  02:38 PM             1,296 Torch.png
07/16/2025  02:38 PM             1,436 Tracker.png
07/16/2025  02:38 PM             1,014 Trap_Bobber.png
07/16/2025  02:38 PM             1,713 Tree_Fertilizer.png
07/16/2025  02:38 PM               342 Tropical_Curry.png
07/16/2025  02:38 PM               355 Truffle.png
07/16/2025  02:38 PM             2,029 Warp_Totem_Beach.png
07/16/2025  02:38 PM             2,034 Warp_Totem_Farm.png
07/16/2025  02:38 PM             2,009 Warp_Totem_Mountains.png
07/16/2025  02:38 PM             1,514 Wheat_Flour.png
07/16/2025  02:38 PM             1,569 White_Juice.png
07/16/2025  02:38 PM             1,694 White_Pickles.png
07/16/2025  02:38 PM             2,056 WildHorseradish.png
07/16/2025  02:38 PM             1,557 WildPlum.png
07/16/2025  02:38 PM               236 Wild_Seeds_Stage_1.png
07/16/2025  02:38 PM               282 Wild_Seeds_Stage_2.png
07/16/2025  02:38 PM               841 Wild_Seeds_Stage_3.png
07/16/2025  02:38 PM             1,587 WinterRoot.png
07/16/2025  02:38 PM             1,769 Winter_Seeds.png
07/16/2025  02:38 PM             1,396 Yam.png
07/16/2025  02:38 PM             1,537 Yellow_Juice.png
07/16/2025  02:38 PM             1,723 Yellow_Pickles.png*/
