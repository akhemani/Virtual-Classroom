package cdvirtualclient;

import java.awt.Dimension;
import java.awt.Toolkit;

public class Cdvirtualclient 
{
    public static void main(String[] args) 
    {
        // TODO code application logic here
        IP_Entry log=new IP_Entry();
         //Student log=new Student();
        //Mentor log =new Mentor();
       // StudentSessionHeld student=new StudentSessionHeld();
        Toolkit tool=Toolkit.getDefaultToolkit();    // abstract class implemented by awt
        Dimension size=tool.getScreenSize();
        //for ip_entry
        final int WIDTH=280; 
        final int HEIGHT=150;
        
        //final int WIDTH=700; 
        //final int HEIGHT=600;
        log.setVisible(true);
        log.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
        log.setTitle("IP_entry");
        log.setResizable(false);
        
    }
}
