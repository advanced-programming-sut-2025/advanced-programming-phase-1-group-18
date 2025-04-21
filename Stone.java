package Model;

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
        kashi.setX(cord.getX());
        kashi.setY(cord.getY());
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().add(this);
        App.getCurrentGame().getPlayers()
                .get(App.getCurrentGame().getIndexPlayerinControl())
                .getMyFarm()
                .getMap().get(kashi.getX()).set(kashi.getY(), kashi);

    }
}
