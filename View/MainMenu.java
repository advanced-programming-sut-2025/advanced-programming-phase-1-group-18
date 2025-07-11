package io.githubgroup18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.githubgroup18.Controller.MainMenuController;
import io.githubgroup18.Main;
import io.githubgroup18.enums.MainMenuCommands;

import java.util.Scanner;

public class MainMenu extends AppMenu implements Screen{
    private final MainMenuController controller = new MainMenuController();

    private Stage stage;
    private final TextButton profileMenuButton;
    private final TextButton gameMenuButton;
    private final TextButton logoutButton;
    public Table table;
    private final MainMenuController mainMenuController;

    public MainMenu(MainMenuController mainMenuController, Skin skin) {
        this.mainMenuController = mainMenuController;
        this.profileMenuButton = new TextButton("Profile Menu", skin);
        this.gameMenuButton = new TextButton("Game Menu", skin);
        this.logoutButton = new TextButton("Logout", skin);
        this.table = new Table();

        mainMenuController.setView(this);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.center();
        table.add(profileMenuButton).width((float) Main.ScreenWidth / 3);
        table.row().pad(20, 20, 20, 20);
        table.add(gameMenuButton).width((float) Main.ScreenWidth / 3);
        table.row().pad(20, 20, 20, 20);
        table.add(logoutButton).width((float) Main.ScreenWidth / 3);

        stage.addActor(table);
    }

    @Override
    public void render(float v) {
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

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        //Matcher goToMenu = MainMenuCommands.GO_TO_MENU.getMather(input);
        if (MainMenuCommands.MenuEnter.getMather(input) != null) {
            String menuName = MainMenuCommands.MenuEnter.getMather(input).group(1);
            controller.menuEnter(menuName);
        } else if (MainMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (MainMenuCommands.Logout.getMather(input) != null) {
            System.out.println(controller.logout());
        } else {
            System.out.println("invalid command");
        }
    }
}
