package project.mobile.mnemosyne;

import java.security.MessageDigest;
import java.util.Date;

public class User {
    private int _id;
    private String username;
    private String password;
    private Date date_of_birth;

    public User(String username, String password, Date date_of_birth) {
        this.username = username;
        this.password = password;
        this.date_of_birth = date_of_birth;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getHashedPassword() throws Exception {
        return computeHash(this.password);
    }

    //Hash password using SHA-256 Algorithm
    private String computeHash(String input) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();

        byte[] byteData = digest.digest(input.getBytes("UTF-8"));
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
