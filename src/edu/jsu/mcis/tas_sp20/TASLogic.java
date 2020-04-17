
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
                    int minutes = (int)((dailypunchlist.get(0).getOriginaltimestamp() - inTime) / 60000);
                    if (!isWeekend){
                        if (minutes > shift.getShiftLunchDeduct()) {
                            minutes -= shift.totalLunchDuration();
                        }
                    }

                    else {
                        if (minutes > shift.getShiftLunchDeduct()) {
                            minutes -= shift.totalLunchDuration();
                        }
                    }
                    total += minutes;
                    break;
                case 1:
                    inTime = dailypunchlist.get(0).getOriginaltimestamp();
                    break;
                case 2:
                    break;
            }
        }

        return total;
    }
}