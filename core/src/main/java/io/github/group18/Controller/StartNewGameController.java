package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.View.GameMenu;
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
                view.getPlayButton().setVisible(true);
            }else if (view.getPlayButton().isPressed()){

            }else if (view.getAddPlayerButton().isPressed() && view.getAddPlayerButton().isVisible()){
                String playerName = view.getAddPlayerField().getText();
                if(!playerName.equals("")){
                    view.getNotif().setText("PLayer " + playerName+" added with map: " +
                        view.getChooseMapBox().getSelected());
                    view.getAddPlayerButton().setVisible(false);
                    users.add(playerName);
                    maps.add(view.getChooseMapBox().getSelected());
                }else {
                    view.getNotif().setVisible(true);
                    view.getNotif().setText("Please enter your name");
                }

            }else if (view.getBackButton().isPressed()){
                users = new ArrayList<>();
                maps = new ArrayList<>();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new GameMenu(new GameMenuController(),view.getSkin()));
            }
        }
    }
}
