import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileWriter;
import java.sql.*;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SignupController implements Initializable {

    static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
    static final String USER = "root";
    static final String PASS = "alibabakhanlu12";

    @FXML
    public Label captcha;
    @FXML
    public Button newCaptchaButton;

    @FXML
    private ComboBox<String> typecombo;

    @FXML
    private Button backlogin;

    @FXML
    private TextField username ,email,lastName ,name ,newcaptha;

    @FXML
    private PasswordField password, confpassword;

    @FXML
    void getbacktologin() {

        backlogin.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
            public void handle(ActionEvent actionEvent)
            {
                DButilsLS.changeScene("Login.fxml", actionEvent, "a", null, null);
            }
        });
    }


@FXML
private Button signup;
    @FXML
    void getinforamtionandsignup() throws Exception
    {
        if (!(username.getText().isEmpty()||typecombo.getValue().toString().equals("[Please Select Account Type]")||email.getText().isEmpty()||lastName.getText().isEmpty()||name.getText().isEmpty()||password.getText().isEmpty()||confpassword.getText().isEmpty()))
        {
//            System.out.print("HI");
            if(password.getText().equals(confpassword.getText())&&newcaptha.getText().equals(captcha.getText()))
            {
//                System.out.println(" heyy");
                //handling not repeat of username
                Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery("SELECT username FROM clients where username="+"'"+username.getText()+"'");
                if(rs.next())
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("This Usernmae Is Unavailable Please Enter Another Obe");
                    alert.show();
                }
                else
                {
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
                    String ch = typecombo.getValue().toString();
                    String ch1 = ch.replace("[","]");
                    String changes = ch1.replace("]","");
                    DButilsLS.singUp(name.getText(),lastName.getText(),username.getText(),password.getText(),email.getText(),changes,give);
                    CustomerController.user=username.getText();
                    System.out.println(CustomerController.user);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Operation");
                    alert.setHeaderText("SUCCESSFUL!");
                    alert.setContentText("Your Signing Up Was Successful");
                    alert.showAndWait();
                    signup.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent actionEvent) {
                            DButilsLS.changeScene("Customer.fxml",actionEvent,"a",null,null);
                        }
                    });
                }
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Check Your Inputs Once Again");
            alert.show();
        }
    }




    public String GenerateCaptcha() {

        char[] data = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                'm', 'n', 'o', 'p', 'q', 'r', 's','t', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
                 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        char[] index = new char[6];

        Random r = new Random();
        int i;
        String captcha = "";

        for (i = 0; i < (index.length); i++) {
            int ran = r.nextInt(data.length);
            index[i] = data[ran];
            captcha = String.valueOf(index);
        }
        return captcha;
    }

    public void onNewCaptchaButtonClick(ActionEvent e) {
        String Captcha = GenerateCaptcha();
        captcha.setText(Captcha);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String Captcha = GenerateCaptcha();
        captcha.setText(Captcha);
        String t1 = "Garzolhasaneh";
        String t2 = "SepordeJari";
        typecombo.getItems().addAll(t1,t2);
//        signup.setOnAction(new EventHandler<ActionEvent>() {

//            @Override
//            public void handle(ActionEvent event) {
//                if (!(name.getText().trim().isEmpty())
//                        &&!(accountnumber.getText().trim().isEmpty()) &&!(accountnumber.getText().trim().isEmpty()) && !(availibility.getText().trim().isEmpty())
//                        &&!(accounttype.getText().trim().isEmpty()) && !(deposit.getText().trim().isEmpty()) &&
//                       !(lastName.getText().trim().isEmpty()) &&(password.getText().equals(confpassword.getText())) &&
//                        !(lastName.getText().trim().isEmpty()) && !(confpassword.getText().trim().isEmpty())
//                        && !(password.getText().trim().isEmpty()) && !(lastName.getPromptText().trim().isEmpty())) {
//
//
////
////                    DButilsLS.singUp(event, name.getText(), lastName.getText(), username.getText(),
////                            email.getText(), password.getText(), confpassword.getText(), String.valueOf(date.getValue()),availibility.getText()
////                            , withdraw.getText(), deposit.getText(), accounttype.getText(),
////                            newcaptha.getText(), accountnumber.getText());
//                    test.checker();
//
//                } else {
//                    if (!(password.getText().equals(confpassword.getText()))) {
//                        Alert alert2 = new Alert(Alert.AlertType.ERROR);
//                        alert2.setContentText("your entered passwords are not same!!");
//                        alert2.show();
//
//
//                    }
//                    if (confpassword.getText().trim().isEmpty() && password.getText().trim().isEmpty()) {
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setContentText("please fill passwords to sign up");
//                        alert.show();
//                    }
//                    if ((username.getText().trim().isEmpty())) {
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setContentText("please fill username to sign up");
//                        alert.show();
//                    }
//                    if ((email.getText().trim().isEmpty())) {
//
//                        Alert alert = new Alert(Alert.AlertType.ERROR);
//                        alert.setContentText("please fill email to sign up");
//                        alert.show();
//                    }
//
//                }
//            }
//        });

    }
}
