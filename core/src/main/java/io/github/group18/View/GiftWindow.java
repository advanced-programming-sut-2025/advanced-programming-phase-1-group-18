package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;

import java.util.Map;

public class GiftWindow extends Window {

    public GiftWindow(Skin skin, Stage stage, Player currentPlayer) {
        super("Send Gift", skin);
        setSize(1000, 800);
        setMovable(true);
        pad(20);
        setPosition((stage.getWidth() - getWidth()) / 2f, (stage.getHeight() - getHeight()) / 2f);

        // Input fields
        Label userLabel = new Label("Target Username:", skin);
        TextField usernameField = new TextField("", skin);

        Label itemLabel = new Label("Item Name:", skin);
        TextField itemField = new TextField("", skin);

        Label amountLabel = new Label("Amount:", skin);
        TextField amountField = new TextField("", skin);

        Label messageLabel = new Label("", skin);

        TextButton sendButton = new TextButton("Send Gift", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText().trim();
                String item = itemField.getText().trim();
                int amount;
                try {
                    amount = Integer.parseInt(amountField.getText().trim());
                } catch (NumberFormatException e) {
                    messageLabel.setText("Invalid amount");
                    return;
                }

                Result result = GameMenuController.gift(username, amount, item);
                messageLabel.setText(result.getMessage());
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

        add(itemLabel).left(); row();
        add(itemField).width(300).padBottom(10); row();

        add(amountLabel).left(); row();
        add(amountField).width(100).padBottom(10); row();

        add(sendButton).pad(10); row();
        add(messageLabel).pad(10); row();
        add(closeButton).padTop(20);

        stage.addActor(this);
    }
}
