import dataObjects.ListenRecord;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DatabaseManager
{
    public static final String DATABASE_URL = "jdbc:sqlite:million.songs.standard.database";

    private static final String LISTEN_RECORD_TABLE = "listenRecord";

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
            System.err.println("Blad przy tworzeniu tabeli");
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
}
