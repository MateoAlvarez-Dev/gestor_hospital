package views;

import controller.PatientController;

import javax.swing.*;

public class PatientMenu {

    PatientController objPatientController;

    public PatientMenu(){
        objPatientController = new PatientController();
    }

    public void render(){
        do{

            String option;
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. List Patients
                    2. Insert Patient
                    3. Update Patient
                    4. Delete Patient
                    5. Find Patient by identity
                    6. Back
                    
                    Choose an option: 
                    """);

            switch (option){
                case "1":
                    objPatientController.getAll();
                    break;

                case "2":
                    objPatientController.create();
                    break;

                case "3":
                    objPatientController.update();
                    break;

                case "4":
                    objPatientController.delete();
                    break;

                case "5":
                    objPatientController.getByIdentity();
                    break;

                case "6":
                    return;
            }

        }while (true);
    }

}
