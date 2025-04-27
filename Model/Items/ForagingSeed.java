package Model.Items;


public class ForagingSeed extends Source {
    ForagingSeedsEnums type;


    public ForagingSeedsEnums getType() {
        return type;
    }

    public void setType(ForagingSeedsEnums type) {
        this.type = type;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        kashi.setX(cord.getX());
        kashi.setY(cord.getY());
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getForagingSeeds().add(this);
        App.getCurrentGame().getPlayers()
                .get(App.getCurrentGame().getIndexPlayerinControl())
                .getMyFarm()
                .getMap().get(kashi.getX()).set(kashi.getY(), kashi);

    }
}
