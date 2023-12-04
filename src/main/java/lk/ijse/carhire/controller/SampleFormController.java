package lk.ijse.carhire.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.carhire.TableModel.SampleTm;

public class SampleFormController {
    public TableView<SampleTm> tbl;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colName;
    public Label customerLabel;
    public TextField customerText;
    public Label custNameLabel;
    public TextField custNameText;
    public Label custAddressLabel;
    public TextField custAddressText;
    public Label NICLabel;
    public TextField nicText;
    public Label NICLabel1;
    public TextField mobileNoText;
    public Button saveButton;
    public Button backBtn;

    public void initialize() {
        setCellValueFactory();
//        List<CustomerDto> customerDtos = customerService.getAllCustomers();
    }

    private void setCellValueFactory() {

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//            colMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
//            colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
//            colUpdate.setCellValueFactory(new PropertyValueFactory<>("update"));
//            colDelete.setCellValueFactory(new PropertyValueFactory<>("delete"));
    }
    public void backBtnClick(ActionEvent actionEvent) {
    }
}
