package io.github.group18.Model;
import java.util.ArrayList;

public class Lake implements PictureModel
{
    protected ArrayList<Kashi> insideKashis;

    public ArrayList<Kashi> getInsideKashis() {
        return insideKashis;
    }

    public void setInsideKashis(ArrayList<Kashi> insideKashis) {
        this.insideKashis = insideKashis;
    }

    public void adaptMap(ArrayList<Cord> cords)
    {
        this.insideKashis = new ArrayList<>();
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
        this.insideKashis.addAll(kashis);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getLakes().add(this);
    }

    @Override
    public String getPath() {
        return "game/ties/water.png";
    }
}
