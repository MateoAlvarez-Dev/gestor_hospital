package views;

import controller.DoctorController;
import controller.PatientController;

import javax.swing.*;

public class DoctorMenu {

    DoctorController objDoctorController;

    public DoctorMenu(){
        objDoctorController = new DoctorController();
    }

    public void render(){
        do{

            String option;
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. List Doctors
                    2. Insert Doctor
                    3. Update Doctor
                    4. Delete Doctor
                    6. Back
                    
                    Choose an option: 
                    """);

            switch (option){
                case "1":
                    objDoctorController.getAll();
                    break;

                case "2":
                    objDoctorController.create();
                    break;

                case "3":
                    objDoctorController.update();
                    break;

                case "4":
                    objDoctorController.delete();
                    break;

                case "6":
                    return;
            }

        }while (true);
    }

}
