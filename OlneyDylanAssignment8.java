/**
 * Dylan Olney
 * CS1450 M/W
 * Due July 27
 * Assignment 8
 * This program will decrypt a secret message that has been split into 3 parts. 
 * 3 ArrayLists will be used for placing each part of the message into and decoded within the ArrayList
 * A decrypter class will use the key to translate the message based on what the message has.
 */

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class OlneyDylanAssignment8{
    
    public static void main(String[] args) throws IOException {
        
        File fileName = new File("ListMessage1.txt");
        Scanner fileInput = new Scanner(fileName);
        
        ArrayList<Character> listMessage1 = new ArrayList<>();
        String message1 = fileInput.next();
        
        //Loops through 1st message string and adds each character to the ArrayList
        for(int i = 0; i < message1.length(); i++){
            listMessage1.add(message1.charAt(i));
        }
        
        fileName = new File("ListMessage2.txt");
        fileInput = new Scanner(fileName);
        
        int numMsg2Values = fileInput.nextInt();
        ArrayList<Integer> listMessage2 = new ArrayList<>();
        
        //Adds each int in the 2nd message to the 2nd ArrayList
        while(fileInput.hasNext()){
           listMessage2.add(fileInput.nextInt());
        }
        
        fileName = new File("ListKey.txt");
        fileInput = new Scanner(fileName);
        
        int numKeyValues = fileInput.nextInt();
        ArrayList<Integer> listKey = new ArrayList<>();
        
        //Adds each int in the 3rd message to the 3rd ArrayList
        while(fileInput.hasNext()){
            listKey.add(fileInput.nextInt());
        }
        
        //Create iterators for all the first message ArrayLists to use in decoder
        Iterator<Character> msg1Iterator = listMessage1.iterator();
        Iterator<Integer> msg2Iterator = listMessage2.iterator();
        Iterator<Integer> keyIterator = listKey.iterator();
        
        //Creating decrypter object to run each iterator through
        Decrypter myDecrypter = new Decrypter();
        
        //Display results of decrypting first message
        System.out.println("The first secret message: ");
        System.out.println(myDecrypter.unscramble(keyIterator, msg1Iterator, msg2Iterator));
        
        fileName = new File("QueueMessage1.txt");
        fileInput = new Scanner(fileName);
        
        //Creating a queue for the first message and string to hold what is read from file
        Queue<Character> queueMessage1 = new LinkedList<>();
        String queueMessage = fileInput.next();
        
        //Seperates the string from the first file into individual chars to add to queue
        for(int i = 0; i < queueMessage.length(); i++){
            queueMessage1.add(queueMessage.charAt(i));
        }
        
        fileName = new File("QueueMessage2.txt");
        fileInput = new Scanner(fileName);
        
        //Creating a 2nd queue for the 2nd message
        int numQueueMsgValues = fileInput.nextInt();
        Queue<Integer> queueMessage2 = new LinkedList<>();
        
        //Add all ints from the 2nd message to the queue
        while(fileInput.hasNext()){
            queueMessage2.add(fileInput.nextInt());
        }
        
        fileName = new File("QueueKey.txt");
        fileInput = new Scanner(fileName);
        
        //Creating a queue for the key
        int numQueueKeyValues = fileInput.nextInt();
        Queue<Integer> queueKey = new LinkedList<>();
        
        //Add all values from the queueKey file to the queue
        while(fileInput.hasNext()){
            queueKey.add(fileInput.nextInt());
        }
        
        //Create iterators for the 2nd message queues to use in decoder
        msg1Iterator = queueMessage1.iterator();
        msg2Iterator = queueMessage2.iterator();
        keyIterator = queueKey.iterator();
        
        //Display results of the 2nd decrypter
        System.out.println("The second secret message: ");
        System.out.println(myDecrypter.unscramble(keyIterator, msg1Iterator, msg2Iterator));
    }
}

class Decrypter{
    
    private MessageStack stack;
    
    //Constructor
    public Decrypter(){
        stack = new MessageStack();
    }
    
    //Uses iterators for collections to translate message
    public String unscramble(Iterator<Integer>keyIterator, Iterator<Character>msg1Iterator, Iterator<Integer>msg2Iterator){
       
        //Loops until the key has been completely gone through
        while(keyIterator.hasNext()){
           //Case for key having a 0 and pulling the value from the 1st message
           if(keyIterator.next().equals(0)){
               stack.push(msg1Iterator.next());
           }
           //Case for alternatives that pulls from the 2nd message
           else{
               int tempInt = msg2Iterator.next();
               char tempChar = (char)tempInt;
               stack.push(tempChar);
           }
       }
       String tempString = new String();
       //Adds all characters from the stack to a string until the stack empties
       while(!stack.isEmpty()){
           tempString += stack.pop();
       }
       return tempString;
    }
}

class MessageStack{
    
    private Stack aStack;
    
    //Constructor
    public MessageStack(){
        aStack = new Stack();
    }
    
    public boolean isEmpty(){
        return aStack.isEmpty();
    }
    
    public int size(){
        return aStack.size();
    }
    
    public void push(Character value){
        aStack.add(value);
    }
    
    public char pop(){
        return (char) aStack.pop();
    }
}