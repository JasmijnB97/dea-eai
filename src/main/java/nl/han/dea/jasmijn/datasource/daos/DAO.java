package nl.han.dea.jasmijn.datasource.daos;

import nl.han.dea.jasmijn.datasource.DatabaseProperties;

import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class DAO {

    protected static final Logger LOGGER = Logger.getLogger(DAO.class.getName());

    protected DatabaseProperties dbProperties = new DatabaseProperties();

    protected DAO(){
        loadDriver();
    }

    protected void loadDriver() {
        try {
            Class.forName(dbProperties.getDriver());
        } catch (ClassNotFoundException e) {
            LOGGER.log(Level.SEVERE, "Failed loadDriver reason: " + e.getMessage());
        }
    }


    protected Connection getDbConnection() {
        try {
            return DriverManager.getConnection(dbProperties.connectionString());
        } catch (SQLException e) {
            LOGGER.info("Error connecting to a database: " + e);
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
            LOGGER.log(Level.SEVERE, "Failed close connection reason: " + e.getMessage());
        }
    }

    protected void updateQuery(String query, List<Object> values){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(query);

            int bindingPosition = 1;
            for(Object value : values) {
                if(value instanceof String) {
                    statement.setString(bindingPosition, (String) value);
                } else if (value instanceof Integer){
                    statement.setInt(bindingPosition, (Integer) value);
                } else if (value instanceof Boolean) {
                    statement.setBoolean(bindingPosition, (Boolean) value);
                }
                bindingPosition++;
            }
            statement.execute();
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed update query reason: " + e.getMessage());
        } finally {
            closeConnection(connection, statement, null);
        }
    }

}
