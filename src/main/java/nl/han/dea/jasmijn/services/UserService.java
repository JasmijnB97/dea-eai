package nl.han.dea.jasmijn.services;

import net.moznion.random.string.RandomStringGenerator;
import nl.han.dea.jasmijn.dao.TrackDAO;
import nl.han.dea.jasmijn.dao.UserDAO;

import javax.inject.Inject;

public class UserService {

    private UserDAO userDAO;
//    RandomStringGenerator generator = new RandomStringGenerator();
//    private String token = generator.generateFromPattern("cnnn-cnnn-cnnn");

    private static final String TOKEN = "1234-5678";

//    public boolean tokenIsCorrect(String token){
//        return this.token.equals(token);
//    }

        public boolean tokenIsCorrect(String token){
        return TOKEN.equals(token);
    }

//    public String getToken(){
//        return TOKEN;
//    }
    public String getToken(){
        return TOKEN;
    }

//    public String getToken(String name, String password){
//        userDAO.setToken(token, name, password);
//        return token;
//    }

    public boolean authenticate(String name, String password){
        return (validUserName(name) && validPassword(password));
    }

    public boolean validUserName (String name) {
        if(name.equals(userDAO.getUser().getUser())) return true;
        return false;
    }

    public boolean validPassword (String password) {
        if(password.equals(userDAO.getUser().getPassword())) return true;
        return false;
    }

    @Inject
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }


}
