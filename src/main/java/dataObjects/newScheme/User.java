package dataObjects.newScheme;

/**
 * Created by Paulina Sadowska on 26.10.2016.
 */
public class User
{
    private int userId;
    private String userOldId;
    private boolean isNew;

    public User(int userId, String userOldId)
    {
        this(userId, userOldId, true);
    }

    public User(int userId, String userOldId, boolean isNew)
    {
        this.userId = userId;
        this.userOldId = userOldId;
        this.isNew = isNew;
    }

    public int getUserId()
    {
        return userId;
    }

    public String getUserOldId()
    {
        return userOldId;
    }

    public boolean isNew()
    {
        return isNew;
    }
}
