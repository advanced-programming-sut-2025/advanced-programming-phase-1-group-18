package Model;

import Model.Items.*;
import enums.ForagingSeedsEnums;
import enums.Seasons;
import enums.TreeSeedEnums;

import java.util.HashMap;

public class PierresGeneralStoreMarket  implements adaptMapMarket{

    HashMap<Object, Integer> Stock = new HashMap<>();
    private boolean largePackBougth = false;
    private boolean deluxePackBought = false;

    public boolean isLargePackBougth() {
        return largePackBougth;
    }

    public void setLargePackBougth(boolean largePackBougth) {
        this.largePackBougth = largePackBougth;
    }

    public boolean isDeluxePackBought() {
        return deluxePackBought;
    }

    public void setDeluxePackBought(boolean deluxePackBought) {
        this.deluxePackBought = deluxePackBought;
    }

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

    public void fillStock(Seasons season) {
        if (Stock.size() != 0) {
            Stock.clear();
        }
        Stock = new HashMap<>();

        AllCrop allCrop1 = new AllCrop();
        allCrop1.initilizeCrop(ForagingSeedsEnums.RiceShoot);
        Stock.put(allCrop1, Integer.MAX_VALUE);

        AllCrop allCrop = new AllCrop();
        allCrop.initilizeCrop(ForagingSeedsEnums.WheatSeeds);
        Stock.put(allCrop, Integer.MAX_VALUE);

        MarketProducts products = new MarketProducts();
        products.setName("Bouquet");
        products.setPrice(1000);
        Stock.put(products, 2);

        MarketProducts products1 = new MarketProducts();
        products1.setName("Wedding Ring");
        products1.setPrice(10000);
        Stock.put(products1, 2);

        Craftingrecipe craftingrecipe1 = new Craftingrecipe();
        craftingrecipe1.setName("Dehydrator");
        craftingrecipe1.setPrice(10000);
        Stock.put(craftingrecipe1, 1);

        Craftingrecipe craftingrecipe = new Craftingrecipe();
        craftingrecipe.setName("GrassStarter");
        craftingrecipe.setPrice(1000);
        Stock.put(craftingrecipe, 1);

        Food food2 = new Food();
        food2.setName("sugar");
        food2.setPrice(100);
        Stock.put(food2, Integer.MAX_VALUE);

        ArtisanGoods artisanGoods = new ArtisanGoods("Oil_Corn");
        artisanGoods.setPrice(200);
        Stock.put(artisanGoods, Integer.MAX_VALUE);

        ArtisanGoods artisanGoods1 = new ArtisanGoods("Vinegar");
        artisanGoods1.setPrice(200);
        Stock.put(artisanGoods1, Integer.MAX_VALUE);

        Fertilizer fertilizer = new Fertilizer();
        fertilizer.setName("Deluxe Retaining Soil");
        fertilizer.setPrice(150);
        Stock.put(fertilizer, Integer.MAX_VALUE);

        //grass starter again wtf
        //TODO

        Fertilizer fertilizer1 = new Fertilizer();
        fertilizer1.setName("Speed-Gro");
        fertilizer1.setPrice(100);
        Stock.put(fertilizer1,Integer.MAX_VALUE);

        TreeSeed treeSeed = new TreeSeed();
        treeSeed.setType(TreeSeedEnums.AppleSapling);
        treeSeed.setPrice(4000);
        Stock.put(treeSeed, Integer.MAX_VALUE);

        TreeSeed treeSeed1 = new TreeSeed();
        treeSeed1.setType(TreeSeedEnums.ApricotSapling);
        treeSeed1.setPrice(2000);
        Stock.put(treeSeed1, Integer.MAX_VALUE);

        TreeSeed treeSeed2 = new TreeSeed();
        treeSeed2.setType(TreeSeedEnums.CherrySapling);
        treeSeed2.setPrice(3400);
        Stock.put(treeSeed2, Integer.MAX_VALUE);

        TreeSeed treeSeed3 = new TreeSeed();
        treeSeed3.setType(TreeSeedEnums.OrangeSapling);
        treeSeed3.setPrice(4000);
        Stock.put(treeSeed3, Integer.MAX_VALUE);

        TreeSeed treeSeed4 = new TreeSeed();
        treeSeed4.setType(TreeSeedEnums.PeachSapling);
        treeSeed4.setPrice(6000);
        Stock.put(treeSeed4, Integer.MAX_VALUE);

        TreeSeed treeSeed5 = new TreeSeed();
        treeSeed5.setType(TreeSeedEnums.PomegranateSapling);
        treeSeed5.setPrice(6000);
        Stock.put(treeSeed5, Integer.MAX_VALUE);

        Fertilizer fertilizer2 = new Fertilizer();
        fertilizer2.setName("Basic Retaining Soil");
        fertilizer2.setPrice(100);
        Stock.put(fertilizer2, Integer.MAX_VALUE);

        Fertilizer fertilizer3 = new Fertilizer();
        fertilizer3.setName("Quality Retaining Soil");
        fertilizer3.setPrice(150);
        Stock.put(fertilizer3, Integer.MAX_VALUE);


        ForagingSeed parsnipSeeds = new ForagingSeed();
        parsnipSeeds.setType(ForagingSeedsEnums.ParsnipSeeds);
        parsnipSeeds.setPrice(20);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(30);
        }
        Stock.put(parsnipSeeds, 5);

