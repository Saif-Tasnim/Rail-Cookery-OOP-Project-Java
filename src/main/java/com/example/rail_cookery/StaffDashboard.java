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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.paint.ImagePattern;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.rail_cookery.HelloController.*;

public class StaffDashboard implements Initializable {

    @FXML
    private Label profile_lb;


    @FXML
    private Circle ca;


    @FXML
    private Button dash;

    @FXML
    private Button cart;

    @FXML
    private Button setting;

    @FXML
    private Button log_out;

    @FXML
    private Label greet_lb2;

    @FXML
    private TextField avaible_burg;

    @FXML
    private Button burger_plus;

    @FXML
    private Label burger_lb;

    @FXML
    private Button burger_minus;

    @FXML
    private Button burger_cart;

    @FXML
    private TextField available_sand;

    @FXML
    private Button sand_plus;

    @FXML
    private Label sand_lb;

    @FXML
    private Button sand_minus;

    @FXML
    private Button sand_cart;

    @FXML
    private TextField chips_tf;

    @FXML
    private Button chips_plus;

    @FXML
    private Label chips_lb;

    @FXML
    private Button chips_minus;

    @FXML
    private Button chips_cart;

    @FXML
    private TextField choc_tf;

    @FXML
    private Button choc_plus;

    @FXML
    private Label choc_lb;

    @FXML
    private Button choc_minus;

    @FXML
    private Button choc_cart;

    @FXML
    private TextField drinks_tf;

    @FXML
    private Button drinks_plus;

    @FXML
    private Label drinks_lb;

    @FXML
    private Button drinks_minus;

    @FXML
    private Button drinks_cart;

    @FXML
    private TextField fry_tf;

    @FXML
    private Button fry_plus;

    @FXML
    private Label fry_lb;

    @FXML
    private Button fry_minus;

    @FXML
    private Button fry_cart;
    @FXML
    private Image image;

    public static  String img = "file:///C:/Users/ABCD/Downloads/cleaning-staff.png";



