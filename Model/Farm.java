package Model;

import java.util.ArrayList;
import java.util.List;

import enums.*;

public class Farm {
    protected ArrayList<ArrayList<Kashi>> Map = new ArrayList<>();
    protected ArrayList<Lake> Lakes = new ArrayList<>();
    protected GreenHouse myGreenHouse = new GreenHouse();
    protected Cottage myCottage = new Cottage();
    protected Quarry myQuarry = new Quarry();
    protected ArrayList<Cage> Cages;
    protected ArrayList<Tavileh> Tavilehs;
    protected ArrayList<AllTree> AllTrees;
    protected ArrayList<CageAnimal> CageAnimals;
    protected Cottage MyCottage;
    protected ArrayList<ForagingTree> ForagingTrees;
    protected GreenHouse MyGreenHouse;
    protected ArrayList<Matarsak> Matarsaks;
    protected ArrayList<Quarry> Quarrys;
    protected ArrayList<Stone> Stones;
    protected ArrayList<ForagingCrop> ForagingCrops;
    protected ArrayList<AllCrop> AllCrops;
    protected ArrayList<ForagingSeed> foragingSeeds;
    protected ArrayList<MixedSeed> MixedSeeds;


    public ArrayList<AllCrop> getAllCrops() {
        return AllCrops;
    }

    public void setAllCrops(ArrayList<AllCrop> allCrops) {
        AllCrops = allCrops;
    }

    public ArrayList<MixedSeed> getMixedSeeds() {
        return MixedSeeds;
    }

    public void setMixedSeeds(ArrayList<MixedSeed> mixedSeeds) {
        MixedSeeds = mixedSeeds;
    }

    // Getter and Setter for Map
    public ArrayList<ArrayList<Kashi>> getMap() {
        return Map;
    }

    public void setMap(ArrayList<ArrayList<Kashi>> Map) {
        this.Map = Map;
    }

    // Getter and Setter for Lakes
    public ArrayList<Lake> getLakes() {
        return Lakes;
    }

    public void setLakes(ArrayList<Lake> Lakes) {
        this.Lakes = Lakes;
    }

    // Getter and Setter for myGreenHouse
    public GreenHouse getMyGreenHouse() {
        return myGreenHouse;
    }

    public void setMyGreenHouse(GreenHouse myGreenHouse) {
        this.myGreenHouse = myGreenHouse;
    }

    // Getter and Setter for myCottage
    public Cottage getMyCottage() {
        return myCottage;
    }

    public void setMyCottage(Cottage myCottage) {
        this.myCottage = myCottage;
    }

    // Getter and Setter for myQuarry
    public Quarry getMyQuarry() {
        return myQuarry;
    }

    public void setMyQuarry(Quarry myQuarry) {
        this.myQuarry = myQuarry;
    }

    public ArrayList<Cage> getCages() {
        return Cages;
    }

    public void setCages(ArrayList<Cage> cages) {
        Cages = cages;
    }

    public ArrayList<Tavileh> getTavilehs() {
        return Tavilehs;
    }

    public void setTavilehs(ArrayList<Tavileh> tavilehs) {
        Tavilehs = tavilehs;
    }

    public ArrayList<AllTree> getAllTrees() {
        return AllTrees;
    }

    public void setAllTrees(ArrayList<AllTree> allTrees) {
        AllTrees = allTrees;
    }

    public ArrayList<CageAnimal> getCageAnimals() {
        return CageAnimals;
    }

    public void setCageAnimals(ArrayList<CageAnimal> cageAnimals) {
        CageAnimals = cageAnimals;
    }

    public ArrayList<ForagingTree> getForagingTrees() {
        return ForagingTrees;
    }

    public void setForagingTrees(ArrayList<ForagingTree> foragingTrees) {
        ForagingTrees = foragingTrees;
    }

    public ArrayList<Matarsak> getMatarsaks() {
        return Matarsaks;
    }

    public void setMatarsaks(ArrayList<Matarsak> matarsaks) {
        Matarsaks = matarsaks;
    }

    public ArrayList<Quarry> getQuarrys() {
        return Quarrys;
    }

    public void setQuarrys(ArrayList<Quarry> quarrys) {
        Quarrys = quarrys;
    }

    public ArrayList<Stone> getStones() {
        return Stones;
    }

