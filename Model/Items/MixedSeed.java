package Model.Items;

import enums.MixedSeedsEnums;

public class MixedSeed extends Source {
    MixedSeedsEnums type;

    public MixedSeedsEnums getType() {
        return type;
    }

    public void setType(MixedSeedsEnums type) {
        this.type = type;
    }
}
