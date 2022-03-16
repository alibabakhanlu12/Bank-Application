package Sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable{

    String[] reasons = {"Reason1", "Reason2", "Reason3"};
    String[] actions = {"Deposit", "Withdraw"};
    String[] reports = {"Deposits", "Withdrawals"};

    @FXML
    ChoiceBox<String> reasonsChoiceBox;
    @FXML
    ChoiceBox<String> actionsChoiceBox;
    @FXML
    ChoiceBox<String> reportsChoiceBox;
    @FXML
    Label amountLabel;
    @FXML
    Button actionsButton;

    public void changeOnActions(ActionEvent e){

        if (actionsChoiceBox.getValue().equals("Deposit")){
            amountLabel.setText("Amount you want to deposit:");
            actionsButton.setText("Deposit");
        }
        if (actionsChoiceBox.getValue().equals("Withdraw")){
            amountLabel.setText("Amount you want to withdraw:");
            actionsButton.setText("Withdraw");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        reasonsChoiceBox.getItems().addAll(reasons);
        reasonsChoiceBox.setValue(reasons[0]);

        actionsChoiceBox.getItems().addAll(actions);
        actionsChoiceBox.setValue(actions[0]);
        actionsChoiceBox.setOnAction(this::changeOnActions);

        reportsChoiceBox.getItems().addAll(reports);
        reportsChoiceBox.setValue(reports[0]);

        amountLabel.setText("Amount you want to Deposit:");

    }
}
