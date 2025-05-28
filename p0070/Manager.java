/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.p0070;

/**
 *
 * @author Yourn
 */
import java.util.*;

public class Manager {

    public void menu() {
        System.out.println("Chose:");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
        int choice = InputValidation.inputInteger(1, 3, "Enter option:");
        switch (choice) {
            case 1:
                vietnameseFun();
                break;
            case 2:
                englishFun();
                break;
        }
    }

    public void vietnameseFun() {
        Locale l = new Locale("vi");
        ResourceBundle vietnamese = ResourceBundle.getBundle("languages/" + l, l);
        System.out.println(vietnamese.getString("greeting"));
        System.out.println(vietnamese.getString("test"));
    }

    public void englishFun() {
        Locale l = new Locale("en");
        ResourceBundle vietnamese = ResourceBundle.getBundle("languages/" + l, l);
        System.out.println(vietnamese.getString("greeting"));
        System.out.println(vietnamese.getString("test"));
    }

    public String genCaptcha() {
        Random r = new Random();
        String captcha = "";
        for (int i = 0; i < 6; i++) {
            int randomChar = r.nextInt(3);
            switch (randomChar) {
                case 0:
                    int upperChar = r.nextInt(26) + 'A';
                    captcha += (char)upperChar;
                    break;
                case 1:
                    int lowerCase = r.nextInt(26) + 'a';
                    captcha += (char)lowerCase;
                    break;
                case 2:
                    int number = r.nextInt(10);
                    captcha += number;
                    break;
            }
        }
        System.out.println(captcha);
        return captcha;
    }
    public void checkCaptcha(String captcha){
        System.out.print("Enter check captcha: ");
        String inputCaptcha = InputValidation.inputString("^[a-zA-Z0-9]+$");
        return captcha.contains(inputCaptcha);
    }

}

class Main {

    public static void main(String[] args) {
        Manager m = new Manager();
        m.genCaptcha();
        System.out.println(m.checkCaptcha(m.genCaptcha()));
    }
}
