package cdvirtualclient;

import java.io.*;
import java.util.Vector;
import javax.swing.JOptionPane;

public class MentorThread extends Thread
{    
    Mentor mentor;
    public MentorThread(Mentor mentor)
    {
        this.mentor=mentor;
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
                 if(resp==Resource.ResponseCodes.INIT_MENTOR_RESPONSE)
                 {
                     Vector<Vector>main=(Vector<Vector>)in.readObject();
                     this.mentor.panel_session_initiate.DATA.clear();
                     for(int i=0;i<main.size();i++)
                     {
                         this.mentor.panel_session_initiate.DATA.add(main.elementAt(i));
                     }
                     this.mentor.panel_session_initiate.table.repaint();
                 }
                 else if(resp==Resource.ResponseCodes.SESSION_REGISTER_SUCCESS)
                 {
                     JOptionPane.showMessageDialog(this.mentor,"Registration Successfull Finally","Success",JOptionPane.INFORMATION_MESSAGE);
                     this.mentor.panel_session_initiate.set_txt_null();
                 }
                 else if(resp==Resource.ResponseCodes.SESSION_REGISTER_FAILED)
                 {
                        JOptionPane.showMessageDialog(this.mentor,"Registration Unsuccessfull","Error",JOptionPane.INFORMATION_MESSAGE);   
                 }
                 else if(resp==Resource.ResponseCodes.NEW_SESSION)
                 {
                     System.out.println("New Session");
                     Vector<Vector>main=(Vector<Vector>)in.readObject();
                     this.mentor.panel_session_initiate.DATA.clear();
                    System.out.println("Total sessions : " + main.size());
                     
                     for(int i=0;i<main.size();i++)
                     {
                         this.mentor.panel_session_initiate.DATA.insertElementAt(main.elementAt(i),i);
                     }
                     System.out.println("Data Size: " + this.mentor.panel_session_initiate.DATA.size());
                     this.mentor.panel_session_initiate.table.repaint();
                     System.out.println("Table painted");
                 }
                 else if(resp==Resource.ResponseCodes.FILTER_SUCCESS)
                 {
                     Vector<Vector>main=(Vector<Vector>)in.readObject();
                     this.mentor.panel_past_session.DATA.clear();
                     for(int i=0;i<main.size();i++){
                         this.mentor.panel_past_session.DATA.add(main.elementAt(i));
                     }
                     this.mentor.panel_past_session.table.repaint();
                       for(int i=0;i<main.size();i++)
                       {
                           System.out.println("DATA :"+this.mentor.panel_past_session.DATA.elementAt(i));
                       }
                     System.out.println("filter success: " + main.size() );
                     
                 }
                 else if(resp==Resource.ResponseCodes.LIVE_SESSION_HEADLINE)
                 {
                     System.out.println("1 mentor thread");
                     String info=in.readObject().toString();
                     this.mentor.panel_session_initiate.lbl_session_info.setText(info);
                     //this.mentor.panel_session_initiate.btn_enroll.setVisible(true);
                     this.mentor.panel_session_initiate.lbl_session_info.setVisible(true);
                     this.mentor.panel_session_initiate.btn_table_showTab.setVisible(false);
                     this.mentor.panel_session_initiate.repaint();
                     System.out.println("2 mentor thread");
                 }
                 else if(resp==Resource.ResponseCodes.ADD_IN_FORM)
                 {
                     String query=in.readObject().toString();
                     this.mentor.session_held.area_form.append(query+"\n\n");
                 }
                 /*else if(resp==Resource.ResponseCodes.LIVE_SESSION_INFO){
                     String topic=in.readObject().toString();
                     String desc=in.readObject().toString();
                     String form_query=in.readObject().toString();
                     CommResource.mentor_log.session_held = new MentorSessionHeld();
                     CommResource.mentor_log.tab.addTab("Session Held",CommResource.mentor_log.session_held);
                     CommResource.mentor_log.tab.setSelectedIndex(2);
                     CommResource.mentor_log.tab.setVisible(true);
                     CommResource.mentor_log.session_held.txt_topic.setText(topic);
                     CommResource.mentor_log.session_held.area_desc.setText(desc);
                     CommResource.mentor_log.session_held.area_form.setText(form_query);
                 }*/
                 else if(resp==Resource.ResponseCodes.INC_COUNT)
                 {
                     String count=in.readObject().toString();
                     CommResource.mentor_log.session_held.lbl_count.setText(count);
                     CommResource.mentor_log.session_held.lbl_count.repaint();
                 }
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception occured " + ex);
        }
    }
}
