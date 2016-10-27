package database.table;

import dataObjects.newScheme.TimeRecord;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 27.10.2016.
 */
public class TimeRecordTableManager implements ITableManager<TimeRecord>
{
    @Override
    public void insertRecord(TimeRecord record, Connection connection) throws SQLException
    {
        PreparedStatement prepStmt = connection.prepareStatement(
                "insert into " + DatabaseManager.TIME_RECORD_TABLE + " values (NULL, ?, ?, ?, ?, ?, ?);");
        prepStmt.setInt(1, record.getDateId());
        prepStmt.setInt(2, record.getMinute());
        prepStmt.setInt(3, record.getHour());
        prepStmt.setInt(4, record.getDay());
        prepStmt.setInt(5, record.getMonth());
        prepStmt.setInt(6, record.getYear());
        prepStmt.execute();
        prepStmt.close();
    }
}
