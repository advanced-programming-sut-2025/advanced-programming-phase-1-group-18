package Controller;

import Model.App;
import Model.Result;
import java.util.HashMap;
import java.util.Map;

public interface MarketController<T> {
    public default void cheatAdd(int count) {
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setMoney(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMoney() + count);
    }

    HashMap<T, Integer> getStock();

    default Result showAllProducts() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<T, Integer> stock = getStock();
        stringBuilder.append("All Products:\n");
        for (Map.Entry<T, Integer> entry : stock.entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue() == -1 ? "Unlimited" : entry.getValue());
        }
        return new Result(true,stringBuilder.toString());
    }

    default Result showAllAvailableProduct() {
        StringBuilder stringBuilder = new StringBuilder();
        HashMap<T, Integer> stock = getStock();
        stringBuilder.append("Available Products:\n");
        for (Map.Entry<T, Integer> entry : stock.entrySet()) {
            if (entry.getValue() > 0 || entry.getValue() == -1) {
                stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue() == -1 ? "Unlimited" : entry.getValue());
            }
        }
        return new Result(true,stringBuilder.toString());
    }
}