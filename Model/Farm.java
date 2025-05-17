package Model;

import java.util.*;

import Model.Items.*;
import enums.*;

//TODO SATL SPAWN AND MAP 2 3 4
public class Farm {

    protected ArrayList<Lake> Lakes = new ArrayList<>();
    protected GreenHouse myGreenHouse;
    //protected Cottage myCottage;
    protected Quarry myQuarry;
    protected ArrayList<Cage> Cages = new ArrayList<>();
    protected ArrayList<Tavileh> Tavilehs;
    protected ArrayList<AllTree> AllTrees = new ArrayList<>();
    protected ArrayList<CageAnimal> CageAnimals = new ArrayList<>();
    protected Cottage MyCottage;
    protected ArrayList<ForagingTree> ForagingTrees;
    protected GreenHouse MyGreenHouse;
    protected ArrayList<Matarsak> Matarsaks = new ArrayList<>();
    protected ArrayList<Quarry> Quarrys;
    protected ArrayList<Stone> Stones;
    protected ArrayList<ForagingCrop> ForagingCrops;
    protected ArrayList<AllCrop> AllCrops = new ArrayList<>();
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

    private double distance(Cord a, Cord b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    private Cord getRandomPosition(int topleftx, int toplefty, int mapWidth, int mapHeight,
                                   Set<Cord> occupied, int minDistance) {
        Random random = new Random();
        while (true) {
            int x = topleftx + random.nextInt(mapWidth);
            int y = toplefty + random.nextInt(mapHeight);
            Cord candidate = new Cord(x, y);

            boolean valid = true;
            for (Cord occupiedPos : occupied) {
                if (distance(candidate, occupiedPos) < minDistance) {
                    valid = false;
                    break;
                }
            }

            if (valid && !occupied.contains(candidate)) {
                return candidate;
            }
        }
    }

    public void initializeMarketsAndNPCs(int topleftx, int toplefty) {
        // Continue with markets and NPCs (unchanged from your original code)
        topleftx = 0;
        toplefty = 0;

        // Blacksmith Shop
        ArrayList<Cord> cords = new ArrayList<>();
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
        App.getCurrentGame().getJojoMartMarket().fillStock(App.getCurrentGame().getCurrentDateTime().getSeason());
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
        App.getCurrentGame().getNPCSEBASTIAN().adaptMap(new Cord(App.getCurrentGame().getNPCSEBASTIANx(), +App.getCurrentGame().getNPCSEBASTIANy()));

        App.getCurrentGame().setNPCABIGAIL(new NPC(NPCEnums.ABIGAIL));
        App.getCurrentGame().getNPCABIGAIL().adaptMap(new Cord(App.getCurrentGame().getNPCABIGAILx(), +App.getCurrentGame().getNPCABIGAILy()));

        App.getCurrentGame().setNPCHARVEY(new NPC(NPCEnums.HARVEY));
        App.getCurrentGame().getNPCHARVEY().adaptMap(new Cord(App.getCurrentGame().getNPCHARVEYx(), App.getCurrentGame().getNPCHARVEYy()));

        App.getCurrentGame().setNPCLEAH(new NPC(NPCEnums.LEAH));
        App.getCurrentGame().getNPCLEAH().adaptMap(new Cord(App.getCurrentGame().getNPCLEAHx(), App.getCurrentGame().getNPCLEAHy()));

        App.getCurrentGame().setNPCROBIN(new NPC(NPCEnums.ROBIN));
        App.getCurrentGame().getNPCROBIN().adaptMap(new Cord(App.getCurrentGame().getNPCROBINx(), App.getCurrentGame().getNPCROBINy()));
    }


    public void createMap1(int topleftx, int toplefty) {
        Set<Cord> occupiedPositions = new HashSet<>();

        Lake Lake1 = new Lake();
        ArrayList<Cord> cords = new ArrayList<>(List.of(
                new Cord(topleftx + 3, toplefty + 0),
                new Cord(topleftx + 3, toplefty + 1),
                new Cord(topleftx + 3, toplefty + 2),
                new Cord(topleftx + 3, toplefty + 3),
                new Cord(topleftx + 4, toplefty + 0),
                new Cord(topleftx + 4, toplefty + 1),
                new Cord(topleftx + 4, toplefty + 2),
                new Cord(topleftx + 4, toplefty + 3)
        ));
        Lake1.adaptMap(cords);
        occupiedPositions.addAll(cords);

        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 9, toplefty + 0),
                new Cord(topleftx + 9, toplefty + 1),
                new Cord(topleftx + 9, toplefty + 2),
                new Cord(topleftx + 9, toplefty + 3),
                new Cord(topleftx + 9, toplefty + 4),
                new Cord(topleftx + 9, toplefty + 5),
                new Cord(topleftx + 9, toplefty + 6),
                new Cord(topleftx + 10, toplefty + 0),
                new Cord(topleftx + 10, toplefty + 1),
                new Cord(topleftx + 10, toplefty + 2),
                new Cord(topleftx + 10, toplefty + 3),
                new Cord(topleftx + 10, toplefty + 4),
                new Cord(topleftx + 10, toplefty + 5),
                new Cord(topleftx + 10, toplefty + 6),
                new Cord(topleftx + 11, toplefty + 0),
                new Cord(topleftx + 11, toplefty + 1),
                new Cord(topleftx + 11, toplefty + 2),
                new Cord(topleftx + 11, toplefty + 3),
                new Cord(topleftx + 11, toplefty + 4),
                new Cord(topleftx + 11, toplefty + 5),
                new Cord(topleftx + 11, toplefty + 6),
                new Cord(topleftx + 12, toplefty + 0),
                new Cord(topleftx + 12, toplefty + 1),
                new Cord(topleftx + 12, toplefty + 2),
                new Cord(topleftx + 12, toplefty + 3),
                new Cord(topleftx + 12, toplefty + 4),
                new Cord(topleftx + 12, toplefty + 5),
                new Cord(topleftx + 12, toplefty + 6),
                new Cord(topleftx + 13, toplefty + 0),
                new Cord(topleftx + 13, toplefty + 1),
                new Cord(topleftx + 13, toplefty + 2),
                new Cord(topleftx + 13, toplefty + 3),
                new Cord(topleftx + 13, toplefty + 4),
                new Cord(topleftx + 13, toplefty + 5),
                new Cord(topleftx + 13, toplefty + 6),
                new Cord(topleftx + 14, toplefty + 0),
                new Cord(topleftx + 14, toplefty + 1),
                new Cord(topleftx + 14, toplefty + 2),
                new Cord(topleftx + 14, toplefty + 3),
                new Cord(topleftx + 14, toplefty + 4),
                new Cord(topleftx + 14, toplefty + 5),
                new Cord(topleftx + 14, toplefty + 6)
        ));
        myGreenHouse = new GreenHouse();
        myGreenHouse.adaptMap(cords, topleftx + 12, toplefty + 6, topleftx + 9, toplefty + 0, topleftx + 14, toplefty + 6);
        occupiedPositions.addAll(cords);

        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 20, toplefty + 0),
                new Cord(topleftx + 20, toplefty + 1),
                new Cord(topleftx + 20, toplefty + 2),
                new Cord(topleftx + 20, toplefty + 3),
                new Cord(topleftx + 20, toplefty + 4),
                new Cord(topleftx + 21, toplefty + 0),
                new Cord(topleftx + 21, toplefty + 1),
                new Cord(topleftx + 21, toplefty + 2),
                new Cord(topleftx + 21, toplefty + 3),
                new Cord(topleftx + 21, toplefty + 4),
                new Cord(topleftx + 22, toplefty + 0),
                new Cord(topleftx + 22, toplefty + 1),
                new Cord(topleftx + 22, toplefty + 2),
                new Cord(topleftx + 22, toplefty + 3),
                new Cord(topleftx + 22, toplefty + 4),
                new Cord(topleftx + 23, toplefty + 0),
                new Cord(topleftx + 23, toplefty + 1),
                new Cord(topleftx + 23, toplefty + 2),
                new Cord(topleftx + 23, toplefty + 3),
                new Cord(topleftx + 23, toplefty + 4),
                new Cord(topleftx + 24, toplefty + 0),
                new Cord(topleftx + 24, toplefty + 1),
                new Cord(topleftx + 24, toplefty + 2),
                new Cord(topleftx + 24, toplefty + 3),
                new Cord(topleftx + 24, toplefty + 4)
        ));
        MyCottage = new Cottage();
        MyCottage.setMyRefrigerator(new Refrigerator());
        MyCottage.adaptMap(cords, topleftx + 22, toplefty + 4, topleftx + 20, toplefty + 0, topleftx + 24, toplefty + 4);
        occupiedPositions.addAll(cords);

        satl = new Satl();
        satl.setItems(new HashMap<>());
        App.getCurrentGame().getMap().get(topleftx + 20).get(toplefty + 5).setInside(satl);
        occupiedPositions.add(new Cord(topleftx + 20, toplefty + 5));

        Lake Lake2 = new Lake();
        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 30, toplefty + 0),
                new Cord(topleftx + 30, toplefty + 1),
                new Cord(topleftx + 31, toplefty + 0),
                new Cord(topleftx + 31, toplefty + 1)
        ));
        Lake2.adaptMap(cords);
        occupiedPositions.addAll(cords);

        Quarry quarry1 = new Quarry();
        cords = new ArrayList<>(List.of(
                new Cord(topleftx + 29, toplefty + 15),
                new Cord(topleftx + 29, toplefty + 16),
                new Cord(topleftx + 30, toplefty + 15),
                new Cord(topleftx + 30, toplefty + 16)
        ));
        quarry1.adaptMap(cords);


