//package nl.han.dea.jasmijn;
//
//import nl.han.dea.jasmijn.controllers.TrackController;
//import nl.han.dea.jasmijn.dtos.TracksDTO;
//import nl.han.dea.jasmijn.services.TrackService;
//import nl.han.dea.jasmijn.services.UserService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import javax.ws.rs.core.Response;
//
//public class TrackControllerTest extends TestUtils{
//
//    private TrackController trackController;
//
//    @BeforeEach
//    public void setup(){
//        trackController = new TrackController();
//
//        TrackService trackService = Mockito.mock(TrackService.class);
//        UserService userService = Mockito.mock(UserService.class);
//
//        this.trackController.setTrackService(trackService);
//        this.trackController.setUserService(userService);
//
//        Mockito.when(userService.tokenIsCorrect(VALID_TOKEN)).thenReturn(true);
//        Mockito.when(trackService.getTracksThatDontExistsInPlayList(Mockito.anyInt())).thenReturn(new TracksDTO(buildTrackDTO()));
//    }
//
//    @Test
//    public void testShowTracksReturnsOk(){
//        Response test = trackController.showTracks(VALID_TOKEN, PLAYLIST_ID);
//
//        Assertions.assertEquals(200, test.getStatus());
//    }
//
//    @Test
//    public void testOfTokenIsInvalidInShowTracksReturns401(){
//        Response test = trackController.showTracks(INVALID_TOKEN, PLAYLIST_ID);
//
//        Assertions.assertEquals(401, test.getStatus());
//    }
//
//}