    @FXML
    void cart_list(ActionEvent event) throws IOException {
    String q = "SELECT * FROM place_order WHERE status = 'Pending'";
    String q2 = "SELECT * FROM place_order WHERE status = 'Preparing'";
    DBConnect db = new DBConnect();
    Connection con = db.connection();
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(q);
            if(res.next()){
                Parent root = FXMLLoader.load(getClass().getResource("get_order.fxml"));
                Scene sc = new Scene(root);
                Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
                st.setScene(sc);
                st.show();
            }

            ResultSet res2 = statement.executeQuery(q2);
             if(res2.next()){
                Parent root = FXMLLoader.load(getClass().getResource("pending_list.fxml"));
                Scene sc = new Scene(root);
                Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
                st.setScene(sc);
                st.show();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    /// update burger quantity
    @FXML
    void pressed_burger_cart(ActionEvent event) {
        int count;
        if (burger_lb.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not add quantity");
            alert.show();

        } else {
            count = Integer.parseInt(burger_lb.getText());
            count+=1;
            DBConnect db = new DBConnect();
            Connection con = db.connection();
            try {
                Statement statement = con.createStatement();
                String query = "UPDATE food_history SET quantity = " + count+ " WHERE name = 'Burger'";
                statement.executeUpdate(query);
                avaible_burg.setText("Available :" + count);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Quantity updated");
                alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void pressed_burger_minus(ActionEvent event) {
        if (burger_lb.getText().equals("") || burger_lb.getText().equals("0")) {
            burger_minus.setDisable(true);
        } else {
            burger_minus.setDisable(false);
            int count = Integer.parseInt(burger_lb.getText());
            count--;
            burger_lb.setText(Integer.toString(count));

        }
    }

    @FXML
    void pressed_burger_plus(ActionEvent event) {
        if (burger_lb.getText().equals("")) {
            String[] a = avaible_burg.getText().split(":");
            int count = Integer.parseInt(a[1]);
            count++;
            burger_lb.setText(Integer.toString(count));
        } else {
            int count = Integer.parseInt(burger_lb.getText());
            count++;
            burger_lb.setText(Integer.toString(count));
        }
    }

    @FXML
    void pressed_chips_cart(ActionEvent event) {
        int count;
        if (chips_lb.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not add quantity");
            alert.show();

        } else {

            count = Integer.parseInt(chips_lb.getText());
            count++;
            DBConnect db = new DBConnect();
            Connection con = db.connection();
            try {
                Statement statement = con.createStatement();
                String query = "UPDATE food_history SET quantity = " + count + " WHERE name = 'Chips'";
                statement.executeUpdate(query);
                chips_tf.setText("Available :" + count);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Quantity updated");
                alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void pressed_chips_minus(ActionEvent event) {
        if (chips_lb.getText().equals("") || chips_lb.getText().equals("0")) {
            chips_minus.setDisable(true);
        } else {
            chips_minus.setDisable(false);
            int count = Integer.parseInt(chips_lb.getText());
            count--;
            chips_lb.setText(Integer.toString(count));

        }
    }

    @FXML
    void pressed_chips_plus(ActionEvent event) {
        if (chips_lb.getText().equals("")) {
            String[] a = chips_tf.getText().split(":");
            int count = Integer.parseInt(a[1]);
            count++;
            chips_lb.setText(Integer.toString(count));
        } else {
            int count = Integer.parseInt(chips_lb.getText());
            count++;
            chips_lb.setText(Integer.toString(count));
        }
    }

    @FXML
    void pressed_choc_cart(ActionEvent event) {
        int count;
        if (choc_lb.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not add quantity");
            alert.show();

        } else {
            count = Integer.parseInt(choc_lb.getText());
            count+=1;
            DBConnect db = new DBConnect();
            Connection con = db.connection();
            try {
                Statement statement = con.createStatement();
                String query = "UPDATE food_history SET quantity = " + count + " WHERE name = 'Chocolate'";
                statement.executeUpdate(query);
                choc_tf.setText("Available :" + count);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Quantity updated");
                alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void pressed_choc_minus(ActionEvent event) {
        if (choc_lb.getText().equals("") || choc_lb.getText().equals("0")) {
            choc_minus.setDisable(true);
        } else {
            choc_minus.setDisable(false);
            int count = Integer.parseInt(choc_lb.getText());
            count--;
            choc_lb.setText(Integer.toString(count));

        }
    }

    @FXML
    void pressed_choc_plus(ActionEvent event) {
        if (choc_lb.getText().equals("")) {
            String[] a = choc_tf.getText().split(":");
            int count = Integer.parseInt(a[1]);
            count++;
            choc_lb.setText(Integer.toString(count));
        } else {
            int count = Integer.parseInt(choc_lb.getText());
            count++;
            choc_lb.setText(Integer.toString(count));
        }
    }

    @FXML
    void pressed_dashboard(ActionEvent event) {

    }

    @FXML
    void pressed_drinks_cart(ActionEvent event) {
        int count;
        if (drinks_lb.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not add quantity");
            alert.show();

        } else {
            count = Integer.parseInt(drinks_lb.getText());
            count+=1;
            DBConnect db = new DBConnect();
            Connection con = db.connection();
            try {
                Statement statement = con.createStatement();
                String query = "UPDATE food_history SET quantity = " + count + " WHERE name = 'Drinks'";
                statement.executeUpdate(query);
                drinks_tf.setText("Available :" + count);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Quantity updated");
                alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void pressed_drinks_minus(ActionEvent event) {
        if (drinks_lb.getText().equals("") || drinks_lb.getText().equals("0")) {
            drinks_minus.setDisable(true);
        } else {
            drinks_minus.setDisable(false);
            int count = Integer.parseInt(drinks_lb.getText());
            count--;
            drinks_lb.setText(Integer.toString(count));

        }
    }

    @FXML
    void pressed_drinks_plus(ActionEvent event) {
        if (drinks_lb.getText().equals("")) {
            String[] a = drinks_tf.getText().split(":");
            int count = Integer.parseInt(a[1]);
            count++;
            drinks_lb.setText(Integer.toString(count));
        } else {
            int count = Integer.parseInt(drinks_lb.getText());
            count++;
            drinks_lb.setText(Integer.toString(count));
        }
    }

    @FXML
    void pressed_fry_cart(ActionEvent event) {
        int count;
        if (fry_lb.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not add quantity");
            alert.show();

        } else {
            count = Integer.parseInt(fry_lb.getText());
            count+=1;
            DBConnect db = new DBConnect();
            Connection con = db.connection();
            try {
                Statement statement = con.createStatement();
                String query = "UPDATE food_history SET quantity = " + count + " WHERE name = 'Fries'";
                statement.executeUpdate(query);
                fry_tf.setText("Available :" + count);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Quantity updated");
                alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void pressed_fry_minus(ActionEvent event) {
        if (fry_lb.getText().equals("") || fry_lb.getText().equals("0")) {
            fry_minus.setDisable(true);
        } else {
            fry_minus.setDisable(false);
            int count = Integer.parseInt(fry_lb.getText());
            count--;
            fry_lb.setText(Integer.toString(count));

        }
    }

    @FXML
    void pressed_fry_plus(ActionEvent event) {
        if (fry_lb.getText().equals("")) {
            String[] a = fry_tf.getText().split(":");
            int count = Integer.parseInt(a[1]);
            count++;
            fry_lb.setText(Integer.toString(count));
        } else {
            int count = Integer.parseInt(fry_lb.getText());
            count++;
            fry_lb.setText(Integer.toString(count));
        }
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_sand_cart(ActionEvent event) {
        int count;
        if (sand_lb.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not add quantity");
            alert.show();

        } else {
            count = Integer.parseInt(sand_lb.getText());
            count+=1;
            DBConnect db = new DBConnect();
            Connection con = db.connection();
            try {
                Statement statement = con.createStatement();
                String query = "UPDATE food_history SET quantity = " + count + " WHERE name = 'Sandwich'";
                statement.executeUpdate(query);
                available_sand.setText("Available :" + count);
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Quantity updated");
                alert.show();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void pressed_sand_minus(ActionEvent event) {
        if (sand_lb.getText().equals("") || sand_lb.getText().equals("0")) {
            sand_minus.setDisable(true);
        } else {
            sand_minus.setDisable(false);
            int count = Integer.parseInt(sand_lb.getText());
            count--;
            sand_lb.setText(Integer.toString(count));

        }
    }
    @FXML
    void msg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("select_type_for_staff.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();

    }
    @FXML
    void pressed_sand_plus(ActionEvent event) {
        if (sand_lb.getText().equals("")) {
            String[] a = available_sand.getText().split(":");
            int count = Integer.parseInt(a[1]);
            count++;
            sand_lb.setText(Integer.toString(count));
        } else {
            int count = Integer.parseInt(sand_lb.getText());
            count++;
            sand_lb.setText(Integer.toString(count));
        }
    }

    @FXML
    void pressed_setting(ActionEvent event) {

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

         dash.setUnderline(true);

        Image image = new Image(img);
        ca.setFill(new ImagePattern(image));

        //try {
            //System.out.println(b);
//            InputStream in = b.getBinaryStream();
//            images = new Image(in);
//            profile_pic.setFill(new ImagePattern(images));
        //} catch (SQLException e) {
           //e.printStackTrace();
        //}


        String new_f = "", new_l = "";
        String first = f_name.substring(0, 1);
        String afterfirst = f_name.substring(1);
        new_f += first.toUpperCase() + afterfirst + " ";


        String last = l_name.substring(0, 1);
        String afterlast = l_name.substring(1);
        new_l += last.toUpperCase() + afterlast + " ";

        profile_lb.setText(id + " " + new_f + new_l);

        DBConnect db = new DBConnect();
        Connection con = db.connection();
        String query = "SELECT * FROM food_history";
        try {
            InputStream in = b.getBinaryStream();
//            Image image = new Image(in);


            //profile_pic.setFill(new ImagePattern(image));
            //ca.setFill(new ImagePattern(image));


            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            while (res.next()) {
                int id = res.getInt(1);
                int quan = res.getInt(3);

                if (id == 1) {
                    avaible_burg.setText("Available :" + quan);
                } else if (id == 2) {
                    available_sand.setText("Available :" + quan);
                } else if (id == 3) {
                    chips_tf.setText("Available :" + quan);
                } else if (id == 4) {
                    choc_tf.setText("Available :" + quan);
                } else if (id == 5) {
                    drinks_tf.setText("Available :" + quan);
                } else if (id == 6) {
                    fry_tf.setText("Available :" + quan);
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
