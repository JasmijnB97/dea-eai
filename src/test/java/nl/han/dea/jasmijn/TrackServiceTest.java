package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.datasource.daos.TrackDAO;
import nl.han.dea.jasmijn.dtos.TracksDTO;
import nl.han.dea.jasmijn.services.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class TrackServiceTest extends TestUtils{
    private TrackService trackService;
    private TracksDTO tracksDTO;

    @BeforeEach
    public void setup(){
        trackService = new TrackService();
        TrackDAO trackDAO = Mockito.mock(TrackDAO.class);
        this.trackService.setTrackDAO(trackDAO);
        tracksDTO = new TracksDTO(buildTrackDTO());

        Mockito.when(trackDAO.getAllTracks()).thenReturn(tracksDTO);
        Mockito.when(trackDAO.getTracksThatNotExistsInPlayList(Mockito.anyInt())).thenReturn(null);
        Mockito.when(trackDAO.getTracksThatNotExistsInPlayList(PLAYLIST_ID)).thenReturn(tracksDTO);
    }

    @Test
    public void testAllTracksReturnsAllTracks(){
        Assertions.assertEquals(tracksDTO, trackService.allTracks());
    }

    @Test
    public void testGetTracksThatNotExistsInPlayListReturnsTracksThatNotExistsInPlayList(){
        Assertions.assertEquals(tracksDTO, trackService.getTracksThatNotExistsInPlayList(PLAYLIST_ID));
    }

    @Test
    public void testGetTracksThatNotExistsInPlayListReturnsNullWhenAllTracksExistsInPlayList(){
        Assertions.assertEquals(null, trackService.getTracksThatNotExistsInPlayList(2));
    }




}
