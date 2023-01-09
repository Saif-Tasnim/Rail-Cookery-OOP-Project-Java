package com.example.rail_cookery;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
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
import static java.lang.Math.abs;


public class UserCartList implements Initializable {

    @FXML
    private Label profile_lb;
    @FXML
    private Circle circle;

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
    private Button back;

    @FXML
    private Button proceed;


    @FXML
    private HBox b_h;

    @FXML
    private Label n_b;

    @FXML
    private Label b_q;

    @FXML
    private Label b_p;

    @FXML
    private HBox s_h;

    @FXML
    private Label n_s;

    @FXML
    private Label s_q;

    @FXML
    private Label s_p;

    @FXML
    private HBox c_h;

    @FXML
    private Label n_c;

    @FXML
    private Label c_q;

    @FXML
    private Label c_p;

    @FXML
    private HBox d_h;

    @FXML
    private Label n_d;

    @FXML
    private Label d_q;

    @FXML
    private Label d_p;

    @FXML
    private HBox choc_h;

    @FXML
    private Label n_choc;

    @FXML
    private Label choc_q;

    @FXML
    private Label choc_p;

    @FXML
    private HBox f_h;

    @FXML
    private Label n_f;

    @FXML
    private Label f_q;

    @FXML
    private Label f_p;

    @FXML
    private HBox sum_h;

    @FXML
    private Label n_sum;

    @FXML
    private Label total_price;


    public static float total_sum;


    public float sum(){
        return  ((count_b*burger_price)+(count_s*sand_price)+(count_c*chips_price)+(count_choc*choc_price)+(count_d*drinks_price)+(count_f*fry_price));
    }

    public float vat(){
        return (float) (sum()*0.15);
    }

    public float totalSum(){
        total_sum = vat()+sum();
        return total_sum;
    }

    @FXML
    void cart_list(ActionEvent event) {

    }

