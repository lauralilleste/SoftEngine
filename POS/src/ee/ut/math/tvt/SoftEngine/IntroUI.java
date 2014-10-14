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

public class IntroUI extends JFrame {
	private static final long serialVersionUID = 1L;

	public IntroUI() {
		String propFileName1 = "application.properties";
		Properties prop = new Properties();
		// size & location
		int width = 600;
		int height = 400;
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
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName1);
		try {
			if (inputStream == null) {
				throw new FileNotFoundException("property file '" + propFileName1 + "' not found in the classpath");
			}
			prop.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(prop);
		
		setVisible(true);
	}
}