package Model;

import enums.TavilehAnimalEnums;

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
    protected TavilehAnimalEnums type;

    public TavilehAnimalEnums getType() {
        return type;
    }

    public void setType(TavilehAnimalEnums type) {
        this.type = type;
    }

    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
