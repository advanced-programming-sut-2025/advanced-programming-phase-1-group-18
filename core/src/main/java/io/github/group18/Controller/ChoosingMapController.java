package io.github.group18.Controller;

import com.badlogic.gdx.graphics.Texture;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.Result;
import io.github.group18.View.ChoosingMapView;
import io.github.group18.View.StartNewGame;

import java.util.ArrayList;

public class ChoosingMapController {
    private ChoosingMapView view;
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<Integer> maps = new ArrayList<>();
    private int map1=0;
    private int map2=0;
    private int map3=0;
    private int map4=0;
    private GameMenuController gameMenuController;

    public void setGameMenuController(GameMenuController gameMenuController) {
        this.gameMenuController = gameMenuController;
    }
    public void setView(ChoosingMapView view) {
        this.view = view;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
    public void setMaps(ArrayList<Integer> maps) {
        this.maps = maps;
    }

    public void arrangeVisibily(){
        if(users.size()<3){
            view.getMap3().dispose();
            view.getChooseMapBox3().setVisible(false);
            view.getSelectMapButton3().setVisible(false);
        }
        if(users.size()<4) {
            view.getMap4().dispose();
            view.getChooseMapBox4().setVisible(false);
            view.getSelectMapButton4().setVisible(false);
        }

    }

    public void handleStartNewGame() {
        if (view != null) {
            if (view.getSelectMapButton1().isPressed()) {
                map1 = view.getChooseMapBox1().getSelected();
                view.getNotif().setText("user 1 select map :" + map1);
                view.setMap1(getMap(map1));
                view.getPlayButton().setVisible(true);
            }else if (view.getSelectMapButton2().isPressed()) {
                map2 = view.getChooseMapBox2().getSelected();
                view.getNotif().setText("user 2 select map :" + map2);
                view.setMap2(getMap(map2));
                view.getPlayButton().setVisible(true);
            }else if (view.getSelectMapButton3().isPressed() && view.getSelectMapButton3().isVisible()) {
                map3 = view.getChooseMapBox3().getSelected();
                view.getNotif().setText("user 3 select map :" + map3);
                view.setMap3(getMap(map3));
                view.getPlayButton().setVisible(true);
            }else if (view.getSelectMapButton4().isPressed() && view.getSelectMapButton4().isVisible()) {
                map4 = view.getChooseMapBox4().getSelected();
                view.getNotif().setText("user 4 select map :" + map4);
                view.setMap4(getMap(map4));
                view.getPlayButton().setVisible(true);
            }else if (view.getPlayButton().isPressed()&& view.getPlayButton().isVisible()) {
                view.getPlayButton().setVisible(false);
                if(map1 != 0){
                    maps.add(map1);
                    if (map2!=0){
                        maps.add(map2);
                        if (map3!=0){
                            maps.add(map3);
                            if (map4!=0){
                                maps.add(map4);
                            }
                        }
                    }
                }
                if (maps.size()==users.size()) {
                    try {
                        ArrayList<String>adaptedUsers = new ArrayList<>();
                        for (int i = 0; i < users.size()-1; i++) {adaptedUsers.add(users.get(i));}
                        Result result = gameMenuController.gameNew(adaptedUsers,maps);
                        if (!result.isSuccessful()){
                            view.getNotif().setText(result.getMessage());
                        }else {
                            App.setGameMenuController(gameMenuController);
                            GameController gameController = new GameController(Main.getMain());
                            App.setGameController(gameController);
                            gameController.init(App.getCurrentGame());
                            gameController.run();
//                            mainMenu.hide();

//                            GameView gameView = new GameView(new GameController(),view.getSkin());
//                            gameView.setMenuController(gameMenuController);
//                            Main.getMain().getScreen().dispose();
//                            Main.getMain().setScreen(gameView);
                        }
                    }catch (Exception e){
                        view.getNotif().setText(e.getMessage());
                    }
                }else {
                    view.getNotif().setText("Choose maps complete");
                }
            }else if (view.getBackButton().isPressed()){
                users = new ArrayList<>();
                maps = new ArrayList<>();
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new StartNewGame(new StartNewGameController(),view.getSkin(),
                    new GameMenuController()));
            }

        }
    }

    public Texture getMap (int mapNum){
        return switch (mapNum) {
            case 1 -> GameAssetManager.getGameAssetManager().getMap1Texture();
            case 2 -> GameAssetManager.getGameAssetManager().getMap2Texture();
            case 3 -> GameAssetManager.getGameAssetManager().getMap3Texture();
            case 4 -> GameAssetManager.getGameAssetManager().getMap4Texture();
            default -> null;
        };
    }
}
