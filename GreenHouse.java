package Model;
import java.util.ArrayList;
import java.util.HashMap;

public class GreenHouse
{
    protected  boolean Status= false;
    protected ArrayList<Matarsak> Matarsaks= new ArrayList<>();
    // Getter and Setter for Status
    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    // Getter and Setter for Matarsaks
    public ArrayList<Matarsak> getMatarsaks() {
        return Matarsaks;
    }

    public void setMatarsaks(ArrayList<Matarsak> Matarsaks) {
        this.Matarsaks = Matarsaks;
    }

    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
