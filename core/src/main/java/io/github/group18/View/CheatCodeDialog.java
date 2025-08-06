package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import io.github.group18.Controller.GameController;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Network.Client.App.ClientModel;

import java.util.Scanner;

public class CheatCodeDialog extends Window {

    private TextField cheatInput;
    private GameController gameController;

    public CheatCodeDialog(Skin skin, GameController gameController) {
        super("Enter Cheat Code", skin);
        this.gameController = gameController;

        setSize(1000, 400);
        setMovable(true);
        pad(10);

        cheatInput = new TextField("", skin);
        cheatInput.setMessageText("Enter your cheat code here...");

        TextButton sendButton = new TextButton("Send", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String code = cheatInput.getText().trim();
                processCheatCode(code);
                remove();  // بستن پنجره
                ClientModel.setWindowOpen(false);
                // بعد از بستن، ورودی رو به استیج بازگردان
//                if (getStage() != null) {
//                    Gdx.input.setInputProcessor(getStage());
//                }
            }
        });

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
                ClientModel.setWindowOpen(false);
//                if (getStage() != null) {
//                    Gdx.input.setInputProcessor(getStage());
//                }
            }
        });

        // اضافه کردن ویجت‌ها به لایه
        add(cheatInput).width(500).row();
        add(sendButton).padTop(10).left();
        add(closeButton).padTop(10).left();

        // مرکز کردن پنجره
        setPosition(
            (Gdx.graphics.getWidth() - getWidth()) / 2f,
            (Gdx.graphics.getHeight() - getHeight()) / 2f
        );

    }

    private void processCheatCode(String code) {
        Scanner scanner = new Scanner(System.in);
        GameMenuMenu gameMenuMenu = new GameMenuMenu(App.getGameMenuController(), GameAssetManager.getGameAssetManager().getSkin());
        gameMenuMenu.check(code, scanner, gameController);
    }
}
