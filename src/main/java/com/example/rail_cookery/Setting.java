package com.example.rail_cookery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Setting {

    @FXML
    private TextField f_name;

    @FXML
    private TextField l_name;

    @FXML
    private TextField p_no;

    @FXML
    private TextField e_mail;

    @FXML
    private PasswordField pass;

    @FXML
    private PasswordField con_pass;

    @FXML
    private Button submit_buttton;

    @FXML
    private Button reset_button;

    @FXML
    void pressed_reset_button(ActionEvent event) {
        f_name.clear();
        l_name.clear();
        p_no.clear();
        pass.clear();
        e_mail.clear();
        con_pass.clear();
    }

    @FXML
    void pressed_submit_button(ActionEvent event) {

    }

    @FXML
    void back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

}
