package views;

import controller.AppointmentController;

import javax.swing.*;

public class AppointmentMenu {

    AppointmentController objAppointmentController;

    public AppointmentMenu(){
        objAppointmentController = new AppointmentController();
    }

    public void render(){
        do{

            String option;
            option = JOptionPane.showInputDialog("""
                    MENU
                    1. List Appointments
                    2. Insert Appointment
                    3. Update Appointment
                    4. Search Appointments by date
                    5. Delete Appointment
                    6. Back
                    
                    Choose an option: 
                    """);

            switch (option){
                case "1":
                    objAppointmentController.getAll();
                    break;

                case "2":
                    objAppointmentController.create();
                    break;

                case "3":
                    objAppointmentController.update();
                    break;

                case "4":
                    objAppointmentController.getByDate();
                    break;

                case "5":
                    objAppointmentController.delete();
                    break;

                case "6":
                    return;
            }

        }while (true);
    }

}