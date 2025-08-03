package io.github.group18.Network.Client.Controller;

import com.badlogic.gdx.Gdx;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.github.group18.Controller.ChoosingMapController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.ChoosingMapView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ChangeMenuController {
    public static Message handleMessage(Message message) {
        switch (message.getType()) {
            case change_menu:
                ArrayList<String> usernames;
                Gson gson = new Gson();
                Object usersArraylistOBJ = message.getBody().get("users");
                String userArraylist = gson.toJson(usersArraylistOBJ);
                Type userListType = new TypeToken<ArrayList<String>>() {}.getType();
                usernames = gson.fromJson(userArraylist, userListType);
                changeMenu(usernames);
                HashMap<String,Object>body = new HashMap<>();
                body.put("message", true);
                return new Message(body, Message.Type.change_menu, Message.Menu.Basic);
            default:
                return message;
        }
    }
    public static void changeMenu(ArrayList<String> usernames) {
        if (App.getCurrentUser().isChangedScreen()) return;
        System.out.println("From Client: change menu"+usernames);
        App.getCurrentUser().setChangedScreen(true);
        Gdx.app.postRunnable(() -> {
            ChoosingMapController controller = new ChoosingMapController();
            controller.setGameMenuController(App.getGameMenuController());
            controller.setUsers(usernames);
            Main.getMain().getScreen().dispose();
            Main.getMain().setScreen(new ChoosingMapView(controller, GameAssetManager.getGameAssetManager().getSkin()));
        });
    }
}
