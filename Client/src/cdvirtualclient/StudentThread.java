package cdvirtualclient;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Vector;
import javax.imageio.ImageIO;

public class StudentThread extends Thread
{
    Student student;
    
    public StudentThread(Student student)
    {
        this.student=student;
        try
        {
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception occured" + ex);
        }
        this.start();
    }
    
    public void run()
    {
        try
        {
            while(true)
            {
                 ObjectInputStream in=new ObjectInputStream(CommResource.client.getInputStream());
                 Resource.ResponseCodes resp=(Resource.ResponseCodes)in.readObject();
                 System.out.println("resp=="+resp);
                 
                 if(resp==Resource.ResponseCodes.STREAM){
                     //Vector<BufferedImage> image=(Vector<BufferedImage>)in.readObject();
                     byte[] image=(byte[])in.readObject();
                     // System.out.println("Received  image...");
                      InputStream ins = new ByteArrayInputStream(image);
                      BufferedImage bImageFromConvert = ImageIO.read(ins);
                     this.student.session_held.player.play(bImageFromConvert);
                     
                     
                 }
                 if(resp==Resource.ResponseCodes.INIT_STUDENT_RESPONSE)
                 {
                     System.out.println("init_student_response1");
                     Vector<Vector>main=(Vector<Vector>)in.readObject();
                     
                     //this.student.panel_upcoming_session.DATA.clear();
                     for(int i=0;i<main.size();i++)
                     {
                         this.student.panel_upcoming_session.DATA.add(main.elementAt(i));
                     }
                     
                     this.student.panel_upcoming_session.table.repaint();
                     System.out.println("init_student_response1");
                     ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                     out.writeObject(Resource.RequestCodes.IS_SESSION_ACTIVE);    
                 }
                 
                 if(resp==Resource.ResponseCodes.NEW_SESSION)
                 {
                     Vector<Vector>main=(Vector<Vector>)in.readObject();
                     //this.mentor.panel_session_initiate.DATA.clear();
                     for(int i=0;i<main.size();i++)
                     {
                         this.student.panel_upcoming_session.DATA.add(main.elementAt(i));
                     }
                     this.student.panel_upcoming_session.table.repaint();
                 }
                 else if(resp==Resource.ResponseCodes.FILTER_SUCCESS)
                 {
                     Vector<Vector>main=(Vector<Vector>)in.readObject();
                     this.student.panel_past_session.DATA.clear();
                     for(int i=0;i<main.size();i++){
                         this.student.panel_past_session.DATA.add(main.elementAt(i));
                     }
                     this.student.panel_past_session.table.repaint();
                     System.out.println("filter success");
                     
                 }
                 
                 
                 
                 else if(resp==Resource.ResponseCodes.LIVE_SESSION_HEADLINE)
                 {
                      System.out.println("1 student thread");
                      String info=in.readObject().toString();
                      this.student.panel_upcoming_session.lbl_session_info.setText(info);
                      this.student.panel_upcoming_session.btn_enroll.setVisible(true);
                      this.student.panel_upcoming_session.lbl_session_info.setVisible(true);
                      this.student.panel_upcoming_session.repaint();
                      System.out.println("2 student thread");
                 }
                 else if(resp==Resource.ResponseCodes.ADD_IN_FORM)
                 {
                     String query=in.readObject().toString();
                     this.student.session_held.area_form.append(query+"\n\n");
                     System.out.println("in query_ask");
                 }
                 else if(resp==Resource.ResponseCodes.LIVE_SESSION_INFO)
                 {
                     String topic=in.readObject().toString();
                     String desc=in.readObject().toString();
                     Vector<String> form_query=(Vector<String>)in.readObject();
                     String count=in.readObject().toString();
                     CommResource.student_log.session_held = new StudentSessionHeld();
                     CommResource.student_log.tab.addTab("Session Held",CommResource.student_log.session_held);
                     CommResource.student_log.tab.setSelectedIndex(1);
                     CommResource.student_log.tab.setVisible(true);
           
                     CommResource.student_log.session_held.txt_topic.setText(topic);
                     CommResource.student_log.session_held.area_desc.setText(desc);
                     for(int i=0;i<form_query.size();i++){
                         CommResource.student_log.session_held.area_form.append(form_query.elementAt(i)+"\n\n");
                     }
                     CommResource.student_log.session_held.lbl_count.setText(count);
                     CommResource.student_log.session_held.repaint();
                 }  
                 else if(resp==Resource.ResponseCodes.INC_COUNT)
                 {
                     String count=in.readObject().toString();
                     CommResource.student_log.session_held.lbl_count.setText(count);
                     CommResource.student_log.session_held.lbl_count.repaint();
                 }
                 else if(resp==Resource.ResponseCodes.NOT_ACTIVE)
                 {
                     
                 }
                 
                 else if(resp==Resource.ResponseCodes.CONFIRM_TERMINATE)
                 {
                     CommResource.student_log.session_held.player.setEnabled(false);
                 }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception occured" + ex);
            ex.printStackTrace();
        }
    }
}
