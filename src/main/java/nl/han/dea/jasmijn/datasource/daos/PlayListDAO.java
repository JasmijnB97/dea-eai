package nl.han.dea.jasmijn.datasource.daos;

import nl.han.dea.jasmijn.dtos.PlayListDTO;
import nl.han.dea.jasmijn.dtos.PlayListsDTO;
import nl.han.dea.jasmijn.dtos.TrackDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class PlayListDAO extends DAO {

    private static final String GET_ALL_PLAYLISTS = "SELECT * FROM playlist";
    private static final String UPDATE_NAME_PLAYLIST = "UPDATE playlist SET name = ? WHERE id = ?";
    private static final String CREATE_PLAYLIST = "INSERT INTO playlist (name, owner_id) VALUES (?, ?)";
    private static final String DELETE_PLAYLIST = "DELETE FROM playlist WHERE id = ?";
    private static final String ADD_TRACK_TO_PLAYLIST = "INSERT INTO trackinplaylist (playlist_id, track_id) VALUES (?, ?)";
    private static final String DELETE_TRACK_FROM_PLAYLIST = "DELETE FROM trackinplaylist WHERE playlist_id = ? AND track_id = ?";

    public PlayListsDTO getAllPlayLists(int userId)  {
        ArrayList<PlayListDTO> playLists = new ArrayList<>();
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
                playLists.add(playListDTO);
            }

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed to get all playLists reason: " + e.getMessage());
        } finally {
            closeConnection(connection, statement, rs);
        }
        return new PlayListsDTO(playLists);
    }

    public void updatePlayList(int playlistId, PlayListDTO playListDTO){
        List<Object> bindings = new ArrayList<>();
        bindings.add(playlistId);
        bindings.add(playListDTO);

        updateQuery(UPDATE_NAME_PLAYLIST, bindings);
    }

    public void createPlayList(PlayListDTO playListDTO, int userId){
        List<Object> bindings = new ArrayList<>();
        bindings.add(playListDTO.getName());
        bindings.add(userId);

        updateQuery(CREATE_PLAYLIST, bindings);
    }

    public void deletePlayList(int playListId){
        List<Object> bindings = new ArrayList<>();
        bindings.add(playListId);

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
