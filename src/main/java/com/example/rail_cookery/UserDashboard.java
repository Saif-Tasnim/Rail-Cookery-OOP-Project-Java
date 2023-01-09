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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.rail_cookery.HelloController.*;

public class UserDashboard implements Initializable {

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
    private Label greet_lb1;

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
    private Button track;

    @FXML
    private Circle circle;

     public static String imagepath = "file:///C:/Users/ABCD/Downloads/hacker.png";


    InputStream input;


    // all static variable

    // ----- Burger ----- //
    public static int count_b;
    public static String burger_name;
    public static float burger_price = 90.00f;

    // ----- Sand ----- //
    public static int count_s;
    public static String sand_name;
    public static float sand_price = 90.00f;

    // ----- Chips ----- //
    public static int count_c;
    public static String chips_name;
    public static float chips_price = 20.00f;

    // ----- Chocolates ----- //
    public static int count_choc;
    public static String choc_name;
    public static float choc_price = 12.00f;

    // ----- Drinks ----- //
    public static int count_d;
    public static String drinks_name;
    public static float drinks_price = 25.00f;

    // ----- Fry ----- //
    public static int count_f;
    public static String fry_name;
    public static float fry_price = 65.00f;



    @FXML
    void cart_list(ActionEvent event) throws IOException, SQLException {
        boolean f = false;
         DBConnect db = new DBConnect();
         Connection con = db.connection();
         String q = "Select * From place_order Where phone_no = '"+phone+"' And status = 'Pending'";
         Statement statement = con.createStatement();
         ResultSet res = statement.executeQuery(q);
         if(res.next()){
             f = true;
             Alert alert = new Alert(Alert.AlertType.WARNING);
             alert.setContentText("You have already a pending order. Go To Track Order");
             alert.show();
         }
         if(f == false){
             Parent cart_p = FXMLLoader.load(getClass().getResource("user_cart_list.fxml"));
             Scene cart_s = new Scene(cart_p);

             Stage cart_st = (Stage) ((Node) event.getSource()).getScene().getWindow();
             cart_st.setScene(cart_s);
             cart_st.show();
         }
    }

    @FXML
    void pressed_burger_cart(ActionEvent event) {

       burger_name = "Burger";
       burger_price = 90.00F;
       if (burger_lb.getText().equals("") || burger_lb.getText().equals("0")){
           Alert alert = new Alert(Alert.AlertType.WARNING);
           alert.setContentText("You have not set any quantity of burger . ");
           alert.show();
       }
       else{

           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setContentText(count_b+ " has added in cart list");
           alert.show();
       }
    }

    @FXML
    void pressed_burger_minus(ActionEvent event) throws SQLException {
     if(burger_lb.getText().equals("")||burger_lb.getText().equals("0")){
         burger_lb.setText("0");
         burger_minus.setDisable(true);
         burger_plus.setDisable(false);
     }

     else
     {
         count_b--;
         burger_lb.setText(Integer.toString(count_b));
         burger_minus.setDisable(false);
         String[] ami = avaible_burg.getText().split(":");
         // System.out.println(ami[1]);
         int i = Integer.parseInt(ami[1]);
         System.out.println(i);
         String s = burger_lb.getText();
         int so = Integer.parseInt(s);
         if(so >= i){
             burger_plus.setDisable(true);

         }
         else{
             burger_plus.setDisable(false);

         }

     }
    }

    @FXML
    void pressed_burger_plus(ActionEvent event) {
     if(burger_lb.getText().equals("") || burger_lb.getText().equals("0")){
         count_b = 0;
         count_b++;
         burger_lb.setText(Integer.toString(count_b));
         burger_minus.setDisable(false);

     }
     else{
         count_b++;
         burger_lb.setText(Integer.toString(count_b));
     }

         String[] ami = avaible_burg.getText().split(":");
        // System.out.println(ami[1]);
        int i = Integer.parseInt(ami[1]);
        // System.out.println(i);
        String s = burger_lb.getText();
        int so = Integer.parseInt(s);
        if(so >= i){
            burger_plus.setDisable(true);
        }
        else{
            burger_plus.setDisable(false);
        }
    }

