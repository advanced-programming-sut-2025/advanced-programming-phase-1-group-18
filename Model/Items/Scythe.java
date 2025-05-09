package Model.Items;

import Model.Name;

public class Scythe extends Item implements Name,Price
{
    protected int EnergyUsage;
    protected String usage;

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

    @Override
    public String getCorrectName() {
        return "scythe";
    }


    @Override
    public int getCorrectPrice() {
        return 0;
    }
}
