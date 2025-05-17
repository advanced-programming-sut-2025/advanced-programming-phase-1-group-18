package Model;

import Model.Items.*;
import enums.ForagingMineralsEnums;
import enums.ForagingSeedsEnums;
import enums.Seasons;

import java.util.HashMap;
import java.util.StringTokenizer;

public class JojoMartMarket implements adaptMapMarket {
    HashMap<Object, Integer> Stock = new HashMap<>();

    public HashMap<Object, Integer> getStock() {
        return Stock;
    }

    public void setStock(HashMap<Object, Integer> stock) {
        Stock = stock;
    }

    public void addItem(Object item, int quantity) {
        Stock.put(item, Stock.getOrDefault(item, 0) + quantity);
    }

    public void removeItem(Object item, int quantity) {
        if (Stock.containsKey(item)) {
            int currentQuantity = Stock.get(item);
            if (currentQuantity > quantity) {
                Stock.put(item, currentQuantity - quantity);
            } else {
                Stock.remove(item);
            }
        }
    }

    public void fillStock(String season) {
        //System.out.println(season.toString());
        if (Stock.size() != 0) {
            Stock.clear();
        }
        Stock = new HashMap<>();

        Food food = new Food();
        food.setName("Joja Cola");
        food.setPrice(75);
        Stock.put(food, Integer.MAX_VALUE);

        ForagingSeed foragingSeed = new ForagingSeed();
        foragingSeed.setType(ForagingSeedsEnums.AncientSeeds);
        foragingSeed.setPrice(500);
        Stock.put(foragingSeed, 1);

        Craftingrecipe craftingrecipe = new Craftingrecipe();
        craftingrecipe.setName("Grass Starter");
        craftingrecipe.setPrice(125);
        Stock.put(craftingrecipe, Integer.MAX_VALUE);

        Food food1 = new Food();
        food1.setName("sugar");
        food1.setPrice(125);
        Stock.put(food1, Integer.MAX_VALUE);

        AllCrop allCrop = new AllCrop();
        allCrop.initilizeCrop(ForagingSeedsEnums.WheatSeeds);
        Stock.put(allCrop, Integer.MAX_VALUE);

        AllCrop allCrop1 = new AllCrop();
        allCrop1.initilizeCrop(ForagingSeedsEnums.RiceShoot);
        Stock.put(allCrop1, Integer.MAX_VALUE);

        if (season.equalsIgnoreCase("Spring")) {
            ForagingSeed parsnipSeeds = new ForagingSeed();
            parsnipSeeds.setType(ForagingSeedsEnums.ParsnipSeeds);
            parsnipSeeds.setPrice(25);
            Stock.put(parsnipSeeds, 5);

            ForagingSeed beanStarter = new ForagingSeed();
            beanStarter.setType(ForagingSeedsEnums.BeanStarter);
            beanStarter.setPrice(75);
            Stock.put(beanStarter, 5);

            ForagingSeed cauliflowerSeeds = new ForagingSeed();
            cauliflowerSeeds.setType(ForagingSeedsEnums.CauliflowerSeeds);
            cauliflowerSeeds.setPrice(100);
            Stock.put(cauliflowerSeeds, 5);

            ForagingSeed potatoSeeds = new ForagingSeed();
            potatoSeeds.setType(ForagingSeedsEnums.PotatoSeeds);
            potatoSeeds.setPrice(62);
            Stock.put(potatoSeeds, 5);

            ForagingSeed strawberrySeeds = new ForagingSeed();
            strawberrySeeds.setType(ForagingSeedsEnums.StrawberrySeeds);
            strawberrySeeds.setPrice(100);
            Stock.put(strawberrySeeds, 5);

            ForagingSeed tulipBulb = new ForagingSeed();
            tulipBulb.setType(ForagingSeedsEnums.TulipBulb);
            tulipBulb.setPrice(25);
            Stock.put(tulipBulb, 5);

            ForagingSeed kaleSeeds = new ForagingSeed();
            kaleSeeds.setType(ForagingSeedsEnums.KaleSeeds);
            kaleSeeds.setPrice(87);
            Stock.put(kaleSeeds, 5);

            ForagingSeed coffeeBeans = new ForagingSeed();
            coffeeBeans.setType(ForagingSeedsEnums.CoffeeBean);
            coffeeBeans.setPrice(200);
            Stock.put(coffeeBeans, 1);

            ForagingSeed carrotSeeds = new ForagingSeed();
            carrotSeeds.setType(ForagingSeedsEnums.CarrotSeeds);
            carrotSeeds.setPrice(5);
            Stock.put(carrotSeeds, 10);

            ForagingSeed rhubarbSeeds = new ForagingSeed();
            rhubarbSeeds.setType(ForagingSeedsEnums.RhubarbSeeds);
            rhubarbSeeds.setPrice(100);
            Stock.put(rhubarbSeeds, 5);

            ForagingSeed jazzSeeds = new ForagingSeed();
            jazzSeeds.setType(ForagingSeedsEnums.JazzSeeds);
            jazzSeeds.setPrice(37);
            Stock.put(jazzSeeds, 5);
        }
        if (season.equalsIgnoreCase("Summer")) {
            ForagingSeed tomatoSeeds = new ForagingSeed();
            tomatoSeeds.setType(ForagingSeedsEnums.TomatoSeeds);
            tomatoSeeds.setPrice(62);
            Stock.put(tomatoSeeds, 5);

            ForagingSeed pepperSeeds = new ForagingSeed();
            pepperSeeds.setType(ForagingSeedsEnums.PepperSeeds);
            pepperSeeds.setPrice(50);
            Stock.put(pepperSeeds, 5);

            ForagingSeed wheatSeeds = new ForagingSeed();
            wheatSeeds.setType(ForagingSeedsEnums.WheatSeeds);
            wheatSeeds.setPrice(12);
            Stock.put(wheatSeeds, 10);

            ForagingSeed summerSquashSeeds = new ForagingSeed();
            summerSquashSeeds.setType(ForagingSeedsEnums.SummerSquashSeeds);
            summerSquashSeeds.setPrice(10);
            Stock.put(summerSquashSeeds, 10);

            ForagingSeed radishSeeds = new ForagingSeed();
            radishSeeds.setType(ForagingSeedsEnums.RadishSeeds);
            radishSeeds.setPrice(50);
            Stock.put(radishSeeds, 5);

            ForagingSeed melonSeeds = new ForagingSeed();
            melonSeeds.setType(ForagingSeedsEnums.MelonSeeds);
            melonSeeds.setPrice(100);
            Stock.put(melonSeeds, 5);

            ForagingSeed hopsStarter = new ForagingSeed();
            hopsStarter.setType(ForagingSeedsEnums.HopsStarter);
            hopsStarter.setPrice(75);
            Stock.put(hopsStarter, 5);

            ForagingSeed poppySeeds = new ForagingSeed();
            poppySeeds.setType(ForagingSeedsEnums.PoppySeeds);
            poppySeeds.setPrice(125);
            Stock.put(poppySeeds, 5);

            ForagingSeed spangleSeeds = new ForagingSeed();
            spangleSeeds.setType(ForagingSeedsEnums.SpangleSeeds);
            spangleSeeds.setPrice(62);
            Stock.put(spangleSeeds, 5);

            ForagingSeed starfruitSeeds = new ForagingSeed();
            starfruitSeeds.setType(ForagingSeedsEnums.StarfruitSeeds);
            starfruitSeeds.setPrice(400);
            Stock.put(starfruitSeeds, 5);

            ForagingSeed coffeeBeans1 = new ForagingSeed();
            coffeeBeans1.setType(ForagingSeedsEnums.CoffeeBean);
            coffeeBeans1.setPrice(200);
            Stock.put(coffeeBeans1, 1);

            ForagingSeed sunflowerSeeds = new ForagingSeed();
            sunflowerSeeds.setType(ForagingSeedsEnums.SunflowerSeeds);
            sunflowerSeeds.setPrice(125);
            Stock.put(sunflowerSeeds, 5);
        }
        if (season.equalsIgnoreCase("Fall")) {
            ForagingSeed foragingSeed1 = new ForagingSeed();
            foragingSeed1.setType(ForagingSeedsEnums.CornSeeds);
            foragingSeed1.setPrice(187);
            Stock.put(foragingSeed1, 5);

            ForagingSeed eggplantSeeds = new ForagingSeed();
            eggplantSeeds.setType(ForagingSeedsEnums.EggplantSeeds);
            eggplantSeeds.setPrice(25);
            Stock.put(eggplantSeeds, 5);

            ForagingSeed pumpkinSeeds = new ForagingSeed();
            pumpkinSeeds.setType(ForagingSeedsEnums.PumpkinSeeds);
            pumpkinSeeds.setPrice(125);
            Stock.put(pumpkinSeeds, 5);

            ForagingSeed broccoliSeeds = new ForagingSeed();
            broccoliSeeds.setType(ForagingSeedsEnums.BroccoliSeeds);
            broccoliSeeds.setPrice(15);
            Stock.put(broccoliSeeds, 5);

            ForagingSeed amaranthSeeds = new ForagingSeed();
            amaranthSeeds.setType(ForagingSeedsEnums.AmaranthSeeds);
            amaranthSeeds.setPrice(87);
            Stock.put(amaranthSeeds, 5);

            ForagingSeed grapeStarter = new ForagingSeed();
            grapeStarter.setType(ForagingSeedsEnums.GrapeStarter);
            grapeStarter.setPrice(75);
            Stock.put(grapeStarter, 5);

            ForagingSeed beetSeeds = new ForagingSeed();
            beetSeeds.setType(ForagingSeedsEnums.BeetSeeds);
            beetSeeds.setPrice(20);
            Stock.put(beetSeeds, 5);

            ForagingSeed yamSeeds = new ForagingSeed();
            yamSeeds.setType(ForagingSeedsEnums.YamSeeds);
            yamSeeds.setPrice(75);
            Stock.put(yamSeeds, 5);

            ForagingSeed bokChoySeeds = new ForagingSeed();
            bokChoySeeds.setType(ForagingSeedsEnums.BokChoySeeds);
            bokChoySeeds.setPrice(62);
            Stock.put(bokChoySeeds, 5);

            ForagingSeed cranberrySeeds = new ForagingSeed();
            cranberrySeeds.setType(ForagingSeedsEnums.CranberrySeeds);
            cranberrySeeds.setPrice(300);
            Stock.put(cranberrySeeds, 5);

            ForagingSeed sunflowerSeeds1 = new ForagingSeed();
            sunflowerSeeds1.setType(ForagingSeedsEnums.SunflowerSeeds);
            sunflowerSeeds1.setPrice(125);
            Stock.put(sunflowerSeeds1, 5);

            ForagingSeed fairySeeds = new ForagingSeed();
            fairySeeds.setType(ForagingSeedsEnums.FairySeeds);
            fairySeeds.setPrice(250);
            Stock.put(fairySeeds, 5);

            ForagingSeed rareSeed = new ForagingSeed();
            rareSeed.setType(ForagingSeedsEnums.RareSeed);
            rareSeed.setPrice(1000);
            Stock.put(rareSeed, 1);

            ForagingSeed wheatSeeds1 = new ForagingSeed();
            wheatSeeds1.setType(ForagingSeedsEnums.WheatSeeds);
            wheatSeeds1.setPrice(12);
            Stock.put(wheatSeeds1, 5);
        }

        if (season.equalsIgnoreCase("Winter")) {
            //System.out.println("winter it is");
            ForagingSeed powdermelonSeeds = new ForagingSeed();
            powdermelonSeeds.setType(ForagingSeedsEnums.PowdermelonSeeds);
            powdermelonSeeds.setPrice(20);
            Stock.put(powdermelonSeeds, 10);
        }

    }
}
