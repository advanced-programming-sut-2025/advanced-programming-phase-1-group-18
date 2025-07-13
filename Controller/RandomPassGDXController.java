package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.User;
import io.github.group18.View.*;
import io.github.group18.enums.Menu;

public class RandomPassGDXController
{
    private RandomPassGDXView view;

    public void setView(RandomPassGDXView view) {
        this.view = view;
    }

    public void handleRandomPassGDXButtons() {
        if (view != null)
        {
            if(view.getGoBackButton().isChecked())
            {
                Main.getMain().setScreen(new RegisterGDXView(new RegisterGDXController(),GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getShowButton().isChecked())
            {
                String randPass = RegisterMenuController.PasswordGenerator();
                view.setRandomPassLabel(randPass);
            }
            if(view.getCopyButton().isChecked())
            {
                Gdx.app.getClipboard().setContents(view.getRandomPass());
            }
        }
    }
}
