package database;

import dataObjects.DataSource;
import dataObjects.IndexManager;
import dataObjects.newScheme.Artist;
import dataObjects.newScheme.Song;
import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;
import database.table.ArtistsTableManager;
import database.table.ListenTableManager;
import database.table.SongsTableManager;
import database.table.UniqueTrackTableManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private Connection connection = null;
    private Statement statement = null;

    private HashMap<String, Integer> songs;

    public DatabaseManager() throws SQLException
    {
        connection = DriverManager.getConnection(DATABASE_URL);
        statement = connection.createStatement();
        songs = new HashMap<>();
        artistsTableManager = new ArtistsTableManager();
        songsTableManager = new SongsTableManager();
        //uniqueTrackManager = new UniqueTrackTableManager();
        //listenRecordManager = new ListenTableManager();
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

    }

    @Override
    public void cleanTable(DataSource dataSource) throws SQLException
    {
        ArrayList<String> queries = new ArrayList<>();
        if(dataSource == DataSource.LISTENS)
        {
            queries.add("DELETE FROM " + LISTEN_RECORD_TABLE);
            queries.add("DELETE FROM " + DATE_RECORD_TABLE);
            queries.add("DELETE FROM " + TIME_RECORD_TABLE);
            queries.add("DELETE FROM " + USER_TABLE);
        }
        else{
            queries.add("DELETE FROM " + ARTISTS_TABLE);
            queries.add("DELETE FROM " + SONGS_TABLE);
        }
        for(String q : queries){
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
}
