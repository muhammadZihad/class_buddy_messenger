package application;

import javafx.scene.control.Alert;

import java.io.InputStream;
import java.sql.*;

public class DataBase extends Details{
	private Connection con;
	private Statement st;
	private ResultSet rs;
	Alert alt = new Alert(Alert.AlertType.INFORMATION);
	Alert alt2 = new Alert(Alert.AlertType.ERROR);
	
	
	public DataBase() {
		try {
				Class.forName("com.mysql.jdbc.Driver");
				con =(Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/classbuddy","root","");
				st = con.createStatement();
			
			}catch(Exception e) {
			alt2.setTitle("Error");
			alt2.setHeaderText("Something Error.");
			alt2.setContentText("Please try again.");
			alt2.show();
		}
	}
	
	
	
	
	public ResultSet getmsg() {						// Get messeges from database 
		try {
			rs = st.executeQuery("Select * from messege");
			return rs;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			alt2.setTitle("Error");
			alt2.setHeaderText("Something Error.");
			alt2.setContentText("Please try again.");
			alt2.show();
		}
		return rs;
	}
	
	public int homelogin(String name,String pass) {				// Check username and password from database

		try {
			rs = st.executeQuery("SELECT * FROM buddies");
			while(rs.next()) {
				if(name.equals(rs.getString("Username")) && pass.equals(rs.getString("Password"))) {
					loginsearch(rs);
					return 1;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			alt2.setTitle("Error");
			alt.setHeaderText("Something Error.");
			alt.setContentText("Please try again.");
			alt.show();
		}
		return 2;
	}
	
	public void setData(String name,String uname,String id,String pass,String phone,String dob,String email,String bg) {			//store new account data into database
		try {
			st.executeUpdate("INSERT INTO `buddies`(`Name`, `Username`, `ID`, `Password`, `Phone`, `Dob`, `Email`, `BG`, `Photo`) Values('"+name+"','"+uname+"','"+id+"','"+pass+"','"+phone+"','"+dob+"','"+email+"','"+bg+"','null')");
			alt.setTitle("Congratulations");
			alt.setHeaderText("Account has been created.\nPlease Login.");
			alt.show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDat(String name, String uname, String id, String pass, String phone, String dob, String email, String bg, InputStream is) {			//store new account data into database
		try {

			PreparedStatement ps = con.prepareStatement("INSERT INTO `buddies`(`Name`, `Username`, `ID`, `Password`, `Phone`, `Dob`, `Email`, `BG`, `Photo`) Values(?,?,?,?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setString(2,uname);
			ps.setString(3,id);
			ps.setString(4,pass);
			ps.setString(5,phone);
			ps.setString(6,dob);
			ps.setString(7,email);
			ps.setString(8,bg);
			ps.setBlob(9,is);
			ps.executeUpdate();
			alt.setTitle("Congratulations");
			alt.setHeaderText("Account has been created.\nPlease Login.");
			alt.show();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			alt2.setTitle("Error");
			alt2.setHeaderText("Something Error.");
			alt2.setContentText("Please try again.");
			alt2.show();
		}
	}
	
	public void sendms(String name ,String text) throws SQLException {			// Store msg in databse
		st.executeUpdate("INSERT INTO `messege` (`name`, `msg`) VALUES ('"+name+"', '"+text+"');");
	}
	
	
	public void loginsearch(ResultSet r) {		//Save user data while login
		try {
			super.setfName(r.getString("Name"));
			super.setuId(r.getString("ID"));
			super.setuPhn(r.getString("Phone"));
			super.setuName(r.getString("Username"));
			super.pass = r.getString("Password");
			super.email = r.getString("Email");
			super.bg = r.getString("BG");
			super.dob = r.getString("Dob");
			super.addr = r.getString("Address");
		} catch (Exception e) {
			alt2.setTitle("Error");
			alt2.setHeaderText("Something Error.");
			alt2.setContentText("Please try again.");
			alt2.show();

		}
	}
	
	public ResultSet proSearch(String name,String pass) throws SQLException { //User profile data searching
		rs = st.executeQuery("SELECT * FROM buddies");
		while(rs.next()) {
			if(name.equals(rs.getString("Name"))) {
				return rs;
			}
		}
		return rs ;
	}

	public void updateprofile(String uname,String pass,String email,String addr,String phn,String id) throws SQLException {		//Update user information
		st.executeUpdate("UPDATE `buddies` SET `Username` = '"+uname+"', `Password` = '"+pass+"', `Phone` = '"+phn+"', `Email` = '"+email+"',`Address` = '"+addr+"' WHERE `buddies`.`ID` = '"+id+"';");
		alt.setTitle("Congratulations");
		alt.setHeaderText("Information Updated.");
		alt.setContentText("Please login again for seeing the changes");
		alt.show();
	}

	public ResultSet frndlst() throws SQLException{		// Get FriendList from database and transfer it to table
		rs = st.executeQuery("SELECT * FROM buddies ");
		return rs;
	}

	public ResultSet Searchfrnd(String id )  throws SQLException{		// Get FriendList from database and transfer it to table
		rs = st.executeQuery("SELECT * FROM `buddies` WHERE `ID` LIKE '"+id+"'");
		return rs;
	}
	public ResultSet Searchme()  throws SQLException{		// Get FriendList from database and transfer it to table
		rs = st.executeQuery("SELECT * FROM `buddies` WHERE `ID` LIKE '"+Details.getuId()+"'");
		return rs;
	}

	public ResultSet chatMsg() {						// Get chat messeges from database
		try {
			rs = st.executeQuery("Select * from pmessege");
			return rs;

		} catch (SQLException e) {
			alt2.setTitle("Error");
			alt2.setHeaderText("Something Error.");
			alt2.setContentText("Please try again.");
			alt2.show();
		}
		return rs;
	}

	public void chatsend(String sndr, String rcvr,String msg){
		try {
			st.executeUpdate("INSERT INTO `pmessege` (`sender`,`reciever`, `msg`) VALUES ('"+sndr+"', '"+rcvr+"','"+msg+"');");
		} catch (SQLException e) {
			alt2.setTitle("Error");
			alt2.setHeaderText("Messege not send.");
			alt2.setContentText("Please try again.");
			alt2.show();
		}
	}
}