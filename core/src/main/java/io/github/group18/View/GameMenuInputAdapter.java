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

import java.util.*;

import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.CraftingItem;
import io.github.group18.Model.Items.Item;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.enums.TavilehAnimalEnums;

public class GameMenuInputAdapter extends InputAdapter {
    private final GameController gameController;
    private final Set<Integer> keysHeld = new HashSet<>();
    private String input;
    private boolean buildingPaceMode = false;

    public GameMenuInputAdapter(GameController gameController) {
        this.gameController = gameController;
        ClientModel.getPlayer().setShowInventory(true);
    }

    @Override
    public boolean keyDown(int keycode) {
        keysHeld.add(keycode);
//        System.out.println("pppppppppppppppppp????????????????");
        if (keycode >= Input.Keys.NUM_1 && keycode <= Input.Keys.NUM_9) {
            switchInventorySlot(keycode);
        }

        if (keycode == Input.Keys.P) {
//            System.out.println("pppppppppppppppppp");
            handleCheatCodeDialog();
        }

        if (keycode == Input.Keys.I) {
            handleInventoryVisibility();
        }

        if (keycode == Input.Keys.B) {
            handleCraftingMenu();
        }

        if (keycode == Input.Keys.V) {
            ClientModel.getPlayer().pickSelectedItem();
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
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();
            ArrayList<Player> friends = game.getFriendsOf(currentPlayer); // فرض بر این که این متد وجود داره

            FriendsWindow friendsWindow = new FriendsWindow(
                GameAssetManager.getGameAssetManager().getSkin(),
                currentPlayer,
                friends
            );

            gameController.getGameMenu().getStage().addActor(friendsWindow);
            return true;
        }

        if (keycode == Input.Keys.H) {
            Player currentPlayer = ClientModel.getPlayer();

            TalkHistoryWindow talkHistoryWindow = new TalkHistoryWindow(
                GameAssetManager.getGameAssetManager().getSkin(),
                gameController.getGameMenu().getStage(),
                currentPlayer
            );

            gameController.getGameMenu().getStage().addActor(talkHistoryWindow);
            return true;
        }

        if (keycode == Input.Keys.T) {
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();

            TalkWindow talkWindow = new TalkWindow(
                GameAssetManager.getGameAssetManager().getSkin(),
                gameController.getGameMenu().getStage(),
                currentPlayer
            );

            gameController.getGameMenu().getStage().addActor(talkWindow);
            return true;
        }

        //gift Window
        if (keycode == Input.Keys.G) {
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();
            GiftWindow giftWindow = new GiftWindow(GameAssetManager.getGameAssetManager().getSkin(),
                gameController.getGameMenu().getStage(),
                currentPlayer);
            gameController.getGameMenu().getStage().addActor(giftWindow);
            return true;
        }

        if (keycode == Input.Keys.R) {
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();
            GiftViewWindow giftViewWindow = new GiftViewWindow(
                game,
                GameAssetManager.getGameAssetManager().getSkin(),
                gameController.getGameMenu().getStage()
            );
            return true;
        }

        if (keycode == Input.Keys.J) {
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();
            Skin skin = GameAssetManager.getGameAssetManager().getSkin();
            Stage stage = gameController.getGameMenu().getStage();

            GiftHistoryWindow giftHistoryWindow = new GiftHistoryWindow(game, skin);
            stage.addActor(giftHistoryWindow);

            return true;
        }

        if (keycode == Input.Keys.X) {
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();
            Skin skin = GameAssetManager.getGameAssetManager().getSkin();
            Stage stage = gameController.getGameMenu().getStage();

            FlowerSendWindow flowerSendWindow = new FlowerSendWindow(skin, stage, currentPlayer);
            stage.addActor(flowerSendWindow);

            return true;
        }

        if (keycode == Input.Keys.M) {
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();
            Skin skin = GameAssetManager.getGameAssetManager().getSkin();
            Stage stage = gameController.getGameMenu().getStage();

            MarriageProposalWindow proposalWindow = new MarriageProposalWindow(skin, stage, currentPlayer);
            stage.addActor(proposalWindow);
            return true;
        }

        if (keycode == Input.Keys.K) {
            //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
            Game game = null;
            Player currentPlayer = ClientModel.getPlayer();
            Skin skin = GameAssetManager.getGameAssetManager().getSkin();
            Stage stage = gameController.getGameMenu().getStage();

            MarriageResponseWindow window = new MarriageResponseWindow(skin, stage, currentPlayer);
            stage.addActor(window);

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
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        int current = ClientModel.getPlayer().getInventory().getSelectedSlot();
        int size = ClientModel.getPlayer().getInventory().getMaxQuantity();
        int next = (current + (amountY > 0 ? 1 : -1) + size) % size;
        ClientModel.getPlayer().getInventory().setSelectedSlot(next);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (ClientModel.getPlayer().isPlacingItem()) {
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
                ClientModel.getPlayer().pickSelectedItem();
                gotoMarket(screenX, screenY);
                openNpcDialog(screenX, screenY);
                openNpcPage(screenX, screenY);
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
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        OrthographicCamera camera = gameController.getGameMenu().getGameView().getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) ClientModel.getPlayer().getX(), (float) ClientModel.getPlayer().getY());

        int tileX = (int) (worldCoordinates.x / ClientModel.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / ClientModel.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game currentGame = null;
        NPC sebastian = currentGame.getNPCSEBASTIAN();
        NPC abigail = currentGame.getNPCABIGAIL();
        NPC harvey = currentGame.getNPCHARVEY();
        NPC leah = currentGame.getNPCLEAH();
        NPC robin = currentGame.getNPCROBIN();

        if (tileX == sebastian.getX() && (tileY == sebastian.getY() || tileY == sebastian.getY() + 1)) {
            gameController.getGameMenu().getGameView().setSebastian_view(true);
            openNPCViewPage(sebastian);
        }
        if (tileX == abigail.getX() && (tileY == abigail.getY() || tileY == abigail.getY() + 1)) {
            gameController.getGameMenu().getGameView().setAbigail_view(true);
            openNPCViewPage(abigail);
        }
        if (tileX == harvey.getX() && (tileY == harvey.getY() || tileY == harvey.getY() + 1)) {
            gameController.getGameMenu().getGameView().setHarvey_view(true);
            openNPCViewPage(harvey);
        }
        if (tileX == leah.getX() && (tileY == leah.getY() || tileY == leah.getY() + 1)) {
            gameController.getGameMenu().getGameView().setLeah_view(true);
            openNPCViewPage(leah);
        }
        if (tileX == robin.getX() && (tileY == robin.getY() || tileY == robin.getY() + 1)) {
            gameController.getGameMenu().getGameView().setRobin_view(true);
            openNPCViewPage(robin);
        }
    }

    private void openNPCViewPage(NPC npc) {
        GameView gameView = gameController.getGameMenu().getGameView();
        gameView.setNpcView(new NPCView(Gdx.input.getInputProcessor(), npc));
        Gdx.input.setInputProcessor(gameView.getNpcView().getStage());
    }

    private void openNpcDialog(int screenX, int screenY) {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        OrthographicCamera camera = gameController.getGameMenu().getGameView().getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) ClientModel.getPlayer().getX(), (float) ClientModel.getPlayer().getY());

