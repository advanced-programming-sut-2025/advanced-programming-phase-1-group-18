package Model.Items;

import Controller.GameMenuController;
import Model.*;
import enums.StoneTypes;

public class Pickaxe extends Tool implements Name, Price {
    protected String Jens;
    protected int EnergyUsage;
    protected String usage;

    public Pickaxe(String jens, int energyUsage) {
        Jens = jens;
        EnergyUsage = energyUsage;
    }

    public Result use(String direction) {
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Cord tileCord = new Cord(player.getX(), player.getY());
        int dir_x = -1;
        int dir_y = -1;
        System.out.println(direction);
        switch (direction.toLowerCase()) {
            case "n":
                dir_x = 0;
                dir_y = -1;
                break;

            case "ne":
                dir_x = 1;
                dir_y = -1;
                break;

            case "e":
                dir_x = 1;
                dir_y = 0;
                break;

            case "se":
                dir_x = 1;
                dir_y = 1;
                break;

            case "s":
                dir_x = 0;
                dir_y = 1;
                break;

            case "sw":
                dir_x = -1;
                dir_y = 1;
                break;

            case "w":
                dir_x = -1;
                dir_y = 0;
                break;

            case "nw":
                dir_x = -1;
                dir_y = -1;
                break;

            default:
                return null;

        }
        tileCord.setX(dir_x + tileCord.getX());
        tileCord.setY(dir_y + tileCord.getY());

        //System.out.println("khar1");
        Kashi kashi = App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY());

