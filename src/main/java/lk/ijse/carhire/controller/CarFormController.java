package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.Impl.CarServiceImpl;


import java.util.logging.Level;
import java.util.logging.Logger;

public class CarFormController {
    @FXML
    private TextField carIdText;
    @FXML
    private TextField plateText;
    @FXML
    private TextField brandText;
    @FXML
    private TextField modelText;
    @FXML
    private TextField yearText;
    @FXML
    private TextField ppdayText;
    @FXML
    private Pane carForm;



    private CarService carService =new CarServiceImpl();
    public void saveCar(ActionEvent actionEvent) {
        addCar();
    }

    private void addCar() {
        try {




            // Provide user feedback here if needed.

        } catch (NumberFormatException ex) {
            // Handle invalid number input (non-numeric characters).
            // You can show an error message to the user.
            Logger.getLogger(CarFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            // Handle other exceptions (e.g., service errors).
            // You can show an error message to the user.
            Logger.getLogger(CarFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void backBtnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/main_form.fxml"));
            Pane dashboard = loader.load();

            carForm.getChildren().clear();
            carForm.getChildren().add(dashboard);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