    public void setStones(ArrayList<Stone> stones) {
        Stones = stones;
    }

    public ArrayList<ForagingCrop> getForagingCrops() {
        return ForagingCrops;
    }

    public void setForagingCrops(ArrayList<ForagingCrop> foragingCrops) {
        ForagingCrops = foragingCrops;
    }

    public ArrayList<ForagingSeed> getForagingSeeds() {
        return foragingSeeds;
    }

    public void setForagingSeeds(ArrayList<ForagingSeed> foragingSeeds) {
        this.foragingSeeds = foragingSeeds;
    }

    public void createMap1() {
        //Lake+
        //GreenHouse+
        //Cottage+
        //Quarry+
        //+++++++++++++
        //ForagingTree+
        //AllTree+
        //Stone+
        //ForagingCrop+
        //ForagingSeed+
        //-------------
        Map = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            ArrayList<Kashi> row = new ArrayList<>();
            Map.add(row);
        }

        Lake Lake1 = new Lake();
        ArrayList<Cord> cords = new ArrayList<>(List.of(
                new Cord(1, 0),
                new Cord(1, 1),
                new Cord(2, 0),
                new Cord(2, 1)
        ));
        Lake1.adaptMap(cords);
        cords = new ArrayList<>(List.of(
                new Cord(4, 0), new Cord(4, 1), new Cord(4, 2), new Cord(4, 3), new Cord(4, 4), new Cord(4, 5), new Cord(4, 6),
                new Cord(5, 0), new Cord(5, 1), new Cord(5, 2), new Cord(5, 3), new Cord(5, 4), new Cord(5, 5), new Cord(5, 6),
                new Cord(6, 0), new Cord(6, 1), new Cord(6, 2), new Cord(6, 3), new Cord(6, 4), new Cord(6, 5), new Cord(6, 6),
                new Cord(7, 0), new Cord(7, 1), new Cord(7, 2), new Cord(7, 3), new Cord(7, 4), new Cord(7, 5), new Cord(7, 6),
                new Cord(8, 0), new Cord(8, 1), new Cord(8, 2), new Cord(8, 3), new Cord(8, 4), new Cord(8, 5), new Cord(8, 6),
                new Cord(9, 0), new Cord(9, 1), new Cord(9, 2), new Cord(9, 3), new Cord(9, 4), new Cord(9, 5), new Cord(9, 6)
        ));
        //Enterance is 7,3
        myGreenHouse.adaptMap(cords, 7, 3, 11, 0, 15, 4);
        cords = new ArrayList<>(List.of(
                new Cord(11, 0), new Cord(11, 1), new Cord(11, 2), new Cord(11, 3), new Cord(11, 4),
                new Cord(12, 0), new Cord(12, 1), new Cord(12, 2), new Cord(12, 3), new Cord(12, 4),
                new Cord(13, 0), new Cord(13, 1), new Cord(13, 2), new Cord(13, 3), new Cord(13, 4),
                new Cord(14, 0), new Cord(14, 1), new Cord(14, 2), new Cord(14, 3), new Cord(14, 4),
                new Cord(15, 0), new Cord(15, 1), new Cord(15, 2), new Cord(15, 3), new Cord(15, 4)
        ));
        //Enterance is 13,2
        MyCottage.adaptMap(cords, 13, 2, 17, 0, 18, 1);
        Lake Lake2 = new Lake();
        cords = new ArrayList<>(List.of(
                new Cord(17, 0),
                new Cord(17, 1),
                new Cord(18, 0),
                new Cord(18, 1)
        ));
        Lake2.adaptMap(cords);
        Quarry quarry1 = new Quarry();
        cords = new ArrayList<>(List.of(
                new Cord(20, 0),
                new Cord(20, 1),
                new Cord(21, 0),
                new Cord(21, 1)
        ));
        quarry1.adaptMap(cords, 20, 1, 20, 0, 21, 1);


        ForagingTree foragingTree1 = new ForagingTree();
        foragingTree1.setType(ForagingTreesEnums.Acorns);
        foragingTree1.adaptMap(new Cord(20, 5));
        ForagingTree foragingTree2 = new ForagingTree();
        foragingTree2.setType(ForagingTreesEnums.MahoganySeeds);
        foragingTree2.adaptMap(new Cord(22, 4));
        ForagingTree foragingTree3 = new ForagingTree();
        foragingTree3.setType(ForagingTreesEnums.PineCones);
        foragingTree3.adaptMap(new Cord(17, 15));
        ForagingTree foragingTree4 = new ForagingTree();
        foragingTree4.setType(ForagingTreesEnums.MapleSeeds);
        foragingTree4.adaptMap(new Cord(15, 10));

