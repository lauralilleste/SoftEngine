package ee.ut.math.tvt.SoftEngine;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

public class IntroUI extends JFrame {
	private static final long serialVersionUID = 1L;

	public IntroUI() {
		String propFileName1 = "application.properties";
		String propFileName2 = "version.properties";
		Properties prop1 = new Properties();
		Properties prop2 = new Properties();
		// size & location
		int width = 600;
		int height = 200;
		setSize(width, height);
		setTitle("Intro");

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - width) / 2, (screen.height - height) / 2);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
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

		JPanel panel = new JPanel();
		JLabel team_name = new JLabel("Team name: "+prop1.getProperty("team_name"));
		JLabel team_email = new JLabel("Team email: "+prop1.getProperty("team_email"));
		JLabel team_leader = new JLabel("Team leader: "+prop1.getProperty("team_leader"));
		JLabel team_members = new JLabel("Team members: "
				+ prop1.getProperty("team_member1") + ", "
				+ prop1.getProperty("team_member2") + ", "
				+ prop1.getProperty("team_member3"));
		JLabel version = new JLabel("Versioon "
				+ prop2.getProperty("build.major.number")+"."+prop2.getProperty("build.minor.number")+"."+prop2.getProperty("build.revision.number"));
		panel.add(team_name);
		panel.add(team_leader);
		panel.add(team_email);
		panel.add(team_members);
		panel.add(version);

		getContentPane().add(panel);
		setVisible(true);
	}
}