        int tileX = (int) (worldCoordinates.x / ClientModel.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / ClientModel.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game currentGame = null;
        NPC sebastian = currentGame.getNPCSEBASTIAN();
        NPC abigail = currentGame.getNPCABIGAIL();
        NPC harvey = currentGame.getNPCHARVEY();
        NPC leah = currentGame.getNPCLEAH();
        NPC robin = currentGame.getNPCROBIN();

        if (tileX == sebastian.getX() && tileY == sebastian.getY() + 2) {
            gameController.getGameMenu().getGameView().setSebastian_dialog(true);
            openDialogPage(sebastian);
        }
        if (tileX == abigail.getX() && tileY == abigail.getY() + 2) {
            gameController.getGameMenu().getGameView().setAbigail_dialog(true);
            openDialogPage(abigail);
        }
        if (tileX == harvey.getX() && tileY == harvey.getY() + 2) {
            gameController.getGameMenu().getGameView().setHarvey_dialog(true);
            openDialogPage(harvey);
        }
        if (tileX == leah.getX() && tileY == leah.getY() + 2) {
            gameController.getGameMenu().getGameView().setLeah_dialog(true);
            openDialogPage(leah);
        }
        if (tileX == robin.getX() && tileY == robin.getY() + 2) {
            gameController.getGameMenu().getGameView().setRobin_dialog(true);
            openDialogPage(robin);
        }
    }

