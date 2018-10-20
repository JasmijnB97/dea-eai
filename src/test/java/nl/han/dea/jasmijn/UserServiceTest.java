package nl.han.dea.jasmijn;

import nl.han.dea.jasmijn.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//public class UserServiceTest {
//
//    private UserService us;
//
//    @BeforeEach
//    public void setup(){
//        us = new UserService();
//    }
//
//    @Test
//    public void shouldReturnTrueForValidUserName(){
//        boolean validUser = us.validUserName("jasmijn");
//        Assertions.assertEquals(true, validUser);
//    }
//
//    @Test
//    public void shouldReturnTrueForValidPassword(){
//        boolean validPassword = us.validPassword("wachtwoord");
//        Assertions.assertEquals(true, validPassword);
//    }
//
//    @Test
//    public void shouldReturnTrueForValidToken(){
//        us.setToken("123a", "jasmijn", "wachtwoord");
//        Assertions.assertEquals(true, us.tokenIsCorrect("123a"));
//    }
//}
