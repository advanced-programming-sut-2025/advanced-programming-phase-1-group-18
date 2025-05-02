package Model;

import enums.CageAnimalsEnums;

import java.util.HashMap;

public class CageAnimal extends Animal
{
    protected Cage LivePlace;
    protected CageAnimalsEnums type;

    public CageAnimalsEnums getType() {
        return type;
    }

    public void setType(CageAnimalsEnums type) {
        this.type = type;
    }

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
