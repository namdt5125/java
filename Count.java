/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package j1.s.pkg0008;

/**
 *
 * @author Yourn
 */
import java.util.*;

public class Count {
    public static void wordCount(String input){
        StringTokenizer tokenizer = new StringTokenizer(input);
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        System.out.println(wordCount);
    }
    public static void letterCount2(String input){
        Map<Character, Integer> charCount = new TreeMap<>();
        for (char ch : input.toCharArray()) {
            if (Character.isLetter(ch)) {
                charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
            }
        }
        System.out.println(charCount);
    }
    public static void letterCount(String input){
        Map<Character, Integer> letterCount = new LinkedHashMap<>();
        for(char ch : input.toCharArray()){
            if(Character.isLetter(ch)){
                letterCount.put(ch, letterCount.getOrDefault(ch,  0) + 1);
            }
        }
        System.out.println(letterCount);
    }
    public static void findMax(String input){
        Map<Character, Integer> letterCount = new LinkedHashMap<>();
        for(char ch : input.toCharArray()){
            if(Character.isLetter(ch)){
                letterCount.put(ch, letterCount.getOrDefault(ch,  0) + 1);
            }
        }
        Map.Entry<Character, Integer> max = null;
        for(Map.Entry<Character, Integer> entry : letterCount.entrySet()){
            if(max == null || entry.getValue() > max.getValue()){
                max = entry;
            }
        }
        if(max != null){
            System.out.println("Max key value is: "+max.getKey());
            System.out.println("Max value is: "+max.getValue());
        }
    }
}

class Main{
    public static void main(String[] args) {
        System.out.print("Enter your content:");
        String input = InputValidation.inputString("^[a-zA-Z\\s]+$");
        Count.wordCount(input);
        Count.letterCount(input);
        Count.findMax(input);
        
    }
}


