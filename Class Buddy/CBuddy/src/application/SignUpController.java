package application;

import java.awt.image.BufferedImage;
import java.io.*;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class SignUpController {
	DataBase db=new DataBase();
	@FXML
	JFXTextField name;
	@FXML
	JFXTextField id;
	@FXML
	JFXTextField email;
	@FXML
	JFXTextField phone;
	@FXML
	JFXButton btn;
	@FXML
	JFXPasswordField pass;
	@FXML
	JFXPasswordField cpass;
	@FXML
	JFXDatePicker date;
	@FXML
	JFXTextField username;
	@FXML
	JFXTextField bg ;
	
	String nam;
	String iD;
	String	phn;
	String emaiL;
	String path;
	String password;
	String uname;
	String bgrp;
	int con_pass = 0;
	String dt;
	int chek = 0;
	InputStream is ;
	File file = null;


	public void getData() {
		nam=name.getText();;
		iD=id.getText();
		phn=phone.getText();
		emaiL=email.getText();
		dt=date.getEditor().getText();
		uname = username.getText();
		bgrp = bg.getText();
	}
	
	public void passmatch() {	// match the username and password from database
		
		password = pass.getText();
		String cpassword= cpass.getText();
		if(password.equals(cpassword)) {
			con_pass = 1;
		}
	}

	
	public void setata(ActionEvent event) throws FileNotFoundException {	// Sent new Account data to database
		getData();
		passmatch();
		if(con_pass==1)
		{
			if(file == null)
				db.setData(nam,uname, iD,password ,phn,dt, emaiL,bgrp);
			else{
				is = new FileInputStream(file);
				db.setDat(nam,uname, iD,password ,phn,dt, emaiL,bgrp,is);
			}
			try {
				Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/application/Login.fxml")));
				Stage home = (Stage) (((Node) event.getSource()).getScene().getWindow());
				home.setScene(sc);
				home.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void photo() throws FileNotFoundException {
		FileChooser fc= new FileChooser();
		file = fc.showOpenDialog(null);
	}

}
