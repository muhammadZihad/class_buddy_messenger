package application;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyPro {
  @FXML
  JFXTextField name;
  @FXML
  JFXTextField uname;
  @FXML
  JFXTextField uid;
  @FXML
  JFXTextField email;
  @FXML
  JFXTextField phn;
  @FXML
  JFXTextField dob;
  @FXML
  JFXTextArea addr;
  @FXML
  JFXTextField bgrp;
  @FXML
  JFXPasswordField pass;
    @FXML
     ImageView image;

  DataBase db = new DataBase();
  Alert alt = new Alert(Alert.AlertType.ERROR);
  ResultSet rs;
  InputStream is;
  OutputStream os;
  Image img;
  public void initialize() throws SQLException, IOException {
      name.setText(Details.fName);
      uname.setText(Details.uName);
      uid.setText(Details.uId);
      email.setText(Details.email);
      phn.setText(Details.uPhn);
      dob.setText(Details.dob);
      addr.setText(Details.addr);
      bgrp.setText(Details.bg);
      pass.setText(Details.pass);
      rs = db.Searchme();
      while(rs.next()){
          is = rs.getBinaryStream("Photo");
          os = new FileOutputStream(new File("photo.jpg"));
          byte[] cnt =new byte[1024];int size = 0;
          while((size = is.read(cnt))!=-1) {
              os.write(cnt, 0, size);
          }
          img = new Image("file:photo.jpg",image.getFitHeight(),image.getFitWidth(),true,true);
          image.setImage(img);
      }
  }
//pass,phn,email,addr

    public void update(){
        try {
            db.updateprofile(uname.getText(),pass.getText(),email.getText(),addr.getText(),phn.getText(),uid.getText());
        } catch (SQLException e) {
            alt.setTitle("Error");
            alt.setHeaderText("Something went wrong !");
            alt.setContentText("Please check every changes that you made.");
            alt.show();
        }
    }

    public void close(ActionEvent event){
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
