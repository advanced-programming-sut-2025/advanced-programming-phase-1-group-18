package Model;

import Model.Items.FoodCooking;
import Model.Buff;
import enums.FoodCookingEnums;
import enums.SkillEnum;

public class Cookingrecipe {

    protected FoodCookingEnums Foodtype;
    protected int price;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public FoodCookingEnums getFood() {
        return Foodtype;
    }

    public void setFood(FoodCookingEnums food) {
        Foodtype = food;
    }

    public FoodCooking letmecook(FoodCookingEnums foodtype) {
        FoodCooking cookedFood = new FoodCooking();
        cookedFood.setName(foodtype);

        Buff buff = null;

        switch (foodtype.toString().toLowerCase()) {
            case "friedegg" -> {
                cookedFood.setEnergy(50);
                cookedFood.setSellPrice(35);
            }
            case "bakedfish" -> {
                cookedFood.setEnergy(75);
                cookedFood.setSellPrice(100);
            }
            case "salad" -> {
                cookedFood.setEnergy(113);
                cookedFood.setSellPrice(110);
            }
            case "omelet" -> {
                cookedFood.setEnergy(100);
                cookedFood.setSellPrice(125);
            }
            case "pumpkinpie" -> {
                cookedFood.setEnergy(225);
                cookedFood.setSellPrice(385);
            }
            case "spaghetti" -> {
                cookedFood.setEnergy(75);
                cookedFood.setSellPrice(120);
            }
            case "pizza" -> {
                cookedFood.setEnergy(150);
                cookedFood.setSellPrice(300);
            }
            case "tortilla" -> {
                cookedFood.setEnergy(50);
                cookedFood.setSellPrice(50);
            }
            case "makiroll" -> {
                cookedFood.setEnergy(100);
                cookedFood.setSellPrice(220);
            }
            case "tripleshotespresso" -> {
                cookedFood.setEnergy(200);
                cookedFood.setSellPrice(450);
                buff = new Buff();
                buff.setEnergyIncrease(100);
                buff.setBuffHours(5);
            }
            case "cookie" -> {
                cookedFood.setEnergy(90);
                cookedFood.setSellPrice(140);
            }
            case "hashbrowns" -> {
                cookedFood.setEnergy(90);
                cookedFood.setSellPrice(120);
                buff = new Buff();
                buff.setBuffHours(5);
                buff.setBuffSkillType(SkillEnum.FarmingSkill);
            }
            case "pancakes" -> {
                cookedFood.setEnergy(90);
                cookedFood.setSellPrice(80);
                buff = new Buff();
                buff.setBuffHours(11);
                buff.setBuffSkillType(SkillEnum.ForagingSkill);
            }
            case "fruitsalad" -> {
                cookedFood.setEnergy(263);
                cookedFood.setSellPrice(450);
            }
            case "redplate" -> {
                cookedFood.setEnergy(240);
                cookedFood.setSellPrice(400);
                buff = new Buff();
                buff.setEnergyIncrease(50);
                buff.setBuffHours(3);
            }
            case "bread" -> {
                cookedFood.setEnergy(50);
                cookedFood.setSellPrice(60);
            }
            case "salmondinner" -> {
                cookedFood.setEnergy(125);
                cookedFood.setSellPrice(300);
            }
            case "vegetablemedley" -> {
                cookedFood.setEnergy(165);
                cookedFood.setSellPrice(120);
            }
            case "farmerslunch" -> {
                cookedFood.setEnergy(200);
                cookedFood.setSellPrice(150);
                buff = new Buff();
                buff.setBuffHours(5);
                buff.setBuffSkillType(SkillEnum.FarmingSkill);
            }
            case "survivalburger" -> {
                cookedFood.setEnergy(125);
                cookedFood.setSellPrice(180);
                buff = new Buff();
                buff.setBuffHours(5);
                buff.setBuffSkillType(SkillEnum.ForagingSkill);
            }
            case "dishothesea" -> {
                cookedFood.setEnergy(150);
                cookedFood.setSellPrice(220);
                buff = new Buff();
                buff.setBuffHours(5);
                buff.setBuffSkillType(SkillEnum.FishingSkill);
            }
            case "seaformpudding" -> {
                cookedFood.setEnergy(175);
                cookedFood.setSellPrice(300);
                buff = new Buff();
                buff.setBuffHours(10);
                buff.setBuffSkillType(SkillEnum.FishingSkill);
            }
            case "minerstreat" -> {
                cookedFood.setEnergy(125);
                cookedFood.setSellPrice(200);
                buff = new Buff();
                buff.setBuffHours(5);
                buff.setBuffSkillType(SkillEnum.MiningSkill);
            }
            default -> {
                cookedFood.setEnergy(0);
                cookedFood.setSellPrice(0);
            }
        }

        if (buff != null) {
            cookedFood.setBuff(buff);
        }

        return cookedFood;
    }
}
