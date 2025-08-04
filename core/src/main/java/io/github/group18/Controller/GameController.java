package io.github.group18.Controller;

import io.github.group18.Main;
import io.github.group18.Model.ForagingTree;
import io.github.group18.Model.Game;
import io.github.group18.Model.Items.*;
import io.github.group18.Model.Kashi;
import io.github.group18.Model.Result;
import io.github.group18.View.GameMenu;

import java.awt.*;
import java.util.ArrayList;

public class GameController {
    private boolean escapePressed = false;
    private final Main main;
    private GameMenu gameMenu;

    public GameController(Main main) {
        this.main = main;
    }

    public void init(Game gameModel) {
        gameMenu = new GameMenu(this, gameModel);
    }

    public void run() {
        main.setScreen(gameMenu);
    }

    public GameMenu getGameMenu() {
        return gameMenu;
    }

    public void useItem(Item selectedItem, int x, int y, Game game) {

        Kashi kashi = game.getMap().get(x).get(y);
        //Use Tools
        if (selectedItem instanceof Tool tool) {
            if (tool instanceof Axe axe) {
                Result result = axe.use(kashi);
                if (!result.isSuccessful()) {
                    System.out.println(result.getMessage());
                }
            } else if (tool instanceof Hoe hoe) {
                Result result = hoe.use(kashi);
                if (!result.isSuccessful()) {
                    System.out.println(result.getMessage());
                }
            } else if (tool instanceof MilkPail milkPail) {
                String result = milkPail.use(kashi);
                System.out.println(result);
            } else if (tool instanceof Pickaxe pickaxe) {
                Result result = pickaxe.use(kashi);
                if (!result.isSuccessful()) {
                    System.out.println(result.getMessage());
                }
            } else if (tool instanceof Scythe scythe) {
                ArrayList<Kashi> map = new ArrayList<>();
                map.add(game.getMap().get(x - 1).get(y - 1));
                map.add(game.getMap().get(x - 1).get(y));
                map.add(game.getMap().get(x - 1).get(y + 1));
                map.add(game.getMap().get(x).get(y - 1));
                map.add(game.getMap().get(x).get(y));
                map.add(game.getMap().get(x).get(y + 1));
                map.add(game.getMap().get(x + 1).get(y - 1));
                map.add(game.getMap().get(x + 1).get(y));
                map.add(game.getMap().get(x + 1).get(y + 1));
                String result = scythe.use(map, kashi);
                System.out.println(result);
            } else if (tool instanceof Shear shear) {
                String result = shear.use(kashi);
                System.out.println(result);
            } else if (tool instanceof TrashCan) {
                //WTF
            } else if (tool instanceof FishingPole fishingPole) {
                String result = fishingPole.use();
                System.out.println(result);
            } else if (tool instanceof WateringCan wateringCan) {
                String result = wateringCan.use(kashi, x, y);
                System.out.println(result);
            }
        }
        //Plant Seeds
        if (selectedItem instanceof ForagingSeed foragingSeed) {
            GameMenuController.plant(foragingSeed, kashi);
        }
        if (selectedItem instanceof TreeSeed treeSeed) {
            GameMenuController.plant(treeSeed, kashi);
        }
        if (selectedItem instanceof MixedSeed mixedSeed) {
            GameMenuController.plant(mixedSeed, kashi);
        }
        //Fertilizer
        if (selectedItem instanceof Fertilizer fertilizer) {
            GameMenuController.fertilize(fertilizer, kashi);
        }
        gameMenu.getGameView().loadTextures();
    }
}
