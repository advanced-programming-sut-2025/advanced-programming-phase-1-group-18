package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class GreenHouse {
    protected boolean Status = false;
    protected ArrayList<Matarsak> Matarsaks = new ArrayList<>();
    protected ArrayList<Kashi> insideKashis;

    public ArrayList<Kashi> getInsideKashis() {
        return insideKashis;
    }

    public void setInsideKashis(ArrayList<Kashi> insideKashis) {
        this.insideKashis = insideKashis;
    }

    // Getter and Setter for Status
    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    // Getter and Setter for Matarsaks
    public ArrayList<Matarsak> getMatarsaks() {
        return Matarsaks;
    }

    public void setMatarsaks(ArrayList<Matarsak> Matarsaks) {
        this.Matarsaks = Matarsaks;
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
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyGreenHouse(this);
        for(Kashi kashi : kashis)
        {
            App.getCurrentGame().getPlayers()
                    .get(App.getCurrentGame().getIndexPlayerinControl())
                    .getMyFarm()
                    .getMap().get(kashi.getX()).set(kashi.getY(), kashi);
        }
    }
}
