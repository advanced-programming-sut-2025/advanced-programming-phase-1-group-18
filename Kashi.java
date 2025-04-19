package Model;
public class Kashi
{
    protected boolean ShokhmZadeh;
    protected int x;
    protected int y;
    protected Object inside;
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
    // Getter and Setter for the content
    public Object getContent() {
        return inside;
    }

    public void setContent(Object content) {
        this.inside = content;
    }

}
