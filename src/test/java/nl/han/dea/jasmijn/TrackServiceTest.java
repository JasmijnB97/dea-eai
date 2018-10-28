package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.datasource.daos.TrackDAO;
import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;
import nl.han.dea.jasmijn.services.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TrackServiceTest extends TestUtils{
    private TrackService trackService;
    private TracksDTO tracksDTO;

    @BeforeEach
    public void setup(){
        trackService = new TrackService();
        TrackDAO trackDAO = Mockito.mock(TrackDAO.class);
        this.trackService.setTrackDAO(trackDAO);
        tracksDTO = new TracksDTO(buildTrackDTO());

        List<Integer> trackIds = new ArrayList<>();
        trackIds.add(TRACK_ID);

        Mockito.when(trackDAO.getAllTracks()).thenReturn(tracksDTO);
        Mockito.when(trackDAO.getTracksThatNotExistsInPlayList(Mockito.anyInt())).thenReturn(null);
        Mockito.when(trackDAO.getTracksThatNotExistsInPlayList(PLAYLIST_ID)).thenReturn(tracksDTO);
        Mockito.when(trackDAO.getTracksByPlaylistId(PLAYLIST_ID)).thenReturn(trackIds);
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
        Assertions.assertNull(trackService.getTracksThatNotExistsInPlayList(2));
    }

    public TrackDTO getTrackDTO(int trackId){
        TrackDTO trackDTO = null;
        for(TrackDTO track : tracksDTO.getTracks()){
            if(track.getId() == trackId){
                trackDTO = track;
            }
        }
        return trackDTO;
    }

    @Test
    public void testGetTrackByTrackIdSameId(){
        Assertions.assertEquals(getTrackDTO(TRACK_ID), trackService.getTrackByTrackId(TRACK_ID));
    }

    @Test
    public void testGetTrackByTrackIdDifferentId(){
        Assertions.assertNotEquals(getTrackDTO(2), trackService.getTrackByTrackId(TRACK_ID));
    }

    @Test
    public void testGetTracksByPlaylistId(){
        TracksDTO tracksFromPlaylistId = trackService.getTracksByPlaylistId(PLAYLIST_ID);
        Assertions.assertEquals(tracksDTO.getTracks(), tracksFromPlaylistId.getTracks());
    }

    @Test
    public void testEqualsCurrentOfflineAvailable(){
        Assertions.assertTrue(trackService.equalsCurrentOfflineAvailable(TRACK_ID, true));
    }

    @Test
    public void testNotEqualsCurrentOfflineAvailable(){
        Assertions.assertFalse(trackService.equalsCurrentOfflineAvailable(TRACK_ID, false));
    }

    @Test
    public void testTrackInPlaylist(){
        Assertions.assertTrue(trackService.trackInPlaylist(PLAYLIST_ID, TRACK_ID));
    }
}
