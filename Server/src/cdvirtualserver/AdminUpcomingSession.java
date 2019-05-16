/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cdvirtualserver;

import java.awt.*;
import java.util.Vector;
import javax.swing.*;

/**
 *
 * @author Chayan-Dhaddha
 */

public class AdminUpcomingSession extends JPanel{
  
    Vector<String> HEAD;
    Vector DATA;
    JTable table;
    JScrollPane  jsp;
    
    public AdminUpcomingSession(){
        this.setLayout(null);
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
        Session session=new Session();
        
    }
    
    void design(){
        
        this.setPosition(jsp,50,80,1200,550);
    }
    
     void setPosition(Component C,int x,int y,int w,int h){
        this.add(C);
        C.setBounds(x,y,w,h);
    }
     
    class Session extends Thread{
        String query;
        public Session(){
            this.start();
        }
        
        public void run(){
               query="select initiated_by,topic,session_date,session_time,duration from session where status=0";
             try{
                 while(true){
                     Vector<Vector> list=ConnectionFactory.getInstance().getData(query);
                     DATA.clear();
                     for(int i=0;i<list.size();i++){
                         DATA.add(list.elementAt(i));
                     }
                     table.repaint();
                }   
             }catch(Exception ex){
                 
             }  
                 
        }
    } 
}
