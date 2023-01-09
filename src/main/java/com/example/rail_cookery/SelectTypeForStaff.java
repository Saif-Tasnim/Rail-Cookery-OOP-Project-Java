package com.example.rail_cookery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class SelectTypeForStaff implements Initializable {

    @FXML
    private RadioButton admin;

    @FXML
    private ToggleGroup select;

    @FXML
    private RadioButton staff;

    @FXML
    private RadioButton passenger;

    @FXML
    void home(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("staff_dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene((sc));
        st.show();
    }

    @FXML
    void press_admin(ActionEvent event) throws IOException {
    if(admin.isSelected()){
      Parent root = FXMLLoader.load(getClass().getResource("staff_admin_chat_controller.fxml"));
      Scene sc = new Scene(root);
      Stage st = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
      st.setScene(sc);
      st.show();
  }
    }

    @FXML
    void press_pas(ActionEvent event) throws IOException {
    if(passenger.isSelected()){
        Parent root = FXMLLoader.load(getClass().getResource("staff_client_chat_controller.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }
    }

    @FXML
    void press_staff(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        staff.setVisible(false);
    }
}