    @FXML
    void pressed_back(ActionEvent event) throws IOException {
        Parent dash_p = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene dash_s = new Scene(dash_p);
        Stage dash_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        dash_st.setScene(dash_s);
        dash_st.show();
    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException {
        Parent dash_p = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene dash_s = new Scene(dash_p);
        Stage dash_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        dash_st.setScene(dash_s);
        dash_st.show();
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException{
        count_b = 0;
        count_c = 0;
        count_choc = 0;
        count_d = 0;
        count_f = 0;
        count_s = 0;
        Parent log_p = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene log_s = new Scene(log_p);
        Stage log_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        log_st.setScene(log_s);
        log_st.show();
    }

    @FXML
    void pressed_proceed(ActionEvent event) throws IOException{
    if(totalSum()==0){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Please Add Some Food in Cart List");
        Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }
    else{
        Parent root = FXMLLoader.load(getClass().getResource("order_food.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }
    }

    @FXML
    void pressed_setting(ActionEvent event) {

    }

    @FXML
    void update_profile_pic(ActionEvent event) {

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
        circle.setFill(new ImagePattern(image));


        DBConnect db = new DBConnect();
        Connection con = db.connection();


        cart.setUnderline(true);
        if(count_b == 0 && count_s == 0 && count_c == 0 && count_choc == 0 && count_d == 0 && count_f == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not select any item");
            alert.show();
            proceed.setVisible(false);

        }

    else {
            proceed.setVisible(true);
            if (count_b != 0) {
                b_h.setVisible(true);
                n_b.setText("Burger");
                b_q.setText(Integer.toString(count_b));
                b_p.setText(Integer.toString(count_b) + " X " + Float.toString(burger_price) + " = " + Float.toString(count_b * burger_price));
                String query = "INSERT INTO food_list(phone_no,status,item_name,item_quantity) VALUES('"+phone+"','Pending','Burger','"+count_b+"')";
                try {
                    Statement statement = con.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int quan = 0;
                String qu = "SELECT * FROM food_history Where name = 'Burger'";
                try {
                    Statement statement = con.createStatement();
                    ResultSet res = statement.executeQuery(qu);
                    if(res.next()) {
                         quan = res.getInt(3);
                    }
                    int a = abs(count_b - quan);
                    String q = "UPDATE food_history SET quantity = "+a+" WHERE name = 'Burger'";
                    statement.executeUpdate(q);

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
            if (count_s != 0) {
                s_h.setVisible(true);
                n_s.setText("Sandwich");
                s_q.setText(Integer.toString(count_s));
                s_p.setText(Integer.toString(count_s) + " X " + Float.toString(sand_price) + " = " + Float.toString(count_s * sand_price));
                String query = "INSERT INTO food_list(phone_no,status,item_name,item_quantity) VALUES('"+phone+"','Pending','Sandwich','"+count_s+"')";
                try {
                    Statement statement = con.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                int quan = 0;
                String qu = "SELECT * FROM food_history Where name = 'Sandwich'";
                try {
                    Statement statement = con.createStatement();
                    ResultSet res = statement.executeQuery(qu);
                    if(res.next()) {
                        quan = res.getInt(3);
                    }
                    int a = abs(count_s - quan);
                    String q = "UPDATE food_history SET quantity = "+a+" WHERE name = 'Sandwich'";
                    statement.executeUpdate(q);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (count_c != 0) {
                c_h.setVisible(true);
                n_c.setText("Chips");
                c_q.setText(Integer.toString(count_c));
                c_p.setText(Integer.toString(count_c) + " X " + Float.toString(chips_price) + " = " + Float.toString(count_c * chips_price));
                String query = "INSERT INTO food_list(phone_no,status,item_name,item_quantity) VALUES('"+phone+"','Pending','Chips','"+count_c+"')";
                try {
                    Statement statement = con.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int quan = 0;
                String qu = "SELECT * FROM food_history Where name = 'Chips'";
                try {
                    Statement statement = con.createStatement();
                    ResultSet res = statement.executeQuery(qu);
                    if(res.next()) {
                        quan = res.getInt(3);
                    }
                    int a = abs(count_c - quan);
                    String q = "UPDATE food_history SET quantity = "+a+" WHERE name = 'Chips'";
                    statement.executeUpdate(q);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (count_choc != 0) {
                choc_h.setVisible(true);
                n_choc.setText("Chocolate");
                choc_q.setText(Integer.toString(count_choc));
                choc_p.setText(Integer.toString(count_choc) + " X " + Float.toString(choc_price) + " = " + Float.toString(count_choc * choc_price));
                String query = "INSERT INTO food_list(phone_no,status,item_name,item_quantity) VALUES('"+phone+"','Pending','Chocolate','"+count_choc+"')";
                try {
                    Statement statement = con.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int quan = 0;
                String qu = "SELECT * FROM food_history Where name = 'Chocolate'";
                try {
                    Statement statement = con.createStatement();
                    ResultSet res = statement.executeQuery(qu);
                    if(res.next()) {
                        quan = res.getInt(3);
                    }
                    int a = abs(count_choc - quan);
                    String q = "UPDATE food_history SET quantity = "+a+" WHERE name = 'Chocolate'";
                    statement.executeUpdate(q);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (count_d != 0) {
                d_h.setVisible(true);
                n_d.setText("Drinks");
                d_q.setText(Integer.toString(count_d));
                d_p.setText(Integer.toString(count_d) + " X " + Float.toString(drinks_price) + " = " + Float.toString(count_d * drinks_price));
                String query = "INSERT INTO food_list(phone_no,status,item_name,item_quantity) VALUES('"+phone+"','Pending','Drink','"+count_d+"')";
                try {
                    Statement statement = con.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int quan = 0;
                String qu = "SELECT * FROM food_history Where name = 'Drinks'";
                try {
                    Statement statement = con.createStatement();
                    ResultSet res = statement.executeQuery(qu);
                    if(res.next()) {
                        quan = res.getInt(3);
                    }
                    int a = abs(count_d - quan);
                    String q = "UPDATE food_history SET quantity = "+a+" WHERE name = 'Drinks'";
                    statement.executeUpdate(q);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (count_f != 0) {
                f_h.setVisible(true);
                n_f.setText("Fry");
                f_q.setText(Integer.toString(count_f));
                f_p.setText(Integer.toString(count_f) + " X " + Float.toString(fry_price) + " = " + Float.toString(count_f * fry_price));
                String query = "INSERT INTO food_list(phone_no,status,item_name,item_quantity) VALUES('"+phone+"','Pending','Fry','"+count_f+"')";
                try {
                    Statement statement = con.createStatement();
                    statement.executeUpdate(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int quan = 0;
                String qu = "SELECT * FROM food_history Where name = 'Fries'";
                try {
                    Statement statement = con.createStatement();
                    ResultSet res = statement.executeQuery(qu);
                    if(res.next()) {
                        quan = res.getInt(3);
                    }
                    int a = abs(count_f - quan);
                    String q = "UPDATE food_history SET quantity = "+a+" WHERE name = 'Fries'";
                    statement.executeUpdate(q);

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            proceed.setText("Pay To "+totalSum());





        }
    }
}

