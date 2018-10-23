package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.dto.PlayListDTO;
import nl.han.dea.jasmijn.dto.TrackDTO;
import nl.han.dea.jasmijn.services.PlayListService;
import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlayListController {
    private PlayListService playListService;
    private UserService userService;
    private TrackService trackService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response showPlayLists(){
        return Response.ok(playListService.allPlayLists()).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{id}/tracks")
    public Response showTracks(@PathParam("id") int playListId, @QueryParam("token") String token){
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        return Response.ok(trackService.getTracksByPlaylistId(playListId)).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}/tracks")
    public Response addTrackToPlaylist(@PathParam("id") int playListId, TrackDTO trackDTO, @QueryParam("token") String token){
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        playListService.addTrackToPlayList(playListId,trackDTO);
        return Response.ok(trackService.getTracksByPlaylistId(playListId)).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePlayList(@PathParam("id") int id, PlayListDTO playListDTO, @QueryParam("token") String token) {
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        playListService.updatePlayList(id, playListDTO);
        return Response.ok(playListService.allPlayLists()).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPlayList(PlayListDTO playListDTO, @QueryParam("token") String token){
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        playListService.createPlayList(playListDTO);
        return Response.ok(playListService.allPlayLists()).build();
    }

    @Path("{id}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response deletePlayList(@PathParam("id") int id, @QueryParam("token") String token){
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        playListService.deletePlayList(id);
        return Response.ok(playListService.allPlayLists()).build();
    }

    @Path("{id}/tracks/{trackId}")
    @DELETE
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteTrackFromPlayList(@PathParam("id") int playListId, @PathParam("trackId") int trackId, @QueryParam("token") String token){
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        playListService.deleteTrackFromPlayList(playListId, trackId);
        return Response.ok(trackService.getTracksByPlaylistId(playListId)).build();
    }

    @Inject
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Inject
    public void setPlayListService(PlayListService playListService){
        this.playListService = playListService;
    }

    @Inject
    public void setTrackService(TrackService trackService){
        this.trackService = trackService;
    }

}