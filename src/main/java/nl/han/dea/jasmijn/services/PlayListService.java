package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.dao.PlayListDAO;
import nl.han.dea.jasmijn.dto.PlayListDTO;
import nl.han.dea.jasmijn.dto.PlayListsDTO;

import javax.inject.Inject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayListService {
    private TrackService trackService;
    private PlayListDAO playListDAO;

    public PlayListsDTO all(){
        List<PlayListDTO> playListDTOS = null;
            playListDTOS = playListDAO.getAllPlayLists();


        for(PlayListDTO playlist : playListDTOS) {
                playlist.setTracks(trackService.allTracks());
        }

        PlayListsDTO playListsDTO = new PlayListsDTO(playListDTOS);
        playListsDTO.setLength(123445);

        return new PlayListsDTO(playListDTOS);
    }

    // TODO aangepast worden -> query die 1 lijst ophaald
    public PlayListDTO findById(int id){
        for(PlayListDTO playList : all().getPlaylists()){
            if (playList.getId() == id) {
                return playList;
            }
        }
        return null;
    }

    @Inject
    public void setUserService(TrackService trackService){
        this.trackService = trackService;
    }

    @Inject
    public void setPlayListDAO(PlayListDAO playListDAO){
        this.playListDAO = playListDAO;
    }


}
