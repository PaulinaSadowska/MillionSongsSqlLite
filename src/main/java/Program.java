import database.DatabaseManager;
import database.DatabaseManagerOld;
import directories.FileNameProvider;
import directories.FileNameProviderTest;
import DataProvider.DataProvider;

import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Program
{
    public static void main( String args[] ){

        try
        {
            DataProvider dataProvider = new DataProvider(new FileNameProviderTest(), new DatabaseManager());

            System.out.println("inserting tracks...");
            long currentTime = System.currentTimeMillis();
            dataProvider.insertUniqueTracksData();
            System.out.println("inserting tracks took: " + (System.currentTimeMillis() - currentTime) + "ms");

            System.out.println("inserting listens...");
            currentTime = System.currentTimeMillis();
            dataProvider.insertListensData();
            System.out.println("inserting listens took: " + (System.currentTimeMillis() - currentTime) + "ms");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
}
