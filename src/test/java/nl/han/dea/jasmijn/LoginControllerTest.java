package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.controllers.LoginController;
import nl.han.dea.jasmijn.dtos.LoginRequestDTO;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginControllerTest extends TestUtils{

    private LoginController loginController;

    @BeforeEach
    public void setup(){
        loginController = new LoginController();
        UserService userService = Mockito.mock(UserService.class);
        loginController.setUserService(userService);
        Mockito.when(userService.authenticate(VALID_NAME, VALID_PASSWORD)).thenReturn(true);
    }

    @Test
    public void testOfUserServiceReturnOk() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser(VALID_NAME);
        loginRequestDTO.setPassword(VALID_PASSWORD);

        Response test = loginController.login(loginRequestDTO);

        Assertions.assertEquals(200, test.getStatus());
    }

    @Test
    public void testOfUserServiceReturn401() {
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("invalid name");
        loginRequestDTO.setPassword("invalid password");

        Response test = loginController.login(loginRequestDTO);

        Assertions.assertEquals(401, test.getStatus());
    }

}
