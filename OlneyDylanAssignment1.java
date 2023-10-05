/**
 * Dylan Olney
 * CS1450 M/W
 * Due Jun 21
 * Assignment 1
 * This program will write random values to a file, place those values into 2 different arrays, sort the arrays, and then combine the arrays
 */ 

import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class OlneyDylanAssignment1 {
    
     public static void main(String[] args) throws IOException {
                 
         File fileName = new File("assignment1.txt");
         PrintWriter outputToFile = new PrintWriter(fileName);
         Scanner inputFromFile = new Scanner(fileName);
         
         final int MAX = 15;
         final int MIN = 1;
         int[] array1;
         int[] array2;
         
         //Create and display the size of the first and second array
         int size1 = (int)(Math.random() * (MAX - MIN + 1) + MIN);
         int size2 = (int)(Math.random() * (MAX - MIN + 1) + MIN);
         
         array1 = new int[size1];
         array2 = new int[size2];
         
         System.out.println("The first array will be size: " + size1);
         System.out.println("The second array will be size: " + size2);
         
         //For loop to create values to fill both arrays and writing those values to file assignment1.txt
         for(int i = 0; i < (size1 + size2); i++){
             
             int max = 49;
             int min = 0;
             
             int randomValue = (int)(Math.random() * (max - min + 1) + min);
             outputToFile.println(randomValue);
             System.out.println(randomValue);  
         }
         
         for(int i = 0; i < array1.length; i++){
             array1[i] = inputFromFile.nextInt();
         }
         
         for(int i = 0; i < array1.length; i++){
            System.out.println("Array1 [" + i + "] = " + array1[i]); 
         }
         
         for(int i = 0; i < array2.length; i++){
             array2[i] = inputFromFile.nextInt();
         }
         
         for(int i = 0; i < array2.length; i++){
            System.out.println("Array2 [" + i + "] = " + array2[i]); 
         }
         
         System.out.println("File path: " + fileName.getAbsolutePath());
         
         
         
         inputFromFile.close();
         outputToFile.close();
    }
}