package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import java.util.ArrayList;

public class TalkWindow extends Window {

    public TalkWindow(Skin skin, Stage stage, Player currentPlayer) {
        super("Talk to Player", skin);

        setSize(1000, 850);
        setMovable(true);
        pad(20);
        setPosition(
            (stage.getWidth() - getWidth()) / 2f,
            (stage.getHeight() - getHeight()) / 2f
        );

        Label userLabel = new Label("Target Username:", skin);
        TextField userField = new TextField("", skin);

        Label msgLabel = new Label("Message:", skin);
        TextField msgField = new TextField("", skin);

        Label resultLabel = new Label("", skin);

        TextButton sendButton = new TextButton("Send", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = userField.getText().trim();
                String message = msgField.getText().trim();
                Result result = GameMenuController.talk(username, message);
                resultLabel.setText(result.getMessage());
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
        add(userLabel).left().padBottom(5).row();
        add(userField).width(300).padBottom(10).row();

        add(msgLabel).left().padBottom(5).row();
        add(msgField).width(300).padBottom(10).row();

        add(sendButton).padTop(5).row();
        add(resultLabel).left().padTop(10).row();

        add(closeButton).padTop(10);

    }
}
