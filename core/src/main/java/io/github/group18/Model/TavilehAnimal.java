package io.github.group18.Model;

import io.github.group18.enums.TavilehAnimalEnums;

import java.util.HashMap;

public class TavilehAnimal extends Animal implements PictureModel
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

    public String getImagePath() {
        return type.getImagePath();
    }

    @Override
    public String getPath() {
        return type.getImagePath();
    }
}
