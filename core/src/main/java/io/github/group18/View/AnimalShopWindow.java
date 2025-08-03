package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Scaling;
import io.github.group18.Model.*;

import java.util.Map;

public class AnimalShopWindow extends Window {

    public interface AnimalSelectionHandler {
        void onAnimalSelected(String animalType, String customName);
    }

    public AnimalShopWindow(Skin skin, Stage stage, AnimalSelectionHandler handler) {
        super("Choose Your Animal", skin);

        this.setModal(true);
        this.setMovable(true);
        this.setResizable(false);
        this.setSize(700, 500);
        this.setPosition(
            Gdx.graphics.getWidth() / 2f - this.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - this.getHeight() / 2f
        );

        ScrollPane scrollPane = new ScrollPane(createAnimalList(skin, stage, handler), skin);
        scrollPane.setFadeScrollBars(false);
        this.add(scrollPane).expand().fill().row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                remove();
            }
        });
        this.add(closeButton).pad(10).center();
    }

    private Table createAnimalList(Skin skin, Stage stage, AnimalSelectionHandler handler) {
        Table table = new Table(skin);

        Map<String, Integer> animalPrices = Map.of(
            "Cow", 1600,
            "Sheep", 8000,
            "Goat", 4000,
            "Pig", 16000,
            "Chicken", 800,
            "Duck", 1200,
            "Rabbit", 8000,
            "Dinosaur", 14000
        );

        for (Map.Entry<String, Integer> entry : animalPrices.entrySet()) {
            String animalType = entry.getKey();
            int price = entry.getValue();
            String displayText = animalType + " - " + price + "g";

            Table row = new Table(skin);

            // Load image
            Image animalImage = createAnimalImage(animalType.toLowerCase());
            row.add(animalImage).width(48).height(48).padRight(10);

            row.add(new Label(displayText, skin)).width(200).left();

            TextField nameField = new TextField("", skin);
            nameField.setMessageText("Enter Name");
            row.add(nameField).width(250).padLeft(10);

            TextButton selectButton = new TextButton("Buy", skin);
            selectButton.addListener(new ClickListener() {
                @Override
                public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                    String customName = nameField.getText().trim();
                    if (customName.isEmpty()) {
                        Dialog errorDialog = new Dialog("Error", skin);
                        errorDialog.text("Please enter a name for your animal.");
                        errorDialog.button("OK");
                        errorDialog.show(stage);
                        return;
                    }

                    handler.onAnimalSelected(animalType, customName);
                    remove();
                }
            });

            row.add(selectButton).width(80).padLeft(10);
            row.pad(10);
            table.add(row).fillX().row();
        }

        return table;
    }

    private Image createAnimalImage(String animalName) {
        String texturePath = "animals/" + animalName + ".png";
        Texture texture;

        try {
            texture = new Texture(Gdx.files.internal(texturePath));
        } catch (Exception e) {
            // Fallback if image not found
            texture = new Texture(Gdx.files.internal("animals/unknown.png"));
        }

        Image image = new Image(texture);
        image.setScaling(Scaling.fit);
        return image;
    }
}
