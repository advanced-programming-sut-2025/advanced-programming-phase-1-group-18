package Model.Items;

public abstract class Crop extends Item
{

   protected int BaseSellPrice;
   protected int Energy;


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
