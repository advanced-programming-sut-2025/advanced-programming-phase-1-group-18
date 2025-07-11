package io.githubgroup18.Model.Items;
import io.githubgroup18.Model.Name;
import io.githubgroup18.enums.ForagingSeedsEnums;
import io.githubgroup18.enums.MixedSeedsEnums;

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
