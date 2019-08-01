package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    @FXML
    private TextArea text;
    @FXML
    private TextArea messege;
    @FXML
    private TableColumn<FDetails,String> tname;
    @FXML
    private TableColumn<FDetails,String> tid;
    @FXML
    private TableView<FDetails> table;
    Alert alt2 = new Alert(Alert.AlertType.ERROR);
    public ObservableList<FDetails> list = FXCollections.observableArrayList();

    String msg;
    String name ;
    ResultSet rs,rr;
    DataBase db = new DataBase();
    public void chat(MouseEvent ev) throws SQLException {
        text.setText("");
        rs = db.chatMsg();
        msg = "";
        name = table.getSelectionModel().getSelectedItem().getUname();
        while(rs.next()) {
            if((rs.getString("sender").equals(name) && rs.getString("reciever").equals(Details.uName))==true || (rs.getString("sender").equals(Details.uName) && rs.getString("reciever").equals(name))==true){
                msg = msg + rs.getString("sender")+"\n-----------------------------------\n"+rs.getString("msg")+"\n\n";
                text.setText(msg);
            }
        }
}



    public void flist(){
        try {
            rr = db.frndlst();
            while(rr.next()){
                list.add(new FDetails(rr.getString("Name"),rr.getString("Username"),rr.getString("ID"),
                        rr.getString("Password"),rr.getString("Phone"),rr.getString("Email"),
                        rr.getString("BG"),rr.getString("Address"),rr.getString("Dob")));
            }

        } catch (SQLException e) {
            alt2.setTitle("Error");
            alt2.setHeaderText("Something Error.");
            alt2.setContentText("Unable to fetch Friend List.");
            alt2.show();
        }
    }
    public void settable(){
        tname.setCellValueFactory(new PropertyValueFactory<>("name"));
        tid.setCellValueFactory(new PropertyValueFactory<>("id"));
        table.setItems(list);
    }

    public void initialize(URL location, ResourceBundle resources){
        flist();
        settable();
    }

    public void sendmsg() throws SQLException {
        db.chatsend(Details.uName,name , messege.getText());
        messege.setText("");
        MouseEvent eve = null;
        chat(eve);
    }

}
