package Model;

import Model.Items.Item;

public class Kashi
{
    protected boolean ShokhmZadeh;
    protected Object inside;
    protected Item itemInside;
    protected boolean Enterance;
    protected boolean Walkable;
    // Getter و Setter برای ShokhmZadeh
    public boolean isShokhmZadeh() {
        return ShokhmZadeh;
    }

    public void setShokhmZadeh(boolean ShokhmZadeh) {
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

    public Item getItemInside() {
        return itemInside;
    }

    public void setItemInside(Item itemInside) {
        this.itemInside = itemInside;
    }
}