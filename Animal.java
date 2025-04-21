package Model;
import java.util.ArrayList;
import java.util.HashMap;

import Model.Items.AnimalProduct;

public class Animal
{
   protected String Name;
   protected int Friendship;
   protected boolean Navazesh;
   protected boolean Taghzieh;

   protected int Price;
   protected ArrayList<AnimalProduct> AnimalProducts;

   // Getter and Setter for Name
   public String getName() {
      return Name;
   }

   public void setName(String Name) {
      this.Name = Name;
   }

   // Getter and Setter for Friendship
   public int getFriendship() {
      return Friendship;
   }

   public void setFriendship(int Friendship) {
      this.Friendship = Friendship;
   }

   // Getter and Setter for Navazesh
   public boolean isNavazesh() {
      return Navazesh;
   }

   public void setNavazesh(boolean Navazesh) {
      this.Navazesh = Navazesh;
   }

   // Getter and Setter for Taghzieh
   public boolean isTaghzieh() {
      return Taghzieh;
   }

   public void setTaghzieh(boolean Taghzieh) {
      this.Taghzieh = Taghzieh;
   }

   // Getter and Setter for Price
   public int getPrice() {
      return Price;
   }

   public void setPrice(int Price) {
      this.Price = Price;
   }

   // Getter and Setter for AnimalProducts
   public ArrayList<AnimalProduct> getAnimalProducts() {
      return AnimalProducts;
   }

   public void setAnimalProducts(ArrayList<AnimalProduct> AnimalProducts) {
      this.AnimalProducts = AnimalProducts;
   }

   public void adaptMap(HashMap<Integer ,Integer> LakeMap)
   {

   }

}
