package dataObjects;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class IndexManager
{
    private int availablePerformanceId;
    private int availableSongId;
    private int availableUserId;
    private int availableListenId;
    private int availableArtistId;
    private int availableDateId;


    private static IndexManager indexManager;

    private IndexManager(){
        availablePerformanceId = 0;
        availableSongId = 0;
        availableUserId = 0;
        availableListenId = 0;
        availableDateId = 0;
    }

    public static IndexManager getInstance(){
        if(indexManager == null)
            indexManager = new IndexManager();

        return indexManager;
    }


    public int getAvailablePerformanceId()
    {
        return availablePerformanceId++;
    }

    public int getAvailableSongId()
    {
        return availableSongId++;
    }

    public int getAvailableUserId()
    {
        return availableUserId++;
    }

    public int getAvailableListenId()
    {
        return availableListenId++;
    }

    public int getAvailableArtistId()
    {
        return availableArtistId++;
    }

    public int getAvailableDateId()
    {
        return availableDateId++;
    }
}