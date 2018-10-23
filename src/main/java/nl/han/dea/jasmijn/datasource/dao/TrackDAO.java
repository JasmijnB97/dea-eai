package nl.han.dea.jasmijn.datasource.dao;

import nl.han.dea.jasmijn.dto.TrackDTO;
import nl.han.dea.jasmijn.dto.TracksDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrackDAO extends DAO {

    private static final String GET_ALL_TRACKS = "SELECT * FROM track";
    private static final String TRACKS_ID_BY_PLAYLIST_ID = "SELECT track_id FROM trackinplaylist WHERE playlist_id = ?";

    public TracksDTO getAllTracks(){
        ArrayList<TrackDTO> tracklists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(GET_ALL_TRACKS);
            rs = statement.executeQuery();

            while (rs.next()) {
                TrackDTO trackDTO = new TrackDTO(rs.getInt("id"), rs.getString("title"), rs.getString("performer"),
                        rs.getInt("duration"), rs.getString("album"), rs.getInt("paycount"), rs.getString("publicationDate"),
                        rs.getString("description"), rs.getBoolean("offlineAvailable"));
                tracklists.add(trackDTO);
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return new TracksDTO(tracklists);
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
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return trackIds;
    }
}