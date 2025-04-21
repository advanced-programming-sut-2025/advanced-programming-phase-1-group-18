package Model;

import enums.AllTreesEnums;

import java.util.HashMap;

public class AllTree extends Tree
{
    AllTreesEnums type;


    public AllTreesEnums getType() {
        return type;
    }

    public void setType(AllTreesEnums type) {
        this.type = type;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        kashi.setX(cord.getX());
        kashi.setY(cord.getY());
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getAllTrees().add(this);
        App.getCurrentGame().getPlayers()
                .get(App.getCurrentGame().getIndexPlayerinControl())
                .getMyFarm()
                .getMap().get(kashi.getX()).set(kashi.getY(), kashi);

    }
}
