package dataObjects;

import java.util.Date;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class ListenRecord
{

    private String songId;
    private String userId;
    private int timestamp;

    public ListenRecord(){
        this.songId = "";
        this.userId = "";
        this.timestamp = 0;
    }

    public ListenRecord(String dataRow){
        String[] data = dataRow.split("<SEP>");
        this.userId = data[0];
        this.songId = data[1];
        this.timestamp = Integer.parseInt(data[2]);
    }

    public ListenRecord(String songId, String userId, int timestamp){
        this.songId = songId;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getSongId()
    {
        return songId;
    }

    public String getUserId()
    {
        return userId;
    }

    public int getTimestamp()
    {
        return timestamp;
    }

    public Date getDate(){
        return new Date(timestamp);
    }
}
