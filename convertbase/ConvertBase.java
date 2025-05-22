/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package convertbase;

/**
 *
 * @author Yourn
 */
import java.util.*;
import java.math.BigInteger;

public class ConvertBase {
    final String HEX_LIMIT = "7FFFFFFF";
    final String BIN_LIMIT = "1111111111111111111111111111111";
    public static final char[] hexDigits = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };
    public static final char[] binDigits = {
        '0', '1'
    };
    //30
    public static String decToHexAndBin(int decimal, int output) {
        String hexa = "";
        int dec1 = decimal;
        while (dec1 != 0) {
            hexa = hexDigits[dec1 % 16] + hexa;
            dec1 /= 16;
        }
        String bin = "";
        int dec2 = decimal;
        while (dec2 != 0) {
            bin = binDigits[dec2 % 2] + bin;
            dec2 /= 2;
        }
        if(output == 2){
            return bin;
        }
        else{
            return hexa;
        }
    }


    public static int binToDec(String binary) {
        int decimal = 0;
        for (int i = 0; i < binary.length(); i++) {
            char c = binary.charAt(i);
            int value = c - '0'; // '0' → 0, '1' → 1
            decimal += Math.pow(2, binary.length()- i - 1) * value; 
        }
        return decimal;
    }

    public static int hexToDec(String hexa) {
        int decimal = 0;
        hexa = hexa.toUpperCase(); // đảm bảo ký tự chữ in hoa
        for (int i = 0; i < hexa.length(); i++) {
            char c = hexa.charAt(i);
            int value = (c >= '0' && c <= '9') ? (c - '0') : (c - 'A' + 10);
            decimal += Math.pow(16, hexa.length()- i - 1) * value; 
        }
        return decimal;
    }
    public static void display(){
        
    }
    
    //--------------------------------------------------------------------------------------
    
    public static String decToHex(int decimal) {
        String hexa = "";
        int deci = decimal;
        while (deci != 0) {
            hexa = hexDigits[deci % 16] + hexa;
            deci /= 16;
        }
        return hexa;
    }

    public static String decToBin(int decimal) {
        String bin = "";
        int deci = decimal;
        while (deci != 0) {
            bin = binDigits[deci % 2] + bin;
            deci /= 2;
        }
        return bin;
    }
    public static void convertDecimalToBinary(int decimal) {
        int remainder, temp = decimal, lenBin = 0, i = 0;
        while (temp != 0) {
            temp /= 2;
            lenBin++;
        }
        temp = decimal;
        int[] array = new int[lenBin];
        while (temp != 0) {
            remainder = temp % 2;
            temp /= 2;
            array[i] = remainder;
            i++;
        }
        System.out.print("Dec to bin result: ");
        for (i = lenBin - 1; i >= 0; i--) {
            System.out.print(array[i]);
        }
        System.out.println("");
    }

    public static int hexToDec0(String hex) {
        return Integer.parseInt(hex, 16);
    }

    public static int binToDec0(String bin) {
        return Integer.parseInt(bin, 2);
    }

    public static String decToHex2(int dec) {
        return Integer.toHexString(dec).toUpperCase();
    }

    
    
    
    
    public static String decToHex2(String decimal, int output) {
        String hexa = "";
        BigInteger dec1 = new BigInteger(decimal);
        BigInteger zero = BigInteger.ZERO;
        BigInteger sixteen = new BigInteger("16");

        // If the input is 0
        if (dec1.equals(zero)) {
            return "0";
        }

        // Convert decimal to hexadecimal
        while (dec1.compareTo(zero) > 0) {
            BigInteger[] divRem = dec1.divideAndRemainder(sixteen);
            int remainder = divRem[1].intValue(); // Remainder to use as index
            hexa = hexDigits[remainder] + hexa;
            dec1 = divRem[0]; // Update dec with quotient
        }
        
        String bin = "";
        BigInteger dec2 = new BigInteger(decimal);
        BigInteger two = new BigInteger("2");

        // If the input is 0
        if (dec2.equals(zero)) {
            return "0";
        }

        // Convert decimal to hexadecimal
        while (dec2.compareTo(zero) > 0) {
            BigInteger[] divRem = dec2.divideAndRemainder(two);
            int remainder = divRem[1].intValue(); // Remainder to use as index
            bin = binDigits[remainder] + bin;
            dec2 = divRem[0]; // Update dec with quotient
        }

        
        if(output == 2){
            return bin;
        }
        return hexa;
    }
//    public static int binToDec(String binary) {
//        int decimal = 0;
//        for (int i = 0; i < binary.length(); i++) {
//            char c = binary.charAt(i);
//            int value = c - '0'; // '0' → 0, '1' → 1
//            decimal += Math.pow(2, binary.length()- i - 1) * value; 
//        }
//        return decimal;
//    }
public static BigInteger binToDec2(String binary) {
    BigInteger decimal = BigInteger.ZERO;
    BigInteger two = new BigInteger("2");

    for (int i = 0; i < binary.length(); i++) {
        char c = binary.charAt(i);
        int value = c - '0'; // '0' → 0, '1' → 1

        // exponent = binary.length - i - 1
        int exponent = binary.length() - i - 1;

        // decimal += 2^exponent * value
        if (value == 1) {
            decimal = decimal.add(two.pow(exponent));
        }
    }

    return decimal;
}

public static BigInteger hexToDec2(String hexa) {
    BigInteger decimal = BigInteger.ZERO;
    BigInteger sixteen = new BigInteger("16");

    hexa = hexa.toUpperCase(); // đảm bảo chữ in hoa

    for (int i = 0; i < hexa.length(); i++) {
        char c = hexa.charAt(i);
        int value = (c >= '0' && c <= '9') ? (c - '0') : (c - 'A' + 10);
        int exponent = hexa.length() - i - 1;

        BigInteger multiplier = sixteen.pow(exponent);
        decimal = decimal.add(BigInteger.valueOf(value).multiply(multiplier));
    }

    return decimal;
}


    
    
}
class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigInteger result = ConvertBase.hexToDec2("7FFFFFF");
        System.out.println(result);

    }
}
