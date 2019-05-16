package cdvirtualclient;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

public class MentorSessionInitiateList extends JPanel implements ActionListener
{
    JLabel lbl_date,lbl_topic,lbl_desc,lbl_rate,lbl_time,lbl_duration,lbl_upcoming_session,lbl_session_info;
    JTextField txt_topic,txt_time,txt_duration;
    JTextArea txt_desc;
    JButton btn_save,btn_enroll;
    JTable table;
    JButton btn_table_delete,btn_table_edit,btn_table_save,btn_table_showTab;    
    Vector<String> HEAD;
    Vector DATA;
    JScrollPane jsp,jsp_desc;
    String initiated_by,initiated_on,date,topic,desc,time,duration,date_day,date_month,date_year;
    Choice drop_date_day,drop_date_month,drop_date_year;
    
    public MentorSessionInitiateList()
    {
        this.setLayout(null);
        lbl_topic=new JLabel("Topic :");
        lbl_date=new JLabel("Date :");
        lbl_time=new JLabel("Time :");
        lbl_duration=new JLabel("Duration :");
        lbl_desc=new JLabel("Description :");
        lbl_upcoming_session=new JLabel("UPCOMING SESSIONS->");
        txt_topic=new JTextField(40);
        drop_date_day=new Choice();
        drop_date_month=new Choice();
        drop_date_year=new Choice();
        txt_time=new JTextField(40);
        txt_duration=new JTextField(40);
        txt_desc=new JTextArea();
        jsp_desc=new JScrollPane(txt_desc,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        btn_save=new JButton("Save");
        
        HEAD=new Vector<String>();
        HEAD.add("INITIATED BY");
        HEAD.add("TOPIC");
        HEAD.add("DATE");
        HEAD.add("TIME");
        HEAD.add("DURATION");
        DATA=new Vector();
        table=new JTable(DATA,HEAD);
        jsp=new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        btn_table_delete=new JButton("Delete");
        btn_table_edit=new JButton("Edit");
        btn_table_save=new JButton("Save");
        btn_table_showTab=new JButton("Go Live");
        btn_enroll=new JButton("Enroll Now");
        lbl_session_info=new JLabel("hdjashkjhsdkhkdjhfksjdhfkjdhfkdshfkjdhkfhkfhdkjhfdkhfkjdhfkjdhfkjhdkj");
        lbl_session_info.setVisible(false);
        btn_enroll.setVisible(false);
        for(int i=1;i<=31;i++)
        {
            drop_date_day.add(""+i);
        }
        
        for(int i=1;i<=12;i++)
        {
            drop_date_month.add(""+i);
        }
        
        for(int i=2016;i<=2019;i++)
        {
            drop_date_year.add(""+i);
        }
        
        this.design();
        btn_save.addActionListener(this);
        btn_table_showTab.addActionListener(this);
        btn_enroll.addActionListener(this);
    }
    
    void design()
    {
        setPosition(lbl_topic,100,40,100,25);
        setPosition(txt_topic,200,40,200,25);
        setPosition(lbl_date,500,40,100,25);
        setPosition(drop_date_day,600,40,45,25);
        setPosition(drop_date_month,655,40,45,25);
        setPosition(drop_date_year,710,40,60,25);
        setPosition(lbl_time,100,80,100,25);
        setPosition(txt_time,200,80,200,25); 
        setPosition(lbl_duration,500,80,100,25);
        setPosition(txt_duration,600,80,200,25);
        setPosition(lbl_desc,100,120,100,25);
        setPosition(jsp_desc,200,120,260,85);
        setPosition(btn_save,650,150,100,25);
        setPosition(lbl_upcoming_session,100,200,200,40);
        setPosition(jsp,40,250,1100,400);
        //setPosition(btn_table_delete,1200,270,100,25);
        //setPosition(btn_table_edit,1200,310,100,25);
        //setPosition(btn_table_save,1200,350,100,25);
        setPosition(btn_table_showTab,1200,390,100,25);
        this.setPosition(lbl_session_info,850,30,500,50);
        this.setPosition(btn_enroll,950,90,100,30);
        
    }
    
    void setPosition(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        try
        {
            if(ae.getSource()==btn_save)
            {
                if(this.validation())
                {
                    ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                    out.writeObject(Resource.RequestCodes.SESSION_REGISTER);
                    out.writeObject(topic);
                    out.writeObject(date);
                    out.writeObject(time);
                    out.writeObject(duration);
                    out.writeObject(desc);
                }
            }    
            else if(ae.getSource()==btn_table_showTab)
            {
                if(this.table.getSelectedRow() >= 0){
                    System.out.println("start");
                    Vector sub=(Vector)this.DATA.elementAt(this.table.getSelectedRow());
                    String sessionid=sub.elementAt(5).toString();
                    String date=sub.elementAt(2).toString();
                    String topic=sub.elementAt(1).toString();
                    String desc=sub.elementAt(6).toString();
                    ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                    out.writeObject(Resource.RequestCodes.LIVE_SESSION);
                    out.writeObject(sessionid);
                    out.writeObject(topic);
                    out.writeObject(date);
                    out.writeObject(desc);
                    CommResource.mentor_log.session_held = new MentorSessionHeld();
                    CommResource.mentor_log.tab.addTab("Session Held",CommResource.mentor_log.session_held);
                    CommResource.mentor_log.tab.setSelectedIndex(1);
                    CommResource.mentor_log.tab.setVisible(true);
                    CommResource.mentor_log.session_held.txt_topic.setText(topic);
                    CommResource.mentor_log.session_held.area_desc.setText(desc);
                    System.out.println("end");
                }
                else{
                    JOptionPane.showMessageDialog(lbl_topic, ae, time, HEIGHT);
                }
            }
            else if (ae.getSource()==btn_enroll){
                try{
                    ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                    out.writeObject(Resource.RequestCodes.SESSION_ENROLL);
                    //out.writeObject();
                }catch(Exception ex){
                
                }
                
            }
            
        }
        catch(Exception ex)
        {
             System.out.println("Exception occured "+ex);
        }  
        
        
    }
    
    boolean validation()
    {
                topic=txt_topic.getText();
                date_day=drop_date_day.getSelectedItem();
                date_month=drop_date_month.getSelectedItem();
                date_year=drop_date_year.getSelectedItem();
                time=txt_time.getText();
                duration=txt_duration.getText();
                desc=txt_desc.getText();
                date=date_year+"/"+date_month+"/"+date_day;
                if(topic.equals(""))
                 return false;
                if(time.equals(""))
                 return false;
                if(duration.equals(""))
                 return false;
                if(desc.equals(""))
                 return false;
                
            return true;
        
    }
    
    void set_txt_null()
    {
        txt_topic.setText("");
        txt_time.setText("");
        txt_duration.setText("");
        txt_desc.setText("");
    }    
}
