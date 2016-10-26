package dataObjects.newScheme;

import dataObjects.IndexManager;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Artist
{
    private int artistId;
    private String artist;

    public Artist(String artist){
        this.artistId = IndexManager.getInstance().getAvailableArtistId();
        this.artist = artist;
    }

    public int getArtistId()
    {
        return artistId;
    }

    public String getArtist()
    {
        return artist;
    }
}

