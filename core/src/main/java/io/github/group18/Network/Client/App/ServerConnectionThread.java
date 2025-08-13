package io.github.group18.Network.Client.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.google.gson.Gson;
import io.github.group18.Controller.GameController;
import io.github.group18.Controller.RadioMenuController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Player;
import io.github.group18.Model.User;
import io.github.group18.Network.Client.Controller.C2SConnectionController;
import io.github.group18.Network.Client.Controller.ChangeMenuController;
import io.github.group18.Network.common.models.ConnectionThread;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.GameMenuInputAdapter;
import io.github.group18.View.GameView;
import io.github.group18.View.TradeHistoryWindow;
import io.github.group18.View.TradeUI;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import static io.github.group18.Network.Client.App.ClientModel.TIMEOUT_MILLIS;

public class ServerConnectionThread extends ConnectionThread {
    GameView gameView;

    public ServerConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public boolean initialHandshake() {
        try {
            socket.setSoTimeout(TIMEOUT_MILLIS);

            dataInputStream.readUTF();
            Message message1 = C2SConnectionController.status();
            sendMessage(message1);

            socket.setSoTimeout(0);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.command) {
            sendMessage(C2SConnectionController.handleCommand(message));
            return true;
        } else if (message.getMenu() == Message.Menu.CHANGE_MENU) {
            sendMessage(ChangeMenuController.handleMessage(message));
            return true;
        }
        else if (message.getType() == Message.Type.RadioUploadChunk) {

            RadioMenuController.receiveAndStoreMusicChunk(message);
            return true;
        } else if (message.getType() == Message.Type.RadioBroadcast) {

            RadioMenuController.receiveAndStoreMusicChunk(message);

            return true;
        }
        else if (message.getMenu() == Message.Menu.game_menu && message.getType() == Message.Type.load_game_screen) {
//            System.out.println("server is telling us to go to game screen " + message.getBody().toString());
            Gdx.app.postRunnable(new Runnable() {
                @Override
                public void run() {
                    GameController gameController = new GameController(Main.getMain());
                    gameController.init();
                    gameController.run();
                }
            });
        } else if (message.getMenu() == Message.Menu.game_menu && message.getType() == Message.Type.add_player_to_Clientmain) {
//            System.out.println("server game us a player to add to clientMain " + message.getBody().toString());
            Gson gson1 = new Gson();
            Object userObj1 = message.getFromBody("owner");
            String userjson1 = gson1.toJson(userObj1);
            User user = gson1.fromJson(userjson1, User.class);
            double energy = message.getFromBody("energy");
            double x = message.getFromBody("x");
            double y = message.getFromBody("y");
            Player player = new Player();
            player.setOwner(user);
            player.setEnergy(energy);
            player.setX(x);
            player.setY(y);
            ClientModel.setPlayer(player);
        } else if (message.getMenu() == Message.Menu.game_menu && message.getType() == Message.Type.player_pos_update) {
            gameView.setPlayerPosUpdated(true);

        } else if (message.getMenu() == Message.Menu.game && message.getType() == Message.Type.remove_user_from_game) {
            this.end();
            System.exit(0);
        }
        if (message.getType() == Message.Type.trade_offer_received && message.getMenu() == Message.Menu.trade) {
            handleTradeOffer(message);
            return true;
        }
        else if (message.getMenu() == Message.Menu.trade && message.getType() == Message.Type.trade_history_update) {
            Object historyObj = message.getFromBody("history");

            Gson gson = new Gson();
            String json = gson.toJson(historyObj);
            java.lang.reflect.Type listType = new com.google.gson.reflect.TypeToken<java.util.List<java.util.Map<String, Object>>>() {}.getType();
            java.util.List<java.util.Map<String, Object>> tradeHistory = gson.fromJson(json, listType);


            ClientModel.setTradeHistory(tradeHistory);
            Gdx.app.postRunnable(() -> {
                TradeHistoryWindow window = ClientModel.getTradeHistoryWindow();
                if (window != null && window.isVisible()) {
                    window.updateHistory(tradeHistory);
                } else {
                    ClientModel.setWindowOpen(true);
                    Stage stage = GameMenuInputAdapter.getGameController().getGameMenu().getStage();
                    TradeHistoryWindow newWindow = new TradeHistoryWindow(
                        GameAssetManager.getGameAssetManager().getSkin(),
                        stage,
                        tradeHistory
                    );
                    stage.addActor(newWindow);
                    Gdx.input.setInputProcessor(stage);
                }
            });

        }

        return false;
    }

    @Override
    public void run() {
        super.run();
        ClientModel.endAll();
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    private void handleTradeOffer(Message message) {
        System.out.println("handleTradeOffer");
        String fromUser = (String) message.getFromBody("fromUser");
        String tradeType = (String) message.getFromBody("tradeType");
        String mode = (String) message.getFromBody("mode");
        String item = (String) message.getFromBody("item");
        int amount = message.getIntFromBody("amount");
        int price = message.getIntFromBody("price");
        String targetItem = (String) message.getFromBody("targetItem");
        int targetAmount = message.getIntFromBody("targetAmount");

        System.out.println(message.getBody());

        Gdx.app.postRunnable(() -> {
            Stage stage = GameMenuInputAdapter.getGameController().getGameMenu().getStage();
            ClientModel.setWindowOpen(true);
            TradeUI tradeUI = new TradeUI(stage);
            Gdx.input.setInputProcessor(stage);
            tradeUI.showTradeOffer(fromUser, tradeType, mode, item, amount, price, targetItem, targetAmount, (Boolean accepted) -> {
                HashMap<String, Object> response = new HashMap<>();
                response.put("fromUser", fromUser);
                response.put("accepted", accepted);
                response.put("tradeType", tradeType);
                response.put("mode", mode);
                response.put("item", item);
                response.put("amount", amount);
                response.put("price", price);
                response.put("targetItem", targetItem);
                response.put("targetAmount", targetAmount);
                System.out.println("Sending trade response: " + response);
                sendMessage(new Message(response, Message.Type.trade_response, Message.Menu.trade));
            });
        });
}
}
