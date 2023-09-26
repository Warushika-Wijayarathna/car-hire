package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MainFormController {
    @FXML
    private Pane dashboard;

    @FXML
    private void custBtnClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/customer_form.fxml"));
            Pane customerForm = loader.load();


            dashboard.getChildren().clear();
            dashboard.getChildren().add(customerForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }





    public void carBtnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/car_form.fxml"));
            Pane carForm = loader.load();


            dashboard.getChildren().clear();
            dashboard.getChildren().add(carForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rentBtnClick(ActionEvent actionEvent) {
    }
}
