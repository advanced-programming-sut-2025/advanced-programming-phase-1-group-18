package Model;

import enums.TradeStatus;

import java.util.ArrayList;
import java.util.List;

public class TradeManager {
    private static final List<Trade> trades = new ArrayList<>();

    public static void addTrade(Trade trade) {
        trades.add(trade);
    }

    public static List<Trade> getAllTrades() {
        return new ArrayList<>(trades); // to avoid direct modification
    }

    public static List<Trade> getTradesByStatus(TradeStatus status) {
        List<Trade> filtered = new ArrayList<>();
        for (Trade trade : trades) {
            if (trade.getStatus() == status) {
                filtered.add(trade);
            }
        }
        return filtered;
    }
}
