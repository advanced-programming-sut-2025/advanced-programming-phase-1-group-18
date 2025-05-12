package Model;

import Model.Items.*;
import enums.CageAnimalsEnums;
import enums.ForagingMineralsEnums;
import enums.TavilehAnimalEnums;

import java.util.HashMap;

public class MarniesRanchMarket  implements adaptMapMarket{
    HashMap<Item, Integer> Stock = new HashMap<>();
    HashMap<Animal, Integer> AnimalStock = new HashMap<>();

    public HashMap<Animal, Integer> getAnimalStock() {
        return AnimalStock;
    }

    public void setAnimalStock(HashMap<Animal, Integer> animalStock) {
        AnimalStock = animalStock;
    }

    public HashMap<Item, Integer> getStock() {
        return Stock;
    }

    public void setStock(HashMap<Item, Integer> stock) {
        Stock = stock;
    }

    public void addItem(Item item, int quantity) {
        Stock.put(item, Stock.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(Item item, int quantity) {
        if (Stock.containsKey(item)) {
            int currentQuantity = Stock.get(item);
            if (currentQuantity > quantity) {
                Stock.put(item, currentQuantity - quantity);
            } else {
                Stock.remove(item);
            }
        }
    }

    public void addItem(Animal animal, int quantity) {
        AnimalStock.put(animal, AnimalStock.getOrDefault(animal, 0) + quantity);
    }

    public void removeItem(Animal animal, int quantity) {
        if (AnimalStock.containsKey(animal)) {
            int currentQuantity = AnimalStock.get(animal);
            if (currentQuantity > quantity) {
                AnimalStock.put(animal, currentQuantity - quantity);
            } else {
                AnimalStock.remove(animal);
            }
        }
    }
    public void fillStock() {
        if (Stock.size() != 0) {
            Stock.clear();
        }
        Stock = new HashMap<>();
        Hay hay = new Hay();
        hay.setPrice(50);
        Stock.put(hay, Integer.MAX_VALUE);

        MilkPail milkPail = new MilkPail();
        milkPail.setPrice(1000);
        Stock.put(milkPail, 1);

        Shear shear = new Shear();
        shear.setPrice(1000);
        Stock.put(shear, 1);

        CageAnimal chicken = new CageAnimal();
        chicken.setType(CageAnimalsEnums.Chicken);
        chicken.setPrice(800);
        AnimalStock.put(chicken, 2);

        CageAnimal duck = new CageAnimal();
        duck.setType(CageAnimalsEnums.Duck);
        duck.setPrice(1200);
        AnimalStock.put(duck, 2);

        CageAnimal rabbit = new CageAnimal();
        rabbit.setType(CageAnimalsEnums.Rabbit);
        rabbit.setPrice(8000);
        AnimalStock.put(rabbit, 2);

        CageAnimal dinosaur = new CageAnimal();
        dinosaur.setType(CageAnimalsEnums.Chicken);
        dinosaur.setPrice(14000);
        AnimalStock.put(dinosaur, 2);

        TavilehAnimal cow = new TavilehAnimal();
        cow.setType(TavilehAnimalEnums.Cow);
        cow.setPrice(1500);
        AnimalStock.put(cow, 2);

        TavilehAnimal goat = new TavilehAnimal();
        goat.setType(TavilehAnimalEnums.Goat);
        goat.setPrice(4000);
        AnimalStock.put(goat, 2);

        TavilehAnimal sheep = new TavilehAnimal();
        sheep.setType(TavilehAnimalEnums.Sheep);
        sheep.setPrice(8000);
        AnimalStock.put(sheep, 2);

        TavilehAnimal pig = new TavilehAnimal();
        pig.setType(TavilehAnimalEnums.Pig);
        pig.setPrice(16000);
        AnimalStock.put(pig, 2);
    }


}
