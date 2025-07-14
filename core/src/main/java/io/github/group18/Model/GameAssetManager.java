package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/pixthulhu-ui.json"));
    public static Texture menuBackground = new Texture(Gdx.files.internal("menu_bg.png"));
    public static Texture defaultAvatar = new Texture(Gdx.files.internal("avatar.png"));

    private GameAssetManager() {}

    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) {
            gameAssetManager = new GameAssetManager();
        }
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public static Texture getBackground() {
        return menuBackground;
    }

    public static Texture getDefaultAvatar() {
        return defaultAvatar;
    }

    public static Texture getUserAvatar(String avatarPath) {
        try {
            // Handle both internal (assets/) and external (local) paths
            FileHandle file = avatarPath.startsWith("avatars/")
                ? Gdx.files.local(avatarPath)
                : Gdx.files.internal(avatarPath);

            if (file.exists()) {
                return new Texture(file);
            }
            Gdx.app.error("Avatar", "File not found: " + avatarPath);
        } catch (Exception e) {
            Gdx.app.error("Avatar", "Error loading: " + avatarPath, e);
        }
        return getDefaultAvatar(); // Fallback
    }
}
