package com.example.rail_cookery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.rail_cookery.HelloController.*;
import static com.example.rail_cookery.HelloController.l_name;
import static com.example.rail_cookery.UserDashboard.*;

public class ConfirmOrder implements Initializable {

    @FXML
    private Circle ca;
    @FXML
    private Label profile_lb;

    @FXML
    private Button dash;

    @FXML
    private Button cart;

    @FXML
    private Button setting;

    @FXML
    private Button log_out;

    @FXML
    private Label total_q;

    @FXML
    private Label total_p;

    @FXML
    private Label payment_status;

    @FXML
    private Label order_status;


    @FXML
    void cart_list(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("user_cart_list.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException {
        count_b = 0;
        count_c = 0;
        count_choc = 0;
        count_d = 0;
        count_f = 0;
        count_s = 0;

        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_setting(ActionEvent event) {

    }

    @FXML
    void pressed_back(ActionEvent event) throws IOException {
        count_b = 0;
        count_c = 0;
        count_choc = 0;
        count_d = 0;
        count_f = 0;
        count_s = 0;

        Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();

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

        Image image = new Image(imagepath);
        ca.setFill(new ImagePattern(image));

        DBConnect db = new DBConnect();
        Connection con = db.connection();
        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM place_order WHERE phone_no = '"+phone+"' AND status = 'Pending'";
            ResultSet res = statement.executeQuery(query);
            if(res.next()){
                int total = res.getInt(4);
                float price = res.getFloat(5);
                String paid = res.getString(7);
                String status = res.getString(6);

                total_q.setText("Total Quantities : "+String.valueOf(total));
                total_p.setText("Total Price : "+String.valueOf(price));
                if(paid.equals("COD")){
                    payment_status.setText("Payment Status : UNPAID");
                }
                else
                    payment_status.setText("Payment Status : PAID");
                order_status.setText("Order Status : Pending");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
