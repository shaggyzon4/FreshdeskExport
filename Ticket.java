public class Ticket
{
    private Helpdesk_ticket helpdesk_ticket;

    public Helpdesk_ticket getHelpdesk_ticket ()
    {
        return helpdesk_ticket;
    }

    public void setHelpdesk_ticket (Helpdesk_ticket helpdesk_ticket)
    {
        this.helpdesk_ticket = helpdesk_ticket;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [helpdesk_ticket = "+helpdesk_ticket+"]";
    }
}