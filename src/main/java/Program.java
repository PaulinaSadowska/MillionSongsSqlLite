import dataObjects.ListenRecord;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class Program
{
    public static void main( String args[] ){
        DataManager dataManager = new DataManager();
        dataManager.insertListenRecord(new ListenRecord("b80344d063b5ccb3212f76538f3d9e43d87dca9e<SEP>SOUKOKB12ABGSDFGS8<SEP>1043412324"));
        dataManager.selectListenRecord();
    }
}
