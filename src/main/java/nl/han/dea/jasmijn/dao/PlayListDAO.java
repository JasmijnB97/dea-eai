package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.PlayListDTO;

import java.sql.*;
import java.util.ArrayList;

public class PlayListDAO extends DatabaseDAO {

    private static final String GET_ALL_PLAYLISTS = "SELECT * FROM playlist";
//    private static final String GET_ALL_PLAYLISTS_BY_ID = "SELECT * FROM playlist WHERE owner_id = ?";
    private static final String UPDATE_NAME_PLAYLIST = "UPDATE playlist SET name = ? WHERE id = ?";
    private static final String CREATE_PLAYLIST = "INSERT INTO playlist (name, owner_id) VALUES (?, ?)";
    private static final String DELETE_PLAYLIST = "DELETE FROM playlist WHERE id = ?";

    public ArrayList<PlayListDTO> getAllPlayLists(int userId)  {
        ArrayList<PlayListDTO> playlists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_ALL_PLAYLISTS);
//            statement = connection.prepareStatement(GET_ALL_PLAYLISTS_BY_ID);
//            statement.setInt(1, id); //nieuw
            rs = statement.executeQuery();

            while (rs.next()) {

                PlayListDTO playListDTO = new PlayListDTO(rs.getInt("id"), rs.getString("name"), false, null); //
                if(rs.getInt("owner_id") == userId){
                    playListDTO.setOwner(true);
                }
                playlists.add(playListDTO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return playlists;
    }

    public void updatePlayList(int playlistId, PlayListDTO playListDTO){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(UPDATE_NAME_PLAYLIST);

            statement.setString(1, playListDTO.getName());
            statement.setInt(2, playlistId);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }

    public void createPlayList(PlayListDTO playListDTO, int user_id){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(CREATE_PLAYLIST);

            statement.setString(1, playListDTO.getName());
            statement.setInt(2, user_id);

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
