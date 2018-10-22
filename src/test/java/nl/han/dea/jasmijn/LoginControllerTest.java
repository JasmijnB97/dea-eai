package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.dto.LoginRequestDTO;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginControllerTest {

    private LoginController loginController;

    @BeforeEach
    public void setup(){
        //setup
        loginController = new LoginController();
        UserService userService = Mockito.mock(UserService.class);
        loginController.setUserService(userService);
        Mockito.when(userService.authenticate("testuser", "testwachtwoord")).thenReturn(true);//false

    }

    @Test
    public void testOfUserServiceReturnOk() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("testuser");
        loginRequestDTO.setPassword("testwachtwoord");
        //test
        Response test = loginController.login(loginRequestDTO);

        //Verify
        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfUserServiceReturn401() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("testuser");
        loginRequestDTO.setPassword("fouttttttt");
        //test
        Response test = loginController.login(loginRequestDTO);

        //Verify
        Assertions.assertEquals(401, test.getStatus());
    }

}
