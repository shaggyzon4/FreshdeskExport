import com.csvreader.CsvWriter;

public class Cc_email {
    private String[] reply_cc;
    private String[] fwd_emails;
    private String[] tkt_cc;
    private String[] cc_emails;

    public String[] getReply_cc() {
        return reply_cc;
    }

    public void setReply_cc(String[] reply_cc) {
        this.reply_cc = reply_cc;
    }

    public String[] getFwd_emails() {
        return fwd_emails;
    }

    public void setFwd_emails(String[] fwd_emails) {
        this.fwd_emails = fwd_emails;
    }

    public String[] getTkt_cc() {
        return tkt_cc;
    }

    public void setTkt_cc(String[] tkt_cc) {
        this.tkt_cc = tkt_cc;
    }

    public String[] getCc_emails() {
        return cc_emails;
    }

    public void setCc_emails(String[] cc_emails) {
        this.cc_emails = cc_emails;
    }

    @
    Override
    public String toString() {
        return listEmailAddys(cc_emails);
    }

    public String listEmailAddys(String[] ccemail) {
        String addys = "";
        for (int i = 0; i < ccemail.length; i++) {
            addys = addys + (ccemail[i]) + ",";
        }
        return addys;
    }
}