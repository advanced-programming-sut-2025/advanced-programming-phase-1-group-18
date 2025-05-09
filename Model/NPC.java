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
    //private ArrayList<Object,Integer> quests;
    private HashMap<Object, NPCItem> quests;
//    private HashMap<Item, NPCItem> rewards;

    public NPC(NPCEnums name) {
        friendships = new ArrayList<>();
        for (Player player : App.getCurrentGame().getPlayers()) {
            Friendshipali friendship = new Friendshipali(player, 0);
            friendships.add(friendship);
        }

        quests = new HashMap<>();
//        rewards = new HashMap<>();

        this.name = name;
        this.talkedWithToday = false;

        NPCItem npcItemlvl0 = new NPCItem();
        npcItemlvl0.setRequiredLevel(0);

        NPCItem npcItemlvl1 = new NPCItem();
        npcItemlvl1.setRequiredLevel(1);

        NPCItem npcItemlvl2 = new NPCItem();
        npcItemlvl2.setRequiredLevel(2);

        switch (name) {
            case SEBASTIAN:
                job = "wool, pumpkin pie, pizza";

                Mineral iron = new Mineral();
                iron.setType(ForagingMineralsEnums.Iron);
                npcItemlvl0.setQuantity(50);
                quests.put(iron, npcItemlvl0);
//                Mineral diamond = new Mineral();
//                diamond.setType(ForagingMineralsEnums.Diamond);
//                npcItemlvl0.setQuantity(2);
//                rewards.put(diamond, npcItemlvl0);


                FoodCooking pumpkinPie = new FoodCooking();
                pumpkinPie.setName(FoodCookingEnums.pumpkinpie);
                npcItemlvl1.setQuantity(1);
                quests.put(pumpkinPie, npcItemlvl1);

                Stone stone = new Stone();
                npcItemlvl2.setQuantity(150);
                quests.put(stone, npcItemlvl2);
//                Mineral quartz = new Mineral();
//                quartz.setType(ForagingMineralsEnums.Quartz);
//                npcItemlvl2.setQuantity(50);
//                rewards.put(quartz, npcItemlvl2);
                break;

            case ABIGAIL:
                job = "stone, iron ore, coffee";

                Mineral goldBar = new Mineral();
                goldBar.setType(ForagingMineralsEnums.Gold);
                npcItemlvl0.setQuantity(1);
                quests.put(goldBar, npcItemlvl0);


                AllCrop pumpkin = new AllCrop();
                pumpkin.setSourceForagingSeedEnum(ForagingSeedsEnums.PumpkinSeeds);
                npcItemlvl1.setQuantity(1);
                quests.put(pumpkin, npcItemlvl1);


                AllCrop wheat = new AllCrop();
                wheat.setSourceMixedSeedEnum(MixedSeedsEnums.Wheat);
                npcItemlvl2.setQuantity(50);
                quests.put(wheat, npcItemlvl2);
                break;

            case HARVEY:
                job = "coffee, pickles, wine";

                AllCrop allCrop2 = new AllCrop();
                npcItemlvl0.setQuantity(12);
                quests.put(allCrop2, npcItemlvl0);


//                Fish salmon = new Fish();
//                salmon.setName(FishEnums.Salmon);
//                npcItemlvl1.setQuantity(1);
//                quests.put(salmon, npcItemlvl1);


//                Beverage wine = new Beverage();
//                wine.setType(BeverageEnums.Wine);
//                npcItemlvl2.setQuantity(1);
//                quests.put(wine, npcItemlvl2);
//                break;
//                FoodCooking salad = new FoodCooking();
//                salad.setName(FoodCookingEnums.Salad);
//                npcItemlvl2.setQuantity(5);
//                rewards.put(salad, npcItemlvl2);

            case LEAH:
                job = "salad, grape, wine";

//                Wood wood = new Wood();
//                wood.setType(WoodEnums.Normal);
//                npcItemlvl0.setQuantity(200);
//                quests.put(wood, npcItemlvl0);
//                break;


//                Fish salmon2 = new Fish();
//                salmon2.setName(FishEnums.Salmon);
//                npcItemlvl1.setQuantity(1);
//                quests.put(salmon2, npcItemlvl1);


//                Furniture scarecrow = new Furniture();
//                scarecrow.setName(FurnitureEnums.DeluxeScarecrow);
//                npcItemlvl2.setQuantity(3);
//                quests.put(scarecrow, npcItemlvl2);

            case ROBIN:
                job = "spaghetti, wood, iron bar";

//                Wood woodLvl1 = new Wood();
//                woodLvl1.setType(WoodEnums.Normal);
//                npcItemlvl0.setQuantity(80);
//                quests.put(woodLvl1, npcItemlvl0);


                Mineral ironbar = new Mineral();
                ironbar.setType(ForagingMineralsEnums.Iron);
                npcItemlvl1.setQuantity(10);
                quests.put(ironbar, npcItemlvl1);


//                Wood woodLvl3 = new Wood();
//                woodLvl3.setType(WoodEnums.Normal);
//                npcItemlvl2.setQuantity(1000);
//                quests.put(woodLvl3, npcItemlvl2);
//                break;

        }
    }

    public ArrayList<Friendshipali> getFriendships() {
        return friendships;
    }

    public void setFriendships(ArrayList<Friendshipali> friendships) {
        this.friendships = friendships;
    }

    public HashMap<Object, NPCItem> getQuests() {
        return quests;
    }

    public void setQuests(HashMap<Object, NPCItem> quests) {
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

