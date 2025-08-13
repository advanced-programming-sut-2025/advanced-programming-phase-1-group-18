package io.github.group18.Controller;

import com.google.gson.Gson;
import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Controller.LoginMenuController;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Client.App.LoginMessageHandler;
import io.github.group18.Network.Client.App.ServerConnectionThread;
import io.github.group18.Network.Client.Controller.C2SConnectionController;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.*;
import io.github.group18.enums.Menu;

import java.io.FileWriter;
import java.util.HashMap;

import static io.github.group18.Controller.LoginMenuController.findUserByUsername;

public class LoginGDXController {
    private LoginGDXView view;

    public void setView(LoginGDXView view) {
        this.view = view;
    }

    public void handleLoginGDXButtons() {

        if (view != null) {
            view.setPasswordErrorLabel("");
            view.setStayLoggedInErrorLabel("");
            view.setUsernameErrorLabel("");


            if (view.getGoBackButton().isChecked()) {
                Main.getMain().setScreen(new RegisterLoginGdxView(new RegisterLoginGdxController(), GameAssetManager.getGameAssetManager().getSkin()));
            }

            if (view.getVerifyButton().isChecked()) {
                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                String stayLoggedIn = view.getStayLoggedInField().getText();

                boolean isOkay = true;

                if (!(stayLoggedIn.equalsIgnoreCase("yes") || stayLoggedIn.equalsIgnoreCase("no"))) {
                    view.setStayLoggedInErrorLabel("You should enter 'YES' or 'NO'");
                    isOkay = false;
                }

                if(findUserByUsername(username) == null) {
                    view.setUsernameErrorLabel("Username not found");
                    isOkay = false;
                }
                if (isOkay) {
                    String hashedPassword = RegisterMenuController.hashPasswordSHA256(password);

                    Message res = LoginMessageHandler.login(username, hashedPassword);
//                    System.out.println("Raw login response: " + new Gson().toJson(res));

                    Boolean success = (Boolean) res.getFromBody("loginSuccess");
                    if (success != null && success) {
                        Gson gson = new Gson();

                        Object userObj = res.getFromBody("user");

                        String userJson = gson.toJson(userObj);
                        User user = gson.fromJson(userJson, User.class);


                        App.setCurrentUser(user);

                        if (stayLoggedIn.equalsIgnoreCase("yes")) {
                            user.setStayLoggedIn(true);
                            try (FileWriter writer = new FileWriter("loggedInUser.json")) {
                                gson.toJson(user, writer);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
                    } else {
                        // لاگین ناموفق: پیام خطا نمایش بده
                        view.setUsernameErrorLabel("Username or password is incorrect!");
                    }



//                    Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));

                    } else {
                        // لاگین ناموفق: پیام خطا نمایش بده
                        view.setUsernameErrorLabel("Username or password is incorrect!");
                    }
                }
            }




            if (view.getForgotPasswordButton().isChecked()) {
                Main.getMain().setScreen(new ForgotPasswordGDXView(new ForgotPasswordGDXController(), GameAssetManager.getGameAssetManager().getSkin()));
            }


            if(view.getGoToRegisterMenuButton().isChecked()) {
                Main.getMain().setScreen(new RegisterGDXView(new RegisterGDXController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }

    }


