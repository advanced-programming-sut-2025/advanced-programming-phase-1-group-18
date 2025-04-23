package Model.Items;

public class AnimalProduct extends Item
{
    protected String Name;
    protected String Jens;
    protected int SellPrice;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        this.Jens = jens;
    }

    public int getSellPrice() {
        return SellPrice;
    }

    public void setSellPrice(int sellPrice) {
        this.SellPrice = sellPrice;
    }

}
