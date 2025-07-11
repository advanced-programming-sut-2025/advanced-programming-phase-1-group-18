package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.RegisterLoginGdxController;
import io.github.group18.Main;

public class RegisterLoginGdxView implements Screen {

    private Stage stage;
    private final TextButton registerButton;
    private final TextButton exitButton;
    private final TextButton loginButton;
    private final Label gameTitle;
    private final Label sharifTitle;
    public Table table;

    private final RegisterLoginGdxController controller;


    public RegisterLoginGdxView(RegisterLoginGdxController controller, Skin skin) {
        this.controller = controller;

        this.registerButton = new TextButton("RegisterMenu", skin);

        this.loginButton = new TextButton("LoginMenu", skin,"toggle");

        this.exitButton = new TextButton("EXIT", skin);

        // Labels
        this.gameTitle = new Label("       ", skin);

        gameTitle.setColor(Color.RED);
        gameTitle.setFontScale(7);
        this.sharifTitle = new Label("SHARIF CE 2025", skin);
        sharifTitle.setColor(Color.BLACK);
        sharifTitle.setFontScale(5);

        this.table = new Table();
        controller.setView(this);

    }
    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        table.setFillParent(true);
        table.row().pad(10, 0 , 10 , 0);
        table.row().pad(10, 0 , 10 , 0);
        table.row().pad(10, 0 , 10 , 0);
        table.add(gameTitle);
        table.row().pad(10, 0 , 10 , 0);
        table.add(registerButton);
        table.row().pad(10, 0 , 10 , 0);
        table.add(loginButton);
        table.row().pad(10, 0 , 10 , 0);
        table.add(exitButton);
        table.row().pad(10, 0 , 10 , 0);
        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = font;
        style.fontColor = Color.PURPLE;

        Label escapeHint = new Label("SHARIF CE 2025", style);
        escapeHint.setFontScale(6.0f);
        escapeHint.getColor().a = 1f;
        escapeHint.addAction(Actions.forever(
            Actions.sequence(
                Actions.fadeOut(0.8f),
                Actions.fadeIn(0.8f)
            )
        ));
        table.row().padTop(5);
        table.add(escapeHint);

        //for background
        Texture bgTexture = new Texture(Gdx.files.internal("Logo_of_Stardew_Valley.png"));
        Image background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background);
        stage.addActor(table);
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);

        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleRegisterLoginButtons();
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




    // get buttons
    public TextButton getRegisterButton() {
        return registerButton;
    }

    public TextButton getExitButton() {
        return exitButton;
    }

    public TextButton getLoginButton() {
        return loginButton;
    }
}
