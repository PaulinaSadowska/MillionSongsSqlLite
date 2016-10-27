package database;

import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DatabaseManager implements IDatabaseManager
{

    @Override
    public boolean createListenRecordTable()
    {
        return false;
    }

    @Override
    public boolean createUniqueTrackTable()
    {
        return false;
    }

    @Override
    public List<ListenRecord> selectListenRecord()
    {
        return null;
    }

    @Override
    public List<UniqueTrack> selectUniqueTracks()
    {
        return null;
    }

    @Override
    public void insertUniqueTrackData(UniqueTrack track)
    {

    }

    @Override
    public void insertListenRecord(ListenRecord record)
    {

    }

    @Override
    public void dropTable(String tableName) throws SQLException
    {

    }

    @Override
    public void setAutocommit(boolean isAutocommit) throws SQLException
    {

    }

    @Override
    public void commit() throws SQLException
    {

    }
}
