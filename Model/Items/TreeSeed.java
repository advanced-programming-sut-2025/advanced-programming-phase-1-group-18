package io.githubgroup18.Model.Items;
import io.githubgroup18.Model.Name;
import io.githubgroup18.enums.TreeSeedEnums;

public class TreeSeed extends Seed implements Name, Price {

    TreeSeedEnums type;
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public TreeSeedEnums getType() {
        return type;
    }

    public void setType(TreeSeedEnums type) {
        this.type = type;
    }


    @Override
    public String getCorrectName() {
        return type.toString().toLowerCase().replace(" ", "");
    }

    @Override
    public int getCorrectPrice() {
        return this.price;
    }
}
