package Model;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Items.*;
import enums.*;

public class NPC implements adaptMapMarket {
    private NPCEnums name;
    private String job;
    private boolean talkedWithToday;
    private boolean giftedToday;
    private ArrayList<Item> favorites;
    private ArrayList<Friendshipali> friendships;
    private HashMap<Item, NPCItem> quests;

    public NPC(NPCEnums name) {

        friendships = new ArrayList<>();

        for (Player player : App.getCurrentGame().getPlayers()) {
            Friendshipali friendship = new Friendshipali(player, 0);
            friendships.add(friendship);
        }

        quests = new HashMap<>();

        this.name = name;
        this.talkedWithToday = false;

        NPCItem npcItemlvl0 = new NPCItem();
        npcItemlvl0.setRequiredLevel(0);

        NPCItem npcItemlvl1 = new NPCItem();
        npcItemlvl1.setRequiredLevel(1);

        NPCItem npcItemlvl2 = new NPCItem();
        npcItemlvl2.setRequiredLevel(2);


        if (name == NPCEnums.SEBASTIAN) {
            job = "wool, pumpkin pie, pizza";

            Mineral iron = new Mineral();
            iron.setType(ForagingMineralsEnums.IronOre);
            npcItemlvl0.setQuantity(50);
            quests.put(iron, npcItemlvl0);

            FoodCooking pumpkinPie = new FoodCooking();
            pumpkinPie.setName(FoodCookingEnums.PumpkinPie);
            npcItemlvl1.setQuantity(1);
            quests.put(pumpkinPie, npcItemlvl1);

            StoneItem stone = new StoneItem();
            npcItemlvl2.setQuantity(150);
            quests.put(stone, npcItemlvl2);

        } else if (name == NPCEnums.ABIGAIL) {
            job = "stone, iron ore, coffee";

            ArtisanGoods artisanGoods = new ArtisanGoods("GoldBar");
            npcItemlvl0.setQuantity(1);
            quests.put(artisanGoods, npcItemlvl0);

            AllCrop pumpkin = new AllCrop();
            pumpkin.setSourceForagingSeedEnum(ForagingSeedsEnums.PumpkinSeeds);
            npcItemlvl1.setQuantity(1);
            quests.put(pumpkin, npcItemlvl1);

            AllCrop wheat = new AllCrop();
            wheat.setSourceMixedSeedEnum(MixedSeedsEnums.Wheat);
            npcItemlvl2.setQuantity(50);
            quests.put(wheat, npcItemlvl2);

        } else if (name == NPCEnums.HARVEY) {
            job = "coffee, pickles, wine";

            AllCrop allCrop2 = new AllCrop();
            npcItemlvl0.setQuantity(12);
            quests.put(allCrop2, npcItemlvl0);

            Fish fish = new Fish("Salmon");
            npcItemlvl1.setQuantity(1);
            quests.put(fish, npcItemlvl1);

            ArtisanGoods artisanGoods = new ArtisanGoods("Wine");
            npcItemlvl2.setQuantity(1);
            quests.put(artisanGoods, npcItemlvl2);

        } else if (name == NPCEnums.LEAH) {
            job = "salad, grape, wine";


            MarketProducts wood = new MarketProducts();
            wood.setName("wood");
            wood.setPrice(10);
            npcItemlvl0.setQuantity(10);
            quests.put(wood, npcItemlvl0);

            Fish fish = new Fish("Salmon");
            npcItemlvl1.setQuantity(1);
            quests.put(fish, npcItemlvl1);

            MarketProducts wood1 = new MarketProducts();
            wood.setName("wood");
            wood1.setPrice(10);
            npcItemlvl2.setQuantity(200);
            quests.put(wood1, npcItemlvl2);

        } else if (name == NPCEnums.ROBIN) {
            job = "spaghetti, wood, iron bar";

            MarketProducts wood = new MarketProducts();
            wood.setPrice(10);
            wood.setName("wood");
            npcItemlvl0.setQuantity(80);
            quests.put(wood, npcItemlvl0);

            ArtisanGoods iron = new ArtisanGoods("IronBar");
            iron.setPrice(100);
            npcItemlvl1.setQuantity(10);
            quests.put(iron, npcItemlvl1);

            MarketProducts wood1 = new MarketProducts();
            wood1.setPrice(10);
            wood1.setName("wood");
            npcItemlvl2.setQuantity(1000);
            quests.put(wood1, npcItemlvl2);
        }
    }

    public ArrayList<Friendshipali> getFriendships() {
        return friendships;
    }

    public void setFriendships(ArrayList<Friendshipali> friendships) {
        this.friendships = friendships;
    }

    public HashMap<Item, NPCItem> getQuests() {
        return quests;
    }

    public void setQuests(HashMap<Item, NPCItem> quests) {
        this.quests = quests;
    }

    public boolean isTalkedWithToday() {
        return talkedWithToday;
    }

    public void setTalkedWithToday(boolean talkedWithToday) {
        this.talkedWithToday = talkedWithToday;
    }

    public NPCEnums getName() {
        return name;
    }

    public void setName(NPCEnums name) {
        this.name = name;
    }

    public boolean isGiftedToday() {
        return giftedToday;
    }

    public void setGiftedToday(boolean giftedToday) {
        this.giftedToday = giftedToday;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
    }

    public Result meetNPC(String npcName) {
        return new Result(true, "");
    }

    public Result giftNPC(String npcName, String item) {
        return new Result(true, "");
    }


    public void friendshipList() {

    }

    public void questsList() {

    }

    public Result questsFinish(int index) {
        return new Result(true, "");
    }

}

