package lib;
import java.util.*;
import java.time.LocalDate;

public class Account extends Person {
    private ArrayList<LocalDate> dataLogin = new ArrayList<LocalDate>();
    private String password;
    private float balance;

    public Account(String ID , String name , String pw , Float balance){
        this.setIdPerson(ID);
        this.setFullname(name);
        this.password = pw;
        this.balance = balance;
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

    /**
     * @return float return the balance
     */
    public float getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void dopositeBalance(float balance) {
        this.balance += balance;
    }

    public void WithdraBalance(float balance) {
        this.balance -= balance;
    }

}
