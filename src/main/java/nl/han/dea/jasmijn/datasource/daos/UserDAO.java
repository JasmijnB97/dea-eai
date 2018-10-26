package nl.han.dea.jasmijn.datasource.daos;

import nl.han.dea.jasmijn.dtos.LoginRequestDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UserDAO extends DAO {

    private static final String GET_USER = "SELECT * FROM user  WHERE token = ?";
    private static final String UPDATE_TOKEN = "UPDATE user SET token = ? WHERE name = ? AND password = ?";
    private static final String GET_USER_ID = "SELECT id FROM user WHERE token = ?";


    public LoginRequestDTO getUser(String token){
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_USER);
            statement.setString(1, token);
            rs = statement.executeQuery();

            while (rs.next()) {
                loginRequestDTO.setUser(rs.getString("name"));
                loginRequestDTO.setPassword(rs.getString("password"));
            }

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed to get a user reason: " + e.getMessage());
        } finally {
            closeConnection(connection, statement, rs);
        }
        return loginRequestDTO;
    }

    public void setToken(String token, String name, String password){
        List<Object> bindings = new ArrayList<>();
        bindings.add(token);
        bindings.add(name);
        bindings.add(password);

        updateQuery(UPDATE_TOKEN, bindings);
    }

    public int getUserId(String token){
        int userId = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_USER_ID);
            statement.setString(1, token);
            rs = statement.executeQuery();

            while (rs.next()) {
                userId = rs.getInt("id");
            }

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed to get a user id reason: " + e.getMessage());
        } finally {
            closeConnection(connection, statement, rs);
        }
        return userId;
    }
}
