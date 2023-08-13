package ui;

import domain.validity.InputValidity;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.ObstacleConfiguration;


public class GroupPanel extends JPanel implements ActionListener{
	private BufferedImage simpleimage;
	private BufferedImage firmimage;
	private BufferedImage explosiveimage;
	private BufferedImage giftimage;
    private JLabel label1 = new JLabel("Simple:");
    private JButton button = new JButton("Apply");
    private JTextField field1 = new JTextField(16);
    private JLabel label2 = new JLabel("Gift:");
    private JTextField field2 = new JTextField(16);
    private JLabel label3 = new JLabel("Explosive:");
    private JTextField field3 = new JTextField(16);
    private JLabel label4 = new JLabel("Firm:");
    private JTextField field4 = new JTextField(16);
    public static int simple_num=0;
    public static int firm_num=0;
    public static int gift_num=0;
 
    
    public static int explosive_num=0;
    ObstacleConfiguration config;
    InputValidity inputValidity;
    public GroupPanel() {
  
    	
    	inputValidity = new InputValidity();
    	
        JLabel jl=new JLabel();
       
        this.add(jl);
    	this.setBorder(BorderFactory.createTitledBorder("Number of Obstacles"));
        GroupLayout layout = new GroupLayout(this);          
        this.setLayout(layout);
        
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(label1)
                .addComponent(label2)
                .addComponent(label3)
                
                .addComponent(label4)
                .addComponent(button))
            	
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(field1)
                .addComponent(field2)
                .addComponent(field3)
                .addComponent(field4)
                .addComponent(button))
        );
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label1)
                .addComponent(field1))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label2)
                .addComponent(field2))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(label3)
                .addComponent(field3))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(label4)
                    .addComponent(field4)	)
            .addComponent(button)
            
           
        );

        button.addActionListener(this);
  
    }
    public ObstacleConfiguration getConfiguration() {
    	return this.config;
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s1=field1.getText();  
        String s2=field2.getText();
        String s3=field3.getText();  
        String s4=field4.getText();
        
        if(!inputValidity.isValidObstacleNumber(s1)) { this.simple_num = 0;
        } else { this.simple_num=Integer.parseInt(s1); }
        
        if(!inputValidity.isValidObstacleNumber(s2)) { this.gift_num = 0;
        } else { this.gift_num=Integer.parseInt(s2); }
        
        if(!inputValidity.isValidObstacleNumber(s3)) { this.explosive_num = 0;
        } else { this.explosive_num=Integer.parseInt(s3); }
        
        if(!inputValidity.isValidObstacleNumber(s4)) { this.firm_num = 0;
        } else { this.firm_num=Integer.parseInt(s4); }
        

    	this.config = ObstacleConfiguration.getInstance();
    	this.config.initializeObstacleConfiguration(simple_num,firm_num,explosive_num,gift_num);
        
        
		
	}

	
}