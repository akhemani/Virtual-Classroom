package cdvirtualclient;

import java.awt.*;
import java.awt.image.*;
import java.util.Vector;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.event.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class MentorSessionHeld extends JPanel implements ActionListener
{
    JLabel lbl_topic,lbl_desc,lbl_pres,lbl_count;
    JTextField txt_topic;
    JTextArea area_form,area_query,area_desc;
    JScrollPane jsp_form,jsp_query;
    JButton btn_send;
    WebcamPanel panel ;
    Webcam webcam ;
    JButton btnStart,btnStop,btnterminate;
    RecordVideo recVideo;
    
    MentorSessionHeld()
    {
        lbl_topic=new JLabel("TOPIC--");
        lbl_desc=new JLabel("DESCRIPTION--");
        lbl_pres=new JLabel("Presence : ");
        lbl_count=new JLabel("0");
        
        webcam= Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		panel= new WebcamPanel(webcam);
		panel.setFPSDisplayed(true);
		panel.setDisplayDebugInfo(true);
		panel.setImageSizeDisplayed(true);
		panel.setMirrored(true);
        
        this.setLayout(null);
        lbl_topic=new JLabel("TOPIC:");
        txt_topic=new JTextField();
        txt_topic.setEditable(false);
        lbl_desc=new JLabel("DESCRIPTION:");        
        area_desc=new JTextArea();
        area_desc.setEditable(false);
        area_form=new JTextArea();
        jsp_form=new JScrollPane(area_form,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        area_query=new JTextArea();
        jsp_query=new JScrollPane(area_query,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        btn_send=new JButton("send");
        btn_send.addActionListener(this);
        this.btnStart=new JButton("Start");
        this.btnStop=new JButton("Pause");
        this.btnterminate = new JButton("Terminate");
        this.btnStart.addActionListener(this);
        this.btnStop.addActionListener(this);
        this.btnterminate.addActionListener(this);
        
        this.design();
    }
    
    void design()
    {
        setPosition(btn_send,700,100,100,25);
        setPosition(lbl_topic,100,50,100,25);
        setPosition(txt_topic,200,50,200,30);
        setPosition(lbl_desc,100,100,100,25);
        setPosition(area_desc,200,100,400,60);
        setPosition(this.btnStart,650,100,100,20);
        setPosition(this.btnterminate,650,60,100,20);
        setPosition(this.btnStop,650,140,100,20);
        setPosition(lbl_pres,1000,20,80,20);
        setPosition(lbl_count,1080,20,50,20);
        setPosition(jsp_form,800,50,500,400);
        setPosition(jsp_query,800,480,500,100);
        setPosition(btn_send,1000,600,100,25);
        setPosition(panel,100,200,650,400);
    }
    
    void setPosition(Component C,int x,int y,int w,int h)
    {
        this.add(C);
        C.setBounds(x,y,w,h);
    } 
   
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==btn_send){
            try{
                ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());    
                out.writeObject(Resource.RequestCodes.QUERY_ASK);
                out.writeObject(area_query.getText());
                this.area_query.setText("");
                
            }catch(Exception ex){
                
            }
            
        }
        if(ae.getSource()==this.btnStart){
            
		//webcam.open(true);

		Vector<BufferedImage> images = new Vector<BufferedImage>();

		System.out.println("recording, please wait");

               this.recVideo=new RecordVideo();
               this.recVideo.running=true;
               this.recVideo.start();
                
		

		
        }
        if(ae.getSource()==this.btnStop){
            this.recVideo.running=false;
             System.out.println("Sending stopped...");
        }
        
        if(ae.getSource()==this.btnterminate)
        {
            this.recVideo.running=false;
            System.out.println("Video Terminated");
            try 
            { 
                ObjectOutputStream out = new ObjectOutputStream(CommResource.client.getOutputStream());
                out.writeObject(Resource.RequestCodes.TERMINATE);
                panel.setEnabled(false);
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(MentorSessionHeld.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class RecordVideo extends Thread{
        volatile boolean running;
        RecordVideo(){
            running =false;
        }
        public void run(){
            try{
            while(running) {
                       
			BufferedImage image= webcam.getImage();
                        //webcam.getI
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write( image, "jpg", baos );
                        baos.flush();
                        byte[] imageInByte = baos.toByteArray();
                        baos.close();
                       // Vector<BufferedImage> vimage=new Vector<BufferedImage>();
                        //vimage.add(image);
                        ObjectOutputStream out=new ObjectOutputStream(CommResource.client.getOutputStream());
                        out.writeObject(Resource.RequestCodes.STREAM);
                        out.writeObject(imageInByte);
                        //System.out.println("Sending image...");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
                            System.out.println("Cannot Read");
                            webcam.close();
				return;
			}
		}
            }catch(Exception ex){
                webcam.close();
                ex.printStackTrace();
            }
        }
    }
}
