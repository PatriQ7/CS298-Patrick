package Control;

import java.io.FileInputStream;
import java.util.Properties;


/*
 * This class is the main class of the program and control all tasks.
 * @author Prashanth Sandela
 * @version 1.0
 */
public class Config {

	private static Properties config;
	private static String DataType = "str";
	static {
		String configFN = DataType + "_config.cfg";
		config = new Properties();
		try {
			config.load(new FileInputStream(configFN));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Properties getConfig()
	{
		return config;
	}
	
	public static String getProperty(String property)
	{
		return config.getProperty(property);
	}
}
