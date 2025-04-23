package Model.Items;

import Model.App;
import Model.Cord;
import Model.Kashi;
import enums.ForagingCropsEnums;

import java.util.HashMap;

public class ForagingCrop extends Crop
{
    ForagingCropsEnums type;


    public ForagingCropsEnums getEnumType() {
        return type;
    }

    public void setType(ForagingCropsEnums type) {
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
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getForagingCrops().add(this);
        App.getCurrentGame().getPlayers()
                .get(App.getCurrentGame().getIndexPlayerinControl())
                .getMyFarm()
                .getMap().get(kashi.getX()).set(kashi.getY(), kashi);

    }
}
