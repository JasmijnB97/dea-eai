package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.dto.LoginRequestDTO;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class LoginControllerTest {

    private UserService us;

    @BeforeEach
    public void setup(){
        us = new UserService();
    }

    @Test
    public void shouldReturnTrueForValidUserName(){
        boolean validUser = us.validUserName("jasmijn");
        Assertions.assertEquals(true, validUser);
    }

    @Test
    public void shouldReturnTrueForValidPassword(){
        boolean validPassword = us.validPassword("wachtwoord");
        Assertions.assertEquals(true, validPassword);
    }
@Test
    public void testOfUserServiceReturnOk(){
        //setup
        LoginController loginController = new LoginController();
        UserService userService= Mockito.mock(UserService.class);
        loginController.setUserService(userService);
        Mockito.when(userService.authenticate(Mockito.anyString(), Mockito.anyString())).thenReturn(true);//false
        LoginRequestDTO loginRequestDTO = new LoginRequestDTO();
        loginRequestDTO.setUser("testuser");
        loginRequestDTO.setPassword("testwachtwoord");

        //test
        Response test = loginController.login(loginRequestDTO);

        //Verify
        Assertions.assertEquals(200, test.getStatus());//404
    }

}
