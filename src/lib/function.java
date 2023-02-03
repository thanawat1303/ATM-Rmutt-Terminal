package lib;
import java.util.*;;

public class function {
    static public String textFieldString(String textShow) {
        System.out.print(textShow);
        return new Scanner(System.in).nextLine();
    }

    static public float textFieldNumber(String textShow) {
        System.out.print(textShow);
        return new Scanner(System.in).nextFloat();
    }

    // method รับค่าประเภทของสกุลเงิน เพิ่มเติม
        static public float typeCurrency(){
            float typeShow = 0;
            System.out.println("1. Bath\n2. BTC");
            do{
                try{
                    typeShow = textFieldNumber("Please enter (1 or 2) : ");
                    if(typeShow >= 1 && typeShow <= 2) break;
                    else System.out.println("!!!------ Please enter number 1 or 2 ------!!!");
                }catch(Exception e){
                    System.out.println("!!!------ Please enter number ------!!!");
                }
            }while(true);
            return typeShow;
        }
    // method รับค่าประเภทของสกุลเงิน เพิ่มเติม
}
