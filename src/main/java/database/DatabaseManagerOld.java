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

        public DatabaseManagerOld()
        {
            try {
                connection = DriverManager.getConnection(DATABASE_URL);
                statement = connection.createStatement();
                System.out.println("Opened database successfully");
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
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
        public void insertUniqueTracksData(List<UniqueTrack> bulkedData)
        {
            uniqueTrackManager.insertRecords(bulkedData, connection);
        }

        @Override
        public void insertListensRecord(List<ListenRecord> bulkedData)
        {
            listenRecordManager.insertRecords(bulkedData, connection);
        }

        @Override
        public void dropTable(String tableName)
        {
            final String dropTableQuery =
                    "DROP TABLE " + tableName;
            try {
                statement.execute(dropTableQuery);
            } catch (SQLException e) {
                System.err.println("Error while creating tracks table");
                e.printStackTrace();
            }
        }

}
