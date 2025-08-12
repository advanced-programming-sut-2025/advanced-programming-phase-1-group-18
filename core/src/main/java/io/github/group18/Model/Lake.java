package io.github.group18.Model;
import java.util.ArrayList;

public class Lake implements PictureModel
{
    protected ArrayList<Kashi> insideKashis;
    private float fishingTimer = 0f;
    private ArrayList<Cord> cords = new ArrayList<>();

    public ArrayList<Kashi> getInsideKashis() {
        return insideKashis;
    }

    public void setInsideKashis(ArrayList<Kashi> insideKashis) {
        this.insideKashis = insideKashis;
    }

    public void adaptMap(ArrayList<Cord> cords)
    {
        this.cords.clear();
        this.cords.addAll(cords);

        this.insideKashis = new ArrayList<>();
        ArrayList<Kashi> kashis = new ArrayList<>();
        for(Cord cord : cords)
        {
            Kashi kashi = new Kashi();
            kashi.setShokhmZadeh(false);
            kashi.setEnterance(false);
            kashi.setInside(this);
            kashi.setWalkable(false);
            kashis.add(kashi);
            App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);
        }
        this.insideKashis.addAll(kashis);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getLakes().add(this);
    }

    public boolean isPlayerNearForFishing(float deltaTime, float playerX, float playerY) {
        float minDistance = Float.MAX_VALUE;


        for (Cord cord : cords) {
            float dx = cord.getX() - playerX;
            float dy = cord.getY() - playerY;
            float dist = (float) Math.sqrt(dx * dx + dy * dy);

            if (dist < minDistance) {
                minDistance = dist;
            }
        }


        if (minDistance < 2f) {
            fishingTimer += deltaTime;
        } else {
            fishingTimer = 0f;
        }


        return fishingTimer >= 2f;
    }

    @Override
    public String getPath() {
        return "game/tiles/water.png";
    }
}
