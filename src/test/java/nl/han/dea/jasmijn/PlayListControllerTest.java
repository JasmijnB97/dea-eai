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

    @BeforeEach
    public void setup(){
        playListController = new PlayListController();
        PlayListService playListService= Mockito.mock(PlayListService.class);
        playListController.setPlayListService(playListService);
        UserService userService = Mockito.mock(UserService.class);
        playListController.setUserService(userService);
        TrackService trackService = Mockito.mock(TrackService.class);
        playListController.setTrackService(trackService);
        Mockito.when(userService.tokenIsCorrect("123-345")).thenReturn(true);
//        Mockito.when(trackService.equalsCurrentOfflineAvailable(1, true)).thenReturn(false);
    }

    @Test
    public void testShowTracksReturnsOk(){
        Response test = playListController.showTracks(1, "123-345");

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInShowTracksReturns401(){
        Response test = playListController.showTracks(1, "3839bg");

        Assertions.assertEquals(401, test.getStatus());
    }


    //TODO addTrackToPlaylist -> offline availeble testen
    @Test
    public void testAddTrackToPlayListReturnsOk(){
        TrackDTO trackDTO = new TrackDTO();
//        trackDTO.setId(1);
//        trackDTO.setOfflineAvailable(true);

        Response test = playListController.addTrackToPlaylist(1, trackDTO,"123-345");

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInAddTrackToPlayListReturns401(){
        TrackDTO trackDTO = new TrackDTO();
        Response test = playListController.addTrackToPlaylist(1, trackDTO,"3839bg");

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testUpdatePlayListReturnsOk(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.updatePlayList(1, playListDTO, "123-345");

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInUpdatePlayListReturns401(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.updatePlayList(1, playListDTO, "3839bg");

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testCreatePlayListReturnsOk(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.createPlayList(playListDTO, "123-345");

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInCreatePlayListReturns401(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.createPlayList(playListDTO, "3839bg");

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testDeletePlayListReturnsOk(){
        Response test = playListController.deletePlayList(1, "123-345");

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInDeletePlayListReturns401(){
        Response test = playListController.deletePlayList(1, "3839bg");

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testDeleteTrackFromPlayListReturnsOk(){
        Response test = playListController.deleteTrackFromPlayList(1, 2,"123-345");

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsInvalidInDeleteTrackFromPlayListReturns401(){
        Response test = playListController.deleteTrackFromPlayList(1, 2,"3839bg");

        Assertions.assertEquals(401, test.getStatus());
    }
}
