package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.dao.PlayListDAO;
import nl.han.dea.jasmijn.dao.UserDAO;
import nl.han.dea.jasmijn.dto.PlayListDTO;
import nl.han.dea.jasmijn.dto.PlayListsDTO;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayListService {
    private TrackService trackService;
    private PlayListDAO playListDAO;
    private UserService userService;


    public PlayListsDTO all(){
        List<PlayListDTO> playListDTOS = null;
            playListDTOS = playListDAO.getAllPlayLists(userService.getUserId());

            //TODO checken of dit hier wel nodig is
        for(PlayListDTO playlist : playListDTOS) {
                //playlist.setTracks(trackService.allTracks());
            playlist.setTracks(trackService.getTracksByPlaylistId(playlist.getId()));
        }

        PlayListsDTO playListsDTO = new PlayListsDTO(playListDTOS);
        playListsDTO.setLength(trackService.getTotalTracksLength());
        System.out.println("getLength = " + playListsDTO.getLength());

        return new PlayListsDTO(playListDTOS);
    }

    public void createPlayList(PlayListDTO playListDTO){
        playListDAO.createPlayList(playListDTO, userService.getUserId());
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
