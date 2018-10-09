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

        List<TrackDTO> trackListDTO = null;
        try {
            trackListDTO = trackDAO.getAllTracks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trackListDTO;
    }

//    public TrackTDO findById(int id){
//        for(TrackDAO playList : allTracks().getPlaylists()){
//            if (playList.getId() == id) {
//                return playList;
//            }
//        }
//        return null;
//    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO){
        this.trackDAO = trackDAO;
        System.out.println("we zijn onder trackService -> inject trackDAO");
    }
}
