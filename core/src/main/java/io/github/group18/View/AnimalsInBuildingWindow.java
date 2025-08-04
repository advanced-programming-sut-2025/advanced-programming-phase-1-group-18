package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.Scaling;
import io.github.group18.Model.*;
import io.github.group18.Controller.GameMenuController;

import java.util.List;

public class AnimalsInBuildingWindow extends Window {

    public AnimalsInBuildingWindow(String buildingType, Skin skin, Stage stage) {
        super("Animals in " + buildingType, skin);

        this.setModal(true);
        this.setMovable(true);
        this.setResizable(false);
        this.setSize(1100, 420);
        this.setPosition(
            Gdx.graphics.getWidth() / 2f - this.getWidth() / 2f,
            Gdx.graphics.getHeight() / 2f - this.getHeight() / 2f
        );

        Table animalTable = new Table(skin);
        animalTable.defaults().pad(10);

        animalTable.add("Name").left().padRight(40);
        animalTable.add("Type").left().padRight(40);
        animalTable.add("Price").left().padRight(40);
        animalTable.add("Outside?").left().padRight(40);
        animalTable.add("Navazesh?").left().padRight(40);
        animalTable.add("Sell").left().padRight(20);
        animalTable.add("Action").left();
        animalTable.row();

        List<Animal> allAnimals = App.getCurrentGame()
            .getPlayers()
            .get(App.getCurrentGame().getIndexPlayerinControl())
            .getMyBoughtAnimals();

        for (Animal animal : allAnimals) {
            if (animal instanceof TavilehAnimal) {
                TavilehAnimal tavilehAnimal = (TavilehAnimal) animal;
                if (tavilehAnimal.getWhereDoILive().equalsIgnoreCase(buildingType)) {

                    animalTable.add(tavilehAnimal.getName()).left().padRight(40);

                    Image animalImage = new Image(getTextureForType(tavilehAnimal.getType()));
                    animalImage.setScaling(Scaling.fit);
                    animalImage.setSize(40, 40);
                    animalTable.add(animalImage).left().padRight(40);

                    animalTable.add(String.valueOf(tavilehAnimal.getPrice())).left().padRight(40);

                    animalTable.add(tavilehAnimal.isOutside() ? "Yes" : "No").left().padRight(40);

                    animalTable.add(tavilehAnimal.isNavazesh() ? "Yes" : "No").left().padRight(40);

                    // Sell Button
                    TextButton sellButton = new TextButton("Sell", skin);
                    sellButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            sellAnimal(tavilehAnimal.getName(), stage, skin);
                        }
                    });
                    animalTable.add(sellButton).left().padRight(20);

                    TextButton navazeshButton = new TextButton("Navazesh", skin);
                    navazeshButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            tavilehAnimal.setNavazesh(true);
                        }
                    });
                    animalTable.add(navazeshButton).left().padRight(20);
                    // Action Button: Send Out or Send In
                    if (tavilehAnimal.isOutside()) {
                        TextButton sendInButton = new TextButton("Send In", skin);
                        sendInButton.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                Result result = GameMenuController.shepherdInAnimals(tavilehAnimal.getName());
                                Dialog dialog = new Dialog("Result", skin);
                                dialog.text(result.getMessage());
                                dialog.button("OK");
                                dialog.show(stage);
                                if (result.isSuccessful()) {
                                    remove();
                                    new AnimalsInBuildingWindow(buildingType, skin, stage);
                                }
                            }
                        });
                        animalTable.add(sendInButton).left();
                    } else {
                        TextButton sendOutButton = new TextButton("Send Out", skin);
                        sendOutButton.addListener(new ClickListener() {
                            @Override
                            public void clicked(InputEvent event, float x, float y) {
                                MoveAnimalWindow moveWindow = new MoveAnimalWindow(tavilehAnimal, skin, stage);
                                stage.addActor(moveWindow);
                            }
                        });
                        animalTable.add(sendOutButton).left();
                    }

                    animalTable.row();
                }
            }
        }

        ScrollPane scrollPane = new ScrollPane(animalTable, skin);
        scrollPane.setFadeScrollBars(false);
        this.add(scrollPane).expand().fill().row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        this.add(closeButton).pad(10);
        stage.addActor(this);
    }

    private Texture getTextureForType(Enum<?> type) {
        String path = "animals/" + type.name().toLowerCase() + ".png";
        return new Texture(Gdx.files.internal(path));
    }

    private void sellAnimal(String animalName, Stage stage, Skin skin) {
        Result result = GameMenuController.sellAnimal(animalName);
        Dialog dialog = new Dialog("Sell Result", skin);
        dialog.text(result.getMessage());
        dialog.button("OK");
        dialog.show(stage);
        if (result.isSuccessful()) {
            remove();
            stage.addActor(new AnimalsInBuildingWindow(getTitleLabel().getText().toString().replace("Animals in ", ""), skin, stage)); // refresh
        }
    }
}
