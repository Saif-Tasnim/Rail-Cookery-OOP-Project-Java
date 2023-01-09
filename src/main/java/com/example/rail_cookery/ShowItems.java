package com.example.rail_cookery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import static com.example.rail_cookery.GetOrder.*;
import  static  com.example.rail_cookery.HelloController.id;
import  static  com.example.rail_cookery.HelloController.f_name;
import  static  com.example.rail_cookery.HelloController.l_name;
import static com.example.rail_cookery.StaffDashboard.img;

public class ShowItems implements Initializable {

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
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label l3;

    @FXML
    private TableView<StaffFoodTable> table;

    @FXML
    private TableColumn<StaffFoodTable, String> item_col;

    @FXML
    private TableColumn<StaffFoodTable, Integer> quantity_col;

    ObservableList<StaffFoodTable>list = FXCollections.observableArrayList();
    DBConnect db = new DBConnect();
    Connection con = db.connection();
    String q = "SELECT * FROM food_list WHERE status = 'Pending' AND phone_no = '"+table_phn+"'";
    Statement statement;

    {
        try {
            statement = con.createStatement();
            ResultSet res = statement.executeQuery(q);
            while(res.next()){
                String i = res.getString(4);
                int quan = res.getInt(5);

                StaffFoodTable s = new StaffFoodTable();
                s.setItem_col(i);
                s.setQuantity_col(quan);
                list.add(s);
        }
    } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @FXML
    void cart_list(ActionEvent event) {

    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException {
     Parent root = FXMLLoader.load(getClass().getResource("staff_dashboard.fxml"));
     Scene sc = new Scene(root);
     Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
     st.setScene(sc);
     st.show();
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
    void pressed_setting(ActionEvent event) {

    }


    @FXML
    void pressed_get_order(ActionEvent event) throws SQLException, IOException {
    String q1 = "UPDATE place_order SET status = 'Done' WHERE phone_no = '"+table_phn+"'";
    DBConnect db = new DBConnect();
    Connection con = db.connection();
    Statement statement = con.createStatement();
    statement.executeUpdate(q1);

    String q2 = "UPDATE food_list SET status = 'Done' WHERE phone_no = '"+table_phn+"'";
    statement.executeUpdate(q2);

    String a1 = "Select * From place_order Where phone_no = '"+table_phn+"'";
    ResultSet re = statement.executeQuery(a1);
    int qu=0;
    float sum=0;
    if(re.next()){
         qu = re.getInt(4);
         sum = re.getFloat(5);

    }
    if(qu!=0 || sum!=0){
        String s = f_name+" "+l_name;
        String b1 = "INSERT INTO list(phone_no , quantity ,price ,s_id ,s_name)VALUES('"+table_phn+"','"+qu+"','"+sum+"','"+id+"','"+s+"')";
        String b2 = "DELETE FROM place_order WHERE phone_no = '"+table_phn+"'";
        statement.executeUpdate(b1);
        Alert aler = new Alert(Alert.AlertType.CONFIRMATION);
        aler.setContentText("This Item Has Successfully Shipped");
        aler.show();
        Parent root = FXMLLoader.load(getClass().getResource("get_order.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
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

        profile_lb.setText(Integer.toString(id)+" "+new_f + " " + new_l);

        Image image = new Image(img);
        ca.setFill(new ImagePattern(image));


        l1.setText("Phone : "+table_phn);
        l2.setText("Comp : "+table_cmp);
        l3.setText("Seat : "+table_seat);



//        DBConnect db = new DBConnect();
//        Connection con = db.connection();
//        String q = "SELECT * FROM food_list WHERE status = 'Pending' AND phone_no = '"+table_phn+"'";
//        try {
//            Statement statement = con.createStatement();
//            ResultSet res = statement.executeQuery(q);
//            while(res.next()){
//             String i = res.getString(3);
//             int quan = res.getInt(4);
//
//             StaffFoodTable s = new StaffFoodTable();
//             s.setItem_col(i);
//             s.setQuantity_col(quan);
//             list.add(s);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        item_col.setCellValueFactory(new PropertyValueFactory<StaffFoodTable,String>("item_col"));
        quantity_col.setCellValueFactory(new PropertyValueFactory<StaffFoodTable,Integer>("quantity_col"));
        table.setItems(list);
    }
}
