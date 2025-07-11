package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.ForgotPasswordGDXController;
import io.github.group18.Controller.RegisterGDXController;
import io.github.group18.Main;

import java.util.Random;

public class ForgotPasswordGDXView implements Screen {


    private Stage stage;
    private final TextField username;
    private final Label usernameerror;
    private final TextField number;
    private final Label numbererror;
    private final TextField answer;
    private final Label answererror;
    private final TextField newPassword;
    private final Label newpassworderror;
    private final TextButton verify;
    private final TextButton goBack;
    private final Label label1;
    private final Label label2;
    private final Label label3;
    private final static int randomMade1 = RandomMaker();
    private final static int randomMade2 = RandomMaker();
    private final static int randomMade3 = RandomMaker();
    private final static int randomMade4 = RandomMaker();
    private final static int randomMade5 = RandomMaker();
    private final static int randomMade6 = RandomMaker();

    public Table table;

    private final ForgotPasswordGDXController controller;

    public ForgotPasswordGDXView(ForgotPasswordGDXController controller, Skin skin) {
        this.controller = controller;
        this.username = new TextField("enter your username: ", skin);
        this.usernameerror = new Label("", skin);

        this.number = new TextField("enter the number: ", skin);
        this.answer = new TextField("enter the answer: ", skin);
        this.newPassword = new TextField("enter the new password: ", skin);
        this.label1 = new Label("1. "+randomMade1+" + "+ randomMade2+" ", skin);
        label1.setColor(Color.RED);
        label1.setFontScale(3);
        this.label2 = new Label("2. "+randomMade3+" + "+ randomMade4+" ", skin);
        label2.setColor(Color.RED);
        label2.setFontScale(3);
        this.label3 = new Label("3. "+randomMade5+" + "+ randomMade6+" ", skin);
        label3.setColor(Color.RED);
        label3.setFontScale(3);
        this.verify = new TextButton("Verify", skin);
        this.goBack = new TextButton("Go Back", skin);

        this.answererror = new Label("", skin);
        this.numbererror = new Label("", skin);
        this.newpassworderror = new Label("", skin);


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

        table.add(username).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(usernameerror);
        table.row().pad(10, 0, 10, 0);
        table.add(label1);
        table.row().pad(10, 0, 10, 0);
        table.add(label2);
        table.row().pad(10, 0, 10, 0);
        table.add(label3);
        table.row().pad(10, 0, 10, 0);
        table.add(number).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(numbererror);
        table.row().pad(10, 0, 10, 0);
        table.add(answer).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(answererror);
        table.row().pad(10, 0, 10, 0);
        table.add(newPassword).width(600);
        table.row().pad(10, 0, 10, 0);
        table.add(newpassworderror);
        table.row().pad(10, 0, 10, 0);
        table.add(verify);
        table.row().pad(2, 0, 10, 0);
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
        controller.handleForgotPasswordsButtons();
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




    //functions
    public static int RandomMaker()
    {
        Random rand = new Random();
        int random1 = rand.nextInt(50);
        return random1;

    }

    public static int getRandomMade1()
    {
        return randomMade1+randomMade2;
    }
    public static int getRandomMade2()
    {
        return randomMade3+randomMade4;
    }
    public static int getRandomMade3()
    {
        return randomMade5+randomMade6;
    }

    //buttons
    public TextButton getVerifyButton() {
        return verify;
    }
    public TextButton getGoBackButton() {
        return goBack;
    }
    //Fields
    public TextField getUsername() {
        return username;
    }
    public TextField getNumber() {
        return number;
    }
    public TextField getAnswer() {
        return answer;
    }
    public TextField getNewPassword() {
        return newPassword;
    }

    //errors
    public void setUsernameErrorLabel(String msg) {
        usernameerror.setText(msg);
    }
    public void setNumberErrorLabel(String msg) {
        numbererror.setText(msg);
    }
    public void setAnswerErrorLabel(String msg) {
        answererror.setText(msg);
    }
    public void setNewPasswordErrorLabel(String msg) {
        newpassworderror.setText(msg);
    }
}
