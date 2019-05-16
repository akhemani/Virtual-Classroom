/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;

import java.sql.*;
import java.util.Vector;

/**
 *
 * @author Chayan-Dhaddha
 */
public class ConnectionFactory 
{    
    Connection con;
    Statement stmt;
    
    private ConnectionFactory()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/virtualclass_db","root","");
            stmt = con.createStatement();    
        }
        catch(Exception ex)
        {
            System.out.println("Exception in db create...");
        }
    }
    private static ConnectionFactory conn;
    
    static 
    {
        conn = new ConnectionFactory();
    }

    public static ConnectionFactory getInstance()
    {
        return conn;
    }
    public  int setData(String query)throws Exception
    {    
        int n=stmt.executeUpdate(query);
        return n;
    }
    
    public  ResultSet getResultSet(String query)throws Exception
    {    
        ResultSet rs=stmt.executeQuery(query);
        return rs;
    }     
    
    public  Vector<Vector> getData(String query)throws Exception
    {   
        ResultSet rs=stmt.executeQuery(query);
        ResultSetMetaData rsmd=rs.getMetaData();
        int col=rsmd.getColumnCount();
        Vector<Vector> main=new Vector<Vector>();
        while(rs.next())
        {
             Vector<String> sub=new Vector<String>(); 
             for(int i=1;i<=col;i++)
                 sub.add(rs.getString(i));
             main.add(sub);
        }
        return main; 
        
    }
}
