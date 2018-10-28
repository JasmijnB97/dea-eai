package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.datasource.daos.PlayListDAO;
import nl.han.dea.jasmijn.dtos.PlayListsDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;
import nl.han.dea.jasmijn.services.PlayListService;
import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PlayListServiceTest extends TestUtils{
    private PlayListService playListService;
    private PlayListsDTO playListsDTO;

    @BeforeEach
    public void setup(){
        playListService = new PlayListService();
        TrackService trackService = Mockito.mock(TrackService.class);
        UserService userService = Mockito.mock(UserService.class);
        PlayListDAO playListDAO = Mockito.mock(PlayListDAO.class);
        this.playListService.setTrackService(trackService);
        this.playListService.setPlayListDAO(playListDAO);
        this.playListService.setUserService(userService);

        playListsDTO = buildPlayListsDTO();
        Mockito.when(playListDAO.getAllPlayLists(Mockito.anyInt())).thenReturn(playListsDTO);
        Mockito.when(trackService.getTracksByPlaylistId(Mockito.anyInt())).thenReturn(new TracksDTO(buildTrackDTO()));
    }

    @Test
    public void testAllPlayListsReturnsAllPlayLists(){
        Assertions.assertEquals(playListsDTO, playListService.allPlayLists(VALID_TOKEN));
    }
}
