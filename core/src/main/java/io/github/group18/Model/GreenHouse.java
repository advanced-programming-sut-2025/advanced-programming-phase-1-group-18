package io.github.group18.Model;

import java.util.ArrayList;

public class GreenHouse implements PictureModel{
    public boolean Status = false;



    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public void adaptMap(ArrayList<Cord> cords, int Enterancex, int Enterancey, int borderlowx, int borderlowy, int borderhighx, int borderhighy) {
        ArrayList<Kashi> kashis = new ArrayList<>();
        for (Cord cord : cords) {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            if (cord.getX() == Enterancex && cord.getY() == Enterancey) {
                kashi.setEnterance(true);
            } else {
                kashi.setEnterance(false);
            }
            kashi.setInside(this);
            if (cord.getX() == borderlowx || cord.getX() == borderhighx || cord.getY() == borderlowy || cord.getY() == borderhighy) {
                kashi.setWalkable(kashi.getEnterance());
            } else {
                kashi.setWalkable(true);
            }
            kashis.add(kashi);
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
        }
        //Server-TODO
//        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setMyGreenHouse(this);
//        System.out.println(" Building: " +
//            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl())+ " " +
//            App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getMyGreenHouse().hashCode());
    }

    @Override
    public String getPath() {
        return "GreenHouse/greenhouse.png";
    }
}
