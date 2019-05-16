/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;
import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;
import javax.swing.*;
/**
 *
 * @author Chayan-Dhaddha
 */
public class LoginWindow extends JFrame implements ActionListener{
      JLabel lbl_id,lbl_pwd;
      JTextField txt_id;
      JPasswordField txt_pwd;
      JButton btn_login;
      Dimension size;
      public Boolean flag;
      String admin_id="abc@gmail.com";
      String admin_pwd="abc12345";
      
    public LoginWindow(){
        Toolkit tool=Toolkit.getDefaultToolkit();
        size=tool.getScreenSize();
        final int WIDTH=400; 
        final int HEIGHT=200;
        this.setVisible(true);
        this.setBounds(size.width/2-WIDTH/2,size.height/2-HEIGHT/2,WIDTH,HEIGHT);
        this.setTitle("Login Window");
        this.setResizable(false);
        this.setLayout(null);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         flag=false;
         lbl_id=new JLabel("Login_id:");
         lbl_pwd=new JLabel("Password:");
         txt_id=new JTextField();
         txt_pwd=new JPasswordField();
         btn_login=new JButton("Login");
         this.design();
         btn_login.addActionListener(this);
    }
    
    void design(){
        setPosition(lbl_id,40,40,100,25);
        setPosition(txt_id,140,40,150,25);
        setPosition(lbl_pwd,40,80,100,25);
        setPosition(txt_pwd,140,80,150,25);
        setPosition(btn_login,90,130,100,20);
    } 
    
    void setPosition(Component c,int x,int y,int w,int h){
        this.add(c);
        c.setBounds(x,y,w,h);
    }
    
    public void actionPerformed(ActionEvent ae){
        String id=txt_id.getText();
        String pwd=new String(txt_pwd.getPassword());
        System.out.println("login id: "+id+" pwd: "+pwd);
        if(id.equals("") || pwd.equals("")){
            JOptionPane.showMessageDialog(this,"Fill all entries","Empty TextField",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(id.equals(admin_id) && pwd.equals(admin_pwd) ){
                  this.dispose();
                  CommResource.admin.tab.addTab("Mentor Registration",CommResource.admin.panel_mentor_reg);
                  CommResource.admin.tab.addTab("Upcoming Session",CommResource.admin.panel_upcoming_session);
                  //CommResource.admin.tab.addTab("Past Session",CommResource.admin.panel_past_session);
                  CommResource.admin.btn_login.setVisible(false);
                  CommResource.admin.addWindowListener(new WindowAdapter(){
                                    public void windowClosing(WindowEvent e) {
                                      
                                        if(JOptionPane.showConfirmDialog(CommResource.admin,"Do you want to logout??","Confirmation",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                                           
            CommResource.admin.dispose();
                                            
                                        }
                                        
                                    }
                               });
    
            }
            else{
                 JOptionPane.showMessageDialog(this,"Invalid login_id/password","Login Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
}
