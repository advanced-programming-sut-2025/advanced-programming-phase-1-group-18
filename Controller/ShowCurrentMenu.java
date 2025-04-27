package Controller;

public interface ShowCurrentMenu {
    default void showCurrentMenu()
    {
        System.out.println(App.getCurrentMenu());
    }
}
