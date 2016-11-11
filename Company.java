import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Company {
    private String sla_policy_id;
    private String id;
    private String updated_at;
    private String description;
    private String name;
    private String domains;
    private String created_at;
    // private Custom_field custom_field;
    private String cust_identifier;

    private String note;

    public String getSla_policy_id() {
        return sla_policy_id;
    }

    public void setSla_policy_id(String sla_policy_id) {
        this.sla_policy_id = sla_policy_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomains() {
        return domains;
    }

    public void setDomains(String domains) {
        this.domains = domains;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
/*
    public Custom_field getCustom_field() {
        return custom_field;
    }

    public void setCustom_field(Custom_field custom_field) {
        this.custom_field = custom_field;
    }
*/
    public String getCust_identifier() {
        return cust_identifier;
    }

    public void setCust_identifier(String cust_identifier) {
        this.cust_identifier = cust_identifier;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @
    Override
    public String toString() {
        return "ClassPojo [sla_policy_id = " + sla_policy_id + ", id = " + id + ", updated_at = " + updated_at + ", description = " + description + ", name = " + name + ", domains = " + domains + ", created_at = " + created_at + ", custom_field = " + custom_field + ", cust_identifier = " + cust_identifier + ", note = " + note + "]";
    }
    public void toData() {
            Connection conn = null;
            String sql = null;
            PreparedStatement pst = null;

            try {

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost;databasename=freshdesk;integratedsecurity=true;";
                conn = DriverManager.getConnection(url);

                // begin insertion

                conn.setAutoCommit(true);

                sql = "INSERT INTO companies(id, description, name, domains, created_at, note) " + " VALUES (?,?,?,?,?,?);";
                pst = conn.prepareStatement(sql);

                //ticket attachment files are written in storeToDisk() method, to C:\bruce\ticketAttachmentFiles\display_id\original_email_in;
                pst.setString(1, id);
                pst.setString(2, description);
                pst.setString(3, name);
                pst.setString(4, domains);
                pst.setString(5, created_at);
                pst.setString(6, note);

                //   System.out.println(pst.toString());
                pst.execute();
                System.out.println("pst.execute was successful!");

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
            } // end try/catch
        } // end toData
}