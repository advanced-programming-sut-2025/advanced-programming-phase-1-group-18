
package io.github.group18.Model.Items;

import io.github.group18.Controller.GameMenuController;
import io.github.group18.Model.*;
import io.github.group18.enums.StoneTypes;

public class Pickaxe extends Tool implements Name, Price, PictureModel {
    public String Jens;
    public int EnergyUsage;
    public String usage;

    public Pickaxe(String jens, int energyUsage) {
        Jens = jens;
        EnergyUsage = energyUsage;
    }

    public Result use(Kashi kashi) {
        //Server-TODO
//        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
//
//        Tool mytool = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInMyHandTool();
//        Pickaxe mypickaxe = (Pickaxe) mytool;
//
//        //not using pickaxe correctly --> if   otherwise else
//        if (!(kashi.getInside() instanceof Quarry) && kashi.getItemInside() == null && !(kashi.getInside() instanceof Stone) && !kashi.isShokhmZadeh()) {
//
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
//                //System.out.println("khar3.5");
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 3);
//                    return new Result(false, "You used it unsuccessfully!(With Max Mining Level)(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                return new Result(false, "You used it unsuccessfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 3);
//                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                return new Result(false, "You used it unsuccessfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 3);
//                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                return new Result(false, "You used it unsuccessfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 3);
//                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                return new Result(false, "You used it unsuccessfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 3);
//                    return new Result(false, "You used it unsuccessfully!(With Max Mining Skill)(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                return new Result(false, "You used it unsuccessfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                if (HaveBuffMiningSkill()) {
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You used it unsuccessfully!(With Mining Buff)");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                return new Result(false, "You used it unsuccessfully!");
//            }
//        }
//
//        if (kashi.getInside() instanceof Quarry) {
//            //first 3 conditions are using pickaxe incorectly
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && ((Quarry) (kashi.getInside())).getStone() != null && (((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRON) || ((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.GOLD) || ((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRIDIUM))) {
//                if (player.getMiningSkill().getLevel() > 350) {
//                    if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    if (HaveBuffMiningSkill()) {
//                        player.setEnergy(player.getEnergy() - getEnergyUsage() + 3);
//                        return new Result(false, "You type of pickaxe is uncomfortable!(With Mining Buff)");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                } else {
//                    if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    if (HaveBuffMiningSkill()) {
//                        player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                        return new Result(false, "You type of pickaxe is uncomfortable!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                }
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && ((Quarry) (kashi.getInside())).getStone() != null && (((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.GOLD) || ((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRIDIUM))) {
//                if (player.getMiningSkill().getLevel() > 350) {
//                    if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    if (HaveBuffMiningSkill()) {
//                        player.setEnergy(player.getEnergy() - getEnergyUsage() + 3);
//                        return new Result(false, "You type of pickaxe is uncomfortable!(With Mining Buff)");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                } else {
//                    if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    if (HaveBuffMiningSkill()) {
//                        player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                        return new Result(false, "You type of pickaxe is uncomfortable!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                }
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && ((Quarry) (kashi.getInside())).getStone() != null && (((Quarry) (kashi.getInside())).getStone().getType().equals(StoneTypes.IRIDIUM))) {
//                if (player.getMiningSkill().getLevel() > 350) {
//                    if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    if (HaveBuffMiningSkill()) {
//                        player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                        return new Result(false, "You type of pickaxe is uncomfortable!(With Mining Buff)");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                } else {
//                    if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    if (HaveBuffMiningSkill()) {
//                        player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                        return new Result(false, "You type of pickaxe is uncomfortable!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                }
//
//            }
//
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                ((Quarry) (kashi.getInside())).setStone(null);
//                return new Result(false, "You used it successfully!");
//            }
//        }
//
//
//        if (kashi.getInside() instanceof Stone) {
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRON) || ((Stone) (kashi.getInside())).getType().equals(StoneTypes.GOLD) || ((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRIDIUM))) {
//                if (player.getMiningSkill().getLevel() > 350) {
//                    if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                } else {
//                    if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                }
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (((Stone) (kashi.getInside())).getType().equals(StoneTypes.GOLD) || ((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRIDIUM))) {
//                if (player.getMiningSkill().getLevel() > 350) {
//                    if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                } else {
//                    if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                }
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && ((Stone) (kashi.getInside())).getType().equals(StoneTypes.IRIDIUM)) {
//                if (player.getMiningSkill().getLevel() > 350) {
//                    if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                } else {
//                    if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                        return new Result(false, "Your energy is not enough!");
//                    }
//                    player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                    return new Result(false, "You type of pickaxe is uncomfortable!");
//                }
//
//            }
//
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                StoneItem stoneItem = new StoneItem();
//                player.getInventory().addItem(stoneItem, 1);
//                kashi.setInside(null);
//                return new Result(false, "You used it successfully!");
//            }
//
//        }
//
//        if ((kashi.getItemInside() instanceof Axe) || (kashi.getItemInside() instanceof Pickaxe) || (kashi.getItemInside() instanceof Hoe) || (kashi.getItemInside() instanceof WateringCan) || (kashi.getItemInside() instanceof FishingPole) || (kashi.getItemInside() instanceof Scythe) || (kashi.getItemInside() instanceof MilkPail) || (kashi.getItemInside() instanceof Shear)) {
//
//            if (player.getMiningSkill().getLevel() >= 350) {
//                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().addItem(kashi.getItemInside(), 1);
//                kashi.setItemInside(null);
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                return new Result(false, "You used it successfully and the item successfully added to your inventory!(With Max Mining Level)");
//
//            } else {
//
//                App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInventory().addItem(kashi.getItemInside(), 1);
//                kashi.setItemInside(null);
//                player.setEnergy(player.getEnergy() - getEnergyUsage());
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                return new Result(false, "You used it successfully and the item successfully destroyed!");
//            }
//        }
//
//        if (kashi.isShokhmZadeh()) {
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() >= 350)) {
//                //System.out.println("khar3.5");
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshozmzadeh!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("initial") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshozmzadeh!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("copper") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iron") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("gold") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!");
//            }
//            //
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() >= 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 2 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 2);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!(With Max Mining Level)");
//            }
//            if (mypickaxe.getJens().equalsIgnoreCase("iridium") && (player.getMiningSkill().getLevel() < 350)) {
//                if (player.getEnergy() - getEnergyUsage() + 1 < 0) {
//                    return new Result(false, "Your energy is not enough!");
//                }
//
//                player.setEnergy(player.getEnergy() - getEnergyUsage() + 1);
//                player.getMiningSkill().setLevel(player.getMiningSkill().getLevel() + 10);
//                GameMenuController.checkSkilRecipe();
//                kashi.setShokhmZadeh(false);
//                return new Result(false, "You used it successfully and made there unshokmzadeh!");
//            }
//        }
        return new Result(false, "what?");
    }

    public boolean HaveBuffMiningSkill() {
        //Server-TODO
//        if (App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getFoodBuff().getBuffHours() > 0) {
//            return true;
//        }
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
        switch (App.getCurrentGame().getCurrentWeather()) {
            case SUNNY -> {
                return EnergyUsage;
            }
            case STORM -> {
                return (int) (EnergyUsage * 1.5);
            }
            case RAIN -> {
                return (int) (EnergyUsage * 1.5);
            }
            case SNOW -> {
                return (int) (EnergyUsage * 2);
            }
        }
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
        switch (Jens.toLowerCase()) {
            case "initial":
                return 200;
            case "iron":
            case "copper":
                return 250;
            case "gold":
                return 300;
            case "iridium":
                return 400;
            default:
                return 200;
        }
    }

    @Override
    public String getPath() {
        return "Tools/Pickaxe/Pickaxe.png";
    }
}
