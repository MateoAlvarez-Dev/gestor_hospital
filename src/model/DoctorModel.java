package model;

import database.CRUD;
import database.ConnectDB;
import entity.Doctor;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorModel implements CRUD {

    private ConnectDB database = ConnectDB.getInstance();

    @Override
    public Object insert(Object object) {

        Connection connection = database.connect();
        Doctor objDoctor = (Doctor) object;

        try{

            String sql = "INSERT INTO doctors(name, last_name, id_specialty) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, objDoctor.getName());
            preparedStatement.setString(2, objDoctor.getLast_name());
            preparedStatement.setInt(3, objDoctor.getId_specialty());

            preparedStatement.execute();

            ResultSet objResult = preparedStatement.getGeneratedKeys();

            while (objResult.next()){
                objDoctor.setId(objResult.getInt(1));
            }

            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Doctor inserted succesfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while inserting the data... " + e.getMessage());
        }

        database.closeConnection();
        return objDoctor;
    }

    @Override
    public boolean update(Object object) {

        Connection connection = database.connect();
        Doctor objDoctor = (Doctor) object;
        boolean isUpdated = false;

        try{

            String sql = "UPDATE doctors SET name = ?, last_name = ?, id_specialty = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, objDoctor.getName());
            preparedStatement.setString(2, objDoctor.getLast_name());
            preparedStatement.setInt(3, objDoctor.getId_specialty());
            preparedStatement.setInt(4, objDoctor.getId());


            int rowsUpdated = preparedStatement.executeUpdate();

            if(rowsUpdated > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Doctor Updated! :)");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while updating the doctor... " + e.getMessage());
        }

        database.closeConnection();
        return isUpdated;
    }

    @Override
    public boolean delete(Object object) {
        Connection connection = database.connect();
        Doctor objDoctor = (Doctor) object;
        boolean isDeleted = false;

        try{

            String sql = "DELETE FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, objDoctor.getId());

            int rowsDeleted = preparedStatement.executeUpdate();

            if(rowsDeleted > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Doctor deleted succesfully");
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
        List doctors = new ArrayList<>();

        try{

            String sql = "SELECT * FROM doctors";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                Doctor doctor = new Doctor();
                doctor.setId(results.getInt(1));
                doctor.setName(results.getString(2));
                doctor.setLast_name(results.getString(3));
                doctor.setId_specialty(results.getInt(4));

                doctors.add(doctor);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading doctors... " + e.getMessage());
        }

        database.closeConnection();
        return doctors;
    }

    @Override
    public Object findById(int id) {
        Connection connection = database.connect();
        Doctor objDoctor = null;

        try{

            String sql = "SELECT * FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                objDoctor = new Doctor();
                objDoctor.setId(results.getInt(1));
                objDoctor.setName(results.getString(2));
                objDoctor.setLast_name(results.getString(3));
                objDoctor.setId_specialty(results.getInt(4));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading doctors... " + e.getMessage());
        }

        database.closeConnection();
        return objDoctor;
    }
}