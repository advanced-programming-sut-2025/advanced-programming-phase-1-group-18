package io.github.group18.enums;

import java.util.Scanner;


import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Controller.MainMenuController;
import io.github.group18.Controller.ProfileMenuController;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Model.GameAssetMannager;
import io.github.group18.View.*;

public enum Menu {
    AvatarMenu(new AvatarMenu()),
    GameMenu(new GameMenu(new GameMenuController(), GameAssetManager.getGameAssetManager().getSkin())),
    LoginMenu(new LoginMenu()),
    MainMenu(new MainMenu(new MainMenuController() , GameAssetMannager.getGameAssetMannager().getSkin())),
    ProfileMenu(new ProfileMenu(new ProfileMenuController() , GameAssetMannager.getGameAssetMannager().getSkin())),
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
