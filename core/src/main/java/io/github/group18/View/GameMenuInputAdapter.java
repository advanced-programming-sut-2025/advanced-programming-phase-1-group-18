package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.Model.Items.Item;
import io.github.group18.enums.TavilehAnimalEnums;

public class GameMenuInputAdapter extends InputAdapter {
    private final Game game;
    private final GameController gameController;
    private final Set<Integer> keysHeld = new HashSet<>();
    private String input;
    private boolean buildingPaceMode = false;

    public GameMenuInputAdapter(Game game, GameController gameController) {
        this.game = game;
        this.gameController = gameController;
        game.getCurrentPlayer().setShowInventory(true);
    }

    @Override
    public boolean keyDown(int keycode) {
        keysHeld.add(keycode);

        if (keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9) {
            switchInventorySlot(keycode);
        }


        if (keycode == Input.Keys.N) {
            nextTurn();
        }

        if (keycode == Input.Keys.P) {
            handleCheatCodeDialog();
        }


        if (keycode == Input.Keys.I) {
            handleInventoryVisibility();
        }


        if (keycode == Input.Keys.B) {
            handleCraftingMenu();
        }


        if (keycode == Input.Keys.V) {
            Game.getCurrentPlayer().pickSelectedItem();
        }

        if (keycode == Input.Keys.C) {
            handleCookingMenu();
        }

        if (keycode == Input.Keys.Q) {
            Sell();
        }

        if (keycode == Input.Keys.ESCAPE) {
            handleInevtnoryView();
        }

        if (keycode == Input.Keys.Z) {
            handleBuildingView();
        }

        if (keycode == Input.Keys.F) {
            Game game = App.getCurrentGame();
            Player currentPlayer = game.getCurrentPlayer();
            ArrayList<Player> friends = game.getFriendsOf(currentPlayer); // فرض بر این که این متد وجود داره

            FriendsWindow friendsWindow = new FriendsWindow(
                GameAssetManager.getGameAssetManager().getSkin(),
                currentPlayer,
                friends
            );

            gameController.getGameMenu().getStage().addActor(friendsWindow);
            return true;
        }


        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        keysHeld.remove(keycode);
        return true;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        int current = game.getCurrentPlayer().getInventory().getSelectedSlot();
        int size = game.getCurrentPlayer().getInventory().getMaxQuantity();
        int next = (current + (amountY > 0 ? 1 : -1) + size) % size;
        game.getCurrentPlayer().getInventory().setSelectedSlot(next);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).isPlacingItem()) {
            if (button == Input.Buttons.LEFT) {
                System.out.println("place ");
                placeCrafting(screenX, screenY);
                return true;
            }
        } else {
            if (button == Input.Buttons.LEFT) {
                if (buildingPaceMode) {
                    buildDamdari(screenX, screenY);
                }
                Game.getCurrentPlayer().pickSelectedItem();
                gotoMarket(screenX, screenY);
                openNpcDialog(screenX, screenY);
                openNpcPage(screenX,screenY);
                return true;
            }
            if (button == Input.Buttons.RIGHT) {
                performAction(screenX, screenY);
                return true;
            }
        }

        return false;
    }

    private void openNpcPage(int screenX, int screenY) {
        OrthographicCamera camera = game.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) Game.getCurrentPlayer().getX(), (float) Game.getCurrentPlayer().getY());

        int tileX = (int) (worldCoordinates.x / Game.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Game.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }
        Game currentGame = App.getCurrentGame();
        NPC sebastian = currentGame.getNPCSEBASTIAN();
        NPC abigail = currentGame.getNPCABIGAIL();
        NPC harvey = currentGame.getNPCHARVEY();
        NPC leah = currentGame.getNPCLEAH();
        NPC robin = currentGame.getNPCROBIN();

        if (tileX == sebastian.getX() && (tileY == sebastian.getY() || tileY == sebastian.getY() + 1)) {
            currentGame.setSebastian_view(true);
            openNPCViewPage(sebastian);
        }
        if (tileX == abigail.getX() && (tileY == abigail.getY() || tileY == abigail.getY() + 1)) {
            currentGame.setAbigail_view(true);
            openNPCViewPage(abigail);
        }
        if (tileX == harvey.getX() && (tileY == harvey.getY() || tileY == harvey.getY() + 1)) {
            currentGame.setHarvey_view(true);
            openNPCViewPage(harvey);
        }
        if (tileX == leah.getX() && (tileY == leah.getY() || tileY == leah.getY() + 1)) {
            currentGame.setLeah_view(true);
            openNPCViewPage(leah);
        }
        if (tileX == robin.getX() && (tileY == robin.getY() || tileY == robin.getY() + 1)) {
            currentGame.setRobin_view(true);
            openNPCViewPage(robin);
        }
    }

    private void openNPCViewPage(NPC npc) {
        GameView gameView = gameController.getGameMenu().getGameView();
        gameView.setNpcView(new NPCView(Gdx.input.getInputProcessor(), npc));
        Gdx.input.setInputProcessor(gameView.getNpcView().getStage());
    }

    private void openNpcDialog(int screenX, int screenY) {
        OrthographicCamera camera = game.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) Game.getCurrentPlayer().getX(), (float) Game.getCurrentPlayer().getY());

        int tileX = (int) (worldCoordinates.x / Game.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Game.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }
        Game currentGame = App.getCurrentGame();
        NPC sebastian = currentGame.getNPCSEBASTIAN();
        NPC abigail = currentGame.getNPCABIGAIL();
        NPC harvey = currentGame.getNPCHARVEY();
        NPC leah = currentGame.getNPCLEAH();
        NPC robin = currentGame.getNPCROBIN();

        if (tileX == sebastian.getX() && tileY == sebastian.getY() + 2) {
            currentGame.setSebastian_dialog(true);
            openDialogPage(sebastian);
        }
        if (tileX == abigail.getX() && tileY == abigail.getY() + 2) {
            currentGame.setAbigail_dialog(true);
            openDialogPage(abigail);
        }
        if (tileX == harvey.getX() && tileY == harvey.getY() + 2) {
            currentGame.setHarvey_dialog(true);
            openDialogPage(harvey);
        }
        if (tileX == leah.getX() && tileY == leah.getY() + 2) {
            currentGame.setLeah_dialog(true);
            openDialogPage(leah);
        }
        if (tileX == robin.getX() && tileY == robin.getY() + 2) {
            currentGame.setRobin_dialog(true);
            openDialogPage(robin);
        }
    }

    private void openDialogPage(NPC npc) {
        GameView gameView = gameController.getGameMenu().getGameView();
        gameView.setNpcDialogView(new NPCDialogView(Gdx.input.getInputProcessor(), npc));
        Gdx.input.setInputProcessor(gameView.getNpcDialogView().getStage());
    }

    public void update(float delta, Batch batch) {
        Player player = game.getCurrentPlayer();
        Game game = gameController.getGameMenu().getGameModel();
        float vx = 0, vy = 0;
        int dir = 0;

        if (keysHeld.contains(Input.Keys.W)) {
            vy += 1;
            dir = 3;
        }
        if (keysHeld.contains(Input.Keys.S)) {
            vy -= 1;
            dir = 1;
        }
        if (keysHeld.contains(Input.Keys.A)) {
            vx -= 1;
            dir = 4;
        }
        if (keysHeld.contains(Input.Keys.D)) {
            vx += 1;
            dir = 2;
        }

        float length = (float) Math.sqrt(vx * vx + vy * vy);
        if (length > 0) {
            vx /= length;
            vy /= length;
            player.setMovingDirection(dir);
        } else {
            player.setMovingDirection(0);
        }

        float speed = player.getSpeed();
        player.setVelocity(vx * speed, vy * speed);
        player.update(delta, game.getMap(), gameController.getGameMenu().getGameView());
        //handle craftinfo
        if (game.isShowPopup()) {
            // Draw semi-transparent background
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, 0.5f); // Semi-transparent black
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1);
            shapeRenderer.rect(game.getPopupRect().x, game.getPopupRect().y, game.getPopupRect().width, game.getPopupRect().height);
            shapeRenderer.end();

            // Draw text
            batch.begin();
            BitmapFont font = new BitmapFont();
            font.draw(batch, game.getPopupMessage(),
                game.getPopupRect().x + 20,
                game.getPopupRect().y + game.getPopupRect().height - 30);

            // Draw close button
            font.draw(batch, "[X] Close",
                game.getPopupRect().x + game.getPopupRect().width - 80,
                game.getPopupRect().y + 30);
            batch.end();

            // Handle input
            if (Gdx.input.justTouched()) {
                Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                game.getCamera().unproject(touchPos);

                // Check if close was clicked (simple bounds check)
                if (touchPos.x > game.getPopupRect().x + game.getPopupRect().width - 80 &&
                    touchPos.x < game.getPopupRect().x + game.getPopupRect().width - 20 &&
                    touchPos.y > game.getPopupRect().y &&
                    touchPos.y < game.getPopupRect().y + 50) {
                    game.setShowPopup(false);
                }
            }
        }
        //handle watering animation
        if (game.isWatering()) {

            game.setWaterTimer(game.getWaterTimer() - Gdx.graphics.getDeltaTime());

            batch.begin();
            batch.setColor(0, 0.5f, 1, 0.3f);
            Texture whitePixel;
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.WHITE);
            pixmap.drawPixel(0, 0);
            whitePixel = new Texture(pixmap);
            pixmap.dispose();
            batch.draw(whitePixel, game.getPlantx() * Game.TILE_SIZE, game.getPlanty() * Game.TILE_SIZE, Game.TILE_SIZE, Game.TILE_SIZE);
            batch.setColor(Color.WHITE);
            batch.end();

            if (game.getWaterTimer() <= 0) {
                game.setWatering(false);
            }
        }
    }

    private void gotoMarket(int screenX, int screenY) {
        OrthographicCamera camera = game.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) Game.getCurrentPlayer().getX(), (float) Game.getCurrentPlayer().getY());

        int tileX = (int) (worldCoordinates.x / Game.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Game.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }

        Kashi kashi = App.getCurrentGame().getMap().get(tileX).get(tileY);
        if (kashi.getInside() instanceof adaptMapMarket) {
            if (kashi.getInside() instanceof NPC) {
                //goto NPC
            } else {
                GameView gameView = gameController.getGameMenu().getGameView();
                if (kashi.getInside() instanceof BlackSmithMarket) {
                    gameView.setBlackSmith(new StoreUI(new BlackSmithController(), Gdx.input.getInputProcessor()));
                    Gdx.input.setInputProcessor(gameView.getBlackSmith().getStage());
                }
                if (kashi.getInside() instanceof CarpentersShopMarket) {
                    gameView.setCarpentersShop(new StoreUI(new CarpentersShopController(), Gdx.input.getInputProcessor()));
                    Gdx.input.setInputProcessor(gameView.getCarpentersShop().getStage());
                }
                if (kashi.getInside() instanceof FishShopMarket) {
                    gameView.setFishShop(new StoreUI(new FishShopController(), Gdx.input.getInputProcessor()));
                    Gdx.input.setInputProcessor(gameView.getFishShop().getStage());
                }
                if (kashi.getInside() instanceof JojoMartMarket) {
                    gameView.setJojaMart(new StoreUI(new JojaMartController(), Gdx.input.getInputProcessor()));
                    Gdx.input.setInputProcessor(gameView.getJojaMart().getStage());
                }
                if (kashi.getInside() instanceof MarniesRanchMarket) {
                    gameView.setMarniesRanch(new StoreUI(new MarniesRanchController(), Gdx.input.getInputProcessor()));
                    Gdx.input.setInputProcessor(gameView.getMarniesRanch().getStage());
                }
                if (kashi.getInside() instanceof PierresGeneralStoreMarket) {
                    gameView.setPirresGeneralStore(new StoreUI(new PierresGeneralStoreController(), Gdx.input.getInputProcessor()));
                    Gdx.input.setInputProcessor(gameView.getPirresGeneralStore().getStage());
                }
                if (kashi.getInside() instanceof TheStardropSaloonMarket) {
                    gameView.setTheStarDropSalooon(new StoreUI(new TheStardropSaloonController(), Gdx.input.getInputProcessor()));
                    Gdx.input.setInputProcessor(gameView.getTheStarDropSalooon().getStage());
                }
                //goto store
            }
        }

    }

    private void handleInevtnoryView() {
        gameController.getGameMenu().getInventoryView().toggle();

    }

    private void handleCraftingMenu() {
        App.getGameController().getGameMenu().getCraftingMenu().setActive(true);
        App.getGameController().getGameMenu().getCraftingMenu().setGameMenuInputAdapter(this);
        Gdx.input.setInputProcessor(App.getGameController().getGameMenu().getCraftingMenu().getStage());
    }

    private void handleInventoryVisibility() {
        game.getCurrentPlayer().setShowInventory(!game.getCurrentPlayer().isShowInventory());
    }

    private void switchInventorySlot(int keycode) {
        int selectedSlot = keycode - Input.Keys.NUM_1;
        game.getCurrentPlayer().getInventory().setSelectedSlot(selectedSlot);
    }

    private void handleBuildingView() {
        Stage stage = gameController.getGameMenu().getCheatCodeStage();
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();

        PlaceEntityDialog dialog = new PlaceEntityDialog(stage, skin, name -> {
            game.setPendingPlacementName(name);
            input = game.getPendingPlacementName();
            inputBuilding(input);
            Gdx.input.setInputProcessor(gameController.getGameMenu().getInputMultiplexer());
        });
        dialog.show(stage);
        Gdx.input.setInputProcessor(stage);
    }

    private void nextTurn() {
        GameMenuController.nextTurn(gameController.getGameMenu(), gameController.getGameMenu().getGameView());
    }


    private void performAction(int screenX, int screenY) {
        OrthographicCamera camera = game.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) Game.getCurrentPlayer().getX(), (float) Game.getCurrentPlayer().getY());

        int tileX = (int) (worldCoordinates.x / Game.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Game.TILE_SIZE);

        if (App.getCurrentGame().getMap().get(tileX).get(tileY).getInside() instanceof CraftingItem) {
            CraftingItem craft = (CraftingItem) App.getCurrentGame().getMap().get(tileX).get(tileY).getInside();
            CraftingUI craftingUI = new CraftingUI(craft);
            craftingUI.setCraftingItem(craft);
            craftingUI.setActive(true);
            craftingUI.setGameMenuInputAdapter(this);
            System.out.println("clicked");
            Gdx.input.setInputProcessor(craftingUI.getStage());
            gameController.getGameMenu().setCraftingUI(craftingUI);
//            gameController.getGameMenu().setCraftingUI(craftingUI);
            return;
        }
        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }

        Item item = Game.getCurrentPlayer().getInventory().getItemBySlot(game.getCurrentPlayer().getInventory().getSelectedSlot());
        gameController.useItem(item, tileX, tileY, game);
    }

    private void placeCrafting(int screenX, int screenY) {
        OrthographicCamera camera = game.getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) Game.getCurrentPlayer().getX(), (float) Game.getCurrentPlayer().getY());

        int tileX = (int) (worldCoordinates.x / Game.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / Game.TILE_SIZE);

