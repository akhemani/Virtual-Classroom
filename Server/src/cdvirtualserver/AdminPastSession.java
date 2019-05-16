/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;

import Resource.RequestCodes;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Chayan-Dhaddha
 */
public class AdminPastSession extends JPanel implements ActionListener{
    Vector<String> HEAD;
    Vector DATA;
    JTable  table;
    JScrollPane jsp;
    JLabel lbl_filter,lbl_date_from,lbl_date_to;
    JRadioButton rbtn_date,rbtn_session,rbtn_search;
    ButtonGroup grp;
    JButton btn_fetch;
    JTextField txt_session,txt_search;
    Choice drop_session,drop_date_from_day,drop_date_from_month,drop_date_from_year,drop_date_to_day,drop_date_to_month,drop_date_to_year;
    int flag;
    
    public AdminPastSession(){
       this.setLayout(null);
        
        lbl_filter=new JLabel("FILTER ::");
        rbtn_date=new JRadioButton("Date:",true);
        rbtn_session=new JRadioButton("Session by:");
        rbtn_search=new JRadioButton("Search by:");
        rbtn_date.setActionCommand("date");
        rbtn_session.setActionCommand("session");
        rbtn_search.setActionCommand("search");
        grp=new ButtonGroup();
        grp.add(rbtn_date);
        grp.add(rbtn_search);
        grp.add(rbtn_session);
        btn_fetch=new JButton("FETCH");
        lbl_date_from=new JLabel("From: ");
        lbl_date_to=new JLabel("To: ");         
        drop_date_from_day=new Choice();
        drop_date_from_month=new Choice();
        drop_date_from_year=new Choice();
        drop_date_to_day=new Choice();
        drop_date_to_month=new Choice();
        drop_date_to_year=new Choice();
        for(int i=1;i<=31;i++){
            drop_date_from_day.add(""+i);
            drop_date_to_day.add(""+i);
        }
        for(int i=1;i<=12;i++){
            drop_date_from_month.add(""+i);
            drop_date_to_month.add(""+i);
        }
        for(int i=2012;i<=2016;i++){
            drop_date_from_year.add(""+i);
            drop_date_to_year.add(""+i);
        }
        
        txt_session=new JTextField();
        txt_search=new JTextField();
        
        txt_session.setVisible(false);
        txt_search.setVisible(false);
        
         HEAD=new Vector<String>();
        
        HEAD.add("INITIATED_BY");
        HEAD.add("TOPIC");
        HEAD.add("DATE");
        HEAD.add("TIME");
        HEAD.add("DURATION");
        DATA=new Vector();
        table=new JTable(DATA,HEAD);
        jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.design();
        //grp.getSelection().addActionListener(this);
        rbtn_date.addActionListener(this);
        rbtn_session.addActionListener(this);
        rbtn_search.addActionListener(this);
        btn_fetch.addActionListener(this);    
     
    }
    
    void design(){
        this.setPosition(lbl_filter,80,20,100,30);
        this.setPosition(rbtn_date,220,20,100,30);
        this.setPosition(lbl_date_from,350,20,50,30);
        this.setPosition(drop_date_from_day,400,20,40,30);
        this.setPosition(drop_date_from_month,440,20,40,30);
        this.setPosition(drop_date_from_year,480,20,60,30);
        this.setPosition(lbl_date_to,570,20,30,30);
        this.setPosition(drop_date_to_day,600,20,40,30);
        this.setPosition(drop_date_to_month,640,20,40,30);
        this.setPosition(drop_date_to_year,680,20,60,30);
        this.setPosition(rbtn_session,220,60,100,30);
        this.setPosition(txt_session,350,60,220,30);
        this.setPosition(rbtn_search,220,100,100,30);
        this.setPosition(txt_search,350,100,220,30);
        this.setPosition(btn_fetch,800,100,100,30);
        this.setPosition(jsp,50,180,1200,460);
    }
    
     void setPosition(Component C,int x,int y,int w,int h){
        this.add(C);
        C.setBounds(x,y,w,h);
    }
     
     public void actionPerformed(ActionEvent ae){
         Vector<Vector>list = null;
       if(ae.getSource()==btn_fetch){
            try
            {
                if(flag==1)
                {
                   String date_from=drop_date_from_year.getSelectedItem()+"/"+drop_date_from_month.getSelectedItem()+drop_date_from_day.getSelectedItem();
                   String date_to=drop_date_to_year.getSelectedItem()+"/"+drop_date_to_month.getSelectedItem()+drop_date_to_day.getSelectedItem();
                   String query="select initiated_by,topic,session_date,session_time,duration from session where session_date>='"+date_from+"' and session_date<='"+date_to+"'";
                   list = ConnectionFactory.getInstance().getData(query);
                }
                else if(flag==2)
                {
                     String session=txt_session.getText();
                     String query="select initiated_by,topic,session_date,session_time,duration from session where topic='"+session+"'";
                     list=ConnectionFactory.getInstance().getData(query);
                     
                }
                else
                {
                   String search=txt_search.getText();
                   String query="select initiated_by,topic,session_date,session_time,duration from session where topic='"+search+"'";
                   list=ConnectionFactory.getInstance().getData(query);
                }
                CommResource.admin.panel_past_session.DATA.clear();
                     for(int i=0;i<list.size();i++)
                     {
                         CommResource.admin.panel_past_session.DATA.add(list.elementAt(i));
                     }
                     CommResource.admin.panel_past_session.table.repaint();
            }
            catch(Exception ex)
            {
                
            }
            
        }
         else if(ae.getSource()==rbtn_date){
             lbl_date_from.setVisible(true);
             lbl_date_to.setVisible(true);
             drop_date_from_day.setVisible(true);
             drop_date_from_month.setVisible(true);
             drop_date_from_year.setVisible(true);
             drop_date_to_day.setVisible(true);
             drop_date_to_month.setVisible(true);
             drop_date_to_year.setVisible(true);
             
             txt_session.setVisible(false);
             txt_search.setVisible(false);    
             flag=1;
        }
        else if(ae.getSource()==rbtn_session){
             lbl_date_from.setVisible(false);
             lbl_date_to.setVisible(false);
             drop_date_from_day.setVisible(false);
             drop_date_from_month.setVisible(false);
             drop_date_from_year.setVisible(false);
             drop_date_to_day.setVisible(false);
             drop_date_to_month.setVisible(false);
             drop_date_to_year.setVisible(false);
             txt_session.setVisible(true);
             txt_search.setVisible(false);
             flag=2;
        }
        else if(ae.getSource()==rbtn_search){
             lbl_date_from.setVisible(false);
             lbl_date_to.setVisible(false);
             drop_date_from_day.setVisible(false);
             drop_date_from_month.setVisible(false);
             drop_date_from_year.setVisible(false);
             drop_date_to_day.setVisible(false);
             drop_date_to_month.setVisible(false);
             drop_date_to_year.setVisible(false);
             txt_session.setVisible(false);
             txt_search.setVisible(true);
             flag=3;
        }
    }
}
