package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import io.github.group18.Controller.CarpentersShopController;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import io.github.group18.enums.TavilehAnimalEnums;


public class MoveAnimalWindow extends Window {
    public MoveAnimalWindow(TavilehAnimal tavilehAnimal, Skin skin, Stage stage) {
        super("Move " + tavilehAnimal.getName() + " Outside", skin);

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

                    Result result = GameMenuController.shepherdOutAnimals(tavilehAnimal.getName(), xPos, yPos);
                    Dialog dialog = new Dialog("Result", skin);
                    dialog.text(result.getMessage());
                    dialog.button("OK");
                    dialog.show(stage);

                    if (result.isSuccessful()) {
                        remove();
                        if (tavilehAnimal.getType().equals(TavilehAnimalEnums.Cow)) {
                            Texture cowTexture = new Texture(Gdx.files.internal("animalSprite/cow_sheet.png"));
                            TextureRegion[][] frames = TextureRegion.split(cowTexture, 32, 32);

                            // چک کردن فریم‌های null (اختیاری)
                            for (int row = 0; row < frames.length; row++) {
                                for (int col = 0; col < frames[row].length; col++) {
                                    if (frames[row][col] == null) {
                                        System.out.println("⚠️ Null در frames[" + row + "][" + col + "]");
                                    }
                                }
                            }

                            TextureRegion[] walkDownFrames = new TextureRegion[4];
                            for (int i = 0; i < 4; i++) {
                                walkDownFrames[i] = frames[2][i];
                            }

                            Animation<TextureRegion> walkAnim = new Animation<>(0.6f, walkDownFrames);

                            int startX = CarpentersShopController.getA();
                            int startY = CarpentersShopController.getB();
                            Vector2 start = new Vector2(startX * 64, startY * 64);
                            Vector2 target = new Vector2(xPos * 64, yPos * 64);

                            AnimalActor cowActor = new AnimalActor(walkAnim, start, target);
                            cowActor.setScale(0.5f);
                            stage.addActor(cowActor);

                            float duration = 8f;
                            cowActor.addAction(Actions.moveTo(target.x, target.y, duration));
                        }
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
