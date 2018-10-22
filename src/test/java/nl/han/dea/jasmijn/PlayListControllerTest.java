package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.dto.LoginRequestDTO;
import nl.han.dea.jasmijn.dto.PlayListDTO;
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
    }

    @Test
    public void testOfTokenIsCorrectReturnsOkInShowTracks(){
        Response test = playListController.showTracks(1, "123-345");

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfTokenIsCorrectReturns401InShowTracks(){
        Response test = playListController.showTracks(1, "2222222");

        Assertions.assertEquals(401, test.getStatus());
    }

    @Test
    public void testUpdatePlayListReturnsOk(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.updatePlayList(1, playListDTO);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testCreatePlayListReturnsOk(){
        PlayListDTO playListDTO = new PlayListDTO();
        Response test = playListController.createPlayList(playListDTO);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testDeletePlayListReturnsOk(){
        Response test = playListController.deletePlayList(1);

        Assertions.assertEquals(200, test.getStatus());
    }

}
