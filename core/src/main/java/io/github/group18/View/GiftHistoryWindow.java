package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.*;
import io.github.group18.Model.*;
import io.github.group18.Model.Items.Gift;
import com.badlogic.gdx.utils.Align;

public class GiftHistoryWindow extends Window {
    private Game game;
    private Player currentPlayer;
    private Skin skin;

    public GiftHistoryWindow(Game game, Skin skin) {
        super("📜 Gift History", skin);
        this.game = game;
        this.skin = skin;
        //Server-TODO
//        this.currentPlayer = game.getPlayers().get(game.getIndexPlayerinControl());

        setSize(1000, 800);
        setPosition(500, 180);
        setMovable(true);


        Label historyLabel = new Label("", skin);
        historyLabel.setWrap(true);
        historyLabel.setAlignment(Align.topLeft);


        ScrollPane scrollPane = new ScrollPane(historyLabel, skin);
        scrollPane.setFadeScrollBars(false);


        StringBuilder historyText = new StringBuilder();

        historyText.append("Gift History for ").append(currentPlayer.getOwner().getUsername()).append(":\n\n");

        historyText.append("As sender:\n");
        boolean sentAny = false;
        for (Gift gift : game.getGifts()) {
            if (gift.getSender().equals(currentPlayer)) {
                sentAny = true;
                historyText.append("\tGift id      : ").append(gift.getId()).append("\n");
                historyText.append("\tGift name    : ").append(gift.getName()).append("\n");
                historyText.append("\tGift amount  : ").append(gift.getAmount()).append("\n");
                historyText.append("\tGift is rated: ").append(gift.isRated()).append("\n");
                historyText.append("\tGift receiver: ").append(gift.getReceiver().getOwner().getUsername()).append("\n----\n");
            }
        }
        if (!sentAny) {
            historyText.append("\tNo gifts sent.\n");
        }

        historyText.append("\nAs receiver:\n");
        boolean receivedAny = false;
        for (Gift gift : game.getGifts()) {
            if (gift.getReceiver().equals(currentPlayer)) {
                receivedAny = true;
                historyText.append("\tGift id      : ").append(gift.getId()).append("\n");
                historyText.append("\tGift name    : ").append(gift.getName()).append("\n");
                historyText.append("\tGift amount  : ").append(gift.getAmount()).append("\n");
                historyText.append("\tGift is rated: ").append(gift.isRated()).append("\n");
                historyText.append("\tGift sender  : ").append(gift.getSender().getOwner().getUsername()).append("\n----\n");
            }
        }
        if (!receivedAny) {
            historyText.append("\tNo gifts received.\n");
        }

        historyLabel.setText(historyText.toString());


        add(scrollPane).width(580).height(300);
        row();


        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                remove();
            }
        });
        add(closeButton).padTop(10);


    }
}
