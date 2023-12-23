/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package penitipanhewan;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author wwwan
 */
public class database {
    public static Connection connectDb(){
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            //link database 
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/petshop", "root", ""); 
            return connect;
        }catch(Exception e) {e.printStackTrace();
        return null;
        }
    }
  
}
