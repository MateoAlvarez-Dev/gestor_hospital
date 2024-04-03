package views;

import javax.swing.*;

public class GeneralMenu {
    SpecialtiesMenu specialtiesMenu;
    PatientMenu patientMenu;
    DoctorMenu doctorMenu;
    AppointmentMenu appointmentMenu;

    public GeneralMenu(){
        this.specialtiesMenu = new SpecialtiesMenu();
        this.patientMenu = new PatientMenu();
        this.doctorMenu = new DoctorMenu();
        this.appointmentMenu = new AppointmentMenu();
    }

    public void render(){
        do{

            String option;
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Specialties Menu
                    2. Patients Menu
                    3. Doctors Menu
                    4. Appointment Menu
                    
                    Choose an option: 
                    """);

            switch (option){
                case "1":
                    specialtiesMenu.render();
                    break;

                case "2":
                    patientMenu.render();
                    break;

                case "3":
                    doctorMenu.render();
                    break;

                case "4":
                    appointmentMenu.render();
                    break;

                case "6":
                    return;
            }

        }while (true);
    }
}
