package Model.Items;

import Model.DateTime;
import Model.Name;
import enums.ArtisanGoodsEnums;
import enums.CraftingRecipesEnums;

import java.util.HashMap;

public class ArtisanGoods extends Item implements Name, Price {

    protected ArtisanGoodsEnums artisanGoods;
    protected CraftingRecipesEnums producer;
    protected int energyUsage;
    protected DateTime timeToProduce;
    protected HashMap<Item, Integer> ingredients;
    private DateTime startTime; // زمان شروع تولید
    private boolean isDone = false;
    protected int price;

    public ArtisanGoods(String artisanName) {
        for (ArtisanGoodsEnums good : ArtisanGoodsEnums.values()) {
            if (good.name().equals(artisanName)) {
                this.artisanGoods = good;
                this.producer = good.getProducer();
                this.energyUsage = good.getEnergy();
                this.timeToProduce = good.getProcessingTime();
                this.ingredients = new HashMap<>();
                break;
            }
        }
    }

    public void startProcessing(DateTime currentTime) {
        this.startTime = currentTime; // ذخیره زمان شروع
        this.isDone = false;
    }

    public boolean isProcessingDone(DateTime currentTime) {
        if (isProcessingDone(startTime, currentTime, timeToProduce)) {
            this.isDone = true;
        }
        return isProcessingDone(startTime, currentTime, timeToProduce);
    }

    public static boolean isProcessingDone(DateTime startTime, DateTime currentTime, DateTime processingTime) {
        int startTotal = startTime.getDay() * 24 + startTime.getHour();
        int currentTotal = currentTime.getDay() * 24 + currentTime.getHour();
        int required = processingTime.getDay() * 24 + processingTime.getHour();

        return (currentTotal - startTotal) >= required;
    }

    public ArtisanGoodsEnums getArtisanGoods() {
        return artisanGoods;
    }

    public CraftingRecipesEnums getProducer() {
        return producer;
    }

    public int getEnergyUsage() {
        return energyUsage;
    }

    public DateTime getTimeToProduce() {
        return timeToProduce;
    }

    public HashMap<Item, Integer> getIngredients() {
        return ingredients;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public boolean isDone() {
        return isDone;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String getCorrectName() {
        return artisanGoods.toString().toLowerCase().replace(" ", "");
    }


    @Override
    public int getCorrectPrice() {
        return price;
    }
}