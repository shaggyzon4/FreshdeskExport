

public class Companies
{
    private Company company;

    public Company  getCompany ()
    {
        return company;
    }

    public void setUser (Company company)
    {
        this.company = company;
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
        return "ClassPojo [company = "+company+"]";
    }
}