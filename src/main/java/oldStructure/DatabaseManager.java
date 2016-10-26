package oldStructure;

import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;
import oldStructure.TableManager.ITableManager;
import oldStructure.TableManager.ListenTableManager;
import oldStructure.TableManager.UniqueTrackTableManager;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DatabaseManager
{
    public static final String DATABASE_URL = "jdbc:sqlite:million.songs.standard.database";

    public static final String LISTEN_RECORD_TABLE = "listenRecord";
    public static final String UNIQUE_TRACKS_TABLE = "uniqueTracks";

    private Connection connection = null;
    private Statement statement = null;
    private ITableManager<UniqueTrack> uniqueTrackManager;
    private ITableManager<ListenRecord> listenRecordManager;

    public DatabaseManager()
    {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
        uniqueTrackManager = new UniqueTrackTableManager();
        listenRecordManager = new ListenTableManager();
    }

    public boolean createListenRecordTable()  {
        return listenRecordManager.createTable(statement);
    }

    public boolean createUniqueTrackTable()  {
        return uniqueTrackManager.createTable(statement);
    }


    public List<ListenRecord> selectListenRecord() {
        return listenRecordManager.selectAll(statement);
    }

    public List<UniqueTrack> selectUniqueTracks() {
        return uniqueTrackManager.selectAll(statement);
    }

    public void insertUniqueTracksData(List<UniqueTrack> bulkedData)
    {
        uniqueTrackManager.insertRecords(bulkedData, connection);
    }

    public void insertListensRecord(List<ListenRecord> bulkedData)
    {
        listenRecordManager.insertRecords(bulkedData, connection);
    }

    protected void dropTable(String tableName)
    {
        final String dropTableQuery =
                "DROP TABLE " + tableName;
        try {
            statement.execute(dropTableQuery);
        } catch (SQLException e) {
            System.err.println("Error while creating tracks table");
            e.printStackTrace();
        }
    }
}
