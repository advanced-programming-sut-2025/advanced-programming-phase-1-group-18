package io.github.group18.Model;

import io.github.group18.Model.Items.*;
import io.github.group18.View.GameView;
import io.github.group18.enums.ActionEnum;
import io.github.group18.enums.CraftingRecipesEnums;

import java.util.ArrayList;
import java.util.LinkedList;

public class Player extends User {
    private int daysAfterGash = 0;
    private int daysAfterJavabeRad = 0;

    protected User Owner;
    protected double Energy;
    protected double maxEnergy = 200;
    protected double maxEnergyforMarriage = 200;

    protected double x;
    protected double y;
    protected Farm myFarm;
    protected boolean UnlimitedEnergy;
    protected Skill FarmingSkill;
    protected Skill ExtractionSkill;
    protected Skill ForagingSkill;
    protected Skill FishingSkill;
    protected Skill MiningSkill;
    protected Player partner;
    protected Buff FoodBuff;
    protected int wood;
    protected int gold;
    protected ArrayList<Cookingrecipe> CookingRecipes;
    protected ArrayList<CraftingRecipesEnums> CraftingRecipes;
    protected Inventory inventory;
    protected ArrayList<ArtisanGoods> artisansInProduce;
    protected ArrayList<Animal> myBoughtAnimals = new ArrayList<>();

    private int movingDirection = 0;
    private float speed = 4f;
    private float vx = 0, vy = 0;
    private boolean showInventory;
    private boolean showActions;

    public static final int STATE_IDLE = 0;
    public static final int STATE_FAINTING = 5; // After your existing states
    public static final int EATING_STATE = 6;
    private int state = STATE_IDLE;
    private float faintTimer;
    private float eatingTimer;
    private boolean isPlacingItem = false;

    private ActionEnum action;
    private float actionTimer;
    private LinkedList<ActionEnum> actionQueue = new LinkedList<>();

    protected Tool inMyHandTool;
    protected CraftingItem craftingInHand;

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
        initActions();

        this.inventory = new Inventory(12, "initial");

        WateringCan wateringCan = new WateringCan("initial", 5, 40, 40);
        this.inventory.addItem(wateringCan, 1);

        Pickaxe pickaxe = new Pickaxe("initial", 5);
        this.inventory.addItem(pickaxe, 1);

        Axe axe = new Axe("initial", 5);
        this.inventory.addItem(axe, 1);

        Hoe hoe = new Hoe("initial", 5);
        this.inventory.addItem(hoe, 1);

        Scythe scythe = new Scythe();
        this.inventory.addItem(scythe, 1);

//        TrashCan trashCan = new TrashCan("initial");
//        this.inventory.addItem(trashCan, 1);
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

    public double getEnergy() {
        return Energy;
    }

