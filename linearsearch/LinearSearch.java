/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package linearsearch;

/**
 *
 * @author Yourn
 */
import java.util.Random;
import java.util.Scanner;

public class LinearSearch {


    public int[] generateRandomArray(int number) {
        Random random = new Random();
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = random.nextInt(number);
        }
        System.out.print("The array: [");
        for(int i=0;i<array.length-1;i++){
            System.out.print(array[i]+", ");
        }
        System.out.println(array[array.length-1]+"]");
        return array;
    }
    public void linearSearch(int[] array, int search){
        int index = -1;
        for(int i = 0;i<array.length;i++){
            if(search == array[i]){
                index = i;
                break;
            }
        }
        if(index != -1){
            System.out.println("Found "+search+" at index: "+index);
        }
        else{
            System.out.println("Not found "+search+" in array");
        }
    }


    
}
class Main{
    public static void main(String[] args) {
        LinearSearch l = new LinearSearch();
        System.out.println("Enter number of array:");
        int numberOfArray = InputValidation.inputInteger(1, Integer.MAX_VALUE);
        System.out.println("Enter search value:");
        int search = InputValidation.inputInteger(1, Integer.MAX_VALUE);        
        int[] array = l.generateRandomArray(numberOfArray);
        l.linearSearch(array, search);
        
    }
}

