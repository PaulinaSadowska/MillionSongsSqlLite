package dataObjects;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class UniqueTrack
{

    private String songId;
    private String performanceId;
    private String artist;
    private String title;

    public UniqueTrack(){
        this.songId = "";
        this.performanceId = "";
        this.artist = "";
        this.title = "";
    }

    public UniqueTrack(String dataRow){
        String[] data = dataRow.split("<SEP>");
        this.performanceId = data[0];
        this.songId = data[1];
        this.artist = data[2];
        if(data.length > 3){
            this.title = data[3];
        }
        else{
            this.title = "";
        }

    }

    public String getSongId()
    {
        return songId;
    }

    public String getPerformanceId()
    {
        return performanceId;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getTitle()
    {
        return title;
    }
}

