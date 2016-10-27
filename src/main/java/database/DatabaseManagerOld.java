package database;

import dataObjects.DataSource;
import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;
import database.table.ITableManager;
import database.table.ListenTableManager;
import database.table.UniqueTrackTableManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DatabaseManagerOld implements IDatabaseManager
{

    public static final String DATABASE_URL = "jdbc:sqlite:million.songs.standard.database";

    public static final String LISTEN_RECORD_TABLE = "listenRecord";
    public static final String UNIQUE_TRACKS_TABLE = "uniqueTracks";

    private Connection connection = null;
    private Statement statement = null;
    private ITableManager<UniqueTrack> uniqueTrackManager;
    private ITableManager<ListenRecord> listenRecordManager;

    public DatabaseManagerOld() throws SQLException
    {
        connection = DriverManager.getConnection(DATABASE_URL);
        statement = connection.createStatement();
        uniqueTrackManager = new UniqueTrackTableManager();
        listenRecordManager = new ListenTableManager();
    }

    @Override
    public void insertUniqueTrackData(UniqueTrack track)
    {
        try
        {
            uniqueTrackManager.insertRecord(track, connection);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void insertListenRecord(ListenRecord record)
    {
        try
        {
            listenRecordManager.insertRecord(record, connection);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanTable(DataSource dataSource) throws SQLException
    {
        String cleanTableQuery = "DELETE FROM ";
        if(dataSource == DataSource.LISTENS)
        {
             cleanTableQuery += LISTEN_RECORD_TABLE;
        }
        else{
            cleanTableQuery += UNIQUE_TRACKS_TABLE;
        }
        statement.execute(cleanTableQuery);
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