//        int dx = tileX - Math.round(playerPos.first);
//        int dy = tileY - Math.round(playerPos.second);
        if (App.getCurrentGame().getMap().get(tileX).get(tileY).getInside() == null) {
            Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
            CraftingItem item = Game.getCurrentPlayer().getCraftingInHand();
            App.getCurrentGame().getMap().get(tileX).get(tileY).setInside(item);
            App.getCurrentGame().getMap().get(tileX).get(tileY).setWalkable(false);
            currentPlayer.setCraftingInHand(null);
            currentPlayer.setPlacingItem(false);
        }
//        gameController.useItem(item, tileX, tileY, game);
    }


    public Game getGame() {
        return game;
    }

    public GameController getGameController() {
        return gameController;
    }

    public Set<Integer> getKeysHeld() {
        return keysHeld;
    }


    private void Sell() {
        InputProcessor originalInputProcessor = Gdx.input.getInputProcessor();
        Stage stage = gameController.getGameMenu().getStage();
        Gdx.input.setInputProcessor(stage);
        showNumberInputDialog(stage, "Enter Count: ", new NumberInputListener() {
            @Override
            public void onNumberEntered(int number) {
                Result result = GameMenuController.sell(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getItemBySlot(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().getSelectedSlot()), number);
                System.out.println("Sell: " + result.getMessage());
                Gdx.input.setInputProcessor(originalInputProcessor);
            }

            @Override
            public void onCancel() {
                Gdx.input.setInputProcessor(originalInputProcessor);
            }
        });
    }

    public void showNumberInputDialog(Stage stage, String title, NumberInputListener listener) {
        // Create window
        Window dialog = new Window(title, GameAssetManager.getGameAssetManager().getSkin());
        dialog.setModal(true);
        dialog.setMovable(false);

        // Create text field (numbers only)
        final TextField inputField = new TextField("", GameAssetManager.getGameAssetManager().getSkin());
        inputField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());
        inputField.setMessageText("Enter number...");

        // Create buttons
        TextButton okButton = new TextButton("OK", GameAssetManager.getGameAssetManager().getSkin());
        TextButton cancelButton = new TextButton("Cancel", GameAssetManager.getGameAssetManager().getSkin());

        // Add listeners
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!inputField.getText().isEmpty()) {
                    try {
                        int number = Integer.parseInt(inputField.getText());
                        listener.onNumberEntered(number);
                    } catch (NumberFormatException e) {
                        listener.onNumberEntered(0);  // Default value if parsing fails
                    }
                }
                dialog.remove();
            }
        });

        cancelButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                listener.onCancel();
                dialog.remove();
            }
        });

        // Layout
        Table contentTable = new Table(GameAssetManager.getGameAssetManager().getSkin());
        contentTable.add(inputField).width(400).pad(10).row();

        Table buttonTable = new Table(GameAssetManager.getGameAssetManager().getSkin());
        buttonTable.add(okButton).padRight(10);
        buttonTable.add(cancelButton);

        contentTable.add(buttonTable);
        dialog.add(contentTable);

        // Size and position
        dialog.pack();
        dialog.setSize(600, 300);
        dialog.setPosition(
            Gdx.graphics.getWidth() / 2 - dialog.getWidth() / 2,
            Gdx.graphics.getHeight() / 2 - dialog.getHeight() / 2
        );

        stage.addActor(dialog);
        inputField.setTextFieldListener(new TextField.TextFieldListener() {
            @Override
            public void keyTyped(TextField textField, char c) {
                // Auto-focus the field
                stage.setKeyboardFocus(textField);
            }
        });
        stage.setKeyboardFocus(inputField);
    }

    private void handleCookingMenu() {
        App.getGameController().getGameMenu().getCookingMenu().setActive(true);
        App.getGameController().getGameMenu().getCookingMenu().setGameMenuInputAdapter(this);
        Gdx.input.setInputProcessor(App.getGameController().getGameMenu().getCookingMenu().getStage());
    }

    private void handleCheatCodeDialog() {
        Stage stage = gameController.getGameMenu().getCheatCodeStage();
        Gdx.input.setInputProcessor(stage);
        CheatCodeDialog cheatDialog = new CheatCodeDialog(stage,
            GameAssetManager.getGameAssetManager().getSkin(), this, this.gameController);
        cheatDialog.show(stage);
        cheatDialog.setColor(Color.WHITE);
    }


    //for building
    private void inputBuilding(String buildingName) {
        if (buildingName == null) {
            return;
        }
        if (buildingName.equals("barn")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 350 ||
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 6000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("bigBarn")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 450 ||
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 12000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("deluxeBarn")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 550 ||
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 25000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("Coop")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 300 ||
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 4000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("bigCoop")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 400 ||
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 10000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("deluxeCoop")) {
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getWood() < 500 ||
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 20000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
    }

    private void buildDamdari(int screenX, int screenY) {
        Vector3 worldCoords = game.getCamera().unproject(new Vector3(screenX, screenY, 0));

        int tileX = (int) (worldCoords.x / game.TILE_SIZE);
        int tileY = (int) (worldCoords.y / game.TILE_SIZE);

        if (input.equalsIgnoreCase("barn")) {
            CarpentersShopController.build("barn", tileX, tileY);
//                for (int dx = -3; dx <= 3; dx++) {
//                    for (int dy = -2; dy <= 1; dy++) {
//                        int x = tileX + dx;
//                        int y = tileY + dy;
//                        System.out.println("khar"+ x);
//                        System.out.println("nigga"+ y);
//                          Tavileh tavileh=  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh();
//                          game.getMap().get(x).get(y).setInside(tavileh);
//                    }
//                }
        }
        if (input.equalsIgnoreCase("bigbarn")) {
            CarpentersShopController.build("bigbarn", tileX, tileY);
        }
        if (input.equalsIgnoreCase("deluxebarn")) {
            CarpentersShopController.build("deluxebarn", tileX, tileY);
        }
        if (input.equalsIgnoreCase("coop")) {
            CarpentersShopController.build("coop", tileX, tileY);

        }
        if (input.equalsIgnoreCase("bigcoop")) {
            CarpentersShopController.build("bigcoop", tileX, tileY);
        }
        if (input.equalsIgnoreCase("deluxecoop")) {
            CarpentersShopController.build("deluxecoop", tileX, tileY);
        }
        if (input.equalsIgnoreCase("cow")) {
            TavilehAnimal animal = new TavilehAnimal();
            if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh() == null
                && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn() == null
                && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn() == null) {

            } else if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh() != null && (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getStatus()) {
                //set animal pack
                System.exit(0);
                animal.setType((TavilehAnimalEnums.Cow));
                animal.setName("mammad");
                animal.setPrice(1500);
                animal.setOutside(false);
                animal.setXofAnimal(-1);
                animal.setYofAnimal(-1);
                animal.setWhereDoILive("barn");
                ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
                animals.add(animal);
                ArrayList<TavilehAnimal> tavilehAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals();
                tavilehAnimals.add(animal);
                int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 1500;
                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
            }
        }
        buildingPaceMode = false;


    }

}
