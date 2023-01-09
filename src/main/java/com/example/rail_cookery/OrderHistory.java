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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class OrderHistory implements Initializable {

    @FXML
    private Button dash;

    @FXML
    private Button add;

    @FXML
    private Button history;

    @FXML
    private TableView<AdminTable> table;

    @FXML
    private TableColumn<AdminTable, String> phn_col;

    @FXML
    private TableColumn<AdminTable, Integer> quan_col;

    @FXML
    private TableColumn<AdminTable, Float> price_col;

    @FXML
    private TableColumn<AdminTable, Integer> id_col;

    @FXML
    private TableColumn<AdminTable, String> staff_col;

    ObservableList<AdminTable>list = FXCollections.observableArrayList();

    String q = "SELECT * FROM list ";
    DBConnect db = new DBConnect();
    Connection con = db.connection();
    Statement statement;

    {
        try {
            statement = con.createStatement();
            ResultSet res = statement.executeQuery(q);
            while(res.next()){
                AdminTable a = new AdminTable();
                a.setPhn_col(res.getString(2));
                a.setItem_col(res.getInt(3));
                a.setPrice_col(res.getFloat(4));
                a.setId_col(res.getInt(5));
                a.setStaff_col(res.getString(6));

                list.add(a);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void pressed_add_staff(ActionEvent event) throws IOException {
        Parent staff_p = FXMLLoader.load(getClass().getResource("staff_register.fxml"));
        Scene staff_s = new Scene(staff_p);
        Stage staff_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        staff_st.setScene(staff_s);
        staff_st.show();
    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException {
        Parent dash = FXMLLoader.load(getClass().getResource("admin_dashboard.fxml"));
        Scene dash_s = new Scene(dash);
        Stage dash_st = (Stage) ((Node)event.getSource()).getScene().getWindow();
        dash_st.setScene(dash_s);
        dash_st.show();
    }

    @FXML
    void pressed_history(ActionEvent event) throws IOException {
        Parent log = FXMLLoader.load(getClass().getResource("order_history.fxml"));
        Scene log_s = new Scene(log);
        Stage log_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        log_st.setScene(log_s);
        log_st.show();
    }

    @FXML
    void pressed_log_out(ActionEvent event) throws IOException {
        Parent log = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Scene log_s = new Scene(log);
        Stage log_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        log_st.setScene(log_s);
        log_st.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        history.setUnderline(true);
        phn_col.setCellValueFactory(new PropertyValueFactory<AdminTable,String>("phn_col"));
        quan_col.setCellValueFactory(new PropertyValueFactory<AdminTable,Integer>("item_col"));
        price_col.setCellValueFactory(new PropertyValueFactory<AdminTable,Float>("price_col"));
        id_col.setCellValueFactory(new PropertyValueFactory<AdminTable,Integer>("id_col"));
        staff_col.setCellValueFactory(new PropertyValueFactory<AdminTable,String>("staff_col"));

        table.setItems(list);

    }
}
