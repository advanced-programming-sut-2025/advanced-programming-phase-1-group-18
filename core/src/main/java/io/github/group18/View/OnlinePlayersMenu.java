package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.StringBuilder;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.MainMenuController;
import io.github.group18.Controller.OnlinePlayersController;
import io.github.group18.Main;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Player;
import io.github.group18.Model.User;

public class OnlinePlayersMenu implements Screen {
    private final OnlinePlayersController controller;
    private final Skin skin;
    private Stage stage;
    private final TextButton backButton;
    public Table table;

    public OnlinePlayersMenu(OnlinePlayersController onlinePlayersController, Skin skin) {
        controller = onlinePlayersController;
        this.skin = skin;
        this.backButton = new TextButton("Back", skin);
        this.table = new Table();

        controller.setOnlinePlayersMenu(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.center();
        StringBuilder sb = new StringBuilder();
        for(User user : controller.getOnlinePlayers()) {
            sb.append(user.getUsername()).append("\n");
        }
        Label title = new Label(sb, skin);
        table.add(title);
        table.row();
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0,0,0,1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
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

    public OnlinePlayersController getController() {
        return controller;
    }

    public Skin getSkin() {
        return skin;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }
}
