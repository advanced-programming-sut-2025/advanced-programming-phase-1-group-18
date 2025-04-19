package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User
{
    protected int Energy;
    protected int x;
    protected int y;
    protected Farm myFarm;
    protected boolean UnlimitedEnergy;
    protected Skill FarmingSkill;
    protected Skill ExtractionSkill;
    protected Skill ForagingSkill;
    protected Skill FishingSkill;
    protected Buff FoodBuff;
    protected ArrayList<Cookingrecipe> Cookingrecipes;
    protected ArrayList<Cookingrecipe> Craftingrecipes;

    protected ArrayList<Cage> Cages;

    protected ArrayList<Tavileh> Tavilehs;

    public Player(String Username, String Password, String Email, String Gender, String NickName)
    {
        super(Username, Password, Email, Gender, NickName);
    }

    public void faintController(){

    }

    public int getEnergy() {
        return Energy;
    }

    public void setEnergy(int energy) {
        this.Energy = energy;
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
        this.UnlimitedEnergy = unlimitedEnergy;
    }

    public Skill getFarmingSkill() {
        return FarmingSkill;
    }

    public void setFarmingSkill(Skill farmingSkill) {
        this.FarmingSkill = farmingSkill;
    }

    public Skill getExtractionSkill() {
        return ExtractionSkill;
    }

    public void setExtractionSkill(Skill extractionSkill) {
        this.ExtractionSkill = extractionSkill;
    }

    public Skill getForagingSkill() {
        return ForagingSkill;
    }

    public void setForagingSkill(Skill foragingSkill) {
        this.ForagingSkill = foragingSkill;
    }

    public Skill getFishingSkill() {
        return FishingSkill;
    }

    public void setFishingSkill(Skill fishingSkill) {
        this.FishingSkill = fishingSkill;
    }

    public Buff getFoodBuff() {
        return FoodBuff;
    }

    public void setFoodBuff(Buff foodBuff) {
        this.FoodBuff = foodBuff;
    }

    public ArrayList<Cookingrecipe> getCookingrecipes() {
        return Cookingrecipes;
    }

    public void setCookingrecipes(ArrayList<Cookingrecipe> cookingrecipes) {
        this.Cookingrecipes = cookingrecipes;
    }

    public ArrayList<Cookingrecipe> getCraftingrecipes() {
        return Craftingrecipes;
    }

    public void setCraftingrecipes(ArrayList<Cookingrecipe> craftingrecipes) {
        this.Craftingrecipes = craftingrecipes;
    }

    public ArrayList<Cage> getCages() {
        return Cages;
    }

    public void setCages(ArrayList<Cage> cages) {
        this.Cages = cages;
    }

    public ArrayList<Tavileh> getTavilehs() {
        return Tavilehs;
    }

    public void setTavilehs(ArrayList<Tavileh> tavilehs) {
        this.Tavilehs = tavilehs;
    }
//    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
//    {
//
//    }
}
