package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.View.ChoosingMapView;
import io.github.group18.View.GameMenuMenu;
import io.github.group18.View.StartNewGame;

import java.util.ArrayList;

public class StartNewGameController {
    private StartNewGame view;
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<Integer> maps = new ArrayList<>();
    public void setView(StartNewGame view) {
        this.view = view;
    }

    public void handleStartNewGame() {
        if (view != null) {
            if (view.getAddPlayerField().isTouchFocusListener()){
                view.getAddPlayerButton().setVisible(true);
                view.getChooseMapButton().setVisible(true);
            }else if (view.getChooseMapButton().isPressed()){
                if(users.size()>0){
                    users.add(App.getCurrentUser().getUsername());
                    ChoosingMapController controller = new ChoosingMapController();
                    controller.setGameMenuController(view.getMenuController());
                    controller.setUsers(users);
                    Main.getMain().getScreen().dispose();
                    Main.getMain().setScreen(new ChoosingMapView(controller,view.getSkin()));
                }else {
                    view.getNotif().setText("You have to choose at least one player");
                }
//                try {
//                    Result result = view.getMenuController().gameNew(users,maps);
//                    if (!result.isSuccessful()){
//                        view.getNotif().setText(result.getMessage());
//                    }else {
//                        System.exit(0);
//                    }
//                }catch (Exception e){
//                    view.getNotif().setText(e.getMessage());
//                }
            }else if (view.getAddPlayerButton().isPressed() && view.getAddPlayerButton().isVisible()){
                String playerName = view.getAddPlayerField().getText();
                if(!playerName.equals("")){
                    if(App.getCurrentUser().getUsername().equals(playerName)){
                        view.getNotif().setText("You can't add yourself again");
                    }else if (LoginMenuController.findUserByUsername(view.getAddPlayerField().getText()) != null){
                        if (users.contains(playerName)){
                            view.getNotif().setText("You can't add player more than once");
                        }else if(users.size()<3){
                            view.getNotif().setText("PLayer " + playerName+" added");
                            view.getAddPlayerButton().setVisible(false);
                            users.add(playerName);
                        }else {
                            view.getNotif().setText("Can't add more player");
                        }
                    }else{
                        view.getNotif().setText("PLayer " + playerName+" not found");
                    }
                }else {
                    view.getNotif().setVisible(true);
                    view.getNotif().setText("Please enter your name");
                }

            }else if (view.getBackButton().isPressed()){
                users = new ArrayList<>();
                maps = new ArrayList<>();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenuMenu(new GameMenuController(),view.getSkin()));
            }
        }
    }
}
