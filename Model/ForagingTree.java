package io.github.group18.Model;

import io.github.group18.enums.ForagingTreesEnums;

import java.util.ArrayList;

public class ForagingTree extends Tree {
    ForagingTreesEnums type;

    public ForagingTreesEnums getType() {
        return type;
    }

    public void setType(ForagingTreesEnums type) {
        this.type = type;
    }

    public void adaptMap(Cord cord) {
        Kashi kashi = new Kashi();
        kashi.setShokhmZadeh(false);
        kashi.setEnterance(false);
        kashi.setInside(this);
        kashi.setWalkable(false);
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().setForagingTrees(new ArrayList<>());
        App.getCurrentGame().getPlayers().get(App.getCurrentGame().getIndexPlayerinControl()).getMyFarm().getForagingTrees().add(this);
        App.getCurrentGame().getMap().get(cord.getX()).set(cord.getY(), kashi);

    }

    public void initilizeCrop(ForagingTreesEnums foragingTreesEnums) {
        this.setType(foragingTreesEnums);
    }

}
