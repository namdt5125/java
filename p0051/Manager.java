/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.p0051;

/**
 *
 * @author Yourn
 */

public class Manager {
    
    public int menu(){
        System.out.println("========= Calculator Program =========");
        System.out.println("1. Normal Calculator");
        System.out.println("2. BMI Calculator");
        System.out.println("3. Exit");
        System.out.print("Please choice one option:");
        int option = InputValidation.inputInteger(1, 3);
        return option;
    }
    public void normalCalculator(){
        String operator;
        double number;
        System.out.println("----- Normal Calculator ------");
        System.out.print("Enter number: ");
        double memory = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);

        while(true){
            System.out.print("Enter Operator: ");
            operator = InputValidation.inputString("^[\\+\\-\\*\\/\\^\\=]$");
            switch (operator) {
                case "+":
                    System.out.print("Enter number: ");
                    number = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                    memory += number;
                    System.out.println("Result: "+memory);
                    break;
                case "-":
                    System.out.print("Enter number: ");
                    number = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                    memory -= number;
                    System.out.println("Result: "+memory);
                    break;
                case "*":
                    System.out.print("Enter number: ");
                    number = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                    memory *= number;
                    System.out.println("Result: "+memory);
                    break;
                case "/":
                    System.out.print("Enter number: ");
                    number = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                    memory /= number;
                    System.out.println("Result: "+memory);
                    break;
                case "^":
                    System.out.print("Enter number: ");
                    number = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);
                    Math.pow(memory, number);
                    System.out.println("Result: "+memory);
                    break;
                case "=":
                    System.out.println("Result: "+memory);
                    return;
            }
        }
    }
    public void BMICalculator(){
        System.out.println("----- BMI Calculator -----");
        System.out.print("Enter weight(kg): ");
        double weight = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        System.out.print("Enter Height(cm): ");
        double height = InputValidation.inputDouble(Double.MIN_VALUE, Double.MAX_VALUE);
        double BMINumber = weight / ((height*height) / 10000);
        if(BMINumber < 19){
            System.out.format("BMI number %.2f\n", BMINumber);
            System.out.println("BMI status: Under-standard");
        }
        else if(BMINumber >= 19 && BMINumber < 25){
            System.out.format("BMI number %.2f\n", BMINumber);
            System.out.println("BMI status: Standard");
        }
        else if(BMINumber >= 25 && BMINumber < 30){
            System.out.format("BMI number %.2f\n", BMINumber);
            System.out.println("BMI status: Overweight");
        }
        else if(BMINumber >= 30 && BMINumber < 40){
            System.out.format("BMI number %.2f\n", BMINumber);
            System.out.println("BMI status: Fat - should lose weight");
        }
        else if(BMINumber >= 40){
            System.out.format("BMI number %.2f\n", BMINumber);
            System.out.println("BMI status: Very Fat - should lose weight immediately");
        }
    }
}

class test{
    public static void main(String[] args) {
        Manager m = new Manager();
//        m.normalCalculator();
        m.BMICalculator();
    }
}

