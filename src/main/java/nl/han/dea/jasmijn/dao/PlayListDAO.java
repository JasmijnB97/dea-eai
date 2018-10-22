package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.PlayListDTO;
import nl.han.dea.jasmijn.dto.TrackDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayListDAO extends DAO {

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
        List<Object> bindings = new ArrayList<>();
        bindings.add(playlistId);
        bindings.add(playListDTO);

        updateQuery(UPDATE_NAME_PLAYLIST, bindings);
    }

    public void createPlayList(PlayListDTO playListDTO, int user_id){
        List<Object> bindings = new ArrayList<>();
        bindings.add(playListDTO.getName());
        bindings.add(user_id);

        updateQuery(CREATE_PLAYLIST, bindings);
    }

    public void deletePlayList(int id){
        List<Object> bindings = new ArrayList<>();
        bindings.add(id);

        updateQuery(DELETE_PLAYLIST, bindings);
    }

    public void deleteTrackFromPlayList(int playListId, int trackId){
        List<Object> bindings = new ArrayList<>();
        bindings.add(playListId);
        bindings.add(trackId);

        updateQuery(DELETE_TRACK_FROM_PLAYLIST, bindings);
    }

    public void addTrackToPlayList(int playListId, TrackDTO trackDTO){
        List<Object> bindings = new ArrayList<>();
        bindings.add(playListId);
        bindings.add(trackDTO.getId());

        updateQuery(ADD_TRACK_TO_PLAYLIST, bindings);
    }
}
