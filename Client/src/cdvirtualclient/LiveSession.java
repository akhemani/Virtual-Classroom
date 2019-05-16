package cdvirtualclient;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LiveSession extends JFrame
{
    JLabel lbl_title,lbl_description;
    JButton btn_send;
    JTextArea txtarea_descp;
    JTextField txtf_title;
    
    public LiveSession()
    {
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        lbl_title = new JLabel("Title");
        lbl_description = new JLabel("Description");
        txtarea_descp = new JTextArea();
        txtf_title = new JTextField();
        btn_send = new JButton("Send");
        
        this.design();
        
    }
    
    void design()
    {
        setComponent(lbl_title,30,30,30,30);
    }
    
    void setComponent(Component cmp,int x,int y,int width,int height)
    {
        this.add(cmp);
        cmp.setBounds(x, y, width, height);
    }
}
