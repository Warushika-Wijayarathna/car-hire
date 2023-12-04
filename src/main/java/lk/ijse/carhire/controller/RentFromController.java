package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.CustomerService;
import lk.ijse.carhire.service.custom.Impl.CarServiceImpl;
import lk.ijse.carhire.service.custom.Impl.CustomerServiceImpl;
import lk.ijse.carhire.service.custom.Impl.RentServiceImpl;
import lk.ijse.carhire.service.custom.RentService;

import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentFromController {

    public Label rentLabel;
    public Label carLabel;
    public Label advanceLabel;
    public Label fromLabel;
    public Label toLabel;
    public TableView carTable;
    public Button backBtn;
    public Label totalLabel;
    public Label refundLabel;
    public TextField rentText;
    public TextField carText;
    public TextField advanceText;
    public TextField fromText;
    public TextField toText;
    public TextField refundText;
    public Label returnLabel;
    public CheckBox returnCheck;
    public Label custLabel;
    public TextField customerText;
    public Label balanceLabel;
    public Label searchCarLabel;
    public Label searchCustomerLabel;
    public Label getTotalLabel;
    public Label getBalanceLabel;
    public Button carSearchButton;
    public Button customerSearchBotton;
    @FXML
    private Pane rentForm;

    private RentService rentService = new RentServiceImpl();
    private CarService carService = new CarServiceImpl();
    private CustomerService customerService = new CustomerServiceImpl();
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

    public void carSearchBtnClick(ActionEvent actionEvent) {
        try {
            String carId=carText.getText();
            CarDto car=carService.getCar(Integer.parseInt(carId));
            if (car!=null) {
                searchCarLabel.setText(car.getPlateNo());
            }else{
                String errorMessage = "Car with ID " + carId  + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

    public void customerSearchBtnClick(ActionEvent actionEvent) {
        try {
            String custId=customerText.getText();
            CustomerDto cust=customerService.getCustomer(Integer.parseInt(custId));
            if(cust!=null){
                searchCustomerLabel.setText(cust.getName());
            }else{
                String errorMessage = "Customer with ID " + custId + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }


}
