package database.table;

import dataObjects.oldScheme.ListenRecord;
import database.DatabaseManagerOld;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class ListenTableManager implements ITableManager<ListenRecord>
{
    @Override
    public boolean createTable(Statement statement)
    {
        final String createListenRecordQuery =
                "CREATE TABLE IF NOT EXISTS " + DatabaseManagerOld.LISTEN_RECORD_TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, songId varchar(18), userId varchar(18), timestamp INTEGER);";
        try {
            statement.execute(createListenRecordQuery);
        } catch (SQLException e) {
            System.err.println("Error while creating record table");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void insertRecords(List<ListenRecord> records, Connection connection)
    {
        try
        {
            connection.setAutoCommit(false);
            records.forEach(s -> insertRecord(s, connection));
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<ListenRecord> selectAll(Statement statement)
    {
        List<ListenRecord> listenRecords = new LinkedList<ListenRecord>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM " + DatabaseManagerOld.LISTEN_RECORD_TABLE);
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

    @Override
    public boolean insertRecord(ListenRecord listenRecord, Connection connection) {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into " + DatabaseManagerOld.LISTEN_RECORD_TABLE + " values (NULL, ?, ?, ?);");
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
}
