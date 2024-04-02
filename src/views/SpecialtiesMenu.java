package views;

import controller.SpecialtyController;

import javax.swing.*;

public class SpecialtiesMenu {

    SpecialtyController objSpecialtyController;

    public SpecialtiesMenu(){
        objSpecialtyController = new SpecialtyController();
    }

    public void render(){
        do{

            String option;
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. List Specialties
                    2. Insert Specialty
                    3. Update Specialty
                    4. Delete Specialty
                    6. Back
                    
                    Choose an option: 
                    """);

            switch (option){
                case "1":
                    objSpecialtyController.getAll();
                    break;

                case "2":
                    objSpecialtyController.create();
                    break;

                case "3":
                    objSpecialtyController.update();
                    break;

                case "4":
                    objSpecialtyController.delete();
                    break;

                case "6":
                    return;
            }

        }while (true);
    }
}
