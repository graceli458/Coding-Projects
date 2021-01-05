README.txt
Name: Grace Li
UNI: gl2676
Assignment: Credit Card class

===============================================================================
Credit Card
===============================================================================

I created 6 private methods within my Credit Card class that tested each of the 
conditions given to us. When the check method is called, each individual check 
is called within the check method body. Also in the check method body, the value 
of the error code is also determined. If the boolean valid, evaluates to false,
the error code is updated and the check method is exited. This is a way that 
makes sure the error code outputted to the user corresponds to the test that the
credit card failed. I used the Character.getNumericValue method to convert a char
to an int. I used the charAt method to access the character at a specific
location in the string. I used parseInt and valueOf methods previously, but
after going to a TA's office hours, Ithought it would yield less errors if I
used Character.getNumericValue instead. I used the oracle java documentation to
check to see that I was calling the method correctly. I also used the substring
method when I needed to evaluate a series of characters, and the parseInt method
for when I needed to combine the two digits into a single int value. I used for
loop for when I needed to find the sum from a series of numbers to streamline
the code. 

I used three instance variable and declared the local variables for each check 
method. There are three public methods--are two accessor methods and one mutator
method: isValid, getErrorCode and check. 

To test my code, I started with inputting 0000-0000-0000 to see both whether it
would fail and to see what error code would be outputted.From there I would 
change the input each time to satisfy the next condition. So after 0000-
0000-0000, I inputted 4000-0000-0000 to see if the first condition would be 
satisfied and whether the correct error code would be outputted: error code 2.

