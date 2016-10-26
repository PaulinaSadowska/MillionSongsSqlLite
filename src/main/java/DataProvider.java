import dataObjects.ListenRecord;
import directories.IFileNameProvider;

import java.io.IOException;
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

    public DataProvider(IFileNameProvider fileNameProvider, DatabaseManager databaseManager){
        this._FileNameProvider = fileNameProvider;
        this._DatabaseManager = databaseManager;
    }

    public void insertListensData()
    {
        try (Stream<String> stream = Files.lines(Paths.get(_FileNameProvider.getTripletsFileName()))) {
            stream.forEach(s -> {
                ListenRecord data = new ListenRecord(s);
                _DatabaseManager.insertListenRecord(data);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
