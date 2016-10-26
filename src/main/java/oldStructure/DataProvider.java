package oldStructure;

import dataObjects.oldScheme.ListenRecord;
import dataObjects.oldScheme.UniqueTrack;
import directories.IFileNameProvider;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class DataProvider
{
    private IFileNameProvider _FileNameProvider;
    private DatabaseManager _DatabaseManager;

    private static final int BULK_SIZE = 500;

    public DataProvider(IFileNameProvider fileNameProvider){
        this._FileNameProvider = fileNameProvider;
        this._DatabaseManager = new DatabaseManager();
    }

    public void insertListensData()
    {
        _DatabaseManager.dropTable(DatabaseManager.LISTEN_RECORD_TABLE);
        _DatabaseManager.createListenRecordTable();
        List<ListenRecord> bulkedData = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(_FileNameProvider.getTripletsFileName()))) {
            stream.forEach(s -> {
                bulkedData.add(new ListenRecord(s));
                if (bulkedData.size() > BULK_SIZE)
                {
                    _DatabaseManager.insertListensRecord(bulkedData);
                    bulkedData.clear();
                }
            });
            if(bulkedData.size()>0)
            {
                _DatabaseManager.insertListensRecord(bulkedData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertUniqueTracksData()
    {
        _DatabaseManager.dropTable(DatabaseManager.UNIQUE_TRACKS_TABLE);
        _DatabaseManager.createUniqueTrackTable();
        List<UniqueTrack> bulkedData = new ArrayList<>();
        try (Stream<String> stream = Files.lines(Paths.get(_FileNameProvider.getSongsFileName()), StandardCharsets.ISO_8859_1)) {
            stream.forEach(s -> {
                bulkedData.add(new UniqueTrack(s));
                if (bulkedData.size() > BULK_SIZE)
                {
                    _DatabaseManager.insertUniqueTracksData(bulkedData);
                    bulkedData.clear();
                }
            });
            if(bulkedData.size()>0)
            {
                _DatabaseManager.insertUniqueTracksData(bulkedData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
