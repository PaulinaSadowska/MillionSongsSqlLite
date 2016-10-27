package database;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class IndexManager
{
    private int availableSongId;
    private int availableUserId;
    private int availableDateId;


    private static IndexManager indexManager;

    private IndexManager(){
        availableSongId = 0;
        availableUserId = 0;
        availableDateId = 0;
    }

    public static IndexManager getInstance(){
        if(indexManager == null)
            indexManager = new IndexManager();

        return indexManager;
    }


    public int getAvailableSongId()
    {
        return availableSongId++;
    }

    public int getAvailableUserId()
    {
        return availableUserId++;
    }

    public int getAvailableDateId()
    {
        return availableDateId++;
    }
}