package qq_activity.service;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import qq_activity.model.LoginModel;

/**
 *
 * @author Rheeey
 */
public class LoginServiceImpl implements LoginService {
    List<LoginModel> loginCredentials = new ArrayList<>();

    @Override
    public void createAccount() { 
        loginCredentials.add(new LoginModel("admin", "12345"));
        loginCredentials.add(new LoginModel("customer", "12345"));
    }

    @Override
    public void login(LoginModel user) {   
        
//        get user input
        String inputUsername = user.getUsername();
        String inputPassword = user.getPassword();
        
        
//        assign admin and customer credentials to variables
        LoginModel firstLoginModel = loginCredentials.get(0);
        String adminUsername = firstLoginModel.getUsername();
        String adminPassword = firstLoginModel.getPassword();

        LoginModel secondLoginModel = loginCredentials.get(1);
        String customerUsername = secondLoginModel.getUsername();
        String customerPassword = secondLoginModel.getPassword();
        
        
        
//        check credientials and assign role
        if(inputUsername.equals(adminUsername) && inputPassword.equals(adminPassword)){
            user.setRole("admin");
            
        }
        else if(inputUsername.equals(customerUsername) && inputPassword.equals(customerPassword)){
            user.setRole("customer");
            
        }
        else{
            user.setRole(null);
            System.out.println("ERROR : Invalid credentials.");
            System.out.print("\nPress \"ENTER\" to continue...");
            try {
                System.in.read();
            } catch (IOException e) { 
                // do nothing
            }
        }
   
    }

    @Override
    public void changePassword(String inputUsername, String oldPassword, String newPassword) {
        LoginModel targetUser = null;
        for (LoginModel userModel : loginCredentials) {
            if (userModel.getUsername().equals(inputUsername)) {
                targetUser = userModel;
                break;
            }
        }

        if (targetUser != null) {
            if (newPassword.isEmpty()){
                System.out.println("Password cannot be empty. Password change failed.");
                System.out.print("\nPress \"ENTER\" to continue...");
                try {
                    System.in.read();
                } catch (IOException e) {
                    // do nothing
                }
            }
            else if (!targetUser.verifyPassword(oldPassword)){
                System.out.println("Incorrect password. Password change failed.");
                System.out.print("\nPress \"ENTER\" to continue...");
                try {
                    System.in.read();
                } catch (IOException e) {
                    // do nothing
                }
            }
            else {
                targetUser.changePassword(newPassword);
                System.out.println("Password changed successfully!");
                System.out.print("\nPress \"ENTER\" to continue...");
                try {
                    System.in.read();
                } catch (IOException e) {
                    // do nothing
                }
            }          
        } 
        else {
            System.out.println("User not found. Password change failed.");
            System.out.print("\nPress \"ENTER\" to continue...");
            try {
                System.in.read();
            } catch (IOException e) {
                // do nothing
            } 
        }
    }   
}
