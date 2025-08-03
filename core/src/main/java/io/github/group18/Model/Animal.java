package io.github.group18.Model;

import java.util.ArrayList;

import io.github.group18.Model.Items.AnimalProduct;

public class Animal {
    public String Name;
    public int Friendship = 0;
    public boolean Navazesh = false;
    public boolean Taghzieh = false;
    //
    public String whereDoILive;
    public int xofAnimal;
    public int yofAnimal;
    public boolean isOutside;

    public int Price;
    public ArrayList<AnimalProduct> AnimalProducts;

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

    public boolean isOutside() {
        return isOutside;
    }

    public void setOutside(boolean outside) {
        isOutside = outside;
    }

    public int getXofAnimal() {
        return xofAnimal;
    }

    public void setXofAnimal(int xofAnimal) {
        this.xofAnimal = xofAnimal;
    }

    public int getYofAnimal() {
        return yofAnimal;
    }

    public void setYofAnimal(int yofAnimal) {
        this.yofAnimal = yofAnimal;
    }

    public String getWhereDoILive() {
        return whereDoILive;
    }

    public void setWhereDoILive(String whereDoILive) {
        this.whereDoILive = whereDoILive;
    }

}
