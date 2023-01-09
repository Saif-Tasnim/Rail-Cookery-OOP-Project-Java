package com.example.rail_cookery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;


import static com.example.rail_cookery.UserDashboard.count_b;
import static com.example.rail_cookery.UserDashboard.count_s;
import static com.example.rail_cookery.UserDashboard.count_c;
import static com.example.rail_cookery.UserDashboard.count_choc;
import static com.example.rail_cookery.UserDashboard.count_d;
import static com.example.rail_cookery.UserDashboard.count_f;


public class HelloController implements Initializable {
    public  static ArrayList<User> users = new ArrayList<>();

    @FXML
    private TextField user_tf;

    @FXML
    private PasswordField pass_tf;

    @FXML
    private Button sign_in;

    @FXML
    private Button sign_up;


    public static String f_name;
    public static String l_name;
    public static String email;
    public static int id;
    public static String phone;
    public static  Blob b;





    @FXML
    void pressed_sign_in(ActionEvent event) throws IOException{
        DBConnect db = new DBConnect();

        if(user_tf.getText().equals("Admin")  && pass_tf.getText().equals("admin")) {
           Parent adminParrent = FXMLLoader.load(getClass().getResource("admin_dashboard.fxml")) ;
            Scene adminViewScene = new Scene(adminParrent);
            Stage userStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            userStage.setScene(adminViewScene);
            userStage.show();

        }

    else if(db != null){
         Connection con = db.connection();
// SELECT* FROM user_table WHERE user_phone_no = '"+ phoneNo+ "' && user_password = '"+passWord+ "'");
         try {
             Statement statement =con.createStatement();
             String query = "SELECT* FROM user_table WHERE user_phone_no = '"+ user_tf.getText()+ "' && user_password = '"+pass_tf.getText()+ "'";
             ResultSet res = statement.executeQuery(query);

             if(res.next()){
                 f_name = res.getString(2);
                 l_name = res.getString(3);
                 email = res.getString(5);
                 phone = res.getString(4);
                 User newUser = new User();
                 newUser.name = f_name;
                 users.add(newUser);


                 Parent userParrent = null;
                 try {
                  userParrent = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
                  Scene userScene = new Scene(userParrent);
                  Stage userStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                  userStage.setScene(userScene);
                  userStage.show();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
//
             }

             String query2 = "SELECT* FROM staff_table WHERE staff_id = '"+ user_tf.getText()+ "' && pass = '"+pass_tf.getText()+ "'";
             ResultSet re2 = statement.executeQuery(query2);

             if(re2.next()){
                 id = re2.getInt(1);
                 f_name = re2.getString(2);
                 l_name = re2.getString(3);
                 email = re2.getString(5);
                 b=re2.getBlob(12);

                 Parent staffParrent = null;
                 try {
                     staffParrent = FXMLLoader.load(getClass().getResource("staff_dashboard.fxml"));
                     Scene staffScene = new Scene(staffParrent);
                     Stage staffStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                     staffStage.setScene(staffScene);
                     staffStage.show();
                 }catch (IOException e){
                     e.printStackTrace();
                 }
             }


             else{
                 System.out.println("No Data Found in Database");
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }


    }

    @FXML
    void pressed_sign_up(ActionEvent event) throws IOException {

        Parent userViewParrent = FXMLLoader.load(getClass().getResource("user_sign_up_form.fxml"));
        Scene userViewScene = new Scene(userViewParrent);
        Stage userStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        userStage.setScene(userViewScene);
        userStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        count_b = 0;
        count_c = 0;
        count_d = 0;
        count_choc = 0;
        count_f = 0;
        count_s = 0;
    }
}
