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
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
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
import static com.example.rail_cookery.UserDashboard.imagepath;

public class Track implements Initializable {

    @FXML
    private Label profile_lb;
    @FXML
    private Circle circle;

    @FXML
    private ImageView arrow;

    @FXML
    private Button dash;

    @FXML
    private Button cart;

    @FXML
    private Button setting;

    @FXML
    private Button track;

    @FXML
    private Button log_out;

    @FXML
    private ImageView pend_img;

    @FXML
    private ImageView prepare_img;

    @FXML
    private ImageView ship_img;

    @FXML
    private Pane pane1;

    @FXML
    private Pane pane2;

    @FXML
    void cart_list(ActionEvent event) throws IOException{
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
        Parent root = FXMLLoader.load(getClass().getResource("user_dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }


    @FXML
    void pressed_track(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("track.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void update_profile_pic(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { String new_f = "", new_l = "";
        String first = f_name.substring(0, 1);
        String afterfirst = f_name.substring(1);
        new_f += first.toUpperCase() + afterfirst + " ";


        String last = l_name.substring(0, 1);
        String afterlast = l_name.substring(1);
        new_l += last.toUpperCase() + afterlast + " ";

        profile_lb.setText(new_f + new_l);

        arrow.setVisible(false);

        Image image = new Image(imagepath);
        circle.setFill(new ImagePattern(image));

        DBConnect db = new DBConnect();
        Connection con = db.connection();
        String query = "SELECT * FROM place_order WHERE phone_no = '"+phone+"'";
        try {
            Statement statement = con.createStatement();
            ResultSet res = statement.executeQuery(query);
            if(res.next()){
                String st = res.getString(6);
                if(st.equals("Pending")){
//                    prepare_img.setVisible(false);
//                    ship_img.setVisible(false);
                    pane1.setVisible(false);
                    pane2.setVisible(false);
                    arrow.setVisible(true);
                }
                else if(st.equals("Preparing")){
//                    prepare_img.setVisible(true);
//                    ship_img.setVisible(false);
                    pane1.setVisible(true);
                    pane2.setVisible(false);
                    arrow.setVisible(false);
                }
                else{
//                    prepare_img.setVisible(true);
//                    ship_img.setVisible(true);
                    pane1.setVisible(true);
                    pane2.setVisible(true);
                    arrow.setVisible(false);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
