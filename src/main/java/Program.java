import dataObjects.ListenRecord;
import directories.FileNameProviderTest;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Program
{
    public static void main( String args[] ){
        DatabaseManager dataManager = new DatabaseManager();
        DataProvider dataProvider = new DataProvider(new FileNameProviderTest(), dataManager);
        dataProvider.insertListensData();
        dataManager.selectListenRecord();
    }
}
