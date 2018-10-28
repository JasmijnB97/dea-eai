package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.controllers.PlayListController;
import nl.han.dea.jasmijn.dtos.PlayListDTO;
import nl.han.dea.jasmijn.dtos.PlayListRequestDTO;
import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.services.PlayListService;
import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlayListControllerTest extends TestUtils{

    private PlayListController playListController;

    @BeforeEach
    public void setup(){
        playListController = new PlayListController();

        PlayListService playListService= Mockito.mock(PlayListService.class);
        UserService userService = Mockito.mock(UserService.class);
        TrackService trackService = Mockito.mock(TrackService.class);

        playListController.setPlayListService(playListService);
        playListController.setUserService(userService);
        playListController.setTrackService(trackService);

        Mockito.when(userService.tokenIsCorrect(VALID_TOKEN)).thenReturn(true);
    }

    @Test
    public void testShowTracksReturnsOk(){
        Response test = playListController.showTracks(PLAYLIST_ID, VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInShowTracksReturns401(){
        Response test = playListController.showTracks(PLAYLIST_ID, INVALID_TOKEN);

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testAddTrackToPlayListReturnsOk(){
        TrackDTO trackDTO = new TrackDTO();
        Response test = playListController.addTrackToPlaylist(PLAYLIST_ID, trackDTO,VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInAddTrackToPlayListReturns401(){
        TrackDTO trackDTO = new TrackDTO();
        Response test = playListController.addTrackToPlaylist(PLAYLIST_ID, trackDTO,INVALID_TOKEN);

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testUpdatePlayListReturnsOk(){
        PlayListRequestDTO playListRequestDTO = new PlayListRequestDTO();
        Response test = playListController.updatePlayList(PLAYLIST_ID, playListRequestDTO, VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInUpdatePlayListReturns401(){
        PlayListRequestDTO playListRequestDTO = new PlayListRequestDTO();
        Response test = playListController.updatePlayList(PLAYLIST_ID, playListRequestDTO, INVALID_TOKEN);

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testCreatePlayListReturnsOk(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.createPlayList(playListDTO, VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInCreatePlayListReturns401(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.createPlayList(playListDTO, INVALID_TOKEN);

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testDeletePlayListReturnsOk(){
        Response test = playListController.deletePlayList(PLAYLIST_ID, VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInDeletePlayListReturns401(){
        Response test = playListController.deletePlayList(PLAYLIST_ID, INVALID_TOKEN);

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testDeleteTrackFromPlayListReturnsOk(){
        Response test = playListController.deleteTrackFromPlayList(PLAYLIST_ID, TRACK_ID,VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInDeleteTrackFromPlayListReturns401(){
        Response test = playListController.deleteTrackFromPlayList(PLAYLIST_ID, TRACK_ID,INVALID_TOKEN);

        Assertions.assertEquals(401, test.getStatus());
    }
}
