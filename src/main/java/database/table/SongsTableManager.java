package database.table;

import dataObjects.newScheme.Song;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 27.10.2016.
 */
public class SongsTableManager implements ITableManager<Song>
{
    @Override
    public void insertRecord(Song record, Connection connection) throws SQLException
    {
        PreparedStatement prepStmt = connection.prepareStatement(
                "insert into " + DatabaseManager.SONGS_TABLE + " values (NULL, ?, ?, ?, ?, ?);");
        prepStmt.setInt(1, record.getSongId());
        prepStmt.setString(2, record.getOldSongId());
        prepStmt.setString(3, record.getOldPerformanceId());
        prepStmt.setString(4, record.getArtist());
        prepStmt.setString(5, record.getTitle());
        prepStmt.execute();
        prepStmt.close();
    }
}
