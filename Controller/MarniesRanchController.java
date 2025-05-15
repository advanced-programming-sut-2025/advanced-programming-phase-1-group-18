package Controller;

import Model.*;
import Model.Items.Hay;
import Model.Items.Item;
import Model.Items.MilkPail;
import Model.Items.Shear;
import enums.CageAnimalsEnums;
import enums.Menu;
import enums.TavilehAnimalEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class MarniesRanchController extends GameMenuController implements MenuEnter, ShowCurrentMenu, MarketController<Item> {
    @Override
    public HashMap<Item, Integer> getStock() {
        return App.getCurrentGame().getMarniesRanchMarket().getStock();
    }

    public Result purchase(String name, String count) {
        int quantity = -1;
        if (count == null)
        {
            quantity = 1;
        } else
        {
            quantity = Integer.parseInt(count);
        }
        Player currentPlayer = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        switch (name.toLowerCase()) {
            case "hay":
                boolean validquantity = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof Hay && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity = true;
                        if (currentPlayer.getGold() >= ((Hay) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + count + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "milk pail":
                boolean validquantity1 = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof MilkPail && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getGold() >= ((MilkPail) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + count + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity1) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "shears":
                boolean validquantity2 = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof Shear && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getGold() >= ((Shear) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + count + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity2)
                {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            default:
                return new Result(false, "Invalid product name");
        }

        return null;
    }
//    public Result buyAnimal(String typeOfAnimal, String nameOfAnimal)
//    {
//        boolean uniqueName = IsAnimalNameUnique(nameOfAnimal);
//        if(!uniqueName)
//        {
//            return new Result(false, "Your entered animal name is not unique!");
//        }
//        typeOfAnimal=typeOfAnimal.toLowerCase();
//
//        switch (typeOfAnimal) {
//            case "chicken":
//                if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() < 800) {
//                    return new Result(false, "You don't have enough gold");
//                } else {
//                    Chicken animal = new Chicken();
//                    // Is Cage Full??
//                    if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage().getCageAnimals().size()) {
//                        animal.setType(String.valueOf(CageAnimalsEnums.Chicken));
//                        animal.setName(nameOfAnimal);
//                        animal.setPrice(800);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<CageAnimal> cageAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyCage().getCageAnimals();
//                        cageAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 800;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Chicken bought successfully and went to simple coop");
//
//                    } else if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals().size()) {
//                        animal.setType(String.valueOf(CageAnimalsEnums.Chicken));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<CageAnimal> cageAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals();
//                        cageAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 800;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Chicken bought successfully and went to big coop");
//
//                    } else if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals().size()) {
//                        animal.setType(String.valueOf(CageAnimalsEnums.Chicken));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<CageAnimal> cageAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals();
//                        cageAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 800;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Chicken bought successfully and went to deluxe coop");
//
//                    } else
//                    {
//                        return new Result(false, "You don't have enough space for keep this chicken!");
//                    }
//                }
//
//
//
//            case "cow":
//                if(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold()<1500)
//                {
//                    return new Result(false, "You don't have enough gold");
//                }
//                else
//                {
//                    TavilehAnimal animal = new TavilehAnimal();
//
//                    if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getStatus()) {
//                        animal.setType(String.valueOf(TavilehAnimalEnums.Cow));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<TavilehAnimal> tavilehAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals();
//                        tavilehAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 1500;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Cow bought successfully and went to simple barn");
//
//                    } else if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getStatus()) {
//                        animal.setType(String.valueOf(TavilehAnimalEnums.Cow));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<TavilehAnimal> tavilehAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getTavilehAnimals();
//                        tavilehAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 1500;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Cow bought successfully and went to big barn");
//
//                    } else if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getStatus() ) {
//                        animal.setType(String.valueOf(TavilehAnimalEnums.Cow));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<TavilehAnimal> tavilehAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
//                        tavilehAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 1500;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Cow bought successfully and went to deluxe barn");
//
//                    } else
//                    {
//                        return new Result(false, "You don't have enough space for keep this cow!");
//                    }
//                }
//
//            case "goat":
//                if(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold()<4000)
//                {
//                    return new Result(false, "You don't have enough gold");
//                }
//                else
//                {
//                    TavilehAnimal animal = new TavilehAnimal();
//                    if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getStatus()) {
//                        animal.setType(String.valueOf(TavilehAnimalEnums.Goat));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<TavilehAnimal> tavilehAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyTavileh().getTavilehAnimals();
//                        tavilehAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 4000;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Goat bought successfully and went to simple barn");
//
//                    } else if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getStatus()) {
//                        animal.setType(String.valueOf(TavilehAnimalEnums.Cow));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<TavilehAnimal> tavilehAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigBarn().getTavilehAnimals();
//                        tavilehAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 4000;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Goat bought successfully and went to big barn");
//                    }
//                    else {
//                        return new Result(false, "You don't have enough space for keep this goat!");
//                    }
//                }
//
//            case "duck":
//                if(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold()<1200)
//                {
//                    return new Result(false, "You don't have enough gold");
//                }
//                else
//                {
//                    CageAnimal animal = new CageAnimal();
//                    // Is Cage Full??
//                    if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getStatus() ) {
//                        animal.setType(String.valueOf(CageAnimalsEnums.Duck));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<CageAnimal> cageAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals();
//                        cageAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 1200;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Duck bought successfully and went to big coop");
//                    }
//                    else if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getStatus() ) {
//                        animal.setType(String.valueOf(CageAnimalsEnums.Duck));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<CageAnimal> cageAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals();
//                        cageAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 1200;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Duck bought successfully and went to deluxe coop");
//                    }
//                    else {
//                        return new Result(false, "You don't have enough space for keep this Duck!");
//                    }
//                }
//
//            case "sheep":
//                if(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold()<1200)
//                {
//                    return new Result(false, "You don't have enough gold");
//                }
//                else
//                {
//                    TavilehAnimal animal = new TavilehAnimal();
//                    // Is Cage Full??
//                    if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getStatus() ) {
//                        animal.setType(String.valueOf(TavilehAnimalEnums.Sheep));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<TavilehAnimal> tavilehAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
//                        tavilehAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 8000;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Sheep bought successfully and went to deluxe barn");
//                    }
//                    else {
//                        return new Result(false, "You don't have enough space for keep this Sheep!");
//                    }
//                }
//
//
//            case "rabbit":
//                if(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold()<8000)
//                {
//                    return new Result(false, "You don't have enough gold");
//                }
//                else
//                {
//                    CageAnimal animal = new CageAnimal();
//                    // Is Cage Full??
//                    if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getStatus() ) {
//                        animal.setType(String.valueOf(CageAnimalsEnums.Rabbit));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<CageAnimal> cageAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeCoop().getCageAnimals();
//                        cageAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 8000;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Rabbit bought successfully and went to deluxe coop");
//                    }
//                    else {
//                        return new Result(false, "You don't have enough space for keep this Rabbit!");
//                    }
//                }
//
//            case "dinosaur":
//                if(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold()<14000)
//                {
//                    return new Result(false, "You don't have enough gold");
//                }
//                else
//                {
//                    Dinosaur  animal = new CageAnimal();
//                    // Is Cage Full??
//                    if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getStatus()) {
//                        animal.setType(String.valueOf(CageAnimalsEnums.Dinosaur));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<CageAnimal> cageAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyBigCoop().getCageAnimals();
//                        cageAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 14000;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Dinosaur bought successfully and went to Big coop");
//                    }
//                    else {
//                        return new Result(false, "You don't have enough space for keep this Dinosaur!");
//                    }
//                }
//
//
//            case "pig":
//                if(App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold()<16000)
//                {
//                    return new Result(false, "You don't have enough gold");
//                }
//                else
//                {
//                    TavilehAnimal animal = new TavilehAnimal();
//                    // Is Tavileh Full??
//                    if ((App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getMaxCapacity() > App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals().size()) && App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getStatus()) {
//                        animal.setType(String.valueOf(TavilehAnimalEnums.Pig));
//                        animal.setName(nameOfAnimal);
//                        ArrayList<Animal> animals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//                        animals.add(animal);
//                        ArrayList<TavilehAnimal> tavilehAnimals =  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
//                        tavilehAnimals.add(animal);
//                        int newGold = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getGold() - 16000;
//                        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).setGold(newGold);
//                        return new Result(true, "Pig bought successfully and went to Big coop");
//                    }
//                    else {
//                        return new Result(false, "You don't have enough space for keep this Pig!");
//                    }
//                }
//
//            default:
//                return new Result(false, "Invalid animal");
//        }
//
//    }
//
//
//    // for finding animal name
//    public boolean  IsAnimalNameUnique(String name)
//    {
//        ArrayList<Animal> playerAnimals = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyBoughtAnimals();
//        for(Animal animal : playerAnimals)
//        {
//            if(animal.getName().equals(name))
//            {
//                return false;
//            }
//        }
//        return true;
//    }
//

    public void menuEnter(String menuName) {
        //from markets we can move to gamemenu
        menuName = menuName.toLowerCase();
        switch (menuName) {
            case "gamemenu":
                App.setCurrentMenu(Menu.GameMenu);
                System.out.println("You are now in GameMenu!");
                break;
            default:
                System.out.println("Invalid menu");
                break;
        }
    }
}