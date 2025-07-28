package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.RandomPassGDXController;
import io.github.group18.Main;

public class BeforeLobbyMenu implements Screen {
    private Stage stage;
    private final Label titleLabel;
    private final TextButton createLobbyButton;
    private final TextField lobbySetNameField;
    private final TextField lobbySetPasswordField;
    private final SelectBox<String> lobbySetPublicBox;
    private final SelectBox<String> lobbySetVisibleBox;

    private final TextButton joinLobbyButton;
    private final TextButton exitButton;
    private final TextButton refreshButton;
    private final TextField lobbyPasswordField;
    private final TextField lobbyIdField;
    public Table table;

    private final RandomPassGDXController controller;

    public BeforeLobbyMenu(RandomPassGDXController controller, Skin skin) {
        this.controller = controller;
        this.titleLabel = new Label("Stardew Valley", skin,"title");
        this.createLobbyButton = new TextButton("Creat New Lobby", skin);
        this.lobbySetNameField = new TextField("", skin);
        lobbySetNameField.setMessageText("Enter lobby ID to create");
        this.lobbySetPasswordField = new TextField("", skin);
        lobbySetPasswordField.setMessageText("Enter lobby password to create");
        this.lobbySetPublicBox = new SelectBox<>(skin);
        lobbySetPublicBox.setItems("Public", "Private");
        this.lobbySetVisibleBox = new SelectBox<>(skin);
        lobbySetVisibleBox.setItems("Visible", "Invisible");

        this.joinLobbyButton = new TextButton("Join Lobby", skin);
        this.lobbyIdField = new TextField("", skin);
        lobbyIdField.setMessageText("Enter lobby ID");
        this.lobbyPasswordField = new TextField("", skin);
        lobbyPasswordField.setMessageText("Enter lobby password");
        this.refreshButton = new TextButton("Refresh", skin);
        this.exitButton = new TextButton("exit", skin);
        this.table = new Table();
//        controller.setView(this);
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture bgTexture = new Texture(Gdx.files.internal("RegisterImage.jpg"));
        Image background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background);

        table.setFillParent(true);
//        table.add(randomPassLabel);
        table.row().pad(10, 0, 10, 0);
        table.add(joinLobbyButton);
        table.row().pad(10, 0, 10, 0);
        table.add(exitButton);
        table.row().pad(10, 0, 10, 0);
        table.add(createLobbyButton);


        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setFillParent(true);
        stage.addActor(scrollPane);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleRandomPassGDXButtons();
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

    }

    @Override
    public void dispose() {

    }

    public TextButton getGoBackButton() {
        return createLobbyButton;
    }

    public TextButton getShowButton() {
        return joinLobbyButton;
    }

    public TextButton getCopyButton() {
        return exitButton;
    }

}
