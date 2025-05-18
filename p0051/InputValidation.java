/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.p0051;

/**
 *
 * @author Yourn
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Yourn
 */
import java.util.Scanner;

public class InputValidation {

    // Nhập số nguyên, kiểm tra rỗng, sai kiểu, ngoài khoảng
    public static int inputInteger(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        int number;
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("❌ Input cannot be empty.");
                continue;
            }

            try {
                number = Integer.parseInt(input);
                if (number < min || number > max) {
                    System.out.println("❌ Input must be between " + min + " and " + max + ".");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Must be an integer.");
            }
        }
    }

    // Nhập số thực (float hoặc double), kiểm tra rỗng, sai kiểu, ngoài khoảng
    public static double inputDouble(double min, double max) {
        Scanner scanner = new Scanner(System.in);
        double number;
        while (true) {

            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("❌ Input cannot be empty.");
                continue;
            }

            try {
                number = Double.parseDouble(input);
                if (number < min || number > max) {
                    System.out.println("❌ Input must be between " + min + " and " + max + ".");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Must be a number (float/double).");
            }
        }
    }

    // Nhập chuỗi, kiểm tra rỗng và regex
    public static String inputString(String regex) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("❌ Input cannot be empty.");
                continue;
            }

            if (!input.matches(regex)) {
                System.out.println("❌ Invalid format. Please try again.");
                continue;
            }

            return input;
        }
    }
}


