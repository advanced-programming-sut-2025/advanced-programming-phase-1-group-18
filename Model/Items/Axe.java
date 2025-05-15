package Model.Items;

import Model.Name;

public class Axe extends Tool implements Name,Price
{
    protected String Jens;
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
        EnergyUsage = energyUsage;
    }

    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        Jens = jens;
    }

    @Override
    public String getCorrectName() {
        return "axe";
    }

    @Override
    public int getCorrectPrice() {
        return 0;
    }
}
