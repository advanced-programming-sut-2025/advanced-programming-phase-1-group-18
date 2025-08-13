package io.github.group18.View;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.group18.Model.Player;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Client.App.ServerConnectionThread;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;

public class TradeWindow extends Window {

    private final ServerConnectionThread serverConnectionThread;
    private final Player currentPlayer;

    public TradeWindow(Skin skin, Stage stage, Player currentPlayer, ServerConnectionThread serverConnectionThread) {
        super("Trade Window", skin);
        this.serverConnectionThread = serverConnectionThread;
        this.currentPlayer = currentPlayer;

        setSize(900, 950);
        setMovable(true);
        pad(20);
        setPosition((stage.getWidth() - getWidth()) / 2f, (stage.getHeight() - getHeight()) / 2f);


        Table contentTable = new Table();
        contentTable.defaults().pad(10).left();

        Label targetUserLabel = new Label("Target Username:", skin);
        TextField targetUserField = new TextField("", skin);

        Label tradeTypeLabel = new Label("Trade Type:", skin);
        SelectBox<String> tradeTypeSelect = new SelectBox<>(skin);
        tradeTypeSelect.setItems("money", "item");

        Label modeLabel = new Label("Mode:", skin);
        SelectBox<String> modeSelect = new SelectBox<>(skin);
        modeSelect.setItems("offer", "request");

        Label itemLabel = new Label("Item Name:", skin);
        TextField itemField = new TextField("", skin);

        Label amountLabel = new Label("Amount:", skin);
        TextField amountField = new TextField("", skin);

        Label targetItemLabel = new Label("Target Item Name (only for item trades):", skin);
        TextField targetItemField = new TextField("", skin);

        Label targetAmountLabel = new Label("Target Item Amount (only for item trades):", skin);
        TextField targetAmountField = new TextField("", skin);

        Label priceLabel = new Label("Price (only for money trades):", skin);
        TextField priceField = new TextField("", skin);

        Label messageLabel = new Label("", skin);


        contentTable.add(targetUserLabel);
        contentTable.add(targetUserField).width(250);
        contentTable.row();

        contentTable.add(tradeTypeLabel);
        contentTable.add(tradeTypeSelect).width(150);
        contentTable.row();

        contentTable.add(modeLabel);
        contentTable.add(modeSelect).width(150);
        contentTable.row();

        contentTable.add(itemLabel);
        contentTable.add(itemField).width(250);
        contentTable.row();

        contentTable.add(amountLabel);
        contentTable.add(amountField).width(100);
        contentTable.row();

        contentTable.add(targetItemLabel);
        contentTable.add(targetItemField).width(250);
        contentTable.row();

        contentTable.add(targetAmountLabel);
        contentTable.add(targetAmountField).width(100);
        contentTable.row();

        contentTable.add(priceLabel);
        contentTable.add(priceField).width(100);
        contentTable.row();


        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);


        add(scrollPane).width(getWidth() - 40).height(getHeight() - 150).padBottom(10).row();

        TextButton sendButton = new TextButton("Send Trade Request", skin);
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String targetUsername = targetUserField.getText().trim();
                String tradeType = tradeTypeSelect.getSelected();
                String mode = modeSelect.getSelected();
                String item = itemField.getText().trim();

                int amount;
                try {
                    amount = Integer.parseInt(amountField.getText().trim());
                } catch (NumberFormatException e) {
                    messageLabel.setText("Invalid amount!");
                    return;
                }

                int price = 0;
                if (tradeType.equals("money")) {
                    try {
                        price = Integer.parseInt(priceField.getText().trim());
                    } catch (NumberFormatException e) {
                        messageLabel.setText("Invalid price!");
                        return;
                    }
                }

                String targetItem = targetItemField.getText().trim();
                int targetAmount = 0;
                if (tradeType.equals("item") && !targetItem.isEmpty()) {
                    try {
                        targetAmount = Integer.parseInt(targetAmountField.getText().trim());
                    } catch (NumberFormatException e) {
                        messageLabel.setText("Invalid target item amount!");
                        return;
                    }
                }

                if (targetUsername.isEmpty()) {
                    messageLabel.setText("Target username is required!");
                    return;
                }

                HashMap<String, Object> body = new HashMap<>();
                body.put("targetUsername", targetUsername);
                body.put("tradeType", tradeType);
                body.put("mode", mode);
                body.put("item", item);
                body.put("amount", amount);

                if (tradeType.equals("money")) {
                    body.put("price", price);
                }

                if (tradeType.equals("item")) {
                    body.put("targetItem", targetItem);
                    body.put("targetAmount", targetAmount);
                }

                Message tradeRequest = new Message(body, Message.Type.trade_request, Message.Menu.trade);

                boolean sent = false;
                try {
                    serverConnectionThread.sendMessage(tradeRequest);
                    System.out.println("trade sent");
                    sent = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    sent = false;
                }

                if (sent) {
                    messageLabel.setText("Trade request sent!");
                } else {
                    messageLabel.setText("Failed to send trade request.");
                }
            }
        });

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ClientModel.setWindowOpen(false);
                remove();
            }
        });

        Table buttonsTable = new Table();
        buttonsTable.add(sendButton).padRight(20);
        buttonsTable.add(closeButton);

        add(buttonsTable).padTop(10);
    }
}
