package Model.Items;

public class MilkPail extends Item
{
    protected int EnergyUsage;
    protected String usage;
    protected int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
    public int getEnergyUsage() {
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.EnergyUsage = energyUsage;
    }
}
