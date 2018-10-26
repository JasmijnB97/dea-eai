package nl.han.dea.jasmijn.controllers;

import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackController {

    private TrackService trackService;

    private UserService userService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response showTracks(@QueryParam("token") String token, @QueryParam("forPlaylist") int playListId){
        if(!userService.tokenIsCorrect(token)){
            return Response.status(401).build();
        }
        return Response.ok(trackService.getTracksThatNotExistsInPlayList(playListId)).build();

    }

    @Inject
    public void setTrackService(TrackService trackService){
        this.trackService = trackService;
    }

    @Inject
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
