package dataObjects.newScheme;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Listen
{

    private int listenId;
    private int userId;
    private int songId;
    private int dateId;

    public Listen(int userId, int songId, int dateId)
    {
        this(userId, songId, dateId, -1);
    }

    public Listen(int userId, int songId, int dateId, int listenId)
    {
        this.listenId = listenId;
        this.userId = userId;
        this.songId = songId;
        this.dateId = dateId;
    }

    public int getListenId()
    {
        return listenId;
    }

    public int getUserId()
    {
        return userId;
    }

    public int getSongId()
    {
        return songId;
    }

    public int getDateId()
    {
        return dateId;
    }
}
