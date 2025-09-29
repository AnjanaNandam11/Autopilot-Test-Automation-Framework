
package org.example;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main {


    public static void main(String[] args) throws IOException, InterruptedException, AWTException {
    	
    	FileInputStream fis = new FileInputStream("./config.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		String email = prop.getProperty("Email");
		String username = prop.getProperty("Username");
		String password = prop.getProperty("Password");
		
        ScenarioOne one = new ScenarioOne(email,username,password);
        ScenarioTwo two = new ScenarioTwo(email,username, password);
	    ScenarioThree three = new  ScenarioThree(); 
	    ScenarioFour four = new ScenarioFour(); 
	    ScenarioFive five = new ScenarioFive(email,password); 
	    
	    one.launch();
	    two.launch();
	    three.launch();
	    four.launch(); 
		five.launch();
		 


    }


}
