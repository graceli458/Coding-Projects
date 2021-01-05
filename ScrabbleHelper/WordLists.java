//*************************************
//
//  WordLists.java
//
//  Class to aid with Scrabble
//  Programming Project 5, COMS W1004
//
//
//  Your Name: Grace Li
//  Your Uni: gl2676
//**************************************

import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Number;
import java.util.Scanner; 
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;


public class WordLists{
    
    private ArrayList<String> myList;
    
    //constructor: scans file and creates an arraylist of the items in the file
    public WordLists(String fileName) throws FileNotFoundException{

            File inputFile = new File(fileName);
            Scanner input = new Scanner(inputFile);
            myList = new ArrayList<String>();
            
            while(input.hasNext()){
                myList.add(input.next());
            }
            input.close();
            
    }
    
    //returns a new ArrayList of words with length n
    public ArrayList<String> lengthN(int n){
        ArrayList<String> nLengthWords = new ArrayList<String>();
        for(String word: myList){
            
            if(word.length()==n){
                nLengthWords.add(word);
            }
        }
        return nLengthWords;
    }

    //returns a new ArrayList of words with n length and ending with char c
    public ArrayList<String> endsWith(char lastLetter, int n){

        ArrayList<String> filteredList = lengthN(n);
        ArrayList<String> endWithList = new ArrayList<String>();
        
        for(String word: filteredList){
            
            if(word.charAt(n-1)==lastLetter){
                endWithList.add(word);
            }
        }
        return endWithList;
    }

    //returns a new ArrayList with n length words, with char c at index i
    public ArrayList<String> containsLetter(char included, int index, int n){

        ArrayList<String> filteredList = lengthN(n);
        ArrayList<String> containsLetterList = new ArrayList<String>();
        
        for(String word: filteredList){
            
            if(word.charAt(index)==included){
                containsLetterList.add(word);
            }
        }
        return containsLetterList;
    }
 
    //returns a new Arraylist that has m occurances of char c
    public ArrayList<String> multiLetter(int m, char included){

        int counter = 0;
        ArrayList<String> multiLetterList = new ArrayList<String>();
        
        for(String word: myList){
            
            for(int i=0; i<word.length(); i++){
                
                if(word.charAt(i)==included){
                    counter++;
                }
            }
            
            if(counter==m){
                multiLetterList.add(word);
            }
            counter=0;
        }
        return multiLetterList;
    }
    
    //returns a string of messages to the user
    public String getUserAction(){
       String output = "What would you like to search for? \n" +
                      "1. Find words of length n. \n" +
                      "2. Find words end with char c and have length n. \n" +
                      "3. Find words of length n with char c " +
                      " at a certain index i.\n"+
                      "4. Find words with m occurances of char c.\n";
      return output;
    }
    
    //scans and returns user input, continually prompts user for proper input 
    public int processUserInput(){
        
        boolean invalidInput = true;
        int userChoice = -5;
        Scanner input = new Scanner(System.in);

        while(invalidInput){
            System.out.println("Please input the corresponding " +
            "number to the action you want to take.");
            
            String userInput = input.nextLine();
            
            if(evaluateUserChoice(userInput)){
                userChoice = Integer.valueOf(userInput);
                invalidInput = false;
            }
        }
        return userChoice;
    }
    
    //evaluates whether userInput is acceptable and returns a boolean
    private boolean evaluateUserChoice(String input){
        boolean isValid = false;
        switch (input){
            case "1":
                isValid = true;
                break;
                
            case "2":
                isValid = true;
                break;
                
            case "3":
                isValid = true;
                break;
                
            case "4":
                isValid = true;
                break;
                
            default: 
                System.out.print("Please enter a valid number 1-4.\n");
                isValid = false;
        }
        return isValid;
    }
    
    //matches userInput with the correct action that needs to be taken
    public void matchUserAction(int input)throws FileNotFoundException{
        switch(input){
            case 1:
                check1();
                break;
                
            case 2:
                check2();
                break;
                
            case 3:
                check3();
                break;
                
            default:
                check4();
                break;  
        }
    }
    
    //executes all actions associated with the first action:
    //find words with length n
    private void check1()throws FileNotFoundException{
        int localInt = 0;
        String outputFile = "";
        
        System.out.println(printMessage1());
        localInt = checkIntInput();
        outputFile = "WordsOf_" + Integer.toString(localInt)+"_length.txt";
        createOutputFile(lengthN(localInt), outputFile);
        System.out.println("The list of words can be viewed at "+ outputFile);
    }
    
    //executes all actions associated with the second action:
    //words of length n and ending with char c
    private void check2()throws FileNotFoundException{
        char localChar = 'a';
        int localInt = 0;
        String outputFile = "";
        
        System.out.println(printMessage2pt1());
        localChar = checkCharInput();
        System.out.println(printMessage2pt2());
        localInt = checkIntInput();
        outputFile = "WordsEndingIn_"+localChar+"_WordsOfLength"+ 
                      Integer.toString(localInt);
        createOutputFile(endsWith(localChar, localInt), outputFile);
        System.out.println("The list of words can be viewed at "+ outputFile);
    }
    
