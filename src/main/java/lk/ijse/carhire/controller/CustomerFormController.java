package lk.ijse.carhire.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.custom.CustomerService;
import lk.ijse.carhire.service.custom.Impl.CustomerServiceImpl;
import lk.ijse.carhire.TableModel.CustomerTm;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomerFormController {

    public TableColumn<CustomerTm, String> colId;
    public TableColumn<CustomerTm, String> colName;
    public TableColumn<CustomerTm,String> colAddress;
    public TableColumn<CustomerTm,String> colMobile;
    public TableColumn<CustomerTm,String> colNic;
    public TableColumn<CustomerTm,String> colEmail;
    public TableColumn<CustomerTm,String> colUpdate;
    public TableColumn<CustomerTm,String> colDelete;
    public Label customerLabel;
    public Label custNameLabel;
    public Label custAddressLabel;
    public Label NICLabel;
    public Button backBtn;
    public TableView<CustomerTm> tblCustomer;
    public Label MobileLabel;
    public Label emailLabel;
    @FXML
    public TextField emailText;

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

    private CustomerService customerService = new CustomerServiceImpl();


    @FXML
    public void saveCustomer(ActionEvent actionEvent) {

        addCustomer();
    }

    private void addCustomer() {
        try {
            CustomerDto customerDto = new CustomerDto(
                    Integer.parseInt(customerText.getText()),
                    custAddressText.getText(),
                    emailText.getText(),
                    Integer.parseInt(mobileNoText.getText()),
                    custNameText.getText(),
                    nicText.getText(),
                    "Update",
                    "Delete"
            );

            String result = customerService.addCustomer(customerDto);
            clear();
            loadAllCustomers();

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

    public void initialize() throws Exception {
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
                    customerDto.getEmail(),
                    customerDto.getMobile(),
                    customerDto.getName(),
                    customerDto.getNic(),
                    "Update",
                    "Delete"
            );
            observableList.add(customerTm);
        }
        System.out.println(observableList);
        if (tblCustomer != null) {
            tblCustomer.setItems(observableList);
        } else {
            new Alert(Alert.AlertType.ERROR, "customerTable is null. Unable to set items.").show();
        }
    }


    private void setCellValueFactory() {
        System.out.println(colId);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colUpdate.setCellFactory(new Callback<TableColumn<CustomerTm, String>, TableCell<CustomerTm, String>>() {
            @Override
            public TableCell<CustomerTm, String> call(TableColumn<CustomerTm, String> param) {
                return new TableCell<CustomerTm, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            int rowIndex = getTableRow().getIndex();
                            Hyperlink updateLink = new Hyperlink("Update");
                            updateLink.setOnAction(event -> {
                                updateCustomer();
                            });

                            setGraphic(updateLink);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        colDelete.setCellFactory(new Callback<TableColumn<CustomerTm, String>, TableCell<CustomerTm, String>>() {
            @Override
            public TableCell<CustomerTm, String> call(TableColumn<CustomerTm, String> param) {
                return new TableCell<CustomerTm, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            int rowIndex = getTableRow().getIndex();
                            Hyperlink deleteLink = new Hyperlink("Delete");
                            deleteLink.setOnAction(event -> {
                                deleteCustomer(rowIndex);
                            });

                            setGraphic(deleteLink);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
    }

    private void updateCustomer(){
        try {
            CustomerDto customerDto = new CustomerDto(
                    Integer.parseInt(customerText.getText()),
                    custAddressText.getText(),
                    emailText.getText(),
                    Integer.parseInt(mobileNoText.getText()),
                    custNameText.getText(),
                    nicText.getText(),
                    "Update",
                    "Delete"
            );

            String result = customerService.updateCustomer(customerDto);
            clear();
            loadAllCustomers();

        } catch (NumberFormatException ex) {
            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllCustomers() {
            try {
                colId.setCellValueFactory(new PropertyValueFactory<>("id"));
                colName.setCellValueFactory(new PropertyValueFactory<>("name"));
                colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
                colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
                colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
                colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));

                ObservableList<CustomerTm> data = FXCollections.observableArrayList();

                ArrayList<CustomerDto> customerModels = (ArrayList<CustomerDto>) customerService.getAllCustomers();

                for (CustomerDto customerDto : customerModels) {
                    CustomerTm customerTm = new CustomerTm(
                            customerDto.getId(),
                            customerDto.getAddress(),
                            customerDto.getEmail(),
                            customerDto.getMobile(),
                            customerDto.getName(),
                            customerDto.getNic(),
                            "Update",
                            "Delete"
                    );
                    data.add(customerTm);
                }

                tblCustomer.setItems(data);
            } catch (Exception ex) {
                Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
                new Alert(Alert.AlertType.ERROR, "Can not load all customers.").show();
            }
    }
    private void clear(){
        customerText.setText("");
        custAddressText.setText("");
        emailText.setText("");
        mobileNoText.setText("");
        custNameText.setText("");
        nicText.setText("");
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {

            int selectedIndex = tblCustomer.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                CustomerTm selectedCustomer = tblCustomer.getItems().get(selectedIndex);

                int customerId = selectedCustomer.getId();
                String customerName = selectedCustomer.getName();
                String customerAddress = selectedCustomer.getAddress();
                int customerMobile = selectedCustomer.getMobile();
                String customerNic = selectedCustomer.getNic();
                String customerEmail = selectedCustomer.getEmail();

                customerText.setText(String.valueOf(customerId));
                custNameText.setText(customerName);
                custAddressText.setText(customerAddress);
                mobileNoText.setText(String.valueOf(customerMobile));
                nicText.setText(customerNic);
                emailText.setText(customerEmail);
            }
        }
    }

    private void deleteCustomer(int customerId) {
        try {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Customer");
            confirmationAlert.setHeaderText("Delete Customer Confirmation");
            confirmationAlert.setContentText("Are you sure you want to delete customer with ID: " + customerId + "?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                String resultMessage = customerService.deleteCustomer(Integer.parseInt(customerText.getText()));
                loadAllCustomers();
                clear();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Customer Deleted");
                successAlert.setContentText(resultMessage);
                successAlert.showAndWait();
            }
        } catch (Exception ex) {
            Logger.getLogger(CustomerFormController.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Error deleting customer.").show();
        }
    }



}


