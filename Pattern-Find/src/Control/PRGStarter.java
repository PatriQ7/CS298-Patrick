
package Control;

import GetDNA.General;
import TestCase.TestCase03;
import Util.AYLogger;

import java.io.*;
import java.util.Properties;

/*
 * This class is the main class of the program and control all tasks.
 * @author Nikhil Kalantri
 * @modified Prashanth Sandela
 * @version 1.0
 */
public class PRGStarter {

// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//

    private static final String PRG_MSG =
        "********** PatternDiscovery Started! **************\n\n";
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** hold configuration object which contains all initialization parameters. */
    private static Properties config;


// ........................ C O N S T R U C T O R S ..........................//
//.............................. G E T T E R S ...............................//
//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//
// ........................ M A I N   M E T H O D ............................//

    /**
     * This is the main method and entry point of this application.
     * @param args the application arguments
     * @throws FileNotFoundException 
     */
    public static void main(String[] args) throws FileNotFoundException {

        config = Config.getConfig();

        File file = new File(config.getProperty("NEW_LOG_FILE"));	
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
//		System.setOut(ps);

		System.out.println("Free Memory " + General.getMemory());
		
		General.createFile(config.getProperty("NEW_LOG_FILE"), "", false);
		
		AYLogger logger = new AYLogger(config);
		logger.record(PRG_MSG);

		TestEngine testEngn = new TestEngine(config, logger);

		TestCase03.test304(testEngn);

		logger.closeLog();
		
		System.setOut(System.out);
		System.out.println("Program Done");

    } //main method

} //class

