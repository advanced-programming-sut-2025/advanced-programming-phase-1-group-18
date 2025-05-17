package enums;

public enum ProductQuality {
    NORMAL(1.0),
    SILVER(1.25),
    GOLD(1.5),
    IRIDIUM(2.0);

    private final double priceMultiplier;

    ProductQuality(double priceMultiplier) {
        this.priceMultiplier = priceMultiplier;
    }

    public double getPriceMultiplier() {
        return priceMultiplier;
    }

    public static ProductQuality getQualityFromValue(double value) {
        if (value < 0.5) return NORMAL;
        else if (value < 0.7) return SILVER;
        else if (value < 0.9) return GOLD;
        else return IRIDIUM;
    }
}
