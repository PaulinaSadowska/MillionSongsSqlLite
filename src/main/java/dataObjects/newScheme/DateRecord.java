package dataObjects.newScheme;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DateRecord
{
    private int dateId;
    private long timestamp;
    private boolean isNew;

    public DateRecord(int dateId, long timestamp)
    {
        this(dateId, timestamp, true);
    }

    public DateRecord(int dateId, long timestamp, boolean isNew)
    {
        this.dateId = dateId;
        this.timestamp = timestamp;
        this.isNew = isNew;
    }

    public int getDateId()
    {
        return dateId;
    }

    public long getTimestamp()
    {
        return timestamp;
    }

    public boolean isNew()
    {
        return isNew;
    }
}
