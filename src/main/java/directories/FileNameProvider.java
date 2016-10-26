package directories;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class FileNameProvider implements IFileNameProvider
{

    @Override
    public String getTripletsFileName()
    {
        return "/Users/palka/Desktop/triplets_sample_20p.txt";
    }

    @Override
    public String getSongsFileName()
    {
        return "/Users/palka/Desktop/unique_tracks.txt";
    }
}
