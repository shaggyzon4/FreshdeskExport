               import java.io.File;
               import java.io.FileWriter;
               import java.io.PrintWriter;
               import java.sql.*;
               import java.util.*;
               import java.util.logging.Level;
               import java.util.logging.Logger;

               import com.csvreader.CsvWriter;

               public class Helpdesk_ticket {
                   private String description_html;
                   private long product_id;
                   private String subject;
                   private String email_config_id;
                   private String requester_name;
                   private String frDueBy;
                   private String id;
                   private String due_by;
                   private String status_name;
                   private String description;
                   private String priority;
                   private String created_at;
                   private String source_name;
                   private Attachments[] attachments;
                   private String trained;
                   private String[] tags;
                   private String ticket_type;
                   private Cc_email cc_email;
                   private String isescalated;
                   private String status;
                   private String urgent;
                   private String delta;
                   private String group_id;
                   private String requester_status_name;
                   private String[] to_emails;
                   private String deleted;
                   private String fr_escalated;
                   private String updated_at;
                   private String source;
                   private String priority_name;
                   private String owner_id;
                   private String responder_id;
                   private String responder_name;
                   private Notes[] notes;
                   private String spam;
                   private String to_email;
                   private String display_id;
                   private String requester_id;


                   public String getDescription_html() {
                       return description_html;
                   }

                   public void setDescription_html(String description_html) {
                       this.description_html = description_html;
                   }

                   public long getProduct_id() {
                       return product_id;
                   }

                   public void setProduct_id(long product_id) {
                       this.product_id = product_id;
                   }

                   public String getSubject() {
                       return subject;
                   }

                   public void setSubject(String subject) {
                       this.subject = subject;
                   }

                   public String getEmail_config_id() {
                       return email_config_id;
                   }

                   public void setEmail_config_id(String email_config_id) {
                       this.email_config_id = email_config_id;
                   }

                   public String getRequester_name() {
                       return requester_name;
                   }

                   public void setRequester_name(String requester_name) {
                       this.requester_name = requester_name;
                   }

                   public String getFrDueBy() {
                       return frDueBy;
                   }

                   public void setFrDueBy(String frDueBy) {
                       this.frDueBy = frDueBy;
                   }

                   public String getId() {
                       return id;
                   }

                   public void setId(String id) {
                       this.id = id;
                   }

                   public String getDue_by() {
                       return due_by;
                   }

                   public void setDue_by(String due_by) {
                       this.due_by = due_by;
                   }

                   public String getStatus_name() {
                       return status_name;
                   }

                   public void setStatus_name(String status_name) {
                       this.status_name = status_name;
                   }

                   public String getDescription() {
                       return description;
                   }

                   public void setDescription(String description) {
                       this.description = description;
                   }

                   public String getPriority() {
                       return priority;
                   }

                   public void setPriority(String priority) {
                       this.priority = priority;
                   }

                   public String getCreated_at() {
                       return created_at;
                   }

                   public void setCreated_at(String created_at) {
                       this.created_at = created_at;
                   }

                   public String getSource_name() {
                       return source_name;
                   }

                   public void setSource_name(String source_name) {
                       this.source_name = source_name;
                   }

                   public Attachments[] getAttachments() {
                       return attachments;
                   }

                   public void setAttachments(Attachments[] attachments) {
                       this.attachments = attachments;
                   }

                   public String getTrained() {
                       return trained;
                   }

                   public void setTrained(String trained) {
                       this.trained = trained;
                   }

                   public String[] getTags() {
                       return tags;
                   }

                   public void setTags(String[] tags) {
                       this.tags = tags;
                   }

                   public String getTicket_type() {
                       return ticket_type;
                   }

                   public void setTicket_type(String ticket_type) {
                       this.ticket_type = ticket_type;
                   }

                   public Cc_email getCc_email() {
                       return cc_email;
                   }

                   public void setCc_email(Cc_email cc_email) {
                       this.cc_email = cc_email;
                   }

                   public String getIsescalated() {
                       return isescalated;
                   }

                   public void setIsescalated(String isescalated) {
                       this.isescalated = isescalated;
                   }

                   public String getStatus() {
                       return status;
                   }

                   public void setStatus(String status) {
                       this.status = status;
                   }

                   public String getUrgent() {
                       return urgent;
                   }

                   public void setUrgent(String urgent) {
                       this.urgent = urgent;
                   }

                   public String getDelta() {
                       return delta;
                   }

                   public void setDelta(String delta) {
                       this.delta = delta;
                   }

                   public String getGroup_id() {
                       return group_id;
                   }

                   public void setGroup_id(String group_id) {
                       this.group_id = group_id;
                   }

                   public String getRequester_status_name() {
                       return requester_status_name;
                   }

                   public void setRequester_status_name(String requester_status_name) {
                       this.requester_status_name = requester_status_name;
                   }

                   public String[] getTo_emails() {
                       return to_emails;
                   }

                   public void setTo_emails(String[] to_emails) {
                       this.to_emails = to_emails;
                   }

                   public String getDeleted() {
                       return deleted;
                   }

                   public void setDeleted(String deleted) {
                       this.deleted = deleted;
                   }

                   public String getFr_escalated() {
                       return fr_escalated;
                   }

                   public void setFr_escalated(String fr_escalated) {
                       this.fr_escalated = fr_escalated;
                   }

                   public String getUpdated_at() {
                       return updated_at;
                   }

                   public void setUpdated_at(String updated_at) {
                       this.updated_at = updated_at;
                   }

                   public String getSource() {
                       return source;
                   }

                   public void setSource(String source) {
                       this.source = source;
                   }

                   public String getPriority_name() {
                       return priority_name;
                   }

                   public void setPriority_name(String priority_name) {
                       this.priority_name = priority_name;
                   }

                   public String getOwner_id() {
                       return owner_id;
                   }

                   public void setOwner_id(String owner_id) {
                       this.owner_id = owner_id;
                   }

                   public String getResponder_id() {
                       return responder_id;
                   }

                   public void setResponder_id(String responder_id) {
                       this.responder_id = responder_id;
                   }

                   public String getResponder_name() {
                       return responder_name;
                   }

                   public void setResponder_name(String responder_name) {
                       this.responder_name = responder_name;
                   }

                   public Notes[] getNotes() {
                       return notes;
                   }

                   public void setNotes(Notes[] notes) {
                       this.notes = notes;
                   }

                   public String getSpam() {
                       return spam;
                   }

                   public void setSpam(String spam) {
                       this.spam = spam;
                   }

                   public String getTo_email() {
                       return to_email;
                   }

                   public void setTo_email(String to_email) {
                       this.to_email = to_email;
                   }

                   public String getDisplay_id() {
                       return display_id;
                   }

                   public void setDisplay_id(String display_id) {
                       this.display_id = display_id;
                   }

                   public String getRequester_id() {
                       return requester_id;
                   }

                   public void setRequester_id(String requester_id) {
                       this.requester_id = requester_id;
                   }

                   public void toCSV(String display_id, String id) {

                       Connection conn = null;
                       Statement stmt = null;
                       String sql = null;
                       PreparedStatement pst = null;

                       try {

                           Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                           String url = "jdbc:sqlserver://localhost;databasename=freshdesk;integratedsecurity=true;";
                           conn = DriverManager.getConnection(url);

                           // begin insertion

                           conn.setAutoCommit(true);
                           stmt = conn.createStatement();

                           sql = "INSERT INTO tickets(ticketnumber, ticketid, subject, description, descriptionhtml, creationdate, userid, username, hasattachments, ownerid, ccemailaddys) " +
                               " VALUES (?,?,?,?,?,?,?,?,?,?,?);";
                           pst = conn.prepareStatement(sql);

                           //ticket attachment files are written in storeToDisk() method, to C:\bruce\ticketAttachmentFiles\display_id\original_email_in;
                           pst.setString(1, this.getDisplay_id());
                           pst.setString(2, this.getId());
                           pst.setString(3, this.getSubject());
                           pst.setString(4, this.getDescription());
                           pst.setString(5, this.getDescription_html());
                           pst.setString(6, this.getCreated_at());
                           pst.setString(7, this.getRequester_id());
                           pst.setString(8, this.getRequester_name());
                           pst.setBoolean(9, this.hasAttachments());
                           pst.setString(10, this.getOwner_id());
                           pst.setString(11, this.getCc_email().toString());
                           System.out.println(pst.toString());
                           pst.execute();
                           System.out.println("pst.execute was successful!");

                           //stmt.execute(sql);

                           //conn.close();

                       } catch (SQLException e) {
                           Logger lgr = Logger.getLogger(PreparedStatement.class.getName());
                           lgr.log(Level.SEVERE, e.getMessage(), e);
                       } catch (Exception e) {
                           System.out.println("Error in data connection: " + e);
                       } finally {
                           if (pst != null) {
                               try {
                                   pst.close();
                               } catch (Exception ex) {
                                   System.out.println("Exception in pst.close: " + ex);
                               }
                           } //end if
                           if (conn != null) {
                               try {
                                   conn.close();
                               } catch (SQLException ex) {
                                   System.out.println("Exception in conn.close: " + ex);
                               }
                           }
                       }

                       String outputFile = "C:\\bruce\\ticketExport.csv";
                       boolean alreadyExists = new File(outputFile).exists();

                       try {

                           CsvWriter writer = new CsvWriter(new FileWriter(outputFile, true), ',');
                           if (!alreadyExists) // write column headers for new file
                           {

                               writer.write("TicketNumber");
                               writer.write("TicketId");
                               writer.write("Subject");
                               writer.write("Description");
                               writer.write("HTMLDescription");
                               writer.write("CreationDate");
                               writer.write("UserID");
                               writer.write("UserName");
                               writer.write("HasAttachment?");
                               writer.write("OwnerID");
                               writer.endRecord();
                           }
                           //write records to file
                           writer.write(this.getDisplay_id());
                           writer.write(this.getId());
                           writer.write(this.getSubject());
                           writer.write(this.getDescription());
                           writer.write(this.getDescription_html());
                           writer.write(this.getCreated_at());
                           writer.write(this.getRequester_id());
                           writer.write(this.getRequester_name());
                           writer.write(Boolean.toString(hasAttachments()));
                           writer.write(this.getOwner_id());
                           writer.endRecord();
                           writer.close();
                       } catch (Exception e) {
                           System.out.println("Error writing CSV to file in Helpdesk_ticket: ");
                       }
                       //export child objects to CSV

                       for (int i = 0; i < notes.length; i++) {
                           this.notes[i].toCSV(display_id, id);
                       } // end for
                       for (int i = 0; i < getAttachments().length; i++) {
                           this.getAttachments()[i].toCSV(display_id, id, "original_email_in");
                       }
                       this.storeToDisk(display_id, id);
                   }

                   private void storeToDisk(String display_id, String id) {
                       String destinationFileURI = "C:\\bruce\\ticketAttachmentFiles\\" + display_id + "\\" + "original_email_in";
                       File dir = new File(destinationFileURI);

                       dir.mkdirs();

                       destinationFileURI += "\\" + this.id + ".html";

                       try {

                           PrintWriter writer = new PrintWriter(destinationFileURI);
                           writer.println(this.subject);
                           writer.println(this.description_html);
                           writer.flush();
                           writer.close();

                       } catch (Exception e) {
                           System.out.println("Exception: " + e);
                       }
                   }

                   private Boolean hasAttachments() {
                       boolean hasAttachments = false;
                       if (this.attachments.length > 0) {
                           hasAttachments = true;
                       }
                       return hasAttachments;
                   }

                   @
                   Override
                   public String toString() {
                       return "ClassPojo [description_html = " + description_html + ", product_id = " + product_id + ", subject = " + subject + ", email_config_id = " + email_config_id + ", requester_name = " + requester_name + ", frDueBy = " + frDueBy + ", id = " + id + ", due_by = " + due_by + ", status_name = " + status_name + ", description = " + description + ", priority = " + priority + ", created_at = " + created_at + ", source_name = " + source_name + ", attachments = " + attachments + ", trained = " + trained + ", tags = " + tags + ", ticket_type = " + ticket_type + ", cc_email = " + cc_email + ", isescalated = " + isescalated + ", status = " + status + ", urgent = " + urgent + ", delta = " + delta + ", group_id = " + group_id + ", requester_status_name = " + requester_status_name + ", to_emails = " + to_emails + ", deleted = " + deleted + ", fr_escalated = " + fr_escalated + ", updated_at = " + updated_at + ", source = " + source + ", priority_name = " + priority_name + ", owner_id = " + owner_id + ", responder_id = " + responder_id + ", responder_name = " + responder_name + ", notes = " + notes + ", spam = " + spam + ", to_email = " + to_email + ", display_id = " + display_id + ", requester_id = " + requester_id + "]";
                   }
               }