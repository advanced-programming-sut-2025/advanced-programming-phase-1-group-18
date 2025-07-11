package io.githubgroup18.Model.Items;
import io.githubgroup18.Model.Name;

public class Flower extends Item implements Name, Price {

    @Override
    public int getCorrectPrice() {
        return 200;
    }

    @Override
    public String getCorrectName() {
        return "flower";
    }
}
