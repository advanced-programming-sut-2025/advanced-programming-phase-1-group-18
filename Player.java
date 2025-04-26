package Model;
import java.util.ArrayList;

public class Player extends User
{
    protected User Owner;
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
    protected int wood;
    protected int gold;
    protected ArrayList<Cookingrecipe> Cookingrecipes;
    protected ArrayList<Cookingrecipe> Craftingrecipes;

    public Player(String Username, String Password, String Email, String Gender, String NickName)
    {
        super(Username, Password, Email, Gender, NickName);
    }
    public Player()
    {

    }

    public void faintController(){

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

    public Buff getFoodBuff() {
        return FoodBuff;
    }

    public void setFoodBuff(Buff foodBuff) {
        FoodBuff = foodBuff;
    }

    public ArrayList<Cookingrecipe> getCookingrecipes() {
        return Cookingrecipes;
    }

    public void setCookingrecipes(ArrayList<Cookingrecipe> cookingrecipes) {
        Cookingrecipes = cookingrecipes;
    }

    public ArrayList<Cookingrecipe> getCraftingrecipes() {
        return Craftingrecipes;
    }

    public void setCraftingrecipes(ArrayList<Cookingrecipe> craftingrecipes) {
        Craftingrecipes = craftingrecipes;
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

//    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
//    {
//
//    }
}
