package com.example.rail_cookery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserSignUpForm {

    @FXML
    private Button signin_button;

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

    public String img_path = "file:///C://Users//ABCD//Downloads/hacker.png";

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
    void pressed_sign_in(ActionEvent event) throws IOException {
        Parent loginParrent = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene loginScene = new Scene(loginParrent);
        Stage loginStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        loginStage.setScene(loginScene);
        loginStage.show();
    }

    @FXML
    void pressed_submit_button(ActionEvent event) {

        if(f_name.getText().isEmpty() || l_name.getText().isEmpty() || p_no.getText().isEmpty() || e_mail.getText().isEmpty()||pass.getText().isEmpty() || con_pass.getText().isEmpty()){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setContentText("Information is missing");
         alert.show();

     }

     else if (p_no.getText().length() != 11){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setContentText("Phone Number is Wrong");
         alert.show();
     }
     else if (!e_mail.getText().contains("@gmail.com")){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setContentText("Email is not correct");
         alert.show();
     }

     else if (pass.getText().length()<8){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setContentText("Password too short");

         alert.show();
     }

     else if(!pass.getText().equals(con_pass.getText())){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setContentText("Password did not match");
         alert.show();
     }

     else{
            InputStream input = null;
            try {
                input = new FileInputStream(img_path);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            DBConnect db = new DBConnect();
         Connection connection = db.connection();
         try {
             Statement statement = connection.createStatement();
             String query1 = "SELECT * FROM user_table WHERE user_phone_no = '"+p_no.getText()+"' || user_email = '"+e_mail.getText()+"'";
             //String query2 = "INSERT INTO user_table VALUES user_phone_no = '"+p_no.getText()+"' || user_email = '"+e_mail.getText()+"'";
             ResultSet resultSet = statement.executeQuery(query1);
             if(resultSet.next()){
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setContentText("Already Registered");
                 alert.show();

             }
             else{
                 statement.executeUpdate("INSERT INTO user_table (user_first_name,user_last_name,user_phone_no,user_email,user_password,image) VALUES('" + f_name.getText() + "', '" + l_name.getText() + "', '" + p_no.getText() + "', '" + e_mail.getText() + "','" + con_pass.getText() + "','" +input+ "')");
                 // System.out.println("Data Unique");
                 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                 alert.setContentText("You are member of Rail Cookery");

                 alert.show();


             }
         } catch (SQLException e) {
             e.printStackTrace();
         }


     }

    }



}

