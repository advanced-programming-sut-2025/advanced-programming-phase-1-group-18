package io.github.group18.Network.Client.Controller;

import com.badlogic.gdx.Gdx;
import io.github.group18.Controller.ChoosingMapController;
import io.github.group18.Controller.LobbyController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Network.Server.App.ClientConnectionThread;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.ChoosingMapView;

public class ChangeMenuController {
    public static Message handleMessage(Message message) {
        switch (message.getType()) {
            case change_menu:
                changeMenu();
                return message;
            default:
                return message;
        }
    }
    public static void changeMenu() {
        System.out.println(App.getCurrentUser());
        Gdx.app.postRunnable(() -> {
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ChoosingMapView(new ChoosingMapController(), GameAssetManager.getGameAssetManager().getSkin()));
        });
    }
}
