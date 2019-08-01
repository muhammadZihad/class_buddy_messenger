package application;

import java.io.IOException;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginController {
	@FXML
	JFXTextField name;
	@FXML
	JFXPasswordField pass;
	String nam;
	DataBase db = new DataBase();
	Alert alt = new Alert(Alert.AlertType.ERROR);
	public void login(ActionEvent ev) {		// if match username and password match then it goes to the home window
		nam = name.getText();
		String pas = pass.getText();
		
		int ck = db.homelogin(nam, pas);
		if(ck==1){
			try {
				Stage stage = new Stage();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/Home.fxml"));
				Parent root = (Parent) loader.load();
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.getIcons().add(new Image("/application/logo.jpg"));
				stage.setTitle(Details.fName+"_ClassBuddy");
				((Node)(ev.getSource())).getScene().getWindow().hide();
				stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		else {
			alt.setTitle("Error");
			alt.setHeaderText("Invalid Username or Password");
			alt.show();
		}
	}
	
	public void newAccount(ActionEvent ev) {	// this windows redirects user to Sign Up window
		try {
			Scene sc = new Scene(FXMLLoader.load(getClass().getResource("/application/SignUp.fxml")));
			Stage home = (Stage) (((Node) ev.getSource()).getScene().getWindow());
			home.setTitle("Create New Account -ClassBuddy");
			home.setScene(sc);
			home.getIcons().add(new Image("/application/logo.jpg"));
			home.setResizable(false);
			home.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
