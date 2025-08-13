package io.github.group18.View;


import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.Client.App.ServerConnectionThread;
import io.github.group18.Network.common.models.Message;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class TradeHistoryWindow extends Window {

    private final Table historyTable;
    private final Skin skin;

    public TradeHistoryWindow(Skin skin, Stage stage, List<Map<String, Object>> tradeHistory) {
        super("Trade History", skin);
        this.skin = skin;

        setSize(900, 950);
        setMovable(true);
        pad(20);
        setPosition((stage.getWidth() - getWidth()) / 2f, (stage.getHeight() - getHeight()) / 2f);

        historyTable = new Table();
        historyTable.defaults().pad(5).left();

        // header row
        historyTable.add(new Label("From", skin)).width(120);
        historyTable.add(new Label("To", skin)).width(120);
        historyTable.add(new Label("Status", skin)).width(80);
        historyTable.add(new Label("Item", skin)).width(150);
        historyTable.add(new Label("Amount", skin)).width(60);
        historyTable.add(new Label("Message", skin)).width(250);
        historyTable.add(new Label("Time", skin)).width(150);
        historyTable.row();

        ScrollPane scrollPane = new ScrollPane(historyTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(false, false);

        add(scrollPane).width(getWidth() - 40).height(getHeight() - 100).row();

        TextButton closeButton = new TextButton("Close", skin);
        closeButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ClientModel.setWindowOpen(false);
                remove();
            }
        });
        add(closeButton).padTop(10);

        updateHistory(tradeHistory);
    }


    public void updateHistory(List<Map<String, Object>> history) {
        historyTable.clear();


        historyTable.add(new Label("From", skin)).width(120);
        historyTable.add(new Label("To", skin)).width(120);
        historyTable.add(new Label("Status", skin)).width(80);
        historyTable.add(new Label("Item", skin)).width(150);
        historyTable.add(new Label("Amount", skin)).width(60);
        historyTable.add(new Label("Message", skin)).width(250);
        historyTable.add(new Label("Time", skin)).width(150);
        historyTable.row();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Map<String, Object> record : history) {
            String from = String.valueOf(record.get("from"));
            String to = String.valueOf(record.get("to"));
            String status = String.valueOf(record.get("status"));
            String item = String.valueOf(record.get("item"));
            String amount = String.valueOf(record.get("amount"));
            String msg = String.valueOf(record.get("message"));
            String time = sdf.format(new Date((Long) record.get("timestamp")));

            historyTable.add(new Label(from, skin)).width(120);
            historyTable.add(new Label(to, skin)).width(120);
            historyTable.add(new Label(status, skin)).width(80);
            historyTable.add(new Label(item, skin)).width(150);
            historyTable.add(new Label(amount, skin)).width(60);
            historyTable.add(new Label(msg, skin)).width(250);
            historyTable.add(new Label(time, skin)).width(150);
            historyTable.row();
        }
    }
}
