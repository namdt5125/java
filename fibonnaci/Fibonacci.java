/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package fibonnaci;

/**
 *
 * @author Yourn
 */
public class Fibonacci {
     
    public void displayRecursion(){//tinh day fibonacci theo de quy
        for (int i = 0; i < 44; i++) {
            System.out.print(fibonacciRecursionMethod(i) + ", ");
        }
        System.out.print(fibonacciRecursionMethod(44));
    }
    
    public int fibonacciRecursionMethod(int n) {//
        if (n < 0) {
            return -1;
        } else if (n == 0 || n == 1) {
            return n;
        } else {
            return fibonacciRecursionMethod(n - 1) + fibonacciRecursionMethod(n - 2);
        }
    }
    public int[] fibonacciArrayMethod(){
        int[] array = new int[45];
        array[0] = 0;
        array[1] = 1;
        for(int i=2;i<45;i++){
            array[i] = array[i-1] + array[i-2];
        }
        return array;
    }
    public void displayArray(){//tinh fibonacci theo kieu array
        int[] array = this.fibonacciArrayMethod();
        for(int i=0;i<44;i++){
            System.out.print(array[i]+", ");
        }
        System.out.print(array[44]);
    }
}

class test{
    public static void main(String[] args) {
        Fibonacci fb = new Fibonacci();
        System.out.println("The 45 sequence fibonacci: ");
        fb.displayArray();
        System.out.println("");
        fb.displayRecursion();
    }
}


