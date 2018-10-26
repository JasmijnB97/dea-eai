package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.datasource.daos.UserDAO;
import nl.han.dea.jasmijn.dtos.LoginResponseDTO;

import javax.inject.Inject;

public class UserService {

    private UserDAO userDAO;

    public void setToken(String token, String name, String password) {
        userDAO.setToken(token, name, password);
    }

    public boolean authenticate(String name, String password){
        LoginResponseDTO loginResponseDTO = userDAO.getUser(name, password);
        return loginResponseDTO != null;
    }

    public boolean tokenIsCorrect(String token){
        return userDAO.getUserId(token) != -1;
    }

    public int getUserId(String token){
        return userDAO.getUserId(token);
    }

    @Inject
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }
}
