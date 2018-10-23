package nl.han.dea.jasmijn.datasource.daos;

import nl.han.dea.jasmijn.datasource.DatabaseProperties;

import java.sql.*;
import java.util.List;

public abstract class DAO {

    protected DatabaseProperties dbProperties = new DatabaseProperties();

    protected DAO(){
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

    public void updateQuery(String query, List<Object> values){
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
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, null);
        }
    }

}
