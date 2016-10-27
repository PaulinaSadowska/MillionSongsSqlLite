package database.table;

import dataObjects.newScheme.User;
import database.DatabaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Paulina Sadowska on 27.10.2016.
 */
public class UserTableManager implements ITableManager<User>
{
    @Override
    public void insertRecord(User record, Connection connection) throws SQLException
    {
        PreparedStatement prepStmt = connection.prepareStatement(
                "insert into " + DatabaseManager.USER_TABLE + " values (?, ?);");
        prepStmt.setInt(1, record.getUserId());
        prepStmt.setString(2, record.getUserOldId());
        prepStmt.execute();
        prepStmt.close();
    }
}
