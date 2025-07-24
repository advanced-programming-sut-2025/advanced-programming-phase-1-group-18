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

public class RandomPassGDXView implements Screen {

    private Stage stage;
    private final Label randomPassLabel;
    private final TextButton goBack;
    private final TextButton Show;
    private final TextButton copy;
    public Table table;

    private final RandomPassGDXController controller;

    public RandomPassGDXView(RandomPassGDXController controller, Skin skin) {
        this.controller = controller;
        this.randomPassLabel = new Label("", skin);
        this.goBack = new TextButton("Go Back", skin);
        this.Show = new TextButton("Show (OR CHANGE)", skin);
        this.copy = new TextButton("Copy", skin);
        this.table = new Table();
        controller.setView(this);
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
        table.add(randomPassLabel);
        table.row().pad(10, 0, 10, 0);
        table.add(Show);
        table.row().pad(10, 0, 10, 0);
        table.add(copy);
        table.row().pad(10, 0, 10, 0);
        table.add(goBack);


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


    //Buttons
    public TextButton getGoBackButton() {
        return goBack;
    }

    public TextButton getShowButton() {
        return Show;
    }

    public TextButton getCopyButton() {
        return copy;
    }

    //fill Label
    public void setRandomPassLabel(String msg) {
        randomPassLabel.setText(msg);
    }
    public String getRandomPass() {
        return(randomPassLabel.getText().toString());
    }




}

