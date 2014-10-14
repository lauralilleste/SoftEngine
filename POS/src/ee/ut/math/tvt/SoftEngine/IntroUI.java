package ee.ut.math.tvt.SoftEngine;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class IntroUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public IntroUI(){

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
		setVisible(true);
	}
}