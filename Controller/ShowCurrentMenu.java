package io.github.group18.Controller;

import io.github.group18.Model.App;

public interface ShowCurrentMenu {
    default void showCurrentMenu()
    {
        System.out.println(App.getCurrentMenu());
    }
}
