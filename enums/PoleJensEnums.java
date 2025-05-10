package enums;

public enum PoleJensEnums {
    Training(0.1),
    Bamboo(0.5),
    Fiberglass(0.9),
    Iridium(1.2);

    private final double qualityMultiplier;

    PoleJensEnums(double qualityMultiplier) {
        this.qualityMultiplier = qualityMultiplier;
    }

    public double getQualityMultiplier() {
        return qualityMultiplier;
    }
}