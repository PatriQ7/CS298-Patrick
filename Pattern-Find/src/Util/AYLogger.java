/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Util;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Properties;

/*
 * This class
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class AYLogger {

// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    BufferedWriter writer;

// ........................ C O N S T R U C T O R S ..........................//


    public AYLogger(Properties config) {

        String logFN = config.getProperty("LOG_FILE_NAME");
        boolean appendLog = Boolean.valueOf(config.getProperty("APPEND_LOG"));

        try {
            if (appendLog)
                writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(logFN, appendLog),
                    Charset.forName("UTF-8")));
            else
                writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(logFN), Charset.forName("UTF-8")));


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } //constructor

//.............................. G E T T E R S ...............................//
//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * write the message into the log file.
     * @param msg - the message
     */
    public void record(String msg) {

        String finalMsg = AYTimer.getTimeStamp() + msg;

        try {
            System.out.print(finalMsg);
            writer.write(finalMsg);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } //method


    /**
     * close the log writer.
     */
    public void closeLog() {

        /*............. Logging Task ..............................*/
        String taskMsg = "logger closed!\n\n";
        record(taskMsg);
        /*.........................................................*/

        try {
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    } //method


    /**
     */
} //class

