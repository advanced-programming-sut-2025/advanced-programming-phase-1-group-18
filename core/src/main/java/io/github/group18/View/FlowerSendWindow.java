package io.github.group18.View;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Flower;

public class FlowerSendWindow extends Window {

    public FlowerSendWindow(Skin skin, Stage stage, Player currentPlayer) {
        super("Send Flower 🌸", skin);
        setSize(1000, 800);
        setMovable(true);
        pad(20);
        setPosition((stage.getWidth() - getWidth()) / 2f, (stage.getHeight() - getHeight()) / 2f);

        Label userLabel = new Label("Target Username:", skin);
        TextField usernameField = new TextField("", skin);

        Label messageLabel = new Label("", skin);

        TextButton sendButton = new TextButton("Send Flower", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText().trim();
                if (username.isEmpty()) {
                    messageLabel.setText("Username cannot be empty.");
                    return;
                }
//Server-TODO
//                Result result = GameMenuController.flower(username);
//                messageLabel.setText(result.getMessage());
            }
        });

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        // Layout
        add(userLabel).left(); row();
        add(usernameField).width(300).padBottom(10); row();

        add(sendButton).pad(10); row();
        add(messageLabel).pad(10); row();
        add(closeButton).padTop(20);

        stage.addActor(this);
    }
}
