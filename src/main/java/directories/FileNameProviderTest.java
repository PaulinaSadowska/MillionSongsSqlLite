package directories;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class FileNameProviderTest implements IFileNameProvider
{

    @Override
    public String getTripletsFileName()
    {
        return "triplets_small_sample.txt";
    }

    @Override
    public String getSongsFileName()
    {
        return "unique_tracks_small.txt";
    }
}

