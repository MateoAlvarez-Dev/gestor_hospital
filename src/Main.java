import database.ConnectDB;
import views.GeneralMenu;

// CODE CLEANER -> document.body.innerHTML = str.split("\n").map(el => el.replaceAll("Specialty", "Appointment").replaceAll("specialties", "appointments")).join("<br>");
// SELECT * FROM appointments a INNER JOIN doctors d ON d.id = a.id_doctor INNER JOIN patients p ON p.id = a.id_patient;
// RECUERDO: FALTA APPOINTMENT CONTROLLER

public class Main {
    public static void main(String[] args) {
        GeneralMenu menu = new GeneralMenu();
        menu.render();
    }
}