    public void setEnergy(double energy) {
        Energy = energy;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
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

    public double getMaxEnergy() {
        return maxEnergy;
    }

    public void setMaxEnergy(double maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public double getMaxEnergyforMarriage() {
        return maxEnergyforMarriage;
    }

    public void setMaxEnergyforMarriage(double maxEnergyforMarriage) {
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

    public int getMovingDirection() {
        return movingDirection;
    }

    public void setMovingDirection(int movingDirection) {
        this.movingDirection = movingDirection;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isShowInventory() {
        return showInventory;
    }

    public void setShowInventory(boolean showInventory) {
        this.showInventory = showInventory;
    }

    public boolean isShowActions() {
        return showActions;
    }

    public void setShowActions(boolean showActions) {
        this.showActions = showActions;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public float getFaintTimer() {
        return faintTimer;
    }

    public float getEatingTimer() {
        return eatingTimer;
    }

    public float getActionTimer() {
        return actionTimer;
    }

    public void setActionTimer(float actionTimer) {
        this.actionTimer = actionTimer;
    }

    public void setEatingTimer(float eatingTimer) {
        this.eatingTimer = eatingTimer;
    }

    public void setFaintTimer(float faintTimer) {
        this.faintTimer = faintTimer;
    }

    public void setVelocity(float vx, float vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void update(float delta, ArrayList<ArrayList<Kashi>> tiles, GameView gameView) {
        if (Energy <= 0 && state != STATE_FAINTING) {
            faint();
        }

        if (action != null){
            actionTimer += delta;
            if (actionTimer >= 5f) {
                action = null;
                actionTimer=0f;
            }
        }
        // Handle current state
        switch (state) {
            case STATE_FAINTING:
                faintTimer += delta;
                if (faintTimer >= 1.4f) {
                    state = STATE_IDLE;
                    faintTimer = 0f;

                }
                break;
            case STATE_IDLE:
                if (Energy < 0) { // Low energy warning
                    state = STATE_FAINTING;
                    faintTimer = 0f;
                }
                break;
            case EATING_STATE:
                eatingTimer += delta;
                if (eatingTimer >= 1.5f) {
                    state = STATE_IDLE;
                    eatingTimer = 0f;
                }

        }

        tryMove(vx * delta, vy * delta, tiles, gameView);
    }

    public boolean tryMove(float dx, float dy, ArrayList<ArrayList<Kashi>> tiles, GameView gameView) {
        if (state == STATE_FAINTING) return false;
        else if (state == EATING_STATE) return false;
        else if (isPlacingItem) {
            return false;
        }

        int newX = (int) (x + dx);
        int newY = (int) (y + dy);

        if (newX < 1 || newX >= tiles.size() - 1 || newY < 1 || newY >= tiles.get(0).size() - 1) return false;

        if (!(tiles.get(newX).get(newY).getInside() instanceof Lake || tiles.get(newX).get(newY).getInside() instanceof Tavileh ||
            tiles.get(newX).get(newY).getInside() instanceof BigBarn || tiles.get(newX).get(newY).getInside() instanceof DeluxeBarn ||
            tiles.get(newX).get(newY).getInside() instanceof Cage || tiles.get(newX).get(newY).getInside() instanceof BigCoop || tiles.get(newX).get(newY).getInside() instanceof DeluxeCoop
        || tiles.get(newX).get(newY).getInside() instanceof CarpentersShopMarket || tiles.get(newX).get(newY).getInside() instanceof MarniesRanchMarket || tiles.get(newX).get(newY).getInside() instanceof PierresGeneralStoreMarket  || tiles.get(newX).get(newY).getInside() instanceof BlackSmithMarket || tiles.get(newX).get(newY).getInside() instanceof JojoMartMarket || tiles.get(newX).get(newY).getInside() instanceof TheStardropSaloonMarket ))
        {
            x += dx;
            y += dy;
            if (dx != 0 || dy != 0) {
                gameView.setWalking(true);
                Energy -= 10 * (dx * dx + dy * dy);
                Energy = Math.max(Energy, 0);
            }
            return true;
        }
        return false;
    }

    public void faint() {
        if (state != STATE_FAINTING) {
            state = STATE_FAINTING;
            faintTimer = 0f;
        }
    }
    public void eat() {
        if (state != EATING_STATE) {
            state = EATING_STATE;
            faintTimer = 0f;
        }
    }

    public boolean isPlacingItem() {
        return isPlacingItem;
    }

    public void setPlacingItem(boolean placingItem) {
        isPlacingItem = placingItem;
    }

    public CraftingItem getCraftingInHand() {
        return craftingInHand;
    }

    public void setCraftingInHand(CraftingItem craftingInHand) {
        this.craftingInHand = craftingInHand;
    }

    public void pickSelectedItem() {
        Item item = getInventory().getItemBySlot(getInventory().getSelectedSlot());
        if (item instanceof Tool tool) {
            inMyHandTool = tool;
        }
    }

    public ActionEnum getAction() {
        return action;
    }

    public void setAction(ActionEnum action) {
        this.action = action;
    }

    public LinkedList<ActionEnum> getActionQueue() {
        return actionQueue;
    }

    public void setActionQueue(LinkedList<ActionEnum> actionQueue) {
        this.actionQueue = actionQueue;
    }

    public void initActions() {
        for (ActionEnum action : ActionEnum.values()) {
            actionQueue.add(action);
        }
    }

    public void moveToFront(ActionEnum selectedAction) {
        actionQueue.remove(selectedAction);
        actionQueue.addFirst(selectedAction);
    }
}
