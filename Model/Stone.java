package Model;

import enums.StoneTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Stone
{
    protected StoneTypes type;
    protected int Price;

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }


    public StoneTypes getType() {
        return type;
    }

    public void setType(StoneTypes type) {
        this.type = type;
    }

    public void adaptMap(Cord cord) {
        Random rand = new Random();
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        int stonetype = rand.nextInt(5);
        if (stonetype == 0) {
            this.setType(StoneTypes.REGULAR);
        } else {
            if (stonetype == 1) {
                this.setType(StoneTypes.COPPER);
            } else {
                if (stonetype == 2) {
                    this.setType(StoneTypes.IRON);
                } else {
                    if (stonetype == 3) {
                        this.setType(StoneTypes.GOLD);
                    } else {
                        if (stonetype == 4) {
                            this.setType(StoneTypes.IRIDIUM);
                        }
                    }
                }
            }
        }
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setStones(new ArrayList<>());
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getStones().add(this);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);

    }
}
