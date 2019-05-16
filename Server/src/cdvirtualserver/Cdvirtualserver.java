/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;
import java.awt.*;
import java.net.*;
import java.util.Vector;
/**
 *
 * @author Chayan-Dhaddha
 */
public class Cdvirtualserver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            ServerSocket server=new ServerSocket(2234);  
            CommResource.admin=new Admin();
            CommResource.loggedInClient=new Vector<ClientInfo>();
            CommResource.sessionInfo=new SessionInfo();
            CommResource.sessionInfo.current_status=false;
            
            while(true)
            {
                Socket client=server.accept();
                new ClientHandlerThread(client);
            }
            
        }catch(Exception ex){
            
        }
        
    }
    
}
