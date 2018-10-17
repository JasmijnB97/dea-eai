package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.PlayListDTO;
import nl.han.dea.jasmijn.dto.TrackDTO;

import java.sql.*;
import java.util.ArrayList;

public class PlayListDAO extends DatabaseDAO {

    private static final String GET_ALL_PLAYLISTS = "SELECT * FROM playlist";
    private static final String UPDATE_NAME_PLAYLIST = "UPDATE playlist SET name = ? WHERE id = ?";
    private static final String CREATE_PLAYLIST = "INSERT INTO playlist (name, owner_id) VALUES (?, ?)";
    private static final String DELETE_PLAYLIST = "DELETE FROM playlist WHERE id = ?";
    private static final String ADD_TRACK_TO_PLAYLIST = "INSERT INTO trackinplaylist (playlist_id, track_id) VALUES (?, ?)";
    private static final String DELETE_TRACK_FROM_PLAYLIST = "DELETE FROM trackinplaylist WHERE playlist_id = ? AND track_id = ?";

    public ArrayList<PlayListDTO> getAllPlayLists(int userId)  {
        ArrayList<PlayListDTO> playlists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_ALL_PLAYLISTS);
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


    public void deleteTrackFromPlayList(int playListId, int trackId){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(DELETE_TRACK_FROM_PLAYLIST);

            statement.setInt(1, playListId);
            statement.setInt(2, trackId);

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }

    public void addTrackToPlayList(int playListId, TrackDTO trackDTO){
        Connection connection = null;
        PreparedStatement statement = null;
        try{
            connection = getDbConnection();
            statement = connection.prepareStatement(ADD_TRACK_TO_PLAYLIST);

            statement.setInt(1, playListId);
            statement.setInt(2, trackDTO.getId());

            statement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }
}
