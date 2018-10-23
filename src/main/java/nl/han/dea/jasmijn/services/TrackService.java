package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.datasource.dao.TrackDAO;
import nl.han.dea.jasmijn.dto.TrackDTO;
import nl.han.dea.jasmijn.dto.TracksDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrackService {
    private TrackDAO trackDAO;

    public TracksDTO allTracks() {
        return trackDAO.getAllTracks();
    }

    public TrackDTO getTrackByTrackId(int trackId) {
        TracksDTO tracks = allTracks();
        for (TrackDTO track : tracks.getTracks()){
            if (track.getId() == trackId){
                return track;
            }
        }
        return null;
    }

    public TracksDTO getTracksByPlaylistId(int playListId) {
        List<TrackDTO> tracks = new ArrayList<>();
        List<Integer> trackIds = trackDAO.getTracksByPlaylistId(playListId);
        for(int i = 0; i < trackIds.size(); i++){
            tracks.add(getTrackByTrackId(trackIds.get(i)));
        }
        return new TracksDTO(tracks);
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO){
        this.trackDAO = trackDAO;
    }
}
