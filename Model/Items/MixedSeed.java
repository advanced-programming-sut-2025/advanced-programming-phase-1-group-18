package Model.Items;

import enums.ForagingSeedsEnums;
import enums.MixedSeedsEnums;

public class MixedSeed extends Seed
{
    MixedSeedsEnums type;

    public MixedSeedsEnums getType() {
        return type;
    }

    public void setType(MixedSeedsEnums type) {
        this.type = type;
    }
}
