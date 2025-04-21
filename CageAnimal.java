package Model;

import java.util.HashMap;

public class CageAnimal extends Animal
{
    protected Cage LivePlace;

    public Cage getLivePlace() {
        return LivePlace;
    }

    public void setLivePlace(Cage livePlace) {
        LivePlace = livePlace;
    }

    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
