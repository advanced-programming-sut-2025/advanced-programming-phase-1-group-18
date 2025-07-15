package io.github.group18.View;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class CheatCodeDialog extends Dialog {

    private TextField cheatInput;

    public CheatCodeDialog(Stage stage, Skin skin) {
        super("Enter Cheat Code", skin);

        // ایجاد TextField برای ورودی چیت
        cheatInput = new TextField("", skin);
        cheatInput.setMessageText("Enter your cheat code here...");

        // افزودن به دیالوگ
        Table content = getContentTable();
        content.add(cheatInput).width(300).pad(10);

        // افزودن دکمه تایید
        button("OK", true);
        button("Cancel", false);
        stage.addActor(content);
        // نمایش دیالوگ
//        show(stage);
        System.out.println("Cheat Code: " + cheatInput.getText());
    }

    @Override
    protected void result(Object object) {
        boolean confirmed = (Boolean) object;
        if (confirmed) {
            String code = cheatInput.getText();
            System.out.println("Cheat Code Entered: " + code);

            processCheatCode(code);

        } else {
            System.out.println("Cheat Code entry cancelled.");
        }
    }

    private void processCheatCode(String code) {
        // ✅ این متد را مطابق با سیستم چیت بازی‌ات پیاده‌سازی کن
        if (code.equalsIgnoreCase("GODMODE")) {
            System.out.println("God Mode Activated!");
            // player.setGodMode(true);
        } else if (code.equalsIgnoreCase("MONEY1000")) {
            System.out.println("Added 1000 money!");
            // player.addMoney(1000);
        } else {
            System.out.println("Invalid Cheat Code.");
        }
    }
}
