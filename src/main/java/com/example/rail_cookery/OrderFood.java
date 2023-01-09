package com.example.rail_cookery;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.rail_cookery.HelloController.*;
import static com.example.rail_cookery.HelloController.l_name;
import static com.example.rail_cookery.UserCartList.total_sum;

import static com.example.rail_cookery.UserDashboard.count_b;
import static com.example.rail_cookery.UserDashboard.count_s;
import static com.example.rail_cookery.UserDashboard.count_c;
import static com.example.rail_cookery.UserDashboard.count_choc;
import static com.example.rail_cookery.UserDashboard.count_d;
import static com.example.rail_cookery.UserDashboard.count_f;



public class OrderFood implements Initializable {

    @FXML
    private Label profile_lb;

    @FXML
    private Button profile_pic;

    @FXML
    private Button dash;

    @FXML
    private Button cart;

    @FXML
    private Button setting;

    @FXML
    private Button log_out;

    @FXML
    private TextField phn_tf;

    @FXML
    private TextField email_tf;

    @FXML
    private TextField sum_tf;

    @FXML
    private ChoiceBox<String> cmp_choice;

    private String[] cmp_string = {"KA","KHA","GA","GHA","UMA","CA","CHA","JA","TA","THA","DA","DHA","TO","THO","NA"};
    String selected_cmp = "";

    @FXML
    private ChoiceBox<String> seat_choice;
    private String[] seat_array ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53","54","55","56","57","58","59","60"};
    int seat_no = 0;

    @FXML
    private RadioButton cod;

    @FXML
    private ToggleGroup select;

    @FXML
    private RadioButton bkash;

    @FXML
    private RadioButton nagad;

    @FXML
    private Button back;

    @FXML
    private Button proceed;

    private String payment;

    @FXML
    void cart_list(ActionEvent event) {

    }

    @FXML
    void pressed_back(ActionEvent event) throws IOException {
      Parent root = FXMLLoader.load(getClass().getResource("user_cart_list.fxml"));
      Scene sc = new Scene(root);
      Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
      st.setTitle("User Cart List");
      st.setScene(sc);
      st.show();
    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setTitle("Dashboard");
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setTitle("Home");
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_proceed(ActionEvent event) {
     if(selected_cmp.equals("") && seat_no == 0 && (!cod.isSelected() || !bkash.isSelected() || !nagad.isSelected())){
         Alert alert = new Alert(Alert.AlertType.WARNING);
         alert.setContentText("You have not enter sufficient info");
         alert.show();
     }

     else {
         boolean found = false;
         int total = count_b + count_c + count_choc + count_d + count_f + count_s;
         DBConnect db = new DBConnect();
         Connection con = db.connection();
         String q ="SELECT * FROM place_order WHERE phone_no = '"+phone+"' AND (status = 'Pending' OR status = 'Preparing')";
         try {
             Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(q);
             if(resultSet.next()){
                 found = true;
                 Alert alert = new Alert(Alert.AlertType.WARNING);
                 alert.setContentText("You have already a pending order. Go to Track Order");
                 alert.show();
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }

         if (found == false) {
             try {
                 String query = "INSERT INTO place_order(phone_no,cmp_name,seat_no,total_quantity,total_price,status,Payment)VALUES('" + phone + "','" + selected_cmp + "','" + seat_no + "','" + total + "','" + total_sum + "','Pending','" + payment + "')";
                 Statement statement = con.createStatement();
                 int i = statement.executeUpdate(query);
                 if (i == 1) {
                     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                     alert.setContentText("Your Order Successfully Delivered");
                     alert.show();
                     Parent root = FXMLLoader.load(getClass().getResource("confirm_order.fxml"));
                     Scene sc = new Scene(root);
                     Stage st = (Stage) ((Node) event.getSource()).getScene().getWindow();
                     st.setScene(sc);
                     st.show();
                 } else {
                     Alert alert = new Alert(Alert.AlertType.WARNING);
                     alert.setContentText("You have error in server");
                     alert.show();
                 }

             } catch (SQLException e) {
                 e.printStackTrace();
             } catch (IOException e) {
                 e.printStackTrace();
             }


         }

     }
    }

    @FXML
    void pressed_radio(ActionEvent event) {
     if(cod.isSelected()){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setContentText("COD selected");
         alert.show();
         payment = "COD";
     }

      else if(bkash.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("BKASH selected");
            alert.show();
            payment = "Bkash";
        }

       else if(nagad.isSelected()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("NAGAD selected");
            alert.show();
            payment = "Nagad";
        }
    }

    @FXML
    void pressed_setting(ActionEvent event) {

    }

    void getCmp(ActionEvent event){
      selected_cmp = cmp_choice.getValue();
    }

    void  getSeat(ActionEvent event){
        String seat = seat_choice.getValue();
         seat_no = Integer.parseInt(seat);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        String new_f = "", new_l = "";
        String first = f_name.substring(0, 1);
        String afterfirst = f_name.substring(1);
        new_f += first.toUpperCase() + afterfirst + " ";


        String last = l_name.substring(0, 1);
        String afterlast = l_name.substring(1);
        new_l += last.toUpperCase() + afterlast + " ";

        profile_lb.setText(new_f + " " + new_l);

        cmp_choice.getItems().addAll(cmp_string);
        cmp_choice.setOnAction(this::getCmp);

        seat_choice.getItems().addAll(seat_array);
        seat_choice.setOnAction(this::getSeat);

        sum_tf.setText(Float.toString(total_sum));
        phn_tf.setText(phone);
        email_tf.setText(email);

        String query = "DELETE From place_order WHERE status = 'Done'";
        DBConnect db = new DBConnect();
        Connection con = db.connection();
        try {
            Statement state = con.createStatement();
            state.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
