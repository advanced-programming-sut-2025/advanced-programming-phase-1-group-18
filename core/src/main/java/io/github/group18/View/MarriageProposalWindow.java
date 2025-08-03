package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Flower;

public class MarriageProposalWindow extends Window {
    public MarriageProposalWindow(Skin skin, Stage stage, Player currentPlayer) {
        super("💍 Marriage Proposal", skin);
        setSize(900, 800);
        setMovable(true);
        pad(20);
        setPosition((stage.getWidth() - getWidth()) / 2f, (stage.getHeight() - getHeight()) / 2f);

        Label userLabel = new Label("Target Username:", skin);
        TextField usernameField = new TextField("", skin);

        Label ringLabel = new Label("Ring Name:", skin);
        TextField ringField = new TextField("", skin);

        Label messageLabel = new Label("", skin);

        TextButton sendButton = new TextButton("Send Proposal", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String username = usernameField.getText().trim();
                String ring = ringField.getText().trim();
//Server-TODO
//                Result result = GameMenuController.askMarriage(username, ring);
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

        add(userLabel).left(); row();
        add(usernameField).width(300).padBottom(10); row();

        add(ringLabel).left(); row();
        add(ringField).width(300).padBottom(10); row();

        add(sendButton).pad(10); row();
        add(messageLabel).pad(10); row();
        add(closeButton).padTop(20);

        stage.addActor(this);
    }
}