// Now continue with the random generation as before
        Random random = new Random();
        int mapWidth = 450;
        int mapHeight = 200;
        int minDistanceFromFixed = 5;

// Generate random ForagingTrees (4 trees)
        for (int i = 0; i < 15; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            ForagingTree tree = new ForagingTree();
            ForagingTreesEnums[] treeTypes = ForagingTreesEnums.values();
            tree.setType(treeTypes[random.nextInt(treeTypes.length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

// Generate random Stones (3 stones)
        for (int i = 0; i < 15; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            Stone stone = new Stone();
            stone.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

// Generate random ForagingCrops (4 crops)
        for (int i = 0; i < 15; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            ForagingCrop crop = new ForagingCrop();
            ForagingCropsEnums[] cropTypes = ForagingCropsEnums.values();
            crop.initilizeCrop(cropTypes[random.nextInt(cropTypes.length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

// Generate random ForagingSeeds (4 seeds)
        for (int i = 0; i < 15; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            ForagingSeed seed = new ForagingSeed();
            ForagingSeedsEnums[] seedTypes = ForagingSeedsEnums.values();
            seed.setType(seedTypes[random.nextInt(seedTypes.length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        initializeMarketsAndNPCs(topleftx, toplefty);
    }

    public void createMap2(int topleftx, int toplefty) {
        Set<Cord> occupiedPositions = new HashSet<>();

        // Multiple quarries scattered across the map
        for (int i = 0; i < 10; i++) {
            int quarryX = topleftx + (i * 3) % 35;
            int quarryY = toplefty + (i * 2) % 20;
            if (quarryX <= 2 || quarryY <= 2) {
                continue;
            }
            Quarry quarry = new Quarry();
            ArrayList<Cord> quarryCords = new ArrayList<>(List.of(
                    new Cord(quarryX, quarryY),
                    new Cord(quarryX, quarryY + 1),
                    new Cord(quarryX + 1, quarryY),
                    new Cord(quarryX + 1, quarryY + 1)
            ));
            quarry.adaptMap(quarryCords);
            occupiedPositions.addAll(quarryCords);
        }

        // Placing a few lakes, but fewer than the lake-heavy map
        for (int i = 0; i < 4; i++) {
            int lakeX = topleftx + (i * 5) % 40;
            int lakeY = toplefty + (i * 6) % 25;
            if (lakeX <= 2 || lakeY <= 2) {
                continue;
            }
            Lake lake = new Lake();
            ArrayList<Cord> lakeCords = new ArrayList<>(List.of(
                    new Cord(lakeX, lakeY),
                    new Cord(lakeX, lakeY + 1),
                    new Cord(lakeX + 1, lakeY),
                    new Cord(lakeX + 1, lakeY + 1)
            ));
            lake.adaptMap(lakeCords);
            occupiedPositions.addAll(lakeCords);
        }

        // Greenhouse (6×7) stays static
        myGreenHouse = new GreenHouse();
        ArrayList<Cord> greenhouseCords = new ArrayList<>(List.of(
                new Cord(topleftx + 12, toplefty + 0),
                new Cord(topleftx + 12, toplefty + 1),
                new Cord(topleftx + 12, toplefty + 2),
                new Cord(topleftx + 12, toplefty + 3),
                new Cord(topleftx + 12, toplefty + 4),
                new Cord(topleftx + 12, toplefty + 5),
                new Cord(topleftx + 12, toplefty + 6),
                new Cord(topleftx + 13, toplefty + 0),
                new Cord(topleftx + 13, toplefty + 1),
                new Cord(topleftx + 13, toplefty + 2),
                new Cord(topleftx + 13, toplefty + 3),
                new Cord(topleftx + 13, toplefty + 4),
                new Cord(topleftx + 13, toplefty + 5),
                new Cord(topleftx + 13, toplefty + 6)
        ));
        myGreenHouse.adaptMap(greenhouseCords, topleftx + 12, toplefty + 6, topleftx + 9, toplefty + 0, topleftx + 14, toplefty + 6);
        occupiedPositions.addAll(greenhouseCords);

        // Cottage (5×5) stays static
        MyCottage = new Cottage();
        MyCottage.setMyRefrigerator(new Refrigerator());
        ArrayList<Cord> cottageCords = new ArrayList<>(List.of(
                new Cord(topleftx + 5, toplefty + 15),
                new Cord(topleftx + 5, toplefty + 16),
                new Cord(topleftx + 5, toplefty + 17),
                new Cord(topleftx + 5, toplefty + 18),
                new Cord(topleftx + 5, toplefty + 19),
                new Cord(topleftx + 6, toplefty + 15),
                new Cord(topleftx + 6, toplefty + 16),
                new Cord(topleftx + 6, toplefty + 17),
                new Cord(topleftx + 6, toplefty + 18),
                new Cord(topleftx + 6, toplefty + 19)
        ));
        MyCottage.adaptMap(cottageCords, topleftx + 5, toplefty + 19, topleftx + 5, toplefty + 15, topleftx + 6, toplefty + 19);
        occupiedPositions.addAll(cottageCords);

        // Satl placement remains static
        satl = new Satl();
        satl.setItems(new HashMap<>());
        App.getCurrentGame().getMap().get(topleftx + 20).get(toplefty + 5).setInside(satl);
        occupiedPositions.add(new Cord(topleftx + 20, toplefty + 5));

        // Generate random ForagingTrees (20 trees)
        Random random = new Random();
        int mapWidth = 450;
        int mapHeight = 200;
        int minDistanceFromFixed = 5;

        for (int i = 0; i < 20; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingTree tree = new ForagingTree();
            ForagingTreesEnums[] treeTypes = ForagingTreesEnums.values();
            tree.setType(treeTypes[random.nextInt(treeTypes.length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Generate random Stones (25 stones)
        for (int i = 0; i < 25; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            Stone stone = new Stone();
            stone.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Generate random ForagingCrops (20 crops)
        for (int i = 0; i < 20; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingCrop crop = new ForagingCrop();
            ForagingCropsEnums[] cropTypes = ForagingCropsEnums.values();
            crop.initilizeCrop(cropTypes[random.nextInt(cropTypes.length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Generate random ForagingSeeds (20 seeds)
        for (int i = 0; i < 20; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingSeed seed = new ForagingSeed();
            ForagingSeedsEnums[] seedTypes = ForagingSeedsEnums.values();
            seed.setType(seedTypes[random.nextInt(seedTypes.length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        initializeMarketsAndNPCs(topleftx, toplefty);


    }

    public void createMap3(int topleftx, int toplefty) {
        Set<Cord> occupiedPositions = new HashSet<>();

        // A mix of lakes and quarries, evenly distributed
        for (int i = 0; i < 6; i++) {
            int lakeX = topleftx + (i * 4) % 35;
            int lakeY = toplefty + (i * 5) % 20;
            if (lakeX <= 2 || lakeY <= 2) {
                continue;
            }
            Lake lake = new Lake();
            ArrayList<Cord> lakeCords = new ArrayList<>(List.of(
                    new Cord(lakeX, lakeY),
                    new Cord(lakeX, lakeY + 1),
                    new Cord(lakeX + 1, lakeY),
                    new Cord(lakeX + 1, lakeY + 1)
            ));
            lake.adaptMap(lakeCords);
            occupiedPositions.addAll(lakeCords);
        }

        for (int i = 0; i < 6; i++) {
            int quarryX = topleftx + (i * 3) % 40;
            int quarryY = toplefty + (i * 2) % 25;
            if (quarryX <= 2 || quarryY <= 2) {
                continue;
            }
            Quarry quarry = new Quarry();
            ArrayList<Cord> quarryCords = new ArrayList<>(List.of(
                    new Cord(quarryX, quarryY),
                    new Cord(quarryX, quarryY + 1),
                    new Cord(quarryX + 1, quarryY),
                    new Cord(quarryX + 1, quarryY + 1)
            ));
            quarry.adaptMap(quarryCords);
            occupiedPositions.addAll(quarryCords);
        }

        // Greenhouse (6×7) at fixed location
        myGreenHouse = new GreenHouse();
        ArrayList<Cord> greenhouseCords = new ArrayList<>(List.of(
                new Cord(topleftx + 12, toplefty + 0),
                new Cord(topleftx + 12, toplefty + 1),
                new Cord(topleftx + 12, toplefty + 2),
                new Cord(topleftx + 12, toplefty + 3),
                new Cord(topleftx + 12, toplefty + 4),
                new Cord(topleftx + 12, toplefty + 5),
                new Cord(topleftx + 12, toplefty + 6),
                new Cord(topleftx + 13, toplefty + 0),
                new Cord(topleftx + 13, toplefty + 1),
                new Cord(topleftx + 13, toplefty + 2),
                new Cord(topleftx + 13, toplefty + 3),
                new Cord(topleftx + 13, toplefty + 4),
                new Cord(topleftx + 13, toplefty + 5),
                new Cord(topleftx + 13, toplefty + 6)
        ));
        myGreenHouse.adaptMap(greenhouseCords, topleftx + 12, toplefty + 6, topleftx + 9, toplefty + 0, topleftx + 14, toplefty + 6);
        occupiedPositions.addAll(greenhouseCords);

        // Cottage (5×5) in its usual size but placed differently
        MyCottage = new Cottage();
        MyCottage.setMyRefrigerator(new Refrigerator());
        ArrayList<Cord> cottageCords = new ArrayList<>(List.of(
                new Cord(topleftx + 18, toplefty + 10),
                new Cord(topleftx + 18, toplefty + 11),
                new Cord(topleftx + 18, toplefty + 12),
                new Cord(topleftx + 18, toplefty + 13),
                new Cord(topleftx + 18, toplefty + 14),
                new Cord(topleftx + 19, toplefty + 10),
                new Cord(topleftx + 19, toplefty + 11),
                new Cord(topleftx + 19, toplefty + 12),
                new Cord(topleftx + 19, toplefty + 13),
                new Cord(topleftx + 19, toplefty + 14)
        ));
        MyCottage.adaptMap(cottageCords, topleftx + 18, toplefty + 14, topleftx + 18, toplefty + 10, topleftx + 19, toplefty + 14);
        occupiedPositions.addAll(cottageCords);

        // Satl placement remains the same
        satl = new Satl();
        satl.setItems(new HashMap<>());
        App.getCurrentGame().getMap().get(topleftx + 20).get(toplefty + 4).setInside(satl);
        occupiedPositions.add(new Cord(topleftx + 20, toplefty + 4));

        // Generating random ForagingTrees (30 trees, much denser than previous maps)
        Random random = new Random();
        int mapWidth = 450;
        int mapHeight = 200;
        int minDistanceFromFixed = 5;

        for (int i = 0; i < 30; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingTree tree = new ForagingTree();
            ForagingTreesEnums[] treeTypes = ForagingTreesEnums.values();
            tree.setType(treeTypes[random.nextInt(treeTypes.length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Generate random Stones (15 stones)
        for (int i = 0; i < 15; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            Stone stone = new Stone();
            stone.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Generate random ForagingCrops (10 crops)
        for (int i = 0; i < 10; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingCrop crop = new ForagingCrop();
            ForagingCropsEnums[] cropTypes = ForagingCropsEnums.values();
            crop.initilizeCrop(cropTypes[random.nextInt(cropTypes.length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Generate random ForagingSeeds (10 seeds)
        for (int i = 0; i < 10; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingSeed seed = new ForagingSeed();
            ForagingSeedsEnums[] seedTypes = ForagingSeedsEnums.values();
            seed.setType(seedTypes[random.nextInt(seedTypes.length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        initializeMarketsAndNPCs(topleftx, toplefty);


    }

    public void createMap4(int topleftx, int toplefty) {
        Set<Cord> occupiedPositions = new HashSet<>();

        // A balanced mix of lakes and quarries (7 lakes, 8 quarries)
        for (int i = 0; i < 7; i++) {
            int lakeX = topleftx + (i * 5) % 40;
            int lakeY = toplefty + (i * 3) % 22;
            if (lakeX <= 2 || lakeY <= 2) {
                continue;
            }
            Lake lake = new Lake();
            ArrayList<Cord> lakeCords = new ArrayList<>(List.of(
                    new Cord(lakeX, lakeY + 1),
                    new Cord(lakeX, lakeY + 2),
                    new Cord(lakeX + 1, lakeY + 1),
                    new Cord(lakeX + 1, lakeY + 2)
            ));
            lake.adaptMap(lakeCords);
            occupiedPositions.addAll(lakeCords);
        }

        for (int i = 0; i < 8; i++) {
            int quarryX = topleftx + (i * 4) % 38;
            int quarryY = toplefty + (i * 2) % 18;
            if (quarryX <= 2 || quarryY <= 2) {
                continue;
            }
            Quarry quarry = new Quarry();
            ArrayList<Cord> quarryCords = new ArrayList<>(List.of(
                    new Cord(quarryX, quarryY),
                    new Cord(quarryX, quarryY + 1),
                    new Cord(quarryX + 1, quarryY),
                    new Cord(quarryX + 1, quarryY + 1)
            ));
            quarry.adaptMap(quarryCords);
            occupiedPositions.addAll(quarryCords);
        }

        // Greenhouse (6×7) perfectly positioned
        myGreenHouse = new GreenHouse();
        ArrayList<Cord> greenhouseCords = new ArrayList<>(List.of(
                new Cord(topleftx + 10, toplefty + 4),
                new Cord(topleftx + 10, toplefty + 5),
                new Cord(topleftx + 10, toplefty + 6),
                new Cord(topleftx + 10, toplefty + 7),
                new Cord(topleftx + 10, toplefty + 8),
                new Cord(topleftx + 10, toplefty + 9),
                new Cord(topleftx + 10, toplefty + 10),
                new Cord(topleftx + 11, toplefty + 4),
                new Cord(topleftx + 11, toplefty + 5),
                new Cord(topleftx + 11, toplefty + 6),
                new Cord(topleftx + 11, toplefty + 7),
                new Cord(topleftx + 11, toplefty + 8),
                new Cord(topleftx + 11, toplefty + 9),
                new Cord(topleftx + 11, toplefty + 10)
        ));
        myGreenHouse.adaptMap(greenhouseCords, topleftx + 10, toplefty + 10, topleftx + 10, toplefty + 4, topleftx + 11, toplefty + 10);
        occupiedPositions.addAll(greenhouseCords);

        // Cottage (5×5) moved to a unique location
        MyCottage = new Cottage();
        MyCottage.setMyRefrigerator(new Refrigerator());
        ArrayList<Cord> cottageCords = new ArrayList<>(List.of(
                new Cord(topleftx + 20, toplefty + 12),
                new Cord(topleftx + 20, toplefty + 13),
                new Cord(topleftx + 20, toplefty + 14),
                new Cord(topleftx + 20, toplefty + 15),
                new Cord(topleftx + 20, toplefty + 16),
                new Cord(topleftx + 21, toplefty + 12),
                new Cord(topleftx + 21, toplefty + 13),
                new Cord(topleftx + 21, toplefty + 14),
                new Cord(topleftx + 21, toplefty + 15),
                new Cord(topleftx + 21, toplefty + 16)
        ));
        MyCottage.adaptMap(cottageCords, topleftx + 20, toplefty + 16, topleftx + 20, toplefty + 12, topleftx + 21, toplefty + 16);
        occupiedPositions.addAll(cottageCords);

        // Satl remains unchanged
        satl = new Satl();
        satl.setItems(new HashMap<>());
        App.getCurrentGame().getMap().get(topleftx + 20).get(toplefty + 5).setInside(satl);
        occupiedPositions.add(new Cord(topleftx + 20, toplefty + 5));

        // Huge foraging zone (35 trees, 20 stones, 15 crops, 15 seeds)
        Random random = new Random();
        int mapWidth = 450;
        int mapHeight = 200;
        int minDistanceFromFixed = 5;

        for (int i = 0; i < 35; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingTree tree = new ForagingTree();
            ForagingTreesEnums[] treeTypes = ForagingTreesEnums.values();
            tree.setType(treeTypes[random.nextInt(treeTypes.length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        for (int i = 0; i < 20; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            Stone stone = new Stone();
            stone.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        for (int i = 0; i < 15; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingCrop crop = new ForagingCrop();
            ForagingCropsEnums[] cropTypes = ForagingCropsEnums.values();
            crop.initilizeCrop(cropTypes[random.nextInt(cropTypes.length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        for (int i = 0; i < 15; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight, occupiedPositions, minDistanceFromFixed);
            ForagingSeed seed = new ForagingSeed();
            ForagingSeedsEnums[] seedTypes = ForagingSeedsEnums.values();
            seed.setType(seedTypes[random.nextInt(seedTypes.length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        initializeMarketsAndNPCs(topleftx, toplefty);


    }

    public Cottage getMyCottage() {
        return MyCottage;
    }

    public void setMyCottage(Cottage myCottage) {
        MyCottage = myCottage;
    }
}
