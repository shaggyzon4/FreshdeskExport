import java.io.*;
import java.net.URL;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.csvreader.CsvWriter;

public class Attachments
{
	
    private String id;

    private String content_file_name;

    private String content_content_type;

    private String attachment_url;

    private String updated_at;

    private String created_at;

    private long content_file_size;


    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getContent_file_name ()
    {
        return content_file_name;
    }

    public void setContent_file_name (String content_file_name)
    {
        this.content_file_name = content_file_name;
    }

    public String getContent_content_type ()
    {
        return content_content_type;
    }

    public void setContent_content_type (String content_content_type)
    {
        this.content_content_type = content_content_type;
    }

    public String getAttachment_url ()
    {
        return attachment_url;
    }

    public void setAttachment_url (String attachment_url)
    {
        this.attachment_url = attachment_url;
    }

    public String getUpdated_at ()
    {
        return updated_at;
    }

    public void setUpdated_at (String updated_at)
    {
        this.updated_at = updated_at;
    }

    public String getCreated_at ()
    {
        return created_at;
    }

    public void setCreated_at (String created_at)
    {
        this.created_at = created_at;
    }

    public long getContent_file_size ()
    {
        return content_file_size;
    }

    public void setContent_file_size (long content_file_size)
    {
        this.content_file_size = content_file_size;
    }
    
    public void toCSV(String display_id, String ticket_id, String note_id)
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

    	sql = "INSERT INTO attachments(id, ticketid, ticket_display_id, content_file_name, content_content_type, attachment_url, updated_at, "
    			+ "created_at, content_file_size, note_id) " + " VALUES (?,?,?,?,?,?,?,?,?,?);";
    	pst = conn.prepareStatement(sql);
    	
    	//ticket attachment files are written in storeToDisk() method, to C:\bruce\ticketAttachmentFiles\display_id\original_email_in;
    	pst.setString(1,this.getId());
    	
    	pst.setString(2,ticket_id);
    	pst.setInt(3, Integer.parseInt(display_id));
	    pst.setString(4, this.getContent_file_name());
	    pst.setString(5,this.getContent_content_type());

		pst.setString(6, this.getAttachment_url());
		pst.setString(7, this.getUpdated_at());

		pst.setString(8,this.getCreated_at()); 
	  
		pst.setString(9,((Long)this.getContent_file_size()).toString());
		pst.setString(10, note_id);

     //   System.out.println(pst.toString());
		pst.execute();
		System.out.println("pst.execute was successful!");
    	
    	//stmt.execute(sql);

    	//conn.close();
    	
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
    	
    	String outputFile = "C:\\bruce\\attachmentExport.csv";
    	String physicalLocation = "C:\\bruce\\ticketAttachmentFiles\\"+ display_id+"\\"+ note_id;
    	boolean alreadyExists = new File(outputFile).exists();
    	System.out.println(alreadyExists);

    	try{
    		
    	    CsvWriter writer = new CsvWriter(new FileWriter(outputFile, true),',');
    	    if(!alreadyExists) // write column headers for new file
    	    {
    	    	writer.write("Ticket Number");
    	    	writer.write("Ticket ID");
    	    	writer.write("Note Id");
    	    	writer.write("Attachment ID");
    	    	writer.write("Content Type");
    	    	writer.write("File name");
    	    	writer.write("Creation Date");
    	    	writer.write("File size");
    	    	writer.write("Physical location");
    	    	writer.endRecord();
    	    }
    	    //write records to file
    	    writer.write(display_id); // ticket number
    	    writer.write(ticket_id);
    	    writer.write(note_id);
    	    writer.write(this.getId()); // attachment id
    	    writer.write(this.getContent_content_type());
    	    writer.write(this.getContent_file_name());
    	    writer.write(this.getCreated_at());
    	    writer.write(Long.toString(this.getContent_file_size()));
    	    writer.write(physicalLocation); 
    	    writer.endRecord();
    	    writer.close();
    	}
    	catch (IOException e){
    		System.out.println("Exception in class Attachments, in toCSV method: " + e);
    	}
    	this.storeAttachmentToDisk(display_id, note_id, this.getAttachment_url());
    } // end toCSV
    
    private void storeAttachmentToDisk(String display_id, String note_id, String fileUrl)
    {
    	String destinationFileURI;
    	
    	try{
    	  URL url = new URL(fileUrl);
    	  int slashIndex = fileUrl.lastIndexOf('/');
    	  int paramIndex = fileUrl.lastIndexOf('?');
    	  destinationFileURI = "C:\\bruce\\ticketAttachmentFiles\\"+ display_id+"\\"+ note_id;
    	  File dir = new File(destinationFileURI);
    	  
    	  dir.mkdirs();
          InputStream is = url.openStream();
          OutputStream os = new FileOutputStream(destinationFileURI+"\\"+fileUrl.substring(slashIndex+1,paramIndex));
    	  
    	  byte[] b = new byte[2048];
    	  int length;
    	  while ((length = is.read(b))!=-1)
    	  {
    		  os.write(b,0,length);
    	  }
    	  is.close();
    	  os.close();
    	}
    	catch(Exception e)
    	{
    		System.out.println("Error writing attachement to disk: " + e);
    	}
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", content_file_name = "+content_file_name+", content_content_type = "+content_content_type+", attachment_url = "+attachment_url+", updated_at = "+updated_at+", created_at = "+created_at+", content_file_size = "+content_file_size+"]";
    }

    
}