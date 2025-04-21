package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class Quarry
{
    protected ArrayList<Kashi> insideKashis;

    public ArrayList<Kashi> getInsideKashis() {
        return insideKashis;
    }

    public void setInsideKashis(ArrayList<Kashi> insideKashis) {
        this.insideKashis = insideKashis;
    }

    public void adaptMap(ArrayList<Cord> cords,int Enterancex, int Enterancey,int borderlowx,int borderlowy,int borderhighx,int borderhighy) {
        this.insideKashis = new ArrayList<>();
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords) {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            if (cord.getX() == Enterancex && cord.getY() == Enterancey) {
                kashi.setEnterance(true);
            } else {
                kashi.setEnterance(false);
            }
            kashi.setX(cord.getX());
            kashi.setY(cord.getY());
            kashi.setInside(this);
            if (cord.getX() == borderlowx || cord.getX() == borderhighx || cord.getY() == borderlowy || cord.getY() == borderhighy) {
                kashi.setWalkable(false);
            } else {
                kashi.setWalkable(true);
            }
            kashis.add(kashi);
        }
        this.insideKashis.addAll(kashis);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getQuarrys().add(this);
        for(Kashi kashi : kashis)
        {
            App.getCurrentGame().getPlayers()
                    .get(App.getCurrentGame().getIndexPlayerinControl())
                    .getMyFarm()
                    .getMap().get(kashi.getX()).set(kashi.getY(), kashi);
        }
    }
}
