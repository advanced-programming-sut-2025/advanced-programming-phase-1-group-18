package io.github.group18.Model;
import java.util.ArrayList;

public class Lake implements PictureModel
{

    public void adaptMap(ArrayList<Cord> cords)
    {
        ArrayList<Kashi> kashis = new ArrayList<>();
        for(Cord cord : cords)
        {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            kashi.setEnterance(false);
            kashi.setInside(this);
            kashi.setWalkable(false);
            kashis.add(kashi);
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
        }
        //Server-TODO
//        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getLakes().add(this);
    }

    @Override
    public String getPath() {
        return "game/tiles/water.png";
    }
}
