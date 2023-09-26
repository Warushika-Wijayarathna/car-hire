package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.carhire.Db.db;

import java.io.IOException;

public class LoginFormController {
    public TextField txtUserName;
    public TextField txtPw;
    public AnchorPane rootNode;


    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String userName = txtUserName.getText();
        String pw = txtPw.getText();

        if(userName.equals(db.USER_NAME) && pw.equals(db.PASSWORD)) {
            navigate();
        } else {
            new Alert(Alert.AlertType.ERROR, "User name or Password is invalid").show();
        }
    }

    private void navigate() throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/View/main_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage primaryStage = (Stage) this.rootNode.getScene().getWindow();

        primaryStage.setTitle("Dashboard");
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
    }
}