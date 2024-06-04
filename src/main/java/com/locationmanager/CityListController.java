package com.locationmanager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.locationmanager.model.CityData;
import com.locationmanager.model.StateData;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CityListController implements Initializable {

    public static Scene createScene() throws Exception {
        URL url = CityListController.class.getResource("city_list.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);

        return scene;
    }

    private ObservableList<CityData> cityList;

    @FXML
    private ComboBox<StateData> cbState;

    @FXML
    private TextField tfName;

    @FXML
    private TableView<CityData> tableCities;

    @FXML
    private TableColumn<CityData, Number> columnID;

    @FXML
    private TableColumn<CityData, String> columnName;

    @FXML
    private TableColumn<CityData, String> columnState;

    @FXML
    void btSaveClick(ActionEvent event) {
        String name = tfName.getText();

        if (name.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Invalid Name!");
            alert.showAndWait();
            return;
        }

        StateData state = cbState.getValue();

        if (state == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Invalid State!");
            alert.showAndWait();
            return;
        }

        DatabaseController.saveCity(new CityData(name, state));

        loadCityList();
    }

    private void loadStateList() {
        cbState.getItems().addAll(DatabaseController.getStates());
    }

    private void loadCityList() {
        cityList  = FXCollections.observableArrayList();
        tableCities.setItems(cityList);
        List<CityData> data = DatabaseController.getCities();

        for (CityData cityData : data) {
            cityList.add(cityData);
        }

        tableCities.refresh();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnID.setCellValueFactory(value -> new SimpleLongProperty(value.getValue().getId()));
        columnName.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCityName()));
        columnState.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getCityState().toString()));
        
        loadCityList();
        loadStateList();
    }

}
