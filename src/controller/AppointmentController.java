package controller;

import entity.Appointment;
import model.AppointmentModel;

import javax.swing.*;
import java.util.List;

public class AppointmentController {

    private AppointmentModel objAppointmentModel;

    public AppointmentController() {
        this.objAppointmentModel = new AppointmentModel();
    }

    public void delete() {
        String listAppointmentString = this.getAll(this.objAppointmentModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAppointmentString + "Enter the ID of the appointment to delete"));
        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idDelete);

        if (objAppointment == null){
            JOptionPane.showMessageDialog(null,"Appointment not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are your sure want to delete the appointment: ");

            if (confirm == 0){
                this.objAppointmentModel.delete(objAppointment);
            }
        }
    }

    public void getAll() {

        String list = this.getAll(this.objAppointmentModel.findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public String getAll(List listObject){
        String list = "Appointment List";

        for (Object obj : listObject) {
            Appointment objAppointment = (Appointment) obj;
            list += objAppointment.getId() + " - ";
        }

        return list;
    }

    public void create() {
        Appointment objAppointment = new Appointment();

        String name = JOptionPane.showInputDialog("Insert name: ");
        String lastName = JOptionPane.showInputDialog("Insert last name: ");
        String identity = JOptionPane.showInputDialog("Insert identity: ");
        String birth = JOptionPane.showInputDialog("Insert birth date in format AAAA-MM-DD: ");

        objAppointment = (Appointment) this.objAppointmentModel.insert(objAppointment);

        JOptionPane.showMessageDialog(null, objAppointment.getId() + " - ");

    }

    public void update(){
        String listAppointments = this.getAll(this.objAppointmentModel.findAll());

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAppointments +"-Enter the ID of the Appointment to edit"));

        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idUpdate);

        if (objAppointment == null){
            JOptionPane.showMessageDialog(null, "Appointment not found.");
        }else {
            this.objAppointmentModel.update(objAppointment);
        }
    }
}