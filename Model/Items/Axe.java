package Model.Items;

import Controller.GameMenuController;
import Model.*;

public class Axe extends Tool implements Name,Price
{
    protected String Jens;
    protected int EnergyUsage;
    protected String usage;

    public Axe(String jens, int energyUsage) {
        Jens = jens;
        EnergyUsage = energyUsage;
    }
    public Result use(String direction)
    {
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Cord tileCord = new Cord(player.getX(), player.getY());
        int dir_x = -1;
        int dir_y = -1;
        System.out.println(direction);
        switch (direction.toLowerCase())
        {
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
        tileCord.setX(dir_x+tileCord.getX());
        tileCord.setY(dir_y+tileCord.getY());
        //System.out.println("khar1");
        Kashi kashi = App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY());

        //System.out.println("khar2");
        Tool mytool=  App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getInMyHandTool();
        Axe myaxe = (Axe) mytool;
        if(!(kashi.getInside() instanceof AllTree))
        {

            if(myaxe.getJens().equalsIgnoreCase("initial") && (player.getFarmingSkill().getLevel()>=350))
            {
                //System.out.println("khar3.5");
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                return new Result(false,"You used it unsuccessfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("initial") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                return new Result(false,"You used it unsuccessfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("copper") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                return new Result(false,"You used it unsuccessfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("copper") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                return new Result(false,"You used it unsuccessfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("iron") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                return new Result(false,"You used it unsuccessfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("iron") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                return new Result(false,"You used it unsuccessfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("gold") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                return new Result(false,"You used it unsuccessfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("gold") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                return new Result(false,"You used it unsuccessfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("iridium") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                return new Result(false,"You used it unsuccessfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("iridium") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                return new Result(false,"You used it unsuccessfully!");
            }
        }
        if(kashi.getInside() instanceof AllTree)
        {
            if(myaxe.getJens().equalsIgnoreCase("iridium") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("iridium") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("gold") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("gold") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("iron") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("iron") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                player.setWood(player.getWood()+10);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                return new Result(false,"You used it successfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("copper") && (player.getFarmingSkill().getLevel()>=350))
            {
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("copper") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!");
            }
            //
            if(myaxe.getJens().equalsIgnoreCase("initial") && (player.getFarmingSkill().getLevel()>=350))
            {
                //System.out.println("khar3.5");
                if(player.getEnergy()-EnergyUsage+2<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+2);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!(With Max Mining Level)");
            }
            if(myaxe.getJens().equalsIgnoreCase("initial") && (player.getFarmingSkill().getLevel()<350))
            {
                if(player.getEnergy()-EnergyUsage+1<0)
                {
                    return new Result(false,"Your energy is not enough!");
                }
                kashi.setInside(null);
                player.setEnergy(player.getEnergy()-EnergyUsage+1);
                player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+10);
                GameMenuController.checkSkilRecipe();
                player.setWood(player.getWood()+10);
                return new Result(false,"You used it successfully!");
            }

        }
        return new Result(false,"what?");
    }
    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public int getEnergyUsage() {
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        EnergyUsage = energyUsage;
    }

    public String getJens() {
        return Jens;
    }

    public void setJens(String jens) {
        Jens = jens;
    }

    @Override
    public String getCorrectName() {
        return "axe";
    }

    @Override
    public int getCorrectPrice() {
        return 0;
    }
}
