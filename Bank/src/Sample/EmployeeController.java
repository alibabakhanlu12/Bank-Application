package Sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
    static final String USER = "root";
    static final String PASS = "alibabakhanlu12";
    @FXML
    private Button Show_customerlist;

    @FXML
    private TableColumn<EmployeesModels,String> customer_acccounttype;

    @FXML
    private TableColumn<EmployeesModels,String> customer_accountnumber;

    @FXML
    private TableColumn<EmployeesModels,String> customer_date;

    @FXML
    private TableColumn<EmployeesModels,String> customer_email;

    @FXML
    private TableColumn<EmployeesModels,String> customer_user;

    @FXML
    private TableColumn<EmployeesModels,String> customer_lastname;

    @FXML
    private TableColumn<EmployeesModels,String> customer_name;


    @FXML
    private TableView<EmployeesModels> cus_table;

    @FXML
    private Button delete_customer;

    @FXML
    private Button logout1;
    @FXML
    private TableView<Transfer_Models> reports_table;

    @FXML
    private TableColumn<Transfer_Models, String> source_reports;

    @FXML
    private TableColumn<Transfer_Models, String> reports_destination11;

    @FXML
    private TableColumn<Transfer_Models, String> reports_amount;

    @FXML
    private TableColumn<Transfer_Models, String> reports_date;

    @FXML
    private TableColumn<Transfer_Models, String> reports_type;

    @FXML
    private Button show_reports;

    @FXML
    private TextField tf_accountNumber;

    @FXML
    private TextField tf_username;






    private ObservableList<EmployeesModels> data;

    @FXML
    void employee()throws Exception {
        data = FXCollections.observableArrayList();
        Connection ccc = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT name, lastname, username, email, accountnumber, accounttype, openningdate FROM clients";
        PreparedStatement stt = ccc.prepareStatement(query);
        ResultSet rsrs = stt.executeQuery();

        while (rsrs.next()) {
            data.add(new EmployeesModels(rsrs.getString("name"), rsrs.getString("lastname"), rsrs.getString("username"),
                    rsrs.getString("email"), rsrs.getString("accountnumber"), rsrs.getString("accounttype"), rsrs.getString("openningdate")));
        }
        customer_name.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("name"));
        customer_lastname.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("lastname"));
        customer_user.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("username"));
        customer_email.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("email"));
        customer_accountnumber.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("accountnumber"));
        customer_acccounttype.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("accounttype"));
        customer_date.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("openningdate"));

        cus_table.setItems(data);
        ccc.close();
    }

    private ObservableList<Transfer_Models> data1;

    @FXML
    void reportss() throws Exception {
        data1 = FXCollections.observableArrayList();
        Connection ccc = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM transfersreposrts";
        PreparedStatement stt = ccc.prepareStatement(query);
        ResultSet rsrs = stt.executeQuery();
        while (rsrs.next()) {
            data1.add(new Transfer_Models(rsrs.getString(1), rsrs.getString(2), rsrs.getString(3),
                    rsrs.getString(4), rsrs.getString(5)));
        }


        source_reports.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("source"));
        reports_destination11.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("destination"));
        reports_amount.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("amount"));
        reports_date.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("Date"));
        reports_type.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("Type"));

        reports_table.setItems(data1);
        ccc.close();
    }


    @FXML
    private ComboBox<String> combo_delete_customer;

    String [] reson_for_rm_cus = {"Because its owner is dead.","Because its owner is dead." ," Due to low money. ", "" +
            "At the request of the owner","Something else"};
    @FXML
    void delete_customer(){

        String sql =  "delete from clients where username = '"+ tf_username.getText() +"' and  accountnumber = '" +tf_accountNumber.getText() +"'";
        try {
            Connection c = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement st = c.prepareStatement(sql);
            st.executeUpdate();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("successfully deleted");
            a.show();

        }catch (SQLException e){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("client with this username not exist!!");
            a.show();

            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        combo_delete_customer.getItems().addAll(reson_for_rm_cus);
        logout1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButilsLS.changeScene("Login.fxml" ,event,"Login" , null,null);
            }
        });

    }
}




