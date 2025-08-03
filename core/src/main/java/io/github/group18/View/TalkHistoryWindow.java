package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import java.util.ArrayList;
import io.github.group18.Model.Result;


public class TalkHistoryWindow extends Window {

    private TextArea historyArea;
    private TextField usernameField;

    public TalkHistoryWindow(Skin skin, Stage stage, Player currentPlayer) {
        super("Talk History", skin);

        setSize(1000, 800);
        setMovable(true);
        pad(20);
        setPosition(
            (stage.getWidth() - getWidth()) / 2f,
            (stage.getHeight() - getHeight()) / 2f
        );

        Label promptLabel = new Label("Enter Username:", skin);
        usernameField = new TextField("", skin);
        TextButton showButton = new TextButton("Show History", skin);

        historyArea = new TextArea("", skin);
        historyArea.setDisabled(true);
        historyArea.setText("Enter a username and press Show History.");

        ScrollPane scrollPane = new ScrollPane(historyArea, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);

        showButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String targetUsername = usernameField.getText().trim();
                if (targetUsername.isEmpty()) {
                    historyArea.setText("Please enter a username.");
                    return;
                }
                //Server-TODO
//                Result talkHistoryResult = GameMenuController.talkHistory(targetUsername);
//                if (talkHistoryResult.isSuccessful()) {
//                    historyArea.setText(talkHistoryResult.getMessage());
//                } else {
//                    historyArea.setText("No messages found or player not found.");
//                }
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
        Table inputTable = new Table();
        inputTable.add(promptLabel).padRight(10);
        inputTable.add(usernameField).width(200);
        inputTable.add(showButton).padLeft(10);

        add(inputTable).left().padBottom(10).row();
        add(scrollPane).width(550).height(300).padBottom(10).row();
        add(closeButton).center();

    }
}

