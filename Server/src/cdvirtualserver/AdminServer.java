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
public class AdminServer extends JPanel implements ActionListener{
    JTextArea txt_area;
    //JButton btn_stop;
    JScrollPane jsp;
   
    public AdminServer() 
    {
        this.setLayout(null);
        txt_area=new JTextArea();
        jsp=new JScrollPane(txt_area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        //btn_stop=new JButton("STOP");
        this.design();
        //this.btn_stop.addActionListener(this);
    }

    void design(){
        setPosition(jsp,65,70,900,550);
        //setPosition(btn_stop,1050,350,150,30);
    }
    
    void setPosition(Component C,int x,int y,int w,int h){
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    
    public void actionPerformed(ActionEvent ae){
        //if(ae.getSource()==btn_stop)
            //System.exit(1);
    }
}
