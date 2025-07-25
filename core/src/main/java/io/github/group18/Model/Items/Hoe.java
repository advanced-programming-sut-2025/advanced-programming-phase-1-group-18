package io.github.group18.Model.Items;

import io.github.group18.Controller.GameMenuController;

import io.github.group18.Model.*;

public class Hoe extends Tool implements Name,Price,PictureModel
{
    protected String Jens;
    protected int EnergyUsage;
    protected String usage;


    public Hoe(String jens, int energyUsage) {
        Jens = jens;
        EnergyUsage = energyUsage;
    }

    public Result use(String direction) {
        Player player = App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl());
        Cord tileCord = new Cord((int) player.getX(),(int) player.getY());
        int dir_x = -1;
        int dir_y = -1;
        switch (direction.toLowerCase()) {
            case "n": {
                dir_x = 0;
                dir_y = -1;
                break;
            }
            case "ne": {
                dir_x = 1;
                dir_y = -1;
                break;
            }
            case "e": {
                dir_x = 1;
                dir_y = 0;
                break;
            }
            case "se": {
                dir_x = 1;
                dir_y = 1;
                break;
            }
            case "s": {
                dir_x = 0;
                dir_y = 1;
                break;
            }
            case "sw": {
                dir_x = -1;
                dir_y = 1;
                break;
            }
            case "w": {
                dir_x = -1;
                dir_y = 0;
                break;
            }
            case "nw": {
                dir_x = -1;
                dir_y = -1;
                break;
            }
            default:
                break;
        }
        tileCord.setX(dir_x + tileCord.getX());
        tileCord.setY(dir_y + tileCord.getY());
        boolean IsOkayOrNot = IsValidForUsing(tileCord);
        if(IsOkayOrNot)
        {
            player.setEnergy(player.getEnergy()-getEnergyUsage());
            Kashi kashi = App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY());
            kashi.setShokhmZadeh(true);
            player.getFarmingSkill().setLevel(player.getFarmingSkill().getLevel()+5);
            GameMenuController.checkSkilRecipe();
            System.out.println(tileCord.getX() + " " + tileCord.getY());
            return new Result(true,"You successfully made there shokm zadeh!");
        }
        else
        {
            player.setEnergy(player.getEnergy()-getEnergyUsage());
            return new Result(false,"You unsuccessfully made there shokm zadeh!");
        }
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
                return (int) (EnergyUsage*1.5);
            }
            case RAIN -> {
                return (int) (EnergyUsage *1.5);
            }
            case SNOW -> {
                return  (EnergyUsage *2);
            }
        }
        return EnergyUsage;
    }

    public void setEnergyUsage(int energyUsage) {
        this.EnergyUsage = energyUsage;
    }




    public boolean IsValidForUsing(Cord tileCord)
    {
        Kashi kashi = App.getCurrentGame().getMap().get(tileCord.getX()).get(tileCord.getY());
        if(!(kashi.isShokhmZadeh()) && kashi.getInside()==null)
        {
            return true;
        }
        return false;

    }
    @Override
    public String getCorrectName() {
        return "hoe";
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
        return "Tools/Hoe/Hoe.png";
    }
}
