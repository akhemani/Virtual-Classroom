package cdvirtualclient;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;

public class IP_Entry extends JFrame implements ActionListener 
{
    JLabel lbl_ip;
    JTextField txt_ip;
    JButton btn_connect;
    LoginWindow win;
    
    public IP_Entry()
    { 
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        lbl_ip=new JLabel("Enter server IP :");
        txt_ip=new JTextField("127.0.0.1");
        btn_connect=new JButton("connect");
        this.design();
        this.btn_connect.addActionListener(this);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {                          
                if(JOptionPane.showConfirmDialog(IP_Entry.this,"Do you want to exit??","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }
    
    void design()
    {
        setPosition(lbl_ip,30,30,100,20);
        setPosition(txt_ip,130,30,100,20);
        setPosition(btn_connect,90,70,80,20);
    }
    
    void setPosition(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
       if(this.txt_ip.getText().trim().equals(""))
       {
           JOptionPane.showMessageDialog(null, "No IP entry provided","IP ENTRY",JOptionPane.ERROR_MESSAGE);  
           return; 
       }
       try
       {
           if(ae.getSource()==btn_connect) 
           {
               InetAddress inet=InetAddress.getByName(this.txt_ip.getText().trim());
               CommResource.client=new Socket(inet,2234);
               JOptionPane.showMessageDialog(this,"Successfully Connected...","IP Entry",JOptionPane.INFORMATION_MESSAGE);
               win=new LoginWindow();
               this.dispose();
           }
       }
       catch(Exception ex)
       {
           JOptionPane.showMessageDialog(this,ex,"IP ENTRY",JOptionPane.ERROR_MESSAGE);
       }
   }
}
