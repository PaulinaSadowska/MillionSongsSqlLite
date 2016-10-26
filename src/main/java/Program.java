import directories.FileNameProviderTest;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Program
{
    public static void main( String args[] ){
        DataProvider dataProvider = new DataProvider(new FileNameProviderTest());
        dataProvider.insertListensData();
        dataProvider.insertUniqueTracksData();
    }
}
