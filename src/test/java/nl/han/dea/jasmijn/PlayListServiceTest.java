package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.datasource.daos.PlayListDAO;
import nl.han.dea.jasmijn.dtos.PlayListDTO;
import nl.han.dea.jasmijn.dtos.PlayListsDTO;
import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;
import nl.han.dea.jasmijn.services.PlayListService;
import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class PlayListServiceTest {
    private PlayListService playListService;

    @BeforeEach
    public void setup(){
        playListService = new PlayListService();
    }

    @Test
    public void testAllPlayListsReturnsAllPlayLists(){
        TrackService trackService = Mockito.mock(TrackService.class);
        UserService userService = Mockito.mock(UserService.class);
        PlayListDAO playListDAO = Mockito.mock(PlayListDAO.class);
        this.playListService.setTrackService(trackService);
        this.playListService.setPlayListDAO(playListDAO);
        this.playListService.setUserService(userService);

        PlayListsDTO playListsDTO = buildPlayListsDTO();
        Mockito.when(playListDAO.getAllPlayLists(Mockito.anyInt())).thenReturn(playListsDTO);
        Mockito.when(trackService.getTracksByPlaylistId(Mockito.anyInt())).thenReturn(new TracksDTO(buildTrackDTO()));
        Assertions.assertEquals(playListsDTO, playListService.allPlayLists());
    }

    public List<TrackDTO> buildTrackDTO(){
        List<TrackDTO> trackDTOS = new ArrayList<>();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);
        trackDTO.setTitle("Track");
        trackDTOS.add(trackDTO);
        return trackDTOS;
    }

    public PlayListsDTO buildPlayListsDTO(){
        PlayListDTO playListDTO = new PlayListDTO();
        List<PlayListDTO> playListDTOS =  new ArrayList<>();
        playListDTO.setId(1);
        playListDTO.setName("Dance list");
        playListDTO.setTracks(new TracksDTO(buildTrackDTO()).getTracks());
        playListDTOS.add(playListDTO);
        return new PlayListsDTO(playListDTOS);
    }
}
