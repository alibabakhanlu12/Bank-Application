package Sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DButilsLS {


        static final String DB_URL = "jdbc:mysql://localhost:3306/bank";
        static final String USER = "root";
        static final String PASS = "Clisqltanintegral45@";



        public static void changeScene(String FxmlFile, ActionEvent event, String title, String userName, String password) {
            Parent root = null;
            if (userName != null && password != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(DButilsLS.class.getResource(FxmlFile));
                    root = loader.load();

                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            } else {
                try {
                    root = FXMLLoader.load(DButilsLS.class.getResource(FxmlFile));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
            }
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        }

        public static void singUp(String name,String lastname,String usernmae,String password,String email,String accounttype,String accountnumber) throws Exception {
            Connection con=DriverManager.getConnection(DB_URL,USER,PASS);
            Date d1 = new Date();
            SimpleDateFormat dateformater = new SimpleDateFormat("yyyy-MM-dd");
            String strDate= dateformater.format(d1);
            System.out.println(strDate);
//            String values =
//            System.out.println(values);
            PreparedStatement insertme = con.prepareStatement("INSERT INTO clients (name,lastname,username,password,email,accounttype,accountnumber,openningdate) VALUES" +"('"+name+"',"+"'"+lastname+"',"+"'"+usernmae+"',"+"'"+password+"',"+"'"+email+"',"+"'"+accounttype+"',"+"'"+accountnumber+"',"+"'"+strDate+"')");
            insertme.executeUpdate();
            con.close();
        }

        public static void loginUser(ActionEvent event, String username, String password)
        {
            Connection connection = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try  {
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                preparedStatement = connection.prepareStatement("SELECT password, username FROM clients WHERE username = ?");
                preparedStatement.setString(1, username);
                resultSet = preparedStatement.executeQuery();
                if (!resultSet.isBeforeFirst()) {
                    System.out.println("user not found in the database ");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("provided are not correct");
                    alert.show();
                } else {
                    while (resultSet.next()) {
                        String retrivedPass = resultSet.getString("password");
                        if (retrivedPass.equals(password)) {
                            changeScene("Customer.fxml", event, "welcome", username, password);
                        } else {
                            System.out.println("passwords did not match!");

                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setContentText("the provided informations are incorrect");
                            alert.show();
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            } finally {
                if (resultSet != null) {
                    try {
                        resultSet.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

