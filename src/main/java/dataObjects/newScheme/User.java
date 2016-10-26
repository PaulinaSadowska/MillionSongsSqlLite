package dataObjects.newScheme;

import dataObjects.IndexManager;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class User
{
    private int userId;
    private String userOldId;

    public User(){

    }

    public User(String userOldId){
        this.userId = IndexManager.getInstance().getAvailableUserId();
        this.userOldId = userOldId;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getUserOldId()
    {
        return userOldId;
    }
}
