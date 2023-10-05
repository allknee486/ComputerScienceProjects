/**
 * Dylan Olney
 * CS1450 M/W
 * Due July 10
 * Assignment 5
 * This program will create a stack and then find/display all even numbers in the stack. 
 * Two stack will then be read from a file and combined/sorted in one combined stack. 
 * Any duplicate values from the two original stacks will be added to a separate stack.
 * Two stacks will then be read from another set of two files in the form of strings.
 * Those stacks will then be combined, sorted, and have all duplicates removed.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Stack;

public class OlneyDylanAssignment5 {
    
    public static void main(String[] args) throws IOException {
        
        //Array of values to be added to stack
        int[] values = {12,9,3,10,4,2,5,15,7,11,14};
        
        Stack<Integer> stack = new Stack<>();
        
        //Adding array of values to the stack
        for(int i: values){
            stack.push(i);
        }
        
        //Integer value for the number of even values in stack
        int evenValues = findNumEven(stack);
        
        //Displays stack in the console
        System.out.println("\nStack Values After Find Number of Even Values");
        System.out.println("------------------------------");
        printStack(stack);
        
        //Display evenValues variable in the console
        System.out.println("Total even values in the stack: " + evenValues);
        
        //File and scanner for reading from numbers1 to write to the first numbers stack
        File fileName = new File("Numbers1.txt");
        Scanner fileInput = new Scanner(fileName);
        
        GenericStack<Integer> stack1 = new GenericStack<>();
        
        //Adds values to the first numbers stack from numbers1 as long as there is values to be read
        while(fileInput.hasNext()){
            stack1.push(fileInput.nextInt());
        }
        
        //Displays stack1 values in console
        System.out.println("\nValues from Numbers1 and pushed onto stack1");
        System.out.println("------------------------------");
        GenericStack.printStack(stack1);
        
        //File and scanner changed to read from numbers2 to write to the second numbers stack
        fileName = new File("Numbers2.txt");
        fileInput = new Scanner(fileName);
        
        GenericStack<Integer> stack2 = new GenericStack<>();
        
        //Add all files read from numbers2 to stack2
        while(fileInput.hasNext()){
            stack2.push(fileInput.nextInt());
        }
        
        //Display all information within stack2
        System.out.println("\nValues from Numbers2 and pushed onto stack2");
        System.out.println("------------------------------");
        GenericStack.printStack(stack2);
        
        //Call combine stack method to comine stack1 and stack2 to a new stack combinedStack
        GenericStack<Integer> combinedStack = GenericStack.combineStacks(stack1, stack2);
        
        GenericStack<Integer> sortedStack = new GenericStack<>();
        
        //Call sort stack method to sort the combinedStack and places sorted values into sortedStack
        GenericStack.sortStack(combinedStack, sortedStack);
        
        //Display all information within sortedStack
        System.out.println("\nCombined Number Stack With Duplicates - lowest value on top:");
        System.out.println("------------------------------");
        GenericStack.printStack(sortedStack);
        
        //Call removeDuplicates for sortedStack that adds the stack with no duplicates to noDuplicatesStack
        GenericStack<Integer> noDuplicatesStack = GenericStack.removeDuplicates(sortedStack);
        
        //Display all information within noDuplicatesStack
        System.out.println("\nCombined Number Stack Duplicates Removed - lowest value on top:");
        System.out.println("------------------------------");
        GenericStack.printStack(noDuplicatesStack);
        
        //Change to read from file Strings1
        fileName = new File("Strings1.txt");
        fileInput = new Scanner(fileName);
        
        GenericStack<String> stringStack1 = new GenericStack<>();
        
        //Add all values from strings1 to stringStack1
        while(fileInput.hasNext()){
            stringStack1.push(fileInput.next());
        }
        
        //Display all values in stringStack1
        System.out.println("\nValues from Strings1 and pushed onto stringStack1");
        System.out.println("------------------------------");
        GenericStack.printStack(stringStack1);
        
        //Change to read from file Strings2
        fileName = new File("Strings2.txt");
        fileInput = new Scanner(fileName);
        
        GenericStack<String> stringStack2 = new GenericStack<>();
        
        //Add all values from strings2 to stringStack2
        while(fileInput.hasNext()){
            stringStack2.push(fileInput.next());
        }
        
        //Display all values in stringStack2
        System.out.println("\nValues from Strings2 and pushed onto stringStack2");
        System.out.println("------------------------------");
        GenericStack.printStack(stringStack2);
        
        //Call combineStack method for both string stacks into the combinedStringStack
        GenericStack<String> combinedStringStack = GenericStack.combineStacks(stringStack1, stringStack2);
        
        GenericStack<String> sortedStringStack = new GenericStack<>();
        
        //Call sortStack method to sort combinedStringStack and added to sortedStringStack lowest to highest
        GenericStack.sortStack(combinedStringStack, sortedStringStack);
        
        //Display the sortedStringStack
        System.out.println("\nCombined String Stack With Duplicates - lowest value on top:");
        System.out.println("------------------------------");
        GenericStack.printStack(sortedStringStack);
        
        //Call removeDuplicates method on sortedStringStack and moving those values to noDuplicatesStringStack
        GenericStack<String> noDuplicatesStringStack =GenericStack.removeDuplicates(sortedStringStack);
        
        //Display all values in noDuplicatesStringStack
        System.out.println("\nCombined String Stack With No Duplicates - lowest value on top:");
        System.out.println("------------------------------");
        GenericStack.printStack(noDuplicatesStringStack);
    }
    
    static int findNumEven (Stack<Integer> stack){
        
        //Create totalEvenValues to track the total number of values as each value is checked
        int totalEvenValues = 0;
        //Temporary stack to move values from stack parameter to as values are checked
        Stack<Integer> tempStack = new Stack<>();
        
        //While loop that continues until stack is empty
        while(stack.empty() != true){
            //If the top value of the stack is even, add 1 to totalEvenValues then push value over to the temp stack
            if((stack.peek() % 2) == 0){
               totalEvenValues++; 
            }
            tempStack.push(stack.pop());
        }
        
        //Move all values from tempStack back over to the original stack
        while(tempStack.isEmpty() != true){
            stack.push(tempStack.pop());
        }
        
        return totalEvenValues;
    }
    
    static void printStack (Stack<Integer> stack){
        
        Stack<Integer> tempStack = new Stack<>();
        
        System.out.println("Values in this stack");
        System.out.println("------------------------------");
        
        //Loop through the stack and print all values as they are popped
        while(stack.isEmpty() != true){
            tempStack.push(stack.peek());
            System.out.println(tempStack.peek());
            stack.pop();
        }
        
        //Place all values from the tempStack into the original stack
        while(tempStack.isEmpty() != true){
            stack.push(tempStack.pop());
        }
    }
}

class GenericStack<E> {

    private Stack<E> stack = new Stack<>(); 

    public boolean isEmpty(){
        return stack.isEmpty();
    }

    public int getSize(){
        return stack.size();
    }

    public E peek(){
        return stack.peek();
    }

    public E pop(){
        E o = stack.pop();
        return o;
    }

    public void push(E value){
        stack.push(value);
    }

    public static <E> void printStack (GenericStack<E> stack){

         GenericStack<E> tempStack = new GenericStack<>();

         //Loop through the stack and print values as they are moved to the tempStack
         while(stack.isEmpty() != true){
             tempStack.push(stack.peek());
             System.out.println(tempStack.peek());
             stack.pop();
         }

         //Move all values back from the tempStack to the original stack
         while(tempStack.isEmpty() != true){
             stack.push(tempStack.pop());
         }
    }
    
    public static <E> GenericStack <E> combineStacks (GenericStack<E> stack1, GenericStack<E> stack2){
        
        GenericStack<E> combinedStack = new GenericStack<>();
        
        //Loop through stack1 and add values until it is empty
        while(stack1.isEmpty() != true){
            combinedStack.push(stack1.pop());
        }
        
        //Loop through stack2 and add values until it is empty
        while(stack2.isEmpty() != true){
            combinedStack.push(stack2.pop());
        }
        
        return combinedStack;
    }
    
    public static <E extends Comparable<E>> void sortStack (GenericStack<E> unsortedStack, GenericStack<E> sortedStack){
        
        //Loop through until unsortedStack is empty at start of loop
        while(unsortedStack.isEmpty() != true){
            //Creating a highestValue variable to be the last value in the stack
            E highestValue = unsortedStack.pop();
            //Iterator to add values back to unsortedStack at the end of loop
            int i = 0;
            //Ensures unsortedStack is looped through until empty
            while(unsortedStack.isEmpty() != true){
                //Checks if next value in unsortedStack is higher or equal to highestValue and changes it if it meets condition then moves old value for highestValue over
                // to the sortedStack
                if(unsortedStack.peek().compareTo(highestValue) >= 0){
                   sortedStack.push(highestValue);
                   highestValue = unsortedStack.pop();
               }
               //If prior condition is not met, the last value in unsorted stack is added to the sorted stack
                else{
                  sortedStack.push(unsortedStack.pop());
               }
                //Iterator increases to make sure all values added to unsortedStack get moved back
                i++;
            }
            //Loop to remove all values added to the sorted stack and add them back to the unsorted stack to ensure they are checked against the value at the beginning of next loop
            while(i > 0){
                   unsortedStack.push(sortedStack.pop());
                   i--;
               }
            //Adds the actual highest value in this loop to the sortedStack
            sortedStack.push(highestValue);
        }
    }
    
    public static <E extends Comparable<E>> GenericStack<E> removeDuplicates(GenericStack<E> stack){
       
        GenericStack<E> stackNoDuplicates = new GenericStack<>();
        GenericStack<E> stackDuplicates = new GenericStack<>();
        
        while(stack.isEmpty() != true){
           E comparingValue = stack.pop();
           int i = 0;
           while(stack.isEmpty() != true){
               if(stack.peek().compareTo(comparingValue) == 0){
                   stackDuplicates.push(stack.pop());
               }
               else{
                   stackNoDuplicates.push(stack.pop());
                   i++;
               }
           }
           while(i > 0){
                   stack.push(stackNoDuplicates.pop());
                   i--;
               }
           stackNoDuplicates.push(comparingValue);
        }
        
        while(stackNoDuplicates.isEmpty() != true){
            stack.push(stackNoDuplicates.pop());
        }
        
        System.out.println("\nDuplicates Stack - largest value on top:");
        System.out.println("------------------------------");
        GenericStack.printStack(stackDuplicates);
        
        return stack;
    }
}