package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.datasource.daos.PlayListDAO;
import nl.han.dea.jasmijn.dtos.PlayListDTO;
import nl.han.dea.jasmijn.dtos.PlayListsDTO;
import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;

import javax.inject.Inject;

public class PlayListService {
    private TrackService trackService;
    private PlayListDAO playListDAO;
    private UserService userService;


    public PlayListsDTO allPlayLists(){
        PlayListsDTO playLists = playListDAO.getAllPlayLists(userService.getUserId());
        int totalLength = 0;

        for(PlayListDTO playlist : playLists.getPlaylists()) {
            TracksDTO tracks = trackService.getTracksByPlaylistId(playlist.getId());
            for (TrackDTO track : tracks.getTracks()) {
                totalLength += track.getDuration();
            }
            playlist.setTracks(tracks.getTracks());
        }
        playLists.setLength(totalLength);
        return playLists;
    }

    public void createPlayList(PlayListDTO playListDTO){
        playListDAO.createPlayList(playListDTO, userService.getUserId());
    }

    public void updatePlayList(int id, PlayListDTO playListDTO){
        playListDAO.updatePlayList(id, playListDTO);
    }

    public void deletePlayList(int id){
        playListDAO.deletePlayList(id);
    }

    public void deleteTrackFromPlayList(int playListId, int trackId) {
        playListDAO.deleteTrackFromPlayList(playListId, trackId);
    }

    public void addTrackToPlayList(int playListId, TrackDTO trackDTO) {
        playListDAO.addTrackToPlayList(playListId, trackDTO);
    }

    @Inject
    public void setUserService(TrackService trackService){
        this.trackService = trackService;
    }

    @Inject
    public void setPlayListDAO(PlayListDAO playListDAO){
        this.playListDAO = playListDAO;
    }

    @Inject
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
