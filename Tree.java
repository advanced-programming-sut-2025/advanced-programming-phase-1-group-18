package Model;

import java.util.HashMap;

public class Tree
{
    protected Kashi TreesMap = new Kashi();

    public boolean isGhoolPeikar() {
        return GhoolPeikar;
    }

    public void setGhoolPeikar(boolean ghoolPeikar) {
        this.GhoolPeikar = ghoolPeikar;
    }

    public Kashi getTreesMap() {
        return TreesMap;
    }

    public void setTreesMap(Kashi treesMap) {
        this.TreesMap = treesMap;
    }

    protected boolean GhoolPeikar;
    public void adaptMap(HashMap<Integer ,Integer> LakeMap)
    {

    }
}
