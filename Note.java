import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.csvreader.CsvWriter;

public class Note
{
    private String id;
    private String incoming;
    private String body;
    private String updated_at;
    private String source;
    private String body_html;
    private String created_at;
    private String user_id;
    private Attachments[] attachments;
    private String deleted;
    private String support_email;
    private String isPrivate;

    public String getId()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getIncoming ()
    {
        return incoming;
    }

    public void setIncoming (String incoming)
    {
        this.incoming = incoming;
    }

    public String getBody ()
    {
        return body;
    }

    public void setBody (String body)
    {
        this.body = body;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getSource ()
    {
        return source;
    }

    public void setSource (String source)
    {
        this.source = source;
    }

    public String getBody_html ()
    {
        return body_html;
    }

    public void setBody_html (String body_html)
    {
        this.body_html = body_html;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public String getUser_id ()
    {
        return user_id;
    }

    public void setUser_id (String user_id)
    {
        this.user_id = user_id;
    }

    public Attachments[] getAttachments ()
    {
        return this.attachments;
    }

    public void setAttachments (Attachments[] attachments)
    {
        this.attachments = attachments;
    }

    public String getDeleted ()
    {
        return deleted;
    }

    public void setDeleted (String deleted)
    {
        this.deleted = deleted;
    }

    public String getSupport_email ()
    {
        return support_email;
    }

    public void setSupport_email (String support_email)
    {
        this.support_email = support_email;
    }

    public String getPrivate ()
    {
        return isPrivate;
    }

    public void setPrivate (String isPrivate)
    {
        this.isPrivate = isPrivate;
    }
    
    public void toCSV(String display_id, String ticket_id)
    {
    	Connection conn = null;
    	Statement stmt = null;
    	String sql = null;
    	PreparedStatement pst = null;
    	
    	try{
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    	String url = "jdbc:sqlserver://localhost;databasename=freshdesk;integratedsecurity=true;";
    	conn=DriverManager.getConnection(url);
    	
    	// begin insertion
    	
    	conn.setAutoCommit(true);
    	stmt=conn.createStatement();

    	sql = "INSERT INTO notes(id, ticketnumber, ticketid, incoming, body, updated_at, source, body_html,"	
    			+ "created_at, user_id, deleted, support_email, isPrivate) " + " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
    	pst = conn.prepareStatement(sql);
    	
    	//ticket attachment files are written in storeToDisk() method, to C:\bruce\ticketAttachmentFiles\display_id\original_email_in;
    	pst.setString(1,this.getId());
    	pst.setString(2,ticket_id);
    	pst.setInt(3, Integer.parseInt(display_id));
	    pst.setString(4, this.getIncoming());
	    pst.setString(5,this.getBody());
		pst.setString(6, this.getUpdated_at());
		pst.setString(7, this.getSource());
		pst.setString(8,this.getBody_html()); 
		pst.setString(9,this.getCreated_at());
		pst.setString(10, this.getUser_id());
		pst.setString(11, this.getDeleted());
		pst.setString(12, this.getSupport_email());
		pst.setString(13, this.getPrivate());
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
    		if(conn!=null)
    		{
    			try
    			{
    				conn.close();	
    			} 
    			catch (SQLException ex)
    			{
    				System.out.println("Exception in conn.close: " + ex);
    			}
    		}
    	}

    	String outputFile = "C:\\bruce\\noteExport.csv";
    	boolean alreadyExists = new File(outputFile).exists();
    	try{
    		
    	    CsvWriter writer = new CsvWriter(new FileWriter(outputFile, true),',');
    	    if(!alreadyExists) // write column headers for new file
    	    {
    	    	writer.write("Ticket Number");
    	    	writer.write("Ticket Id");
    	    	writer.write("Note ID");
    	    	writer.write("Body");
    	    	writer.write("HTML Body");
    	    	writer.write("Creation Date");
    	    	writer.write("User ID");
    	    	writer.write("Has attachment?");
    	    	writer.write("Is Private?");
    	    	writer.endRecord();
    	    }
    	    //write records to file
    	    writer.write(display_id);
    	    writer.write(ticket_id);
    	    writer.write(id);
    	    writer.write(this.getBody());
    	    writer.write(this.getBody_html());
    	    writer.write(this.getCreated_at());
    	    writer.write(this.getUser_id());
    	    writer.write(Boolean.toString(hasAttachments()));
    	    writer.write(this.getPrivate());
    	    writer.endRecord();
    	    // write note attachment files to disk
    	    for(int i=0;i<this.getAttachments().length;i++){
    	      this.getAttachments()[i].toCSV(display_id, ticket_id, id);
    	    }
    	    
    	    this.storeNoteToDisk(display_id,id);
    	}
    	catch (IOException e){
    		System.out.println("IO Exception in class Note, CSV output method (toCSV): " + e);
    	}
    	
    }
    
    private void storeNoteToDisk(String display_id, String id)
    {
    	String destinationFileURI="C:\\bruce\\ticketAttachmentFiles\\" +display_id + "\\" + this.id;
  	    File dir = new File(destinationFileURI);
  	    dir.mkdirs();
  	    destinationFileURI += "\\" +this.id + ".html";
    	
    	try{
    		
    		PrintWriter writer = new PrintWriter(destinationFileURI);
    		writer.println(this.body_html);
    		writer.flush();
    		writer.close();
    	}
    	catch (Exception e)
    	{
    		
    	}
    }
    
    private boolean hasAttachments()
    {
    	boolean hasAttachment=false;
    	if(this.getAttachments().length>0)
    		{
    		 hasAttachment=true;
    		}
    	return hasAttachment;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", incoming = "+incoming+", body = "+body+", updated_at = "+updated_at+", source = "+source+", body_html = "+body_html+", created_at = "+created_at+", user_id = "+user_id+", attachments = "+attachments+", deleted = "+deleted+", support_email = "+support_email+", private = "+ isPrivate+"]";
    }
}