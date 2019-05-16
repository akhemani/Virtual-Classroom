package cdvirtualclient;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class Mentor extends JFrame implements ActionListener
{
    //JLabel lbl_mentor_name,lbl_id;
    JButton btn_logout;
    JTabbedPane tab;
    MentorSessionInitiateList panel_session_initiate;
    MentorPastSession panel_past_session;
    MentorSessionHeld session_held;
    Dimension size;
    
    public Mentor()
    {    
        Toolkit tool=Toolkit.getDefaultToolkit();
        size=tool.getScreenSize();
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //final int WIDTH=800; 
        //final int HEIGHT=700;
        this.setVisible(true);
        this.setBounds(0,0,size.width,size.height);
        this.setTitle("Mentor");
        this.setResizable(false);
        this.setLayout(null);
        panel_session_initiate=new MentorSessionInitiateList();
        panel_past_session=new MentorPastSession();
        //session_held=new SessionHeld();
        tab=new JTabbedPane();
        tab.addTab("Initiate Session",panel_session_initiate);
        //tab.addTab("Past Sessions",panel_past_session);
        //tab.addTab("Session Held",session_held);
        this.design();
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {                        
                if(JOptionPane.showConfirmDialog(Mentor.this,"Do you want to logout??","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    try
                    {
                        ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                        out.writeObject(Resource.RequestCodes.LOGOUT);
                        System.exit(0);
                        //JOptionPane.showMessageDialog(null,"Successfully logout...." );
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
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btn_logout)
        {
                                    
        }   
    }
}

