import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class rl_example_1 {

	//0*10*1(0+1)*
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//System.out.println("Starting Writing to test");
		for (int i =1; i <= 100; i ++) {
			System.out.println("Starting Writing to test" + i + ".txt");
			FileWriter fw_0 = null;
	    	BufferedWriter bw_0 = null;
	    	PrintWriter out = null;
	    	try {
	    		File file_0 = new File("test" + i + ".txt");
	    		fw_0 = new FileWriter(file_0.getAbsoluteFile());
	    	    bw_0 = new BufferedWriter(fw_0);	    	    
	    	} catch (IOException e) {
	    	    //exception handling left as an exercise for the reader
	    	}
	    	
	    	
	    	Random r = new Random();
	    	int limit;
	    	int Low = 0;
	    	int High = 20;
	    	int max = 500 * i;
	    	for (int j = 1; j <= max; j++) {
	    		String line = "S";
	    		
	    		limit = r.nextInt(High-Low+1) + Low;	    		
	    		for (int star_1 = 0; star_1 <= limit; star_1++) {
	    			line = line + "A";
	    		}
	    		
	    		line = line + "B";
	    		
	    		limit = r.nextInt(High-Low) + Low;
	    		for (int star_2 = 0; star_2 <= limit; star_2++) {
	    			line = line + "A";
	    		}
	    		
	    		line = line + "B";
	    		
	    		limit = r.nextInt(High-Low) + Low;
	    		for (int star_3 = 0; star_3 <= limit; star_3++) {
	    			int random =  r.nextInt(2);
	    			if (random == 0) {
	    				line = line + "A";
	    			}
	    			if (random == 1) {
	    				line = line + "B";
	    			}
	    		}
	    		
	    		line = line + "E\n";
	    		bw_0.write(line);
	    	}
	    	bw_0.close();
	    	
		}
	}

}