    //executes all actions associated with the third action:
    //words with char c at index i with length n 
    private void check3()throws FileNotFoundException{
        char localChar = 'a';
        int localInt = 0;
        int localIndex = 0;
        String outputFile = "";
        
        System.out.println(printMessage3pt1());
        localChar = checkCharInput();
        System.out.println(printMessage3pt3());
        localInt = checkIntInput();
        System.out.println(printMessage3pt2());
        localIndex = checkIndexInput(localInt);
        outputFile = "WordsWith_"+localChar+"_AtIndexOf_"+localIndex+
                     "_WithWordsOfLength_"+Integer.toString(localInt);
        createOutputFile(containsLetter(localChar, localIndex, localInt), 
                         outputFile);
        System.out.println("The list of words can be viewed at "+ outputFile);
    }
    
    //executes all actions associated with the fourth action:
    //words with m occurances of char c
    private void check4()throws FileNotFoundException{
        char localChar = 'a';
        int localInt = 0;
        String outputFile = "";
        
        System.out.println(printMessage4pt1());
        localInt = checkIntInput();
        System.out.println(printMessage4pt2());
        localChar = checkCharInput();
        outputFile = "WordsWith_"+localInt+"_OccurancesOfTheLetter_"
                    + localChar;
        createOutputFile(multiLetter(localInt, localChar), outputFile);
        System.out.println("The list of words can be viewed at " + outputFile);
    }
    
    //checks whether the user input is valid by comparing to alphabet array
    private char checkCharInput(){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        char userChar = 'a';
        String[] alpha = getAlphabet();
        
        while(invalidInput){
            String userInput = input.nextLine();
            
            if(Arrays.asList(alpha).contains(userInput)){
                invalidInput = false;
                userChar = userInput.charAt(0);
            }
            else{
                System.out.println("Please enter a valid lowercase character.");
//                 throw new InvalidCharInputException();
            }
        }
        return userChar;
    }
    
    //creates and returns an array of lowercase letters
    private String[] getAlphabet(){
        String[] alphabet = new String[]{
        "a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q",
        "r","s","t","u","v","w","x","y","z"};
        
        return alphabet;
    }
    
    //checks user input for word length, must be between 1-29(max word length)
    private int checkIntInput(){
        Scanner input = new Scanner(System.in);
        boolean invalidInput = true;
        int wordLength = 0;
        ArrayList<String> lengthOfWords = getLengthsOfWords();
        
        while(invalidInput){
            String userInput = input.nextLine();
            
            if(lengthOfWords.contains(userInput)){
                invalidInput = false;
                 wordLength = Integer.valueOf(userInput);
            }
            else{
                System.out.println("Please enter a number between 1 and 29.");
//                 throw new InvalidCharInputException();
            }
        }
        return wordLength;
    }
    
    //checks whether the user inputed index is valid--must be less than 
    //word length (max index would be n-1)
    private int checkIndexInput(int lengthOfWord){
        boolean invalidInput = true;
        Scanner input = new Scanner(System.in);
        ArrayList<String> lengthOfWords = getLengthsOfWords();
        lengthOfWords.add("0");
        int validInput = 0;
        
        while(invalidInput){
            String userInput = input.nextLine();
            
            if(lengthOfWords.contains(userInput)){
                validInput = Integer.valueOf(userInput);
                
                if(validInput<lengthOfWord){
                    invalidInput = false;
                }
                else{
                System.out.println("Please enter a number that is less "+
                                   "than the length of word. Remember that "+
                                   "Indexing starts from 0 to n-1.");
                }
            }
            else{
                System.out.println("Please enter a valid integer value.");
//                 throw new InvalidCharInputException();
            }
        }
        return validInput;
    }
    
    //creates and returns an arrayList of all possible word lengths 1-29
    private ArrayList<String> getLengthsOfWords(){
        ArrayList<String> rangeOfLengths = new ArrayList<String>();
        
        for(int i=1; i<30; i++){
            String num = Integer.toString(i);
            rangeOfLengths.add(num);
        }
        return rangeOfLengths;
    }
    
    //returns a string with action 1 message
    private String printMessage1(){
        String message1 = "Enter:\n1) the length of words you would"+
            " like to find:";
        
        return message1;
    }
    
    //returns a string with action 2 message pt.1
    private String printMessage2pt1(){
        String message2 = "Enter:\n1)the last char of each word \n" +
            "Note: UpperCase Letters are not accepted.";
        return message2;
    }
    
    //returns a string with action 2 message pt.2
    private String printMessage2pt2(){
        String message2 = "\n2)the length of words you would like to find";
        
        return message2;
    }
    
    //returns a string with action 3 message pt.1
    private String printMessage3pt1(){
        String message3 = "Enter:\n1)the char you would like to be included" +
            "\nNote: UpperCase Letters are not accepted.";
        return message3;
    }
    
    //returns a string with action 3 message pt.2
    private String printMessage3pt2(){
        String message3 = 
            "3)the index at which you would like that char to appear";
        return message3;
    }
    
    //returns a string with action 3 message pt.3
    private String printMessage3pt3(){
        String message3 = "2)the length of the words you would like to find";
        
        return message3;
    }
    
    //returns a string with action 4 message pt.1
    private String printMessage4pt1(){
        String message4 = "Enter:\n1) the number of occurances of a letter";
            
        return message4;
    }
    //returns a string with action 4 message pt.2
    private String printMessage4pt2(){
        String message4 = "2) the specific char you want to find repeated"+
            "\nNote: UpperCase Letters are not accepted.";
        
        return message4;
    }
    
    //creates an output file with the contents of an arrayList 
    //with specified name
    private void createOutputFile(ArrayList<String> list, String name) 
        throws FileNotFoundException{
        
        PrintWriter output = new PrintWriter(new File(name));
        
        for(String word: list){
            output.println(word);
        }
        output.close();
    }

} // end of class








