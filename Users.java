
public class Users
{
    private User user;

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public void toCSV(String id)
    {
    	//export notes to CSV
    	//this.user.toCSV(id);
    	System.out.println("stub for toCSV");
    }

    @Override
    public String toString()
    {
        return "ClassPojo [user = "+user+"]";
    }
}