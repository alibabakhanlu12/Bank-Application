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

public class AdminController implements Initializable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
    static final String USER = "root";
    static final String PASS = "alibabakhanlu12";
    @FXML
    private Button logout1;


    @FXML
    private TableColumn<EmployeesModels, String> customer_accounttype;

    @FXML
    private TableColumn<EmployeesModels, String> customer_email;

    @FXML
    private TableColumn<EmployeesModels, String> customer_lastname;

    @FXML
    private TableColumn<EmployeesModels, String> customer_name;

    @FXML
    private TableColumn<EmployeesModels, String> customer_number;

    @FXML
    private TableColumn<EmployeesModels, String> customer_openningdate;

    @FXML
    private TableColumn<EmployeesModels, String> customer_username;

    @FXML
    private TableView<EmployeesModels> customerlist_admin;



    private ObservableList<EmployeesModels> data;

    @FXML
    void admin()throws Exception {
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
        customer_username.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("username"));
        customer_email.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("email"));
        customer_number.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("accountnumber"));
        customer_accounttype.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("accounttype"));
        customer_openningdate.setCellValueFactory(new PropertyValueFactory<EmployeesModels,String>("openningdate"));

        customerlist_admin.setItems(data);
        ccc.close();
    }



    @FXML
    private TableView<Transfer_Models> admin_Show_reports1;
    @FXML
    private TableColumn<Transfer_Models, String> reports_amount;

    @FXML
    private TableColumn<Transfer_Models, String> reports_date;

    @FXML
    private TableColumn<Transfer_Models, String> reports_destination;

    @FXML
    private TableColumn<Transfer_Models, String> reports_source;

    @FXML
    private TableColumn<Transfer_Models, String> reports_type;
    private ObservableList<Transfer_Models> data1;

    @FXML
    void admin_show_reports() throws Exception {
        data1 = FXCollections.observableArrayList();
        Connection ccc = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM transfersreposrts";
        PreparedStatement stt = ccc.prepareStatement(query);
        ResultSet rsrs = stt.executeQuery();
        while (rsrs.next()) {
            data1.add(new Transfer_Models(rsrs.getString(1), rsrs.getString(2), rsrs.getString(3),
                    rsrs.getString(4), rsrs.getString(5)));
        }


        reports_source.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("source"));
        reports_destination.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("destination"));
        reports_amount.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("amount"));
        reports_date.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("Date"));
        reports_type.setCellValueFactory(new PropertyValueFactory<Transfer_Models,String>("Type"));

        admin_Show_reports1.setItems(data1);
        ccc.close();
    }

    @FXML
    private TableColumn<CustomerModels2,String> employee_pass;

    @FXML
    private TableColumn<CustomerModels2,String> employee_username;

    @FXML
    private TableView<CustomerModels2> admin_list;


    private ObservableList<CustomerModels2> data2;
    @FXML
    void show_admin_list() throws Exception {
        data2 = FXCollections.observableArrayList();
        Connection ccc = DriverManager.getConnection(DB_URL, USER, PASS);
        String query = "SELECT * FROM employees";
        PreparedStatement stt = ccc.prepareStatement(query);
        ResultSet rsrs = stt.executeQuery();
        while (rsrs.next()) {
            data2.add(new CustomerModels2(rsrs.getString(1), rsrs.getString(2) ));
        }
        employee_username.setCellValueFactory(new PropertyValueFactory<CustomerModels2,String>("username"));
        employee_pass.setCellValueFactory(new PropertyValueFactory<CustomerModels2,String>("Password"));

        admin_list.setItems(data2);
    }

    @FXML
    private TextField deleteEmployee_tf;

    @FXML
   public void delete_btn() throws ClassNotFoundException , SQLException{
        String sql =  "delete from employees where username ='"+ deleteEmployee_tf.getText() +"'";
        try {
            Connection c = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement st = c.prepareStatement(sql);

          st.executeUpdate();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("successfully deleted");
            a.show();

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @FXML
    private TextField accountnumber_tf;
    @FXML
    private TextField username_tf;


    @FXML
    void delete_clients(ActionEvent event) {
        String sql =  "delete from clients where username ='"+ username_tf.getText() +"'";
        try {
            Connection c = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement st = c.prepareStatement(sql);

            st.executeUpdate();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("successfully deleted");
            a.show();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {







        logout1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButilsLS.changeScene("Login.fxml" ,event,"Login" , null,null);
            }
        });
    }
}
