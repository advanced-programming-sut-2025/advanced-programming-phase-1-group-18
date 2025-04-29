package Model.Items;

import Model.App;
import Model.Cord;
import Model.Kashi;
import enums.ForagingSeedsEnums;


public class ForagingSeed extends Seed
{
    ForagingSeedsEnums type;


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
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getForagingSeeds().add(this);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
    }
}
