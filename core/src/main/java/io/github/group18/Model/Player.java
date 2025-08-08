package io.github.group18.Model;

import com.google.gson.Gson;
import io.github.group18.Model.Items.*;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;
import io.github.group18.View.GameView;
import io.github.group18.enums.CraftingRecipesEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class Player extends User {
    private int daysAfterGash = 0;
    private int daysAfterJavabeRad = 0;

    public User Owner;
    public double Energy;
    public double maxEnergy = 200;
    public double maxEnergyforMarriage = 200;

    public double x;
    public double y;
    public Farm myFarm;
    public boolean UnlimitedEnergy;
    public Skill FarmingSkill;
    public Skill ExtractionSkill;
    public Skill ForagingSkill;
    public Skill FishingSkill;
    public Skill MiningSkill;
    public Player partner;
    public Buff FoodBuff;
    public int wood;
    public int gold;
    public ArrayList<Cookingrecipe> CookingRecipes;
    public ArrayList<CraftingRecipesEnums> CraftingRecipes;
    public Inventory inventory;
    public ArrayList<ArtisanGoods> artisansInProduce;
    public ArrayList<Animal> myBoughtAnimals = new ArrayList<>();

    private int movingDirection = 0;
    private float speed = 4f;
    private float vx = 0, vy = 0;
    private boolean showInventory;

    public static final int STATE_IDLE = 0;
    public static final int STATE_FAINTING = 5; // After your existing states
    public static final int EATING_STATE = 6;
    private int state = STATE_IDLE;
    private float faintTimer;
    private float eatingTimer;
    private boolean isPlacingItem = false;

    public Tool inMyHandTool;
    public CraftingItem craftingInHand;

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

    public void update(float delta, GameView gameView) {
        if (Energy <= 0 && state != STATE_FAINTING) {
            faint();
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

        tryMove(vx * delta, vy * delta, gameView);
    }

    public boolean tryMove(float dx, float dy, GameView gameView) {
        if (state == STATE_FAINTING) return false;
        else if (state == EATING_STATE) return false;
        else if (isPlacingItem) {
            return false;
        }

        int newX = (int) (x + dx);
        int newY = (int) (y + dy);

        HashMap<String, Object> body = new HashMap<>();
        body.put("x", newX);
        body.put("y", newY);
        Message send = new Message(body, Message.Type.get_kashi_using_x1_y, Message.Menu.game);
        Message response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        while (response.getType() != Message.Type.get_kashi_using_x1_y) {
            response = ClientModel.getServerConnectionThread().sendAndWaitForResponse(send, ClientModel.TIMEOUT_MILLIS);
        }
        Boolean shokhmZadehObj = response.getFromBody("ShokhmZadeh");
        Boolean entranceObj = response.getFromBody("Enterance");
        Boolean walkableObj = response.getFromBody("Walkable");
        String insideState = response.getFromBody("inside");

        Kashi tile = new Kashi();
        tile.setShokhmZadeh(shokhmZadehObj);
        tile.setEnterance(entranceObj);
        tile.setWalkable(walkableObj);
        if ("full".equals(insideState)) {
            Object insideClassObj = response.getFromBody("insideCLASS");
            Object insideRaw = response.getFromBody("insideOBJ");

            if (insideClassObj instanceof Class<?>) {
                Class<?> clazz = (Class<?>) insideClassObj;

                Gson gson = new Gson();
                String insideJson = gson.toJson(insideRaw);
                Object insideDeserialized = gson.fromJson(insideJson, clazz);

                tile.setInside(insideDeserialized);
            } else {
                tile.setInside(new FishShopMarketali());
            }
        } else {
            tile.setInside(null);
        }


        if (newX < 1 || newX >= ClientModel.mapWidth - 1 || newY < 1 || newY >= ClientModel.mapHeight - 1) return false;

        if (!(tile.getInside() instanceof Lake || tile.getInside() instanceof Tavileh ||
            tile.getInside() instanceof BigBarn || tile.getInside() instanceof DeluxeBarn ||
            tile.getInside() instanceof Cage || tile.getInside() instanceof BigCoop || tile.getInside() instanceof DeluxeCoop
            || tile.getInside() instanceof CarpentersShopMarket || tile.getInside() instanceof MarniesRanchMarket || tile.getInside() instanceof PierresGeneralStoreMarket || tile.getInside() instanceof BlackSmithMarket || tile.getInside() instanceof JojoMartMarket || tile.getInside() instanceof TheStardropSaloonMarket)) {
            x += dx;
            y += dy;
            if (dx != 0 || dy != 0) {
                gameView.setWalking(true);
                Energy -= 10 * (dx * dx + dy * dy);
                Energy = Math.max(Energy, 0);
                HashMap<String, Object> body1 = new HashMap<>();
                body1.put("username", ClientModel.getPlayer().getOwner().getUsername());
                body1.put("x", String.valueOf(x));
                body1.put("y", String.valueOf(y));
                Message send1 = new Message(body1, Message.Type.player_pos_update, Message.Menu.game);
                ClientModel.getServerConnectionThread().sendMessage(send1);
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

    public String getUsername() {
        return getOwner().Username;
    }
}
