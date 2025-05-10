package Model.Items;

import Model.App;
import Model.Cord;
import Model.Kashi;
import Model.Name;
import enums.ForagingCropsEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class ForagingCrop extends Crop implements Name ,Price{
    ForagingCropsEnums type;


    public ForagingCropsEnums getType() {
        return type;
    }

    public void setType(ForagingCropsEnums type) {
        this.type = type;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(true);
        kashi.setEnterance(false);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setForagingCrops(new ArrayList<>());
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getForagingCrops().add(this);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
    }


    public void initilizeCrop(ForagingCropsEnums foragingCropsEnums) {
        this.setType(foragingCropsEnums);
        switch (this.getType()) {
            case ForagingCropsEnums.CommonMushroom: {
                this.setEnergy(38);
                this.setBaseSellPrice(40);
                break;
            }
            case ForagingCropsEnums.Daffodil: {
                this.setEnergy(0);
                this.setBaseSellPrice(30);
                break;
            }
            case ForagingCropsEnums.Dandelion: {
                this.setEnergy(25);
                this.setBaseSellPrice(40);
                break;
            }
            case ForagingCropsEnums.Leek: {
                this.setEnergy(40);
                this.setBaseSellPrice(60);
                break;
            }
            case ForagingCropsEnums.Morel: {
                this.setEnergy(20);
                this.setBaseSellPrice(150);
                break;
            }
            case ForagingCropsEnums.Salmonberry: {
                this.setEnergy(25);
                this.setBaseSellPrice(5);
                break;
            }
            case ForagingCropsEnums.SpringOnion: {
                this.setEnergy(13);
                this.setBaseSellPrice(8);
                break;
            }
            case ForagingCropsEnums.WildHorseradish: {
                this.setEnergy(13);
                this.setBaseSellPrice(50);
                break;
            }
            case ForagingCropsEnums.FiddleheadFern: {
                this.setEnergy(25);
                this.setBaseSellPrice(90);
                break;
            }
            case ForagingCropsEnums.Grape: {
                this.setEnergy(38);
                this.setBaseSellPrice(80);
                break;
            }
            case ForagingCropsEnums.RedMushroom: {
                this.setEnergy(-50);
                this.setBaseSellPrice(75);
                break;
            }
            case ForagingCropsEnums.SpiceBerry: {
                this.setEnergy(25);
                this.setBaseSellPrice(80);
                break;
            }
            case ForagingCropsEnums.SweetPea: {
                this.setEnergy(0);
                this.setBaseSellPrice(50);
                break;
            }
            case ForagingCropsEnums.Blackberry: {
                this.setEnergy(25);
                this.setBaseSellPrice(25);
                break;
            }
            case ForagingCropsEnums.Chanterelle: {
                this.setEnergy(75);
                this.setBaseSellPrice(160);
                break;
            }
            case ForagingCropsEnums.Hazelnut: {
                this.setEnergy(38);
                this.setBaseSellPrice(40);
                break;
            }
            case ForagingCropsEnums.PurpleMushroom: {
                this.setEnergy(30);
                this.setBaseSellPrice(90);
                break;
            }
            case ForagingCropsEnums.WildPlum: {
                this.setEnergy(25);
                this.setBaseSellPrice(80);
                break;
            }
            case ForagingCropsEnums.Crocus: {
                this.setEnergy(0);
                this.setBaseSellPrice(60);
                break;
            }
            case ForagingCropsEnums.CrystalFruit: {
                this.setEnergy(63);
                this.setBaseSellPrice(150);
                break;
            }
            case ForagingCropsEnums.Holly: {
                this.setEnergy(-37);
                this.setBaseSellPrice(80);
                break;
            }
            case ForagingCropsEnums.SnowYam: {
                this.setEnergy(30);
                this.setBaseSellPrice(100);
                break;
            }
            case ForagingCropsEnums.WinterRoot: {
                this.setEnergy(25);
                this.setBaseSellPrice(70);
                break;
            }
        }
    }

    @Override
    public String getCorrectName() {
        return type.toString().toLowerCase().replace(" ","");
    }

    @Override
    public int getCorrectPrice() {
        return getBaseSellPrice();
    }
}
