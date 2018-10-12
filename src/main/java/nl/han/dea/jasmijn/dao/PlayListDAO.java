package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.PlayListDTO;

import java.sql.*;
import java.util.ArrayList;

public class PlayListDAO extends DatabaseDAO {

    private static final String GET_ALL_PLAYLISTS = "SELECT * FROM playlist";
    private static final String UPDATE_NAME_PLAYLIST = "UPDATE playlist SET name = ? WHERE id = ?";
    private static final String CREATE_PLAYLIST = "INSERT INTO playlist (name, owner) VALUES (?, ?)";
    private static final String DELETE_PLAYLIST = "DELETE FROM playlist WHERE id = ?";

    public ArrayList<PlayListDTO> getAllPlayLists()  {
        ArrayList<PlayListDTO> playlists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_ALL_PLAYLISTS);
            rs = statement.executeQuery();

            while (rs.next()) {
                PlayListDTO playListDTO = new PlayListDTO(rs.getInt("id"), rs.getString("name"), rs.getBoolean("owner"), null);
                playlists.add(playListDTO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return playlists;
    }

    public void updatePlayList(int id, PlayListDTO playListDTO){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(UPDATE_NAME_PLAYLIST);

            statement.setString(1, playListDTO.getName());
            statement.setInt(2, id);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }

    public void createPlayList(PlayListDTO playListDTO){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(CREATE_PLAYLIST);

            statement.setString(1, playListDTO.getName());
            statement.setBoolean(2, true);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }

    public void deletePlayList(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(DELETE_PLAYLIST);

            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }
}
