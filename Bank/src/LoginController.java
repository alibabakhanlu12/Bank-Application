import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    String[] roles = {"customer", "employee", "admin"};

    @FXML
    private  ComboBox<String> rolesChoiceBox1;
    @FXML
    private Button btn_login;

    @FXML
    private Button btn_signup1;

    @FXML
    private PasswordField pass_login;
    @FXML
    private TextField user_login;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        rolesChoiceBox1.getItems().addAll(roles);
        btn_login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (rolesChoiceBox1.getValue().equals("customer")) {
                        DButilsLS.loginUser(event, user_login.getText(), pass_login.getText());
                        user_login.setText("");
                        pass_login.setText("");
                        getvalue();
                    }else if (rolesChoiceBox1.getValue().equals("employee")){
                        DButilsLS.loginUserToEmployee(event, user_login.getText(), pass_login.getText());
                        user_login.setText("");
                        pass_login.setText("");
                        getvalue();
                    }else if (rolesChoiceBox1.getValue().equals("admin")){
                        DButilsLS.loginUserToAdmin(event, user_login.getText(), pass_login.getText());
                        user_login.setText("");
                        pass_login.setText("");
                        getvalue();
                    }

                }catch (Exception e){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please choose one of the roles !!!");
                    alert.show();
                }

            }  });

        btn_signup1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DButilsLS.changeScene("Signup.fxml",event,"SignUp",null,null);
            }
        });

    }

    public void getvalue(){
        System.out.println(rolesChoiceBox1.getValue());

    }}