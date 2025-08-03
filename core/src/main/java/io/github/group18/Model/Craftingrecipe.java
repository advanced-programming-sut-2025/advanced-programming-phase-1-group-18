package io.github.group18.Model;
public class Craftingrecipe implements Name
{
    public String Name;
    public int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    @Override
    public String getCorrectName() {
        return Name.replace(" ","");
    }
}
