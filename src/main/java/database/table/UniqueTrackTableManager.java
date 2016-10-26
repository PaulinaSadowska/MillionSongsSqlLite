package database.table;

import dataObjects.oldScheme.UniqueTrack;
import database.DatabaseManagerOld;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class UniqueTrackTableManager implements ITableManager<UniqueTrack>
{
    @Override
    public boolean createTable(Statement statement)
    {
        final String createListenRecordQuery =
                "CREATE TABLE IF NOT EXISTS " + DatabaseManagerOld.UNIQUE_TRACKS_TABLE + " (performanceId varchar(18), songId varchar(18), " +
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

    @Override
    public void insertRecords(List<UniqueTrack> records, Connection connection)
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
    public List<UniqueTrack> selectAll(Statement statement)
    {
        List<UniqueTrack> uniqueTracks = new LinkedList<>();
        try {
            ResultSet result = statement.executeQuery("SELECT * FROM " + DatabaseManagerOld.UNIQUE_TRACKS_TABLE);
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

    @Override
    public boolean insertRecord(UniqueTrack uniqueTrack, Connection connection)
    {
        try {
            PreparedStatement prepStmt = connection.prepareStatement(
                    "insert into " + DatabaseManagerOld.UNIQUE_TRACKS_TABLE + " values (?, ?, ?, ?);");
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
}
