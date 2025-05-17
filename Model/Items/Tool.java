package Model.Items;

public class Tool extends Item {
    protected int energyUsage;
    protected String usage;

    public int getEnergyUsage() {
        return energyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.energyUsage = energyUsage;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    @Override
    public String getCorrectName()
    {
        return "Tool: " + name;
    }
}