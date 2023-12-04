package lk.ijse.carhire.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lk.ijse.carhire.TableModel.CarTm;
import lk.ijse.carhire.TableModel.CustomerTm;
import lk.ijse.carhire.dto.CarCategoryDto;
import lk.ijse.carhire.dto.CarDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.entity.CarCategoryEntity;
import lk.ijse.carhire.service.custom.CarCategoryService;
import lk.ijse.carhire.service.custom.CarService;
import lk.ijse.carhire.service.custom.Impl.CarCategoryServiceImpl;
import lk.ijse.carhire.service.custom.Impl.CarServiceImpl;
import lk.ijse.carhire.service.custom.Impl.CustomerServiceImpl;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CarFormController {
    public Label carLabel;
    public Label plateLabel;
    public Label brandLabel;
    public Label modelLabel;
    public Label ppdayLabel;
    public TableView<CarTm> carTable;
    public Label yearLabel;
    public Button carBtnClick;
    public Label typeLabel;
    public TableColumn<CarTm, Integer> colId;
    public TableColumn<CarTm, String> colPlate;
    public TableColumn<CarTm, String> colBrand;
    public TableColumn<CarTm, String> colModel;
    public TableColumn<CarTm, Integer> colYear;
    public TableColumn<CarTm, String> colType;
    public TableColumn<CarTm, Double> colPricePerDay;
    public TableColumn<CarTm, String> colUpdate;
    public TableColumn<CarTm, String> colDelete;
    public Button backBtn;
    public Button carSearchButton;
    public Label searchTypeLabel;
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
    private TextField typeText;
    @FXML
    private TextField ppdayText;
    @FXML
    private Pane carForm;



    private CarService carService = new CarServiceImpl();
    private CarCategoryService carCategoryService = new CarCategoryServiceImpl();

    public CarFormController() throws Exception {
        carService = new CarServiceImpl();
        initialize();
    }

    private void initialize() throws Exception {
        System.out.println("Car form just loaded!");
        List<CarDto> carDtos = carService.getAllCar();
        setCellValueFactory();
        setCar(carDtos);
    }

    private void setCar(List<CarDto> carDtos) {
        ObservableList<CarTm> observableList = FXCollections.observableArrayList();

        for (CarDto carDto : carDtos) {
            CarCategoryEntity carCategoryEntity = new CarCategoryEntity();
            var carTm = new CarTm(
                    carDto.getId(),
                    carDto.getBrand(),
                    carDto.getModel(),
                    carDto.getPlateNo(),
                    carDto.getPriceperday(),
                    carDto.getYear(),
                    carCategoryEntity,
                    "Update",
                    "Delete"
            );
            observableList.add(carTm);
        }
        System.out.println(observableList);
        if (carTable != null) {
            carTable.setItems(observableList);
        } else {
            new Alert(Alert.AlertType.ERROR, "carTable is null. Unable to set items.").show();
        }
    }

    private void setCellValueFactory() {
        System.out.println(colId);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colPlate.setCellValueFactory(new PropertyValueFactory<>("plateNo"));
        colBrand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colModel.setCellValueFactory(new PropertyValueFactory<>("model"));
        colYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        colType.setCellValueFactory(new PropertyValueFactory<>("carCategoryEntity"));
        colUpdate.setCellFactory(new Callback<TableColumn<CarTm, String>, TableCell<CarTm, String>>() {
            @Override
            public TableCell<CarTm, String> call(TableColumn<CarTm, String> param) {
                return new TableCell<CarTm, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            int rowIndex = getTableRow().getIndex();
                            Hyperlink updateLink = new Hyperlink("update");
                            updateLink.setOnAction(event -> {
                                updateCar();
                            });

                            setGraphic(updateLink);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        colDelete.setCellFactory(new Callback<TableColumn<CarTm, String>, TableCell<CarTm, String>>() {
            @Override
            public TableCell<CarTm, String> call(TableColumn<CarTm, String> param) {
                return new TableCell<CarTm, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            int rowIndex = getTableRow().getIndex();
                            Hyperlink deleteLink = new Hyperlink("delete");
                            deleteLink.setOnAction(event -> {
                                deleteCar(rowIndex);
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

    private void deleteCar(int rowIndex) {
        try {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Car");
            confirmationAlert.setHeaderText("Delete Car Confirmation");
            confirmationAlert.setContentText("Are you sure you want to delete car with ID: " + carIdText + "?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                String resultMessage = carService.deleteCar(String.valueOf(Integer.parseInt(carIdText.getText())));
                loadAllCars();
                clear();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Car Deleted");
                successAlert.setContentText(resultMessage);
                successAlert.showAndWait();
            }
        } catch (Exception ex) {
            Logger.getLogger(CarFormController.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Error deleting car.").show();
        }
    }

    private void updateCar() {
        try {
            CarCategoryEntity carCategoryEntity = new CarCategoryEntity();
            CarDto carDto = new CarDto(
                    Integer.parseInt(carIdText.getText()),
                    brandText.getText(),
                    modelText.getText(),
                    plateText.getText(),
                    Double.parseDouble(ppdayText.getText()),
                    Integer.parseInt(yearText.getText()),
                    carCategoryEntity,
                    "Update",
                    "Delete"
            );

            String result = carService.updateCar(carDto);
            clear();
            loadAllCars();

        } catch (NumberFormatException ex) {
            Logger.getLogger(CarFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CarFormController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllCars() {

        try {
            CarCategoryEntity carCategoryEntity = new CarCategoryEntity();

            colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
            colPlate.setCellValueFactory(new PropertyValueFactory<>("PlateNo."));
            colBrand.setCellValueFactory(new PropertyValueFactory<>("Brand"));
            colModel.setCellValueFactory(new PropertyValueFactory<>("Model"));
            colYear.setCellValueFactory(new PropertyValueFactory<>("Year"));
            colType.setCellValueFactory(new PropertyValueFactory<>("Type"));

            ObservableList<CarTm> data = FXCollections.observableArrayList();

            ArrayList<CarDto> carModels = (ArrayList<CarDto>) carService.getAllCar();

            for (CarDto carDto : carModels) {
                CarTm carTm = new CarTm(
                        Integer.parseInt(carIdText.getText()),
                        brandText.getText(),
                        modelText.getText(),
                        plateText.getText(),
                        Double.parseDouble(ppdayText.getText()),
                        Integer.parseInt(yearText.getText()),
                        carCategoryEntity,
                        "Update",
                        "Delete"
                );
                data.add(carTm);
            }
            carTable.setItems(data);
        } catch (Exception ex) {
            Logger.getLogger(CarFormController.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Can not load all cars.").show();
        }
    }

    private void clear() {
        carIdText.setText("");
        brandText.setText("");
        modelText.setText("");
        plateText.setText("");
        ppdayText.setText("");
        yearText.setText("");
        typeText.setText("");
        searchTypeLabel.setText("");
    }

    public void saveCar(ActionEvent actionEvent) {
        addCar();
    }
    public void typeSearchBtnClick(ActionEvent actionEvent){
        try {
            String categoryId=typeText.getText();
            CarCategoryDto carCategoryDto=carCategoryService.getCarCategory(categoryId);
            if (categoryId!=null) {
                typeText.setText(carCategoryDto.getType());
            }else{
                String errorMessage = "Car with ID " + categoryId  + " not found!";
                JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
    }

    private void addCar() {
        try {
            CarCategoryEntity carCategoryEntity = new CarCategoryEntity();
            CarDto carDto = new CarDto(
                    Integer.parseInt(carIdText.getText()),
                    brandText.getText(),
                    modelText.getText(),
                    plateText.getText(),
                    Double.parseDouble(ppdayText.getText()),
                    Integer.parseInt(yearText.getText()),
                    carCategoryEntity,
                    "Update",
                    "Delete"
            );

            String result = carService.addCar(carDto);

        } catch (NumberFormatException ex) {
            Logger.getLogger(CarFormController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
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
