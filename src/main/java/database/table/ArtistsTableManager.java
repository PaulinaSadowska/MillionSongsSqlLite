package database.table;

import dataObjects.newScheme.Artist;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 27.10.2016.
 */
public class ArtistsTableManager implements ITableManager<Artist>
{
    @Override
    public void insertRecord(Artist record, Connection connection) throws SQLException
    {
        PreparedStatement prepStmt = connection.prepareStatement(
            "insert into " + DatabaseManager.ARTISTS_TABLE + " values (NULL, ?);");
        prepStmt.setString(1, record.getArtist());
        prepStmt.execute();
        prepStmt.close();
    }
}
