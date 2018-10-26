package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.dtos.PlayListDTO;
import nl.han.dea.jasmijn.dtos.PlayListsDTO;
import nl.han.dea.jasmijn.dtos.TrackDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;

import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    protected static final String VALID_TOKEN = "123a-345b";
    protected static final String INVALID_TOKEN = "b543-a321";
    protected static final int PLAYLIST_ID = 1;
    protected static final int TRACK_ID = 1;
    protected static final String VALID_NAME = "name";
    protected static final String VALID_PASSWORD = "password";

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
