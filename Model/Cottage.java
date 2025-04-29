package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class Cottage
{
    protected ArrayList<Kashi> insideKashis;
    protected Refrigerator myRefrigerator;
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
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
            kashi.setInside(this);
            if (cord.getX() == borderlowx || cord.getX() == borderhighx || cord.getY() == borderlowy || cord.getY() == borderhighy) {
                kashi.setWalkable(kashi.getEnterance());
            } else {
                kashi.setWalkable(true);
            }
            kashis.add(kashi);
        }
        this.insideKashis.addAll(kashis);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyCottage(this);
    }

    public Refrigerator getMyRefrigerator() {
        return myRefrigerator;
    }

    public void setMyRefrigerator(Refrigerator myRefrigerator) {
        this.myRefrigerator = myRefrigerator;
    }
}
