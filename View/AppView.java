package View;
import Model.*;
import java.util.Scanner;

import enums.Menu;

public class AppView {
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        do {
            App.getCurrentMenu().checkCommand(scanner);
        } while (App.getCurrentMenu() != Menu.Exit);
    }
}
