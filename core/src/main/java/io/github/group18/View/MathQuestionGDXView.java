package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.MathQuestionGDXController;
import io.github.group18.Main;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MathQuestionGDXView implements Screen {

    private Stage stage;

    private final Label titleLabel;
    private final Label questionsLabel;
    private final TextField questionNumberField;
    private final TextField answerField;
    private final Label errorLabel;
    private final TextButton verifyButton;
    private final TextButton goBackButton;

    public Table table;
    private final MathQuestionGDXController controller;

    // ذخیره سوال و جواب‌ها
    private final Map<Integer, Integer> correctAnswers = new HashMap<>();

    public MathQuestionGDXView(MathQuestionGDXController controller, Skin skin) {
        this.controller = controller;

        this.titleLabel = new Label("Security Questions", skin, "title");
        titleLabel.setColor(Color.CYAN);

        // تولید سه سوال ریاضی
        generateQuestions();

        this.questionsLabel = new Label(getQuestionsText(), skin);

        // فیلد شماره سوال
        this.questionNumberField = new TextField("", skin);
        questionNumberField.setMessageText("Enter question number (1-3)");

        // فیلد جواب
        this.answerField = new TextField("", skin);
        answerField.setMessageText("Enter your answer");

        this.errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);

        // دکمه‌ها
        this.verifyButton = new TextButton("Verify", skin);
        this.goBackButton = new TextButton("Go Back", skin);

        this.table = new Table();
        controller.setView(this);
    }

    private void generateQuestions() {
        Random rand = new Random();
        String[] ops = {"+", "-", "*"};
        for (int i = 1; i <= 3; i++) {
            int a = rand.nextInt(10) + 1;
            int b = rand.nextInt(10) + 1;
            String op = ops[rand.nextInt(ops.length)];
            int answer = 0;
            switch (op) {
                case "+": answer = a + b; break;
                case "-": answer = a - b; break;
                case "*": answer = a * b; break;
            }
            correctAnswers.put(i, answer);
            correctAnswers.put(-i, op.hashCode()); // برای نگهداری عملیات هم اگر لازم شد
            correctAnswers.put(100 + i, a * 100 + b); // ذخیره A و B در یک عدد
        }
    }

    private String getQuestionsText() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 3; i++) {
            int packed = correctAnswers.get(100 + i);
            int a = packed / 100;
            int b = packed % 100;
            String op = "";
            for (String o : new String[]{"+", "-", "*"}) {
                if (o.hashCode() == correctAnswers.get(-i)) {
                    op = o;
                    break;
                }
            }
            sb.append(i).append(") ").append(a).append(" ").append(op).append(" ").append(b).append(" = ?\n");
        }
        return sb.toString();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture bgTexture = new Texture(Gdx.files.internal("MathBG.jpg"));
        Image background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background);

        table.setFillParent(true);

        table.add(titleLabel).padBottom(15).row();
        table.add(questionsLabel).padBottom(15).row();
        table.add(questionNumberField).width(300).padBottom(10).row();
        table.add(answerField).width(300).padBottom(10).row();
        table.add(errorLabel).padBottom(15).row();
        table.add(verifyButton).padBottom(10).row();
        table.add(goBackButton);

        ScrollPane scrollPane = new ScrollPane(table);
        scrollPane.setFillParent(true);
        stage.addActor(scrollPane);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        controller.handleMathQuestionButtons();
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

    // Getters
    public TextButton getVerifyButton() { return verifyButton; }
    public TextButton getGoBackButton() { return goBackButton; }
    public TextField getAnswerField() { return answerField; }
    public TextField getQuestionNumberField() { return questionNumberField; }
    public Map<Integer, Integer> getCorrectAnswers() { return correctAnswers; }

    // Setter for error
    public void setErrorLabel(String msg) {
        errorLabel.setText(msg);
    }
}
