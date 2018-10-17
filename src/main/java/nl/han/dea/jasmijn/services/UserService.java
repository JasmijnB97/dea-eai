package nl.han.dea.jasmijn.services;

import nl.han.dea.jasmijn.dao.UserDAO;

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
        System.out.println("ik ben in setToken van userService");
    }

    public String getToken() {
        return token;
    }

    public boolean authenticate(String name, String password){
        System.out.println("valid name = " + userDAO.getUser(token).getUser());
        System.out.println("valid password = " + userDAO.getUser(token).getPassword());
        return (validUserName(name) && validPassword(password));
    }

    public boolean validUserName (String name) {
        if(name.equals(userDAO.getUser(token).getUser())) return true;
        return false;
    }

    public boolean validPassword (String password) {
        if(password.equals(userDAO.getUser(token).getPassword())) return true;
        return false;
    }

    public int getUserId(){
        return userDAO.getUserId(token);
    }

    @Inject
    public void setUserDAO(UserDAO userDAO){
        this.userDAO = userDAO;
    }



}
