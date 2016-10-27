package database;

import dataObjects.DataSource;
import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;

import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public interface IDatabaseManager
{

    void insertUniqueTrackData(UniqueTrack track);

    void insertListenRecord(ListenRecord record);

    void cleanTable(DataSource dataSource) throws SQLException;

    void setAutocommit(boolean isAutocommit) throws SQLException;

    void commit() throws SQLException;

    void rollback() throws SQLException;

}
