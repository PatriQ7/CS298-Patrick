package Control;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class Start {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		FileWriter fw_0 = null;
    	BufferedWriter bw_0 = null;
    	PrintWriter out = null;
    	try {
    		File file_0 = new File("out_node_mini.txt");
    		fw_0 = new FileWriter(file_0.getAbsoluteFile());
    	    bw_0 = new BufferedWriter(fw_0);
    	    bw_0.write("(");
    	    bw_0.close();
    	} catch (IOException e) {
    	    //exception handling left as an exercise for the reader
    	}
    	
    	try {
    		File file_0 = new File("out_node_alergia.txt");
    		fw_0 = new FileWriter(file_0.getAbsoluteFile());
    	    bw_0 = new BufferedWriter(fw_0);
    	    bw_0.write("(");
    	    bw_0.close();
    	} catch (IOException e) {
    	    //exception handling left as an exercise for the reader
    	}
    	    	
    	for (int i = 1; i <=100; i++) {
	    	//Change config file	
    		File file_r = new File("str_config_temp.cfg");
    		
    		File file_w = new File("str_config.cfg");
    		FileWriter fw_w = null;
			try {
				fw_w = new FileWriter(file_w.getAbsoluteFile());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
        	BufferedWriter bw_w = new BufferedWriter(fw_w);
        	
        	
        	try (BufferedReader br = new BufferedReader(new FileReader(file_r))) {
        	    String line;
        	    while ((line = br.readLine()) != null) {
        	    	line = line + "\n";      	    
            	    bw_w.write(line);
        	       // process the line.
        	    }
        	    line = "TRAINING_FILE = ./str_training_data/rl_test_2/test" + i + ".txt\n";
        	    bw_w.write(line);
        	    line = "TEST_FOLDER = ./str_test_data/empty/\n";
        	    bw_w.write(line);     	    
        	} catch (IOException e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    		
        	try {
				bw_w.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//Config temp = new Config();
        	
        	Properties config = new Properties();
        	config.load(new FileInputStream("str_config.cfg"));
        	//Properties config = new Config.new_Config(tmp_conf, "str");
        	System.out.println("*********************");
        	System.out.println(config.getProperty("TRAINING_FILE"));
        	System.out.println("*********************");
        	String[] example = {};

				PRGStarter.start(example, config);


    	}
    	
    	
    	FileWriter fw_1 = null;
    	BufferedWriter bw_1 = null;
    	PrintWriter out_1 = null;
    	try {
    	    fw_1 = new FileWriter("out_node_mini.txt", true);
    	    bw_1 = new BufferedWriter(fw_1);
    	    out_1 = new PrintWriter(bw_1);
    	    out_1.print(")");
    	    out_1.close();
    	} catch (IOException e) {
    	    //exception handling left as an exercise for the reader
    	}
    	
    	try {
    	    fw_1 = new FileWriter("out_node_alergia.txt", true);
    	    bw_1 = new BufferedWriter(fw_1);
    	    out_1 = new PrintWriter(bw_1);
    	    out_1.print(")");
    	    out_1.close();
    	} catch (IOException e) {
    	    //exception handling left as an exercise for the reader
    	}
    }
	


	

}
