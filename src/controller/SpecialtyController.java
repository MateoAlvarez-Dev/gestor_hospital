package controller;

import entity.Specialty;
import model.SpecialtyModel;

import javax.swing.*;
import java.util.List;

public class SpecialtyController {

    private SpecialtyModel objSpecialtyModel;

    public SpecialtyController() {
        this.objSpecialtyModel = new SpecialtyModel();
    }

    public void delete() {
        String listSpecialtyString = this.getAll(this.objSpecialtyModel.findAll());

        int confirm = 1;
        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listSpecialtyString + "Enter the ID of the specialty to delete"));
        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(idDelete);

        if (objSpecialty == null){
            JOptionPane.showMessageDialog(null,"Specialty not found.");
        }else {
            confirm = JOptionPane.showConfirmDialog(null,"Are your sure want to delete the specialty: "+ objSpecialty.getName());

            if (confirm == 0){
                this.objSpecialtyModel.delete(objSpecialty);
            }
        }
    }

    public void getAll() {

        String list = this.getAll(this.objSpecialtyModel.findAll());
        JOptionPane.showMessageDialog(null, list);

    }

    public String getAll(List listObject){
        String list = "Specialty List\n";

        for (Object obj : listObject) {
            Specialty objSpecialty = (Specialty) obj;
            list += objSpecialty.getId() + " - " + objSpecialty.getName() + " - " + objSpecialty.getDescription() + "\n";
        }

        return list;
    }

    public void create() {
        Specialty objSpecialty = new Specialty();

        String name = JOptionPane.showInputDialog("Insert name: ");
        String description = JOptionPane.showInputDialog("Insert description: ");

        objSpecialty.setName(name);
        objSpecialty.setDescription(description);

        objSpecialty = (Specialty) this.objSpecialtyModel.insert(objSpecialty);

        JOptionPane.showMessageDialog(null, objSpecialty.getId() + " - " + objSpecialty.getName() + " - " + objSpecialty.getDescription());

    }

    public void update(){
        String listSpecialtys = this.getAll(this.objSpecialtyModel.findAll());

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listSpecialtys +"\n-Enter the ID of the Specialty to edit"));

        Specialty objSpecialty = (Specialty) this.objSpecialtyModel.findById(idUpdate);

        if (objSpecialty == null){
            JOptionPane.showMessageDialog(null, "Specialty not found.");
        }else {
            String name = JOptionPane.showInputDialog(null,"Enter new name",objSpecialty.getName());
            String description = JOptionPane.showInputDialog(null,"Enter new description", objSpecialty.getDescription());

            objSpecialty.setName(name);
            objSpecialty.setDescription(description);

            this.objSpecialtyModel.update(objSpecialty);
        }
    }
}