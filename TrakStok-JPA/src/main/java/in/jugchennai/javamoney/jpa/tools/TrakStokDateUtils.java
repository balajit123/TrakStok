/*
 * Copyright 2013 JUGChennai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.jugchennai.javamoney.jpa.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Balaji T
 */
public abstract class TrakStokDateUtils {
    
    private static final Logger LOGGER = Logger.getGlobal();
    
    public static Date getCurrentDateAndTime(){
        GregorianCalendar calendar = (GregorianCalendar) GregorianCalendar.getInstance();
        return calendar.getTime();
    }
    
    public static Date getCurrentDate(){        
        return getTruncatedDate(getCurrentDateAndTime());
    }
    
    public static Date getTruncatedDate(Date date){
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(GregorianCalendar.HOUR_OF_DAY, 0);
        calendar.set(GregorianCalendar.MINUTE, 0);
        calendar.set(GregorianCalendar.SECOND, 0);
        calendar.set(GregorianCalendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static boolean isToday(Date dateToCompare) {
        if(dateToCompare == null){
            throw new IllegalArgumentException("Date to Compare should not be null");
        }
        Date currentDate = getCurrentDate();
        LOGGER.info("Current date is "+currentDate.toString());
        LOGGER.info("Date to compare is "+dateToCompare.toString());
        if(currentDate.compareTo(getTruncatedDate(dateToCompare)) == 0){
            return true;
        }
        return false;
    }

    public static java.util.Date parse(String date, String dateFormat) {
        if(date == null || date.trim().equals("")){
            throw new IllegalArgumentException("The date string should not be null");
        }
        if(dateFormat == null || dateFormat.trim().equals("")){
            throw new IllegalArgumentException("The date format string should not be null");
        }
        
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        try {
            return format.parse(date);
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "Could not parse the date "+date+" with the date format "+dateFormat, ex);
        }
        return null;
    }

    public static String format(java.util.Date date, String dateFormat) {
        TrakStokUtils.validateParameter(date, "Date should not be null");
        TrakStokUtils.validateParameter(dateFormat, "Date Format should not be null or empty string");
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }
}
