//*************************************
//
//  WordTest.java
//
//  Test class for WordLists.java
//  Programming Project 5, COMS W1004
//
//
//  Your Name: Grace Li
//  Your Uni: gl2676
//**************************************

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;


public class WordTest{

    public static void main (String[] args){
        try{
            //checks whether command line arguments are inputed
            if(args[0]!=null){
                
                //makes a file with user input
                File userInputedFile = new File(args[0]);
                
                if(userInputedFile.exists()){
                    
                    if(args[0].equals("dictionary.txt")){
                        
                        WordLists myList = new WordLists(args[0]);
                        System.out.println(myList.getUserAction());
                        myList.matchUserAction(myList.processUserInput());
                    }
                    else{
                        throw new FileNotFoundException();
                    }
                }
                throw new FileNotFoundException(); 
            }
             
        }catch(InvalidCharInputException e){
            System.out.println("Please enter a valid lowercase character.");
        }
        catch(InvalidIntInputException e){
            System.out.println("Please enter a valid integer number.");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Please copy and paste the following line into "+
                               "the command line to run the program: \n" +
                              "java WordTest dictionary.txt");
        }
        catch(FileNotFoundException e){
            System.out.println("Please make a \"dictionary.txt\" "+
                               "file with all the words that you"+
                               " wish to search."); 
        }
        catch(Exception e){
            System.out.println("Something went wrong please try again.");
        }
    }
} // end of class








