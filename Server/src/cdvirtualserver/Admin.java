/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Chayan-Dhaddha
 */
public class Admin extends JFrame implements ActionListener{

    JTabbedPane tab;
    AdminMentorReg panel_mentor_reg;
    AdminPastSession panel_past_session;
    AdminUpcomingSession panel_upcoming_session;
    AdminServer panel_server;
    Dimension size;
    JButton btn_login;
    Boolean flag;
    
    public Admin(){
        Toolkit tool=Toolkit.getDefaultToolkit();
        size=tool.getScreenSize();
        //final int WIDTH=800; 
        //final int HEIGHT=700;
        this.setVisible(true);
        this.setBounds(0,0,size.width,size.height);
        this.setTitle("Server");
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        btn_login=new JButton("SignIn ");
        panel_server=new AdminServer();
        panel_mentor_reg=new AdminMentorReg();
        panel_past_session=new AdminPastSession();
        panel_upcoming_session=new AdminUpcomingSession();
        tab=new JTabbedPane();
        tab.addTab("Server",panel_server);
//        tab.addTab("Registration",panel_mentor_reg);
//        tab.addTab("Past Sessions",panel_past_session);
//        tab.addTab("Upcoming Sessions",panel_upcoming_session);
       
        this.design();
        btn_login.addActionListener(this);
        this.addWindowListener(new WindowAdapter(){
                                    public void windowClosing(WindowEvent e) {
                                      
                                        if(JOptionPane.showConfirmDialog(Admin.this,"Do you want to exit??","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                                            System.exit(0);
                                        }
                                    }
                               });
    }    
    
    void design(){
        setPosition(btn_login,size.width-150,20,100,20);
        setPosition(tab,5,50,size.width,size.height);
    } 
    
    void setPosition(Component C,int x,int y,int w,int h){
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btn_login){
            LoginWindow login=new LoginWindow();
        }
    }
    
}
