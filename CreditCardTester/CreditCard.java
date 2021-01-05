//Name: Grace Li
//UNI: gl2676
//validating a credit card number class

public class CreditCard{
    //declare instance variables
    private int errorCode;
    private boolean valid;
    private String number;
    
    //constructor
    public CreditCard(String number){
        //initalize instance variables
        errorCode = 0;
        valid = true;
        this.number = number;
    }
    
    //checks to see if the first digit is a 4
    private void check1(){
        //gets the first character in the string
        //converts to an int, and stores in variable
        int firstNum = Character.getNumericValue(number.charAt(0));
        //if the first character isn't 4, change valid to false
        if (firstNum != 4){
            valid = false;
        }
    }
    
    //checks whether 4th number = 5th number +1
    private void check2(){
        //initalize and declare ints with the converted characters
        //at the 4th and 5th place in the string
        int fourthNum = Character.getNumericValue(number.charAt(3));
        int fifthNum = Character.getNumericValue(number.charAt(5));
        
        //if the 4th number doesn't = the 5th number plus one
        //change valid to false
        if (fourthNum != fifthNum +1){
            valid = false;
        } 
    }
    
    //checks to see if the product of the 1st, 5th, and 9th digits
    //are 24
    private void check3(){
        //intialize and declare ints with the converted characters
        //at the 1st, 5th, and 9th place in the string
        int firstNum = Character.getNumericValue(number.charAt(0));
        int fifthNum = Character.getNumericValue(number.charAt(5));
        int ninthNum = Character.getNumericValue(number.charAt(10));
        
        //if the product of the digits doesn't equal 24
        //change valid to false
        if (firstNum*fifthNum*ninthNum != 24){
            valid = false;
        }
    }
    
    //checks to see if the sume of all the digits are evenly divisble by 4
    private void check4(){
        //declare and initalize a new string variable that stores the sting
        //without dashes
        String numWithoutDash = number.replace('-', '0');
        
        //declare and initalize a variable that stores the total
        int total = 0;
        //a for loop that gets the value of each digit 
        //for each place in the string
        for(int i=0; i<=13; i++){
            //converts and stores the character at place i as an integer 
            int currentDigit;
            currentDigit = Character.getNumericValue(numWithoutDash.charAt(i));
            //adds the int to the total
            total = total + currentDigit;
        }
        
        //if the total is not evenly divisible by 4, change valid to false
        if (total%4 != 0){
            valid = false;
        } 
    }
    
    //check to see if the sum of the first four digits is one less than 
    //the sum of the last four digits
    private void check5(){
        //store the first four and last four digits as two new strings
        String firstFour = number.substring(0,4);
        String lastFour = number.substring(10, 14);
        
        //declare and initalize two ints that store the total sum 
        //for each of the two strings
        int totalOne = 0;
        int totalTwo = 0;
        //the for loop evaluates each of the character's value at place i
        for (int i=0; i<4; i++){
            //converts and stores the value of the character in an int
            int first = Character.getNumericValue(firstFour.charAt(i));
            int second = Character.getNumericValue(lastFour.charAt(i));
            
            //add the value to the total
            totalOne = totalOne + first;
            totalTwo = totalTwo + second;
        }
        
        //if the value of the second string is not one greater
        //than the first change valid to false
        if(totalOne + 1 != totalTwo){
            valid = false;
        }
    }
    
    //check to see whether the sum of first two digits, as a two digit number
    //and the seventh and eigth digit, as a two digit number is 100
    private void check6(){
        //stores the first two digits as a single int
        //stores the seventh and eight digits as a single int
        int firstTwo = Integer.parseInt(number.substring(0,2));
        int seventhEighth = Integer.parseInt(number.substring(7,9));
        
        //if the sum of the first two and the seventh and eighth values
        //do not equal 100 change valid to false
        if(firstTwo+seventhEighth != 100){
            valid = false;
        }
    }
    
    //the check method
    public void check(){
        //calls the check1 method
        check1();
        //if valid is false, the error code is 1 and exits the check method
        if(valid == false){
            errorCode = 1;
            return;
        }  
        //calls the check2 method
        check2();
        //if valid is false, the error code is 2 and exists the check method
        if(valid == false){
            errorCode = 2;
            return;
        }
        //calls the check3 method
        check3();
        //if valid is false, the error code is 3 and exists the check method
        if(valid == false){
            errorCode = 3;
            return;
        }
        //calls the check4 method
        check4();
        //if valid is false, the error code is 4 and exists the check method
        if(valid == false){
            errorCode = 4;
            return;
        }
        //calls the check5 method
        check5();
        //if valid is false, the error code is 5 and exists the check method
        if(valid == false){
            errorCode = 5;
            return;
        }
        //calls the check6 method
        check6();   
        //if valid is false, the error code is 6 and exists the check method
        if(valid == false){
            errorCode = 6;
            return;
        }   
    }
    
    //returns the error code
    public int getErrorCode(){
        return errorCode;
    }
    
    //returns the value of valid
    public boolean isValid(){
        return valid;
    }
}