package model;

import database.CRUD;
import database.ConnectDB;
import entity.Specialty;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


// ESTE ESTA LISTO


public class SpecialtyModel implements CRUD {

    private ConnectDB database = ConnectDB.getInstance();

    @Override
    public Object insert(Object object) {

        Connection connection = database.connect();
        Specialty objSpecialty = (Specialty) object;

        try{

            String sql = "INSERT INTO specialties(name, description) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, objSpecialty.getName());
            preparedStatement.setString(2, objSpecialty.getDescription());

            preparedStatement.execute();

            ResultSet objResult = preparedStatement.getGeneratedKeys();

            while (objResult.next()){
                objSpecialty.setId(objResult.getInt(1));
            }

            preparedStatement.close();
            JOptionPane.showMessageDialog(null, "Specialty inserted succesfully!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while inserting the data... " + e.getMessage());
        }

        database.closeConnection();
        return objSpecialty;
    }

    @Override
    public boolean update(Object object) {

        Connection connection = database.connect();
        Specialty objSpecialty = (Specialty) object;
        boolean isUpdated = false;

        try{

            String sql = "UPDATE specialties SET name = ?, description = ? WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, objSpecialty.getName());
            preparedStatement.setString(2, objSpecialty.getDescription());
            preparedStatement.setInt(3, objSpecialty.getId());


            int rowsUpdated = preparedStatement.executeUpdate();

            if(rowsUpdated > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null, "Specialty Updated! :)");
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
        Specialty objSpecialty = (Specialty) object;
        boolean isDeleted = false;

        try{

            String sql = "DELETE FROM specialties WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, objSpecialty.getId());

            int rowsDeleted = preparedStatement.executeUpdate();

            if(rowsDeleted > 0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null, "Specialty deleted succesfully");
            }

            preparedStatement.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        database.closeConnection();
        return isDeleted;
    }

    @Override
    public List<Object> findAll() {
        Connection connection = database.connect();
        List specialties = new ArrayList<>();

        try{

            String sql = "SELECT * FROM specialties";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                Specialty specialty = new Specialty();
                specialty.setId(results.getInt(1));
                specialty.setName(results.getString(2));
                specialty.setDescription(results.getString(3));

                specialties.add(specialty);
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading specialties... " + e.getMessage());
        }

        database.closeConnection();
        return specialties;
    }

    @Override
    public Object findById(int id) {
        Connection connection = database.connect();
        Specialty objSpecialty = null;

        try{

            String sql = "SELECT * FROM specialties WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()){
                objSpecialty = new Specialty();
                objSpecialty.setId(results.getInt(1));
                objSpecialty.setName(results.getString(2));
                objSpecialty.setDescription(results.getString(3));
            }

            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error while loading specialties... " + e.getMessage());
        }

        database.closeConnection();
        return objSpecialty;
    }
}
