package io.github.group18;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.group18.Controller.RegisterLoginGdxController;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Network.Client.SetupClient;
import io.github.group18.View.RegisterLoginGdxView;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static int ScreenWidth = 1920, ScreenHeight= 1080;
    private static Main main;
    private static SpriteBatch batch;
    private static SetupClient gameClient;

    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        String[] argumentsForA = {"arg1", "arg2"};

        SetupClient.startClientConnectionThread();

//        client shouldnt use app
//        App.loadUsersFromFile();

//        there is no longer a logged in user in phase 3
//        File loggedInUserFile = new File("loggedInUser.json");
//        if (loggedInUserFile.exists() && loggedInUserFile.length() > 0) {
//            try (FileReader reader = new FileReader(loggedInUserFile)) {
//                User user = new Gson().fromJson(reader, User.class);
//                if (user != null) {
//                    App.setCurrentUser(user);
//                    Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
//                    System.out.println("Welcome back, " + user.getUsername());
//                    return;
//                }
//            } catch (IOException e) {
//                System.err.println("Failed to read logged-in user: " + e.getMessage());
//            }
//        }

        getMain().setScreen(new RegisterLoginGdxView(new RegisterLoginGdxController(), GameAssetManager.getGameAssetManager().getSkin()));
    }

    @Override
    public void render() {
       super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static void setMain(Main main) {
        Main.main = main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setBatch(SpriteBatch batch) {
        Main.batch = batch;
    }

    public static int getScreenWidth() {
        return ScreenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        ScreenWidth = screenWidth;
    }

    public static int getScreenHeight() {
        return ScreenHeight;
    }

    public static void setScreenHeight(int screenHeight) {
        ScreenHeight = screenHeight;
    }

//    public static GameClient getGameClient() {
//        return gameClient;
//    }
//
//    public static void setGameClient(GameClient gameClient) {
//        Main.gameClient = gameClient;
//    }
}
