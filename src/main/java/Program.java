import directories.FileNameProviderTest;
import oldStructure.DataProvider;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Program
{
    public static void main( String args[] ){
        DataProvider dataProvider = new DataProvider(new FileNameProviderTest());
        long currentTime = System.currentTimeMillis();
        dataProvider.insertUniqueTracksData();
        System.out.println("inserting tracks took: " + (System.currentTimeMillis() - currentTime) + "ms");
        currentTime = System.currentTimeMillis();
        dataProvider.insertListensData();
        System.out.println("inserting listens took: " + (System.currentTimeMillis() - currentTime) + "ms");
    }
}
