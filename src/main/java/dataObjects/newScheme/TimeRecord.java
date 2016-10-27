package dataObjects.newScheme;

import java.util.Date;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class TimeRecord
{
    private int dateId;
    private int minute;
    private int hour;
    private int day;
    private int month;
    private int year;

    public TimeRecord(int dateId, long timestamp){

        this.dateId = dateId;

        Date date = new Date(timestamp);
        this.minute = date.getMinutes();
        this.hour = date.getHours();
        this.day = date.getDay();
        this.month = date.getMonth();
        this.year = date.getYear();
    }

    public int getMinute()
    {
        return minute;
    }

    public int getHour()
    {
        return hour;
    }

    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public int getDateId()
    {
        return dateId;
    }
}

