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
        try {
            playListDTOS = playListDAO.getAllPlayLists();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for(PlayListDTO playlist : playListDTOS) {
                playlist.setTracks(trackService.allTracks());
        }

        PlayListsDTO playListsDTO = new PlayListsDTO(playListDTOS);
        playListsDTO.setLength(123445);

        return new PlayListsDTO(playListDTOS);
    }

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

        System.out.println("we zijn onder playlistService -> inject trackservice");
    }

    @Inject
    public void setPlayListDAO(PlayListDAO playListDAO){
        this.playListDAO = playListDAO;

        System.out.println("we zijn onder playlistService -> inject playlistDAO");
    }


}
