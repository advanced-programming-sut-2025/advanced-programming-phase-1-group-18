package Model;
import java.util.ArrayList;

public class Farm
{





    protected ArrayList<ArrayList<Kashi>> Map= new ArrayList<>();
    protected ArrayList<Lake>  Lakes= new ArrayList<>();
    protected GreenHouse myGreenHouse= new GreenHouse();
    protected Cottage myCottage= new Cottage();
    protected  Quarry myQuarry= new Quarry();
    public void mapCreator (){

    }
    // Getter and Setter for Map
    public ArrayList<ArrayList<Kashi>> getMap() {
        return Map;
    }

    public void setMap(ArrayList<ArrayList<Kashi>> Map) {
        this.Map = Map;
    }

    // Getter and Setter for Lakes
    public ArrayList<Lake> getLakes() {
        return Lakes;
    }

    public void setLakes(ArrayList<Lake> Lakes) {
        this.Lakes = Lakes;
    }

    // Getter and Setter for myGreenHouse
    public GreenHouse getMyGreenHouse() {
        return myGreenHouse;
    }

    public void setMyGreenHouse(GreenHouse myGreenHouse) {
        this.myGreenHouse = myGreenHouse;
    }

    // Getter and Setter for myCottage
    public Cottage getMyCottage() {
        return myCottage;
    }

    public void setMyCottage(Cottage myCottage) {
        this.myCottage = myCottage;
    }

    // Getter and Setter for myQuarry
    public Quarry getMyQuarry() {
        return myQuarry;
    }

    public void setMyQuarry(Quarry myQuarry) {
        this.myQuarry = myQuarry;
    }

}
