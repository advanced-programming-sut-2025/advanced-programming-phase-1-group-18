package Model;

import java.util.ArrayList;
import java.util.List;

import Model.Items.*;
import enums.*;

public class Farm {

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
    protected Satl satl;
    protected Tavileh myTavileh;
    protected BigBarn myBigBarn;
    protected DeluxeBarn myDeluxeBarn;
    protected Cage myCage;
    protected BigCoop myBigCoop;
    protected DeluxeCoop myDeluxeCoop;
    protected Well myWell;
    protected ShippingBin myShippingBin;

    public Well getMyWell() {
        return myWell;
    }

    public void setMyWell(Well myWell) {
        this.myWell = myWell;
    }

    public ShippingBin getMyShippingBin() {
        return myShippingBin;
    }

    public void setMyShippingBin(ShippingBin myShippingBin) {
        this.myShippingBin = myShippingBin;
    }

    public DeluxeCoop getMyDeluxeCoop() {
        return myDeluxeCoop;
    }

    public void setMyDeluxeCoop(DeluxeCoop myDeluxeCoop) {
        this.myDeluxeCoop = myDeluxeCoop;
    }

    public BigCoop getMyBigCoop() {
        return myBigCoop;
    }

    public void setMyBigCoop(BigCoop myBigCoop) {
        this.myBigCoop = myBigCoop;
    }

    public Cage getMyCage() {
        return myCage;
    }

    public void setMyCage(Cage myCage) {
        this.myCage = myCage;
    }

    public BigBarn getMyBigBarn() {
        return myBigBarn;
    }

    public void setMyBigBarn(BigBarn myBigBarn) {
        this.myBigBarn = myBigBarn;
    }

    public DeluxeBarn getMyDeluxeBarn() {
        return myDeluxeBarn;
    }

    public void setMyDeluxeBarn(DeluxeBarn myDeluxeBarn) {
        this.myDeluxeBarn = myDeluxeBarn;
    }

    public Tavileh getMyTavileh() {
        return myTavileh;
    }

    public void setMyTavileh(Tavileh myTavileh) {
        this.myTavileh = myTavileh;
    }

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

    public Satl getSatl() {
        return satl;
    }

    public void setSatl(Satl satl) {
        this.satl = satl;
    }

