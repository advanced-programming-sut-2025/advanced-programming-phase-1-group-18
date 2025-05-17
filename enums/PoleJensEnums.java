package enums;

public enum PoleJensEnums {
    Training(0.1),
    Bamboo(0.5),
    Fiberglass(0.9),
    Iridium(1.2);

    private final double qualityMultiplier;
    public static boolean isContain(String poleName){
        poleName = poleName.toLowerCase().replaceAll(" ", "");
        poleName=poleName.replaceAll("pole", "");
        for(PoleJensEnums pole : PoleJensEnums.values()){
            if(pole.name().equalsIgnoreCase(poleName)){
                return true;
            }
        }
        return false;
    }
    PoleJensEnums(double qualityMultiplier) {
        this.qualityMultiplier = qualityMultiplier;
    }

    public double getQualityMultiplier() {
        return qualityMultiplier;
    }
}