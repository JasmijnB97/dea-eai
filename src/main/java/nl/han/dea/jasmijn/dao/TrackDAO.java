package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.TrackDTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TrackDAO extends DatabaseDAO {

    private static final String SQL = "SELECT * FROM track";

    public ArrayList<TrackDTO> getAllTracks() throws SQLException {
        ArrayList<TrackDTO> tracklists = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

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

    public ArrayList<TrackDTO> getTracksById(int id){
        return null;
    }
}