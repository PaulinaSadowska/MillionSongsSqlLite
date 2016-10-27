package database.table;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public interface ITableManager<T>
{
    void insertRecord(T record, Connection connection) throws SQLException;
}
