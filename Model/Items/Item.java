package Model.Items;

import Model.Name;

public class Item implements Name, Price {
    protected String name;  // یا public

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getCorrectName() {
        return "";
    }


    @Override
    public int getCorrectPrice() {
        return 0;
    }
}
