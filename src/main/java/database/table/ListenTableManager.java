package database.table;

import dataObjects.newScheme.Listen;
import dataObjects.oldScheme.ListenRecord;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 27.10.2016.
 */
public class ListenTableManager implements ITableManager<Listen>
{
    @Override
    public void insertRecord(Listen record, Connection connection) throws SQLException
    {
        PreparedStatement prepStmt = connection.prepareStatement(
                "insert into " + DatabaseManager.LISTEN_RECORD_TABLE + " values (NULL, ?, ?, ?);");
        prepStmt.setInt(1, record.getUserId());
        prepStmt.setInt(2, record.getSongId());
        prepStmt.setInt(2, record.getDateId());
        prepStmt.execute();
        prepStmt.close();
    }
}
