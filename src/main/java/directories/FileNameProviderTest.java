package directories;

import oldStructure.Table;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class FileNameProviderTest implements IFileNameProvider
{
    private final String LISTENS_RECORD_SMALL_FILENAME = "triplets_small_sample.txt";
    private final String UNIQUE_TRACKS_SMALL_FILENAME = "unique_tracks_small.txt";

    @Override
    public String getTripletsFileName()
    {
        return LISTENS_RECORD_SMALL_FILENAME;
    }

    @Override
    public String getSongsFileName()
    {
        return UNIQUE_TRACKS_SMALL_FILENAME;
    }
}

