package database;

import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;

import java.sql.SQLException;
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

    void insertUniqueTrackData(UniqueTrack track);

    void insertListenRecord(ListenRecord record);

    void dropTable(String tableName) throws SQLException;

    void setAutocommit(boolean isAutocommit) throws SQLException;

    void commit() throws SQLException;

}
