package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class RentFromController {

    @FXML
    private Pane rentForm;
    public void backBtnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/main_form.fxml"));
            Pane dashboard = loader.load();

            rentForm.getChildren().clear();
            rentForm.getChildren().add(dashboard);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
