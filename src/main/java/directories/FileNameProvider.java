package directories;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class FileNameProvider implements IFileNameProvider
{
    private final String LISTENS_RECORD_FILENAME = "triplets_sample_20p.txt";
    private final String UNIQUE_TRACKS_FILENAME = "unique_tracks.txt";

    public String getTripletsFileName()
    {
        return LISTENS_RECORD_FILENAME;
    }

    public String getSongsFileName()
    {
        return UNIQUE_TRACKS_FILENAME;
    }
}
