package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.dto.LoginRequestDTO;
import nl.han.dea.jasmijn.dto.LoginResponseDTO;
import nl.han.dea.jasmijn.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {

    private UserService userService;

    @POST
    @Produces({MediaType.APPLICATION_JSON}) //geef je aan wat voor datatype de server terug moet geven.
    @Consumes({MediaType.APPLICATION_JSON})  //wat voor datatype de server accepteert.
    public Response login(LoginRequestDTO loginRequestDTO){
        String user = loginRequestDTO.getUser();
        String password = loginRequestDTO.getPassword();

        if(!userService.authenticate(user, password)){
            return Response.status(401).build();
        }

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setUser(user);
        loginResponseDTO.setToken(userService.getToken());

        return Response.ok(loginResponseDTO).build();
    }

    @Inject
    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
