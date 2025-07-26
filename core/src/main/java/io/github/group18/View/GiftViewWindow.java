package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Gift;

import java.util.List;

public class GiftViewWindow extends Window {
    private Game game;
    private Player currentPlayer;
    private Skin skin;

    public GiftViewWindow(Game game, Skin skin, Stage stage) {
        super("🎁 Your Gifts", skin);
        this.game = game;
        this.skin = skin;
        this.currentPlayer = game.getPlayers().get(game.getIndexPlayerinControl());
        this.setSize(1000, 800);
        this.setMovable(true);
        this.setPosition((stage.getWidth() - getWidth()) / 2f, (stage.getHeight() - getHeight()) / 2f);

        populateGifts();

        stage.addActor(this);
    }

    private void populateGifts() {
        this.clear();

        Table contentTable = new Table();
        contentTable.align(Align.topLeft);

        boolean hasGift = false;
        for (Gift gift : game.getGifts()) {
            if (gift.getReceiver().equals(currentPlayer)) {
                hasGift = true;

                Table giftRow = new Table();
                giftRow.align(Align.left);

                Label nameLabel = new Label("🎁 " + gift.getCorrectName() + " x" + gift.getAmount(), skin);
                Label fromLabel = new Label("From: " + gift.getSender().getOwner().getUsername(), skin);
                giftRow.add(nameLabel).pad(5);
                giftRow.row();
                giftRow.add(fromLabel).pad(5);

                if (!gift.isRated()) {
                    TextField rateTextField = new TextField("", skin);
                    rateTextField.setMessageText("Enter rate 1-5");

                    Label messageLabel = new Label("", skin);

                    TextButton rateButton = new TextButton("Rate Gift", skin);
                    rateButton.addListener(new ClickListener() {
                        @Override
                        public void clicked(InputEvent event, float x, float y) {
                            try {
                                int rate = Integer.parseInt(rateTextField.getText().trim());
                                if (rate < 1 || rate > 5) {
                                    messageLabel.setText("Rate must be between 1 and 5.");
                                    return;
                                }

                                if (gift.isRated()) {
                                    messageLabel.setText("Gift already rated.");
                                    return;
                                }

                                gift.setRated(true);
                                gift.setRate(rate);

                                Friendship friendship = GameMenuController.getFriendship(gift.getSender(), currentPlayer);
                                if (friendship != null) {
                                    friendship.interact(((rate - 3) * 30) + 15);
                                }

                                messageLabel.setText("Gift rated successfully.");
                                rateButton.setDisabled(true);
                            } catch (NumberFormatException e) {
                                messageLabel.setText("Invalid number.");
                            }
                        }
                    });

                    giftRow.row();
                    giftRow.add(rateTextField).pad(5);
                    giftRow.row();
                    giftRow.add(rateButton).pad(5);
                    giftRow.row();
                    giftRow.add(messageLabel).pad(5);
                } else {
                    Label ratedLabel = new Label("Rated: ⭐" + gift.getRate(), skin);
                    giftRow.row();
                    giftRow.add(ratedLabel).pad(5);
                }

                contentTable.add(giftRow).left().pad(10);
                contentTable.row();
            }
        }

        if (!hasGift) {
            Label noGiftLabel = new Label("You have no gifts to display.", skin);
            contentTable.add(noGiftLabel).pad(10);
            contentTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setFadeScrollBars(false);

        this.add(scrollPane).expand().fill();
        this.row();


        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });
        this.add(closeButton).center().pad(10);

        this.pack();
    }

}
