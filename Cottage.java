package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class Cottage
{
    //    protected ArrayList<ArrayList<Kashi>> CottageMap = new ArrayList<>();
    protected Kashi Enterance = new Kashi();
    // Getter and Setter for CottageMap
//    public ArrayList<ArrayList<Kashi>> getCottageMap() {
//        return CottageMap;
//    }
//
//    public void setCottageMap(ArrayList<ArrayList<Kashi>> CottageMap) {
//        this.CottageMap = CottageMap;
//    }

    // Getter and Setter for Enterance
    public Kashi getEnterance() {
        return Enterance;
    }

    public void setEnterance(Kashi Enterance) {
        this.Enterance = Enterance;
    }
    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
