package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.datasource.daos.UserDAO;

import javax.inject.Inject;

public class UserService {

    private UserDAO userDAO;
    private static String token;

    public boolean tokenIsCorrect(String token){
        return this.token.equals(token);
    }

    public void setToken(String token, String name, String password) {
            this.token = token;
            userDAO.setToken(token, name, password);
    }

    public String getToken() {
        return token;
    }

    public boolean authenticate(String name, String password){
        return (validUserName(name) && validPassword(password));
    }

    public boolean validUserName (String name) {
        return name.equals(userDAO.getUser(token).getUser());
    }

    public boolean validPassword (String password) {
        return password.equals(userDAO.getUser(token).getPassword());
    }

    public int getUserId(){
        return userDAO.getUserId(token);
    }

    @Inject
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }



}
