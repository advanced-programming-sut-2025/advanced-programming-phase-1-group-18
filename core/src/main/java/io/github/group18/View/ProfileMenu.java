package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.ProfileMenuController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.User;
import io.github.group18.enums.ProfileMenuCommands;

import java.util.Scanner;
public class ProfileMenu extends AppMenu implements Screen {
    private final ProfileMenuController controller = new ProfileMenuController();

    private Stage stage;

    private final Label usernameLabel;
    private final TextField usernameTextField;
    private final TextButton applyUsername;

    private final Label passwordLabel;
    private final TextField passwordTextField;
    private final TextButton applyPassword;

    private final Label oldPasswordLabel;
    private final TextField oldpasswordTextField;

    private final Label nicknameLabel;
    private final TextField nicknameTextField;
    private final TextButton applyNickname;

    private final Label emailLabel;
    private final TextField emailTextField;
    private final TextButton applyEmail;

    private final Label genderLabel;
    private final Label highestMoenyEarnedLabel;
    private final Label gamesPlayedLabel;
    private final TextButton backButton;
    public Table table;
    private final ProfileMenuController profileMenuController;

    public ProfileMenu(ProfileMenuController profileMenuController, Skin skin) {
        App.setCurrentUser(new User("alisoltani", "aliSOLTANI123@", "ali", "alisoltani1810@gmail.com", "male"));
        this.profileMenuController = profileMenuController;

        this.usernameLabel = new Label("Username:", skin);
        this.usernameTextField = new TextField(App.getCurrentUser().getUsername(), skin);
        this.applyUsername = new TextButton("Apply", skin);

        this.passwordLabel = new Label("New Password:", skin);
        this.passwordTextField = new TextField("", skin);
        this.applyPassword = new TextButton("Apply", skin);

        this.oldPasswordLabel = new Label("Old Password:", skin);
        this.oldpasswordTextField = new TextField("", skin);

        this.nicknameLabel = new Label("Nickname:", skin);
        this.nicknameTextField = new TextField(App.getCurrentUser().getNickName(), skin);
        this.applyNickname = new TextButton("Apply", skin);

        this.emailLabel = new Label("Email:", skin);
        this.emailTextField = new TextField(App.getCurrentUser().getEmail(), skin);
        this.applyEmail = new TextButton("Apply", skin);

        this.genderLabel = new Label("Gender: " + App.getCurrentUser().getGender(), skin);
        this.highestMoenyEarnedLabel = new Label("Highest Moeny Earned: " + App.getCurrentUser().getHighestGold(), skin);
        this.gamesPlayedLabel = new Label("Games Played: " + App.getCurrentUser().getTimesPlayed(), skin);
        this.backButton = new TextButton("Back", skin);
        this.table = new Table();

        profileMenuController.setView(this);

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture bgTexture = new Texture(Gdx.files.internal("MainMenuBackground.jpg"));
        Image background = new Image(bgTexture);
        stage.addActor(background);

        table.setFillParent(true);
        table.center();

        table.add(usernameLabel).width((float) Main.getScreenWidth() / 5);
        table.add(usernameTextField).width((float) Main.getScreenWidth() / 2);
        table.add(applyUsername).width((float) Main.getScreenWidth() / 6);
        table.row();

        table.add(oldPasswordLabel).width((float) Main.getScreenWidth() / 5);
        table.add(oldpasswordTextField).width((float) Main.getScreenWidth() / 2);
        table.row();

        table.add(passwordLabel).width((float) Main.getScreenWidth() / 5);
        table.add(passwordTextField).width((float) Main.getScreenWidth() / 2);
        table.add(applyPassword).width((float) Main.getScreenWidth() / 6);
        table.row();

        table.add(nicknameLabel).width((float) Main.getScreenWidth() / 5);
        table.add(nicknameTextField).width((float) Main.getScreenWidth() / 2);
        table.add(applyNickname).width((float) Main.getScreenWidth() / 6);
        table.row();

        table.add(emailLabel).width((float) Main.getScreenWidth() / 5);
        table.add(emailTextField).width((float) Main.getScreenWidth() / 2);
        table.add(applyEmail).width((float) Main.getScreenWidth() / 6);
        table.row();

        table.add(genderLabel).colspan(3).center();
        table.row();
        table.add(highestMoenyEarnedLabel).colspan(3).center();
        table.row();
        table.add(gamesPlayedLabel).colspan(3).center();
        table.row();

        table.add(backButton).colspan(3).center().padTop(20);

        stage.addActor(table);
    }


    @Override
    public void render(float v) {
        ScreenUtils.clear(0, 0, 0, 1);
        Main.getBatch().begin();
        Main.getBatch().end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        if (stage != null) stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TextField getUsernameTextField() {
        return usernameTextField;
    }

    public TextField getPasswordTextField() {
        return passwordTextField;
    }

    public TextField getNicknameTextField() {
        return nicknameTextField;
    }

    public TextField getEmailTextField() {
        return emailTextField;
    }

    public Label getGenderLabel() {
        return genderLabel;
    }

    public Label getHighestMoenyEarnedLabel() {
        return highestMoenyEarnedLabel;
    }

    public Label getGamesPlayedLabel() {
        return gamesPlayedLabel;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public ProfileMenuController getProfileMenuController() {
        return profileMenuController;
    }

    public TextButton getBackButton() {
        return backButton;
    }

    public TextField getOldpasswordTextField() {
        return oldpasswordTextField;
    }

    public Label getUsernameLabel() {
        return usernameLabel;
    }

    public TextButton getApplyUsername() {
        return applyUsername;
    }

    public Label getPasswordLabel() {
        return passwordLabel;
    }

    public TextButton getApplyPassword() {
        return applyPassword;
    }

    public Label getOldPasswordLabel() {
        return oldPasswordLabel;
    }

    public Label getNicknameLabel() {
        return nicknameLabel;
    }

    public TextButton getApplyNickname() {
        return applyNickname;
    }

    public Label getEmailLabel() {
        return emailLabel;
    }

    public TextButton getApplyEmail() {
        return applyEmail;
    }

    @Override
    public void check(Scanner scanner) {
        String input = scanner.nextLine();
        if (ProfileMenuCommands.MenuEnter.getMather(input) != null) {
            String menuName = ProfileMenuCommands.MenuEnter.getMather(input).group(1);
            controller.menuEnter(menuName);
        } else if (ProfileMenuCommands.ShowCurrentMenu.getMather(input) != null) {
            controller.showCurrentMenu();
        } else if (ProfileMenuCommands.ChangeUsername.getMather(input) != null) {
            System.out.println(controller.changeUsername(ProfileMenuCommands.ChangeUsername.getMather(input).group("username")));
        } else if (ProfileMenuCommands.ChangePassword.getMather(input) != null) {
            String newPassword = ProfileMenuCommands.ChangePassword.getMather(input).group(1);
            String oldPassword = ProfileMenuCommands.ChangePassword.getMather(input).group(2);
            System.out.println(controller.changePassword(newPassword, oldPassword));
        } else if (ProfileMenuCommands.ChangeNickname.getMather(input) != null) {
            String nickname = ProfileMenuCommands.ChangeNickname.getMather(input).group("nickname");
            System.out.println(controller.changeNickname(nickname));
        } else if (ProfileMenuCommands.ChangeEmail.getMather(input) != null) {
            String email = ProfileMenuCommands.ChangeEmail.getMather(input).group("email");
            System.out.println(controller.changeEmail(email));
        } else if (ProfileMenuCommands.ShowInfo.getMather(input) != null) {
            controller.userInfo();
        } else {
            System.out.println("invalid command");
        }
    }
}
