/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package convertbase;

/**
 *
 * @author Yourn
 */
import java.util.Scanner;

public class UserInput {
    static Scanner sc = new Scanner(System.in);

    public static int baseNumberInput() {
        System.out.print("Enter input base (2/10/16): ");
        return Integer.parseInt(InputValidation.inputString("^2|10|16$"));
    }

    public static int baseNumberOutput(int inputBase) {
        String choice;
        while (true) {
            System.out.print("Enter output base (2/10/16), not same as input: ");
            choice = InputValidation.inputString("^(2|10|16)$");
            if (Integer.parseInt(choice) != inputBase) break;
            System.out.println("Output base must be different from input base.");
        }
        return Integer.parseInt(choice);
    }

    public static String process(int input, int output) {
        String value;
        int decimalValue;
        switch (input) {
            case 2:
                System.out.print("Enter binary value: ");
                value = InputValidation.inputString("^[01]+$");
                decimalValue = ConvertBase.binToDec(value);
                return ConvertBase.decToHexAndBin(decimalValue, output);
            case 16:
                System.out.print("Enter hexadecimal value: ");
                value = InputValidation.inputString("^[0-9A-F]+$");
                decimalValue = ConvertBase.hexToDec(value);
                return ConvertBase.decToHexAndBin(decimalValue, output);
            default:
                System.out.print("Enter decimal value: ");
                value = InputValidation.inputString("^[0-9]+$");
                decimalValue = Integer.parseInt(value);
                return output == 2 ? ConvertBase.decToBin(decimalValue)
                                   : ConvertBase.decToHex(decimalValue);
        }
    }

    public static void display(String result) {
        System.out.println("Converted result: " + result);
    }
}

class Test {
    public static void main(String[] args) {
        while (true) {
            int input = UserInput.baseNumberInput();
            int output = UserInput.baseNumberOutput(input);
            String result = UserInput.process(input, output);
            UserInput.display(result);

            System.out.print("Do you want to convert again? (y/n): ");
            String again = InputValidation.inputString("^[yn]$");
            if (!again.equals("y")) break;
        }
    }
}

