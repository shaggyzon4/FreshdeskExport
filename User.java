import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User {
	
	    private String company_id; 
	    private String external_id;
	    private String phone;
	    private String fb_profile_id;
	    private String deleted;
	    private String id; 
	    private String twitter_id;
	    private String time_zone;
	    private String updated_at;
	    private String address;
	    private String email;
	    private String description;
	    private String name;
	    private String job_title;
	    private String active;
	    private String created_at;
	    private String language;
	    private String helpdesk_agent;
	    private String customer_id;
	    private String mobile;
	    
	    public String getCompany_id ()
	    {
	        return company_id;
	    }

	    public void setCompany_id (String company_id)
	    {
	        this.company_id = company_id;
	    }

	    public String getExternal_id ()
	    {
	        return external_id;
	    }

	    public void setExternal_id (String external_id)
	    {
	        this.external_id = external_id;
	    }

	    public String getPhone ()
	    {
	        return phone;
	    }

	    public void setPhone (String phone)
	    {
	        this.phone = phone;
	    }

	    public String getFb_profile_id ()
	    {
	        return fb_profile_id;
	    }

	    public void setFb_profile_id (String fb_profile_id)
	    {
	        this.fb_profile_id = fb_profile_id;
	    }

	    public String getDeleted ()
	    {
	        return deleted;
	    }

	    public void setDeleted (String deleted)
	    {
	        this.deleted = deleted;
	    }

	    public String getId ()
	    {
	        return id;
	    }

	    public void setId (String id)
	    {
	        this.id = id;
	    }

	    public String getTwitter_id ()
	    {
	        return twitter_id;
	    }

	    public void setTwitter_id (String twitter_id)
	    {
	        this.twitter_id = twitter_id;
	    }

	    public String getTime_zone ()
	    {
	        return time_zone;
	    }

	    public void setTime_zone (String time_zone)
	    {
	        this.time_zone = time_zone;
	    }

	    public String getUpdated_at ()
	    {
	        return updated_at;
	    }

	    public void setUpdated_at (String updated_at)
	    {
	        this.updated_at = updated_at;
	    }

	    public String getAddress ()
	    {
	        return address;
	    }

	    public void setAddress (String address)
	    {
	        this.address = address;
	    }

	    public String getEmail ()
	    {
	        return email;
	    }

	    public void setEmail (String email)
	    {
	        this.email = email;
	    }

	    public String getDescription ()
	    {
	        return description;
	    }

	    public void setDescription (String description)
	    {
	        this.description = description;
	    }

	    public String getName ()
	    {
	        return name;
	    }

	    public void setName (String name)
	    {
	        this.name = name;
	    }

	    public String getJob_title ()
	    {
	        return job_title;
	    }

	    public void setJob_title (String job_title)
	    {
	        this.job_title = job_title;
	    }

	    public String getActive ()
	    {
	        return active;
	    }

	    public void setActive (String active)
	    {
	        this.active = active;
	    }

	    public String getCreated_at ()
	    {
	        return created_at;
	    }

	    public void setCreated_at (String created_at)
	    {
	        this.created_at = created_at;
	    }

	    public String getLanguage ()
	    {
	        return language;
	    }

	    public void setLanguage (String language)
	    {
	        this.language = language;
	    }

	    public String getHelpdesk_agent ()
	    {
	        return helpdesk_agent;
	    }

	    public void setHelpdesk_agent (String helpdesk_agent)
	    {
	        this.helpdesk_agent = helpdesk_agent;
	    }

	    public String getCustomer_id ()
	    {
	        return customer_id;
	    }

	    public void setCustomer_id (String customer_id)
	    {
	        this.customer_id = customer_id;
	    }

	    public String getMobile ()
	    {
	        return mobile;
	    }

	    public void setMobile (String mobile)
	    {
	        this.mobile = mobile;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [company_id = "+company_id+", external_id = "+external_id+", phone = "+phone+", fb_profile_id = "+fb_profile_id+", custom_field = "+custom_field+", deleted = "+deleted+", id = "+id+", twitter_id = "+twitter_id+", time_zone = "+time_zone+", updated_at = "+updated_at+", address = "+address+", email = "+email+", description = "+description+", name = "+name+", job_title = "+job_title+", active = "+active+", created_at = "+created_at+", language = "+language+", helpdesk_agent = "+helpdesk_agent+", customer_id = "+customer_id+", mobile = "+mobile+"]";
	    }
	    
	    public void toData()
	    {
	    	Connection conn = null;
	    	String sql = null;
	    	PreparedStatement pst = null;
	    	
	    	try{

	    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    	String url = "jdbc:sqlserver://localhost;databasename=freshdesk;integratedsecurity=true;";
	    	conn=DriverManager.getConnection(url);
	    	
	    	// begin insertion
	    	
	    	conn.setAutoCommit(true);

	    	sql = "INSERT INTO contacts(id, company_id, name, email, phone, description, time_zone, created_at, helpdesk_agent) " 
	    	+ " VALUES (?,?,?,?,?,?,?,?,?);";
	    	pst = conn.prepareStatement(sql);
	    	
	    	//ticket attachment files are written in storeToDisk() method, to C:\bruce\ticketAttachmentFiles\display_id\original_email_in;
	    	pst.setString(1,id);
	    	pst.setString(2,company_id);
	    	pst.setString(3, name);
		    pst.setString(4, email);
		    pst.setString(5, phone);
			pst.setString(6, description);
			pst.setString(7, time_zone);
			pst.setString(8,created_at); 
			pst.setString(9, helpdesk_agent);
			pst.execute();
			System.out.println("pst.execute was successful!");
	    	
	    	}
	    	catch (SQLException e)
	    	{
	    		Logger lgr = Logger.getLogger(PreparedStatement.class.getName());
	    		lgr.log(Level.SEVERE, e.getMessage(), e);
	    	}
	    	catch (Exception e)
	    	{
	    		System.out.println("Error in data connection: " + e);
	    	}
	    	
	    	finally
	    	{
	    		if(pst!=null)
	    		{
	    			try
	    			{
	    				pst.close();
	    			}
	    			catch (Exception ex)
	    			{
	    				System.out.println("Exception in pst.close: " + ex);
	    			}
	    		} //end if

	    	} // end try/catch

	    } // end toData
} // end class
