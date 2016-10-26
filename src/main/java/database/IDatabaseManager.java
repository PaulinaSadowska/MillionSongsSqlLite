package database;

import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;

import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public interface IDatabaseManager
{

    boolean createListenRecordTable();

    boolean createUniqueTrackTable();

    List<ListenRecord> selectListenRecord();

    List<UniqueTrack> selectUniqueTracks();

    void insertUniqueTracksData(List<UniqueTrack> bulkedData);

    void insertListensRecord(List<ListenRecord> bulkedData);

    void dropTable(String tableName);

}
