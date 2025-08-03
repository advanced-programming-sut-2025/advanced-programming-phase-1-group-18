package io.github.group18.Model;

import io.github.group18.Model.Items.Item;

public class Kashi
{
    public Boolean ShokhmZadeh;
    public Object inside;
    public Boolean Enterance;
    public Boolean Walkable;

    public Kashi() {
    }

    // Getter و Setter برای ShokhmZadeh
    public Boolean isShokhmZadeh() {
        return ShokhmZadeh;
    }

    public void setShokhmZadeh(Boolean ShokhmZadeh) {
        this.ShokhmZadeh = ShokhmZadeh;
    }

    public Boolean getEnterance() {
        return Enterance;
    }

    public void setEnterance(Boolean enterance) {
        Enterance = enterance;
    }

    public Object getInside() {
        return inside;
    }

    public void setInside(Object inside) {
        this.inside = inside;
    }

    public Boolean getWalkable() {
        return Walkable;
    }

    public void setWalkable(Boolean walkable) {
        Walkable = walkable;
    }

}
