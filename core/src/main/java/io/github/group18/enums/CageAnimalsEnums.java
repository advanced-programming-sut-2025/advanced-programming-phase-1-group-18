package io.github.group18.enums;

public enum CageAnimalsEnums
{
    Chicken,
    Duck,
    Rabbit,
    Dinosaur;

    public String getImagePath() {
    return "animals/" + this.name().toLowerCase() + ".png";
}
}
