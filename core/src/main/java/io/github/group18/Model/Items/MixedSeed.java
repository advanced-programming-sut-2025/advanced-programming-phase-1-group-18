package io.github.group18.Model.Items;

import io.github.group18.Model.Name;
import io.github.group18.Model.PictureModel;

public class MixedSeed extends Seed implements Name, Price, PictureModel {
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

    @Override
    public String getPath() {
        return "Foraging/Autumn%27s_Bounty.png";
    }
}
