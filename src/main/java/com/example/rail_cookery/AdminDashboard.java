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
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminDashboard implements Initializable {
    @FXML
    private Button dash;

    @FXML
    private Button add_staff;

    @FXML
    private Button order_h;

    @FXML
    private Label user_tf;

    @FXML
    private Label staff_tf;

    @FXML
    private LineChart<?, ?> lineChart;

    @FXML
    private PieChart pieChart;

    @FXML
    void pressed_add_staff(ActionEvent event) throws IOException {
        Parent staff_p = FXMLLoader.load(getClass().getResource("staff_register.fxml"));
        Scene staff_s = new Scene(staff_p);
        Stage staff_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        staff_st.setScene(staff_s);
        staff_st.show();
//        Stage userStage = (Stage)((Node)event.getSource()).getScene().getWindow();
//        userStage.setScene(adminViewScene);
//        userStage.show();

    }

    @FXML
    void pressed_dashboard(ActionEvent event) throws IOException{
        Parent order = FXMLLoader.load(getClass().getResource("admin_dashboard.fxml"));
        Scene  order_s = new Scene(order);
        Stage order_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        order_st.setScene(order_s);
        order_st.show();
    }

    @FXML
    void pressed_msg(ActionEvent event) throws IOException {
        Parent order = FXMLLoader.load(getClass().getResource("select_type_for_admin.fxml"));
        Scene  order_s = new Scene(order);
        Stage order_st = (Stage)((Node)event.getSource()).getScene().getWindow();
        order_st.setScene(order_s);
        order_st.show();
    }

    @FXML
    void pressed_history(ActionEvent event) throws IOException {
     Parent order = FXMLLoader.load(getClass().getResource("order_history.fxml"));
     Scene  order_s = new Scene(order);
     Stage order_st = (Stage)((Node)event.getSource()).getScene().getWindow();
     order_st.setScene(order_s);
     order_st.show();
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
        dash.setUnderline(true);
        XYChart.Series series = new XYChart.Series();
        series.getData().add(new XYChart.Data("Burger",30));
        series.getData().add(new XYChart.Data("Sandwich",23));
        series.getData().add(new XYChart.Data("Chips",45));
        series.getData().add(new XYChart.Data("Chocolates",21));
        series.getData().add(new XYChart.Data("Drinks",36));
        series.getData().add(new XYChart.Data("Fries",19));

        lineChart.getData().addAll(series);
        lineChart.lookup(".chart-plot-background").setStyle("-fx-background-color: transparent;");

        ObservableList<PieChart.Data> pie = FXCollections.observableArrayList(
                new PieChart.Data("COD",55),
                new PieChart.Data("Bkash",30),
                new PieChart.Data("Nagad",15)
        );
      pieChart.setData(pie);
    }
}