        ForagingSeed beanStarter = new ForagingSeed();
        beanStarter.setType(ForagingSeedsEnums.BeanStarter);
        beanStarter.setPrice(60);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(90);
        }
        Stock.put(beanStarter, 5);

        ForagingSeed cauliflowerSeeds = new ForagingSeed();
        cauliflowerSeeds.setType(ForagingSeedsEnums.CauliflowerSeeds);
        cauliflowerSeeds.setPrice(80);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(120);
        }
        Stock.put(cauliflowerSeeds, 5);

        ForagingSeed potatoSeeds = new ForagingSeed();
        potatoSeeds.setType(ForagingSeedsEnums.PotatoSeeds);
        potatoSeeds.setPrice(50);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(75);
        }
        Stock.put(potatoSeeds, 5);

        ForagingSeed tulipBulb = new ForagingSeed();
        tulipBulb.setType(ForagingSeedsEnums.TulipBulb);
        tulipBulb.setPrice(20);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(30);
        }
        Stock.put(tulipBulb, 5);

        ForagingSeed kaleSeeds = new ForagingSeed();
        kaleSeeds.setType(ForagingSeedsEnums.KaleSeeds);
        kaleSeeds.setPrice(70);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(105);
        }
        Stock.put(kaleSeeds, 5);

        ForagingSeed jazzSeeds = new ForagingSeed();
        jazzSeeds.setType(ForagingSeedsEnums.JazzSeeds);
        jazzSeeds.setPrice(30);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(45);
        }
        Stock.put(jazzSeeds, 5);

        ForagingSeed garlic = new ForagingSeed();
        garlic.setType(ForagingSeedsEnums.GarlicSeeds);
        garlic.setPrice(40);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(60);
        }
        Stock.put(garlic, 5);

        ForagingSeed riceshoot = new ForagingSeed();
        riceshoot.setType(ForagingSeedsEnums.RiceShoot);
        riceshoot.setPrice(40);
        if (season != Seasons.Spring) {
            parsnipSeeds.setPrice(60);
        }
        Stock.put(riceshoot, 5);

        ForagingSeed melonSeeds = new ForagingSeed();
        melonSeeds.setType(ForagingSeedsEnums.MelonSeeds);
        melonSeeds.setPrice(80);
        if (season != Seasons.Summer) {
            melonSeeds.setPrice(120);
        }
        Stock.put(melonSeeds, 5);

        ForagingSeed tomatoSeeds = new ForagingSeed();
        tomatoSeeds.setType(ForagingSeedsEnums.TomatoSeeds);
        tomatoSeeds.setPrice(50);
        if (season != Seasons.Summer) {
            tomatoSeeds.setPrice(75);
        }
        Stock.put(tomatoSeeds, 5);

        ForagingSeed blueberrySeeds = new ForagingSeed();
        blueberrySeeds.setType(ForagingSeedsEnums.BlueberrySeeds);
        blueberrySeeds.setPrice(80);
        if (season != Seasons.Summer) {
            blueberrySeeds.setPrice(120);
        }
        Stock.put(blueberrySeeds, 5);

        ForagingSeed pepperSeeds = new ForagingSeed();
        pepperSeeds.setType(ForagingSeedsEnums.PepperSeeds);
        pepperSeeds.setPrice(40);
        if (season != Seasons.Summer) {
            pepperSeeds.setPrice(60);
        }
        Stock.put(pepperSeeds, 5);

        ForagingSeed wheatSeeds = new ForagingSeed();
        wheatSeeds.setType(ForagingSeedsEnums.WheatSeeds);
        wheatSeeds.setPrice(10);
        if (season != Seasons.Summer) {
            wheatSeeds.setPrice(15);
        }
        Stock.put(wheatSeeds, 5);

        ForagingSeed radishSeeds = new ForagingSeed();
        radishSeeds.setType(ForagingSeedsEnums.RadishSeeds);
        radishSeeds.setPrice(40);
        if (season != Seasons.Summer) {
            radishSeeds.setPrice(60);
        }
        Stock.put(radishSeeds, 5);

        ForagingSeed poppySeeds = new ForagingSeed();
        poppySeeds.setType(ForagingSeedsEnums.PoppySeeds);
        poppySeeds.setPrice(100);
        if (season != Seasons.Summer) {
            poppySeeds.setPrice(150);
        }
        Stock.put(poppySeeds, 5);

        ForagingSeed spangleSeeds = new ForagingSeed();
        spangleSeeds.setType(ForagingSeedsEnums.SpangleSeeds);
        spangleSeeds.setPrice(50);
        if (season != Seasons.Summer) {
            spangleSeeds.setPrice(75);
        }
        Stock.put(spangleSeeds, 5);

        ForagingSeed hopsStarter = new ForagingSeed();
        hopsStarter.setType(ForagingSeedsEnums.HopsStarter);
        hopsStarter.setPrice(60);
        if (season != Seasons.Summer) {
            hopsStarter.setPrice(90);
        }
        Stock.put(hopsStarter, 5);

        ForagingSeed cornSeeds = new ForagingSeed();
        cornSeeds.setType(ForagingSeedsEnums.CornSeeds);
        cornSeeds.setPrice(150);
        if (season != Seasons.Summer) {
            cornSeeds.setPrice(225);
        }
        Stock.put(cornSeeds, 5);

        ForagingSeed sunflowerSeeds = new ForagingSeed();
        sunflowerSeeds.setType(ForagingSeedsEnums.SunflowerSeeds);
        sunflowerSeeds.setPrice(200);
        if (season != Seasons.Summer) {
            sunflowerSeeds.setPrice(300);
        }
        Stock.put(sunflowerSeeds, 5);

        ForagingSeed redCabbageSeeds = new ForagingSeed();
        redCabbageSeeds.setType(ForagingSeedsEnums.RedCabbageSeeds);
        redCabbageSeeds.setPrice(100);
        if (season != Seasons.Summer) {
            redCabbageSeeds.setPrice(150);
        }
        Stock.put(redCabbageSeeds, 5);

        ForagingSeed eggplantSeeds = new ForagingSeed();
        eggplantSeeds.setType(ForagingSeedsEnums.EggplantSeeds);
        eggplantSeeds.setPrice(20);
        if (season != Seasons.Fall) {
            eggplantSeeds.setPrice(30);
        }
        Stock.put(eggplantSeeds, 5);

        ForagingSeed cornSeeds1 = new ForagingSeed();
        cornSeeds1.setType(ForagingSeedsEnums.CornSeeds);
        cornSeeds1.setPrice(150);
        if (season != Seasons.Fall) {
            cornSeeds1.setPrice(225);
        }
        Stock.put(cornSeeds1, 5);

        ForagingSeed pumpkinSeeds = new ForagingSeed();
        pumpkinSeeds.setType(ForagingSeedsEnums.PumpkinSeeds);
        pumpkinSeeds.setPrice(100);
        if (season != Seasons.Fall) {
            pumpkinSeeds.setPrice(150);
        }
        Stock.put(pumpkinSeeds, 5);

        ForagingSeed bokChoySeeds = new ForagingSeed();
        bokChoySeeds.setType(ForagingSeedsEnums.BokChoySeeds);
        bokChoySeeds.setPrice(50);
        if (season != Seasons.Fall) {
            bokChoySeeds.setPrice(75);
        }
        Stock.put(bokChoySeeds, 5);

        ForagingSeed yamSeeds = new ForagingSeed();
        yamSeeds.setType(ForagingSeedsEnums.YamSeeds);
        yamSeeds.setPrice(60);
        if (season != Seasons.Fall) {
            yamSeeds.setPrice(90);
        }
        Stock.put(yamSeeds, 5);

        ForagingSeed cranberrySeeds = new ForagingSeed();
        cranberrySeeds.setType(ForagingSeedsEnums.CranberrySeeds);
        cranberrySeeds.setPrice(240);
        if (season != Seasons.Fall) {
            cranberrySeeds.setPrice(360);
        }
        Stock.put(cranberrySeeds, 5);

        ForagingSeed sunflowerSeeds1 = new ForagingSeed();
        sunflowerSeeds1.setType(ForagingSeedsEnums.SunflowerSeeds);
        sunflowerSeeds1.setPrice(200);
        if (season != Seasons.Fall) {
            sunflowerSeeds1.setPrice(300);
        }
        Stock.put(sunflowerSeeds1, 5);

        ForagingSeed fairySeeds = new ForagingSeed();
        fairySeeds.setType(ForagingSeedsEnums.FairySeeds);
        fairySeeds.setPrice(200);
        if (season != Seasons.Fall) {
            fairySeeds.setPrice(300);
        }
        Stock.put(fairySeeds, 5);

        ForagingSeed amaranthSeeds = new ForagingSeed();
        amaranthSeeds.setType(ForagingSeedsEnums.AmaranthSeeds);
        amaranthSeeds.setPrice(70);
        if (season != Seasons.Fall) {
            amaranthSeeds.setPrice(105);
        }
        Stock.put(amaranthSeeds, 5);

        ForagingSeed grapeStarter = new ForagingSeed();
        grapeStarter.setType(ForagingSeedsEnums.GrapeStarter);
        grapeStarter.setPrice(60);
        if (season != Seasons.Fall) {
            grapeStarter.setPrice(90);
        }
        Stock.put(grapeStarter, 5);

        ForagingSeed wheatSeeds1 = new ForagingSeed();
        wheatSeeds1.setType(ForagingSeedsEnums.WheatSeeds);
        wheatSeeds1.setPrice(10);
        if (season != Seasons.Fall) {
            wheatSeeds1.setPrice(15);
        }
        Stock.put(wheatSeeds1, 5);

        ForagingSeed artichokeSeeds = new ForagingSeed();
        artichokeSeeds.setType(ForagingSeedsEnums.ArtichokeSeeds);
        artichokeSeeds.setPrice(30);
        if (season != Seasons.Fall) {
            artichokeSeeds.setPrice(45);
        }
        Stock.put(artichokeSeeds, 5);

    }
}
