package views;

import controller.SpecialtyController;

import javax.swing.*;

public class GeneralMenu {
    SpecialtiesMenu specialtiesMenu;
    PatientMenu patientMenu;

    public GeneralMenu(){
        specialtiesMenu = new SpecialtiesMenu();
        patientMenu = new PatientMenu();
    }

    public void render(){
        String option = "";
        do{

            String authorOption;
            authorOption = JOptionPane.showInputDialog("""
                    MENU
                    1. Specialties Menu
                    2. Patients Menu
                    
                    Choose an option: 
                    """);

            switch (authorOption){
                case "1":
                    specialtiesMenu.render();
                    break;

                case "2":
                    patientMenu.render();
                    break;
            }

        }while (!option.equals("6"));
    }
}
