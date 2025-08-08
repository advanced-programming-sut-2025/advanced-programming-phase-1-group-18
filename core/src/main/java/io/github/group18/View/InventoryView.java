package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import io.github.group18.Controller.MainMenuController;
import io.github.group18.Main;
import io.github.group18.Model.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.Model.Items.Item;

import javax.swing.event.ChangeEvent;


import java.util.*;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.*;

public class InventoryView {

    private final Window inventoryWindow;
    private final Texture slotTexture, closeTexture;
    private final Texture[] tabTextures;
    private final ImageButton closeButton;
    private final Array<ImageButton> tabButtons;
    private final Table contentTable;
    private final Skin skin;
    private final Label tooltipLabel;
    private final Label skillTooltipLabel;

    private long tooltipStartTime = 0;
    private long skillTooltipStartTime = 0;

    private int hoveredTabIndex = -1;
    private Image hoveredSkillIcon = null;

   // private String mammad;

    //for Music
    private Music[] musicTracks;
    private Music currentMusic;
    //for map
    private Texture minimapTexture;
    private Image minimapImage;

    private final Map<Item, TextureRegion> itemTextures = new HashMap<>();

    public InventoryView(Skin skin) {
        this.skin = skin;

        slotTexture = new Texture(Gdx.files.internal("inventory/slot.png"));
        closeTexture = new Texture(Gdx.files.internal("inventory/close.png"));

        tabTextures = new Texture[] {
            new Texture(Gdx.files.internal("inventory/Inventory_tab.png")),
            new Texture(Gdx.files.internal("inventory/skill_tab.png")),
            new Texture(Gdx.files.internal("inventory/tab_crafting.png")),
            new Texture(Gdx.files.internal("inventory/tab_cooking.png")),
            new Texture(Gdx.files.internal("inventory/settings.png")),
            new Texture(Gdx.files.internal("inventory/tab_map.png")),
            new Texture(Gdx.files.internal("inventory/tab_chat.png")),
            new Texture(Gdx.files.internal("inventory/tab_journal.png"))
        };

        inventoryWindow = new Window("", skin);
        inventoryWindow.setSize(1000, 700);
        inventoryWindow.setPosition(
            (Gdx.graphics.getWidth() - inventoryWindow.getWidth()) / 2f,
            (Gdx.graphics.getHeight() - inventoryWindow.getHeight()) / 2f
        );
        inventoryWindow.setVisible(false);
        inventoryWindow.setMovable(false);

        closeButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(closeTexture)));
        closeButton.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                closeButton.setColor(Color.RED);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                closeButton.setColor(Color.WHITE);
            }

            @Override
            public void clicked(InputEvent event, float x, float y) {
                hideWithAnimation();
            }
        });

        Table topBar = new Table();
        tabButtons = new Array<>();

        for (int i = 0; i < tabTextures.length; i++) {
            final int index = i;
            ImageButton tabButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(tabTextures[i])));
            tabButton.getImage().setColor(1, 1, 1, 0.5f);

            tabButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    selectTab(index);
                    hoveredTabIndex = index;
                    tooltipStartTime = System.currentTimeMillis();
                }

                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    hoveredTabIndex = index;
                    tooltipStartTime = System.currentTimeMillis();
                    tooltipLabel.setVisible(false);
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    hoveredTabIndex = -1;
                    tooltipStartTime = 0;
                    tooltipLabel.setVisible(false);
                }
            });

            tabButtons.add(tabButton);
            topBar.add(tabButton).size(48).pad(5);
        }

        topBar.add(closeButton).expandX().right().padRight(8);
        inventoryWindow.add(topBar).expandX().fillX().pad(10);
        inventoryWindow.row();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.LIGHT_GRAY);
        pixmap.fill();
        Texture lineTexture = new Texture(pixmap);
        pixmap.dispose();

        Image separator = new Image(new TextureRegionDrawable(new TextureRegion(lineTexture)));
        separator.setHeight(2);
        inventoryWindow.add(separator).expandX().fillX().padBottom(5);
        inventoryWindow.row();

        contentTable = new Table();
        inventoryWindow.add(contentTable).expand().fill().pad(15);

        tooltipLabel = new Label("", skin);
        tooltipLabel.setColor(Color.YELLOW);
        tooltipLabel.setFontScale(0.6f);
        tooltipLabel.setVisible(false);

        skillTooltipLabel = new Label("", skin);
        skillTooltipLabel.setColor(Color.WHITE);
        skillTooltipLabel.setFontScale(0.6f);
        skillTooltipLabel.setVisible(false);

        selectTab(1);
    }

    private void selectTab(int index) {
        for (int i = 0; i < tabButtons.size; i++) {
            tabButtons.get(i).getImage().setColor(1, 1, 1, 0.5f);
        }
        tabButtons.get(index).getImage().setColor(1, 1, 1, 1f);

        contentTable.clear();

        switch (index) {
            case 0: showInventoryTab(); break;
            case 1: showSkillTab(); break;
            case 2: showCraftTab(); break;
            case 3: showCookTab(); break;
            case 4: showSettingsTab(); break;
            case 5: showMapTab(); break;
            case 6: showChatTab(); break;
            case 7: showJournalTab(); break;
        }
    }

    private void showSkillTab() {
        contentTable.clear();
        contentTable.top().left();

        Table mainTable = new Table();
        Table skillTable = new Table();

        TextureRegion playerFrontFrame = GameView.getPlayerFrontImage();
        Image playerImage = new Image(new TextureRegionDrawable(playerFrontFrame));
        Label nameLabel = new Label("     Name: " + Game.getCurrentPlayer().getOwner().getUsername(), skin);
        nameLabel.setColor(Color.GOLD);
        nameLabel.setFontScale(0.75f);

        skillTable.add(playerImage).size(128, 200).padRight(17).top().left();
        skillTable.row();
        skillTable.add(nameLabel).left().padTop(4);

        Table skillsIconsTable = new Table();

        String[] skillPaths = {
            "inventory/skills/Farming_Skill_Icon.png",
            "inventory/skills/Fishing_Skill_Icon.png",
            "inventory/skills/Foraging_Skill_Icon.png",
            "inventory/skills/Mining_Skill_Icon.png",
            "inventory/skills/Combat_Skill_Icon.png"
        };

        String[] skillNames = {"Farming", "Fishing", "Foraging", "Mining", "Combat"};

        Texture diamondTexture = new Texture(Gdx.files.internal("inventory/skills/Ruby.png"));

        for (int i = 0; i < skillPaths.length; i++) {
            Texture skillTexture = new Texture(Gdx.files.internal(skillPaths[i]));
            Image icon = new Image(new TextureRegionDrawable(new TextureRegion(skillTexture)));
            icon.setSize(24, 24);

            Label name = new Label(skillNames[i], skin);
            name.setFontScale(0.5f);
            name.setColor(Color.WHITE);

            Table leftCol = new Table();
            leftCol.add(icon).center().row();
            leftCol.add(name).padTop(3).center();

            int value = getSkillValue(i);
            int diamonds = value / 10;

            Label valueLabel = new Label("XP: " + value, skin);
            valueLabel.setFontScale(1.0f);
            valueLabel.setColor(Color.GOLD);

            Table diamondTable = new Table();
            for (int j = 0; j < diamonds; j++) {
                Image diamond = new Image(new TextureRegionDrawable(new TextureRegion(diamondTexture)));
                diamond.setSize(14, 14);
                diamondTable.add(diamond).padRight(2);
            }

            Table valueAndDiamonds = new Table();
            valueAndDiamonds.left();
            valueAndDiamonds.add(valueLabel).padRight(8);
            valueAndDiamonds.add(diamondTable);

            Table skillRow = new Table();
            skillRow.left();
            skillRow.add(leftCol).width(80).padRight(25);
            skillRow.add(valueAndDiamonds).left().expandX();

            skillsIconsTable.add(skillRow).padBottom(15).left().row();

            // Tooltip hover
            final Image finalIcon = icon;
            icon.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    hoveredSkillIcon = finalIcon;
                    skillTooltipStartTime = System.currentTimeMillis();
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    hoveredSkillIcon = null;
                    skillTooltipStartTime = 0;
                    skillTooltipLabel.setVisible(false);
                }
            });
        }
        mainTable.add(skillTable).top().left().padLeft(80).padRight(50);
        mainTable.add(skillsIconsTable).top().left();
        contentTable.add(mainTable).top().left();
    }




    private int getSkillValue(int idx) {
        return switch (idx) {
            case 0 -> Objects.requireNonNull(Game.getCurrentPlayer()).getFarmingSkill().getLevel();
            case 1 -> Objects.requireNonNull(Game.getCurrentPlayer()).getFishingSkill().getLevel();
            case 2 -> Objects.requireNonNull(Game.getCurrentPlayer()).getForagingSkill().getLevel();
            case 3 -> Objects.requireNonNull(Game.getCurrentPlayer()).getMiningSkill().getLevel();
            case 4 -> 0;
            default -> 0;
        };
    }


    private void showInventoryTab() {
        contentTable.clear();
//
        loadInventoryTextures();

        Table inventoryTable = new Table();
        inventoryTable.left().top();

        Map<Item, Pair<Integer, Integer>> items = Game.getCurrentPlayer().getInventory().getItems();

        int maxSlots = 60;

        TextureRegion slotTextureRegion = new TextureRegion(slotTexture);
        Texture highlightTexture = new Texture(Gdx.files.internal("game/tiles/highlight.png"));

        for (int i = 0; i < maxSlots; i++) {
            Stack slotStack = new Stack();

            Image slotBg = new Image(slotTextureRegion);
            slotStack.add(slotBg);

            slotBg.addListener(new ClickListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    if (pointer == -1) {
                        slotBg.setDrawable(new TextureRegionDrawable(new TextureRegion(highlightTexture)));
                    }
                }

                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    if (pointer == -1) {
                        slotBg.setDrawable(new TextureRegionDrawable(slotTextureRegion));
                    }
                }
            });


            for (Map.Entry<Item, Pair<Integer, Integer>> entry : items.entrySet()) {
                Item item = entry.getKey();
                int slotIndex = entry.getValue().second;
                int quantity = entry.getValue().first;

                if (slotIndex == i) {
                    TextureRegion itemTex = itemTextures.get(item);
                    if (itemTex != null) {
                        Image itemImage = new Image(itemTex);
                        itemImage.setSize(32, 32);
                        slotStack.add(itemImage);

                        Label qtyLabel = new Label(String.valueOf(quantity), skin);
                        qtyLabel.setFontScale(0.8f);
                        qtyLabel.setColor(Color.WHITE);
                        Container<Label> qtyContainer = new Container<>(qtyLabel);
                        qtyContainer.align(Align.bottomRight);
                        qtyContainer.fill();
                        qtyContainer.pad(2);
                        slotStack.add(qtyContainer);
                    }
                    break;
                }
            }

            inventoryTable.add(slotStack).size(48, 48).pad(5);
            if ((i + 1) % 6 == 0)
                inventoryTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(inventoryTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setForceScroll(false, true);
        scrollPane.setScrollbarsOnTop(true);
        scrollPane.layout();

        contentTable.add(scrollPane)
            .size(400, 300)
            .expand()
            .center();
    }




    private void showCraftTab() {

    }

    private void showCookTab() {
        Table profileTable = new Table();
        Texture profileTexture = new Texture(Gdx.files.internal("background.jpg"));
        Image profileImage = new Image(new TextureRegionDrawable(new TextureRegion(profileTexture)));
        Label nameLabel = new Label("Name: Mammad", skin);

        profileTable.add(profileImage).size(64, 64).padRight(10);
        profileTable.add(nameLabel).left().center();

        contentTable.add(profileTable);
    }



    //settings
    private void showSettingsTab() {
        contentTable.clear();
        contentTable.top().left();

        Table settingsTable = new Table();

        // ===== MUSIC SELECTION =====
        Label musicSelectLabel = new Label("Select Music:", skin);
        String[] musicNames = {"Track 1", "Track 2", "Track 3"};
        SelectBox<String> musicSelectBox = new SelectBox<>(skin);
        musicSelectBox.setItems(musicNames);

        if (musicTracks == null) {
            musicTracks = new Music[] {
                Gdx.audio.newMusic(Gdx.files.internal("inventory/music/Music1.mp3")),
                Gdx.audio.newMusic(Gdx.files.internal("inventory/music/Music2.mp3")),
                Gdx.audio.newMusic(Gdx.files.internal("inventory/music/Music3.mp3"))
            };
            currentMusic = musicTracks[0];
            currentMusic.setLooping(true);
            currentMusic.play();
            currentMusic.setVolume(0f);

        }

        musicSelectBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                int selectedIndex = musicSelectBox.getSelectedIndex();
                if (currentMusic != null) {
                    currentMusic.stop();
                }
                currentMusic = musicTracks[selectedIndex];
                currentMusic.setLooping(true);
                currentMusic.play();
            }
        });

        settingsTable.add(musicSelectLabel).left().padRight(10);
        settingsTable.add(musicSelectBox).width(150).left();
        settingsTable.row().padTop(20);

        // ===== VOLUME SLIDER =====
        Label volumeLabel = new Label("Music Volume:", skin);
        Slider volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(0f);

        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (currentMusic != null) {
                    currentMusic.setVolume(volumeSlider.getValue());
                }
            }
        });

        settingsTable.add(volumeLabel).left().padRight(10);
        settingsTable.add(volumeSlider).width(200).left();
        settingsTable.row().padTop(20);

        // ===== FULLSCREEN INVENTORY TOGGLE =====
        CheckBox fullscreenCheckBox = new CheckBox(" Fullscreen Inventory", skin);
        fullscreenCheckBox.setChecked(false);

        fullscreenCheckBox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (fullscreenCheckBox.isChecked()) {
                    inventoryWindow.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                    inventoryWindow.setPosition(0, 0);
                } else {

                    inventoryWindow.setSize(1000, 700);
                    inventoryWindow.setPosition(
                        (Gdx.graphics.getWidth() - 1000) / 2f,
                        (Gdx.graphics.getHeight() - 700) / 2f
                    );
                }
            }
        });

        // ===== BACK TO MAIN MENU BUTTON =====
        TextButton backButton = new TextButton("Exit to Main Menu", skin);

        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Main.getMain().setScreen(new MainMenu(new MainMenuController(),GameAssetManager.getGameAssetManager().getSkin()));
            }
        });

        settingsTable.add(backButton).colspan(2).left();
        settingsTable.row().padTop(20);


        settingsTable.add(fullscreenCheckBox).colspan(2).left();
        settingsTable.row().padTop(20);

        // ===== ADD TO MAIN TABLE =====
        contentTable.add(settingsTable).top().left().pad(20);
    }

    private void showChatTab() {
        contentTable.clear();
        contentTable.top().left();

        Table chatTable = new Table();

        Label friendsLabel = new Label("Your Friends:", skin);
        chatTable.add(friendsLabel).left();
        chatTable.row().padTop(20);

        ArrayList<Player> friends =Game.getFriendsOf(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()));

        if (friends.isEmpty()) {
            Label noFriendsLabel = new Label("No Friends", skin);
            chatTable.add(noFriendsLabel).left();
        } else {
            for (Player friend : friends) {
                Label friendLabel = new Label("- " + friend.getOwner().getUsername(), skin);
                chatTable.add(friendLabel).left();
                chatTable.row().padTop(10);
            }
        }

        contentTable.add(chatTable).top().left().pad(20);


        Label npcLabel = new Label("(NPC):", skin);
        chatTable.add(npcLabel).left();
        chatTable.row().padTop(10);

        String[] npcNames = {"SEBASTIAN", "ABIGAIL", "HARVEY", "LEAH", "ROBIN"};
        for (String npcName : npcNames) {
            Label npcNameLabel = new Label("- " + npcName, skin);
            npcNameLabel.setColor(Color.RED);
            chatTable.add(npcNameLabel).left();
            chatTable.row().padTop(5);
        }

        contentTable.add(chatTable).top().left().pad(20);
    }


    private void showMapTab() {
        contentTable.clear();
        contentTable.top().left();

        Texture mapTexture = new Texture(Gdx.files.internal("map/map_placeholder.jpg"));
        Image mapImage = new Image(new TextureRegionDrawable(new TextureRegion(mapTexture)));

        mapImage.setScaling(Scaling.fit);

        contentTable.add(mapImage).expand().fill();
    }

    private void showJournalTab() {
        contentTable.clear();
        contentTable.top().left();

        Table journalTable = new Table();

        Label journalLabel = new Label("Mission Journal", skin);
        journalTable.add(journalLabel).left();
        journalTable.row().padTop(20);

        // List of possible quests (in English)
        String[] allQuests = {
            "Collect 10 wildflowers",
            "Plant 5 medicinal herbs",
            "Water your crops for 3 days",
            "Build a small greenhouse",
            "Give gifts to 3 villagers",
            "Catch 5 fish",
            "Produce 3 compost using waste",
            "Tame a chicken",
            "Build a fence around your farm",
            "Find rare mushrooms in the forest"
        };

        // Random generator based on current player name
        int seed = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getOwner().getUsername().hashCode();
        Random random = new Random(seed);

        HashSet<Integer> selectedIndexes = new HashSet<>();
        int numberOfQuests = 3; // Number of quests to show

        for (int i = 0; i < numberOfQuests; i++) {
            int index;
            do {
                index = random.nextInt(allQuests.length);
            } while (selectedIndexes.contains(index));
            selectedIndexes.add(index);

            String questText = "- " + allQuests[index];
            Label questLabel = new Label(questText, skin);
            journalTable.add(questLabel).left();
            journalTable.row().padTop(10);
        }

        contentTable.add(journalTable).top().left().pad(20);
    }





    //




    private void loadInventoryTextures() {
        itemTextures.clear();
        for (Item item : Game.getCurrentPlayer().getInventory().getItems().keySet()) {
            if (item instanceof CraftingItem craftingItem) {
                itemTextures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager()
                    .getCraftingAtlas().findRegion(craftingItem.getCraftingItem().name())));
            } else if (item instanceof PictureModel pictureModel) {
                itemTextures.put(item, new TextureRegion(new Texture(Gdx.files.internal(pictureModel.getPath()))));
            } else {
                itemTextures.put(item, new TextureRegion(GameAssetManager.getGameAssetManager().getPickaxe()));
            }
        }
    }

    public void update() {
        if (!inventoryWindow.isVisible()) return;

        float mouseX = Gdx.input.getX();
        float mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();

        boolean hoveringTab = false;

        for (int i = 0; i < tabButtons.size; i++) {
            ImageButton tab = tabButtons.get(i);
            Vector2 pos = tab.localToStageCoordinates(new Vector2(0, 0));
            float x = pos.x, y = pos.y, w = tab.getWidth(), h = tab.getHeight();

            if (mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h) {
                hoveringTab = true;
                if (hoveredTabIndex != i)
                {
                    hoveredTabIndex = i;
                    tooltipStartTime = System.currentTimeMillis();
                } else if (System.currentTimeMillis() - tooltipStartTime > 1200)
                {
                    tooltipLabel.setText(getTooltipForTab(i));
                    tooltipLabel.setPosition(x, y + h + 10);
                    tooltipLabel.setVisible(true);
                }
                break;
            }
        }

        if (!hoveringTab) {
            tooltipLabel.setVisible(false);
        }

        if (hoveredSkillIcon != null && System.currentTimeMillis() - skillTooltipStartTime > 1000) {
            Vector2 pos = hoveredSkillIcon.localToStageCoordinates(new Vector2(0, 0));
            float x = pos.x + hoveredSkillIcon.getWidth() + 10;
            float y = pos.y + hoveredSkillIcon.getHeight() / 2-40;

            skillTooltipLabel.setText(getTooltipForSkillIcon(hoveredSkillIcon));
            skillTooltipLabel.setFontScale(1.2f);
            skillTooltipLabel.setColor(Color.BLACK);
            skillTooltipLabel.setPosition(x, y);
            skillTooltipLabel.setVisible(true);
        } else {
            skillTooltipLabel.setVisible(false);
        }
    }

    private String getTooltipForTab(int index) {
        return switch (index) {
            case 0 -> "inventory";
            case 1 -> "skill";
            case 2 -> "craft";
            case 3 -> "cook";
            case 4 -> "settings";
            case 5 -> "map";
            case 6 -> "chat";
            default -> "";
        };
    }

    private String getTooltipForSkillIcon(Image icon) {
        Drawable drawable = icon.getDrawable();
        if (drawable instanceof TextureRegionDrawable tex) {
            String path = tex.getRegion().getTexture().toString();
            if (path.contains("Farming")) return "Farming: Grow crops.";
            if (path.contains("Fishing")) return "Fishing: Catch fish.";
            if (path.contains("Foraging")) return "Foraging: Gather items.";
            if (path.contains("Mining")) return "Mining: Mine ores.";
            if (path.contains("Combat")) return "Combat: Fight enemies.";
        }
        return "Skill info";
    }

    public void toggle() {
        if (inventoryWindow.isVisible()) {
            hideWithAnimation();
        } else {
            showWithAnimation();
        }
    }

    public void showWithAnimation() {
        inventoryWindow.clearActions();
        inventoryWindow.getColor().a = 0f;
        inventoryWindow.setVisible(true);
        selectTab(1);
        inventoryWindow.addAction(sequence(
            fadeIn(0.6f),
            run(() -> {
                tooltipLabel.setVisible(false);
                hoveredTabIndex = -1;
                tooltipStartTime = 0;
            })
        ));
    }

    public void hideWithAnimation() {
        inventoryWindow.clearActions();
        inventoryWindow.addAction(sequence(
            fadeOut(0.5f),
            run(() -> {
                inventoryWindow.setVisible(false);
                hoveredTabIndex = -1;
                tooltipLabel.setVisible(false);
                skillTooltipLabel.setVisible(false);
            })
        ));
    }

    public void dispose() {
        slotTexture.dispose();
        closeTexture.dispose();
        for (Texture t : tabTextures) t.dispose();
    }

    public Window getWindow() {
        return inventoryWindow;
    }

    public Label getTooltipLabel() {
        return tooltipLabel;
    }

    public Label getSkillTooltipLabel() {
        return skillTooltipLabel;
    }
}
