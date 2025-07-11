package io.githubgroup18.enums;

import java.util.Scanner;

import io.githubgroup18.Model.App;
import io.githubgroup18.Model.PierresGeneralStoreMarket;
import io.githubgroup18.View.*;

public enum Menu {
    AvatarMenu(new AvatarMenu()),
    GameMenu(new GameMenu()),
    LoginMenu(new LoginMenu()),
    //MainMenu(new MainMenu()),
    //ProfileMenu(new ProfileMenu()),
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
