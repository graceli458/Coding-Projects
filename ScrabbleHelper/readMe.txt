README.txt
Name: Grace Li
UNI: gl2676
Assignment: WordTest / Scrabble

To preface: I hope I showed that I know how to handle exceptions. I know that I
could have implemented my code in a different way that could have more 
explicitly thrown exceptions, so I'll talk about the alternative methods that I
could have used to illustrate that I understand how to handle exceptions. 

I added places where I could have thrown an exception instead of 
continually prompting the user for input--seen on line 272,303,335--where I put 
throw new InvalidIntInputException and InvalidCharInputException. But since 
throwing an exception breaks out of that class, I wanted to continually 
prompt the user until the correct input is entered, so I used a while loop 
instead. I created two subclass of the IllegalArgumentException() class with a
InvalidIntInputException and InvalidCharInputException subclass that extend the
parent class. I created two new subclasses to differentitate between what type 
of input error the user had and so the user can more easily see where they went
wrong. 

===============================================================================
WordLists
===============================================================================

I had declared an instance variable called myList to store the list of 
dictionary entries as an ArrayList of Strings. I instatiated the list in the 
constructor by taking in a fileName and then creating a file Object from the 
parameter. I used a Scanner object to read the contents of the file by using
the hasNext() method to ensure that each entry was being copied into the myList
arrayList by using the add(next()). Because I am working with file objects, I 
need to ensure that if the file was accidentally deleted that I catch the 
exception, so I uses the throws keyword to pass the exception onto the main 
method. 

lengthN():
This method takes in an int--which illustrates the length of words that needs 
to be found. By using an advanced for loop, I iterate through all the elements
in myList and use an if statement to determine whether the current entry has 
length n. If it does, I add the word to a new ArrayList of String--which I 
return at the end of the method. 

endsWith();
This method take in a char and an int--the char representing the last character
in the word and the int representing the length of the word. First, I call the 
lengthN() method above to create a preliminary list of words with length n. 
Then, by using a specializaed for loop, I iterate through each element in the 
arraylist to see whether the character at the last index (n-1) where n is the 
length of the word matches with the specified character. Then I add each match
to an ArrayList of Strings which I return.

containsLetter();
Like endsWith(), this method calls upon lengthN() to create a filteredList, to 
help narrow down the number of words that qualify. Then with a specialized for 
loop, if the charAt(specific index) matches with the specified char, the word
is added to a new ArrayList of Strings--at the end the ArrayList is returned.

multiLetter();
This method uses a nested for loop and a counter to count the instances of a 
certain character in a word. The specialized loop iterates through the entire 
list while the inner for loop iterates through each letter in the word. If the
char at a certain index matches the target char then the counter is incremented
by one. After the all the letters in the word are evaluated then if, the value 
of the counter equals the target occurances of the letter, the word is added to 
an ArrayList of Strings. After the ArrayList is returned.

When the program is run, a series of messages is printed to the user, detailing 
the types of actions that they can execute. To execute an action they need to 
enter the corresponding number to the action--1,2,3 or 4. To ensure proper user 
input, the processUserInput() method is called. While the userInput is invalid,
the user is continually prompted to enter a correct input. Within the while loop
is an if statement that calls upon the evaluateUserChoice() method which matches
the String of the userInput to match with either "1", "2", "3", or "4". If none 
match the evaluateUserChoice() returns false. This process is repeated until the 
user enters a correct input. 

The processUserInput() method returns an int--which represents the method that 
the user wants to take. The matchUserAction() method is called, which matches 
the action of the user with the proper methods by using a switch statement. The 
set up for each check() method is similar--each calling certain messages to be 
printed to the user, then continually prompting the user until the user inputs 
a valid input, before moving onto the next method. Each method then calls the 
respective actions such as lengthN, multiLetter etc with the correct parameters.
Then the createOutputFile() method is called and a new file with the list of 
words is printed. 

createOutputFile():
Takes in an ArrayList of String and a string for the file name, and creates a
new File object with parameter. Then it instatiates a new PrintWriter Object to 
write the contents of the ArrayList into the output file by using a specialized 
for loop which iterates through all elements in the ArrayList.

To ensure correct userInput the following methods are called:

checkCharInput():
Scans the user's input into a string and uses the contains() method to check 
whether the user input matches with the elements in an Array of Strings 
called alpha. If the userInput matches then, it breaks out of the while loop
and the method returns the char that the user inputted, otherwise the user is 
continually prompted for a valid input until one is give.

checkIntInput():
Scans the user's input into a string and uses the contains() method to check 
whether the user's input can be found within the Arraylist of possible valid
inputs. The number of possible inputs ranges from 1-29: 29 being the longest 
word in the dictonary.txt file. If the userInput is not found within the 
ArrayList of possible inputs, then the user is continually prompted for a new 
input. The method returns the valid int input.

checkIndexInput():
This method functions very similarly to the methods above. It scans user input,
and checks to see whether the input is valid. In this method, the ArrayList 
that is called in checkIntInput is called and a 0 is added--because indexing 
begins at 0, where words cannot have a 0 length. This method also checks to see
whether the index is the same length or greater the the length of the word--
indexing stops at n-1. 


===============================================================================
WordTest
===============================================================================

First, depending on whether the user enters a dictionary.txt file in the 
command line, an exception will be thrown. Additionally, even if the user 
enters dictionary.txt in the command line, the program first checks that there 
is infact a dictionary.txt file available, this ensures that the dictionary.txt 
exists. Then the dictionary.txt file is used to create a new WordList object. 
The getUserAction()and processUserInput() methods of the WordList class are 
called to evaluate the action that the user wants to take. 

I used the try/catch method to catch exceptions. I used the hierarchical 
structure when ordering the exceptions to ensure that it went from specific
to general, this way the user is able to clearly understand what went wrong and 
try again. 

I also used the exists() method to ensure that the user inputted file actually 
exists--this would account for when the dictionary.txt file is deleted, but the 
user still runs java WordTest dictionary.txt