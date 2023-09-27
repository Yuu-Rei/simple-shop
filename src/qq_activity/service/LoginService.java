package qq_activity.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

import qq_activity.model.LoginModel;

/**
 *
 * @author Rheeey
 */
public interface LoginService {
    
    void createAccount();
    
    void login(LoginModel user);
   
    void changePassword(String inputUsername, String oldPassword, String newPassword);
}
