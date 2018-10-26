package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.datasource.daos.UserDAO;

import javax.inject.Inject;

public class UserService {

    private UserDAO userDAO;
    private static String storedToken;

    public boolean tokenIsCorrect(String token){
        return storedToken.equals(token);
    }

    public void setToken(String token, String name, String password) {
            storedToken = token;
            userDAO.setToken(token, name, password);
    }

    public String getToken() {
        return storedToken;
    }

    public boolean authenticate(String name, String password){
        return (validUserName(name) && validPassword(password));
    }

    public boolean validUserName (String name) {
        return name.equals(userDAO.getUser(storedToken).getUser());
    }

    public boolean validPassword (String password) {
        return password.equals(userDAO.getUser(storedToken).getPassword());
    }

    public int getUserId(){
        return userDAO.getUserId(storedToken);
    }

    @Inject
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }



}
