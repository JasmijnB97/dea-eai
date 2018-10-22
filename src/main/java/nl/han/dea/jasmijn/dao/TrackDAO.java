package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.TrackDTO;

import java.sql.*;
import java.util.ArrayList;

public class TrackDAO extends DAO {

    private static final String ALLSQL = "SELECT * FROM track";
    private static final String TRACKS_BY_PLAYLIST_ID_SQL = "SELECT track.* FROM track INNER JOIN trackinplaylist ON track.id = trackinplaylist.track_id WHERE trackinplaylist.playlist_id = ?";
    private static final String TOTAL_TRACK_LENGTH = "SELECT SUM(duration) AS total FROM track";

    public ArrayList<TrackDTO> getAllTracks(){
        ArrayList<TrackDTO> tracklists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(ALLSQL);
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
        return tracklists;
    }

    public ArrayList<TrackDTO> getTracksByPlaylistId(int id){
        ArrayList<TrackDTO> tracklists = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(TRACKS_BY_PLAYLIST_ID_SQL);
            statement.setInt(1,id);
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
        return tracklists;
    }

    //TODO moet dit voor het totaal van de playlists of per playlist?
    public int getTotalTracksLength() {
        int totalTracksLength = -1;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.prepareStatement(TOTAL_TRACK_LENGTH);
            rs = statement.executeQuery();

            while (rs.next()) {
                totalTracksLength = rs.getInt("total");
            }

        } catch (SQLException e){
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement, rs);
        }
        return totalTracksLength;
    }
}