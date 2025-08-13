package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.Gdx;
import io.github.group18.Model.GameAssetManager;
import io.github.group18.Network.Client.App.ClientModel;

import java.util.function.Consumer;

public class TradeUI {

    private final Stage stage;
    private final Skin skin;

    public TradeUI(Stage stage) {
        this.stage = stage;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
    }

    public void showTradeOffer(String fromUser, String tradeType, String mode,
                               String item, int amount, int price,
                               String targetItem, int targetAmount,
                               Consumer<Boolean> callback) {

        Window window = new Window("Trade Offer", skin);
        window.setSize(700, 500);
        window.setPosition(Gdx.graphics.getWidth()/2f - 200, Gdx.graphics.getHeight()/2f - 150);

        StringBuilder message = new StringBuilder();
        message.append("Player ").append(fromUser).append(" sent you a trade offer:\n");

        if (tradeType.equalsIgnoreCase("money")) {
            message.append(mode).append(" ").append(amount).append(" x ").append(item)
                .append(" for ").append(price).append(" gold.\n");
        } else if (tradeType.equalsIgnoreCase("item")) {
            message.append(mode).append(" ").append(amount).append(" x ").append(item)
                .append(" for ").append(targetAmount).append(" x ").append(targetItem).append("\n");
        }

        Label label = new Label(message.toString(), skin);
        window.add(label).pad(10);
        window.row();

        TextButton acceptButton = new TextButton("Accept", skin);
        TextButton rejectButton = new TextButton("Reject", skin);

        acceptButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                callback.accept(true);
                ClientModel.setWindowOpen(false);
                window.remove();
            }
        });

        rejectButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                callback.accept(false);
                ClientModel.setWindowOpen(false);
                window.remove();
            }
        });

        Table buttons = new Table();
        buttons.add(acceptButton).pad(10);
        buttons.add(rejectButton).pad(10);

        window.add(buttons);

        stage.addActor(window);
    }
}
