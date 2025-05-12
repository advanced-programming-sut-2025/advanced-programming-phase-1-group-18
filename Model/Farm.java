package Model;

import java.util.*;

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

    public void createMap1(int topleftx, int toplefty) {
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
        myGreenHouse.adaptMap(cords, topleftx + 7, toplefty + 6, topleftx + 4, toplefty + 0, topleftx + 9, toplefty + 6);

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
        MyCottage = new Cottage();
        MyCottage.adaptMap(cords, topleftx + 13, toplefty + 4, topleftx + 11, toplefty + 0, topleftx + 15, toplefty + 4);

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
        quarry1.adaptMap(cords);

        // Track all occupied positions
        Set<Cord> occupiedPositions = new HashSet<>();

// Add Lake1 positions (using the cords list we already have)
        ArrayList<Cord> lake1Cords = new ArrayList<>(List.of(
                new Cord(topleftx + 1, toplefty + 0),
                new Cord(topleftx + 1, toplefty + 1),
                new Cord(topleftx + 2, toplefty + 0),
                new Cord(topleftx + 2, toplefty + 1)
        ));

        occupiedPositions.addAll(lake1Cords);

// Add Greenhouse positions
        ArrayList<Cord> greenhouseCords = new ArrayList<>(List.of(
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
        occupiedPositions.addAll(greenhouseCords);

// Add Cottage positions
        ArrayList<Cord> cottageCords = new ArrayList<>(List.of(
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
        occupiedPositions.addAll(cottageCords);

// Add Lake2 positions
        ArrayList<Cord> lake2Cords = new ArrayList<>(List.of(
                new Cord(topleftx + 17, toplefty + 0),
                new Cord(topleftx + 17, toplefty + 1),
                new Cord(topleftx + 18, toplefty + 0),
                new Cord(topleftx + 18, toplefty + 1)
        ));
        occupiedPositions.addAll(lake2Cords);

// Add Quarry positions
        ArrayList<Cord> quarryCords = new ArrayList<>(List.of(
                new Cord(topleftx + 20, toplefty + 0),
                new Cord(topleftx + 20, toplefty + 1),
                new Cord(topleftx + 21, toplefty + 0),
                new Cord(topleftx + 21, toplefty + 1)
        ));
        occupiedPositions.addAll(quarryCords);

// Now continue with the random generation as before
        Random random = new Random();
        int mapWidth = 450;
        int mapHeight = 200;
        int minDistanceFromFixed = 5;

// Generate random ForagingTrees (4 trees)
        for (int i = 0; i < 4; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            ForagingTree tree = new ForagingTree();
            ForagingTreesEnums[] treeTypes = ForagingTreesEnums.values();
            tree.setType(treeTypes[random.nextInt(treeTypes.length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

// Generate random Stones (3 stones)
        for (int i = 0; i < 3; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            Stone stone = new Stone();
            stone.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

// Generate random ForagingCrops (4 crops)
        for (int i = 0; i < 4; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            ForagingCrop crop = new ForagingCrop();
            ForagingCropsEnums[] cropTypes = ForagingCropsEnums.values();
            crop.initilizeCrop(cropTypes[random.nextInt(cropTypes.length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

// Generate random ForagingSeeds (4 seeds)
        for (int i = 0; i < 4; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);

            ForagingSeed seed = new ForagingSeed();
            ForagingSeedsEnums[] seedTypes = ForagingSeedsEnums.values();
            seed.setType(seedTypes[random.nextInt(seedTypes.length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Continue with markets and NPCs (unchanged from your original code)
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

    public void createMap2(int topleftx, int toplefty) {
        // Lake - Smaller and in the corner
        Lake Lake1 = new Lake();
        ArrayList<Cord> lakeCords = new ArrayList<>(List.of(
                new Cord(topleftx + 1, toplefty + 1),
                new Cord(topleftx + 1, toplefty + 2),
                new Cord(topleftx + 2, toplefty + 1),
                new Cord(topleftx + 2, toplefty + 2)
        ));
        Lake1.adaptMap(lakeCords);

        // Greenhouse - Compact version
        ArrayList<Cord> greenhouseCords = new ArrayList<>(List.of(
                new Cord(topleftx + 5, toplefty + 5),
                new Cord(topleftx + 5, toplefty + 6),
                new Cord(topleftx + 6, toplefty + 5),
                new Cord(topleftx + 6, toplefty + 6)
        ));
        myGreenHouse.adaptMap(greenhouseCords, topleftx + 6, toplefty + 6,
                topleftx + 5, toplefty + 5, topleftx + 6, toplefty + 6);

        // Cottage - Smaller footprint
        ArrayList<Cord> cottageCords = new ArrayList<>(List.of(
                new Cord(topleftx + 8, toplefty + 8),
                new Cord(topleftx + 8, toplefty + 9),
                new Cord(topleftx + 9, toplefty + 8),
                new Cord(topleftx + 9, toplefty + 9)
        ));
        MyCottage = new Cottage();
        MyCottage.adaptMap(cottageCords, topleftx + 9, toplefty + 9,
                topleftx + 8, toplefty + 8, topleftx + 9, toplefty + 9);

        // Quarry - Larger version
        ArrayList<Cord> quarryCords = new ArrayList<>(List.of(
                new Cord(topleftx + 15, toplefty + 15),
                new Cord(topleftx + 15, toplefty + 16),
                new Cord(topleftx + 16, toplefty + 15),
                new Cord(topleftx + 16, toplefty + 16),
                new Cord(topleftx + 15, toplefty + 17),
                new Cord(topleftx + 16, toplefty + 17)
        ));
        Quarry quarry1 = new Quarry();
        quarry1.adaptMap(quarryCords);

        // Track occupied positions
        Set<Cord> occupiedPositions = new HashSet<>();
        occupiedPositions.addAll(lakeCords);
        occupiedPositions.addAll(greenhouseCords);
        occupiedPositions.addAll(cottageCords);
        occupiedPositions.addAll(quarryCords);

        // Random generation with forest focus
        Random random = new Random();
        int mapWidth = 300;
        int mapHeight = 150;
        int minDistanceFromFixed = 4;

        // More trees (8 instead of 4)
        for (int i = 0; i < 8; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingTree tree = new ForagingTree();
            tree.setType(ForagingTreesEnums.values()[random.nextInt(ForagingTreesEnums.values().length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Fewer stones (2 instead of 3)
        for (int i = 0; i < 2; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            new Stone().adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // More crops (6 instead of 4)
        for (int i = 0; i < 6; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingCrop crop = new ForagingCrop();
            crop.initilizeCrop(ForagingCropsEnums.values()[random.nextInt(ForagingCropsEnums.values().length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Same seeds (4)
        for (int i = 0; i < 4; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingSeed seed = new ForagingSeed();
            seed.setType(ForagingSeedsEnums.values()[random.nextInt(ForagingSeedsEnums.values().length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Initialize markets and NPCs (same as original)
        initializeMarketsAndNPCs(topleftx, toplefty);
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

    public void createMap3(int topleftx, int toplefty) {
        // Lake - Long and narrow
        Lake Lake1 = new Lake();
        ArrayList<Cord> lakeCords = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            lakeCords.add(new Cord(topleftx + 3, toplefty + i));
        }
        Lake1.adaptMap(lakeCords);

        // Greenhouse - Vertical layout
        ArrayList<Cord> greenhouseCords = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            greenhouseCords.add(new Cord(topleftx + 8, toplefty + i));
        }
        myGreenHouse.adaptMap(greenhouseCords, topleftx + 8, toplefty + 2,
                topleftx + 8, toplefty + 0, topleftx + 8, toplefty + 4);

        // Cottage - On a "hill"
        ArrayList<Cord> cottageCords = new ArrayList<>(List.of(
                new Cord(topleftx + 15, toplefty + 10),
                new Cord(topleftx + 15, toplefty + 11),
                new Cord(topleftx + 16, toplefty + 10),
                new Cord(topleftx + 16, toplefty + 11)
        ));
        MyCottage = new Cottage();
        MyCottage.adaptMap(cottageCords, topleftx + 16, toplefty + 11,
                topleftx + 15, toplefty + 10, topleftx + 16, toplefty + 11);

        // Large quarry
        ArrayList<Cord> quarryCords = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                quarryCords.add(new Cord(topleftx + 20 + i, toplefty + 20 + j));
            }
        }
        Quarry quarry1 = new Quarry();
        quarry1.adaptMap(quarryCords);

        // Track occupied positions
        Set<Cord> occupiedPositions = new HashSet<>();
        occupiedPositions.addAll(lakeCords);
        occupiedPositions.addAll(greenhouseCords);
        occupiedPositions.addAll(cottageCords);
        occupiedPositions.addAll(quarryCords);

        // Random generation with mountain focus
        Random random = new Random();
        int mapWidth = 400;
        int mapHeight = 200;
        int minDistanceFromFixed = 6; // More spacing for mountainous terrain

        // Fewer trees (3 instead of 4)
        for (int i = 0; i < 3; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingTree tree = new ForagingTree();
            tree.setType(ForagingTreesEnums.values()[random.nextInt(ForagingTreesEnums.values().length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // More stones (6 instead of 3)
        for (int i = 0; i < 6; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            new Stone().adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Fewer crops (2 instead of 4)
        for (int i = 0; i < 2; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingCrop crop = new ForagingCrop();
            crop.initilizeCrop(ForagingCropsEnums.values()[random.nextInt(ForagingCropsEnums.values().length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // More seeds (6 instead of 4)
        for (int i = 0; i < 6; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingSeed seed = new ForagingSeed();
            seed.setType(ForagingSeedsEnums.values()[random.nextInt(ForagingSeedsEnums.values().length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Initialize markets and NPCs (same as original)
        initializeMarketsAndNPCs(topleftx, toplefty);
    }

    //
    public void createMap4(int topleftx, int toplefty) {
        // Central lake
        Lake Lake1 = new Lake();
        ArrayList<Cord> lakeCords = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                lakeCords.add(new Cord(topleftx + 10 + i, toplefty + 10 + j));
            }
        }
        Lake1.adaptMap(lakeCords);

        // Large greenhouse
        ArrayList<Cord> greenhouseCords = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                greenhouseCords.add(new Cord(topleftx + 5 + i, toplefty + 5 + j));
            }
        }
        myGreenHouse.adaptMap(greenhouseCords, topleftx + 7, toplefty + 8,
                topleftx + 5, toplefty + 5, topleftx + 8, toplefty + 10);

        // Cottage near greenhouse
        ArrayList<Cord> cottageCords = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                cottageCords.add(new Cord(topleftx + 3 + i, toplefty + 5 + j));
            }
        }
        MyCottage = new Cottage();
        MyCottage.adaptMap(cottageCords, topleftx + 4, toplefty + 6,
                topleftx + 3, toplefty + 5, topleftx + 4, toplefty + 7);

        // Small quarry in corner
        ArrayList<Cord> quarryCords = new ArrayList<>(List.of(
                new Cord(topleftx + 20, toplefty + 1),
                new Cord(topleftx + 20, toplefty + 2),
                new Cord(topleftx + 21, toplefty + 1),
                new Cord(topleftx + 21, toplefty + 2)
        ));
        Quarry quarry1 = new Quarry();
        quarry1.adaptMap(quarryCords);

        // Track occupied positions
        Set<Cord> occupiedPositions = new HashSet<>();
        occupiedPositions.addAll(lakeCords);
        occupiedPositions.addAll(greenhouseCords);
        occupiedPositions.addAll(cottageCords);
        occupiedPositions.addAll(quarryCords);

        // Random generation with farm focus
        Random random = new Random();
        int mapWidth = 350;
        int mapHeight = 180;
        int minDistanceFromFixed = 3; // Closer spacing for farm layout

        // Balanced trees (5)
        for (int i = 0; i < 5; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingTree tree = new ForagingTree();
            tree.setType(ForagingTreesEnums.values()[random.nextInt(ForagingTreesEnums.values().length)]);
            tree.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Balanced stones (4)
        for (int i = 0; i < 4; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            new Stone().adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Lots of crops (8 instead of 4)
        for (int i = 0; i < 8; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingCrop crop = new ForagingCrop();
            crop.initilizeCrop(ForagingCropsEnums.values()[random.nextInt(ForagingCropsEnums.values().length)]);
            crop.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Lots of seeds (8 instead of 4)
        for (int i = 0; i < 8; i++) {
            Cord randomPos = getRandomPosition(topleftx, toplefty, mapWidth, mapHeight,
                    occupiedPositions, minDistanceFromFixed);
            ForagingSeed seed = new ForagingSeed();
            seed.setType(ForagingSeedsEnums.values()[random.nextInt(ForagingSeedsEnums.values().length)]);
            seed.adaptMap(randomPos);
            occupiedPositions.add(randomPos);
        }

        // Initialize markets and NPCs (same as original)
        initializeMarketsAndNPCs(topleftx, toplefty);

    }
}
