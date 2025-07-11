package io.githubgroup18.Controller;

import io.githubgroup18.Model.App;

public interface ShowCurrentMenu {
    default void showCurrentMenu()
    {
        System.out.println(App.getCurrentMenu());
    }
}
