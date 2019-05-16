/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualclient;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import java.util.Vector;
/**
 *
 * @author Chayan-Dhaddha
 */
public class StudentUpcomingSession extends JPanel implements ActionListener{
      
    Vector<String> HEAD;
    Vector DATA;
    JTable  table;
    JScrollPane jsp;
    JButton btn_enroll;
    JLabel lbl_session_info;
    
    public StudentUpcomingSession(){
        this.setLayout(null);
        HEAD=new Vector<String>();
        HEAD.add("INITIATED BY");
        HEAD.add("TOPIC");
        HEAD.add("DATE");
        HEAD.add("TIME");
        HEAD.add("DURATION");
        DATA=new Vector();
        table=new JTable(DATA,HEAD);
        
        jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        btn_enroll=new JButton("Enroll Now");
        lbl_session_info=new JLabel("hgdjhsdkjshkhdskjhksjhfkjhfk");
        lbl_session_info.setVisible(false);
        btn_enroll.setVisible(false);
        btn_enroll.addActionListener(this);
        this.design();
        
    }

    void design(){
        this.setPosition(lbl_session_info,50,20,450,50);
        this.setPosition(btn_enroll,500,30,100,30);
        this.setPosition(jsp,50,80,1200,550);    
    }
    
    void setPosition(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }    
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btn_enroll)
        {
            try
            {
                ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                out.writeObject(Resource.RequestCodes.SESSION_ENROLL);
            }
            catch(Exception ex)
            {
                
            }
                     
        }
    }
}

