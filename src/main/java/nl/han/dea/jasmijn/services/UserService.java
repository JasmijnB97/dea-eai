package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.LoginController;

import javax.ws.rs.core.Response;

public class UserService {
    private static final String TOKEN = "1234-5678";
    public boolean tokenIsCorrect(String token){
        return TOKEN.equals(token);
    }

    public String getToken(){
        return TOKEN;
    }

    public boolean authenticate(String name, String password){
        return (validUserName(name) || validPassword(password));
    }

    public boolean validUserName (String name) {
        if(name.equals("jasmijn")) return true;
        return false;
    }

    public boolean validPassword (String password) {
        if(password.equals("wachtwoord")) return true;
        return false;
    }
}
