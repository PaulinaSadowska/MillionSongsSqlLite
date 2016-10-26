package database;

import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;

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
    public void insertUniqueTracksData(List<UniqueTrack> bulkedData)
    {

    }

    @Override
    public void insertListensRecord(List<ListenRecord> bulkedData)
    {

    }

    @Override
    public void dropTable(String tableName)
    {

    }
}
