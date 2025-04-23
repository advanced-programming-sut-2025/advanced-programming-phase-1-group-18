package Model;
public class Kashi
{
    protected boolean ShokhmZadeh;
    protected int x;
    protected int y;
    protected Object inside;
    protected Boolean Enterance;
    protected Boolean Walkable;
    // Getter و Setter برای ShokhmZadeh
    public boolean isShokhmZadeh() {
        return ShokhmZadeh;
    }

    public void setShokhmZadeh(boolean ShokhmZadeh) {
        this.ShokhmZadeh = ShokhmZadeh;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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
