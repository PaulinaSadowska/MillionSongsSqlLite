package database.table;

import dataObjects.newScheme.DateRecord;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 27.10.2016.
 */
public class DateRecordTableManager implements ITableManager<DateRecord>
{
    @Override
    public void insertRecord(DateRecord record, Connection connection) throws SQLException
    {
        PreparedStatement prepStmt = connection.prepareStatement(
                "insert into " + DatabaseManager.DATE_RECORD_TABLE + " values (?, ?);");
        prepStmt.setInt(1, record.getDateId());
        prepStmt.setLong(2, record.getTimestamp());
        prepStmt.execute();
        prepStmt.close();
    }
}
