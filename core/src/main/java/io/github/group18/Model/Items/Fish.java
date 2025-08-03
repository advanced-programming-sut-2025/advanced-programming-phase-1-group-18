package io.github.group18.Model.Items;

import io.github.group18.Model.Name;
import io.github.group18.enums.CommonFishesEnums;
import io.github.group18.enums.LegendaryFishesEnums;
import io.github.group18.enums.Quality;
import io.github.group18.enums.Seasons;

public class Fish extends Item implements Name,Price
{
    public String Jens;
    public String name;
    public double basePrice ;
    public Seasons seasons ;
    public Quality quality;

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
