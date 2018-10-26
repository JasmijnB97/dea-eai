package nl.han.dea.jasmijn.datasource.daos;

import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class TrackDAO extends DAO {

    private static final String GET_ALL_TRACKS = "SELECT * FROM track";
    private static final String TRACKS_ID_BY_PLAYLIST_ID = "SELECT track_id FROM trackinplaylist WHERE playlist_id = ?";
    private static final String UPDATE_OFFLINE_AVAILABLE = "UPDATE track SET offlineAvailable = ? WHERE id = ?";
    private static final String GET_TRACKS_NOT_IN_PLAYLIST = "SELECT * FROM track WHERE id NOT IN (SELECT track_id FROM trackinplaylist WHERE playlist_id = ?)";

    public TracksDTO getAllTracks(){
        ArrayList<TrackDTO> tracklists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_ALL_TRACKS);
            rs = statement.executeQuery();

            buildTrackDTOWithResultSet(tracklists, rs);

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed to get all tracks reason: " + e.getMessage());
        } finally {
            closeConnection(connection, statement, rs);
        }
        return new TracksDTO(tracklists);
    }

    public TracksDTO getTracksThatNotExistsInPlayList(int playListId){
        ArrayList<TrackDTO> tracklists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_TRACKS_NOT_IN_PLAYLIST);
            statement.setInt(1, playListId);
            rs = statement.executeQuery();

            buildTrackDTOWithResultSet(tracklists, rs);

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed to get all tracks reason: " + e.getMessage());
        } finally {
            closeConnection(connection, statement, rs);
        }
        return new TracksDTO(tracklists);
    }

    private void buildTrackDTOWithResultSet(ArrayList<TrackDTO> tracklists, ResultSet rs) throws SQLException {
        while (rs.next()) {
            TrackDTO trackDTO = new TrackDTO();
            trackDTO.setId(rs.getInt("id"));
            trackDTO.setTitle(rs.getString("title"));
            trackDTO.setPerformer(rs.getString("performer"));
            trackDTO.setDuration(rs.getInt("duration"));
            trackDTO.setAlbum(rs.getString("album"));
            trackDTO.setPaycount(rs.getInt("paycount"));
            trackDTO.setPublicationDate(rs.getString("publicationDate"));
            trackDTO.setDescription(rs.getString("description"));
            trackDTO.setOfflineAvailable(rs.getBoolean("offlineAvailable"));
            tracklists.add(trackDTO);
        }
    }

    public List<Integer> getTracksByPlaylistId(int id){
        List<Integer> trackIds = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(TRACKS_ID_BY_PLAYLIST_ID);
            statement.setInt(1,id);
            rs = statement.executeQuery();

            while (rs.next()) {
                trackIds.add(rs.getInt("track_id"));
            }

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "Failed to get tracks by playlist id reason: " + e.getMessage());
        } finally {
            closeConnection(connection, statement, rs);
        }
        return trackIds;
    }

    public void updateOfflineAvailable(int trackId, Boolean offlineAvailable) {
        List<Object> bindings = new ArrayList<>();
        bindings.add(offlineAvailable);
        bindings.add(trackId);

        updateQuery(UPDATE_OFFLINE_AVAILABLE, bindings);
    }

}