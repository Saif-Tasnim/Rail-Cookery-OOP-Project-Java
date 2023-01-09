package com.example.rail_cookery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.lang.annotation.Inherited;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StaffRegister implements Initializable {

    @FXML
    private Button add;

    @FXML
    private TextField f_tf;

    @FXML
    private TextField l_tf;

    @FXML
    private TextField nid_tf;

    @FXML
    private TextField phn_tf;

    @FXML
    private TextField email_tf;

    @FXML
    private PasswordField pass_tf;

    @FXML
    private TextField vill;

    @FXML
    private TextField thana;

    @FXML
    private TextField po;

    @FXML
    private TextField dst;

    @FXML
    private Button proceed;

    @FXML
    private Button reset;

    @FXML
    private Circle circle;

    @FXML
    private Button add_pic;

    @FXML
    private TextField st_tf;


    String imagepath;
    InputStream input;

    DBConnect db = new DBConnect();

    @FXML
    void presed_reset(ActionEvent event) {
     f_tf.clear();
     l_tf.clear();
     phn_tf.clear();
     email_tf.clear();
     nid_tf.clear();
     pass_tf.clear();
     vill.clear();
     thana.clear();
     po.clear();
     dst.clear();
     st_tf.clear();


    }

    @FXML
    void select_pic(ActionEvent event){
        FileChooser file = new FileChooser();
        file.setTitle("Open File");
        File file1 = file.showOpenDialog(new Stage());
        if(file1 != null){
            imagepath = "file:///"+file1.getPath();
            Image image = new Image(imagepath);

            circle.setFill(new ImagePattern(image));

            try {
                input = new FileInputStream(imagepath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void pressed_add_staff(ActionEvent event) {

    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException {
        Parent dash = FXMLLoader.load(getClass().getResource("admin_dashboard.fxml"));
        Scene dash_s = new Scene(dash);
        Stage dash_st = (Stage) ((Node)event.getSource()).getScene().getWindow();
        dash_st.setScene(dash_s);
        dash_st.show();

    }

    @FXML
    void pressed_history(ActionEvent event) throws IOException {
        Parent order = FXMLLoader.load(getClass().getResource("order_history.fxml"));
        Scene  order_s = new Scene(order);
        Stage order_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        order_st.setScene(order_s);
        order_st.show();
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException {
     Parent log = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
     Scene log_s = new Scene(log);
     Stage log_st = (Stage)((Node)event.getSource()).getScene().getWindow();
     log_st.setScene(log_s);
     log_st.show();
    }

    @FXML
    void pressed_procced(ActionEvent event) {
        if(!f_tf.getText().isEmpty() && !l_tf.getText().isEmpty()&&!phn_tf.getText().isEmpty()&&!email_tf.getText().isEmpty()&&!nid_tf.getText().isEmpty()&&!pass_tf.getText().isEmpty()&&!vill.getText().isEmpty()&&!thana.getText().isEmpty()&&!po.getText().isEmpty()&&!dst.getText().isEmpty()) {
            Connection con = db.connection();

            try {
                Statement statement = con.createStatement();
                String query = "INSERT INTO staff_table (staff_id,f_name,l_name,nid_no,phn_no,email,pass,village,thana,post,district,picture) VALUES('"+st_tf.getText()+"','"+f_tf.getText()+"','"+l_tf.getText()+"','"+nid_tf.getText()+"','"+phn_tf.getText()+"','"+email_tf.getText()+"','"+pass_tf.getText()+"','"+vill.getText()+"','"+thana.getText()+"','"+po.getText()+"','"+dst.getText()+"','"+input+"')";
//                ResultSet res = statement.executeUpdate(query);
                 statement.executeUpdate(query);
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setContentText("New Staff Successfully Added");
                 alert.show();
            } catch (SQLException e) {
                System.out.println("Something is error  : "+e);
            }
        }

        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Information Missing ...");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        add.setUnderline(true);
    }
}
