/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author milos
 */
public class SessionSingleton {
    private static SessionSingleton instance = null;
    private User currentUser;
    
    private SessionSingleton(){        
    }
    
    public static SessionSingleton getInstance(){
        if(instance == null){
            instance = new SessionSingleton();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    
}