    public void createMap1(int topleftx, int toplefty) {
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
        //initialize map size
        Lake Lake1 = new Lake();
        ArrayList<Cord> cords = new ArrayList<>(List.of(
                new Cord(topleftx + 1, toplefty + 0),
                new Cord(topleftx + 1, toplefty + 1),
                new Cord(topleftx + 2, toplefty + 0),
                new Cord(topleftx + 2, toplefty + 1)
        ));
        Lake1.adaptMap(cords);

        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 4, toplefty + 0),
                new Cord(topleftx + 4, toplefty + 1),
                new Cord(topleftx + 4, toplefty + 2),
                new Cord(topleftx + 4, toplefty + 3),
                new Cord(topleftx + 4, toplefty + 4),
                new Cord(topleftx + 4, toplefty + 5),
                new Cord(topleftx + 4, toplefty + 6),
                new Cord(topleftx + 5, toplefty + 0),
                new Cord(topleftx + 5, toplefty + 1),
                new Cord(topleftx + 5, toplefty + 2),
                new Cord(topleftx + 5, toplefty + 3),
                new Cord(topleftx + 5, toplefty + 4),
                new Cord(topleftx + 5, toplefty + 5),
                new Cord(topleftx + 5, toplefty + 6),
                new Cord(topleftx + 6, toplefty + 0),
                new Cord(topleftx + 6, toplefty + 1),
                new Cord(topleftx + 6, toplefty + 2),
                new Cord(topleftx + 6, toplefty + 3),
                new Cord(topleftx + 6, toplefty + 4),
                new Cord(topleftx + 6, toplefty + 5),
                new Cord(topleftx + 6, toplefty + 6),
                new Cord(topleftx + 7, toplefty + 0),
                new Cord(topleftx + 7, toplefty + 1),
                new Cord(topleftx + 7, toplefty + 2),
                new Cord(topleftx + 7, toplefty + 3),
                new Cord(topleftx + 7, toplefty + 4),
                new Cord(topleftx + 7, toplefty + 5),
                new Cord(topleftx + 7, toplefty + 6),
                new Cord(topleftx + 8, toplefty + 0),
                new Cord(topleftx + 8, toplefty + 1),
                new Cord(topleftx + 8, toplefty + 2),
                new Cord(topleftx + 8, toplefty + 3),
                new Cord(topleftx + 8, toplefty + 4),
                new Cord(topleftx + 8, toplefty + 5),
                new Cord(topleftx + 8, toplefty + 6),
                new Cord(topleftx + 9, toplefty + 0),
                new Cord(topleftx + 9, toplefty + 1),
                new Cord(topleftx + 9, toplefty + 2),
                new Cord(topleftx + 9, toplefty + 3),
                new Cord(topleftx + 9, toplefty + 4),
                new Cord(topleftx + 9, toplefty + 5),
                new Cord(topleftx + 9, toplefty + 6)
        ));
        //Enterance is 7,3
        myGreenHouse.adaptMap(cords, topleftx + 7, toplefty + 3, topleftx + 11, toplefty + 0, topleftx + 15, toplefty + 4);

        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 11, toplefty + 0),
                new Cord(topleftx + 11, toplefty + 1),
                new Cord(topleftx + 11, toplefty + 2),
                new Cord(topleftx + 11, toplefty + 3),
                new Cord(topleftx + 11, toplefty + 4),
                new Cord(topleftx + 12, toplefty + 0),
                new Cord(topleftx + 12, toplefty + 1),
                new Cord(topleftx + 12, toplefty + 2),
                new Cord(topleftx + 12, toplefty + 3),
                new Cord(topleftx + 12, toplefty + 4),
                new Cord(topleftx + 13, toplefty + 0),
                new Cord(topleftx + 13, toplefty + 1),
                new Cord(topleftx + 13, toplefty + 2),
                new Cord(topleftx + 13, toplefty + 3),
                new Cord(topleftx + 13, toplefty + 4),
                new Cord(topleftx + 14, toplefty + 0),
                new Cord(topleftx + 14, toplefty + 1),
                new Cord(topleftx + 14, toplefty + 2),
                new Cord(topleftx + 14, toplefty + 3),
                new Cord(topleftx + 14, toplefty + 4),
                new Cord(topleftx + 15, toplefty + 0),
                new Cord(topleftx + 15, toplefty + 1),
                new Cord(topleftx + 15, toplefty + 2),
                new Cord(topleftx + 15, toplefty + 3),
                new Cord(topleftx + 15, toplefty + 4)
        ));
        //Enterance is 13,2
        MyCottage = new Cottage();
        MyCottage.adaptMap(cords, topleftx + 13, toplefty + 2, topleftx + 17, toplefty + 0, topleftx + 18, toplefty + 1);

        Lake Lake2 = new Lake();
        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 17, toplefty + 0),
                new Cord(topleftx + 17, toplefty + 1),
                new Cord(topleftx + 18, toplefty + 0),
                new Cord(topleftx + 18, toplefty + 1)
        ));
        Lake2.adaptMap(cords);

        Quarry quarry1 = new Quarry();
        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 20, toplefty + 0),
                new Cord(topleftx + 20, toplefty + 1),
                new Cord(topleftx + 21, toplefty + 0),
                new Cord(topleftx + 21, toplefty + 1)
        ));
        quarry1.adaptMap(cords, topleftx + 20, toplefty + 1, topleftx + 20, toplefty + 0, topleftx + 21, toplefty + 1);

        ForagingTree foragingTree1 = new ForagingTree();
        foragingTree1.setType(ForagingTreesEnums.Acorns);
        foragingTree1.adaptMap(new Cord(topleftx + 20, toplefty + 5));
        ForagingTree foragingTree2 = new ForagingTree();
        foragingTree2.setType(ForagingTreesEnums.MahoganySeeds);
        foragingTree2.adaptMap(new Cord(topleftx + 22, toplefty + 4));
        ForagingTree foragingTree3 = new ForagingTree();
        foragingTree3.setType(ForagingTreesEnums.PineCones);
        foragingTree3.adaptMap(new Cord(topleftx + 17, toplefty + 15));
        ForagingTree foragingTree4 = new ForagingTree();
        foragingTree4.setType(ForagingTreesEnums.MapleSeeds);
        foragingTree4.adaptMap(new Cord(topleftx + 15, toplefty + 10));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(topleftx + 45, toplefty + 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(topleftx + 45, toplefty + 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(topleftx + 45, toplefty + 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.initilizeCrop(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(topleftx + 30, toplefty + 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.initilizeCrop(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(topleftx + 32, toplefty + 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.initilizeCrop(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(topleftx + 37, toplefty + 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.initilizeCrop(ForagingCropsEnums.Dandelion);
        foragingCrop4.adaptMap(new Cord(topleftx + 35, toplefty + 10));

        ForagingSeed foragingSeed1 = new ForagingSeed();
        foragingSeed1.setType(ForagingSeedsEnums.AncientSeeds);
        foragingSeed1.adaptMap(new Cord(topleftx + 30, toplefty + 15));
        ForagingSeed foragingSeed2 = new ForagingSeed();
        foragingSeed2.setType(ForagingSeedsEnums.BlueberrySeeds);
        foragingSeed2.adaptMap(new Cord(topleftx + 32, toplefty + 14));
        ForagingSeed foragingSeed3 = new ForagingSeed();
        foragingSeed3.setType(ForagingSeedsEnums.BeanStarter);
        foragingSeed3.adaptMap(new Cord(topleftx + 37, toplefty + 17));
        ForagingSeed foragingSeed4 = new ForagingSeed();
        foragingSeed4.setType(ForagingSeedsEnums.CauliflowerSeeds);
        foragingSeed4.adaptMap(new Cord(topleftx + 35, toplefty + 13));

        topleftx = 0;
        toplefty = 0;

        // Blacksmith Shop
        cords = new ArrayList<>();
        for (int i = topleftx + App.getCurrentGame().getBlackSmithTopLeftx(); i < topleftx + App.getCurrentGame().getBlackSmithTopLeftx() + App.getCurrentGame().getBlackSmithWidth(); i++) {
            for (int j = toplefty + App.getCurrentGame().getBlackSmithTopLefty(); j < toplefty + App.getCurrentGame().getBlackSmithTopLefty() + App.getCurrentGame().getBlackSmithHeight(); j++) {
                cords.add(new Cord(i, j));
            }
        }
        App.getCurrentGame().setBlackSmithMarket(new BlackSmithMarket());
        App.getCurrentGame().getBlackSmithMarket().fillStock();
        App.getCurrentGame().getBlackSmithMarket().adaptMap(
                cords,
                topleftx + App.getCurrentGame().getBlackSmithEnterancex(),
                toplefty + App.getCurrentGame().getBlackSmithEnterancey(),
                topleftx + App.getCurrentGame().getBlackSmithTopLeftx(),
                toplefty + App.getCurrentGame().getBlackSmithTopLefty(),
                topleftx + App.getCurrentGame().getBlackSmithTopLeftx() + App.getCurrentGame().getBlackSmithWidth(),
                toplefty + App.getCurrentGame().getBlackSmithTopLefty() + App.getCurrentGame().getBlackSmithHeight()
        );

        //Carpenter's Shop
        cords = new ArrayList<>();
        for (int i = topleftx + App.getCurrentGame().getCarpentersShopTopLeftx(); i < topleftx + App.getCurrentGame().getCarpentersShopTopLeftx() + App.getCurrentGame().getCarpentersShopWidth(); i++) {
            for (int j = toplefty + App.getCurrentGame().getCarpentersShopTopLefty(); j < toplefty + App.getCurrentGame().getCarpentersShopTopLefty() + App.getCurrentGame().getCarpentersShopHeight(); j++) {
                cords.add(new Cord(i, j));
            }
        }
        App.getCurrentGame().setCarpentersShopMarket(new CarpentersShopMarket());
        App.getCurrentGame().getCarpentersShopMarket().fillStock();
        App.getCurrentGame().getCarpentersShopMarket().adaptMap(
                cords,
                topleftx + App.getCurrentGame().getCarpentersShopEnterancex(),
                toplefty + App.getCurrentGame().getCarpentersShopEnterancey(),
                topleftx + App.getCurrentGame().getCarpentersShopTopLeftx(),
                toplefty + App.getCurrentGame().getCarpentersShopTopLefty(),
                topleftx + App.getCurrentGame().getCarpentersShopTopLeftx() + App.getCurrentGame().getCarpentersShopWidth(),
                toplefty + App.getCurrentGame().getCarpentersShopTopLefty() + App.getCurrentGame().getCarpentersShopHeight()
        );

        // Fish Shop
        cords = new ArrayList<>();
        for (int i = topleftx + App.getCurrentGame().getFishShopTopLeftx(); i < topleftx + App.getCurrentGame().getFishShopTopLeftx() + App.getCurrentGame().getFishShopWidth(); i++) {
            for (int j = toplefty + App.getCurrentGame().getFishShopTopLefty(); j < toplefty + App.getCurrentGame().getFishShopTopLefty() + App.getCurrentGame().getFishShopHeight(); j++) {
                cords.add(new Cord(i, j));
            }
        }
        App.getCurrentGame().setFishShopMarket(new FishShopMarket());
        App.getCurrentGame().getFishShopMarket().fillStock();
        App.getCurrentGame().getFishShopMarket().adaptMap(
                cords,
                topleftx + App.getCurrentGame().getFishShopEnterancex(),
                toplefty + App.getCurrentGame().getFishShopEnterancey(),
                topleftx + App.getCurrentGame().getFishShopTopLeftx(),
                toplefty + App.getCurrentGame().getFishShopTopLefty(),
                topleftx + App.getCurrentGame().getFishShopTopLeftx() + App.getCurrentGame().getFishShopWidth(),
                toplefty + App.getCurrentGame().getFishShopTopLefty() + App.getCurrentGame().getFishShopHeight()
        );
        // JojaMart
        cords = new ArrayList<>();
        for (int i = topleftx + App.getCurrentGame().getJojoMartTopLeftx(); i < topleftx + App.getCurrentGame().getJojoMartTopLeftx() + App.getCurrentGame().getJojoMartWidth(); i++) {
            for (int j = toplefty + App.getCurrentGame().getJojoMartTopLefty(); j < toplefty + App.getCurrentGame().getJojoMartTopLefty() + App.getCurrentGame().getJojoMartHeight(); j++) {
                cords.add(new Cord(i, j));
            }
        }
        App.getCurrentGame().setJojoMartMarket(new JojoMartMarket());
        App.getCurrentGame().getJojoMartMarket().fillStock(App.getCurrentGame().getCurrentSeason());
        App.getCurrentGame().getJojoMartMarket().adaptMap(
                cords,
                topleftx + App.getCurrentGame().getJojoMartEnterancex(),
                toplefty + App.getCurrentGame().getJojoMartEnterancey(),
                topleftx + App.getCurrentGame().getJojoMartTopLeftx(),
                toplefty + App.getCurrentGame().getJojoMartTopLefty(),
                topleftx + App.getCurrentGame().getJojoMartTopLeftx() + App.getCurrentGame().getJojoMartWidth(),
                toplefty + App.getCurrentGame().getJojoMartTopLefty() + App.getCurrentGame().getJojoMartHeight()
        );

        // Marnie's Ranch
        cords = new ArrayList<>();
        for (int i = topleftx + App.getCurrentGame().getMarniesRanchTopLeftx(); i < topleftx + App.getCurrentGame().getMarniesRanchTopLeftx() + App.getCurrentGame().getMarniesRanchWidth(); i++) {
            for (int j = toplefty + App.getCurrentGame().getMarniesRanchTopLefty(); j < toplefty + App.getCurrentGame().getMarniesRanchTopLefty() + App.getCurrentGame().getMarniesRanchHeight(); j++) {
                cords.add(new Cord(i, j));
            }
        }
        App.getCurrentGame().setMarniesRanchMarket(new MarniesRanchMarket());
        App.getCurrentGame().getMarniesRanchMarket().fillStock();
        App.getCurrentGame().getMarniesRanchMarket().adaptMap(
                cords,
                topleftx + App.getCurrentGame().getMarniesRanchEnterancex(),
                toplefty + App.getCurrentGame().getMarniesRanchEnterancey(),
                topleftx + App.getCurrentGame().getMarniesRanchTopLeftx(),
                toplefty + App.getCurrentGame().getMarniesRanchTopLefty(),
                topleftx + App.getCurrentGame().getMarniesRanchTopLeftx() + App.getCurrentGame().getMarniesRanchWidth(),
                toplefty + App.getCurrentGame().getMarniesRanchTopLefty() + App.getCurrentGame().getMarniesRanchHeight()
        );

        // Pierre's General Store
        cords = new ArrayList<>();
        for (int i = topleftx + App.getCurrentGame().getPierresGeneralStoreTopLeftx(); i < topleftx + App.getCurrentGame().getPierresGeneralStoreTopLeftx() + App.getCurrentGame().getPierresGeneralStoreWidth(); i++) {
            for (int j = toplefty + App.getCurrentGame().getPierresGeneralStoreTopLefty(); j < toplefty + App.getCurrentGame().getPierresGeneralStoreTopLefty() + App.getCurrentGame().getPierresGeneralStoreHeight(); j++) {
                cords.add(new Cord(i, j));
            }
        }
        App.getCurrentGame().setPierresGeneralStoreMarket(new PierresGeneralStoreMarket());
        App.getCurrentGame().getPierresGeneralStoreMarket().fillStock(App.getCurrentGame().getCurrentSeason());
        App.getCurrentGame().getPierresGeneralStoreMarket().adaptMap(
                cords,
                topleftx + App.getCurrentGame().getPierresGeneralStoreEnterancex(),
                toplefty + App.getCurrentGame().getPierresGeneralStoreEnterancey(),
                topleftx + App.getCurrentGame().getPierresGeneralStoreTopLeftx(),
                toplefty + App.getCurrentGame().getPierresGeneralStoreTopLefty(),
                topleftx + App.getCurrentGame().getPierresGeneralStoreTopLeftx() + App.getCurrentGame().getPierresGeneralStoreWidth(),
                toplefty + App.getCurrentGame().getPierresGeneralStoreTopLefty() + App.getCurrentGame().getPierresGeneralStoreHeight()
        );

        // Stardrop Saloon
        cords = new ArrayList<>();
        for (int i = topleftx + App.getCurrentGame().getTheStardropSaloonTopLeftx(); i < topleftx + App.getCurrentGame().getTheStardropSaloonTopLeftx() + App.getCurrentGame().getTheStardropSaloonWidth(); i++) {
            for (int j = toplefty + App.getCurrentGame().getTheStardropSaloonTopLefty(); j < toplefty + App.getCurrentGame().getTheStardropSaloonTopLefty() + App.getCurrentGame().getTheStardropSaloonHeight(); j++) {
                cords.add(new Cord(i, j));
            }
        }
        App.getCurrentGame().setTheStardropSaloonMarket(new TheStardropSaloonMarket());
        App.getCurrentGame().getTheStardropSaloonMarket().fillStock();
        App.getCurrentGame().getTheStardropSaloonMarket().adaptMap(
                cords,
                topleftx + App.getCurrentGame().getTheStardropSaloonEnterancex(),
                toplefty + App.getCurrentGame().getTheStardropSaloonEnterancey(),
                topleftx + App.getCurrentGame().getTheStardropSaloonTopLeftx(),
                toplefty + App.getCurrentGame().getTheStardropSaloonTopLefty(),
                topleftx + App.getCurrentGame().getTheStardropSaloonTopLeftx() + App.getCurrentGame().getTheStardropSaloonWidth(),
                toplefty + App.getCurrentGame().getTheStardropSaloonTopLefty() + App.getCurrentGame().getTheStardropSaloonHeight()
        );

        App.getCurrentGame().setNPCSEBASTIAN(new NPC(NPCEnums.SEBASTIAN));
        App.getCurrentGame().getNPCSEBASTIAN().adaptMap(new Cord(topleftx + App.getCurrentGame().getNPCSEBASTIANx(), toplefty + App.getCurrentGame().getNPCSEBASTIANy()));

        App.getCurrentGame().setNPCABIGAIL(new NPC(NPCEnums.ABIGAIL));
        App.getCurrentGame().getNPCABIGAIL().adaptMap(new Cord(topleftx + App.getCurrentGame().getNPCABIGAILx(), toplefty + App.getCurrentGame().getNPCABIGAILy()));

        App.getCurrentGame().setNPCHARVEY(new NPC(NPCEnums.HARVEY));
        App.getCurrentGame().getNPCHARVEY().adaptMap(new Cord(topleftx + App.getCurrentGame().getNPCHARVEYx(), toplefty + App.getCurrentGame().getNPCHARVEYy()));

        App.getCurrentGame().setNPCLEAH(new NPC(NPCEnums.LEAH));
        App.getCurrentGame().getNPCLEAH().adaptMap(new Cord(topleftx + App.getCurrentGame().getNPCLEAHx(), toplefty + App.getCurrentGame().getNPCLEAHy()));

        App.getCurrentGame().setNPCROBIN(new NPC(NPCEnums.ROBIN));
        App.getCurrentGame().getNPCROBIN().adaptMap(new Cord(topleftx + App.getCurrentGame().getNPCROBINx(), toplefty + App.getCurrentGame().getNPCROBINy()));
    }

    public void createMap2(int topleft, int topright) {
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
//        Map = new ArrayList<>(50);
//        for (int i = 0; i < 50; i++) {
//            ArrayList<Kashi> row = new ArrayList<>();
//            Map.add(row);
//        }

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

//        AllTree AllTree1 = new AllTree();
//        AllTree1.setType(AllTreesEnums.AppleTree);
//        AllTree1.adaptMap(new Cord(20, 6));
//        AllTree AllTree2 = new AllTree();
//        AllTree2.setType(AllTreesEnums.BananaTree);
//        AllTree2.adaptMap(new Cord(22, 5));
//        AllTree AllTree3 = new AllTree();
//        AllTree3.setType(AllTreesEnums.MangoTree);
//        AllTree3.adaptMap(new Cord(17, 16));
//        AllTree AllTree4 = new AllTree();
//        AllTree4.setType(AllTreesEnums.OakTree);
//        AllTree4.adaptMap(new Cord(15, 11));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(45, 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(45, 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(45, 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.initilizeCrop(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(30, 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.initilizeCrop(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(32, 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.initilizeCrop(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(37, 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.initilizeCrop(ForagingCropsEnums.Dandelion);
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

    public void createMap3(int topleft, int topright) {
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
//        Map = new ArrayList<>(50);
//        for (int i = 0; i < 50; i++) {
//            ArrayList<Kashi> row = new ArrayList<>();
//            Map.add(row);
//        }

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

//        AllTree AllTree1 = new AllTree();
//        AllTree1.setType(AllTreesEnums.AppleTree);
//        AllTree1.adaptMap(new Cord(20, 6));
//        AllTree AllTree2 = new AllTree();
//        AllTree2.setType(AllTreesEnums.BananaTree);
//        AllTree2.adaptMap(new Cord(22, 5));
//        AllTree AllTree3 = new AllTree();
//        AllTree3.setType(AllTreesEnums.MangoTree);
//        AllTree3.adaptMap(new Cord(17, 16));
//        AllTree AllTree4 = new AllTree();
//        AllTree4.setType(AllTreesEnums.OakTree);
//        AllTree4.adaptMap(new Cord(15, 11));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(45, 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(45, 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(45, 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.initilizeCrop(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(30, 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.initilizeCrop(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(32, 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.initilizeCrop(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(37, 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.initilizeCrop(ForagingCropsEnums.Dandelion);
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

    public void createMap4(int topleft, int topright) {
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
//        Map = new ArrayList<>(50);
//        for (int i = 0; i < 50; i++) {
//            ArrayList<Kashi> row = new ArrayList<>();
//            Map.add(row);
//        }

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

//        AllTree AllTree1 = new AllTree();
//        AllTree1.setType(AllTreesEnums.AppleTree);
//        AllTree1.adaptMap(new Cord(20, 6));
//        AllTree AllTree2 = new AllTree();
//        AllTree2.setType(AllTreesEnums.BananaTree);
//        AllTree2.adaptMap(new Cord(22, 5));
//        AllTree AllTree3 = new AllTree();
//        AllTree3.setType(AllTreesEnums.MangoTree);
//        AllTree3.adaptMap(new Cord(17, 16));
//        AllTree AllTree4 = new AllTree();
//        AllTree4.setType(AllTreesEnums.OakTree);
//        AllTree4.adaptMap(new Cord(15, 11));

        Stone stone1 = new Stone();
        stone1.adaptMap(new Cord(45, 11));
        Stone stone2 = new Stone();
        stone2.adaptMap(new Cord(45, 12));
        Stone stone3 = new Stone();
        stone3.adaptMap(new Cord(45, 13));

        ForagingCrop foragingCrop1 = new ForagingCrop();
        foragingCrop1.initilizeCrop(ForagingCropsEnums.SpiceBerry);
        foragingCrop1.adaptMap(new Cord(30, 5));
        ForagingCrop foragingCrop2 = new ForagingCrop();
        foragingCrop2.initilizeCrop(ForagingCropsEnums.CommonMushroom);
        foragingCrop2.adaptMap(new Cord(32, 4));
        ForagingCrop foragingCrop3 = new ForagingCrop();
        foragingCrop3.initilizeCrop(ForagingCropsEnums.Crocus);
        foragingCrop3.adaptMap(new Cord(37, 15));
        ForagingCrop foragingCrop4 = new ForagingCrop();
        foragingCrop4.initilizeCrop(ForagingCropsEnums.Dandelion);
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
