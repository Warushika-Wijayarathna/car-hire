package lk.ijse.carhire.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import lk.ijse.carhire.TableModel.CategoryTm;
import lk.ijse.carhire.TableModel.CustomerTm;
import lk.ijse.carhire.dto.CarCategoryDto;
import lk.ijse.carhire.dto.CustomerDto;
import lk.ijse.carhire.service.custom.CarCategoryService;
import lk.ijse.carhire.service.custom.Impl.CarCategoryServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.*;

public class CategoryFormCntroller {
    public Label categoryLabel;
    public TextField categoryIdText;
    public Label typeLabel;
    public TextField typeText;
    public TableView <CategoryTm> categoryTable;
    public Button categoryBtnClick;
    public Button backBtn;
    public Pane categoryForm;
    public TableColumn<CategoryTm, String> colId;
    public TableColumn<CategoryTm, String> colType;
    public TableColumn<CategoryTm,String> colUpdate;
    public TableColumn<CategoryTm,String> colDelete;

    private CarCategoryService carCategoryService = new CarCategoryServiceImpl();
    public void saveCategory(ActionEvent actionEvent) {
        addCategory();
    }

    private void addCategory() {
        try {
            CarCategoryDto carCategoryDto = new CarCategoryDto(
                    parseInt(categoryIdText.getText()),
                    typeText.getText(),
                    "Update",
                    "Delete"
            );

            String result = carCategoryService.addCarCategory(carCategoryDto);
            clear();
            loadAllCategories();


        } catch (NumberFormatException ex) {
            Logger.getLogger(CategoryFormCntroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategoryFormCntroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void backBtnClick(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/main_form.fxml"));
            Pane dashboard = loader.load();
            categoryForm.getChildren().clear();
            categoryForm.getChildren().add(dashboard);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void initialize() throws Exception {
        System.out.println("Category form just loaded!");
        List<CarCategoryDto> carCategoryDtos = carCategoryService.getAllCarCategory();
        setCellValueFactory();
        setCategory(carCategoryDtos);
    }

    private void setCellValueFactory() {
        System.out.println(colId);
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colUpdate.setCellFactory(new Callback<TableColumn<CategoryTm, String>, TableCell<CategoryTm, String>>() {

            @Override
            public TableCell<CategoryTm, String> call(TableColumn<CategoryTm, String> param) {
                return new TableCell<CategoryTm, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            int rowIndex = getTableRow().getIndex();
                            Hyperlink updateLink = new Hyperlink("Update");
                            updateLink.setOnAction(event -> {
                                updateCategory();
                            });

                            setGraphic(updateLink);
                        } else {
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        colDelete.setCellFactory(new Callback<TableColumn<CategoryTm, String>, TableCell<CategoryTm, String>>() {
            @Override
            public TableCell<CategoryTm, String> call(TableColumn<CategoryTm, String> param) {
                return new TableCell<CategoryTm, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty) {
                            int rowIndex = getTableRow().getIndex();
                            Hyperlink deleteLink = new Hyperlink("Delete");
                            deleteLink.setOnAction(event -> {
                                deleteCategory(rowIndex);
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

    private void deleteCategory(int categoryId) {
        try {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Delete Car Category");
            confirmationAlert.setHeaderText("Delete Car Category Confirmation");
            confirmationAlert.setContentText("Are you sure you want to delete category with ID: " + categoryId + "?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                String resultMessage = carCategoryService.deleteCarCategory(Integer.parseInt(categoryIdText.getText()));
                loadAllCategories();
                clear();
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setTitle("Success");
                successAlert.setHeaderText("Category Deleted");
                successAlert.setContentText(resultMessage);
                successAlert.showAndWait();
            }
        } catch (Exception ex) {
            Logger.getLogger(CategoryFormCntroller.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Error deleting category.").show();
        }
    }

    private void updateCategory() {
        try {
            CarCategoryDto carCategoryDto = new CarCategoryDto(
                    parseInt(categoryIdText.getText()),
                    typeText.getText(),
                    "Update",
                    "Delete"
            );

            String result = carCategoryService.updateCarCategory(carCategoryDto);
            clear();
            loadAllCategories();

        } catch (NumberFormatException ex) {
            Logger.getLogger(CategoryFormCntroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(CategoryFormCntroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllCategories() {
        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colType.setCellValueFactory(new PropertyValueFactory<>("type"));

            ObservableList<CategoryTm> data = FXCollections.observableArrayList();

            ArrayList<CarCategoryDto> categoryModels = (ArrayList<CarCategoryDto>) carCategoryService.getAllCarCategory();

            for (CarCategoryDto carCategoryDto: categoryModels) {
                CategoryTm categoryTm = new CategoryTm(
                        carCategoryDto.getId(),
                        carCategoryDto.getType(),

                        "Update",
                        "Delete"
                );
                data.add(categoryTm);
            }

            categoryTable.setItems(data);
        } catch (Exception ex) {
            Logger.getLogger(CategoryFormCntroller.class.getName()).log(Level.SEVERE, null, ex);
            new Alert(Alert.AlertType.ERROR, "Can not load all catogories.").show();
        }
    }

    private void setCategory(List<CarCategoryDto> carCategoryDtoList) {
            ObservableList<CategoryTm> observableList = FXCollections.observableArrayList();

            for (CarCategoryDto carCategoryDto : carCategoryDtoList) {
                var categoryTm = new CategoryTm(
                        carCategoryDto.getId(),
                        carCategoryDto.getType(),
                        "Update",
                        "Delete"
                );
                observableList.add(categoryTm);
            }
            System.out.println(observableList);
            if (categoryTable != null) {
                categoryTable.setItems(observableList);
            } else {
                new Alert(Alert.AlertType.ERROR, "categoryTable is null. Unable to set items.").show();
            }
    }

    private  void clear(){
        categoryIdText.setText("");
        typeText.setText("");
    }

    public void onMouseClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {

            int selectedIndex = categoryTable.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                CategoryTm selectedCustomer = categoryTable.getItems().get(selectedIndex);

                int categoryId = selectedCustomer.getId();
                String Type = selectedCustomer.getType();

                categoryIdText.setText(String.valueOf(categoryId));
                typeText.setText(Type);

            }
        }
    }
}
