package GetDNA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * This class is used to get data from url in which sequence is present.
 * This is not the actual URL from the browser. This is parsing URL.
 * @author Prashanth Sandela
 *
 */
public class GetDnaData {
	public static void main(String args[]) throws IOException
	{
		// Human DNA
//		String URL = "http://www.ncbi.nlm.nih.gov/portal/loader/pload.cgi?curl=http%3A%2F%2Fwww.ncbi.nlm.nih.gov%2Fsviewer%2Fviewer.cgi%3Fval%3D82583714%26db%3Dnuccore%26dopt%3Dfasta%26extrafeat%3D0%26fmt_mask%3D0%26retmode%3Dhtml%26withmarkup%3Don%26log%24%3Dseqview&dsrc=D&oid=3199742587&pid=";
		
		//Chimpanzee DNA
		String URL ="http://www.ncbi.nlm.nih.gov/portal/loader/pload.cgi?curl=http%3A%2F%2Fwww.ncbi.nlm.nih.gov%2Fsviewer%2Fviewer.cgi%3Fval%3D49619244%26db%3Dnuccore%26dopt%3Dfasta%26extrafeat%3D0%26fmt_mask%3D0%26retmode%3Dhtml%26withmarkup%3Don%26log%24%3Dseqview&dsrc=B&oid=2278541163&pid=";
		String outputFile = ".\\DNA_Data\\chimpanzee.txt";
		StringBuilder data = new StringBuilder();
		//Create a new file
		General.createFile(outputFile, "", false);
		int i = 0;
		while(true)
		{
			URL url = new URL(URL + i);
			System.out.println(i + " Loading URL: " + URL + i);
			URLConnection conn = url.openConnection();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			StringBuilder sb = new StringBuilder();
			
			while((inputLine = br.readLine()) != null)
			{
				if(!inputLine.isEmpty())
					sb.append(inputLine.trim() + "\r\n");	
			}
			
			data.append(sb.toString().replaceAll("<span .*\">|</span>", "").trim());
			if( i % 10 == 0)
			{
				if(data.toString().isEmpty())
					break;
				
				General.createFile(outputFile, data.toString(), true);
				data = new StringBuilder();
			}
			
			i++;
		}
		
		System.out.println("Done");
	}
}
