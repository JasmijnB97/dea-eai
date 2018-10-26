package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.datasource.daos.PlayListDAO;
import nl.han.dea.jasmijn.datasource.daos.UserDAO;
import nl.han.dea.jasmijn.dtos.LoginResponseDTO;
import nl.han.dea.jasmijn.dtos.PlayListsDTO;
import nl.han.dea.jasmijn.dtos.TracksDTO;
import nl.han.dea.jasmijn.services.PlayListService;
import nl.han.dea.jasmijn.services.TrackService;
import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class UserServiceTest {
    private UserService userService;
    private static final String VALID_NAME = "name";
    private static final String VALID_PASSWORD = "password";
    private static final String VALID_TOKEN = "123a-456b";

    @BeforeEach
    public void setup(){
        userService = new UserService();

        UserDAO userDAO = Mockito.mock(UserDAO.class);
        this.userService.setUserDAO(userDAO);
        LoginResponseDTO loginResponseDTO = buildLoginResponseDTO();
        Mockito.when(userDAO.getUser(VALID_NAME, VALID_PASSWORD)).thenReturn(loginResponseDTO);
        Mockito.when(userDAO.getUserId(Mockito.anyString())).thenReturn(-1);
        Mockito.when(userDAO.getUserId(VALID_TOKEN)).thenReturn(1);

    }

    @Test
    public void testValidAuthenticate(){
        Assertions.assertTrue(userService.authenticate(VALID_NAME, VALID_PASSWORD));
    }

    @Test
    public void testInvalidAuthenticate(){
        Assertions.assertFalse(userService.authenticate("invalid name", "invalid password"));
    }

    @Test
    public void testValidTokenInTokenIsCorrect(){
        Assertions.assertTrue(userService.tokenIsCorrect(VALID_TOKEN));
    }

    @Test
    public void testInvalidTokenInTokenIsIncorrect(){
        Assertions.assertFalse(userService.tokenIsCorrect("938jjksh"));
    }

    public LoginResponseDTO buildLoginResponseDTO(){
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(VALID_TOKEN);
        loginResponseDTO.setUser(VALID_NAME);
        return loginResponseDTO;
    }
}
