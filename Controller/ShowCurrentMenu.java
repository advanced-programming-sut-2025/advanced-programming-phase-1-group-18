package Controller;

import Model.App;

public interface ShowCurrentMenu {
    default void showCurrentMenu()
    {
        System.out.println(App.getCurrentMenu());
    }
}
