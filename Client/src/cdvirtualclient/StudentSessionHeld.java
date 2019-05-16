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

public class StudentSessionHeld extends JPanel implements ActionListener
{
    JLabel lbl_topic,lbl_desc,lbl_pres,lbl_count;
    JTextField txt_topic;
    JTextArea area_form,area_query,area_desc;
    JScrollPane jsp_form,jsp_query;
    JButton btn_send;
    PlayerPanel player;
    
    StudentSessionHeld()
    {
        lbl_topic=new JLabel("TOPIC--");
        lbl_desc=new JLabel("DESCRIPTION--");
        lbl_pres=new JLabel("Presence : ");
        lbl_count=new JLabel("0");
        
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
        player=new PlayerPanel();
        this.design();
    }
    
    public final class PlayerPanel extends JPanel {

		private static final long serialVersionUID = 1L;
                private BufferedImage image;
		
		private  Dimension size;
		private int offset = 0;

                
		public PlayerPanel() {
			super();
			
		}

		public void play(BufferedImage image){
                    this.image=image;
                    this.repaint();
                }

		@Override
		protected void paintComponent(Graphics g) {
                    if(this.image!=null){
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(image, 0, 0, null);
                    }
		}
	}
    
    void design()
    {
        setPosition(btn_send,700,100,100,25);
        setPosition(lbl_topic,100,50,100,25);
        setPosition(txt_topic,200,50,200,30);
        setPosition(lbl_desc,100,100,100,25);
        setPosition(area_desc,200,100,400,60);
        setPosition(lbl_pres,1000,20,80,20);
        setPosition(lbl_count,1080,20,50,20);
        setPosition(jsp_form,800,50,500,400);
        setPosition(jsp_query,800,480,500,100);
        setPosition(btn_send,1000,600,100,25);
        setPosition(this.player,100,200,650,400);
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
    }
}
