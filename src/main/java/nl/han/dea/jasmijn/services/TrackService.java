package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.datasource.daos.TrackDAO;
import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class TrackService {
    private TrackDAO trackDAO;

    public TracksDTO allTracks() {
        return trackDAO.getAllTracks();
    }

    public TracksDTO getTracksThatDontExistsInPlayList(int playListId) {
        return trackDAO.getTracksThatDontExistsInPlayList(playListId);
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

    public boolean equalsCurrentOfflineAvailable(int trackId, Boolean offlineAvailable) {
        return getTrackByTrackId(trackId).isOfflineAvailable() == offlineAvailable;
    }

    public void updateOfflineAvailable(int trackId, Boolean offlineAvailable) {
        trackDAO.updateOfflineAvailable(trackId, offlineAvailable);
    }

    public boolean trackInPlaylist(int playlistId, int trackId){
        TracksDTO tracks = getTracksByPlaylistId(playlistId);
        for (TrackDTO track : tracks.getTracks()){
            if (track.getId() == trackId){
                return true;
            }
        }
        return false;
    }

    @Inject
    public void setTrackDAO(TrackDAO trackDAO){
        this.trackDAO = trackDAO;
    }
}
