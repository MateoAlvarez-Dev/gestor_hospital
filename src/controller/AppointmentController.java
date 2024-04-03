package controller;

import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import model.AppointmentModel;
import model.DoctorModel;
import model.PatientModel;

import javax.swing.*;
import java.util.List;

public class AppointmentController {

    private AppointmentModel objAppointmentModel;
    private DoctorModel objDoctorModel;
    private DoctorController objDoctorController;
    private PatientModel objPatientModel;
    private PatientController objPatientController;

    public AppointmentController() {
        this.objAppointmentModel = new AppointmentModel();
        this.objDoctorModel = new DoctorModel();
        this.objPatientModel = new PatientModel();
        this.objDoctorController = new DoctorController();
        this.objPatientController = new PatientController();
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

    public void getByDate(){
        String date = JOptionPane.showInputDialog("Insert a date to search, format AAAA-MM-DD");
        String list = this.getAll(this.objAppointmentModel.findByDate(date));
        JOptionPane.showMessageDialog(null, list);
    }

    public void getAll() {

        String list = this.getAll(this.objAppointmentModel.findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public String getAll(List listObject){
        String list = "Appointment List\n";

        // RECORDAR HACER UN INNER JOIN PARA ESTA

        for (Object obj : listObject) {
            Appointment objAppointment = (Appointment) obj;
            Patient objPatient = (Patient) objPatientModel.findById(objAppointment.getId_patient());
            Doctor objDoctor = (Doctor) objDoctorModel.findById(objAppointment.getId_doctor());

            list += objAppointment.getId() + " - "
                    + objAppointment.getDate_appointment() + " - "
                    + objAppointment.getTime_appointment() + " - "
                    + objAppointment.getReason() + " - "
                    + objPatient.getName() + " - "
                    + objDoctor.getName() + "\n";
        }

        return list;
    }

    public void create() {
        Appointment objAppointment = new Appointment();
        String doctorList = this.objDoctorController.getAll(objDoctorModel.findAll());
        String patientList = this.objPatientController.getAll(objPatientModel.findAll());

        String date_appointment = JOptionPane.showInputDialog("Insert the appointment date in format AAAA-MM-DD ");
        String time_appointment = JOptionPane.showInputDialog("Insert the appointment time in format HH:MM:SS  ");
        String reason = JOptionPane.showInputDialog("Insert the reason of the appointment: ");
        int id_patient = Integer.parseInt(JOptionPane.showInputDialog(patientList + "\nInsert the id of the patient: "));
        int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(doctorList + "\nInsert the id of the doctor: "));

        objAppointment.setDate_appointment(date_appointment);
        objAppointment.setTime_appointment(time_appointment);
        objAppointment.setReason(reason);
        objAppointment.setId_patient(id_patient);
        objAppointment.setId_doctor(id_doctor);

        objAppointment = (Appointment) this.objAppointmentModel.insert(objAppointment);

        JOptionPane.showMessageDialog(null, objAppointment.getId() + " - "
                + objAppointment.getDate_appointment() + " - "
                + objAppointment.getTime_appointment() + " - "
                + objAppointment.getReason() + " - "
                + objAppointment.getId_patient() + " - "
                + objAppointment.getId_doctor() + "\n");

    }

    public void update(){
        String listAppointments = this.getAll(this.objAppointmentModel.findAll());

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAppointments +"-Enter the ID of the Appointment to edit"));

        Appointment objAppointment = (Appointment) this.objAppointmentModel.findById(idUpdate);

        if (objAppointment == null){
            JOptionPane.showMessageDialog(null, "Appointment not found.");
        }else {
            String doctorList = this.objDoctorController.getAll(objDoctorModel.findAll());
            String patientList = this.objPatientController.getAll(objPatientModel.findAll());

            String date_appointment = JOptionPane.showInputDialog("Insert the appointment date in format AAAA-MM-DD ", objAppointment.getDate_appointment());
            String time_appointment = JOptionPane.showInputDialog("Insert the appointment time in format HH:MM:SS  ", objAppointment.getTime_appointment());
            String reason = JOptionPane.showInputDialog("Insert the reason of the appointment: ", objAppointment.getReason());
            int id_patient = Integer.parseInt(JOptionPane.showInputDialog(patientList + "\nInsert the id of the patient: ", objAppointment.getId_patient()));
            int id_doctor = Integer.parseInt(JOptionPane.showInputDialog(doctorList + "\nInsert the id of the doctor: ", objAppointment.getId_doctor()));

            objAppointment.setDate_appointment(date_appointment);
            objAppointment.setTime_appointment(time_appointment);
            objAppointment.setReason(reason);
            objAppointment.setId_patient(id_patient);
            objAppointment.setId_doctor(id_doctor);
            this.objAppointmentModel.update(objAppointment);
        }
    }
}