package Model.Items;

import Model.App;
import Model.Cord;
import Model.Kashi;
import Model.Name;
import enums.ForagingSeedsEnums;

import java.util.ArrayList;


public class ForagingSeed extends Seed implements Name,Price
{
    ForagingSeedsEnums type;
    private int price;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ForagingSeedsEnums getType() {
        return type;
    }

    public void setType(ForagingSeedsEnums type) {
        this.type = type;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setForagingSeeds(new ArrayList<>());
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getForagingSeeds().add(this);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
    }

    @Override
    public String getCorrectName() {
        return type.toString().toLowerCase().replace(" ","");
    }

    @Override
    public int getCorrectPrice() {
        return this.price;
    }
}
