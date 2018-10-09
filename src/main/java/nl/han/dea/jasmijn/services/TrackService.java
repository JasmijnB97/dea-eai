package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.dao.PlayListDAO;
import nl.han.dea.jasmijn.dao.TrackDAO;
import nl.han.dea.jasmijn.dto.PlayListsDTO;
import nl.han.dea.jasmijn.dto.TrackDTO;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackService {
    private TrackDAO trackDAO;

    public List<TrackDTO> allTracks() {
        return trackDAO.getAllTracks();
    }

    public List<TrackDTO> getTracksByPlaylistId(int id) {
        return trackDAO.getTracksByPlaylistId(id);
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO){
        this.trackDAO = trackDAO;
        System.out.println("we zijn onder trackService -> inject trackDAO");
    }
}
