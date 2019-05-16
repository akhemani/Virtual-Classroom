package cdvirtualclient;

import Resource.RequestCodes;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;

public class LoginWindow extends JFrame implements ActionListener
{
    JLabel lbl_id,lbl_pwd,lbl_head;
    // CheckBoxGroup cbg;
    JCheckBox box_mentor,box_student;
    JButton btn_login,btn_register;
    //JRadioButton box_admin,box_mentor,box_student;
    JTextField txt_id;
    JPasswordField txt_pwd;
    ButtonGroup grp;
    String response,role; 
    
    public LoginWindow()
    {   
        this.setLayout(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        Toolkit tool=Toolkit.getDefaultToolkit();
        Dimension size=tool.getScreenSize();
        final int WIDTH=400;
        final int HEIGHT=300;
        this.setVisible(true);
        this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
        this.setTitle("LOGIN_WINDOW");
        this.setResizable(false);
        btn_register=new JButton("Student's Registration");
        lbl_head=new JLabel("LOGIN AS->");
       
        this.box_mentor=new JCheckBox("Mentor");
        this.box_student=new JCheckBox("Student");
        this.grp=new ButtonGroup();
        this.grp.add(box_mentor);
        this.grp.add(box_student);
        this.box_mentor.setActionCommand("mentor");
        this.box_student.setActionCommand("student");
        lbl_id=new JLabel("LOGIN_ID :");
        lbl_pwd=new JLabel("PASSWORD :");
        txt_id=new JTextField(40);
        txt_pwd=new JPasswordField();
        btn_login=new JButton("Login");
        this.btn_register.addActionListener(this);
        this.btn_login.addActionListener(this);
        this.design();
        this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) 
            {                          
                if(JOptionPane.showConfirmDialog(LoginWindow.this,"Do you want to exit??","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
    }
    
    void design()
    {   
        setPosition(btn_register,200,20,180,20);
        setPosition(lbl_head,40,60,100,20);
        setPosition(box_mentor,120,80,80,20);
        setPosition(box_student,200,80,80,20);
        
        setPosition(lbl_id,40,135,100,25);
        setPosition(txt_id,140,135,200,25);
        setPosition(lbl_pwd,40,175,100,25);
        setPosition(txt_pwd,140,175,200,25);
        setPosition(btn_login,190,220,100,20);
    }
    
    void setPosition(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    
    public void actionPerformed(ActionEvent ae) 
    {
       //validation check
       if(ae.getSource()==btn_register)
       {
           Register log=new Register();
           this.dispose();
       }
       else if(ae.getSource()==btn_login)
       {
           try
           {
               ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
               out.writeObject(RequestCodes.LOGIN_DETAIL);
               role=this.grp.getSelection().getActionCommand();
               out.writeObject(this.role);
               out.writeObject(this.txt_id.getText());
               out.writeObject(new String(this.txt_pwd.getPassword()));
               
               ObjectInputStream in=new ObjectInputStream(CommResource.client.getInputStream());
               response=in.readObject().toString();
               if(response.equals("Success"))
               {
                      if(role.equals("mentor"))
                      {
                          CommResource.mentor_log=new Mentor();
                          new MentorThread(CommResource.mentor_log);
                          ObjectOutputStream os=new ObjectOutputStream(CommResource.client.getOutputStream());
                          os.writeObject(RequestCodes.INIT_UPCOMING_SESSION_MENTOR);
                          //os.writeObject(RequestCodes.IS_SESSION_ACTIVE);
                          this.dispose();
                      }
                      else
                      {
                          CommResource.student_log=new Student();
                          new StudentThread(CommResource.student_log);
                          ObjectOutputStream os=new ObjectOutputStream(CommResource.client.getOutputStream());
                          os.writeObject(RequestCodes.INIT_UPCOMING_SESSION_STUDENT);
                          //os.writeObject(RequestCodes.IS_SESSION_ACTIVE);
                          this.dispose();
                      }
               }
               else
               {
                   JOptionPane.showMessageDialog(this, "Invalid LoginId/Password","Login",JOptionPane.ERROR_MESSAGE);
               }
           }
           catch(Exception ex)
           {
               
           }
       }
       
   }      

}
