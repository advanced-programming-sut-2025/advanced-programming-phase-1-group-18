package io.github.group18.View;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;

import java.util.function.Consumer;

public class PlaceEntityDialog extends Dialog {

    public PlaceEntityDialog(Stage stage, Skin skin, Consumer<String> onConfirm) {
        super("Place Entity", skin);

        TextField inputField = new TextField("", skin);
        inputField.setMessageText("Enter name");
        getContentTable().add(inputField).width(250).pad(10);
        getContentTable().row();

        TextButton confirmButton = new TextButton("OK", skin);
        confirmButton.addListener(e -> {
            if (inputField.getText().length() > 0) {
                onConfirm.accept(inputField.getText());
                this.hide();
            }
            return true;
        });

        getButtonTable().add(confirmButton).pad(10);
        setMovable(true);
        setModal(true);
        setResizable(false);

        setPosition(stage.getWidth() / 2f - 150, stage.getHeight() / 2f - 75, Align.center);
        setWidth(300);
        setHeight(150);
    }
}
