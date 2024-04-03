package model;

import database.CRUD;
import database.ConnectDB;
import entity.Patient;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientModel implements CRUD {

    private ConnectDB database = ConnectDB.getInstance();

    @Override
    public Object insert(Object object) {

        Connection connection = database.connect();
        Patient objPatient = (Patient) object;

        try{

            String sql = "INSERT INTO patients(name, last_name, birth_date, identity) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, objPatient.getName());
            preparedStatement.setString(2, objPatient.getLast_name());
            preparedStatement.setString(3, objPatient.getBirth_date());
            preparedStatement.setString(4, objPatient.getIdentity());

            preparedStatement.execute();

            ResultSet objResult = preparedStatement.getGeneratedKeys();

            while (objResult.next()){
                objPatient.setId(objResult.getInt(1));
            }

            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Patient inserted succesfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while inserting the data... " + e.getMessage());
        }

        database.closeConnection();
        return objPatient;
    }

    @Override
    public boolean update(Object object) {

        Connection connection = database.connect();
        Patient objPatient = (Patient) object;
        boolean isUpdated = false;

        try{

            String sql = "UPDATE patients SET name = ?, last_name = ?, birth_date = ?, identity = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, objPatient.getName());
            preparedStatement.setString(2, objPatient.getLast_name());
            preparedStatement.setString(3, objPatient.getBirth_date());
            preparedStatement.setString(4, objPatient.getIdentity());
            preparedStatement.setInt(5, objPatient.getId());


            int rowsUpdated = preparedStatement.executeUpdate();

            if(rowsUpdated > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Patient Updated! :)");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while updating the specialty... " + e.getMessage());
        }

        database.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        Connection connection = database.connect();
        Patient objPatient = (Patient) object;
        boolean isDeleted = false;

        try{

            String sql = "DELETE FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, objPatient.getId());

            int rowsDeleted = preparedStatement.executeUpdate();

            if(rowsDeleted > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Patient deleted succesfully");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return isDeleted;
    }

    @Override
    public List findAll() {
        Connection connection = database.connect();
        List patients = new ArrayList<>();

        try{

            String sql = "SELECT * FROM patients";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                Patient patient = new Patient();
                patient.setId(results.getInt(1));
                patient.setName(results.getString(2));
                patient.setLast_name(results.getString(3));
                patient.setBirth_date(results.getString(4));
                patient.setIdentity(results.getString(5));

                patients.add(patient);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading patients... " + e.getMessage());
        }

        database.closeConnection();
        return patients;
    }

    @Override
    public Object findById(int id) {
        Connection connection = database.connect();
        Patient objPatient = null;

        try{

            String sql = "SELECT * FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                objPatient = new Patient();
                objPatient.setId(results.getInt(1));
                objPatient.setName(results.getString(2));
                objPatient.setLast_name(results.getString(3));
                objPatient.setBirth_date(results.getString(4));
                objPatient.setIdentity(results.getString(5));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading patients... " + e.getMessage());
        }

        database.closeConnection();
        return objPatient;
    }

    public Object findByIdentity(String identity){
        Connection connection = database.connect();
        Patient objPatient = null;

        try{

            String sql = "SELECT * FROM patients WHERE identity = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, identity);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                objPatient = new Patient();
                objPatient.setId(results.getInt(1));
                objPatient.setName(results.getString(2));
                objPatient.setLast_name(results.getString(3));
                objPatient.setBirth_date(results.getString(4));
                objPatient.setIdentity(results.getString(5));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading patients... " + e.getMessage());
        }

        database.closeConnection();
        return objPatient;
    }
}