        AllTree AllTree1 = new AllTree();
        AllTree1.setType(AllTreesEnums.AppleTree);
        AllTree1.adaptMap(new Cord(20, 6));
        AllTree AllTree2 = new AllTree();
        AllTree2.setType(AllTreesEnums.BananaTree);
        AllTree2.adaptMap(new Cord(22, 5));
        AllTree AllTree3 = new AllTree();
        AllTree3.setType(AllTreesEnums.MangoTree);
        AllTree3.adaptMap(new Cord(17, 16));
        AllTree AllTree4 = new AllTree();
        AllTree4.setType(AllTreesEnums.OakTree);
        AllTree4.adaptMap(new Cord(15, 11));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(45, 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(45, 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(45, 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.setType(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(30, 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.setType(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(32, 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.setType(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(37, 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.setType(ForagingCropsEnums.Dandelion);
        foragingCrop4.adaptMap(new Cord(35, 10));

        ForagingSeed foragingSeed1 = new ForagingSeed();
        foragingSeed1.setType(ForagingSeedsEnums.AncientSeeds);
        foragingSeed1.adaptMap(new Cord(30, 15));
        ForagingSeed foragingSeed2 = new ForagingSeed();
        foragingSeed2.setType(ForagingSeedsEnums.BlueberrySeeds);
        foragingSeed2.adaptMap(new Cord(32, 14));
        ForagingSeed foragingSeed3 = new ForagingSeed();
        foragingSeed3.setType(ForagingSeedsEnums.BeanStarter);
        foragingSeed3.adaptMap(new Cord(37, 17));
        ForagingSeed foragingSeed4 = new ForagingSeed();
        foragingSeed4.setType(ForagingSeedsEnums.CauliflowerSeeds);
        foragingSeed4.adaptMap(new Cord(35, 13));
    }

    public void createMap2() {
        // Lake+
        // GreenHouse+ (Unchanged)
        // Cottage+ (Unchanged)
        // Quarry+
        //+++++++++++++
        // ForagingTree-
        // AllTree-
        // Stone-
        // ForagingCrop-
        // ForagingSeed-
        //-------------
        Map = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            ArrayList<Kashi> row = new ArrayList<>();
            Map.add(row);
        }

        // Adjusted Lake sizes
        Lake Lake1 = new Lake();
        ArrayList<Cord> cords = new ArrayList<>(List.of(
                new Cord(1, 0), new Cord(1, 1), new Cord(2, 0), new Cord(2, 1),
                new Cord(3, 0), new Cord(3, 1) // Expanded Lake1
        ));
        Lake1.adaptMap(cords);

        // Greenhouse (Unchanged)
        cords = new ArrayList<>(List.of(
                new Cord(4, 0), new Cord(4, 1), new Cord(4, 2), new Cord(4, 3), new Cord(4, 4),
                new Cord(4, 5), new Cord(4, 6), new Cord(5, 0), new Cord(5, 1), new Cord(5, 2),
                new Cord(5, 3), new Cord(5, 4), new Cord(5, 5), new Cord(5, 6), new Cord(6, 0),
                new Cord(6, 1), new Cord(6, 2), new Cord(6, 3), new Cord(6, 4), new Cord(6, 5),
                new Cord(6, 6), new Cord(7, 0), new Cord(7, 1), new Cord(7, 2), new Cord(7, 3),
                new Cord(7, 4), new Cord(7, 5), new Cord(7, 6), new Cord(8, 0), new Cord(8, 1),
                new Cord(8, 2), new Cord(8, 3), new Cord(8, 4), new Cord(8, 5), new Cord(8, 6),
                new Cord(9, 0), new Cord(9, 1), new Cord(9, 2), new Cord(9, 3), new Cord(9, 4),
                new Cord(9, 5), new Cord(9, 6)
        ));
        myGreenHouse.adaptMap(cords, 7, 3, 11, 0, 15, 4);

        // Cottage (Unchanged)
        cords = new ArrayList<>(List.of(
                new Cord(11, 0), new Cord(11, 1), new Cord(11, 2), new Cord(11, 3), new Cord(11, 4),
                new Cord(12, 0), new Cord(12, 1), new Cord(12, 2), new Cord(12, 3), new Cord(12, 4),
                new Cord(13, 0), new Cord(13, 1), new Cord(13, 2), new Cord(13, 3), new Cord(13, 4),
                new Cord(14, 0), new Cord(14, 1), new Cord(14, 2), new Cord(14, 3), new Cord(14, 4),
                new Cord(15, 0), new Cord(15, 1), new Cord(15, 2), new Cord(15, 3), new Cord(15, 4)
        ));
        MyCottage.adaptMap(cords, 13, 2, 17, 0, 18, 1);

        // Adjusted Lake2 sizes
        Lake Lake2 = new Lake();
        cords = new ArrayList<>(List.of(
                new Cord(17, 0), new Cord(17, 1),
                new Cord(18, 0), new Cord(18, 1),
                new Cord(19, 0), new Cord(19, 1) // Expanded Lake2
        ));
        Lake2.adaptMap(cords);

        // Adjusted Quarry sizes
        Quarry quarry1 = new Quarry();
        cords = new ArrayList<>(List.of(
                new Cord(20, 0), new Cord(20, 1), new Cord(21, 0), new Cord(21, 1),
                new Cord(22, 0) // Expanded Quarry
        ));
        quarry1.adaptMap(cords, 20, 1, 20, 0, 22, 0);

        ForagingTree foragingTree1 = new ForagingTree();
        foragingTree1.setType(ForagingTreesEnums.Acorns);
        foragingTree1.adaptMap(new Cord(20, 5));
        ForagingTree foragingTree2 = new ForagingTree();
        foragingTree2.setType(ForagingTreesEnums.MahoganySeeds);
        foragingTree2.adaptMap(new Cord(22, 4));
        ForagingTree foragingTree3 = new ForagingTree();
        foragingTree3.setType(ForagingTreesEnums.PineCones);
        foragingTree3.adaptMap(new Cord(17, 15));
        ForagingTree foragingTree4 = new ForagingTree();
        foragingTree4.setType(ForagingTreesEnums.MapleSeeds);
        foragingTree4.adaptMap(new Cord(15, 10));

        AllTree AllTree1 = new AllTree();
        AllTree1.setType(AllTreesEnums.AppleTree);
        AllTree1.adaptMap(new Cord(20, 6));
        AllTree AllTree2 = new AllTree();
        AllTree2.setType(AllTreesEnums.BananaTree);
        AllTree2.adaptMap(new Cord(22, 5));
        AllTree AllTree3 = new AllTree();
        AllTree3.setType(AllTreesEnums.MangoTree);
        AllTree3.adaptMap(new Cord(17, 16));
        AllTree AllTree4 = new AllTree();
        AllTree4.setType(AllTreesEnums.OakTree);
        AllTree4.adaptMap(new Cord(15, 11));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(45, 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(45, 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(45, 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.setType(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(30, 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.setType(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(32, 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.setType(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(37, 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.setType(ForagingCropsEnums.Dandelion);
        foragingCrop4.adaptMap(new Cord(35, 10));

        ForagingSeed foragingSeed1 = new ForagingSeed();
        foragingSeed1.setType(ForagingSeedsEnums.AncientSeeds);
        foragingSeed1.adaptMap(new Cord(30, 15));
        ForagingSeed foragingSeed2 = new ForagingSeed();
        foragingSeed2.setType(ForagingSeedsEnums.BlueberrySeeds);
        foragingSeed2.adaptMap(new Cord(32, 14));
        ForagingSeed foragingSeed3 = new ForagingSeed();
        foragingSeed3.setType(ForagingSeedsEnums.BeanStarter);
        foragingSeed3.adaptMap(new Cord(37, 17));
        ForagingSeed foragingSeed4 = new ForagingSeed();
        foragingSeed4.setType(ForagingSeedsEnums.CauliflowerSeeds);
        foragingSeed4.adaptMap(new Cord(35, 13));
    }

    public void createMap3() {
        // Lake+
        // GreenHouse+ (Unchanged)
        // Cottage+ (Unchanged)
        // Quarry+
        //+++++++++++++
        // ForagingTree-
        // AllTree-
        // Stone-
        // ForagingCrop-
        // ForagingSeed-
        //-------------
        Map = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            ArrayList<Kashi> row = new ArrayList<>();
            Map.add(row);
        }

        // Large Lake
        Lake Lake1 = new Lake();
        ArrayList<Cord> cords = new ArrayList<>(List.of(
                new Cord(1, 0), new Cord(1, 1), new Cord(1, 2), new Cord(1, 3), new Cord(1, 4),
                new Cord(2, 0), new Cord(2, 1), new Cord(2, 2), new Cord(2, 3), new Cord(2, 4),
                new Cord(3, 0), new Cord(3, 1), new Cord(3, 2), new Cord(3, 3), new Cord(3, 4)
        ));
        Lake1.adaptMap(cords);

        // Medium Lake
        Lake Lake2 = new Lake();
        cords = new ArrayList<>(List.of(
                new Cord(5, 0), new Cord(5, 1), new Cord(5, 2),
                new Cord(6, 0), new Cord(6, 1), new Cord(6, 2)
        ));
        Lake2.adaptMap(cords);

        // Small Lake
        Lake Lake3 = new Lake();
        cords = new ArrayList<>(List.of(
                new Cord(8, 0), new Cord(8, 1),
                new Cord(9, 0), new Cord(9, 1)
        ));
        Lake3.adaptMap(cords);

        // Greenhouse (Unchanged)
        cords = new ArrayList<>(List.of(
                new Cord(10, 0), new Cord(10, 1), new Cord(10, 2), new Cord(10, 3), new Cord(10, 4),
                new Cord(10, 5), new Cord(10, 6), new Cord(11, 0), new Cord(11, 1), new Cord(11, 2),
                new Cord(11, 3), new Cord(11, 4), new Cord(11, 5), new Cord(11, 6), new Cord(12, 0),
                new Cord(12, 1), new Cord(12, 2), new Cord(12, 3), new Cord(12, 4), new Cord(12, 5),
                new Cord(12, 6), new Cord(13, 0), new Cord(13, 1), new Cord(13, 2), new Cord(13, 3),
                new Cord(13, 4), new Cord(13, 5), new Cord(13, 6), new Cord(14, 0), new Cord(14, 1),
                new Cord(14, 2), new Cord(14, 3), new Cord(14, 4), new Cord(14, 5), new Cord(14, 6)
        ));
        myGreenHouse.adaptMap(cords, 12, 3, 16, 0, 18, 4);

        // Cottage (Unchanged)
        cords = new ArrayList<>(List.of(
                new Cord(16, 0), new Cord(16, 1), new Cord(16, 2), new Cord(16, 3), new Cord(16, 4),
                new Cord(17, 0), new Cord(17, 1), new Cord(17, 2), new Cord(17, 3), new Cord(17, 4),
                new Cord(18, 0), new Cord(18, 1), new Cord(18, 2), new Cord(18, 3), new Cord(18, 4),
                new Cord(19, 0), new Cord(19, 1), new Cord(19, 2), new Cord(19, 3), new Cord(19, 4),
                new Cord(20, 0), new Cord(20, 1), new Cord(20, 2), new Cord(20, 3), new Cord(20, 4)
        ));
        MyCottage.adaptMap(cords, 18, 2, 22, 0, 23, 1);

        // Additional Fishing Lake (Small)
        Lake Lake4 = new Lake();
        cords = new ArrayList<>(List.of(
                new Cord(22, 0), new Cord(22, 1),
                new Cord(23, 0), new Cord(23, 1)
        ));
        Lake4.adaptMap(cords);

        ForagingTree foragingTree1 = new ForagingTree();
        foragingTree1.setType(ForagingTreesEnums.Acorns);
        foragingTree1.adaptMap(new Cord(20, 5));
        ForagingTree foragingTree2 = new ForagingTree();
        foragingTree2.setType(ForagingTreesEnums.MahoganySeeds);
        foragingTree2.adaptMap(new Cord(22, 4));
        ForagingTree foragingTree3 = new ForagingTree();
        foragingTree3.setType(ForagingTreesEnums.PineCones);
        foragingTree3.adaptMap(new Cord(17, 15));
        ForagingTree foragingTree4 = new ForagingTree();
        foragingTree4.setType(ForagingTreesEnums.MapleSeeds);
        foragingTree4.adaptMap(new Cord(15, 10));

        AllTree AllTree1 = new AllTree();
        AllTree1.setType(AllTreesEnums.AppleTree);
        AllTree1.adaptMap(new Cord(20, 6));
        AllTree AllTree2 = new AllTree();
        AllTree2.setType(AllTreesEnums.BananaTree);
        AllTree2.adaptMap(new Cord(22, 5));
        AllTree AllTree3 = new AllTree();
        AllTree3.setType(AllTreesEnums.MangoTree);
        AllTree3.adaptMap(new Cord(17, 16));
        AllTree AllTree4 = new AllTree();
        AllTree4.setType(AllTreesEnums.OakTree);
        AllTree4.adaptMap(new Cord(15, 11));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(45, 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(45, 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(45, 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.setType(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(30, 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.setType(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(32, 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.setType(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(37, 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.setType(ForagingCropsEnums.Dandelion);
        foragingCrop4.adaptMap(new Cord(35, 10));

        ForagingSeed foragingSeed1 = new ForagingSeed();
        foragingSeed1.setType(ForagingSeedsEnums.AncientSeeds);
        foragingSeed1.adaptMap(new Cord(30, 15));
        ForagingSeed foragingSeed2 = new ForagingSeed();
        foragingSeed2.setType(ForagingSeedsEnums.BlueberrySeeds);
        foragingSeed2.adaptMap(new Cord(32, 14));
        ForagingSeed foragingSeed3 = new ForagingSeed();
        foragingSeed3.setType(ForagingSeedsEnums.BeanStarter);
        foragingSeed3.adaptMap(new Cord(37, 17));
        ForagingSeed foragingSeed4 = new ForagingSeed();
        foragingSeed4.setType(ForagingSeedsEnums.CauliflowerSeeds);
        foragingSeed4.adaptMap(new Cord(35, 13));
    }

    public void createMap4() {
        // Lake+
        // GreenHouse+ (Unchanged)
        // Cottage+ (Unchanged)
        // Quarry+
        //+++++++++++++
        // ForagingTree-
        // AllTree-
        // Stone-
        // ForagingCrop-
        // ForagingSeed-
        //-------------
        Map = new ArrayList<>(50);
        for (int i = 0; i < 50; i++) {
            ArrayList<Kashi> row = new ArrayList<>();
            Map.add(row);
        }

        // Small Lake
        Lake Lake1 = new Lake();
        ArrayList<Cord> cords = new ArrayList<>(List.of(
                new Cord(1, 0), new Cord(1, 1),
                new Cord(2, 0), new Cord(2, 1)
        ));
        Lake1.adaptMap(cords);

        // Greenhouse (Unchanged)
        cords = new ArrayList<>(List.of(
                new Cord(4, 0), new Cord(4, 1), new Cord(4, 2), new Cord(4, 3), new Cord(4, 4),
                new Cord(4, 5), new Cord(4, 6), new Cord(5, 0), new Cord(5, 1), new Cord(5, 2),
                new Cord(5, 3), new Cord(5, 4), new Cord(5, 5), new Cord(5, 6), new Cord(6, 0),
                new Cord(6, 1), new Cord(6, 2), new Cord(6, 3), new Cord(6, 4), new Cord(6, 5),
                new Cord(6, 6), new Cord(7, 0), new Cord(7, 1), new Cord(7, 2), new Cord(7, 3),
                new Cord(7, 4), new Cord(7, 5), new Cord(7, 6), new Cord(8, 0), new Cord(8, 1),
                new Cord(8, 2), new Cord(8, 3), new Cord(8, 4), new Cord(8, 5), new Cord(8, 6),
                new Cord(9, 0), new Cord(9, 1), new Cord(9, 2), new Cord(9, 3), new Cord(9, 4),
                new Cord(9, 5), new Cord(9, 6)
        ));
        myGreenHouse.adaptMap(cords, 7, 3, 11, 0, 15, 4);

        // Cottage (Unchanged)
        cords = new ArrayList<>(List.of(
                new Cord(11, 0), new Cord(11, 1), new Cord(11, 2), new Cord(11, 3), new Cord(11, 4),
                new Cord(12, 0), new Cord(12, 1), new Cord(12, 2), new Cord(12, 3), new Cord(12, 4),
                new Cord(13, 0), new Cord(13, 1), new Cord(13, 2), new Cord(13, 3), new Cord(13, 4),
                new Cord(14, 0), new Cord(14, 1), new Cord(14, 2), new Cord(14, 3), new Cord(14, 4),
                new Cord(15, 0), new Cord(15, 1), new Cord(15, 2), new Cord(15, 3), new Cord(15, 4)
        ));
        MyCottage.adaptMap(cords, 13, 2, 17, 0, 18, 1);

        // Large Quarry
        Quarry quarry1 = new Quarry();
        cords = new ArrayList<>(List.of(
                new Cord(17, 0), new Cord(17, 1), new Cord(17, 2), new Cord(17, 3),
                new Cord(18, 0), new Cord(18, 1), new Cord(18, 2), new Cord(18, 3),
                new Cord(19, 0), new Cord(19, 1), new Cord(19, 2), new Cord(19, 3)
        ));
        quarry1.adaptMap(cords, 18, 1, 17, 0, 19, 3);

        // Medium Quarry
        Quarry quarry2 = new Quarry();
        cords = new ArrayList<>(List.of(
                new Cord(20, 0), new Cord(20, 1), new Cord(20, 2),
                new Cord(21, 0), new Cord(21, 1), new Cord(21, 2)
        ));
        quarry2.adaptMap(cords, 21, 1, 20, 0, 21, 2);

        // Small Quarry
        Quarry quarry3 = new Quarry();
        cords = new ArrayList<>(List.of(
                new Cord(23, 0), new Cord(23, 1),
                new Cord(24, 0), new Cord(24, 1)
        ));
        quarry3.adaptMap(cords, 23, 1, 23, 0, 24, 1);

        ForagingTree foragingTree1 = new ForagingTree();
        foragingTree1.setType(ForagingTreesEnums.Acorns);
        foragingTree1.adaptMap(new Cord(20, 5));
        ForagingTree foragingTree2 = new ForagingTree();
        foragingTree2.setType(ForagingTreesEnums.MahoganySeeds);
        foragingTree2.adaptMap(new Cord(22, 4));
        ForagingTree foragingTree3 = new ForagingTree();
        foragingTree3.setType(ForagingTreesEnums.PineCones);
        foragingTree3.adaptMap(new Cord(17, 15));
        ForagingTree foragingTree4 = new ForagingTree();
        foragingTree4.setType(ForagingTreesEnums.MapleSeeds);
        foragingTree4.adaptMap(new Cord(15, 10));

        AllTree AllTree1 = new AllTree();
        AllTree1.setType(AllTreesEnums.AppleTree);
        AllTree1.adaptMap(new Cord(20, 6));
        AllTree AllTree2 = new AllTree();
        AllTree2.setType(AllTreesEnums.BananaTree);
        AllTree2.adaptMap(new Cord(22, 5));
        AllTree AllTree3 = new AllTree();
        AllTree3.setType(AllTreesEnums.MangoTree);
        AllTree3.adaptMap(new Cord(17, 16));
        AllTree AllTree4 = new AllTree();
        AllTree4.setType(AllTreesEnums.OakTree);
        AllTree4.adaptMap(new Cord(15, 11));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(45, 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(45, 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(45, 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.setType(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(30, 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.setType(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(32, 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.setType(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(37, 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.setType(ForagingCropsEnums.Dandelion);
        foragingCrop4.adaptMap(new Cord(35, 10));

        ForagingSeed foragingSeed1 = new ForagingSeed();
        foragingSeed1.setType(ForagingSeedsEnums.AncientSeeds);
        foragingSeed1.adaptMap(new Cord(30, 15));
        ForagingSeed foragingSeed2 = new ForagingSeed();
        foragingSeed2.setType(ForagingSeedsEnums.BlueberrySeeds);
        foragingSeed2.adaptMap(new Cord(32, 14));
        ForagingSeed foragingSeed3 = new ForagingSeed();
        foragingSeed3.setType(ForagingSeedsEnums.BeanStarter);
        foragingSeed3.adaptMap(new Cord(37, 17));
        ForagingSeed foragingSeed4 = new ForagingSeed();
        foragingSeed4.setType(ForagingSeedsEnums.CauliflowerSeeds);
        foragingSeed4.adaptMap(new Cord(35, 13));
    }

}
