package io.github.group18.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.ProfileMenuController;
import io.github.group18.Main;
import io.github.group18.Model.App;
import io.github.group18.Model.GameAssetManager;
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
    private final TextButton randomPassword;
    private final TextButton applyPassword;

    private final Label oldPasswordLabel;
    private final TextField oldpasswordTextField;

    private final Label nicknameLabel;
    private final TextField nicknameTextField;
    private final TextButton applyNickname;

    private final Label emailLabel;
    private final TextField emailTextField;
    private final TextButton applyEmail;

    private final ImageButton avatarButton;
    private String currentAvatarPath;

    private final Label genderLabel;
    private final Label highestMoenyEarnedLabel;
    private final Label gamesPlayedLabel;
    private final TextButton backButton;
    public Table table;
    private final ProfileMenuController profileMenuController;

    public ProfileMenu(ProfileMenuController profileMenuController, Skin skin) {
//        App.setCurrentUser(new User("alisoltani", "aliSOLTANI123@", "ali", "alisoltani1810@gmail.com", "male"));
        this.profileMenuController = profileMenuController;

        this.usernameLabel = new Label("Username:", skin);
        this.usernameTextField = new TextField(App.getCurrentUser().getUsername(), skin);
        this.applyUsername = new TextButton("Apply", skin);

        this.passwordLabel = new Label("New Password:", skin);
        this.passwordTextField = new TextField("", skin);
        this.applyPassword = new TextButton("Apply", skin);
        this.randomPassword = new TextButton("Random Password",skin);

        this.oldPasswordLabel = new Label("Old Password:", skin);
        this.oldpasswordTextField = new TextField("", skin);

        this.nicknameLabel = new Label("Nickname:", skin);
        this.nicknameTextField = new TextField(App.getCurrentUser().getNickName(), skin);
        this.applyNickname = new TextButton("Apply", skin);

        this.emailLabel = new Label("Email:", skin);
        this.emailTextField = new TextField(App.getCurrentUser().getEmail(), skin);
        this.applyEmail = new TextButton("Apply", skin);

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        this.avatarButton = new ImageButton(style);
        updateAvatarButtonStyle(App.getCurrentUser().getAvatar());
        avatarButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                profileMenuController.handleAvatarChange();
            }
        });

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

        // Background (fills screen)
        Texture bgTexture = new Texture(Gdx.files.internal("MainMenuBackground.jpg"));
        Image background = new Image(bgTexture);
        background.setFillParent(true);
        stage.addActor(background);

        table.setFillParent(true);
        table.top(); // Start from the top (prevents overflow)

        // Dynamic scaling (now more compact)
        float labelWidth = 0.18f;   // 18% of screen width
        float fieldWidth = 0.35f;   // 35% of screen width
        float buttonWidth = 0.12f;  // 12% of screen width
        float minButtonWidth = 120; // Minimum button width

        int screenWidth = Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();
        float padSize = screenHeight * 0.005f; // Smaller padding (0.5% of height)

        // Ensure buttons aren't too small
        float actualButtonWidth = Math.max(screenWidth * buttonWidth, minButtonWidth);

        // Row 1: Username (smaller padding)
        table.add(usernameLabel).width(screenWidth * labelWidth).padBottom(padSize);
        table.add(usernameTextField).width(screenWidth * fieldWidth).padBottom(padSize);
        table.add(applyUsername).width(actualButtonWidth).padBottom(padSize);
        table.row();

        // Row 2: Old Password
        table.add(oldPasswordLabel).width(screenWidth * labelWidth).padBottom(padSize);
        table.add(oldpasswordTextField).width(screenWidth * fieldWidth).padBottom(padSize);
        table.row();

        // Row 3: New Password (adjusted for "Random Password")
        table.add(passwordLabel).width(screenWidth * labelWidth * 0.7f).padBottom(padSize).left();
        table.add(passwordTextField).width(screenWidth * fieldWidth * 0.8f).padBottom(padSize);
        table.add(applyPassword).width(actualButtonWidth).padBottom(padSize).right();
        table.add(randomPassword).width(actualButtonWidth * 1.3f).padBottom(padSize).right();
        table.row();

        // Row 4: Nickname
        table.add(nicknameLabel).width(screenWidth * labelWidth).padBottom(padSize);
        table.add(nicknameTextField).width(screenWidth * fieldWidth).padBottom(padSize);
        table.add(applyNickname).width(actualButtonWidth).padBottom(padSize);
        table.row();

        // Row 5: Email
        table.add(emailLabel).width(screenWidth * labelWidth).padBottom(padSize);
        table.add(emailTextField).width(screenWidth * fieldWidth).padBottom(padSize);
        table.add(applyEmail).width(actualButtonWidth).padBottom(padSize);
        table.row();

        // Avatar (smaller but still visible)
        float avatarSize = Math.min(screenHeight * 0.15f, 150); // Max 150px height
        table.add(avatarButton).center().size(avatarSize).padBottom(padSize * 2);
        table.row();

        // Info labels (less padding)
        table.add(genderLabel).colspan(4).center().padBottom(padSize);
        table.row();
        table.add(highestMoenyEarnedLabel).colspan(4).center().padBottom(padSize);
        table.row();
        table.add(gamesPlayedLabel).colspan(4).center().padBottom(padSize);
        table.row();

        // Back button (smaller top padding)
        table.add(backButton).colspan(4).center().width(actualButtonWidth * 1.5f).padTop(padSize * 2);

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

    public ImageButton getAvatarButton() {
        return avatarButton;
    }

    public TextButton getRandomPassword() {
        return randomPassword;
    }

    public void updateAvatarButtonStyle(String newAvatarPath) {
        Texture avatarTexture = GameAssetManager.getUserAvatar(newAvatarPath);
        if (avatarTexture == null) {
            Gdx.app.error("Avatar", "Texture is null for: " + newAvatarPath);
            return;
        }

        ImageButton.ImageButtonStyle style = new ImageButton.ImageButtonStyle();
        style.imageUp = new TextureRegionDrawable(new TextureRegion(avatarTexture));
        style.imageDown = new TextureRegionDrawable(new TextureRegion(avatarTexture)).tint(Color.GRAY);

        assert avatarButton != null;
        avatarButton.setStyle(style);
        currentAvatarPath = newAvatarPath;
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
