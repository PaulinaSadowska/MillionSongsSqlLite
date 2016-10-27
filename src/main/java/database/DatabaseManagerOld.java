package database;

import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;
import database.table.ITableManager;
import database.table.ListenTableManager;
import database.table.UniqueTrackTableManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DatabaseManagerOld implements IDatabaseManager
{

        public static final String DATABASE_URL = "jdbc:sqlite:million.songs.standard.database";

        public static final String LISTEN_RECORD_TABLE = "listenRecord";
        public static final String UNIQUE_TRACKS_TABLE = "uniqueTracks";

        private Connection connection = null;
        private Statement statement = null;
        private ITableManager<UniqueTrack> uniqueTrackManager;
        private ITableManager<ListenRecord> listenRecordManager;

        public DatabaseManagerOld() throws SQLException
        {
            connection = DriverManager.getConnection(DATABASE_URL);
            statement = connection.createStatement();
            uniqueTrackManager = new UniqueTrackTableManager();
            listenRecordManager = new ListenTableManager();
        }

        @Override
        public boolean createListenRecordTable()  {
            return listenRecordManager.createTable(statement);
        }

        @Override
        public boolean createUniqueTrackTable()  {
            return uniqueTrackManager.createTable(statement);
        }

        @Override
        public List<ListenRecord> selectListenRecord() {
            return listenRecordManager.selectAll(statement);
        }

        @Override
        public List<UniqueTrack> selectUniqueTracks() {
            return uniqueTrackManager.selectAll(statement);
        }

    @Override
    public void insertUniqueTrackData(UniqueTrack track)
    {
        uniqueTrackManager.insertRecord(track, connection);
    }

    @Override
    public void insertListenRecord(ListenRecord record)
    {
        listenRecordManager.insertRecord(record, connection);
    }

    @Override
        public void dropTable(String tableName) throws SQLException
    {
            final String dropTableQuery =
                    "DROP TABLE " + tableName;
            statement.execute(dropTableQuery);
        }

    @Override
    public void setAutocommit(boolean isAutocommit) throws SQLException
    {
        connection.setAutoCommit(isAutocommit);
    }

    @Override
    public void commit() throws SQLException
    {
        connection.commit();
    }

}
