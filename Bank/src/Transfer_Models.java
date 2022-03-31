import javafx.beans.property.SimpleStringProperty;

public class Transfer_Models
{

    // Table Models for transferReports

    String Date ,Type, source , destination,amount ;

    public Transfer_Models(String source, String destination, String Date, String amount, String Type) {
        this.Date = Date;
        this.Type = Type;
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }



    public String getDate()
    {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }



    // Models for customer table




}
