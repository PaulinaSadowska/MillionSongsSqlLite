import dataObjects.ListenRecord;
import dataObjects.UniqueTrack;
import directories.IFileNameProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DataProvider
{
    private IFileNameProvider _FileNameProvider;
    private DatabaseManager _DatabaseManager;

    public DataProvider(IFileNameProvider fileNameProvider){
        this._FileNameProvider = fileNameProvider;
        this._DatabaseManager = new DatabaseManager();
    }

    public void insertListensData()
    {
        _DatabaseManager.dropTable(DatabaseManager.LISTEN_RECORD_TABLE);
        _DatabaseManager.createListenRecordTable();
        try (Stream<String> stream = Files.lines(Paths.get(_FileNameProvider.getTripletsFileName()))) {
            stream.forEach(s -> _DatabaseManager.insertListenRecord(new ListenRecord(s)));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void insertUniqueTracksData()
    {
        _DatabaseManager.dropTable(DatabaseManager.UNIQUE_TRACKS_TABLE);
        _DatabaseManager.createUniqueTrackTable();
        try (Stream<String> stream = Files.lines(Paths.get(_FileNameProvider.getSongsFileName()), StandardCharsets.ISO_8859_1)) {
            stream.forEach(s -> _DatabaseManager.insertUniqueTracksData(new UniqueTrack(s)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        //_DatabaseManager.selectUniqueTracks();
    }

}
