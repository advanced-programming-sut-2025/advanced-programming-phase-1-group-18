package Model.Items;

import Model.Items.Item;
import Model.Items.Price;
import Model.Name;
import enums.AnimalProductType;
import enums.ProductQuality;

public class AnimalProduct extends Item implements Name, Price {
    private String name;
    private String jens;
    private int basePrice;
    private AnimalProductType type;
    private ProductQuality quality;

    public AnimalProduct(String name, String jens, int basePrice, AnimalProductType type, ProductQuality quality) {
        this.name = name;
        this.jens = jens;
        this.basePrice = basePrice;
        this.type = type;
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJens() {
        return jens;
    }

    public void setJens(String jens) {
        this.jens = jens;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public AnimalProductType getType() {
        return type;
    }

    public void setType(AnimalProductType type) {
        this.type = type;
    }

    public ProductQuality getQuality() {
        return quality;
    }

    public void setQuality(ProductQuality quality) {
        this.quality = quality;
    }

    @Override
    public String getCorrectName() {
        return name.toLowerCase().replace(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return (int) (basePrice * quality.getPriceMultiplier());
    }
}