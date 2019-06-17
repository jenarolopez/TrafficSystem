/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulePackage;

import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Jen
 */
public class NewClass {

    public static void main(String[] args) {

        //getting current date and time using Date class
        DateFormat df = new SimpleDateFormat("MM");
        Date dateobj = new Date();
        System.out.println(df.format(dateobj));

        /*getting current date time using calendar class 
        * An Alternative of above*/
        Calendar calobj = Calendar.getInstance();
        System.out.println(df.format(calobj.getTime()));

        int dayOfWeek = Calendar.TUESDAY;
        int hour = 10; // 10 AM
        int minute = 0;

        Calendar cal = Calendar.getInstance(); // Today, now
        if (cal.get(Calendar.DAY_OF_WEEK) != dayOfWeek) {
            cal.add(Calendar.DAY_OF_MONTH, (dayOfWeek + 7 - cal.get(Calendar.DAY_OF_WEEK)) % 7);
        } else {
            int minOfDay = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
            if (minOfDay >= hour * 60 + minute) {
                cal.add(Calendar.DAY_OF_MONTH, 7); // Bump to next week
            }
        }
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        System.out.println(cal.getTime());

        /* The pattern "EEE" for Day of the week in short form
	 * such as "Mon", "Tue", "Wed" etc.
         */
        SimpleDateFormat sdf = new SimpleDateFormat("EEE");
        String stringDate = sdf.format(new Date());
        System.out.println("Today is: " + stringDate);

        /* The pattern "EEEE" for Day of the week in full form
	 * such as "Monday", "Tuesday", "Wednesday" etc.
         */
        SimpleDateFormat sdf2 = new SimpleDateFormat("EEEE");
        String stringDate2 = sdf2.format(new Date());
        System.out.println("Today is: " + stringDate2);
        Date a = getFirstDateOfCurrentMonth();
        System.out.println(a);
        
    }

    public static Date getFirstDateOfCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
        return cal.getTime();
    }
}
