package Model;

import java.util.ArrayList;
import java.util.HashMap;

public class Stone
{

    protected int Price;

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setStones(new ArrayList<>());
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().add(this);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);

    }
}
