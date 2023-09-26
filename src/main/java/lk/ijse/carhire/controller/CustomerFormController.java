package lk.ijse.carhire.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.custom.CustomerService;
import lk.ijse.carhire.service.custom.Impl.CustomerServiceImpl;
import lk.ijse.carhire.tm.CustomerTm;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerFormController {

    @FXML
    private Pane customerForm;

    @FXML
    private TextField customerText;

    @FXML
    private TextField custNameText;

    @FXML
    private TextField custAddressText;

    @FXML
    private TextField nicText;

    @FXML
    private TextField mobileNoText;

    @FXML
    private TableView<CustomerTm> customerTable;
    @FXML
    private TableColumn<CustomerTm, Integer> colId;
    @FXML
    private TableColumn<CustomerTm, String> colAddress;
    @FXML
    private TableColumn<CustomerTm, Integer> colMobile;
    @FXML
    private TableColumn<CustomerTm, String> colName;
    @FXML
    private TableColumn<CustomerTm, String> colNic;
    @FXML
    private TableColumn<CustomerTm, String> colUpdate;
    @FXML
    private TableColumn<CustomerTm, String> colDelete;


    private CustomerService customerService;


    public CustomerFormController() throws Exception {
        customerService = new CustomerServiceImpl();

        initialize();
    }

    @FXML
    public void saveCustomer(ActionEvent actionEvent) {
        addCustomer();
    }

    private void addCustomer() {
        try {
            CustomerDto customerDto = new CustomerDto(
                    Integer.parseInt(customerText.getText()),
                    custAddressText.getText(),
                    Integer.parseInt(mobileNoText.getText()),
                    custNameText.getText(),
                    nicText.getText(),
                    "Update",
                    "Delete"
            );

            String result = customerService.addCustomer(customerDto);

        } catch (NumberFormatException ex) {
            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void backBtnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/main_form.fxml"));
            Pane dashboard = loader.load();
            customerForm.getChildren().clear();
            customerForm.getChildren().add(dashboard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initialize() throws Exception {
        System.out.println("Customer form just loaded!");
        List<CustomerDto> customerDtos = customerService.getAllCustomers();
        setCellValueFactory();
        setCustomers(customerDtos);
    }

    private void setCustomers(List<CustomerDto> customerDtoList) {
        ObservableList<CustomerTm> observableList = FXCollections.observableArrayList();

        for (CustomerDto customerDto : customerDtoList) {
            var customerTm = new CustomerTm(
                    customerDto.getId(),
                    customerDto.getAddress(),
                    customerDto.getMobile(),
                    customerDto.getName(),
                    customerDto.getNic(),
                    customerDto.getUpdate(),
                    customerDto.getDelete()
            );
            observableList.add(customerTm);
        }
        System.out.println(observableList);
        if (customerTable != null) {
            customerTable.setItems(observableList);
        } else {
            new Alert(Alert.AlertType.ERROR, "customerTable is null. Unable to set items.").show();
        }
    }


    private void setCellValueFactory() {
        if (colId != null) {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        }

        if (colAddress != null) {
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        }

        if (colMobile != null) {
            colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        }

        if (colName != null) {
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        }

        if (colNic != null) {
            colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        }

        if (colUpdate != null) {
            colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
        }

        if (colDelete != null) {
            colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        }

    }

    public void customerTableMouseClicked(MouseEvent mouseEvent) {

    }
}


