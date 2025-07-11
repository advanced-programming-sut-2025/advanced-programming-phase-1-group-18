package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Result;
import io.github.group18.Controller.LoginMenuController;
import io.github.group18.Model.User;
import io.github.group18.View.ForgotPasswordGDXView;
import io.github.group18.View.LoginGDXView;
import io.github.group18.View.RegisterGDXView;
import io.github.group18.View.RegisterLoginGdxView;
import io.github.group18.enums.Menu;

import static io.github.group18.Controller.LoginMenuController.findUserByUsername;

public class LoginGDXController
{
    private LoginGDXView view;

    public void setView(LoginGDXView view) {
        this.view = view;
    }

    public void handleLoginGDXButtons() {
        if (view != null)
        {
            view.setPasswordErrorLabel("");
            view.setStayLoggedInErrorLabel("");
            view.setUsernameErrorLabel("");



            if(view.getGoBackButton().isChecked())
            {
                Main.getMain().setScreen(new RegisterLoginGdxView(new RegisterLoginGdxController(),GameAssetManager.getGameAssetManager().getSkin()));
            }
            if(view.getVerifyButton().isChecked())
            {
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                String stayLoggedIn  = view.getStayLoggedInField().getText();

                //errors//
                boolean isOkay = true;
                //first:
                if (findUserByUsername(username) == null) {
                    view.setUsernameErrorLabel("incorrect username!");
                    isOkay = false;
                }

                //second:
                if(!(stayLoggedIn.toLowerCase().equals("yes") || stayLoggedIn.toLowerCase().equals("no")))
                {
                    view.setStayLoggedInErrorLabel("You should enter 'YES' or 'NO'");
                    isOkay = false;
                }

                //third:
                User user = findUserByUsername(username);
                String hashedInput = RegisterMenuController.hashPasswordSHA256(password);
                if (user!=null && !user.getPassword().equals(hashedInput) && user.getPassword()!=null) {
                    view.setPasswordErrorLabel("Wrong Password!");
                    isOkay = false;
                }
/*
                if(isOkay)
                {
                    user.setStayLoggedIn(!(stayLoggedIn == null));
                    App.setCurrentUser(user);
                }
                */
            }

            if(view.getForgotPasswordButton().isChecked())
            {
                Main.getMain().setScreen(new ForgotPasswordGDXView(new ForgotPasswordGDXController(),GameAssetManager.getGameAssetManager().getSkin()));
            }
        }

    }

}
