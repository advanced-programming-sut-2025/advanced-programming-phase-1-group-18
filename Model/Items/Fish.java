package Model.Items;

import Model.Name;

public class Fish implements Name,Price
{

    @Override
    public String getCorrectName() {
        return "";
    }

    @Override
    public int getCorrectPrice() {
        return 0;
    }
}
