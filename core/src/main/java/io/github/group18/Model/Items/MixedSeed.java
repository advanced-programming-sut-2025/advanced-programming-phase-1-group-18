package io.github.group18.Model.Items;

import io.github.group18.Model.Name;

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
