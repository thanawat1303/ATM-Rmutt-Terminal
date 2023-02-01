import lib.*;
import lib.function;
import netscape.javascript.JSObject;

// libraly json
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

import javax.xml.transform.Templates;;

public class ATM implements ATMAction {

    public void Checkable(Account account , float Rate) {
        // ส่วนที่เพิ่มในการเลือกการแสดงยอดเงิน เพิ่มเติม
            // รับเป็น Rate BTC เข้ามายัง method โดยแก้ไขที่ไฟล์ ATMAction ด้วย เพิ่มเติม
            float typeShow = function.typeCurrency(); //ประกาศใช้ฟังก์ชั่นจากไฟล์ฟังชั่น หรือ Class function เพิ่มเติม
            String balance = (typeShow == 1) ? account.getBalance() + " Bath" : (account.getBalance()/Rate) + " BTC"; //เลือกข้อความที่ต้องการแสดงแยกเป็น bath กับ BTC เพิ่มเติม
        // ส่วนที่เพิ่มในการเลือกการแสดงยอดเงิน เพิ่มเติม

        System.out.println("-----------------------");
        System.out.println("Balance in the account : " + balance);
        System.out.println("-----------------------");
    }
    public void Withdrawable(Account account , float Rate) {
        System.out.println("-----------------------");
        
        // ส่วนฟังก์ชั่นในการรับประเภทของสกุลเงิน เพิ่มเติม
            // รับเป็น Rate BTC เข้ามายัง method โดยแก้ไขที่ไฟล์ ATMAction ด้วย 
            float typeShow = function.typeCurrency(); //ประกาศใช้ฟังก์ชั่นจากไฟล์ฟังชั่น หรือ Class function เพิ่มเติม

        do {
            try {
                float money = function.textFieldNumber("Amount to withdraw : ");

                // ส่วนแปลงจำนวนเงินคงเหลือตามสกุลเงิน เพิ่มเติม
                    float Balance = (typeShow == 1) ? account.getBalance() : account.getBalance()/Rate;

                if(money == 0) {
                    System.out.print("!!!------ Cancel withdraw ------!!!");
                    break;
                }
                else if (Balance >= money) {

                    // ส่วนการส่งข้อมูล โดยจะเช็คแล้วทำการส่งตามสกุลเงิน เพิ่มเติม
                        account.WithdraBalance((typeShow == 1) ? money : money*Rate);

                    // ส่วนเลือกสกุลเงินในการแสดง เพิ่มเติม
                        String Currency = (typeShow == 1) ? " Bath" : " BTC";

                    System.out.println("withdraw success : " + money + Currency);
                    break;
                } else
                    System.out.println("!!!------ Your amount is not enough ------!!!");
            } catch (Exception e) {
                System.out.println("!!!------ Please enter numerical information ------!!!");
            }
        } while (true);
        System.out.println("-----------------------");
    }
    public void Depositeable(Account account) {
        System.out.println("--------------------------------");
        do {
            try {
                float money = function.textFieldNumber("Amount to be deposited : ");
                if(money == 0) {
                    System.out.print("!!!------ Cancel deposit ------!!!");
                } else {
                    account.dopositeBalance(money);
                    System.out.println("Deposit success : " + money + " Bath");
                }
                break;
            } catch (Exception e) {
                System.out.println("!!!------ Please enter numerical information ------!!!");
            }
        } while(true);
        System.out.println("--------------------------------");
    }
    public void Transferable(Account account, ArrayList<Account> arrayATM) {
        do {
            System.out.println("--------------------------------");
            String IdAccount = function.textFieldString("account ID to be transferred to : ");
            if(!IdAccount.equals(account.getIdPerson())){
                for (int x = 0; x < arrayATM.size(); x++) {
                    if (arrayATM.get(x).getIdPerson().equals(IdAccount)) {
                        do {
                            try {
                                System.out.println("Balance in the account : "+account.getBalance()+" Bath");
                                float money = function.textFieldNumber("Amount you want to transfer : ");
                                if(money == 0){
                                    System.out.print("!!!------ Cancel transfer ------!!!");
                                    break;
                                }
                                else if(account.getBalance() >= money) {
                                    arrayATM.get(x).dopositeBalance(money);
                                    account.WithdraBalance(money);
                                    System.out.println("Transfer success : "+money+" Bath");
                                    System.out.println("Balance in the account : "+account.getBalance()+" Bath");
                                    break;
                                } else {
                                    System.out.println("!!!------ Your amount is not enough ------!!!");
                                }
                            } catch(Exception e) {
                                System.out.println("!!!------ Please enter numerical information ------!!!");
                            }
                        } while (true);
                        return;
                    }
                }
                break;
            } else {
                System.out.println("!!!------ ID Your Account  ------!!!");
            }
        }  while (true);
        System.out.println("!!!------ Invaid ID to be transfer ------!!!");
        System.out.println("--------------------------------");
    }


