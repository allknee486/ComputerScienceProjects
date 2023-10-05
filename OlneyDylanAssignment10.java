/**
 * Dylan Olney
 * CS1450 M/W
 * Due Aug 4
 * Assignment 10
 * This program will use a binary tree to convey a secret message from parrot objects added to the tree 
 * A level-order traversal of the tree will reveal the message once the tree is complete
 */

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.lang.Comparable;

public class OlneyDylanAssignment10 {
    
    public static void main(String[] args) throws IOException {
        
        File fileName = new File("parrots.txt");
        Scanner fileInput = new Scanner(fileName);
        
        //Construct BinarySearchTree for adding parrots to
        BinarySearchTree mySearchTree = new BinarySearchTree();
        
        //Loop through file as long as a parrot can be added from the file to the tree
        while(fileInput.hasNext()){
            Parrot tempParrot = new Parrot(fileInput.nextInt(), fileInput.next(), fileInput.next());
            mySearchTree.insert(tempParrot);
        }
        
        System.out.println("Parrot's Song");
        System.out.println("-------------------------------");
        mySearchTree.levelOrder();
        System.out.println();
        
        System.out.println("Parrot's on Leaf Nodes");
        System.out.println("-------------------------------");
        mySearchTree.visitLeaves();
    }

}

class Parrot implements Comparable<Parrot>{
    
    private int id;
    private String name;
    private String songWord;
    
    //Constructor
    public Parrot(int id, String name, String songWord){
        this.id = id;
        this.name = name;
        this.songWord = songWord;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String sing(){
        return this.songWord;
    }
    
    @Override
    public int compareTo(Parrot compareParrot){
        if(this.id > compareParrot.id){
            return 1;
        }
        else if(this.id < compareParrot.id){
            return -1;
        }
        else{
            return 0;
        }
    }
}

class BinarySearchTree{
    
    private TreeNode root;
    
    public BinarySearchTree(){
        root = null;
    }
    
    public boolean insert(Parrot parrotToAdd){
        
        //If the first node in the tree, add the parrot as the root
        if(root == null){
            root = new TreeNode(parrotToAdd);
        }
        //Adds parent as a leaf to the tree once an empty node is found
        else{
            TreeNode current = root;
            TreeNode parent = null;
            
            while(current != null){
                //Checks parrot id to current node and goes to left if less than
                if(parrotToAdd.compareTo(current.data) < 0){
                    parent = current;
                    current = current.left;
                }
                //Checks parrot id to current node and goes to right if greater han
                else if(parrotToAdd.compareTo(current.data) > 0){
                    parent = current;
                    current = current.right;
                }
                //If a duplicate value is found, returns false as a tree cannot have duplicate values
                else{
                    return false;
                }
            }
            
            //Adds parrot to left if less than parent node
            if(parrotToAdd.compareTo(parent.data) < 0){
                parent.left = new TreeNode(parrotToAdd);
            }
            //Adds parrot to right if greater than parent node
            else{
                parent.right = new TreeNode(parrotToAdd);
            }
        }
        return true;
    }
    
    public void levelOrder(){
       
        //Checks that tree has a root
        if(root != null){
            //Temporary queue to add parrots to
            Queue<TreeNode> tempQueue = new LinkedList<>();
            tempQueue.add(root);
            
            //Loop continues as long as the tempQueue has something to output
            while(!tempQueue.isEmpty()){
                TreeNode tempNode = tempQueue.remove();
                System.out.print(tempNode.data.sing() + " ");
                
                //Adds from left leaf to the queue 
                if(tempNode.left != null){
                    tempQueue.add(tempNode.left);
                }
                //Adds from right leaf to the queue
                if(tempNode.right != null){
                    tempQueue.add(tempNode.right);
                }
            }   
        }
    }
    
    //Calls private method
    public void visitLeaves(){
        visitLeaves(root);
    }
    
    private void visitLeaves(TreeNode aNode){
        
        //Prints out the current provided node
        System.out.println(aNode.data.getName());
        
        //Checks that method is not using a null node
        if(aNode != null){
            
            //Moves to the left node if it contains a parrot
            if(aNode.left != null){
                visitLeaves(aNode.left);
            }
            //Moves to the right node if it contains a parrot
            if(aNode.right != null){
                visitLeaves(aNode.right);
            }
        }
    }
    
    private class TreeNode{
        
        private Parrot data;
        private TreeNode left;
        private TreeNode right;
        
        //Constructor for nodes that use a parrot object
        TreeNode(Parrot data){
            this.data = data;
            left = null;
            right = null;
        }
    }
}