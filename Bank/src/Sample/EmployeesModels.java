package Sample;

public class EmployeesModels {


    String name,lastname,username,email,accounttype,accountnumber,openningdate;

    public EmployeesModels(String name, String lastname, String username, String email, String accounttype, String accountnumber, String openningdate) {
        this.name = name;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.accounttype = accounttype;
        this.accountnumber = accountnumber;
        this.openningdate = openningdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype;
    }

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber;
    }

    public String getOpenningdate() {
        return openningdate;
    }

    public void setOpenningdate(String openningdate) {
        this.openningdate = openningdate;
    }


}
