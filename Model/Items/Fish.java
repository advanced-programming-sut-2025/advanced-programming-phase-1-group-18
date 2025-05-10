package Model.Items;

import Model.Name;
import enums.CommonFishesEnums;
import enums.LegendaryFishesEnums;
import enums.Quality;
import enums.Seasons;

public class Fish extends Item implements Name,Price
{
    protected String Jens;
    protected String name;
    protected double basePrice ;
    protected Seasons seasons ;
    protected Quality quality;

    public String getJens() {
        return Jens;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Seasons getSeasons() {
        return seasons;
    }

    public void setJens(String jens) {
        Jens = jens;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public void setSeasons(Seasons seasons) {
        this.seasons = seasons;
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public Fish(String fishName) {
        for (CommonFishesEnums fish : CommonFishesEnums.values()) {
            if (fish.name().equals(fishName)){
                this.name = fish.name();
                this.basePrice = fish.getValue();
                this.seasons = fish.getSeason();
                this.Jens = "Common";
                break;
            }
        }
        for (LegendaryFishesEnums fish : LegendaryFishesEnums.values()) {
            if (fish.name().equals(fishName)){
                this.name = fish.name();
                this.basePrice = fish.getValue();
                this.seasons = fish.getSeason();
                this.Jens = "Legendary";
                break;
            }
        }
        this.quality = Quality.Normal;
    }
    @Override
    public String getCorrectName() {
        return this.name.toLowerCase().replace(" ","");
    }

    @Override
    public int getCorrectPrice() {
        return (int)this.basePrice;
    }
}