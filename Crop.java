package Model.Items;

public class Crop extends Item
{

   protected String Name;
   protected int BaseSellPrice;
   protected int Energy;


   public String getName() {
      return Name;
   }

   public void setName(String name) {
      this.Name = name;
   }

   public int getBaseSellPrice() {
      return BaseSellPrice;
   }

   public void setBaseSellPrice(int baseSellPrice) {
      this.BaseSellPrice = baseSellPrice;
   }

   public int getEnergy() {
      return Energy;
   }

   public void setEnergy(int energy) {
      this.Energy = energy;
   }

}
