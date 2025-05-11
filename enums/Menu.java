package enums;

import java.util.Scanner;

import Model.App;
import Model.PierresGeneralStoreMarket;
import View.*;

public enum Menu {
    AvatarMenu(new AvatarMenu()),
    GameMenu(new GameMenu()),
    LoginMenu(new LoginMenu()),
    MainMenu(new MainMenu()),
    ProfileMenu(new ProfileMenu()),
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