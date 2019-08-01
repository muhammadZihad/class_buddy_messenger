package application;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class FDetails {
    private StringProperty name;
    private StringProperty uname;
    private StringProperty id;
    private StringProperty pass;
    private StringProperty phn;
    private StringProperty email;
    private StringProperty bg;
    private StringProperty addr;
    private StringProperty dob;

    public String getName() {
        return name.get();
    }


    public String getUname() {
        return uname.get();
    }


    public String getId() {
        return id.get();
    }


    public String getPass() {
        return pass.get();
    }


    public String getPhn() {
        return phn.get();
    }


    public String getEmail() {
        return email.get();
    }


    public String getBg() {
        return bg.get();
    }


    public String getAddr() {
        return addr.get();
    }


    public String getDob() {
        return dob.get();
    }

    public FDetails(String name, String uname, String id, String pass, String phn, String email, String bg, String addr, String dob) {
        this.name = new SimpleStringProperty(name);
        this.uname =new SimpleStringProperty(uname);
        this.id = new SimpleStringProperty(id);
        this.pass = new SimpleStringProperty(pass);
        this.phn = new SimpleStringProperty(phn);
        this.email = new SimpleStringProperty(email);
        this.bg = new SimpleStringProperty(bg);
        this.addr = new SimpleStringProperty(addr);
        this.dob = new SimpleStringProperty(dob);
    }
}
