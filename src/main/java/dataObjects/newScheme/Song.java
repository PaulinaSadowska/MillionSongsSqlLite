package dataObjects.newScheme;

import dataObjects.IndexManager;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Song
{
    private int performanceId;
    private int songId;
    private String oldSongId;
    private String oldPerformanceId;
    private String artist;
    private String title;

    public Song(String oldPerformanceId, String oldSongId, String artist, String title, int songId){

        this.performanceId = -1;
        this.songId = songId;

        this.oldPerformanceId = oldPerformanceId;
        this.oldSongId = oldSongId;
        this.artist = artist;
        this.title = title;
    }

    public int getPerformanceId()
    {
        return performanceId;
    }

    public int getSongId()
    {
        return songId;
    }

    public String getOldSongId()
    {
        return oldSongId;
    }

    public String getOldPerformanceId()
    {
        return oldPerformanceId;
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
