package Model;

import enums.StoneTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Quarry {
    protected ArrayList<Kashi> insideKashis;
    protected Stone stone;

    public ArrayList<Kashi> getInsideKashis() {
        return insideKashis;
    }

    public void setInsideKashis(ArrayList<Kashi> insideKashis) {
        this.insideKashis = insideKashis;
    }


    public Stone getStone() {
        return stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
    }

    public void adaptMap(ArrayList<Cord> cords) {
        Random rand = new Random();
        this.insideKashis = new ArrayList<>();
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords) {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            Stone stone = new Stone();
            int stonetype = rand.nextInt(5);
            if (stonetype == 0) {
                stone.setType(StoneTypes.REGULAR);
            } else {
                if (stonetype == 1) {
                    stone.setType(StoneTypes.COPPER);
                } else {
                    if (stonetype == 2) {
                        stone.setType(StoneTypes.IRON);
                    } else {
                        if (stonetype == 3) {
                            stone.setType(StoneTypes.GOLD);
                        } else {
                            if (stonetype == 4) {
                                stone.setType(StoneTypes.IRIDIUM);
                            }
                        }
                    }
                }
            }
            stone.setPrice(20);
            this.setStone(stone);
            kashi.setInside(this);
            kashis.add(kashi);
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
        }
        this.insideKashis.addAll(kashis);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setQuarrys(new ArrayList<>());
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getQuarrys().add(this);
    }
}
