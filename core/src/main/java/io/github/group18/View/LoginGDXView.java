package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.LoginGDXController;
import io.github.group18.Controller.RegisterGDXController;
import io.github.group18.Main;

public class LoginGDXView implements Screen {


    private Stage stage;
    private final Label loginLabel;
    private final TextField username;
    private final Label usernameerror;
    private final TextField password;
    private final Label passworderror;
    private final TextField stayLoggedIn;
    private final Label stayLoggedinerror;
    private final TextButton Veirfy;
    private final TextButton goBack;
    private final TextButton goToRegisterMenu;
    private final TextButton forgotPasswordButton;


    public Table table;

    private final LoginGDXController controller;
    public LoginGDXView(LoginGDXController controller, Skin skin)
    {
        this.controller = controller;
        this.loginLabel= new Label("login Menu", skin, "title");
        loginLabel.setColor(Color.RED);
//        loginLabel.setFontScale(3);
        this.username = new TextField("a", skin);
        username.setMessageText("Username");
        this.usernameerror = new Label("", skin);
        this.password = new TextField("aaAA11@@", skin);
        password.setMessageText("Password");
        this.passworderror = new Label("", skin);
        this.goBack = new TextButton("Go Back",skin);
        this.Veirfy =  new TextButton("Veirfy",skin);
        this.forgotPasswordButton = new TextButton("Forgot Password",skin);
        this.stayLoggedIn = new TextField("no", skin);
        stayLoggedIn.setMessageText("stay logged in:(Just 'Yes' or 'No')");
        this.stayLoggedinerror = new Label("", skin);
        this.goToRegisterMenu = new TextButton("Go Back to Is_Unique Menu",skin);
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
        table.add(loginLabel);
        table.row().pad(10, 0, 10, 0);
        table.add(username).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(usernameerror);
        table.row().pad(10, 0, 10, 0);
        table.add(password).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(passworderror);
        table.row().pad(10, 0, 10, 0);
        table.add(stayLoggedIn).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(stayLoggedinerror);
        table.row().pad(10, 0, 10, 0);
        table.add(Veirfy);
        table.row().pad(10, 0, 10, 0);
        table.add(forgotPasswordButton);
        table.row().pad(10, 0, 10, 0);
        table.add(goToRegisterMenu);
        table.row().pad(10, 0, 10, 0);
        table.add(goBack);

        //for controlling by scroll
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
        controller.handleLoginGDXButtons();
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


    /// //


    //buttons
    public TextButton getGoBackButton() {
        return goBack;
    }
    public TextButton getVerifyButton() {
        return Veirfy;
    }
    public TextButton getForgotPasswordButton() {
        return forgotPasswordButton;
    }
    public TextButton getGoToRegisterMenuButton() {
        return goToRegisterMenu;
    }
    //Fields
    public TextField getUsernameField() {
        return username;
    }
    public TextField getPasswordField() {
        return password;
    }
    public TextField getStayLoggedInField() {
        return stayLoggedIn;
    }
    //errors
    public void setUsernameErrorLabel(String msg) {
        usernameerror.setText(msg);
    }
    public void setPasswordErrorLabel(String msg) {
        passworderror.setText(msg);
    }
    public void setStayLoggedInErrorLabel(String msg) {
        stayLoggedinerror.setText(msg);
    }


}
