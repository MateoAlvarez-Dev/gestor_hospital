import database.ConnectDB;
import views.GeneralMenu;

// CODE CLEANER -> document.body.innerHTML = str.split("\n").map(el => el.replaceAll("Specialty", "Appointment").replaceAll("specialties", "appointments")).join("<br>");

// RECUERDO: FALTA DOCTOR Y APPOINTMENT CONTROLLER

public class Main {
    public static void main(String[] args) {
        GeneralMenu menu = new GeneralMenu();
        menu.render();
    }
}