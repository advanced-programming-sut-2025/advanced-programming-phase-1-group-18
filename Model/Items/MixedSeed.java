package Model.Items;

import Model.Name;
import enums.ForagingSeedsEnums;
import enums.MixedSeedsEnums;

public class MixedSeed extends Seed implements Name, Price {
//    MixedSeedsEnums type;
//
//    public MixedSeedsEnums getType() {
//        return type;
//    }
//
//    public void setType(MixedSeedsEnums type) {
//        this.type = type;
//    }

    @Override
    public String getCorrectName() {
        return "mixedseeds";
    }

    @Override
    public int getCorrectPrice() {
        return 0;
    }
}
