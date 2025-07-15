package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.Game;
import io.github.group18.View.GameMenu;

public class GameController {
    private boolean escapePressed = false;
    private final Main main;
    private GameMenu gameMenu;

    public GameController(Main main) {
        this.main = main;
    }

    public void init(Game gameModel) {
        gameMenu = new GameMenu(this,gameModel);
    }

    public void run() {
        main.setScreen(gameMenu);
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }
//    public void useItem(ItemDescriptionId selectedItem, Point point, GameModel game) {
//        TileDescriptionId selectedTile = game.getTile(point);
//        if (!selectedItem.getAllowedTiles().contains(selectedTile)) {
//            return;
//        }
//
//        game.getPlayer().useSelectedItem();
//        selectedItem.getFunction().invoke(game, point);
//    }
//
//    public void advanceToNextDay() {
//        gameMenu.gameModel.advanceToNextDay();
//        gameMenu.startSleepTransition();
//    }
}
