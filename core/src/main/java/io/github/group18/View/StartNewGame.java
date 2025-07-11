package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.StartNewGameController;
import io.github.group18.Main;
import io.github.group18.Model.GameAssetManager;

public class StartNewGame implements Screen {
    private StartNewGameController controller;
    private Stage stage;
    private TextButton addPlayerButton;
    private TextField addPlayerField;
    private TextButton playButton;
    private TextButton backButton;
    private SelectBox<Integer> chooseMapBox;
    private Table table;
    private Skin skin;
    private Label notif;
    private Label menuTitle;

    public StartNewGame(StartNewGameController controller , Skin skin) {
        this.controller = controller;
        this.skin = skin;
        this.stage = new Stage();
        this.table = new Table();
        this.addPlayerButton = new TextButton("Add Player", skin);
        this.playButton = new TextButton("Play Game", skin);
        this.backButton = new TextButton("Back", skin);
        this.notif = new Label("", skin);
        this.addPlayerField = new TextField("",skin);
        addPlayerField.setMessageText("Enter Player Name");
        this.menuTitle = new Label("Add Player", skin,"title");
        this.chooseMapBox = new SelectBox<>(skin);
        chooseMapBox.setItems(1,2,3,4);
        controller.setView(this);
    }


    @Override
    public void show() {
        // Initialize Stage and set input processor
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

// Setup main table
        table.setFillParent(true);
        table.center();

// Add menu title spanning all columns for center alignment
        table.add(menuTitle).colspan(4).center().pad(20);
        table.row();

// First input row: player field (spanning 2 columns) + choose map label + choose map box
        table.add(addPlayerField).width(500).colspan(2).pad(10);
        table.add(new Label("Choose Map", skin)).pad(10).right();
        table.add(chooseMapBox).width(200).pad(10).left();
        table.row();

// Second row: addPlayerButton spanning all columns
        table.add(addPlayerButton).colspan(4).center().pad(10);
        table.row();

// Third row: chooseMapButton and backButton centered with empty cells for spacing
        table.add().expandX(); // empty for spacing
        table.add(playButton).pad(10).center();
        table.add(backButton).pad(10).center();
        table.add().expandX(); // empty for spacing
        table.row();

// Notification row spanning all columns
        table.add(notif).colspan(4).center().pad(10);
        table.row();

// Add table to stage
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getMain().getBatch().begin();
        Main.getMain().getBatch().draw(GameAssetManager.getBackground(), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getMain().getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleStartNewGame();
    }

    @Override
    public void resize(int width, int height) {

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

    public StartNewGameController getController() {
        return controller;
    }

    public void setController(StartNewGameController controller) {
        this.controller = controller;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextButton getAddPlayerButton() {
        return addPlayerButton;
    }

    public void setAddPlayerButton(TextButton addPlayerButton) {
        this.addPlayerButton = addPlayerButton;
    }

    public TextField getAddPlayerField() {
        return addPlayerField;
    }

    public void setAddPlayerField(TextField addPlayerField) {
        this.addPlayerField = addPlayerField;
    }

    public TextButton getPlayButton() {
        return playButton;
    }

    public void setPlayButton(TextButton playButton) {
        this.playButton = playButton;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public void setBackButton(TextButton backButton) {
        this.backButton = backButton;
    }

    public SelectBox<Integer> getChooseMapBox() {
        return chooseMapBox;
    }

    public void setChooseMapBox(SelectBox<Integer> chooseMapBox) {
        this.chooseMapBox = chooseMapBox;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Label getNotif() {
        return notif;
    }

    public void setNotif(Label notif) {
        this.notif = notif;
    }

    public Label getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(Label menuTitle) {
        this.menuTitle = menuTitle;
    }
}
