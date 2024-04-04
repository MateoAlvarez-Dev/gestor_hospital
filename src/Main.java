import database.ConnectDB;
import views.GeneralMenu;

public class Main {
    public static void main(String[] args) {
        GeneralMenu menu = new GeneralMenu();
        menu.render();
    }
}