    @FXML
    void pressed_chips_cart(ActionEvent event) {
        chips_price = 20.00f;
        chips_name = "Chips";
        if (chips_lb.getText().equals("") || chips_lb.getText().equals("0")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not set any quantity of chips . ");
            alert.show();
        }
        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(count_c+ " has added in cart list");
            alert.show();
        }
    }

    @FXML
    void pressed_chips_minus(ActionEvent event) {
        if(chips_lb.getText().equals("")||chips_lb.getText().equals("0")){
            chips_lb.setText("0");
            chips_minus.setDisable(true);
            chips_plus.setDisable(false);
        }
        else{
            count_c--;
            chips_lb.setText(Integer.toString(count_c));
            chips_minus.setDisable(false);
            String[] ami = chips_tf.getText().split(":");
            // System.out.println(ami[1]);
            int i = Integer.parseInt(ami[1]);
            // System.out.println(i);
            String s = chips_lb.getText();
            int so = Integer.parseInt(s);
            if(so >= i){
                chips_plus.setDisable(true);

            }
            else{
                chips_plus.setDisable(false);

            }

        }
    }

    @FXML
    void pressed_chips_plus(ActionEvent event) {
        if(chips_lb.getText().equals("") || chips_lb.getText().equals("0")){
            count_c = 0;
            count_c++;
            chips_lb.setText(Integer.toString(count_c));
            chips_minus.setDisable(false);

        }
        else{
            count_c++;
            chips_lb.setText(Integer.toString(count_c));
        }

        String[] ami = chips_tf.getText().split(":");
        // System.out.println(ami[1]);
        int i = Integer.parseInt(ami[1]);
       // System.out.println(i);
        String s = chips_lb.getText();
        int so = Integer.parseInt(s);
        if(so >= i){
            chips_plus.setDisable(true);
        }
        else{
            chips_plus.setDisable(false);
        }
    }

    @FXML
    void pressed_choc_cart(ActionEvent event) {
        choc_price = 12.00f;
        choc_name = "Chocolates";
        if (choc_lb.getText().equals("") || choc_lb.getText().equals("0")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not set any quantity of chocolates . ");
            alert.show();
        }
        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(count_choc+ " has added in cart list");
            alert.show();
        }
    }

    @FXML
    void pressed_choc_minus(ActionEvent event) {
        if(choc_lb.getText().equals("")||choc_lb.getText().equals("0")){
            choc_lb.setText("0");
            choc_minus.setDisable(true);
            choc_plus.setDisable(false);
        }
        else{
            count_choc--;
            choc_lb.setText(Integer.toString(count_choc));
            choc_minus.setDisable(false);
            String[] ami = avaible_burg.getText().split(":");
            // System.out.println(ami[1]);
            int i = Integer.parseInt(ami[1]);
           // System.out.println(i);
            String s = choc_lb.getText();
            int so = Integer.parseInt(s);
            if(so >= i){
                choc_plus.setDisable(true);

            }
            else{
                choc_plus.setDisable(false);

            }

        }
    }

    @FXML
    void pressed_choc_plus(ActionEvent event) {
        if(choc_lb.getText().equals("") || choc_lb.getText().equals("0")){
            count_choc = 0;
            count_choc++;
            choc_lb.setText(Integer.toString(count_choc));
            choc_minus.setDisable(false);

        }
        else{
            count_choc++;
            choc_lb.setText(Integer.toString(count_choc));
        }

        String[] ami = choc_tf.getText().split(":");
        // System.out.println(ami[1]);
        int i = Integer.parseInt(ami[1]);
       // System.out.println(i);
        String s = choc_lb.getText();
        int so = Integer.parseInt(s);
        if(so >= i){
            choc_plus.setDisable(true);
        }
        else{
            choc_plus.setDisable(false);
        }
    }

    @FXML
    void pressed_dashboard(ActionEvent event) {

    }

    @FXML
    void pressed_drinks_cart(ActionEvent event) {
        drinks_price = 25.00f;
        drinks_name = "Cold Drinks";
        if (drinks_lb.getText().equals("") || drinks_lb.getText().equals("0")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not set any quantity of drinks . ");
            alert.show();
        }

        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(count_d+ " has added in cart list");
            alert.show();
        }
    }

    @FXML
    void pressed_drinks_minus(ActionEvent event) {
        if(drinks_lb.getText().equals("")||drinks_lb.getText().equals("0")){
            drinks_lb.setText("0");
            drinks_minus.setDisable(true);
            drinks_plus.setDisable(false);
        }
        else{
            count_d--;
            drinks_lb.setText(Integer.toString(count_d));
            drinks_minus.setDisable(false);
            String[] ami = drinks_tf.getText().split(":");
            // System.out.println(ami[1]);
            int i = Integer.parseInt(ami[1]);
         //   System.out.println(i);
            String s = drinks_lb.getText();
            int so = Integer.parseInt(s);
            if(so >= i){
                drinks_plus.setDisable(true);

            }
            else{
                drinks_plus.setDisable(false);

            }

        }
    }

    @FXML
    void pressed_drinks_plus(ActionEvent event) {
        if(drinks_lb.getText().equals("") || drinks_lb.getText().equals("0")){
            count_d = 0;
            count_d++;
            drinks_lb.setText(Integer.toString(count_d));
            drinks_minus.setDisable(false);

        }
        else{
            count_d++;
            drinks_lb.setText(Integer.toString(count_d));
        }

        String[] ami = drinks_tf.getText().split(":");
        // System.out.println(ami[1]);
        int i = Integer.parseInt(ami[1]);
      //  System.out.println(i);
        String s = drinks_lb.getText();
        int so = Integer.parseInt(s);
        if(so >= i){
            drinks_plus.setDisable(true);
        }
        else{
            drinks_plus.setDisable(false);
        }
    }

    @FXML
    void pressed_fry_cart(ActionEvent event) {
        fry_price = 65.00f;
        fry_name = "Fries";
        if (fry_lb.getText().equals("") || fry_lb.getText().equals("0")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not set any quantity of fries . ");
            alert.show();
        }
        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(count_f+ " has added in cart list");
            alert.show();
        }
    }

    @FXML
    void pressed_fry_minus(ActionEvent event) {
        if(fry_lb.getText().equals("")||fry_lb.getText().equals("0")){
            fry_lb.setText("0");
            fry_minus.setDisable(true);
            fry_plus.setDisable(false);
        }
        else{
            count_f--;
            fry_lb.setText(Integer.toString(count_f));
            fry_minus.setDisable(false);
            String[] ami = fry_tf.getText().split(":");
            // System.out.println(ami[1]);
            int i = Integer.parseInt(ami[1]);
         //   System.out.println(i);
            String s = fry_lb.getText();
            int so = Integer.parseInt(s);
            if(so >= i){
                fry_plus.setDisable(true);

            }
            else{
                fry_plus.setDisable(false);

            }

        }
    }

    @FXML
    void pressed_fry_plus(ActionEvent event) {
        if(fry_lb.getText().equals("") || fry_lb.getText().equals("0")){
            count_f = 0;
            count_f++;
            fry_lb.setText(Integer.toString(count_f));
            fry_minus.setDisable(false);

        }
        else{
            count_f++;
            fry_lb.setText(Integer.toString(count_f));
        }

        String[] ami = fry_tf.getText().split(":");
        // System.out.println(ami[1]);
        int i = Integer.parseInt(ami[1]);
      //  System.out.println(i);
        String s = fry_lb.getText();
        int so = Integer.parseInt(s);
        if(so >= i){
            fry_plus.setDisable(true);
        }
        else{
            fry_plus.setDisable(false);
        }
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException {
        count_b = 0;
        count_c = 0;
        count_choc = 0;
        count_d = 0;
        count_f = 0;
        count_s = 0;

        Parent log = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
    Scene log_s = new Scene(log);
    Stage log_st = (Stage) ((Node)event.getSource()).getScene().getWindow();
    log_st.setScene(log_s);
    log_st.show();


    }

    @FXML
    void pressed_sand_cart(ActionEvent event) {
        sand_price = 90.00f;
        sand_name = "Sandwich";
        if (sand_lb.getText().equals("") || sand_lb.getText().equals("0")){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You have not set any quantity of sandwich . ");
            alert.show();
        }
        else{

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(count_s+ " has added in cart list");
            alert.show();
        }
    }

    @FXML
    void pressed_sand_minus(ActionEvent event) {
        if(sand_lb.getText().equals("")||sand_lb.getText().equals("0")){
            sand_lb.setText("0");
            sand_minus.setDisable(true);
            sand_plus.setDisable(false);
        }
        else{
            count_s--;
            sand_lb.setText(Integer.toString(count_s));
            sand_minus.setDisable(false);
            String[] ami = available_sand.getText().split(":");
            // System.out.println(ami[1]);
            int i = Integer.parseInt(ami[1]);
         //   System.out.println(i);
            String s = sand_lb.getText();
            int so = Integer.parseInt(s);
            if(so >= i){
                sand_plus.setDisable(true);

            }
            else{
                sand_plus.setDisable(false);

            }

        }
    }

    @FXML
    void pressed_sand_plus(ActionEvent event) {
        if(sand_lb.getText().equals("") || sand_lb.getText().equals("0")){
            count_s = 0;
            count_s++;
            sand_lb.setText(Integer.toString(count_s));
            sand_minus.setDisable(false);

        }
        else{
            count_s++;
            sand_lb.setText(Integer.toString(count_s));
        }

        String[] ami = available_sand.getText().split(":");
        // System.out.println(ami[1]);
        int i = Integer.parseInt(ami[1]);
        // System.out.println(i);
        String s = sand_lb.getText();
        int so = Integer.parseInt(s);
        if(so >= i){
            sand_plus.setDisable(true);
        }
        else{
            sand_plus.setDisable(false);
        }
    }



    @FXML
    void pressed_setting(ActionEvent event)  throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("setting.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_msg(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("select_type_for_pas.fxml"));
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
        greet_lb1.setText("Hello " + new_f);
        greet_lb2.setText("Hope you have a great journey with us....");
        dash.setUnderline(true);

        Image image = new Image(imagepath);
        circle.setFill(new ImagePattern(image));

        DBConnect db = new DBConnect();
        Connection con = db.connection();
        try {
            Statement statement = con.createStatement();
            String query = "SELECT * FROM food_history";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                int id = res.getInt(1);
                int quan = res.getInt(3);

                if (id == 1) {
                    avaible_burg.setText("Available :" + quan);
                    System.out.println(quan);
                } else if (id == 2) {
                    available_sand.setText("Available :" + quan);

                } else if (id == 3) {
                    chips_tf.setText("Available :" + quan);
                } else if (id == 4) {
                    choc_tf.setText("Available :" + quan);
                    System.out.println(quan);
                } else if (id == 5) {
                    drinks_tf.setText("Available :" + quan);
                    System.out.println(quan);
                } else if (id == 6) {
                    fry_tf.setText("Available :" + quan);
                }



            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean f = false ;
//        DBConnect db = new DBConnect();
//        Connection con = db.connection();
        String q = "Select * From place_order Where phone_no = '"+phone+"' And (status = 'Pending' OR status = 'Preparing')";
        Statement statement = null;
        try {
            statement = con.createStatement();
            ResultSet res = statement.executeQuery(q);
            if(res.next()) {
                f = true;
                Alert alert = new Alert(Alert.AlertType.WARNING);
                burger_plus.setDisable(true);
                burger_minus.setDisable(true);
                sand_minus.setDisable(true);
                sand_plus.setDisable(true);
                chips_plus.setDisable(true);
                chips_minus.setDisable(true);
                choc_plus.setDisable(true);
                choc_minus.setDisable(true);
                drinks_plus.setDisable(true);
                drinks_minus.setDisable(true);
                fry_minus.setDisable(true);
                fry_plus.setDisable(true);
                cart.setDisable(true);
            }
            else{
                burger_plus.setDisable(false);
                burger_minus.setDisable(false);
                sand_minus.setDisable(false);
                sand_plus.setDisable(false);
                chips_plus.setDisable(false);
                chips_minus.setDisable(false);
                choc_plus.setDisable(false);
                choc_minus.setDisable(false);
                drinks_plus.setDisable(false);
                drinks_minus.setDisable(false);
                fry_minus.setDisable(false);
                fry_plus.setDisable(false);
                cart.setDisable(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    }

