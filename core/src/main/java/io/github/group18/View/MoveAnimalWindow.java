package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;

public class MoveAnimalWindow extends Window {
    public MoveAnimalWindow(String animalName, Skin skin, Stage stage) {
        super("Move " + animalName + " Outside", skin);

        this.setModal(true);
        this.setMovable(true);
        this.setSize(650, 360);
        this.setPosition(
            Gdx.graphics.getWidth() / 2f - this.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - this.getHeight() / 2f
        );

        Label xLabel = new Label("X:", skin);
        TextField xField = new TextField("", skin);

        Label yLabel = new Label("Y:", skin);
        TextField yField = new TextField("", skin);

        TextButton confirmButton = new TextButton("Confirm", skin);
        confirmButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                try {
                    int xPos = Integer.parseInt(xField.getText());
                    int yPos = Integer.parseInt(yField.getText());

                    Result result = GameMenuController.shepherdOutAnimals(animalName, xPos, yPos);
                    Dialog dialog = new Dialog("Result", skin);
                    dialog.text(result.getMessage());
                    dialog.button("OK");
                    dialog.show(stage);

                    if (result.isSuccessful()) {
                        remove();
                    }
                } catch (NumberFormatException e) {
                    Dialog error = new Dialog("Error", skin);
                    error.text("Invalid number format for X or Y!");
                    error.button("OK");
                    error.show(stage);
                }
            }
        });

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        this.add(xLabel).pad(5);
        this.add(xField).pad(5).width(100);
        this.row();
        this.add(yLabel).pad(5);
        this.add(yField).pad(5).width(100);
        this.row();

        Table buttonTable = new Table();
        buttonTable.add(confirmButton).pad(10);
        buttonTable.add(closeButton).pad(10);

        this.add(buttonTable).colspan(2);
        stage.addActor(this);
    }
}
