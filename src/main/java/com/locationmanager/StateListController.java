package com.locationmanager;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class StateListController implements Initializable {
    public static Scene createScene() throws Exception {
        URL url = CityListController.class.getResource("state_list.fxml");
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        
        return scene;
    }
    
    private ObservableList<StateData> stateList;

    @FXML
    private TableView<StateData> tableStates;

    @FXML
    private TableColumn<StateData, Number> columnId;

    @FXML
    private TableColumn<StateData, String> columnName;

    @FXML
    private TableColumn<StateData, String> columnAcronym;

    

    @FXML
    void btCitiesClick(ActionEvent event) throws Exception {
        Stage newStage = new Stage();
        newStage.setScene(CityListController.createScene());

        newStage.setTitle("City List");
        newStage.showAndWait();
    }

    @FXML
    void btStateClick(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setContentText("State name:");
        dialog.setTitle("Create new State");
        dialog.setHeaderText("");
        
        dialog.showAndWait();

        String result = dialog.getResult();

        if (result == null) {
            return;
        }

        if (result.isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("Invalid Input!");
            alert.showAndWait();

            return;
        }

        DatabaseController.saveState(new StateData(result));

        loadStateList();
    }

    private void loadStateList() {
        stateList  = FXCollections.observableArrayList();
        tableStates.setItems(stateList);
        List<StateData> data = DatabaseController.getStates();

        for (StateData stateData : data) {
            stateList.add(stateData);
        }

        tableStates.refresh();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnId.setCellValueFactory(value -> new SimpleLongProperty(value.getValue().getId()));
        columnName.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getStateName()));
        columnAcronym.setCellValueFactory(value -> new SimpleStringProperty(value.getValue().getStateAcronym().toUpperCase()));
        

        loadStateList();
    }

}
