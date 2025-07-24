package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.RegisterGDXController;
import io.github.group18.Controller.RegisterLoginGdxController;
import io.github.group18.Main;

public class RegisterGDXView implements Screen {

    private Stage stage;
    private final Label registerTitle;
    private final TextField username;
    private final Label usernameerror;
    private final TextField password;
    private final Label passworderror;
    private final TextField repassword;
    private final Label repassworderror;
    private final TextField nickname;
    private final TextField email;
    private final Label emailerror;
    private final TextField gender;
    private final Label gendererror;
    private final TextButton verify;
    private final TextButton goBack;
    private final TextButton goToLogin;
    public Table table;

    private final RegisterGDXController controller;

    public RegisterGDXView(RegisterGDXController controller, Skin skin)
    {
        this.controller = controller;
        this.registerTitle = new Label("Register Menu", skin);
        registerTitle.setColor(Color.RED);
        this.username = new TextField("", skin);
        username.setMessageText("Username");
        this.usernameerror = new Label("", skin);
        this.password = new TextField("", skin);
        password.setMessageText("Password");
        this.passworderror = new Label("", skin);
        this.repassword = new TextField("", skin);
        repassword.setMessageText("Password again");
        this.repassworderror = new Label("", skin);
        this.nickname = new TextField("", skin);
        nickname.setMessageText("Nickname");
        this.email = new TextField("", skin);
        email.setMessageText("Email");
        this.emailerror = new Label("", skin);
        this.gender = new TextField("", skin);
        gender.setMessageText("Gender");
        this.gendererror = new Label("", skin);
        this.verify = new TextButton("Verify",skin);
        this.goBack = new TextButton("Go Back",skin);
        this.goToLogin = new TextButton("GoToLoginMenu",skin);
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
        table.add(registerTitle);
        table.row().pad(1, 0, 10, 0);
        table.add(username).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(usernameerror);
        table.row().pad(1, 0, 10, 0);
        table.add(password).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(passworderror);
        table.row().pad(1, 0, 10, 0);
        table.add(repassword).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(repassworderror);
        table.row().pad(1, 0, 10, 0);
        table.add(nickname).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(email).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(emailerror).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(gender).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(gendererror).width(600);
        table.row().pad(1, 0, 10, 0);
        table.add(verify);
        table.row().pad(1, 0, 10, 0);
        table.add(goToLogin);
        table.row().pad(1, 0, 10, 0);
        table.add(goBack);

        //for controlling by scroll
        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setFillParent(true);
        stage.addActor(scrollPane);
    }

    @Override
    public void render(float v)
    {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleRegisterGDXButtons();
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
    //buttons
    public TextButton getGoBackButton() {
        return goBack;
    }
    public TextButton getVerifyButton() {
        return verify ;
    }
    public TextButton getGoToLoginButton() {
        return goToLogin;
    }
    //Fields
    public TextField getUsernameField() {
        return username;
    }
    public TextField getPasswordField() {
        return password;
    }
    public TextField getRepasswordField() {
        return repassword;
    }
    public TextField getNicknameField() {
        return nickname;
    }
    public TextField getEmailField() {
        return email;
    }
    public TextField getGenderField() {
        return gender;
    }
    //errors
    public void setUsernameErrorLabel(String msg) {
        usernameerror.setText(msg);
    }
    public void setPasswordErrorLabel(String msg) {
        passworderror.setText(msg);
    }
    public void setRepasswordErrorLabel(String msg) {
        repassworderror.setText(msg);
    }
    public void setEmailErrorLabel(String msg) {
        emailerror.setText(msg);
    }
    public void setGenderErrorLabel(String msg) {
        gendererror.setText(msg);
    }
}
