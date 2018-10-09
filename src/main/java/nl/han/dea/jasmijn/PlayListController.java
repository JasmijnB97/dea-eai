package nl.han.dea.jasmijn;


import nl.han.dea.jasmijn.dao.PlayListDAO;
import nl.han.dea.jasmijn.dao.TrackDAO;
import nl.han.dea.jasmijn.dto.TracksDTO;
import nl.han.dea.jasmijn.services.PlayListService;
import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;


@Path("/playlists")
public class PlayListController {
    private PlayListService playListService;
    private UserService userService;
    private TrackService trackService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response showPlayLists(){
        return Response.ok(playListService.all()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}/tracks")
    public Response showTracks(@PathParam("id") int playlistId, @QueryParam("token") String token){
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        TracksDTO tracksDTO = new TracksDTO(trackService.getTracksByPlaylistId(playlistId));

        return Response.ok(tracksDTO).build();
    }

    @Inject
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Inject
    public void setPlayListService(PlayListService playListService){
        this.playListService = playListService;

        System.out.println("we zijn onder playlistController -> inject playlistService!!!!!!!");
    }

    @Inject
    public void setTrackService(TrackService trackService){
        this.trackService = trackService;
    }


}