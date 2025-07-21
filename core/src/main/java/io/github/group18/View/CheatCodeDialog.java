package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;

import java.util.Scanner;

public class CheatCodeDialog extends Dialog {

    private TextField cheatInput;
    private GameMenuInputAdapter gameMenuInputAdapter;

    public CheatCodeDialog(Stage stage, Skin skin, GameMenuInputAdapter gameMenuInputAdapter) {
        super("Enter Cheat Code", skin);
        this.gameMenuInputAdapter = gameMenuInputAdapter;
        // ایجاد TextField برای ورودی چیت
        cheatInput = new TextField("", skin);
        cheatInput.setMessageText("Enter your cheat code here...");

        // افزودن به دیالوگ
        Table content = getContentTable();
        content.add(cheatInput).width(800).pad(10);

        // افزودن دکمه تایید
        button("OK", true);
        button("Cancel", false);
//        stage.addActor(content);
        // نمایش دیالوگ
//        show(stage);
        System.out.println("Cheat Code: " + cheatInput.getText());
    }

    @Override
    protected void result(Object object) {
        boolean confirmed = (Boolean) object;
        if (confirmed) {
            String code = cheatInput.getText();
//            System.out.println("Cheat Code Entered: " + code);

            processCheatCode(code);

        } else {
            System.out.println("Cheat Code entry cancelled.");
        }
        Gdx.input.setInputProcessor(gameMenuInputAdapter);
    }

    private void processCheatCode(String code) {
        // ✅ این متد را مطابق با سیستم چیت بازی‌ات پیاده‌سازی کن
        Scanner scanner = new Scanner(System.in);
        GameMenuMenu gameMenuMenu = new GameMenuMenu(App.getGameMenuController(),
            GameAssetManager.getGameAssetManager().getSkin());
        gameMenuMenu.check(code,scanner);
    }
}
