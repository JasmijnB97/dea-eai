package nl.han.dea.jasmijn.dtos;

import java.util.List;

public class PlayListsDTO {

    private List<PlayListDTO> playlists;
    private int length;

    public PlayListsDTO(List<PlayListDTO> playlists){
        this.playlists = playlists;
    }

    public List<PlayListDTO> getPlaylists() {
        return playlists;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
