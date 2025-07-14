package io.github.group18.enums;

import java.util.Scanner;


import io.github.group18.Controller.GameMenuController;
import io.github.group18.Controller.MainMenuController;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.View.*;

public enum Menu {
    AvatarMenu(new AvatarMenu()),
    GameMenu(new GameMenuMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin())),
    LoginMenu(new LoginMenu()),
    MainMenu(new MainMenu(new MainMenuController() , GameAssetManager.getGameAssetManager().getSkin())),
//    ProfileMenu(new ProfileMenu(new ProfileMenuController() , GameAssetMannager.getGameAssetMannager().getSkin())),
    RegisterMenu(new RegisterMenu()),
    BlacksmithMenu(new BlackSmithMenu()),
    CarpentersshopMenu(new CarpentersShopMenu()),
    FishshopMenu(new FishShopMenu()),
    JojamartMenu(new JojaMartMenu()),
    MarniesranchMenu(new MarniesRanchMenu()),
    PirresgeneralstoreMenu(new PirresGeneralStoreMenu()),
    ThestardropsaloonMenu(new TheStardropSaloonMenu()),
    Exit(new ExitMenu());

    private final AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }

    public void checkCommand(Scanner scanner) {
        this.menu.check(scanner);
    }

}
