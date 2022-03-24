package Sample;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import javax.xml.stream.events.StartDocument;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CustomerController implements Initializable {
    static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
    static final String USER = "root";
    static final String PASS = "Clisqltanintegral45@";
    public static String user;
    @FXML
    private ComboBox<String> accounts;

//    @FXML
//    private TextField actfortransferarea;

    @FXML
    private Button addmoneybutton;

    @FXML
    private TextField desaccount;

    @FXML
    private  TextField transferamount;

    @FXML
    private TextField enternmoney;

    @FXML
    private Button calculatebtn;

    @FXML
    private Label firstdigits;

    @FXML
    private Label fourthdigits;

    @FXML
    private Button makenewaccount;

    @FXML
    private Label myuser;

    @FXML
    private PasswordField passwordarea;

    @FXML
    private Label futuremoney;

    @FXML
    private Label currentmoney;

    @FXML
    private DatePicker selectdate;

    @FXML
    private TableView<reports> reporttable;

    @FXML
    private TableColumn<reports, String> sourcecolum;

    @FXML
    private TableColumn<reports, String> destinationcolumn;

    @FXML
    private TableColumn<reports, String> amountcolumn;

    @FXML
    private TableColumn<reports, String> datecolumn;


    @FXML
    private TableColumn<reports, String> typecolumn;


    @FXML
    private Label seconddigits;

    @FXML
    private Label accountlbl;

    @FXML
    private Label availabilitylbl;

    @FXML
    private Label namelbl;

    @FXML
    private Label emaillbl;
    @FXML
    private Label aferonedate;

    @FXML
    private Label opendate;

    @FXML
    private Label usernamelbl;

    @FXML
    private Button signoutbutton;

    @FXML
    private Label lastnamelbl;

    @FXML
    private Label thirddigits;

    @FXML
    void calculate(){
        try
        {
            Connection lastconnection = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stlast = lastconnection.createStatement();
            ResultSet dateresult = stlast.executeQuery("SELECT openningdate from clients Where username ="+"'"+user+"'");
            dateresult.next();
            String opendate = dateresult.getString(1);
            ResultSet lastresult = stlast.executeQuery("SELECT availability from clients Where username ="+"'"+user+"'");
            lastresult.next();
            String currentavailability = lastresult.getString(1);
            Long longavailabilty = Long.parseLong(currentavailability);
            String x = selectdate.getValue().toString();
                System.out.println(x);
                String numbers[] = new String[3];
                numbers=x.split("-");
                //0 is year  1 is month  2 is day
                String opennumbers[] = new String[3];
                opennumbers=opendate.split("-");
                int z = Integer.parseInt(numbers[2])-Integer.parseInt(opennumbers[2]);
                int y = Integer.parseInt(numbers[1])-Integer.parseInt(opennumbers[1]);
                int xx = Integer.parseInt(numbers[0])-Integer.parseInt(opennumbers[0]);
                int i=0;
                if(((xx*365)+(y*30)+z)<=0) {
                    System.out.println("na baba");
                }
                else
                {
                    if(z<0) {
                        i = xx * 12 + y - 1;
                        System.out.println(i);
                    }
                    else {
                        i = xx * 12 + y;
                        System.out.println(i);
                    }
                }
                for(int j = 0;j<i;j++)
                {
                    longavailabilty=longavailabilty+(longavailabilty/20);
                }
                futuremoney.setText(String.valueOf(longavailabilty));
        }
        catch (Exception date){
            date.printStackTrace();
        }
    }

    @FXML
    void showww()throws Exception {
        Connection ccc = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM transfersreports WHERE srcactnum = " + "'" + accounts.getValue().toString() + "'" + "OR desactnum = " + "'" + accounts.getValue().toString() + "'";
        PreparedStatement stt = ccc.prepareStatement(query);
        ResultSet rsrs = stt.executeQuery();
        ObservableList<reports> reportlist = FXCollections.observableArrayList();
        while (rsrs.next()) {
           reportlist.add(new reports(rsrs.getString(1), rsrs.getString(2), rsrs.getString(3), rsrs.getString(4), rsrs.getString(5)));
        }
        sourcecolum.setCellValueFactory(new PropertyValueFactory<reports, String>("source"));
        destinationcolumn.setCellValueFactory(new PropertyValueFactory<reports, String>("destination"));
        amountcolumn.setCellValueFactory(new PropertyValueFactory<reports, String>("amount"));
        datecolumn.setCellValueFactory(new PropertyValueFactory<reports, String>("Date"));
        typecolumn.setCellValueFactory(new PropertyValueFactory<reports, String>("Type"));
        reporttable.setItems(reportlist);
        ccc.close();
    }



    @FXML
    private Button transfermoney;



    @FXML
    void newaccount() throws Exception {
        System.out.println("HEY");
        File randomaccountcreator = new File("generator.txt");
        if(!(randomaccountcreator.exists()))
        {
            System.out.println("created");
            randomaccountcreator.createNewFile();
        }
        Scanner scanaccountnumber = new Scanner(randomaccountcreator);
        String give = scanaccountnumber.nextLine();
        System.out.println(give);
        scanaccountnumber.close();
        FileWriter writenewone = new FileWriter(randomaccountcreator);
        long x = Long.parseLong(give);
        x++;
        String newaccount = String.valueOf(x);
        writenewone.write(newaccount);
        writenewone.close();
        scanaccountnumber.close();
        Connection cc = DriverManager.getConnection(DB_URL,USER,PASS);
        PreparedStatement creator = cc.prepareStatement("insert into newaccount values ("+"'"+user+"'"+",'"+give+"')");
        creator.executeUpdate();
        String section1 = give.substring(0,4);
        String section2 = give.substring(4,8);
        String section3 = give.substring(8,12);
        String section4 = give.substring(12,16);
        firstdigits.setText(section1);
        seconddigits.setText(section2);
        thirddigits.setText(section3);
        fourthdigits.setText(section4);
        accounts.getItems().addAll(give);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Operation");
        alert.setHeaderText("SUCCESSFUL!");
        alert.setContentText("YOUR NEW ACCOUNT NUMBER : "+give);
        alert.showAndWait();
    }

    @FXML
     void addmoney() {
        try{
            Connection c1 = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement st1 = c1.createStatement();
            ResultSet r1 = st1.executeQuery("SELECT availability FROM clients where username="+"'"+user+"'");
            System.out.println(r1.next());
            System.out.println(r1.getString(1));
            String x = String.valueOf(enternmoney.getText());
            System.out.println(x);
            String y = String.valueOf(Long.parseLong(x)+Long.parseLong(r1.getString(1)));
            System.out.println("this is y "+y);
            Statement st2 = c1.createStatement();
            ResultSet r2 = st1.executeQuery("SELECT deposit FROM clients where username="+"'"+user+"'");
            System.out.println(r2.next());
            String z = String.valueOf(Long.parseLong(r2.getString(1))+Long.parseLong(x));
            System.out.println(z);
            PreparedStatement insertme = c1.prepareStatement("UPDATE clients SET availability = "+"'"+y+"'"+","+"deposit = "+"'"+z+"'"+"where username = "+"'"+user+"'");
            insertme.executeUpdate();
            java.util.Date d1 = new Date();
            SimpleDateFormat dateformater = new SimpleDateFormat("yyyy-MM-dd");
            String strDate= dateformater.format(d1);
            String sourceaccount = accounts.getValue().toString();
            String src1 = sourceaccount.replace("]","[");
            String src2 = src1.replace("[","");
//            String transferquery = "INSERT INTO transfersreports VALUES (+"Bank"+","+src2+","+x+","+strDate+","+"Deposit)";
            String transferquery = "insert into transfersreports (srcactnum, desactnum, amount, Date, type) values ("+"'BANK"+"','"+src2+"','"+x+"','"+strDate+"','"+"Deposit')";
            System.out.println(transferquery);
            PreparedStatement insertreport = c1.prepareStatement(transferquery);
            insertreport.executeUpdate();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Operation");
            alert.setHeaderText("SUCCESSFUL!");
            alert.setContentText("Money Deposited Successfully");
            alert.showAndWait();
            c1.close();
        }
        catch (Exception sql){
            sql.printStackTrace();
        }
    }

    @FXML
    void transfermoney() throws Exception
    {
        Connection c = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement getpass = c.createStatement();
        ResultSet getingpass = getpass.executeQuery("SELECT password from clients where username = "+"'"+user+"'");
        getingpass.next();
        String userpassword = getingpass.getString(1);
        System.out.println(userpassword);
        String enternpass = passwordarea.getText();
        String sourceaccount = accounts.getValue().toString();
        String destination = desaccount.getText();
        String amount = transferamount.getText();
        Long amountlongform = Long.parseLong(amount);
        Statement stt = c.createStatement();
        ResultSet checkfordestination = stt.executeQuery("SELECT * From clients where accountnumber = '"+destination+"'");
        boolean check = checkfordestination.next();
        ResultSet checkforothers = stt.executeQuery("SELECT * From newaccount where accountnumber = '"+destination+"'");
        boolean secondcheck = checkforothers.next();
        System.out.println(check);
        if(check || secondcheck)
        {
            System.out.println("THRE ISSSSS");
            Statement availabilitygetter = c.createStatement();
            ResultSet getnow = availabilitygetter.executeQuery("SELECT availability FROM clients where username ="+"'"+user+"'");
            getnow.next();
            Long x = Long.parseLong(getnow.getString(1));
            if(enternpass.equals(userpassword) && x>=amountlongform )
            {
                System.out.println("TRUE USER PASSWORD and true amount");
                //geraftan availablity az karbar va kam kardan az khod karabar dar mojoodi va ezafe kardan be withdraw

                Long y = Long.parseLong(amount);
                x=x-y;
                ResultSet getwithdraw = availabilitygetter.executeQuery("SELECT withdraw FROM clients where username ="+"'"+user+"'");
                getwithdraw.next();
                Long withdraw = Long.parseLong(getwithdraw.getString(1));
                withdraw=withdraw+amountlongform;
                PreparedStatement updateavailability = c.prepareStatement("UPDATE clients SET availability = "+"'"+String.valueOf(x)+"'"+","+"withdraw = "+"'"+String.valueOf(withdraw)+"'"+"where username = "+"'"+user+"'");
                updateavailability.executeUpdate();
                //ezafe kardan pool be destionation(ebteda availability grefte shavad bad be an afzoode shavad) va deposit karbar
                Statement destavailabilitygetter = c.createStatement();
                ResultSet destgetnow = destavailabilitygetter.executeQuery("SELECT username FROM newaccount where accountnumber ="+"'"+destination+"'");
                String destuser;
                if(destgetnow.next())
                {
                    destuser = destgetnow.getString(1);
                }
                else
                {
                    ResultSet destgetnowelse = destavailabilitygetter.executeQuery("SELECT username FROM clients where accountnumber ="+"'"+destination+"'");
                    destgetnowelse.next();
                    destuser = destgetnowelse.getString(1);
                }
                ResultSet destgetavailability = availabilitygetter.executeQuery("SELECT availability FROM clients where username ="+"'"+destuser+"'");
                destgetavailability.next();
                Long xdest = Long.parseLong(destgetavailability.getString(1));
                xdest=xdest+amountlongform;
                ResultSet rrr = availabilitygetter.executeQuery("SELECT deposit FROM clients where username ="+"'"+destuser+"'");
                rrr.next();
                Long destdeposit = Long.parseLong(rrr.getString(1));
                destdeposit=destdeposit+amountlongform;
                PreparedStatement updateavailabilityofdest = c.prepareStatement("UPDATE clients SET availability = "+"'"+String.valueOf(xdest)+"'"+","+"deposit = "+"'"+String.valueOf(destdeposit)+"'"+"where username = "+"'"+destuser+"'");
                updateavailabilityofdest.executeUpdate();
                //ezafe kardan etelaat be tranfersreport
                java.util.Date d1 = new Date();
                SimpleDateFormat dateformater = new SimpleDateFormat("yyyy-MM-dd");
                String strDate= dateformater.format(d1);
                String srcacount = accounts.getValue().toString();
                String src1 = srcacount.replace("]","[");
                String src2 = src1.replace("[","");
                String transferquery = "insert into transfersreports (srcactnum, desactnum, amount, Date, type) values ("+"'"+src2+"','"+destination+"','"+amount+"','"+strDate+"','"+"Withdraw')";
                System.out.println(transferquery);
                PreparedStatement insertreport = c.prepareStatement(transferquery);
                insertreport.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SUCCESSFUL OPERATON");
                alert.setHeaderText("SUCCESSFUL OPERATON");
                alert.setContentText("SUCCESSFUL OPERATON"+" To Account Number : "+destination +" To "+destuser);
                alert.showAndWait();
                c.close();
            }
        }
        else
        {
            System.out.println("THERE ISSSS NOOOOOOOT");
            System.out.println("NOT FOUND");
           System.out.println("NOT FOUNDDDDD");
            Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setTitle("ERROR");
           alert.setHeaderText("FAILURE");
           alert.setContentText("UNAVAILABLE ACCOUNT NUMBER PLEASE CHECK ONCE AGGAIN");
           alert.showAndWait();
        }
    }
        @FXML
    void signout(ActionEvent event){
        user=null;
        DButilsLS.changeScene("Login.fxml",event,"a",null,null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        myuser.setText(user);
        System.out.println(user);
        try {
            Connection con= DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stmt=con.createStatement();
            System.out.println("Im Here");
            System.out.println(user);
            ResultSet rs=stmt.executeQuery("SELECT accountnumber FROM clients where username="+"'"+user+"'");
            System.out.println(rs.next());
            String firstact = rs.getString(1);
            accounts.setValue(firstact);
            ResultSet news = stmt.executeQuery("SELECT accountnumber FROM newaccount where username="+"'"+user+"'");
            accounts.getItems().addAll(firstact);
            while(news.next())
            {
                accounts.getItems().addAll(news.getString(1));
            }
            con.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try{
            Connection hro = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement hrost = hro.createStatement();
            ResultSet getinfo = hrost.executeQuery("SELECT * FROM clients Where username ="+"'"+user+"'");
            while (getinfo.next()){
                String name = getinfo.getString(1);
                namelbl.setText(name);
                String lastname = getinfo.getString(2);
                lastnamelbl.setText(lastname);
                String username = getinfo.getString(3);
                usernamelbl.setText(username);
                String email = getinfo.getString(5);
                emaillbl.setText(email);
                String accountnumber = getinfo.getString(9);
                accountlbl.setText(accountnumber);
                String availability = getinfo.getString(10);
                availabilitylbl.setText(availability);
                hro.close();
            }
        }
        catch (Exception tr){

        }
        try{
            Connection lastconnection = DriverManager.getConnection(DB_URL,USER,PASS);
            Statement stlast = lastconnection.createStatement();
            ResultSet lastresult = stlast.executeQuery("SELECT availability from clients Where username ="+"'"+user+"'");
            lastresult.next();
            String currentavailability = lastresult.getString(1);
            currentmoney.setText(currentavailability);
            Long x = Long.parseLong(currentavailability);
            x=x+(x/20);
            String willbemoney = String.valueOf(x);
            Date d1 = new Date();
            SimpleDateFormat dateformater = new SimpleDateFormat("yyyy-MM-dd");
            String strDate= dateformater.format(d1);
            System.out.println(strDate);
//            currentdate.setText(strDate);
            ResultSet dateresult = stlast.executeQuery("SELECT openningdate from clients Where username ="+"'"+user+"'");
            dateresult.next();
            opendate.setText(dateresult.getString(1));
        }
        catch (Exception profit){
            profit.printStackTrace();
        }
    }
}
