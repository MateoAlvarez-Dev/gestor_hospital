package views;

import controller.SpecialtyController;

import javax.swing.*;

public class GeneralMenu {
    SpecialtiesMenu specialtiesMenu;
    PatientMenu patientMenu;
    DoctorMenu doctorMenu;

    public GeneralMenu(){
        specialtiesMenu = new SpecialtiesMenu();
        patientMenu = new PatientMenu();
        doctorMenu = new DoctorMenu();
    }

    public void render(){
        do{

            String option;
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. Specialties Menu
                    2. Patients Menu
                    3. Doctors Menu
                    
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

                case "6":
                    return;
            }

        }while (true);
    }
}