        //System.out.println("khar2");
        Tool mytool = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInMyHandTool();
        Pickaxe mypickaxe = (Pickaxe) mytool;
        //System.out.println("khar3");
        //
        if (!(kashi.getInside() instanceof Quarry) && kashi.getItemInside() == null && !(kashi.getInside() instanceof Stone) && !kashi.isShokhmZadeh()) {

            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
                //System.out.println("khar3.5");
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 3);
                    return new Result(false, "You used it unsuccessfully!(With Max Mining Level)(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                return new Result(false, "You used it unsuccessfully!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 3);
                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                return new Result(false, "You used it unsuccessfully!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 3);
                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                return new Result(false, "You used it unsuccessfully!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 3);
                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                return new Result(false, "You used it unsuccessfully!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 3);
                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                if (HaveBuffMiningSkill()) {
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                return new Result(false, "You used it unsuccessfully!");
            }
        }
        //System.out.println("khar4");
        if (kashi.getInside() instanceof Quarry) {
            if (mypickaxe.getJens().equalsIgnoreCase("initial") && ((Quarry) (kashi.getInside())).getStone() != null && (((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRON) || ((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.GOLD) || ((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRIDIUM))) {
                if (player.getMiningSkill().getLevel() > 350) {
                    if (player.getEnergy() - EnergyUsage + 2 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    if (HaveBuffMiningSkill()) {
                        player.setEnergy(player.getEnergy() - EnergyUsage + 3);
                        return new Result(false, "You type of pickaxe is uncomfortable!(With Mining Buff)");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                } else {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    if (HaveBuffMiningSkill()) {
                        player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                        return new Result(false, "You type of pickaxe is uncomfortable!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                }
            }
            if (mypickaxe.getJens().equalsIgnoreCase("copper") && ((Quarry) (kashi.getInside())).getStone() != null && (((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.GOLD) || ((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRIDIUM))) {
                if (player.getMiningSkill().getLevel() > 350) {
                    if (player.getEnergy() - EnergyUsage + 2 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    if (HaveBuffMiningSkill()) {
                        player.setEnergy(player.getEnergy() - EnergyUsage + 3);
                        return new Result(false, "You type of pickaxe is uncomfortable!(With Mining Buff)");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                } else {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    if (HaveBuffMiningSkill()) {
                        player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                        return new Result(false, "You type of pickaxe is uncomfortable!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                }
            }
            if (mypickaxe.getJens().equalsIgnoreCase("iron") && ((Quarry) (kashi.getInside())).getStone() != null && (((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRIDIUM))) {
                if (player.getMiningSkill().getLevel() > 350) {
                    if (player.getEnergy() - EnergyUsage + 2 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    if (HaveBuffMiningSkill()) {
                        player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                        return new Result(false, "You type of pickaxe is uncomfortable!(With Mining Buff)");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                } else {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    if (HaveBuffMiningSkill()) {
                        player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                        return new Result(false, "You type of pickaxe is uncomfortable!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                }

            } else {

                if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);

                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }

                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    ((Quarry) (kashi.getInside())).setStone(null);
                    return new Result(false, "You used it successfully!");
                }
            }
        }


        if (kashi.getInside() instanceof Stone) {
            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRON) || ((Stone) (kashi.getInside())).getType().equals(StoneTypes.GOLD) || ((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRIDIUM))) {
                if (player.getMiningSkill().getLevel() > 350) {
                    if (player.getEnergy() - EnergyUsage + 2 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                } else {
                    if (player.getEnergy() - EnergyUsage + 2 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                }
            }

            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (((Stone) (kashi.getInside())).getType().equals(StoneTypes.GOLD) || ((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRIDIUM))) {
                if (player.getMiningSkill().getLevel() > 350) {
                    if (player.getEnergy() - EnergyUsage + 2 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                } else {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                }
            }
            if (mypickaxe.getJens().equalsIgnoreCase("iron") && ((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRIDIUM)) {
                if (player.getMiningSkill().getLevel() > 350) {
                    if (player.getEnergy() - EnergyUsage + 2 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                } else {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    return new Result(false, "You type of pickaxe is uncomfortable!");
                }

            } else {

                if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!");
                }
                //
                if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
                    if (player.getEnergy() - EnergyUsage + 1 < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!(With Max Mining Level)");
                }
                if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
                    if (player.getEnergy() - EnergyUsage < 0) {
                        return new Result(false, "Your energy is not enough!");
                    }
                    player.setEnergy(player.getEnergy() - EnergyUsage);
                    player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                    GameMenuController.checkSkilRecipe();
                    StoneItem stoneItem = new StoneItem();
                    player.getInventory().addItem(stoneItem, 1);
                    kashi.setInside(null);
                    return new Result(false, "You used it successfully!");
                }
            }
        }
        if ((kashi.getItemInside() instanceof Axe) || (kashi.getItemInside() instanceof Pickaxe) || (kashi.getItemInside() instanceof Hoe) || (kashi.getItemInside() instanceof WateringCan) || (kashi.getItemInside() instanceof FishingPole) || (kashi.getItemInside() instanceof Scythe) || (kashi.getItemInside() instanceof MilkPail) || (kashi.getItemInside() instanceof Shear)) {

            if (player.getMiningSkill().getLevel() >= 350) {
                kashi.setItemInside(null);
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                return new Result(false, "You used it successfully and the item successfully destroyed!(With Max Mining Level)");

            } else {

                kashi.setItemInside(null);
                player.setEnergy(player.getEnergy() - EnergyUsage);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                return new Result(false, "You used it successfully and the item successfully destroyed!");
            }
        }

        if (kashi.isShokhmZadeh()) {
            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
                //System.out.println("khar3.5");
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshozmzadeh!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshozmzadeh!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!");
            }
            //
            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
                if (player.getEnergy() - EnergyUsage + 2 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy() - EnergyUsage + 2);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
            }
            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
                if (player.getEnergy() - EnergyUsage + 1 < 0) {
                    return new Result(false, "Your energy is not enough!");
                }

                player.setEnergy(player.getEnergy() - EnergyUsage + 1);
                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
                GameMenuController.checkSkilRecipe();
                kashi.setShokhmZadeh(false);
                return new Result(false, "You used it successfully and made there unshokmzadeh!");
            }
        }
        return new Result(false, "what?");
    }

    public boolean HaveBuffMiningSkill() {
        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getFoodBuff().getBuffHours() > 0) {
            return true;
        }
        return false;
    }


    public void update(String jens) {
        switch (jens) {
            case "copper":
                this.setJens("copper");
                this.setEnergyUsage(4);
                break;
            case "iron":
                this.setJens("iron");
                this.setEnergyUsage(3);
                break;
            case "gold":
                this.setJens("gold");
                this.setEnergyUsage(2);
                break;
            case "iridium":
                this.setJens("iridium");
                this.setEnergyUsage(1);
                break;
            default:
                break;
        }
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        this.Jens = jens;
    }

    public int getEnergyUsage() {
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.EnergyUsage = energyUsage;
    }

    @Override
    public String getCorrectName() {
        return "pickaxe";
    }


    @Override
    public int getCorrectPrice() {
        return 0;
    }
}
