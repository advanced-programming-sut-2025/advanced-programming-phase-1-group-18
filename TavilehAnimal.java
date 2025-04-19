package Model;

import java.util.HashMap;

public class TavilehAnimal extends Animal
{
    public Tavileh getLivePlace() {
        return LivePlace;
    }

    public void setLivePlace(Tavileh livePlace) {
        this.LivePlace = livePlace;
    }

    protected Tavileh LivePlace;
    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
