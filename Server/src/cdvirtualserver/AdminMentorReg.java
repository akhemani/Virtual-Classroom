/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import java.util.Vector;
import javax.swing.*;
/**
 *
 * @author Chayan-Dhaddha
 */
public class AdminMentorReg extends JPanel implements ActionListener{
     
    JLabel lbl_id,lbl_pwd,lbl_cpwd,lbl_name,lbl_qual,lbl_contact,lbl_dept,lbl_desig;
    JTextField txt_id,txt_name,txt_qual,txt_contact,txt_dept,txt_desig;
    JPasswordField  txt_pwd,txt_cpwd;
    JButton  btn_register;
    Vector<String> HEAD;
    Vector<Vector> DATA,main,update;
    JTable table;
    JScrollPane jsp;
    String login_id,pwd,cpwd,name,qual,contact,dept,desig,query;
    
    public AdminMentorReg(){
         this.setLayout(null);
         lbl_id=new JLabel("Login_id:");
         lbl_pwd=new JLabel("Password:");
         lbl_cpwd=new JLabel("Confirm Password:");
         lbl_name=new JLabel("Name:");
         lbl_qual=new JLabel("Qualification:");
         lbl_dept=new JLabel("Department:");
         lbl_desig=new JLabel("Designation:");
         lbl_contact=new JLabel("Contact:");
         txt_id=new JTextField();
         txt_pwd=new JPasswordField();
         txt_cpwd=new JPasswordField();
         txt_name=new JTextField();
         txt_qual=new JTextField();
         txt_contact=new JTextField();
         txt_dept=new JTextField();
         txt_desig=new JTextField();
         btn_register=new JButton("REGISTER");
         HEAD=new Vector<String>();
         HEAD.add("NAME");
         HEAD.add("LOGIN-ID");
         HEAD.add("QUALIFICATION");
         HEAD.add("DEPT");
         HEAD.add("CONTACT");
         DATA=new Vector();
         query="select name,login_id,qualification,department,contact from mentor";
         try
         {
             main=ConnectionFactory.getInstance().getData(query);
             for(int i=0;i<main.size();i++)
             {
                 this.DATA.add(main.elementAt(i));
             }
         }
         catch(Exception ex)
         {
             
         }
         table=new JTable(DATA,HEAD);
         jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
         this.design();
         btn_register.addActionListener(this);
     }
    
    void design()
    {
        this.setPosition(lbl_id, 70, 80, 100, 25);
        this.setPosition(txt_id, 170, 80, 200, 25);
        this.setPosition(lbl_pwd, 70, 120, 100, 25);
        this.setPosition(txt_pwd, 170, 120, 200, 25);
        this.setPosition(lbl_cpwd, 70, 160, 100, 25);
        this.setPosition(txt_cpwd, 170,160, 200, 25);
        this.setPosition(lbl_name, 70, 200, 100, 25);
        this.setPosition(txt_name, 170,200, 200, 25);
        this.setPosition(lbl_qual, 70, 240, 100, 25);
        this.setPosition(txt_qual, 170, 240, 200, 25);
        this.setPosition(lbl_dept, 70, 280, 100, 25);
        this.setPosition(txt_dept, 170, 280, 200, 25);
        this.setPosition(lbl_desig, 70, 320, 100, 25);
        this.setPosition(txt_desig, 170, 320, 200, 25);
        this.setPosition(lbl_contact, 70, 360, 100, 25);
        this.setPosition(txt_contact, 170, 360, 200, 25);
        this.setPosition(btn_register,220,420,100,25);
        this.setPosition(jsp, 450,60,830,590);
         
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
                if(pwd.equals(cpwd)){
                try
                {
                           
                           query="insert into mentor set "+
                                  "login_id='"+this.login_id+"',"+
                                  "password='"+this.pwd+"',"+
                                  "name='"+this.name+"',"+
                                  "qualification='"+this.qual+"',"+
                                  "designation='"+this.desig+"',"+
                                  "department='"+this.dept+"',"+
                                  "contact='"+this.contact+"'";
                          
                           int n=ConnectionFactory.getInstance().setData(query);
                           
                             if(n>0){
                                   
                                   JOptionPane.showMessageDialog(this, "Successfully Registered ","Confirmation",JOptionPane.INFORMATION_MESSAGE);
                                   this.set_txt_null();
                                   query="select name,login_id,qualification,department,contact from mentor where id=(select max(id) from mentor)";
                                   main=ConnectionFactory.getInstance().getData(query);
                                   for(int i=0;i<main.size();i++){
                                       this.DATA.add(main.elementAt(i));
                                   }
                                   this.table.repaint();
                                  
                               }
                               else{
                                   JOptionPane.showMessageDialog(this, "Unsuccessfull","Error",JOptionPane.ERROR_MESSAGE);
                               }
                         }catch(Exception ex){
                               JOptionPane.showMessageDialog(this, "Exception ocurred "+ex,"Error",JOptionPane.ERROR_MESSAGE);
                         }         
                    }
                    else{
                        JOptionPane.showMessageDialog(this, "Conf. password doesn't match password ","password error",JOptionPane.ERROR_MESSAGE);
                    }
              }         
        }
    } 
    
    boolean validation(){
                this.login_id=txt_id.getText();
                this.pwd=new String(this.txt_pwd.getPassword());
                this.cpwd=new String(this.txt_cpwd.getPassword());
                this.name=txt_name.getText();
                this.qual=txt_qual.getText();
                this.desig=txt_desig.getText();
                this.dept=txt_dept.getText();
                this.contact=txt_contact.getText();
                if(this.login_id.equals(""))
                  return false;
                if(this.pwd.equals(""))
                  return false;
                if(this.cpwd.equals(""))
                  return false;
                if(this.name.equals(""))
                  return false;
                if(this.qual.equals(""))
                  return false;
                if(this.dept.equals(""))
                  return false;
                if(this.desig.equals(""))
                  return false;
                if(this.contact.equals(""))
                  return false;
            
            return true;
    }
    
    void set_txt_null(){
        txt_id.setText("");
        txt_pwd.setText("");
        txt_cpwd.setText("");
        txt_name.setText("");
        txt_qual.setText("");
        txt_desig.setText("");
        txt_dept.setText("");
        txt_contact.setText("");
    }
}
