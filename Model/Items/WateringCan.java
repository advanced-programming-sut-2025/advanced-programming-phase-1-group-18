package Model.Items;

import Model.Name;

public class WateringCan extends Item implements Name,Price
{
    protected String Jens;
    protected int EnergyUsage;
    protected String usage;
    protected int capacity;
    protected int max_Capacity;

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        this.Jens = jens;
    }

    public int getEnergyUsage() {
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this. EnergyUsage = energyUsage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getMax_Capacity() {
        return max_Capacity;
    }

    public void setMax_Capacity(int max_Capacity) {
        this.max_Capacity = max_Capacity;
    }

    @Override
    public String getCorrectName() {
        return "wateringcan";
    }

    @Override
    public int getCorrectPrice() {
        return 0;
    }
}





