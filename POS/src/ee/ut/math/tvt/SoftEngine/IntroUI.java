package ee.ut.math.tvt.SoftEngine;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.apache.log4j.Logger;
import com.jgoodies.looks.MicroLayout;
import com.jgoodies.looks.windows.WindowsLookAndFeel;
import com.sun.xml.internal.ws.api.server.Container;
import ee.ut.math.tvt.SoftEngine.IntroUI;

public class IntroUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private final Logger log = Logger.getLogger(IntroUI.class);
	public IntroUI() {
		log.info("Intro started");
		this.Frame();
	}
	Properties prop3 = new Properties();
	private void Frame() {
		
        final JFrame jFrame = new JFrame();
        
        
        final java.awt.Container contentPane = jFrame.getContentPane();
        contentPane.setLayout(new GridLayout(3, 1));
     
       
		String propFileName1 = "application.properties";
		String propFileName2 = "version.properties";
		Properties prop1 = new Properties();
		Properties prop2 = new Properties();
		
		// size & location
		int width = 800;
		int height = 500;
		jFrame.setSize(width, height);
		jFrame.setTitle("Intro");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		jFrame.setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName1);
		try {
			UIManager.setLookAndFeel(new WindowsLookAndFeel());

		} catch (UnsupportedLookAndFeelException e1) {
			// log.warn(e1.getMessage());
		}

		// Application
		InputStream inputStream1 = getClass().getClassLoader()
				.getResourceAsStream(propFileName1);
		try {
			if (inputStream1 == null) {
				throw new FileNotFoundException("property file '"
						+ propFileName1 + "' not found in the classpath");
			}
			prop1.load(inputStream1);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		// Version
		InputStream inputStream2 = getClass().getClassLoader()
				.getResourceAsStream(propFileName2);
		try {
			if (inputStream2 == null) {
				throw new FileNotFoundException("property file '"
						+ propFileName2 + "' not found in the classpath");
			}
			prop2.load(inputStream2);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		InputStream inputStream3 = getClass().getClassLoader()
				.getResourceAsStream(propFileName2);
		
		JPanel cpanel = new JPanel();
		cpanel.setLayout(new FlowLayout());
		
		JPanel panel = new JPanel();
		 panel.setLayout(new GridLayout(6, 1));
	         
		 
		
		 JLabel team_name = new JLabel("Team name: "+prop1.getProperty("team_name"),JLabel.CENTER);
		 team_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    team_name.setBounds(10, 91, 79, 19);
		 panel.add(team_name);
		JLabel team_email = new JLabel("Team leader email: "+prop1.getProperty("team_email"),JLabel.CENTER);
		 team_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    team_email.setBounds(10, 91, 79, 19);
		 panel.add(team_email);
		 
		JLabel team_leader = new JLabel("Team leader: "+prop1.getProperty("team_leader"),JLabel.CENTER);
		 team_leader.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    team_leader.setBounds(10, 91, 79, 19);
		 panel.add(team_leader);
		JLabel team_members = new JLabel("Team members: "
				+ prop1.getProperty("team_member1") + ", "
				+ prop1.getProperty("team_member2") + ", "
				+ prop1.getProperty("team_member3"),JLabel.CENTER);
		 team_members.setFont(new Font("Tahoma", Font.PLAIN, 15));
		    team_members.setBounds(10, 91, 79, 19);
		 panel.add(team_members);
		
		
		  try {
				BufferedImage image = ImageIO.read(new File(prop1.getProperty("team_logo")));
				ImageIcon image1 = new ImageIcon(image);
				JLabel imagelabel = new JLabel(image1,JLabel.CENTER);
			
				 contentPane.add(imagelabel);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		

		
		
		
		contentPane.add(panel);
		  JLabel version = new JLabel("Versioon "
					+ prop2.getProperty("build.major.number")+"."+prop2.getProperty("build.minor.number")+"."+prop2.getProperty("build.revision.number"),JLabel.CENTER);
			 contentPane.add(version);
	     
		jFrame.setVisible(true);
	}

}
