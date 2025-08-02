package io.github.group18.Controller;


import io.github.group18.Model.*;
import io.github.group18.Model.Items.Hay;
import io.github.group18.Model.Items.Item;
import io.github.group18.Model.Items.MilkPail;
import io.github.group18.Model.Items.Shear;
import io.github.group18.enums.CageAnimalsEnums;
import io.github.group18.enums.Menu;
import io.github.group18.enums.TavilehAnimalEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class MarniesRanchController extends GameMenuController implements MenuEnter, ShowCurrentMenu, MarketController<Item> {
    @Override
    public HashMap<Item, Integer> getStock() {
        return App.getCurrentGame().getMarniesRanchMarket().getStock();
    }

    public Result purchase(String name, String count,Player playerrr) {
        int quantity = -1;
        if (count == null) {
            quantity = 1;
        } else {
            quantity = Integer.parseInt(count);
        }
        Player currentPlayer = playerrr;
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

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "milkpail":
                boolean validquantity1 = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof MilkPail && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity1 = true;
                        if (currentPlayer.getGold() >= ((MilkPail) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity1) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            case "shear":
                boolean validquantity2 = false;
                for (Item item : App.getCurrentGame().getMarniesRanchMarket().getStock().keySet()) {
                    if (item instanceof Shear && App.getCurrentGame().getMarniesRanchMarket().getStock().get(item) >= quantity) {
                        validquantity2 = true;
                        if (currentPlayer.getGold() >= ((Shear) item).getPrice()) {
                            currentPlayer.getInventory().addItem(item, quantity);
                            App.getCurrentGame().getMarniesRanchMarket().removeItem(item, quantity);
                            currentPlayer.setGold(currentPlayer.getGold() - item.getCorrectPrice());

                            return new Result(true, "You purchased " + quantity + " of " + name);
                        } else {
                            return new Result(false, "You don't have enough money");
                        }
                    }
                }
                if (!validquantity2) {
                    return new Result(false, "Not enough stock in store");
                }
                break;
            default:
                return new Result(false, "Invalid product name");
        }

        return null;
    }

    public static Result buyAnimal(String typeOfAnimal, String nameOfAnimal,Player playerrr) {
        boolean uniqueName = IsAnimalNameUnique(nameOfAnimal,playerrr);
        if (!uniqueName) {
            return new Result(false, "Your entered animal name is not unique!");
        }
        typeOfAnimal = typeOfAnimal.toLowerCase();

        switch (typeOfAnimal) {
            case "chicken":
                if (playerrr.getGold() < 800) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    CageAnimal animal = new CageAnimal();
                    if (playerrr.getMyFarm().getMyCage() == null
                        && playerrr.getMyFarm().getMyBigCoop() == null
                        && playerrr.getMyFarm().getMyDeluxeCoop() == null) {
                        return new Result(false, "You don't have any coops for keep this chicken!");
                    }
                    if (playerrr.getMyFarm().getMyCage() != null && (playerrr.getMyFarm().getMyCage().getMaxCapacity() > playerrr.getMyFarm().getMyCage().getCageAnimals().size())) {
                        //sets pack
                        animal.setType((CageAnimalsEnums.Chicken));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(800);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("coop");

                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyCage().getCageAnimals();
                        cageAnimals.add(animal);
                        int newGold = playerrr.getGold() - 800;
                        playerrr.setGold(newGold);
                        return new Result(true, "Chicken bought successfully and went to simple coop");

                    } else if (playerrr.getMyFarm().getMyBigCoop() != null && (playerrr.getMyFarm().getMyBigCoop().getMaxCapacity() > playerrr.getMyFarm().getMyBigCoop().getCageAnimals().size())) {
                        //set pack
                        animal.setType((CageAnimalsEnums.Chicken));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(800);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("bigcoop");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyBigCoop().getCageAnimals();
                        cageAnimals.add(animal);
                        int newGold = playerrr.getGold() - 800;
                        playerrr.setGold(newGold);
                        return new Result(true, "Chicken bought successfully and went to big coop");

                    } else if (playerrr.getMyFarm().getMyDeluxeCoop() != null && playerrr.getMyFarm().getMyDeluxeCoop().getMaxCapacity() > playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals().size()) {
                        //set pack
                        animal.setType(CageAnimalsEnums.Chicken);
                        animal.setName(nameOfAnimal);
                        animal.setPrice(800);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("deluxecoop");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals();
                        cageAnimals.add(animal);
                        int newGold = playerrr.getGold() - 800;
                        playerrr.setGold(newGold);
                        return new Result(true, "Chicken bought successfully and went to deluxe coop");

                    } else {
                        return new Result(false, "You don't have enough space for keep this chicken!");
                    }
                }


            case "cow":
                if (playerrr.getGold() < 1500) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    TavilehAnimal animal = new TavilehAnimal();
                    if (playerrr.getMyFarm().getMyTavileh() == null
                        && playerrr.getMyFarm().getMyBigBarn() == null
                        && playerrr.getMyFarm().getMyDeluxeBarn() == null) {
                        return new Result(false, "You don't have any barns for keep this cow!");
                    }
                    if (playerrr.getMyFarm().getMyTavileh() != null && (playerrr.getMyFarm().getMyTavileh().getMaxCapacity() > playerrr.getMyFarm().getMyTavileh().getTavilehAnimals().size()) && playerrr.getMyFarm().getMyTavileh().getStatus()) {
                        //set animal pack
                        animal.setType((TavilehAnimalEnums.Cow));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(1500);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("barn");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyTavileh().getTavilehAnimals();
                        tavilehAnimals.add(animal);
                        int newGold = playerrr.getGold() - 1500;
                        playerrr.setGold(newGold);
                        return new Result(true, "Cow bought successfully and went to simple barn");

                    } else if (playerrr.getMyFarm().getMyBigBarn() != null && (playerrr.getMyFarm().getMyBigBarn().getMaxCapacity() > playerrr.getMyFarm().getMyBigBarn().getTavilehAnimals().size()) && playerrr.getMyFarm().getMyBigBarn().getStatus()) {
                        //set pack
                        animal.setType((TavilehAnimalEnums.Cow));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(1500);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("bigbarn");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyBigBarn().getTavilehAnimals();
                        tavilehAnimals.add(animal);
                        int newGold = playerrr.getGold() - 1500;
                        playerrr.setGold(newGold);
                        return new Result(true, "Cow bought successfully and went to big barn");

                    } else if ((playerrr.getMyFarm().getMyDeluxeBarn().getMaxCapacity() > playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals().size()) && playerrr.getMyFarm().getMyDeluxeBarn().getStatus()
                        && playerrr.getMyFarm().getMyDeluxeBarn() != null) {
                        //set animal pack
                        animal.setType((TavilehAnimalEnums.Cow));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(1500);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("deluxebarn");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                        tavilehAnimals.add(animal);
                        int newGold = playerrr.getGold() - 1500;
                        playerrr.setGold(newGold);
                        return new Result(true, "Cow bought successfully and went to deluxe barn");

                    } else {
                        return new Result(false, "You don't have enough space for keep this cow!");
                    }
                }

            case "goat":
                if (playerrr.getGold() < 4000) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    TavilehAnimal animal = new TavilehAnimal();
                    if (playerrr.getMyFarm().getMyTavileh() == null
                        && playerrr.getMyFarm().getMyBigBarn() == null) {
                        return new Result(false, "You don't have any barns for keep this goat!");
                    }
                    if (playerrr.getMyFarm().getMyTavileh() != null && (playerrr.getMyFarm().getMyTavileh().getMaxCapacity() > playerrr.getMyFarm().getMyTavileh().getTavilehAnimals().size()) && playerrr.getMyFarm().getMyTavileh().getStatus()) {
                        animal.setType((TavilehAnimalEnums.Goat));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(4000);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("barn");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyTavileh().getTavilehAnimals();
                        tavilehAnimals.add(animal);
                        int newGold = playerrr.getGold() - 4000;
                        playerrr.setGold(newGold);
                        return new Result(true, "Goat bought successfully and went to simple barn");

                    } else if (playerrr.getMyFarm().getMyBigBarn() != null && (playerrr.getMyFarm().getMyBigBarn().getMaxCapacity() > playerrr.getMyFarm().getMyBigBarn().getTavilehAnimals().size()) && playerrr.getMyFarm().getMyBigBarn().getStatus()) {
                        //set animal pack
                        animal.setType((TavilehAnimalEnums.Cow));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(4000);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("bigbarn");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyBigBarn().getTavilehAnimals();
                        tavilehAnimals.add(animal);
                        int newGold = playerrr.getGold() - 4000;
                        playerrr.setGold(newGold);
                        return new Result(true, "Goat bought successfully and went to big barn");
                    } else {
                        return new Result(false, "You don't have enough space for keep this goat!");
                    }
                }

            case "duck":
                if (playerrr.getGold() < 1200) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    CageAnimal animal = new CageAnimal();
                    if (playerrr.getMyFarm().getMyBigCoop() == null
                        && playerrr.getMyFarm().getMyDeluxeCoop() == null) {
                        return new Result(false, "You don't have any coops for keep this duck!");
                    }
                    if (playerrr.getMyFarm().getMyBigCoop() != null && (playerrr.getMyFarm().getMyBigCoop().getMaxCapacity() > playerrr.getMyFarm().getMyBigCoop().getCageAnimals().size()) && playerrr.getMyFarm().getMyBigCoop().getStatus()) {
                        //set animal pack
                        animal.setType((CageAnimalsEnums.Duck));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(1200);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("bigcoop");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyBigCoop().getCageAnimals();
                        cageAnimals.add(animal);
                        int newGold = playerrr.getGold() - 1200;
                        playerrr.setGold(newGold);
                        return new Result(true, "Duck bought successfully and went to big coop");
                    } else if (playerrr.getMyFarm().getMyDeluxeCoop() != null && (playerrr.getMyFarm().getMyDeluxeCoop().getMaxCapacity() > playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals().size()) && playerrr.getMyFarm().getMyDeluxeCoop().getStatus()) {
                        animal.setType((CageAnimalsEnums.Duck));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(1200);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("deluxecoop");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals();
                        cageAnimals.add(animal);
                        int newGold = playerrr.getGold() - 1200;
                        playerrr.setGold(newGold);
                        return new Result(true, "Duck bought successfully and went to deluxe coop");
                    } else {
                        return new Result(false, "You don't have enough space for keep this Duck!");
                    }
                }

            case "sheep":
                if (playerrr.getGold() < 1200) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    TavilehAnimal animal = new TavilehAnimal();
                    if (playerrr.getMyFarm().getMyDeluxeBarn() == null) {
                        return new Result(false, "You don't have any barns for keep this sheep!");
                    }

                    if (playerrr.getMyFarm().getMyDeluxeBarn() != null && (playerrr.getMyFarm().getMyDeluxeBarn().getMaxCapacity() > playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals().size()) && playerrr.getMyFarm().getMyDeluxeBarn().getStatus()) {
                        animal.setType((TavilehAnimalEnums.Sheep));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(8000);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("deluxebarn");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                        tavilehAnimals.add(animal);
                        int newGold = playerrr.getGold() - 8000;
                        playerrr.setGold(newGold);
                        return new Result(true, "Sheep bought successfully and went to deluxe barn");
                    } else {
                        return new Result(false, "You don't have enough space for keep this Sheep!");
                    }
                }


            case "rabbit":
                if (playerrr.getGold() < 8000) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    CageAnimal animal = new CageAnimal();
                    if (playerrr.getMyFarm().getMyDeluxeCoop() == null) {
                        return new Result(false, "You don't have any coops for keep this rabbit!");
                    }
                    if (playerrr.getMyFarm().getMyDeluxeBarn() != null && (playerrr.getMyFarm().getMyDeluxeCoop().getMaxCapacity() > playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals().size()) && playerrr.getMyFarm().getMyDeluxeBarn().getStatus()) {
                        animal.setType((CageAnimalsEnums.Rabbit));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(8000);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("deluxecoop");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyDeluxeCoop().getCageAnimals();
                        cageAnimals.add(animal);
                        int newGold = playerrr.getGold() - 8000;
                        playerrr.setGold(newGold);
                        return new Result(true, "Rabbit bought successfully and went to deluxe coop");
                    } else {
                        return new Result(false, "You don't have enough space for keep this Rabbit!");
                    }
                }

            case "dinosaur":
                if (playerrr.getGold() < 14000) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    CageAnimal animal = new CageAnimal();
                    if (playerrr.getMyFarm().getMyBigCoop() == null) {
                        return new Result(false, "You don't have any coops for keep this dinosaur!");
                    }
                    if (playerrr.getMyFarm().getMyBigCoop() != null && (playerrr.getMyFarm().getMyBigCoop().getMaxCapacity() > playerrr.getMyFarm().getMyBigCoop().getCageAnimals().size()) && playerrr.getMyFarm().getMyBigCoop().getStatus()) {
                        animal.setType((CageAnimalsEnums.Dinosaur));
                        animal.setName(nameOfAnimal);
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        animal.setPrice(14000);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("bigcoop");
                        ArrayList<CageAnimal> cageAnimals = playerrr.getMyFarm().getMyBigCoop().getCageAnimals();
                        cageAnimals.add(animal);
                        int newGold = playerrr.getGold() - 14000;
                        playerrr.setGold(newGold);
                        return new Result(true, "Dinosaur bought successfully and went to Big coop");
                    } else {
                        return new Result(false, "You don't have enough space for keep this Dinosaur!");
                    }
                }


            case "pig":
                if (playerrr.getGold() < 16000) {
                    return new Result(false, "You don't have enough gold");
                } else {
                    TavilehAnimal animal = new TavilehAnimal();

                    if (playerrr.getMyFarm().getMyDeluxeBarn() == null) {
                        return new Result(false, "You don't have any barns for keep this pig!");
                    }

                    if (playerrr.getMyFarm().getMyDeluxeBarn() != null && (playerrr.getMyFarm().getMyDeluxeBarn().getMaxCapacity() > playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals().size()) && playerrr.getMyFarm().getMyDeluxeBarn().getStatus()) {
                        animal.setType((TavilehAnimalEnums.Pig));
                        animal.setName(nameOfAnimal);
                        animal.setPrice(16000);
                        animal.setOutside(false);
                        animal.setXofAnimal(-1);
                        animal.setYofAnimal(-1);
                        animal.setWhereDoILive("deluxebarn");
                        ArrayList<Animal> animals = playerrr.getMyBoughtAnimals();
                        animals.add(animal);
                        ArrayList<TavilehAnimal> tavilehAnimals = playerrr.getMyFarm().getMyDeluxeBarn().getTavilehAnimals();
                        tavilehAnimals.add(animal);
                        int newGold = playerrr.getGold() - 16000;
                        playerrr.setGold(newGold);
                        return new Result(true, "Pig bought successfully and went to Big coop");
                    } else {
                        return new Result(false, "You don't have enough space for keep this Pig!");
                    }
                }

            default:
                return new Result(false, "Invalid animal");
        }

    }

    public static boolean IsAnimalNameUnique(String name,Player playerrr) {
        ArrayList<Animal> playerAnimals = playerrr.getMyBoughtAnimals();
        for (Animal animal : playerAnimals) {
            if (animal.getName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public void menuEnter(String menuName,Player playerrr) {
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

    @Override
    public boolean isAnimalStore() {
        return true;
    }

}
