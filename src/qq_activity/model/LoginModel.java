/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package qq_activity.model;


/**
 *
 * @author Rheeey
 */
public class LoginModel {

    private String username;
    private String password;
    private String role;
    
//    Constructors
    public LoginModel(){}
    
    public LoginModel(String role){
        this.role = role;
    }
    
    public LoginModel(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public void changePassword(String newPassword){
        this.password = newPassword;
    }
    
    public boolean verifyPassword(String oldPassword) {
        return this.password.equals(oldPassword);
    }

    
//    getters and setters
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }   
    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return this.role;
    }
    
}
