package Controller;

import Model.App;
import Model.Items.Item;
import Model.Result;

import java.util.HashMap;
import java.util.Map;

public interface MarketController<T> {
    public default void cheatAdd(int count) {
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() + count);
        System.out.println("new Balance: " + App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold());
    }

    HashMap<T, Integer> getStock();

    default Result showAllProducts() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<T, Integer> stock = getStock();
        stringBuilder.append("All Products:\n");
        for (Map.Entry<T, Integer> entry : stock.entrySet()) {
            if (entry.getKey() instanceof Item) {
                Item convertItem = (Item) entry.getKey();
                stringBuilder.append(convertItem.getCorrectName()).append(": ").append(entry.getValue() == -1 ? "Unlimited" : entry.getValue());
            } else {
                stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue() == -1 ? "Unlimited" : entry.getValue());
            }
            stringBuilder.append("\n");
        }
        return new Result(true, stringBuilder.toString());
    }

    default Result showAllAvailableProduct() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<T, Integer> stock = getStock();
        stringBuilder.append("Available Products:\n");
        for (Map.Entry<T, Integer> entry : stock.entrySet()) {
            if (entry.getValue() > 0 || entry.getValue() == -1) {
                if (entry.getKey() instanceof Item) {
                    Item convertItem = (Item) entry.getKey();
                    stringBuilder.append(convertItem.getCorrectName()).append(": ").append(entry.getValue() == -1 ? "Unlimited" : entry.getValue());
                } else {
                    stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue() == -1 ? "Unlimited" : entry.getValue());
                }
                stringBuilder.append("\n");
            }
        }
        return new Result(true, stringBuilder.toString());
    }
}