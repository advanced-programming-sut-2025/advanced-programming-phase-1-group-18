package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.MainMenuController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.enums.MainMenuCommands;

import java.util.Scanner;
public class MainMenu extends AppMenu implements Screen {
    private final MainMenuController controller = new MainMenuController();

    private Stage stage;
    private final TextButton profileMenuButton;
    private final TextButton gameMenuButton;
    private final TextButton onlinePlayers;
    private final TextButton logoutButton;
    private final TextButton exitButton;
    public Table table;
    private final MainMenuController mainMenuController;


    public MainMenu(MainMenuController mainMenuController, Skin skin) {
//        System.out.println("niggger1");
        this.mainMenuController = mainMenuController;
        this.profileMenuButton = new TextButton("Profile Menu", skin);
        this.gameMenuButton = new TextButton("Game Menu", skin);
        this.onlinePlayers = new TextButton("Online Players", skin);
        this.logoutButton = new TextButton("Logout", skin);
        this.exitButton = new TextButton("EXIT", skin);
        this.table = new Table();

        mainMenuController.setView(this);
//        System.out.println("niggger2");
    }

    @Override
    public void show() {
//        System.out.println("niggger3");
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
//        System.out.println("niggger3.05");
        Texture bgTexture = new Texture(Gdx.files.internal("MainMenuBackground.jpg"));
//        System.out.println("niggger3.06");
        Image background = new Image(bgTexture);
//        System.out.println("niggger3.08");
        stage.addActor(background);
//        System.out.println("niggger3.1");
        background.setFillParent(true);
        table.setFillParent(true);
        table.center();
        Label title = new Label("MainMenu", GameAssetManager.getGameAssetManager().getSkin(),"title");
        table.add(title);
        table.row();
        Label nicknigga = new Label("Hello " + App.getCurrentUser().getNickName(), GameAssetManager.getGameAssetManager().getSkin());
        table.add(nicknigga);
        table.row();
//        System.out.println("niggger3.2");
        Texture currentAvatar = GameAssetManager.getUserAvatar(App.getCurrentUser().getAvatar());
        Image avatarImage = new Image(new TextureRegionDrawable(new TextureRegion(currentAvatar)));
        avatarImage.setSize(128, 128);
        table.add(avatarImage).size(128, 128);
        table.row().pad(20, 20, 20, 20);
        table.add(profileMenuButton).width((float) Main.ScreenWidth / 3);
        table.row().pad(20, 20, 20, 20);
        table.add(gameMenuButton).width((float) Main.ScreenWidth / 3);
        table.row().pad(20, 20, 20, 20);
        table.add(onlinePlayers).width((float) Main.ScreenWidth / 3);
        table.row().pad(20, 20, 20, 20);
        table.add(logoutButton).width((float) Main.ScreenWidth / 3);
        table.row().pad(10, 0 , 10 , 0);
        table.add(exitButton);
        table.row().pad(10, 0 , 10 , 0);
//        System.out.println("niggger3.5");
        stage.addActor(table);
//        System.out.println("niggger4");
    }

    @Override
    public void render(float v) {
//        System.out.println("niggger5");
        ScreenUtils.clear(0,0,0,1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        mainMenuController.handleMainMenuButtons();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getProfileMenuButton() {
        return profileMenuButton;
    }

    public TextButton getGameMenuButton() {
        return gameMenuButton;
    }

    public TextButton getOnlinePlayers() {
        return onlinePlayers;
    }

    public TextButton getLogoutButton() {
        return logoutButton;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public MainMenuController getMainMenuController() {
        return mainMenuController;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        //Matcher goToMenu = MainMenuCommands.GO_TO_MENU.getMather(input);
        if (MainMenuCommands.MenuEnter.getMather(input) != null) {
            String menuName = MainMenuCommands.MenuEnter.getMather(input).group(1);
//            controller.menuEnter(menuName);
        } else if (MainMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (MainMenuCommands.Logout.getMather(input) != null) {
            System.out.println(controller.logout());
        } else {
            System.out.println("invalid command");
        }
    }
}
