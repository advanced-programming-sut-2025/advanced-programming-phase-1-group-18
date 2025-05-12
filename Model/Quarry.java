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

    public void adaptMap(ArrayList<Cord> cords) {
        this.insideKashis = new ArrayList<>();
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords) {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            kashi.setInside(this);
            kashis.add(kashi);
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
        }
        this.insideKashis.addAll(kashis);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setQuarrys(new ArrayList<>());
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getQuarrys().add(this);
    }
}
