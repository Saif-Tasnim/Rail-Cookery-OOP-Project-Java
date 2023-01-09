package com.example.rail_cookery;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
import static com.example.rail_cookery.StaffDashboard.img;

public class GetOrder implements Initializable {

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
    private TableView<TableUserCart> table;

    @FXML
    private TableColumn<TableUserCart, String> cmp_col;

    @FXML
    private TableColumn<TableUserCart, String> seat_col;

    @FXML
    private TableColumn<TableUserCart, String> phn_col;

    @FXML
    private TableColumn<TableUserCart,Integer> item_col;

    @FXML
    private TableColumn<TableUserCart,String> pay_col;

    // static Variable

    public static String table_phn;
    public static String table_cmp;
    public static String table_seat;

    String query = "Select * From place_order Where status = 'Pending'";
    DBConnect db = new DBConnect();
    Connection con = db.connection();
    Statement statement;
    ResultSet resultSet;
    ObservableList<TableUserCart> list = FXCollections.observableArrayList();

    {
        try {
            statement = con.createStatement();
            resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                String phn = resultSet.getString(1);
                String cmp = resultSet.getString(2);
                String seat = resultSet.getString(3);
                int item = resultSet.getInt(4);
                String paid = resultSet.getString(7);

                TableUserCart t = new TableUserCart();
                t.setCmp_col(cmp);
                t.setSeat_col(seat);
                t.setPhn_col(phn);
                t.setItem_col(item);
                if(paid.equals("COD"))
                   t.setPay_col("Unpaid");
                else
                    t.setPay_col("Paid");

                list.add(t);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void getData(MouseEvent event) {
      Integer index = table.getSelectionModel().getSelectedIndex();
      if(index<=-1){
          return;
      }

      table_cmp = cmp_col.getCellData(index);
      table_seat = seat_col.getCellData(index);
      table_phn = phn_col.getCellData(index);

//        System.out.println(table_cmp);
//        System.out.println(table_seat);
//        System.out.println(table_phn);
    }




    @FXML
    void cart_list(ActionEvent event) throws IOException,SQLException {
//        boolean f = false;
//        DBConnect db = new DBConnect();
//        Connection con = db.connection();
//        String q = "Select * From place_order Where phone_no = '"+phone+"' And status = 'Pending'";
//        Statement statement = con.createStatement();
//        ResultSet res = statement.executeQuery(q);
//        if(res.next()){
//            f = true;
//            Alert alert = new Alert(Alert.AlertType.WARNING);
//            alert.setContentText("You have already a pending order.");
//            alert.show();
//        }
//        if(f == false){
//            Parent cart_p = FXMLLoader.load(getClass().getResource("user_cart_list.fxml"));
//            Scene cart_s = new Scene(cart_p);
//
//            Stage cart_st = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            cart_st.setScene(cart_s);
//            cart_st.show();
//        }
    }

    @FXML
    void pressed_show(ActionEvent event) throws IOException, SQLException {
        DBConnect db = new DBConnect();
        Connection con = db.connection();
        String q = "UPDATE place_order SET status = 'Preparing' WHERE phone_no = '"+table_phn+"' AND status = 'Pending'" ;
        Statement statement = con.createStatement();
        statement.executeUpdate(q);
    Parent root = FXMLLoader.load(getClass().getResource("show_items.fxml"));
    Scene sc = new Scene(root);
    Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
    st.setScene(sc);
    st.show();

    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("staff_dashboard.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }

    @FXML
    void pressed_setting(ActionEvent event) {

    }
//    private String cmp_col;
//    private String seat_col;
//    private String phn_col;
//    private int item_col;
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

        cmp_col.setCellValueFactory(new PropertyValueFactory<TableUserCart,String>("cmp_col"));
        seat_col.setCellValueFactory(new PropertyValueFactory<TableUserCart,String>("seat_col"));
        phn_col.setCellValueFactory(new PropertyValueFactory<TableUserCart,String>("phn_col"));
        item_col.setCellValueFactory(new PropertyValueFactory<TableUserCart,Integer>("item_col"));
        pay_col.setCellValueFactory(new PropertyValueFactory<TableUserCart,String>("pay_col"));
        table.setItems(list);



    }
}
