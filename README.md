# Bank-Application
Simulate Bank Application With JavaFX
------------------------------------
>***Before Star***
</br></br>
**SQL Setting**</br></br></br>
For Setting Sql For This Application Please Create Database With Name Of (Bank) Then Import Sql Files In Our Repository(admin,clients,employees,newaccount,transfersreports).</br></br>
Attention That You Must Change (SignupController.java, EmployeeController.java, DButilsLS.java, CustomerController.java, AdminController.java) DB_URL,USER,PASS According To Your Sql Settings.</br></br>We Have Used MySql For Our Database If You Use Another Database Please Change DB_URL(jdbc:mysql://localhost:3306/bank) mysql To Whatever Database You Use Instead.</br></br>
And For The USER Default In mysql Is root...But If Your Username In Your Sql Is Another Thing Please Change This Too.</br></br>
In The End It's Obvious That You Must Change Change PASS To Your Database password.</br></br></br>
**External Libraries You Need To Import**</br></br>
All JavaFX Libraries And Also Connector J If Your Using mysql.(Default Place Of Connector J Is C:\Program Files (x86)\MySQL\Connector J 8.0)
</br></br>
After Adding All Needed Mentioned Libraries If You Have (Java FX Components Are Missing Problem) In Running  Main Class Please Add This vm-args In Your Configuration</br></br>
--module-path C:/Java/javafx-sdk-17.0.1/lib --add-modules javafx.controls,javafx.fxml
------------------------------------
**Bank Application**
