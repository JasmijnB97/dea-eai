package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.controllers.PlayListController;
import nl.han.dea.jasmijn.dtos.PlayListDTO;
import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.services.PlayListService;
import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlayListControllerTest {

    private PlayListController playListController;
    private static final String VALID_TOKEN = "123a-345b";
    private static final String INVALID_TOKEN = "b543-a321";
    private static final int PLAYLIST_ID = 1;
    private static final int TRACK_ID = 1;

    @BeforeEach
    public void setup(){
        playListController = new PlayListController();
        PlayListService playListService= Mockito.mock(PlayListService.class);
        playListController.setPlayListService(playListService);
        UserService userService = Mockito.mock(UserService.class);
        playListController.setUserService(userService);
        TrackService trackService = Mockito.mock(TrackService.class);
        playListController.setTrackService(trackService);
        Mockito.when(userService.tokenIsCorrect(VALID_TOKEN)).thenReturn(true);
//        Mockito.when(trackService.equalsCurrentOfflineAvailable(1, true)).thenReturn(false);
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


    //TODO addTrackToPlaylist -> offline availeble testen
    @Test
    public void testAddTrackToPlayListReturnsOk(){
        TrackDTO trackDTO = new TrackDTO();
//        trackDTO.setId(1);
//        trackDTO.setOfflineAvailable(true);

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
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.updatePlayList(PLAYLIST_ID, playListDTO, VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInUpdatePlayListReturns401(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.updatePlayList(PLAYLIST_ID, playListDTO, INVALID_TOKEN);

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
        Response test = playListController.deleteTrackFromPlayList(PLAYLIST_ID, 2,VALID_TOKEN);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInDeleteTrackFromPlayListReturns401(){
        Response test = playListController.deleteTrackFromPlayList(PLAYLIST_ID, 2,INVALID_TOKEN);

        Assertions.assertEquals(401, test.getStatus());
    }
}
