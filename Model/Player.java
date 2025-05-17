package Model;

import Model.Items.*;
import enums.CraftingRecipesEnums;
import Model.Items.ArtisanGoods;
import Model.Items.Tool;
import Model.Items.WateringCan;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User {
    private int daysAfterGash = 0;
    private int daysAfterJavabeRad=0;

    protected User Owner;
    protected int Energy;
    protected int maxEnergy = 200;
    protected int maxEnergyforMarriage=200;

    protected int x;
    protected int y;
    protected Farm myFarm;
    protected boolean UnlimitedEnergy;
    protected Skill FarmingSkill;
    protected Skill ExtractionSkill;
    protected Skill ForagingSkill;
    protected Skill FishingSkill;
    protected Skill MiningSkill;

    protected Buff FoodBuff;
    protected int wood;
    protected int gold;
    protected ArrayList<Cookingrecipe> CookingRecipes;
    protected ArrayList<CraftingRecipesEnums> CraftingRecipes;
    protected Inventory inventory;
    protected ArrayList<ArtisanGoods> artisansInProduce;
    //
    protected Tool inMyHandTool = null;
    //
    protected ArrayList<Animal> myBoughtAnimals = new ArrayList<>();
    //
    protected Player partner;

    public Player() {
        //super(this.getUsername(),this.getPassword(),this.getEmail(),this.getGender(),this.getNickName());
        super();
        this.Owner = new User();
        this.Energy = 0;
        this.myFarm = new Farm();
        this.UnlimitedEnergy = false;
        this.FarmingSkill = new Skill();
        this.ExtractionSkill = new Skill();
        this.ForagingSkill = new Skill();
        this.FishingSkill = new Skill();
        this.MiningSkill = new Skill();
        this.FoodBuff = new Buff();
        this.wood = 0;
        this.gold = 0;
        this.CookingRecipes = new ArrayList<>();
        this.CraftingRecipes = new ArrayList<>();
        this.artisansInProduce = new ArrayList<>();
        this.inventory = new Inventory(12, "initial");
        WateringCan wateringCan = new WateringCan("initial", 5, 40, 40);
        this.inventory.addItem(wateringCan, 1);


        Pickaxe pickaxe  = new Pickaxe("initial",5);
        this.inventory.addItem(pickaxe, 1);

        Axe axe = new Axe("initial",5);
        this.inventory.addItem(axe, 1);

        Hoe hoe =  new Hoe("initial",5);
        this.inventory.addItem(hoe, 1);

        TrashCan trashCan =  new TrashCan("initial");
        this.inventory.addItem(trashCan, 1);

    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void faintController() {

    }

    public User getOwner() {
        return Owner;
    }

    public void setOwner(User owner) {
        Owner = owner;
    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        Energy = energy;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Farm getMyFarm() {
        return myFarm;
    }

    public void setMyFarm(Farm myFarm) {
        this.myFarm = myFarm;
    }

    public boolean isUnlimitedEnergy() {
        return UnlimitedEnergy;
    }

    public void setUnlimitedEnergy(boolean unlimitedEnergy) {
        UnlimitedEnergy = unlimitedEnergy;
    }

    public Skill getFarmingSkill() {
        return FarmingSkill;
    }

    public void setFarmingSkill(Skill farmingSkill) {
        FarmingSkill = farmingSkill;
    }

    public Skill getExtractionSkill() {
        return ExtractionSkill;
    }

    public void setExtractionSkill(Skill extractionSkill) {
        ExtractionSkill = extractionSkill;
    }

    public Skill getForagingSkill() {
        return ForagingSkill;
    }

    public void setForagingSkill(Skill foragingSkill) {
        ForagingSkill = foragingSkill;
    }

    public Skill getFishingSkill() {
        return FishingSkill;
    }

    public void setFishingSkill(Skill fishingSkill) {
        FishingSkill = fishingSkill;
    }

    public Skill getMiningSkill() {
        return MiningSkill;
    }

    public void setMiningSkill(Skill miningSkill) {
        MiningSkill = miningSkill;
    }
    public Buff getFoodBuff() {
        return FoodBuff;
    }

    public void setFoodBuff(Buff foodBuff) {
        FoodBuff = foodBuff;
    }

    public ArrayList<Cookingrecipe> getCookingRecipes() {
        return CookingRecipes;
    }

    public void setCookingRecipes(ArrayList<Cookingrecipe> cookingrecipes) {
        CookingRecipes = cookingrecipes;
    }

    public ArrayList<CraftingRecipesEnums> getCraftingRecipes() {
        return CraftingRecipes;
    }

    public void setCraftingRecipes(ArrayList<CraftingRecipesEnums> craftingRecipes) {
        CraftingRecipes = craftingRecipes;
    }

    public ArrayList<ArtisanGoods> getArtisansInProduce() {
        return artisansInProduce;
    }

    public void setArtisansInProduce(ArrayList<ArtisanGoods> artisansInProduce) {
        this.artisansInProduce = artisansInProduce;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public int getMaxEnergyforMarriage() {
        return maxEnergyforMarriage;
    }

    public void setMaxEnergyforMarriage(int maxEnergyforMarriage) {
        this.maxEnergyforMarriage = maxEnergyforMarriage;
    }

    public Tool getInMyHandTool() {
        return inMyHandTool;
    }

    public void setInMyHandTool(Tool inMyHandTool) {
        this.inMyHandTool = inMyHandTool;
    }
    //bought animals
    public ArrayList<Animal> getMyBoughtAnimals() {
        return myBoughtAnimals;
    }

    public void setMyBoughtAnimals(ArrayList<Animal> myBoughtAnimals) {
        this.myBoughtAnimals = myBoughtAnimals;
    }
    //
    public int getDaysAfterGash() {
        return daysAfterGash;
    }

    public void setDaysAfterGash(int daysAfterGash) {
        this.daysAfterGash = daysAfterGash;
    }

    public int getDaysAfterJavabeRad() {
        return daysAfterJavabeRad;
    }

    public void setDaysAfterJavabeRad(int daysAfterJavabeRad) {
        this.daysAfterJavabeRad = daysAfterJavabeRad;
    }

    public Player getPartner() {
        return partner;
    }

    public void setPartner(Player partner) {
        this.partner = partner;
    }
}
