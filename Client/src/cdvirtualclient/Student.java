package cdvirtualclient;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Student extends JFrame
{
    
    JTabbedPane tab;
    StudentUpcomingSession panel_upcoming_session;
    StudentPastSession panel_past_session;
    StudentSessionHeld session_held;
    Dimension size;
    public Student()
    {         
        Toolkit tool=Toolkit.getDefaultToolkit();
        size=tool.getScreenSize();
        //final int WIDTH=700; 
        //final int HEIGHT=600;
        this.setVisible(true);
        this.setBounds(0,0,size.width,size.height);
        this.setTitle("Student");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        panel_upcoming_session=new StudentUpcomingSession();
        panel_past_session=new StudentPastSession();
        tab=new JTabbedPane();
        tab.addTab("Upcoming Sessions",panel_upcoming_session);
        //tab.addTab("Past Sessions",panel_past_session);
        this.design();
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {                
                if(JOptionPane.showConfirmDialog(Student.this,"Do you want to logout??","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    try
                    {
                         ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                         out.writeObject(Resource.RequestCodes.LOGOUT);
                         System.exit(0);
                    }
                    catch(Exception ex)
                    {                            
                        
                    }
                }
            }
        });        
    }
    
    void design()
    {
        setPosition(tab,5,50,size.width,size.height);
    }
    
    void setPosition(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
}
