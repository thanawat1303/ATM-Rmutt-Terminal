package lib;
import java.util.*;
import java.time.LocalDate;

public class Manager extends Person {
    private ArrayList<LocalDate> dataLogin = new ArrayList<LocalDate>();
    private String password;

    public Manager(String ID , String pw){
        this.setIdPerson(ID);
        this.password = pw;
    }

    /**
     * @return ArrayList<LocalDate> return the dataLogin
     */
    public ArrayList<LocalDate> getDataLogin() {
        return dataLogin;
    }

    /**
     * @param dataLogin the dataLogin to set
     */
    public void setDataLogin() {
        this.dataLogin.add(LocalDate.now());
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
