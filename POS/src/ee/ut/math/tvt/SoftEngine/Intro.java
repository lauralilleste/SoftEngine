package ee.ut.math.tvt.SoftEngine;

import java.io.IOException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import ee.ut.math.tvt.SoftEngine.Intro;

public class Intro {
	private static final Logger log = Logger.getLogger(Intro.class);
	public static void main(String[] args) throws IOException {	
		BasicConfigurator.configure();
		IntroUI abc = new IntroUI();
		

	}
}
