package dataObjects.newScheme;

import dataObjects.IndexManager;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class TimeRecord
{
    private int dateId;
    private long timestamp;

    public TimeRecord(){

    }

    public TimeRecord(long timestamp){
        dateId = IndexManager.getInstance().getAvailableDateId();
        this.timestamp = timestamp;
    }

    public int getDateId()
    {
        return dateId;
    }

    public long getTimestamp()
    {
        return timestamp;
    }
}
