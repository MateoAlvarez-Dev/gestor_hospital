package database;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {

    /*private static final String URL = "jdbc:mysql://b6aggcgdvevluf65e2zs-mysql.services.clever-cloud.com:3306/b6aggcgdvevluf65e2zs";
    private static final String USERNAME = "uirnq6zhde2i5pnf";
    private static final String PASSWORD = "eUqH0rVEizjzHNFahZ7O";*/
    private static final String URL = "jdbc:mysql://localhost:3306/prueba_sql";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Rlwl2023.";
    private static ConnectDB instance;

    private Connection connection;

    private ConnectDB(){};

    public Connection connect(){

        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);

            return connection;

        }catch (Exception e){
            System.out.println("ERROR -> " + e.getMessage());
        }

        return connection;
    }

    public void closeConnection(){
        try{

            if(connection != null) connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ConnectDB getInstance(){
        if(instance == null) instance = new ConnectDB();
        return instance;
    }

}
