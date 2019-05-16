package cdvirtualclient;

import Resource.RequestCodes;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class Register extends JFrame implements ActionListener
{
    JLabel lbl_id,lbl_pwd,lbl_cpwd,lbl_name,lbl_branch,lbl_semester,lbl_contact;
    JTextField txt_id,txt_name,txt_branch,txt_semester,txt_contact;
    JPasswordField txt_pwd,txt_cpwd;
    JButton btn_register;
    String pwd,cpwd,login_id,name,branch,semester,contact,response;
    
    public Register()
    {
        this.setLayout(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Toolkit tool=Toolkit.getDefaultToolkit();
        Dimension size=tool.getScreenSize();
        final int WIDTH=500;
        final int HEIGHT=400;
        this.setVisible(true);
        this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
        this.setTitle("Registration");
        this.setResizable(false);
        
        lbl_id=new JLabel("LOGIN_ID :"); 
        lbl_pwd=new JLabel("PASSWORD :");
        lbl_cpwd=new JLabel("CONF. PASSWORD :");
        lbl_name=new JLabel("NAME :");
        lbl_branch=new JLabel("BRANCH :");
        lbl_semester=new JLabel("SEMESTER :");
        lbl_contact=new JLabel("CONTACT :");
        txt_id=new JTextField(40);
        txt_pwd=new JPasswordField();
        txt_cpwd=new JPasswordField();
        txt_name=new JTextField(40);
        txt_branch=new JTextField(40);
        txt_semester=new JTextField(40);
        txt_contact=new JTextField(40);
        btn_register=new JButton("Register");
        this.design();
        this.btn_register.addActionListener(this);
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {                          
                if(JOptionPane.showConfirmDialog(Register.this,"Do you want to exit??","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }
    
    void design()
    {
        setPosition(lbl_id,100,60,120,20);
        setPosition(txt_id,220,60,150,20);
        setPosition(lbl_pwd,100,95,120,20);
        setPosition(txt_pwd,220,95,150,20);
        setPosition(lbl_cpwd,100,130,120,20);
        setPosition(txt_cpwd,220,130,150,20);
        setPosition(lbl_name,100,165,120,20);
        setPosition(txt_name,220,165,150,20);
        setPosition(lbl_branch,100,200,120,20);
        setPosition(txt_branch,220,200,150,20);
        setPosition(lbl_semester,100,235,120,20);
        setPosition(txt_semester,220,235,150,20);
        setPosition(lbl_contact,100,270,120,20);
        setPosition(txt_contact,220,270,150,20);
        setPosition(btn_register,170,320,100,20);
    }
    
    void setPosition(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==btn_register)
        {
              if(!validation())
              {
                  JOptionPane.showMessageDialog(this,"It is mandatory to fill all entries","Registration Error",JOptionPane.ERROR_MESSAGE);
              }
              else
              {
                    if(pwd.equals(cpwd))
                    {
                         try
                         {
                               ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                               out.writeObject(Resource.RequestCodes.STUDENT_REGISTER);
                               out.writeObject(this.login_id);
                               out.writeObject(this.pwd);
                               out.writeObject(this.name);
                               out.writeObject(this.branch);
                               out.writeObject(this.semester);
                               out.writeObject(this.contact);
                               
                               ObjectInputStream in=new ObjectInputStream(CommResource.client.getInputStream());
                               response=in.readObject().toString();
                               if(response.equals("Success"))
                               {
                                   JOptionPane.showMessageDialog(this, "Successfully Registered ","Confirmation",JOptionPane.INFORMATION_MESSAGE);
                                   LoginWindow log=new LoginWindow();
                                   this.dispose();
                               }
                               else
                               {
                                   JOptionPane.showMessageDialog(this, "Unsuccessfull  ","Error",JOptionPane.ERROR_MESSAGE);
                               }
                         }
                         catch(Exception ex)
                         {
                               JOptionPane.showMessageDialog(this, "Unsuccessfull"+ex,"Error",JOptionPane.ERROR_MESSAGE);
                         }         
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(this, "Conf. password doesn't match password ","password error",JOptionPane.ERROR_MESSAGE);
                    }
              } 
               
       }
    }   
    
    boolean validation()
    {
                login_id=txt_id.getText();
                pwd=new String(this.txt_pwd.getPassword());
                cpwd=new String(this.txt_cpwd.getPassword());
                name=txt_name.getText();
                branch=txt_branch.getText();
                semester=txt_semester.getText();
                contact=txt_contact.getText();
                if(login_id.equals(""))
                 return false;
                if(pwd.equals(""))
                 return false;
                if(cpwd.equals(""))
                 return false;
                if(name.equals(""))
                 return false;
                if(branch.equals(""))
                 return false;
                if(semester.equals(""))
                 return false;
                if(contact.equals(""))
                 return false;
            
            return true;
    }
}    