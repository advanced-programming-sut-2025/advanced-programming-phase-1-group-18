package Model.Items;

import Model.App;
import Model.Cord;
import Model.Kashi;
import Model.Name;
import enums.ForagingCropsEnums;

import java.util.ArrayList;
import java.util.HashMap;

public class ForagingCrop extends Crop implements Name, Price {
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
            case CommonMushroom: {
                this.setEnergy(38);
                this.setBaseSellPrice(40);
                break;
            }
            case Daffodil: {
                this.setEnergy(0);
                this.setBaseSellPrice(30);
                break;
            }
            case Dandelion: {
                this.setEnergy(25);
                this.setBaseSellPrice(40);
                break;
            }
            case Leek: {
                this.setEnergy(40);
                this.setBaseSellPrice(60);
                break;
            }
            case Morel: {
                this.setEnergy(20);
                this.setBaseSellPrice(150);
                break;
            }
            case Salmonberry: {
                this.setEnergy(25);
                this.setBaseSellPrice(5);
                break;
            }
            case SpringOnion: {
                this.setEnergy(13);
                this.setBaseSellPrice(8);
                break;
            }
            case WildHorseradish: {
                this.setEnergy(13);
                this.setBaseSellPrice(50);
                break;
            }
            case FiddleheadFern: {
                this.setEnergy(25);
                this.setBaseSellPrice(90);
                break;
            }
            case Grape: {
                this.setEnergy(38);
                this.setBaseSellPrice(80);
                break;
            }
            case RedMushroom: {
                this.setEnergy(-50);
                this.setBaseSellPrice(75);
                break;
            }
            case SpiceBerry: {
                this.setEnergy(25);
                this.setBaseSellPrice(80);
                break;
            }
            case SweetPea: {
                this.setEnergy(0);
                this.setBaseSellPrice(50);
                break;
            }
            case Blackberry: {
                this.setEnergy(25);
                this.setBaseSellPrice(25);
                break;
            }
            case Chanterelle: {
                this.setEnergy(75);
                this.setBaseSellPrice(160);
                break;
            }
            case Hazelnut: {
                this.setEnergy(38);
                this.setBaseSellPrice(40);
                break;
            }
            case PurpleMushroom: {
                this.setEnergy(30);
                this.setBaseSellPrice(90);
                break;
            }
            case WildPlum: {
                this.setEnergy(25);
                this.setBaseSellPrice(80);
                break;
            }
            case Crocus: {
                this.setEnergy(0);
                this.setBaseSellPrice(60);
                break;
            }
            case CrystalFruit: {
                this.setEnergy(63);
                this.setBaseSellPrice(150);
                break;
            }
            case Holly: {
                this.setEnergy(-37);
                this.setBaseSellPrice(80);
                break;
            }
            case SnowYam: {
                this.setEnergy(30);
                this.setBaseSellPrice(100);
                break;
            }
            case WinterRoot: {
                this.setEnergy(25);
                this.setBaseSellPrice(70);
                break;
            }
        }
    }

    @Override
    public String getCorrectName() {
        return type.toString().toLowerCase().replace(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return getBaseSellPrice();
    }
}