    private void openDialogPage(NPC npc) {
        GameView gameView = gameController.getGameMenu().getGameView();
        gameView.setNpcDialogView(new NPCDialogView(Gdx.input.getInputProcessor(), npc));
        Gdx.input.setInputProcessor(gameView.getNpcDialogView().getStage());
    }

    public void update(float delta, Batch batch) {
        Player player = ClientModel.getPlayer();
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

        HashMap<String, Object> map = new HashMap<>();
        map.put("username", ClientModel.getPlayer().getOwner().getUsername());
        map.put("movingdirection", String.valueOf(dir));
        Message send = new Message(map, Message.Type.player_movingdirection_update, Message.Menu.game);
        ClientModel.getServerConnectionThread().sendMessage(send);

        float speed = player.getSpeed();
        player.setVelocity(vx * speed, vy * speed);
        player.update(delta, gameController.getGameMenu().getGameView());
        //handle craftinfo
        if (gameController.getGameMenu().getGameView().isShowPopup()) {
            // Draw semi-transparent background
            ShapeRenderer shapeRenderer = new ShapeRenderer();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(0, 0, 0, 0.5f); // Semi-transparent black
            shapeRenderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1);
            shapeRenderer.rect(gameController.getGameMenu().getGameView().getPopupRect().x, gameController.getGameMenu().getGameView().getPopupRect().y, gameController.getGameMenu().getGameView().getPopupRect().width, gameController.getGameMenu().getGameView().getPopupRect().height);
            shapeRenderer.end();

            // Draw text
            batch.begin();
            BitmapFont font = new BitmapFont();
            font.draw(batch, gameController.getGameMenu().getGameView().getPopupMessage(),
                gameController.getGameMenu().getGameView().getPopupRect().x + 20,
                gameController.getGameMenu().getGameView().getPopupRect().y + gameController.getGameMenu().getGameView().getPopupRect().height - 30);

            // Draw close button
            font.draw(batch, "[X] Close",
                gameController.getGameMenu().getGameView().getPopupRect().x + gameController.getGameMenu().getGameView().getPopupRect().width - 80,
                gameController.getGameMenu().getGameView().getPopupRect().y + 30);
            batch.end();

            // Handle input
            if (Gdx.input.justTouched()) {
                Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
                gameController.getGameMenu().getGameView().getCamera().unproject(touchPos);

                // Check if close was clicked (simple bounds check)
                if (touchPos.x > gameController.getGameMenu().getGameView().getPopupRect().x + gameController.getGameMenu().getGameView().getPopupRect().width - 80 &&
                    touchPos.x < gameController.getGameMenu().getGameView().getPopupRect().x + gameController.getGameMenu().getGameView().getPopupRect().width - 20 &&
                    touchPos.y > gameController.getGameMenu().getGameView().getPopupRect().y &&
                    touchPos.y < gameController.getGameMenu().getGameView().getPopupRect().y + 50) {
                    gameController.getGameMenu().getGameView().setShowPopup(false);
                }
            }
        }
        //handle watering animation
        if (gameController.getGameMenu().getGameView().isWatering()) {

            gameController.getGameMenu().getGameView().setWaterTimer(gameController.getGameMenu().getGameView().getWaterTimer() - Gdx.graphics.getDeltaTime());

            batch.begin();
            batch.setColor(0, 0.5f, 1, 0.3f);
            Texture whitePixel;
            Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
            pixmap.setColor(Color.WHITE);
            pixmap.drawPixel(0, 0);
            whitePixel = new Texture(pixmap);
            pixmap.dispose();
            batch.draw(whitePixel, gameController.getGameMenu().getGameView().getPlantx() * ClientModel.TILE_SIZE, gameController.getGameMenu().getGameView().getPlanty() * ClientModel.TILE_SIZE, ClientModel.TILE_SIZE, ClientModel.TILE_SIZE);
            batch.setColor(Color.WHITE);
            batch.end();

            if (gameController.getGameMenu().getGameView().getWaterTimer() <= 0) {
                gameController.getGameMenu().getGameView().setWatering(false);
            }
        }
    }

    private void gotoMarket(int screenX, int screenY) {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        OrthographicCamera camera = gameController.getGameMenu().getGameView().getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) ClientModel.getPlayer().getX(), (float) ClientModel.getPlayer().getY());

        int tileX = (int) (worldCoordinates.x / ClientModel.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / ClientModel.TILE_SIZE);

        int dx = tileX - Math.round(playerPos.first);
        int dy = tileY - Math.round(playerPos.second);

        if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            return;
        }
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Kashi kashi = game.getMap().get(tileX).get(tileY);
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
        gameController.getGameMenu().getCraftingMenu().setActive(true);
        gameController.getGameMenu().getCraftingMenu().setGameMenuInputAdapter(this);
        Gdx.input.setInputProcessor(gameController.getGameMenu().getCraftingMenu().getStage());
    }

    private void handleInventoryVisibility() {
        ClientModel.getPlayer().setShowInventory(!ClientModel.getPlayer().isShowInventory());
    }

    private void switchInventorySlot(int keycode) {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        int selectedSlot = keycode - Input.Keys.NUM_1;
        ClientModel.getPlayer().getInventory().setSelectedSlot(selectedSlot);
    }

    private void handleBuildingView() {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        Stage stage = gameController.getGameMenu().getCheatCodeStage();
        Skin skin = GameAssetManager.getGameAssetManager().getSkin();

        PlaceEntityDialog dialog = new PlaceEntityDialog(stage, skin, name -> {
            gameController.getGameMenu().getGameView().setPendingPlacementName(name);
            input = gameController.getGameMenu().getGameView().getPendingPlacementName();
            inputBuilding(input);
            Gdx.input.setInputProcessor(gameController.getGameMenu().getInputMultiplexer());
        });
        dialog.show(stage);
        Gdx.input.setInputProcessor(stage);
    }

    private void performAction(int screenX, int screenY) {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        OrthographicCamera camera = gameController.getGameMenu().getGameView().getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) ClientModel.getPlayer().getX(), (float) ClientModel.getPlayer().getY());

        int tileX = (int) (worldCoordinates.x / ClientModel.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / ClientModel.TILE_SIZE);

        //Server-TODO(ask server for game)
        if (game.getMap().get(tileX).get(tileY).getInside() instanceof CraftingItem) {
            CraftingItem craft = (CraftingItem) game.getMap().get(tileX).get(tileY).getInside();
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

        Item item = ClientModel.getPlayer().getInventory().getItemBySlot(ClientModel.getPlayer().getInventory().getSelectedSlot());
        gameController.useItem(item, tileX, tileY, game);
    }

    private void placeCrafting(int screenX, int screenY) {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        OrthographicCamera camera = gameController.getGameMenu().getGameView().getCamera();
        camera.update();
        Vector3 worldCoordinates = camera.unproject(new Vector3(screenX, screenY, 0));
        Pair<Float, Float> playerPos = new Pair<>((float) ClientModel.getPlayer().getX(), (float) ClientModel.getPlayer().getY());

        int tileX = (int) (worldCoordinates.x / ClientModel.TILE_SIZE);
        int tileY = (int) (worldCoordinates.y / ClientModel.TILE_SIZE);

//        int dx = tileX - Math.round(playerPos.first);
//        int dy = tileY - Math.round(playerPos.second);
        if (game.getMap().get(tileX).get(tileY).getInside() == null) {
            Player currentPlayer = ClientModel.getPlayer();
            CraftingItem item = ClientModel.getPlayer().getCraftingInHand();
            game.getMap().get(tileX).get(tileY).setInside(item);
            game.getMap().get(tileX).get(tileY).setWalkable(false);
            currentPlayer.setCraftingInHand(null);
            currentPlayer.setPlacingItem(false);
        }
//        gameController.useItem(item, tileX, tileY, game);
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
                Result result = GameMenuController.sell(ClientModel.getPlayer().getInventory().getItemBySlot(ClientModel.getPlayer().getInventory().getSelectedSlot()), number, ClientModel.getPlayer());
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
        gameController.getGameMenu().getCookingMenu().setActive(true);
        gameController.getGameMenu().getCookingMenu().setGameMenuInputAdapter(this);
        Gdx.input.setInputProcessor(gameController.getGameMenu().getCookingMenu().getStage());
    }

    private void handleCheatCodeDialog() {
        Stage stage = gameController.getGameMenu().getCheatCodeStage();
        CheatCodeDialog cheatWindow = new CheatCodeDialog(
            GameAssetManager.getGameAssetManager().getSkin(),
            this.gameController
        );
        gameController.getGameMenu().getStage().addActor(cheatWindow);
    }

    //for building
    private void inputBuilding(String buildingName) {
        if (buildingName == null) {
            return;
        }
        if (buildingName.equals("barn")) {
            if (ClientModel.getPlayer().getWood() < 350 ||
                ClientModel.getPlayer().getGold() < 6000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("bigBarn")) {
            if (ClientModel.getPlayer().getWood() < 450 ||
                ClientModel.getPlayer().getGold() < 12000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("deluxeBarn")) {
            if (ClientModel.getPlayer().getWood() < 550 ||
                ClientModel.getPlayer().getGold() < 25000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("Coop")) {
            if (ClientModel.getPlayer().getWood() < 300 ||
                ClientModel.getPlayer().getGold() < 4000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("bigCoop")) {
            if (ClientModel.getPlayer().getWood() < 400 ||
                ClientModel.getPlayer().getGold() < 10000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
        if (buildingName.equalsIgnoreCase("deluxeCoop")) {
            if (ClientModel.getPlayer().getWood() < 500 ||
                ClientModel.getPlayer().getGold() < 20000) {
                return;
            } else {
                buildingPaceMode = true;
            }
        }
    }

    private void buildDamdari(int screenX, int screenY) {
        //Server-TODO(ask server for game)
//        Game game = gameController.getGame();
        Game game = null;
        Vector3 worldCoords = gameController.getGameMenu().getGameView().getCamera().unproject(new Vector3(screenX, screenY, 0));

        int tileX = (int) (worldCoords.x / ClientModel.TILE_SIZE);
        int tileY = (int) (worldCoords.y / ClientModel.TILE_SIZE);

        if (input.equalsIgnoreCase("barn")) {
            //Server-TODO
//            CarpentersShopController.build("barn", tileX, tileY);
//                for (int dx = -3; dx <= 3; dx++) {
//                    for (int dy = -2; dy <= 1; dy++) {
//                        int x = tileX + dx;
//                        int y = tileY + dy;
//                        System.out.println("khar"+ x);
//                        System.out.println("nigga"+ y);
//                          Tavileh tavileh=  ClientModel.getPlayer().getMyFarm().getMyTavileh();
//                          game.getMap().get(x).get(y).setInside(tavileh);
//                    }
//                }
        }
        if (input.equalsIgnoreCase("bigbarn")) {
            //Server-TODO
//            CarpentersShopController.build("bigbarn", tileX, tileY);
        }
        if (input.equalsIgnoreCase("deluxebarn")) {
            //Server-TODO
//            CarpentersShopController.build("deluxebarn", tileX, tileY);
        }
        if (input.equalsIgnoreCase("coop")) {
            //Server-TODO
//            CarpentersShopController.build("coop", tileX, tileY);

        }
        if (input.equalsIgnoreCase("bigcoop")) {
            //Server-TODO
//            CarpentersShopController.build("bigcoop", tileX, tileY);
        }
        if (input.equalsIgnoreCase("deluxecoop")) {
            //Server-TODO
//            CarpentersShopController.build("deluxecoop", tileX, tileY);
        }
        if (input.equalsIgnoreCase("cow")) {
            TavilehAnimal animal = new TavilehAnimal();
            if (ClientModel.getPlayer().getMyFarm().getMyTavileh() == null
                && ClientModel.getPlayer().getMyFarm().getMyBigBarn() == null
                && ClientModel.getPlayer().getMyFarm().getMyDeluxeBarn() == null) {

            } else if (ClientModel.getPlayer().getMyFarm().getMyTavileh() != null && (ClientModel.getPlayer().getMyFarm().getMyTavileh().getMaxCapacity() > ClientModel.getPlayer().getMyFarm().getMyTavileh().getTavilehAnimals().size()) && ClientModel.getPlayer().getMyFarm().getMyTavileh().getStatus()) {
                //set animal pack
                System.exit(0);
                animal.setType((TavilehAnimalEnums.Cow));
                animal.setName("mammad");
                animal.setPrice(1500);
                animal.setOutside(false);
                animal.setXofAnimal(-1);
                animal.setYofAnimal(-1);
                animal.setWhereDoILive("barn");
                ArrayList<Animal> animals = ClientModel.getPlayer().getMyBoughtAnimals();
                animals.add(animal);
                ArrayList<TavilehAnimal> tavilehAnimals = ClientModel.getPlayer().getMyFarm().getMyTavileh().getTavilehAnimals();
                tavilehAnimals.add(animal);
                int newGold = ClientModel.getPlayer().getGold() - 1500;
                ClientModel.getPlayer().setGold(newGold);
            }
        }
        buildingPaceMode = false;


    }

}
