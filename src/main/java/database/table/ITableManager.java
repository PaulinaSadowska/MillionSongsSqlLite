package database.table;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public interface ITableManager<T>
{
    boolean createTable(Statement statement);
    void insertRecords(List<T> records, Connection connection);
    boolean insertRecord(T record, Connection connection);
    List<T> selectAll(Statement statement);
}
