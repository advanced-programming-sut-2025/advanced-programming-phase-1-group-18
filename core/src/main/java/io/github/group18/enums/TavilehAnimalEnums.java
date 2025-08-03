package io.github.group18.enums;

public enum TavilehAnimalEnums
{
    Cow,
    Sheep,
    Goat,
    Pig;

    public String getImagePath() {
        return "animals/" + this.name().toLowerCase() + ".png";
    }
}
