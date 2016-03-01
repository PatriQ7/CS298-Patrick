
package Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/*
 * This class simulate a timer by using the system timer.
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class AYTimer {
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//

    private static final long SEC_TO_MILLISEC  = 1000;
    private static final long MIN_TO_MILLISEC  = 60 * SEC_TO_MILLISEC;
    private static final long HOUR_TO_MILLISEC = 60 * MIN_TO_MILLISEC;
    private static final long DAY_TO_MILLISEC  = 24 * HOUR_TO_MILLISEC;

// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** Hold the start of a timer. */
    private long start;

    /** Hold the end of the timer. */
    private long end;

// ........................ C O N S T R U C T O R S ..........................//

    /**
     * Create a new object by setting the start instance variable.
     */
    public AYTimer() {

        start = System.currentTimeMillis();

    } //constructor

    //.............................. G E T T E R S ...............................//

    public long getEnd() {
        return end;
    }


    public long getStart() {
        return start;
    }



//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * Stop the timer.
     */
    public void stopTimer() {

        end = System.currentTimeMillis();

    } //method


    /**
     * Calculate the duration of the event in seconds.
     * @return the duration in terms of seconds.
     */
    public double getDurationInSec() {

            return ((double) end - (double) start) / 1000.0;

    } //method


    public String getDurationString() {

        long currDur = end - start;

        int day = (int) (currDur / DAY_TO_MILLISEC);
        currDur = currDur % DAY_TO_MILLISEC;

        int hour = (int) (currDur / HOUR_TO_MILLISEC);
        currDur = currDur % HOUR_TO_MILLISEC;

        int min = (int) (currDur / MIN_TO_MILLISEC);
        currDur = currDur % MIN_TO_MILLISEC;

        int sec = (int) (currDur / SEC_TO_MILLISEC);
        int milliSec = (int) (currDur % SEC_TO_MILLISEC);

        StringBuilder sb = new StringBuilder();

        if (day != 0) sb.append(String.format("%d Day, ", day));
        if (hour != 0) sb.append(String.format("%2d Hour, ", hour));
        if (min != 0) sb.append(String.format("%2d Min, ", min));
        if (sec != 0) sb.append(String.format("%2d Sec, ", sec));
        if (milliSec != 0) sb.append(String.format("%3d Millisec, ", milliSec));

        return sb.toString();

    } //method


    /**
     * Return current date and time as an string of the
     * format MM/DD/YYYY @ HH:MM.
     */
    public static String getTimeStamp() {

        SimpleDateFormat formatter =
			new SimpleDateFormat("MM/dd/yyyy@HH:mm ", new Locale("en_US"));

		return formatter.format(new Date());

    } //method


    private static void testgetTimeStamp() {

        System.out.println(getTimeStamp());

    } //test method


} //class

