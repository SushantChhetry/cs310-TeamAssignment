
package edu.jsu.mcis.tas_sp20;

import java.util.ArrayList;
import java.util.Calendar;


public class TASLogic {
    
    public static final int CLOCKIN = 1;
    
    public static final int CLOCKOUT = 0;
    
    public static int calculateTotalMinutes(ArrayList<Punch> dailypunchlist, Shift shift){
        int total = 0;
        int day;
        long inTime = 0;

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(dailypunchlist.get(0).getOriginaltimestamp());
        day = cal.get(Calendar.DAY_OF_WEEK);
        boolean isWeekend = (day == Calendar.SATURDAY) || (day == Calendar.SUNDAY);

        for (Punch punch : dailypunchlist) {
            switch (punch.getPunchtypeid()) {
                case 0:
                    int minutes = (int)((/* Adjusted Timestamp*/ - inTime) / 60000);
                    if (!isWeekend){
                        if (minutes > /* Lunch Deduct*/)) {
                            minutes -= /* Shift Lunch Duration*/;
                        }
                    }

                    else {
                        if (minutes > /* Lunch Deduct*/) {
                            minutes -= /* Lunch Duration*/;
                        }
                    }
                    total += minutes;
                    break;
                case 1:
                    inTime = /* Adjusted Timestamp*/;
                    break;
                case 2:
                    break;
            }
        }

        return total;
    }
    
}
