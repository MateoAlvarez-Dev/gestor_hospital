package model;

import database.CRUD;
import database.ConnectDB;
import entity.Appointment;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentModel implements CRUD {

    private ConnectDB database = ConnectDB.getInstance();

    @Override
    public Object insert(Object object) {

        Connection connection = database.connect();
        Appointment objAppointment = (Appointment) object;

        try{

            String sql = "INSERT INTO appointments(date_appointment, time_oppointment, reason, id_patient, id_doctor) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, objAppointment.getDate_appointment());
            preparedStatement.setString(2, objAppointment.getTime_appointment());
            preparedStatement.setString(3, objAppointment.getReason());
            preparedStatement.setInt(4, objAppointment.getId_patient());
            preparedStatement.setInt(5, objAppointment.getId_doctor());



            preparedStatement.execute();

            ResultSet objResult = preparedStatement.getGeneratedKeys();

            while (objResult.next()){
                objAppointment.setId(objResult.getInt(1));
            }

            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Appointment inserted succesfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while inserting the data... " + e.getMessage());
        }

        database.closeConnection();
        return objAppointment;
    }

    @Override
    public boolean update(Object object) {

        Connection connection = database.connect();
        Appointment objAppointment = (Appointment) object;
        boolean isUpdated = false;

        try{

            String sql = "UPDATE appointments SET date_appointment = ?, time_oppointment = ?, reason = ?, id_patient = ?, id_doctor = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, objAppointment.getDate_appointment());
            preparedStatement.setString(2, objAppointment.getTime_appointment());
            preparedStatement.setString(3, objAppointment.getReason());
            preparedStatement.setInt(4, objAppointment.getId_patient());
            preparedStatement.setInt(5, objAppointment.getId_doctor());
            preparedStatement.setInt(6, objAppointment.getId());


            int rowsUpdated = preparedStatement.executeUpdate();

            if(rowsUpdated > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Appointment Updated! :)");
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
        Appointment objAppointment = (Appointment) object;
        boolean isDeleted = false;

        try{

            String sql = "DELETE FROM appointments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, objAppointment.getId());

            int rowsDeleted = preparedStatement.executeUpdate();

            if(rowsDeleted > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Appointment deleted succesfully");
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
        List appointments = new ArrayList<>();

        try{

            String sql = "SELECT * FROM appointments";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                Appointment objAppointment = new Appointment();
                objAppointment.setId(results.getInt(1));
                objAppointment.setDate_appointment(results.getString(2));
                objAppointment.setTime_appointment(results.getString(3));
                objAppointment.setReason(results.getString(4));
                objAppointment.setId_patient(results.getInt(5));
                objAppointment.setId_doctor(results.getInt(6));

                appointments.add(objAppointment);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading appointments... " + e.getMessage());
        }

        database.closeConnection();
        return appointments;
    }

    @Override
    public Object findById(int id) {
        Connection connection = database.connect();
        Appointment objAppointment = null;

        try{

            String sql = "SELECT * FROM appointments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                objAppointment = new Appointment();
                objAppointment.setId(results.getInt(1));
                objAppointment.setDate_appointment(results.getString(2));
                objAppointment.setTime_appointment(results.getString(3));
                objAppointment.setReason(results.getString(4));
                objAppointment.setId_patient(results.getInt(5));
                objAppointment.setId_doctor(results.getInt(6));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading appointments... " + e.getMessage());
        }

        database.closeConnection();
        return objAppointment;
    }

    public List findByDate(String date){
        Connection connection = database.connect();
        List appointments = new ArrayList<>();

        try{

            String sql = "SELECT * FROM appointments WHERE date_appointment = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                Appointment objAppointment = new Appointment();
                objAppointment.setId(results.getInt(1));
                objAppointment.setDate_appointment(results.getString(2));
                objAppointment.setTime_appointment(results.getString(3));
                objAppointment.setReason(results.getString(4));
                objAppointment.setId_patient(results.getInt(5));
                objAppointment.setId_doctor(results.getInt(6));

                appointments.add(objAppointment);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading appointments... " + e.getMessage());
        }

        database.closeConnection();
        return appointments;
    }
}