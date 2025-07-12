package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.View.LoginGDXView;
import io.github.group18.View.RegisterGDXView;
import io.github.group18.View.RegisterLoginGdxView;

public class RegisterLoginGdxController
{
    private RegisterLoginGdxView view;

    public void setView(RegisterLoginGdxView view) {
        this.view = view;
    }

    public void handleRegisterLoginButtons() {
        if (view != null)
        {
            if (view.getRegisterButton().isChecked())
            {
                Main.getMain().setScreen(new RegisterGDXView(new RegisterGDXController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
            //
            if(view.getLoginButton().isChecked())
            {
                Main.getMain().setScreen(new LoginGDXView(new LoginGDXController(),GameAssetManager.getGameAssetManager().getSkin()));
            }
            //
            if(view.getExitButton().isChecked())
            {
                System.exit(0);
            }

        }
    }
}
