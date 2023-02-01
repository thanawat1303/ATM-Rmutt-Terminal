package lib;
import java.util.*;

public interface ATMAction {
  // เพิ่มตัวแปร Rate เพิ่มเติม
    void Checkable(Account account , float Rate);
    void Withdrawable(Account account , float Rate);
  void Depositeable(Account account);
  void Transferable(Account account , ArrayList<Account> arrayATM);
}

