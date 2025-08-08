package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.View.MainMenu;
import io.github.group18.View.MathQuestionGDXView;
import io.github.group18.View.RegisterGDXView;

import java.util.Map;

public class MathQuestionGDXController {

    private MathQuestionGDXView view;

    public void setView(MathQuestionGDXView view) {
        this.view = view;
    }

    public void handleMathQuestionButtons() {
        if (view != null) {

            view.setErrorLabel("");


            if (view.getGoBackButton().isChecked()) {
                Main.getMain().setScreen(
                    new RegisterGDXView(
                        new RegisterGDXController(),
                        GameAssetManager.getGameAssetManager().getSkin()
                    )
                );
            }


            if (view.getVerifyButton().isChecked()) {
                String questionNumberText = view.getQuestionNumberField().getText().trim();
                String answerText = view.getAnswerField().getText().trim();

                try {
                    int questionNumber = Integer.parseInt(questionNumberText);
                    int userAnswer = Integer.parseInt(answerText);

                    if (questionNumber < 1 || questionNumber > 3) {
                        view.setErrorLabel("Question number must be between 1 and 3.");
                        return;
                    }


                    Map<Integer, Integer> correctAnswers = view.getCorrectAnswers();
                    int correctAnswer = correctAnswers.get(questionNumber);

                    if (userAnswer == correctAnswer) {
                        view.setErrorLabel("Correct! Welcome to the game.");

                        Main.getMain().setScreen(
                            new MainMenu(
                                new MainMenuController(),
                                GameAssetManager.getGameAssetManager().getSkin()
                            )
                        );
                    } else {
                        view.setErrorLabel("Incorrect answer, try again.");
                    }

                } catch (NumberFormatException e) {
                    view.setErrorLabel("Please enter valid numbers for question and answer.");
                }
            }
        }
    }
}
