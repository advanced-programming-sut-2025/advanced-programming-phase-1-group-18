package View;

import Controller.GameMenuController;
import enums.GameMenuCommands;

import java.util.Scanner;

public class GameMenu extends AppMenu {
    private final GameMenuController controller = new GameMenuController();

    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (GameMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else {
            if (GameMenuCommands.GameNew.getMather(input) != null) {
                System.out.println(controller.gameNew(input,scanner));
            } else {
                if (GameMenuCommands.ExitGame.getMather(input) != null) {
                    controller.exitGame();
                } else {
                    if (GameMenuCommands.VoteTerminateGame.getMather(input) != null) {
                        System.out.println(controller.voteTerminateGame(scanner));
                    }
                    else
                    {
                        if(GameMenuCommands.NextTurn.getMather(input) != null) {
                            controller.nextTurn();
                        }
                    }
                }
            }
        }
    }


}
