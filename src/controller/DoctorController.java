package controller;

import entity.Doctor;
import entity.Specialty;
import model.DoctorModel;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class DoctorController {

    private DoctorModel objDoctorModel;
    private SpecialtyModel specialtyModel;

    public DoctorController() {
        this.objDoctorModel = new DoctorModel();
        this.specialtyModel = new SpecialtyModel();
    }

    public void delete() {
        String listDoctorString = this.getAll(this.objDoctorModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listDoctorString + "Enter the ID of the doctor to delete"));
        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idDelete);

        if (objDoctor == null){
            JOptionPane.showMessageDialog(null,"Doctor not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are your sure want to delete the doctor: "+ objDoctor.getName());

            if (confirm == 0){
                this.objDoctorModel.delete(objDoctor);
            }
        }
    }

    public void getAll() {

        String list = this.getAll(this.objDoctorModel.findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public String getAll(List listObject){
        String list = "Doctor List\n";

        for (Object obj : listObject) {
            Doctor objDoctor = (Doctor) obj;
            Specialty objSpecialty = (Specialty) specialtyModel.findById(objDoctor.getId_specialty());
            list += objDoctor.getId() + " - " 
                    + objDoctor.getName() + " - " 
                    + objDoctor.getLast_name() + " - " 
                    + objSpecialty.getName() + "\n";
        }

        return list;
    }

    public void create() {
        Doctor objDoctor = new Doctor();

        String name = JOptionPane.showInputDialog("Insert name: ");
        String lastName = JOptionPane.showInputDialog("Insert last name: ");
        int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog("Insert specialty: "));


        objDoctor.setName(name);
        objDoctor.setLast_name(lastName);
        objDoctor.setId_specialty(idSpecialty);

        objDoctor = (Doctor) this.objDoctorModel.insert(objDoctor);

        JOptionPane.showMessageDialog(null, 
                objDoctor.getId() + " - " 
                        + objDoctor.getName() + " - " 
                        + objDoctor.getLast_name() + " - "
                        + objDoctor.getId_specialty()
        );

    }

    public void update(){
        String listDoctors = this.getAll(this.objDoctorModel.findAll());

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listDoctors + "-Enter the ID of the Doctor to edit"));

        Doctor objDoctor = (Doctor) this.objDoctorModel.findById(idUpdate);

        if (objDoctor == null){
            JOptionPane.showMessageDialog(null, "Doctor not found.");
        }else {
            String name = JOptionPane.showInputDialog("Insert new name: ", objDoctor.getName());
            String lastName = JOptionPane.showInputDialog("Insert new last name: ", objDoctor.getLast_name());
            int idSpecialty = Integer.parseInt(JOptionPane.showInputDialog("Insert new specialty ID: ", objDoctor.getId_specialty()));

            objDoctor.setName(name);
            objDoctor.setLast_name(lastName);
            objDoctor.setId_specialty(idSpecialty);

            this.objDoctorModel.update(objDoctor);
        }
    }
}