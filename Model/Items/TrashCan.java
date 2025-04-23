package Model.Items;

public class TrashCan extends Item
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
        this.EnergyUsage = energyUsage;
    }
}
