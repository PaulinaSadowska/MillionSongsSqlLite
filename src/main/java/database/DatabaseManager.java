package database;

import dataObjects.DataSource;
import dataObjects.newScheme.*;
import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;
import database.table.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DatabaseManager implements IDatabaseManager
{
    public static final String DATABASE_URL = "jdbc:sqlite:million.songs.newscheme.database";

    public static final String ARTISTS_TABLE = "Artists";
    public static final String DATE_RECORD_TABLE = "DateRecord";
    public static final String LISTEN_RECORD_TABLE = "ListenRecord";
    public static final String SONGS_TABLE = "Songs";
    public static final String TIME_RECORD_TABLE = "TimeRecord";
    public static final String USER_TABLE = "Users";

    private ArtistsTableManager artistsTableManager;
    private SongsTableManager songsTableManager;
    private ListenTableManager listenRecordManager;
    private DateRecordTableManager dateRecordTableManager;
    private UserTableManager userTableManager;
    private TimeRecordTableManager timeRecordTableManager;

    private Connection connection = null;
    private Statement statement = null;

    private Map<String, Integer> songs = new HashMap<>();
    ;
    private Map<String, Integer> users = new HashMap<>();
    ;
    private Map<Integer, Integer> dates = new HashMap<>();
    ;

    public DatabaseManager() throws SQLException
    {
        connection = DriverManager.getConnection(DATABASE_URL);
        statement = connection.createStatement();
        artistsTableManager = new ArtistsTableManager();
        songsTableManager = new SongsTableManager();
        listenRecordManager = new ListenTableManager();
        dateRecordTableManager = new DateRecordTableManager();
        userTableManager = new UserTableManager();
        timeRecordTableManager = new TimeRecordTableManager();
    }

    @Override
    public void insertUniqueTrackData(UniqueTrack track)
    {
        Integer songId = songs.get(track.getSongId());
        if (songId == null)
        {
            songId = IndexManager.getInstance().getAvailableSongId();
            songs.put(track.getSongId(), songId);
        }

        Artist artist = new Artist(track.getArtist());
        Song song = new Song(
                track.getPerformanceId(),
                track.getSongId(),
                track.getArtist(),
                track.getTitle(),
                songId);
        try
        {
            artistsTableManager.insertRecord(artist, connection);
            songsTableManager.insertRecord(song, connection);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertListenRecord(ListenRecord record)
    {
        DateRecord date = getDate(record);
        User user = getUser(record);
        try
        {
            if (user.isNew())
            {
                userTableManager.insertRecord(user, connection);
            }
            if (date.isNew())
            {
                dateRecordTableManager.insertRecord(date, connection);
                timeRecordTableManager.insertRecord(getTime(record, date.getDateId()), connection);
            }
            listenRecordManager.insertRecord(getListen(record, user.getUserId(), date.getDateId()), connection);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    private TimeRecord getTime(ListenRecord record, int dateId)
    {
        return new TimeRecord(dateId, record.getTimestamp());
    }

    private DateRecord getDate(ListenRecord record)
    {

        Integer dateId = dates.get(record.getTimestamp());
        if (dateId == null)
        {
            dateId = IndexManager.getInstance().getAvailableDateId();
            dates.put(record.getTimestamp(), dateId);
            return new DateRecord(dateId, record.getTimestamp());
        }
        return new DateRecord(dateId, record.getTimestamp(), false);

    }

    private Listen getListen(ListenRecord record, int userId, int dateId)
    {
        Integer songId = songs.get(record.getUserId());

        if (songId == null)
        {
            songId = -1;
            System.out.println("song not found! Did you load songs list first?");
        }

        return new Listen(userId, songId, dateId);
    }

    private User getUser(ListenRecord record)
    {
        Integer userId = users.get(record.getUserId());
        if (userId == null)
        {
            userId = IndexManager.getInstance().getAvailableUserId();
            users.put(record.getUserId(), userId);
            return new User(userId, record.getUserId());
        }
        return new User(userId, record.getUserId(), false); //old user
    }

    @Override
    public void cleanTable(DataSource dataSource) throws SQLException
    {
        ArrayList<String> queries = new ArrayList<>();
        if (dataSource == DataSource.LISTENS)
        {
            queries.add("DELETE FROM " + LISTEN_RECORD_TABLE);
            queries.add("DELETE FROM " + DATE_RECORD_TABLE);
            queries.add("DELETE FROM " + TIME_RECORD_TABLE);
            queries.add("DELETE FROM " + USER_TABLE);
        } else
        {
            queries.add("DELETE FROM " + ARTISTS_TABLE);
            queries.add("DELETE FROM " + SONGS_TABLE);
        }
        for (String q : queries)
        {
            statement.execute(q);
        }
    }

    @Override
    public void setAutocommit(boolean isAutocommit) throws SQLException
    {
        connection.setAutoCommit(isAutocommit);
    }

    @Override
    public void commit() throws SQLException
    {
        connection.commit();
    }

    @Override
    public void rollback() throws SQLException
    {
        connection.rollback();
    }
}
