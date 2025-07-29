package io.github.group18.Controller;

import com.google.gson.Gson;
import io.github.group18.Main;
import io.github.group18.Model.*;
import io.github.group18.Controller.LoginMenuController;
import io.github.group18.Network.Client.App.ClientModel;
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


                //TODO
                //delete this later
//                App.loadUsersFromFile();



                String username = view.getUsernameField().getText();
                String password = view.getPasswordField().getText();
                String stayLoggedIn = view.getStayLoggedInField().getText();

                //errors//
                boolean isOkay = true;
                //first:
                if (findUserByUsername(username) == null) {
                    view.setUsernameErrorLabel("incorrect username!");
                    isOkay = false;
                }

                //second:
                if (!(stayLoggedIn.toLowerCase().equals("yes") || stayLoggedIn.toLowerCase().equals("no"))) {
                    view.setStayLoggedInErrorLabel("You should enter 'YES' or 'NO'");
                    isOkay = false;
                }

                //third:
                User user = findUserByUsername(username);
                String hashedInput = RegisterMenuController.hashPasswordSHA256(password);
                if (user != null && !user.getPassword().equals(hashedInput) && user.getPassword() != null) {
                    view.setPasswordErrorLabel("Wrong Password!");
                    isOkay = false;
                }
                if (isOkay) {
                    boolean stayin = false;
                    if (stayLoggedIn.equalsIgnoreCase("yes")) {
                        stayin = true;
                    }
                    user.setStayLoggedIn(stayin);
                    if (user.isStayLoggedIn()) {
                        Gson gson = new Gson();
                        try (FileWriter writer = new FileWriter("loggedInUser.json")) {
                            gson.toJson(user, writer);
                            System.out.println("User info saved to loggedInUser.json");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    App.setCurrentUser(user);
                    HashMap<String,Object> body = new HashMap<>();
                    body.put("user", user);
                    Message message = new Message(body, Message.Type.add_to_online_players, Message.Menu.OnlinePlayers1);
                    System.out.println("1" + message.getBody().toString());
                    ClientModel.getServerConnectionThread().sendMessage(message);
                    System.out.println("2");
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new MainMenu(new MainMenuController(), GameAssetManager.getGameAssetManager().getSkin()));
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

}
