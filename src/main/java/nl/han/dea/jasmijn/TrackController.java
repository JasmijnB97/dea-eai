package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.dto.TrackDTO;
import nl.han.dea.jasmijn.dto.TracksDTO;
import nl.han.dea.jasmijn.services.TrackService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

//@Path("/tracks")
//public class TrackController {
//
//    private TrackService trackService;
//
//    @GET
//    @Produces({MediaType.APPLICATION_JSON})
//    public Response showTracks(){
//        return Response.ok(trackService.allTracks()).build();
//    }
//
//    @Inject
//    public void setUserService(TrackService trackService){
//        this.trackService = trackService;
//    }
//}
