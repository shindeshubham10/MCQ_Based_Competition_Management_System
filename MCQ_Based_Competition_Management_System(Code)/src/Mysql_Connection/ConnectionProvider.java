/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mysql_Connection;
import java.sql.*;

/**
 *
 * @author pc
 */
public class ConnectionProvider 
{
    public static Connection getcon()
    {
        Connection con=null;
        String url="jdbc:mysql://localhost:3306/MCMS_Project";
        String user="root";
        String password="shubhamMysql@321";
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
            
            
            con=DriverManager.getConnection(url, user, password);
            System.out.println("Connection Established");
            return con;
        } 
        catch (ClassNotFoundException | SQLException e) 
        {
            System.out.println("Exception :"+e.getMessage());
            return null;
        }
        
    }
    
}
