package DataProvider;

import dataObjects.DataSource;
import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;
import database.IDatabaseManager;
import directories.IFileNameProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.stream.Stream;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DataProvider
{
    private IFileNameProvider _FileNameProvider;
    private IDatabaseManager _DatabaseManager;

    public DataProvider(IFileNameProvider fileNameProvider, IDatabaseManager databaseManager)
    {
        this._FileNameProvider = fileNameProvider;
        this._DatabaseManager = databaseManager;
    }

    public void insertListensData() throws SQLException
    {
        _DatabaseManager.cleanTable(DataSource.LISTENS);
        _DatabaseManager.setAutocommit(false);
        try (Stream<String> stream = Files.lines(Paths.get(_FileNameProvider.getTripletsFileName())))
        {
            stream.forEach(s -> _DatabaseManager.insertListenRecord(new ListenRecord(s)));
        } catch (IOException e)
        {
            _DatabaseManager.rollback();
            e.printStackTrace();
        }
        _DatabaseManager.commit();
        _DatabaseManager.setAutocommit(true);
    }

    public void insertUniqueTracksData() throws SQLException
    {
        _DatabaseManager.cleanTable(DataSource.TRACKS);
        _DatabaseManager.setAutocommit(false);
        try (Stream<String> stream = Files.lines(Paths.get(_FileNameProvider.getSongsFileName()), StandardCharsets.ISO_8859_1))
        {
            stream.forEach(s -> _DatabaseManager.insertUniqueTrackData(new UniqueTrack(s)));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        _DatabaseManager.commit();
        _DatabaseManager.setAutocommit(true);
    }

}