    public static void main(String[] args) throws Exception {
        Manager manager;
        String IdAdmin;
        String pwAdmin;

        JSONParser parser = new JSONParser();
        JSONObject adminDB = new JSONObject();

        // ส่วน Value ที่เพิ่มไว้เก็บค่า Rate BTC เพิ่มเติม
            float BTCtoBath = 0;

        try {
            Object admin = parser.parse(new FileReader("src/db.json"));
            adminDB = (JSONObject)admin;
        } catch(Exception e) {
            e.printStackTrace();
        }

        if(adminDB.get("ID") == null) {
                while(true) {
                    IdAdmin = function.textFieldString("Enter ID number admin : ");
                    if(IdAdmin.length() != 13){
                        System.out.println("!!!------ Please enter correct ID number ------!!!\n");
                        continue;
                    } 
                    break;
                } 
        
                while(true) {
                    pwAdmin = function.textFieldString("Enter Password 6 character : ");
                    if(pwAdmin.length() != 6){
                        System.out.println("!!!------ Please enter correct Password ------!!!\n");
                        continue;
                    } 
                    break;
                } 

                adminDB.put("ID", IdAdmin);
                adminDB.put("password" , pwAdmin);

                System.out.println(adminDB);
                
                FileWriter adminNew = new FileWriter("src/db.json");
                adminNew.write(adminDB.toJSONString());
                adminNew.close();

                manager = new Manager(IdAdmin , pwAdmin);
        } else {
            manager = new Manager(adminDB.get("ID").toString() , adminDB.get("password").toString());
        }

        while(true) {
            System.out.println("\n--------------------------------");
            String IDAdmin = function.textFieldString("ID number admin : ");
            String PwAdmin = function.textFieldString("Password : ");

            if(manager.getIdPerson().equals(IDAdmin) && manager.getPassword().equals(PwAdmin))
                break;
            else 
                System.out.println("!!!------ Incorrect Login ------!!!");
        }
        System.out.println("--------------------------------\n");

        manager.setDataLogin();
        float numAccount = 0; //จำนวนผู้ใช้งาน
        String nameATM = "ATM ComputerThanyaburi Bank";
        
        // ส่วนที่เพิ่ม การรับเรทเงิน BTC กับ Bath เพิ่มเติม
            do {
                try{
                    BTCtoBath = function.textFieldNumber("Please enter BTC rate=> ");
                    break;
                } catch(Exception e){
                    System.out.println("!!!------ Please enter number ------!!!");
                }
            } while (true);
        System.out.println("1 BTC is "+BTCtoBath+" Bath\n");
        // ส่วนที่เพิ่ม การรับเรทเงิน BTC กับ Bath
        
        // รับค่าจำนวนบัญชี และตรวจสอบค่าจากที่รับ
        do {
            try {
                numAccount = function.textFieldNumber("Step 1. Enter amount of all account = ");
                if (numAccount >= 2)
                    break;
                else
                    System.out.println("!!!------ Please enter account then 5 ------!!!");
            } catch (Exception e){
                System.out.println("!!!------ Please enter number ------!!!");
            }
            System.out.println("");
        } while (true);

        // รับข้อมูลบัญชี
        System.out.println("Step 2. Enter Detail of each account.");
        ArrayList<Account> accountList = new ArrayList<Account>();
        for (int x = 0; x < numAccount; x++) {
            System.out.println("No." + String.valueOf(x + 1));
            String idInput;
            String nameInput;
            String password;
            float money;
            do {
                idInput = function.textFieldString("Account ID=");
                if (idInput.length() != 13) {
                    System.out.println("!!!------ Account ID equal 13 charector -----!!!");
                    continue;
                }

                // check id ซ้ำซ้อน
                try {
                    for (int y = 0; y < accountList.size(); y++) {
                        if (accountList.get(y).getIdPerson().equals(idInput)) {
                            System.out.println("!!!------ Account ID already ------!!!");
                            throw new NullPointerException("Account ID already");
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
                break;
            } while (true);

            do {
                nameInput = function.textFieldString("Account Name=");
                if (nameInput.length() <= 50 && nameInput.length() != 0)
                    break;
                else
                    if(nameInput.length() > 50) 
                        System.out.println("!!!------- Account Name less than or equal to 50 charector ------!!!");
                    else if(nameInput.length() == 0)
                        System.out.println("!!!------ Account Name no emply ------!!!");
            }while (true);

            do {
                password = function.textFieldString("Password=");
                if (password.length() == 4)
                    break;
                else
                    System.out.println("!!!------ Password equal 4 charector ------!!!");
            } while (true);

            do {
                try {
                    money = function.textFieldNumber("Balance=");
                    if (money <= 1000000 && money != 0) {
                        break;
                    } else if(money > 1000000) {
                        System.out.println("!!!------ Please enter money no more than 1,000,000 ------!!!");
                    } else {
                        System.out.println("!!!------ Please enter money no equal 0 ------!!!");
                    }
                } catch (Exception e) {
                    System.out.println("!!!------ Please enter number ------!!!");
                }
            } while (true);
            accountList.add((new Account(idInput, nameInput, password, money)));
            System.out.println("");
        }

        // ส่วน Service Account
        do {
            System.out.println("-------------------------------------");
            System.out.println(nameATM);
            String idScanner = function.textFieldString("Account ID : ");
            int posisionAccount = 0;
            String statusLogin = "";
            for (int x = 0; x < accountList.size(); x++) {
                statusLogin = "";
                if (accountList.get(x).getIdPerson().equals(idScanner)) {
                    // Max input password 3 round
                    for (int loopEnd = 3; loopEnd > 0; loopEnd--) {
                        // นับจำนวนครั้งที่กรอก password ได้
                        System.out.print("(" + String.valueOf(loopEnd) + ")");
                        String passScanner = function.textFieldString("Account Password : ");
                        if (accountList.get(x).getPassword().equals(passScanner)) {
                            statusLogin = "pass login";
                            posisionAccount = x;
                            accountList.get(x).setDataLogin();
                            break;
                        } else {
                            statusLogin = "";
                        }
                    }
                    break;
                } else {
                    statusLogin = "!!!------ Invaid account ------!!!";
                }
            }

            if (statusLogin.equals("pass login")) {
                do {
                    System.out.print("Menu Service\n1. Account Balance\n2. Withdrawal\n3. Depositeable\n4. Transferable\n5. Check Login\n6. Exit\n");
                    try {
                        float choose = function.textFieldNumber("Choose : ");
                        ATM atm = new ATM();
                        switch ((int)choose) {
                            case 1:
                                atm.Checkable(accountList.get(posisionAccount) , BTCtoBath);
                                break;
                            case 2:
                                atm.Withdrawable(accountList.get(posisionAccount) , BTCtoBath);
                                break;
                            case 3:
                                atm.Depositeable(accountList.get(posisionAccount));
                                break;
                            case 4:
                                atm.Transferable(accountList.get(posisionAccount), accountList);
                                break;
                            case 5:
                                System.out.println("---------------------------");
                                System.out.println(accountList.get(posisionAccount).getDataLogin());
                                System.out.println("---------------------------");
                                break;
                            case 6:
                                break;
                            default:
                                System.out.println("!!!------ Please enter number 1-6 ------!!!");
                                break;
                        }
                        if(choose == 6)
                            break;
                    } catch (Exception e) {
                    }
                    System.out.println("-------------------------------------");
                } while (true);
            } else if (statusLogin.equals("!!!------ Invaid account ------!!!")) {
                System.out.println(statusLogin);
            } else {
                System.out.println("!!!------ Account no correct ------!!!");
            }
            System.out.println("");
        } while (true);
    }
}
