package io.github.group18.View;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;

import java.util.ArrayList;

import io.github.group18.Model.Friendship;
import io.github.group18.Model.Player;

public class FriendsWindow extends Window {

    public FriendsWindow(Skin skin, Player currentPlayer, ArrayList<Player> friends) {
        super("Friends of " + currentPlayer.getOwner().getUsername(), skin);
        setName("friendsWindow");
        setSize(300, 300);

        setPosition(
            (Gdx.graphics.getWidth() - getWidth()) / 2f,
            (Gdx.graphics.getHeight() - getHeight()) / 2f
        );

        setMovable(true);
        setResizable(false);
        pad(10);


        for (Player friend : friends) {
            Friendship friendship = Friendship.getFriendshipBetween(currentPlayer, friend);
            int level = (friendship != null) ? friendship.getLevel() : 0;
            Label nameLabel = new Label("- " + friend.getOwner().getUsername() + " | Level: " + level, skin);
            add(nameLabel).left().padBottom(5).row();
        }


        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });

        add(closeButton).padTop(10).colspan(1).center().row();
    }
}
