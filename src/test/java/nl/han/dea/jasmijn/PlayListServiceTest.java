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

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class PlayListServiceTest {

    private PlayListService playListService;
    private PlayListDTO playListDTO;
    private PlayListsDTO playListsDTO;
    private List<PlayListDTO> playListDTOS;
    private TracksDTO tracksDTO;

    @BeforeEach
    public void setup(){
        playListService = new PlayListService();

        //TracksDTO
        List<TrackDTO> trackDTOS = new ArrayList<>();
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(1);
        trackDTO.setTitle("TrackOne");
        trackDTOS.add(trackDTO);
        tracksDTO = new TracksDTO(trackDTOS);

        //PlayListsDTO
        playListDTO = new PlayListDTO();
        playListDTOS =  new ArrayList<>();
        playListDTO.setId(1);
        playListDTO.setName("Dance list");
        playListDTO.setTracks(tracksDTO.getTracks());
        playListDTOS.add(playListDTO);
        playListsDTO = new PlayListsDTO(playListDTOS);

        PlayListDAO playListDAO = Mockito.mock(PlayListDAO.class);
        this.playListService.setPlayListDAO(playListDAO);
        UserService userService = Mockito.mock(UserService.class);
        this.playListService.setUserService(userService);
        TrackService trackService = Mockito.mock(TrackService.class);
        this.playListService.setTrackService(trackService);
    }

    @Test
    public void testAllPlayListsReturnsAllPlayLists(){
//        Assertions.assertEquals(PlayListsDTO, playListService.allPlayLists());
    }
}
