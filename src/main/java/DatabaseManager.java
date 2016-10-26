import dataObjects.ListenRecord;
import dataObjects.UniqueTrack;

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

    public DatabaseManager()
    {
        try {
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();
            System.out.println("Opened database successfully");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }

    public boolean createListenRecordTable()  {
        final String createListenRecordQuery =
                "CREATE TABLE IF NOT EXISTS " + LISTEN_RECORD_TABLE + " (songId varchar(18), userId varchar(18), timestamp INTEGER);";
        try {
            statement.execute(createListenRecordQuery);
        } catch (SQLException e) {
            System.err.println("Error while creating record table");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createUniqueTrackTable()  {
        final String createListenRecordQuery =
                "CREATE TABLE IF NOT EXISTS " + UNIQUE_TRACKS_TABLE + " (performanceId varchar(18), songId varchar(18), " +
                        "artist varchar(80), title varchar(80));";
        try {
            statement.execute(createListenRecordQuery);
        } catch (SQLException e) {
            System.err.println("Error while creating tracks table");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertListenRecord(ListenRecord listenRecord) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into " + LISTEN_RECORD_TABLE + " values (?, ?, ?);");
            prepStmt.setString(1, listenRecord.getSongId());
            prepStmt.setString(2, listenRecord.getUserId());
            prepStmt.setInt(3, listenRecord.getTimestamp());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Error while inserting listen record");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<ListenRecord> selectListenRecord() {
        List<ListenRecord> listenRecords = new LinkedList<ListenRecord>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM " + LISTEN_RECORD_TABLE);
            while(result.next()) {
                String songId = result.getString("songId");
                String userId = result.getString("userId");
                int timestamp = result.getInt("timestamp");
                listenRecords.add(new ListenRecord(songId, userId, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return listenRecords;
    }

    public boolean insertUniqueTracksData(UniqueTrack uniqueTrack)
    {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into " + UNIQUE_TRACKS_TABLE + " values (?, ?, ?, ?);");
            prepStmt.setString(1, uniqueTrack.getPerformanceId());
            prepStmt.setString(2, uniqueTrack.getSongId());
            prepStmt.setString(3, uniqueTrack.getArtist());
            prepStmt.setString(4, uniqueTrack.getTitle());
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Error while inserting listen record");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<UniqueTrack> selectUniqueTracks() {
        List<UniqueTrack> uniqueTracks = new LinkedList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM " + UNIQUE_TRACKS_TABLE);
            while(result.next()) {
                String perfId = result.getString("performanceId");
                String songId = result.getString("songId");
                String userId = result.getString("artist");
                String title = result.getString("title");
                uniqueTracks.add(new UniqueTrack(perfId, songId, userId, title));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return uniqueTracks;
    }


    public void dropTable(String tableName)
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
