package Model;

import java.util.HashMap;

public class Stone
{

    protected int Price;
    protected Season Season = new Season();

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        this.Price = price;
    }

    public Season getSeason() {
        return Season;
    }

    public void setSeason(Season season) {
        this.Season = season;
    }

    public void changeseason()
    {

    }
    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
