package io.github.group18;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.ScreenUtils;
import com.google.gson.Gson;
import io.github.group18.Controller.MainMenuController;
import io.github.group18.Controller.RegisterLoginGdxController;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.User;
import io.github.group18.View.MainMenu;
import io.github.group18.View.RegisterLoginGdxView;
import io.github.group18.enums.Menu;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public static int ScreenWidth = 1920, ScreenHeight= 1080;
    private static Main main;
    private static SpriteBatch batch;
    @Override
    public void create() {
        main = this;
        batch = new SpriteBatch();
        App.loadUsersFromFile();
        File loggedInUserFile = new File("loggedInUser.json");
        if (loggedInUserFile.exists() && loggedInUserFile.length() > 0) {
            try (FileReader reader = new FileReader(loggedInUserFile)) {
                User user = new Gson().fromJson(reader, User.class);
                if (user != null) {
                    App.setCurrentUser(user);
                    Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    System.out.println("Welcome back, " + user.getUsername());
                    return;
                }
            } catch (IOException e) {
                System.err.println("Failed to read logged-in user: " + e.getMessage());
            }
        }
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
}
