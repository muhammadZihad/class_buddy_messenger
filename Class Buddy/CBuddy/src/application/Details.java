package application;

import javafx.event.ActionEvent;
import javafx.scene.Node;

public class Details {
	public static String uName;
	public static String fName;
	public static String uId;
	public static String uPhn;
	public static String email;
	public static String dob;
	public static String bg;
	public static String pass;
	public static String addr;
	public static String getuName() {
		return uName;
	}
	public static void setuName(String uName) {
		Details.uName = uName;
	}
	public static String getfName() {
		return fName;
	}
	public static void setfName(String fName) {
		Details.fName = fName;
	}
	public static String getuId() {
		return uId;
	}
	public static void setuId(String uId) {
		Details.uId = uId;
	}
	public static String getuPhn() {
		return uPhn;
	}
	public static void setuPhn(String uPhn) {
		Details.uPhn = uPhn;
	}

	public static String getEmail() {
		return email;
	}

	public static String getDob() {
		return dob;
	}

	public static String getBg() {
		return bg;
	}

	public static String getPass() {
		return pass;
	}


	public void close(ActionEvent event) {
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
}

