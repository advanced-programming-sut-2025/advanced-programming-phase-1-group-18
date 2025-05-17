package Model;

import Model.Items.Item;
import Model.Player;
import enums.TradeStatus;

public class Trade {
    private static int nextId = 1;
    private final int id;

    private String type;
    private Item item;
    private int amount;

    private Integer price;
    private Item targetItem;
    private Integer targetAmount;

    private Player sender;
    private Player receiver;

    private TradeStatus status; // ENUM → PENDING, ACCEPTED, REJECTED
    public Trade(String type, Item item, int amount, Integer price,
                 Item targetItem, Integer targetAmount, Player sender) {
        this.id = nextId++;
        this.type = type;
        this.item = item;
        this.amount = amount;
        this.price = price;
        this.targetItem = targetItem;
        this.targetAmount = targetAmount;
        this.sender = sender;
        this.receiver = null;
        this.status = TradeStatus.PENDING;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public Integer getPrice() {
        return price;
    }

    public Item getTargetItem() {
        return targetItem;
    }

    public Integer getTargetAmount() {
        return targetAmount;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public TradeStatus getStatus() {
        return status;
    }

    public void setStatus(TradeStatus status) {
        this.status = status;
    }
}
