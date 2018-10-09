package nl.han.dea.jasmijn.dao;

import nl.han.dea.jasmijn.dto.PlayListDTO;

import java.sql.*;
import java.util.ArrayList;

public class PlayListDAO extends DatabaseDAO {

    private static final String SQL = "SELECT * FROM playlist";

    public ArrayList<PlayListDTO> getAllPlayLists() throws SQLException {
        ArrayList<PlayListDTO> playlists = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        try {
            connection = getDbConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(SQL);

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
}
