package controller;

import entity.Patient;
import model.PatientModel;

import javax.swing.*;
import java.util.List;

public class PatientController {

    private PatientModel objPatientModel;

    public PatientController() {
        this.objPatientModel = new PatientModel();
    }

    public void delete() {
        String listPatientString = this.getAll(this.objPatientModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPatientString + "Enter the ID of the patient to delete"));
        Patient objPatient = (Patient) this.objPatientModel.findById(idDelete);

        if (objPatient == null){
            JOptionPane.showMessageDialog(null,"Patient not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are your sure want to delete the patient: "+ objPatient.getName());

            if (confirm == 0){
                this.objPatientModel.delete(objPatient);
            }
        }
    }

    public void getAll() {

        String list = this.getAll(this.objPatientModel.findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public String getAll(List listObject){
        String list = "Patient List\n";

        for (Object obj : listObject) {
            Patient objPatient = (Patient) obj;
            list += objPatient.getId() + " - " + objPatient.getName() + " - " + objPatient.getLast_name() + " - " + objPatient.getIdentity() + " - " + objPatient.getBirth_date() + "\n";
        }

        return list;
    }

    public void create() {
        Patient objPatient = new Patient();

        String name = JOptionPane.showInputDialog("Insert name: ");
        String lastName = JOptionPane.showInputDialog("Insert last name: ");
        String identity = JOptionPane.showInputDialog("Insert identity: ");
        String birth = JOptionPane.showInputDialog("Insert birth date in format AAAA-MM-DD: ");


        objPatient.setName(name);
        objPatient.setIdentity(identity);
        objPatient.setBirth_date(birth);
        objPatient.setLast_name(lastName);

        objPatient = (Patient) this.objPatientModel.insert(objPatient);

        JOptionPane.showMessageDialog(null, objPatient.getId() + " - " + objPatient.getName() + " - " + objPatient.getLast_name() + " - " + objPatient.getIdentity() + " - " + objPatient.getBirth_date());

    }

    public void update(){
        String listPatients = this.getAll(this.objPatientModel.findAll());

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listPatients +"\n-Enter the ID of the Patient to edit"));

        Patient objPatient = (Patient) this.objPatientModel.findById(idUpdate);

        if (objPatient == null){
            JOptionPane.showMessageDialog(null, "Patient not found.");
        }else {
            String name = JOptionPane.showInputDialog("Insert new name: ", objPatient.getName());
            String lastName = JOptionPane.showInputDialog("Insert new last name: ", objPatient.getLast_name());
            String identity = JOptionPane.showInputDialog("Insert new identity: ", objPatient.getIdentity());
            String birth = JOptionPane.showInputDialog("Insert new birth date in format AAAA-MM-DD: ", objPatient.getBirth_date());

            objPatient.setName(name);
            objPatient.setIdentity(identity);
            objPatient.setBirth_date(birth);
            objPatient.setLast_name(lastName);

            this.objPatientModel.update(objPatient);
        }
    }
}