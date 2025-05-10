package Model.Items;

import Model.Name;
import Model.Player;

public class Gift extends Item implements Name, Price{
    protected Item giftItem;
    protected Player sender;
    protected Player receiver;
    protected int rate;
    protected boolean isRated = false;
    protected String name;
    protected int amount;
    private static int nextId = 1; // شمارنده استاتیک
    private final int id;

    public Gift(Item giftItem,int amount, Player sender, Player receiver) {
        this.giftItem = giftItem;
        this.amount = amount;
        this.id = nextId;
        nextId++;
        this.sender = sender;
        this.receiver = receiver;
        this.rate = 0;
        this.isRated = false;
        this.name = giftItem.getName();
    }

    public Item getGiftItem() {
        return giftItem;
    }

    public void setGiftItem(Item giftItem) {
        this.giftItem = giftItem;
    }

    public int getId() {
        return id;
    }


    public Player getSender() {
        return sender;
    }

    public void setSender(Player sender) {
        this.sender = sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public void setReceiver(Player receiver) {
        this.receiver = receiver;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public boolean isRated() {
        return isRated;
    }

    public void setRated(boolean rated) {
        isRated = rated;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String getCorrectName() {
        return name.toLowerCase().replace(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return 0;
    }

}