package com.example.rail_cookery;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static com.example.rail_cookery.HelloController.f_name;
import static com.example.rail_cookery.HelloController.users;

public class ClientAdminChatController extends Thread implements Initializable {
    @FXML
    private ToggleGroup radio;

    @FXML
    private TextArea txt_msg;

    @FXML
    private TextField tf;

    BufferedReader reader;
    PrintWriter writer = null;
    Socket socket;
    String userName;
    public void takename()
    {

        for (User user : users) {
            userName = user.name;
        }

    }







    @FXML
    private void handleSendEvent(MouseEvent event) throws SQLException, ClassNotFoundException {
        send();

//        for (User user : users) {
//            System.out.println(user.name);
//        }
    }



//
//    int serial;
//    String Name1,Password1,email1,phone1,gender1 = "";










    public void send() throws SQLException, ClassNotFoundException {
        takename();
        String msg = tf.getText();

        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/train_food_aoop_project";
        String username = "root";
        String passwords = "";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
        String sql="INSERT INTO user_admin(`name`,massage) VALUES ( ?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,f_name);
        ps.setString(2,msg);


        ps.executeUpdate();

        conn.close();

        writer.println(f_name  +" : " + msg);
        //writer.println(" ");
        txt_msg.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        //msgRoom.appendText("Me: " + msg + "\n");

        txt_msg.setText("");
        if (msg.equalsIgnoreCase("BYE") || (msg.equalsIgnoreCase("logout"))) {
            System.exit(0);
        }
    }
    @FXML
    void ok(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        send();
        Parent root = FXMLLoader.load(getClass().getResource("client_admin_chat_controller.fxml"));
        Scene sc = new Scene(root);
        Stage st = (Stage) ((Node)event.getSource()).getScene().getWindow();
        st.setScene(sc);
        st.show();
    }
    @FXML
    private void sendMessageByKey(KeyEvent event) throws SQLException, ClassNotFoundException {
        if (event.getCode().toString().equals("ENTER")) {
            send();
        }
    }

    @FXML
//    private void handleProfileBtn(ActionEvent event) {
//
//        if (event.getSource().equals(profileBtn) && !toggleProfile) {
//            new FadeTransition().play();
//            profile.toFront();
//            chat.toBack();
//            toggleProfile = true;
//            toggleChat = false;
//            profileBtn.setText("Back");
//            setProfile();
//        } else if (event.getSource().equals(profileBtn) && toggleProfile) {
//            new FadeTransition().play();
//            chat.toFront();
//            toggleProfile = false;
//            toggleChat = false;
//            profileBtn.setText("Profile");
//        }
//
//    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




        String driver = "com.mysql.cj.jdbc.Driver";
        String databaseurl = "jdbc:mysql://localhost:3306/train_food_aoop_project";
        String username = "root";
        String passwords = "";







//        //info();
        takename();
//        System.out.println("Chat Room Controller user name : "+userName);
//        // gender1 = "Male";
//        showProPic.setStroke(Color.valueOf("#90a4ae"));
//        Image image;
//        if (gender1.equalsIgnoreCase("Male")) {
//            image = new Image(new File("src/main/resources/com/example/icons/user.png").toURI().toString(), false);//false chilos
//        } else {
//            image = new Image(new File("src/main/resources/com/example/icons/female.png").toURI().toString(), false);
//            proImage.setImage(image);
//        }
//        showProPic.setFill(new ImagePattern(image));
//        clientName.setText(String.valueOf(userName));
        connectSocket();

//        msgRoom.setText("bob\n");



        try {
            Class.forName(driver);
            Connection conn = DriverManager.getConnection(databaseurl, username, passwords);
            String sql = "SELECT * FROM user_admin ";
            PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, sid1);

            ResultSet rsz = pst.executeQuery();


            while (rsz.next()) {
                //        list2.add(new studenthomeshow(rsz.getString("tname"), rsz.getString("stime"),rsz.getString("endtime"), rsz.getString("status"), rsz.getString("date")));
                txt_msg.appendText(rsz.getString(1)+" :- "+rsz.getString(2)+"\n");
            }

            conn.close();
            rsz.close();
        } catch (Exception e) {
        }
    }
    @FXML
    void back(ActionEvent event) throws IOException {
   Parent root = FXMLLoader.load(getClass().getResource("select_type_for_pas.fxml"));
   Scene sc = new Scene(root);
   Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
   st.setScene((sc));
   st.show();
    }

    private void connectSocket() {
        try {
            socket = new Socket("127.0.0.1", 5556);
            System.out.println("Socket is connected with server!");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean saveControl = false;





    @Override
    public void run() {
        try {
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                System.out.println(cmd);
                StringBuilder fulmsg = new StringBuilder();
                for(int i = 1; i < tokens.length; i++) {
                    fulmsg.append(tokens[i]);
                }
                System.out.println(fulmsg);
                if (cmd.equalsIgnoreCase(userName + ":")) {
                    continue;
                } else if(fulmsg.toString().equalsIgnoreCase("bye")) {
                    break;
                }
                txt_msg.appendText(msg + "\n");
            }
            reader.close();
            writer.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
