package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.LoginRequestDTO;
import nl.han.dea.jasmijn.dto.PlayListDTO;
import nl.han.dea.jasmijn.dto.TrackDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO extends DatabaseDAO{

    private static final String GET_USER = "SELECT * FROM user";
    private static final String UPDATE_TOKEN = "UPDATE user SET token = ? WHERE name = ? AND password = ?";


    public LoginRequestDTO getUser(){
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_USER);
            rs = statement.executeQuery();

            while (rs.next()) {
                loginRequestDTO.setUser(rs.getString("name"));
                loginRequestDTO.setPassword(rs.getString("password"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return loginRequestDTO;
    }

    public void setToken(String token, String name, String password){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(UPDATE_TOKEN);

            statement.setString(1, token);
            statement.setString(2, name);
            statement.setString(2, password);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }
}
