package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.datasource.util.DatabaseProperties;
import java.sql.*;

public abstract class DatabaseDAO {

    protected DatabaseProperties dbProperties = new DatabaseProperties();

    protected DatabaseDAO(){
        loadDriver();
    }

    protected void loadDriver() {
        try {
            Class.forName(dbProperties.getDriver());//hoi
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    protected Connection getDbConnection() {
        try {
            return DriverManager.getConnection(dbProperties.connectionString());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connecting to a database: " + e);
        }
        return null;
    }

    protected void closeConnection(Connection connection, Statement statement, ResultSet resultSet){
        try {
            if(connection != null) {
                connection.close();
            }
            if(statement != null) {
                statement.close();
            }
            if(resultSet != null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO dit is dubbel
    protected void closeConnection(Connection connection, PreparedStatement statement){
        try {
            if(connection != null) {
                connection.close();
            }
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
