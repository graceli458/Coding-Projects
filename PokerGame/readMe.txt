README.txt
Name: Grace Li 
UNI: gl2676
Assignment: Poker Project

There was a "hack-a-thon" esque study group, where we talked about the project.
I attend the session for a day and was in dicussion with the following people:
Yuvia Leon, Arthi Krisha, Dawson Franz, Anna Nuttle, Jennifer Wang, and Youhan
Zhang.

===============================================================================
Game Class
===============================================================================

The Game class has two different constructors, one takes in a command line 
argument--for testing purposes--and the other allows the user to play an 
interactive game. The constructor that takes in a command line argument that is 
stored in a string. The constructor creates a new Player object as well as a new
Deck object. To read the command line input, a for loop is used to iterate 
through all the substrings in the string. Because the substrings are separated
by white space, I use testHand[i] to access each substring. Then because the 
first character denotes the suit, if store the character at index 0 of the 
substring and pass it into the checkTestHandSuit() method that assigns the 
character input to a integer value--to allow it to be passed into a Card. 
checkTestHandSuit() uses a switch statment to evalue the character that the user
inputted and assigns it to an integer value once a match is made. Then to
evaluate the rank, first I determined whether the length of the substring is 
less than 3. If it is less than 3 than the substring contains a one digit rank,
otherwise it contains a two digit rank. For the one digit rank, the 
getNumericValue() method converts the character at index 1 of the substring into
an integer. If the input is a two digit number, then I take the substring of 
index 1 to 3 (not including 3), and use the valueOf() method to convert the 
substring into an integer. Then I use the suit and rank integer values and pass
it into a Card to create a new Card Object which is then added to the Hand by 
using the addCard() method. 

The other constructor for an interactive game, just initalizes a new Player 
object and a new Deck object.object

The play() method allows for both types of games to execute. Depending on the
size of the hand. If the hand size is 0, that means the user wants to play the
interactive version of the game, because no cards have been dealt and added to 
the hand. Otherwise, the tester game is played. 

The interactive version of the game calls upon the betting() method, which 
prints a message to the user asking them for an input of between 1-5, 
illustrating the number of tokens the user wants to bet. The user is continually
prompted for a valid input. Then the shuffle() method is called. Then the 
dealAndStore() method is called, which deals the user 5 cards and stores them 
into the player's hand. The player's hand is then printed to the user with the 
printHand() method. The exchangeCard() is called, which first prints a series of
messages to the user asking them to input a list of the cards they want to 
exchange, using spaces to separate each card they want to exchange. The user's 
input is scanned and stored in a string which is them evaluated using a for 
loop. The for loop iterates through the length of the string, by two. The loop
iterates by two because the empty space separating the user's input needs to be
accounted for. The index of the for loop is used to get the char at that 
index. Then the char is converted into an integer, -1 is substracted from that 
value to account for the indexing of ArrayLists starting at 0. A new card is 
dealt, then the replaceCard() method in Player is called to exchange the cards.
The evaluateExchanges() method is used to ensure that the user isn't exchanging
the same card multiple time. The user is continually prompted for a valid input. 
The user's new hand is printed, then the checkHand() method is called.The

The checkHand() method takes in an ArrayList as a parameter. It starts by 
declaring and initalizing an empty String. Then the hand is sorted using the 
Collections.sort() method. Then a series of if/else statements are used to 
determine the type of hand the user has by calling methods like checkDouble(), 
checkTwoPair(), checkThreeOfAKind(), checkStraight(); checkFullHouse(), 
checkFourOfAKind(), checkStraightFlush(), checkRoyalFlush() which returns 
booleans. From least likely to most likey (Royal Flush to High Card), then 
depending on which if statement is triggered first, the winnings are updated 
according to the payout of the hand they held. 

checkRoyalFlush(): the rank and suit of the first card is stored at the 
beginning. Immediately, if the firstRank is not an Ace, it cannot be a Royal 
Flush. Then to ensure that the other ranks are 10, 11, 12, 13--a for loop is 
used to see whether each is 9+j (the value of the index of the card). The loop
also ensures that all the cards have the same suit. 

checkStraightFlush(): this is just a straight and a flush, so if both the 
checkStraight() and checkFlush() statements return true, then 
checkStraightFlush() is true. 

checkFourOfAKind(): because the hand is sorted, then the four of a kind can
only either be in the following combination: abbbb or bbbba. Then the value of
the rank at index 0 and index 3 must be equal or the value of 1 and 4. 

checkFullHouse(): because there must be a double and a three of a kind only the
following combination of card values is possible: aabbb or bbbaa. For the first
combination, index at 0 and 1 must be equal AS WELL as the index at 2 and 4. For
the second combination, index at 0 and 2 must be equal AS WELL as the index at 3
and 4. 

checkFlush(): only when all 5 cards have the same suit is there a Flush, so by
storing the value of the first suit in a String and then using a for loop to 
compare subsequent suits at the other indexes. 

checkStraight(): when the player has 5 consecutive cards. There are two 
different situations to test. If there is an Ace, you can have an Ace to
Four straight or a Ace, 10, Jack, Queen, King. To account for each of those
occurances two different statements need to be tested. Both occur within the
for loop--which start incredmenting from the first index. For non-Ace related
straights, the rank at index i must be i greater than the first Rank--this 
also works for the first example of an Ace. The other Ace variation is when,
the second element and following elements are 9+i more than the Ace.

checkThreeOfAKind(): for three of a kind, following combinations are the only
available options: aaabb, baaab, or bbaaa. For each of these combinations only
evaluating the first three indexes will show whether there is three of a kind.
Index 0 and index 2, index 1 and index 3, or index 2 and index 4--at least one
must be true for there to be three of a kind.

checkTwoPair(): to evaluate that there are two pairs, there is a counter that 
keeps track of the number of pairs that are in the hand. By the end of the loop, 
the counter should be at 2, if there are two pairs.

checkOnePair(): increments through the hand, checking whether the card at the 
next index is equal to the one before it, if it is, the equation returns true.
Illustrating that there is at least one pair in the hand.

If none of the previous statements are true, then the player has a high card.
The series of if/else if statement ensure that the player is returned the 
highest payout of their hand. In each if statement the winnings of the player
is calculated by calling the p.winnings() method, and the player's bankroll is
updated respectively depending on the amount they won.

Then at the end, the player's hand is cleared with the clearHand method, and is
asked whether they want to play again.

For the testHand game, there's a test to ensure that the same card is not 
entered twice, otherwise, the checkHand method is called and the hand of the 
player is returned.


===============================================================================
Player Class
===============================================================================

The Player class creates an ArrayList of Card objects called hand, as well as an
instance variables for the bankroll and the amount the user wants to bet. The 
bankroll is set at 100 to begin. The getCard() method returns a Card at a 
specific index in the hand by iterating through the hand and finding the match
at the specificed parameter--then returning that card. The addCard() method adds
a specific card to the ArrayList called hand. The removeCard() method removes
the specified card from the ArrayList. The bets() method stores the user 
inputted bet value in the instance variable bet. The winnings() method updates
the bankroll, by adding the winnings (which is calculated by the amount the user
bet by the payout) and substracting the amount the user bet--aka buyin price. 
The getBankroll() method returns the bankroll of the player. The printHand 
method, iterates through the entire ArrayList and prints all the elements in the
hand. The replaceCard() method takes in an index and a Card as the explicit 
parameters. replaceCard() uses the set method to overwrite, the previous card 
at the specific index with a new card. Finally the clearHand() method uses the 
clear method to remove all the card objects in the ArrayList.

===============================================================================
Deck Class
===============================================================================

The Deck class creates an array of Card Objects. The constructor uses nested 
loops to create 13 cards (Ace through King) for all four suits (Clubs through 
Spades). The Deck class also has an instance variable called top that keeps the
index of the top of the deck. Each time a card is dealt, top is iterated. The
shuffle() method chooses two random cards in the deck and swaps them--this is
repeated 10000 times to ensure a well shuffled deck. Everytime the deck is 
top is reset to 0. The deal() method returns the card at the top of the deck.
Because top needs to be incremented before the return value, you need to 
increment before the return, so you always need to return the card at top-1.
deal() also takes into account when the deck runs out of cards to deal, if that
is the case, the deck is reshuffled, and the top card is dealt.

===============================================================================
Card Class
===============================================================================

For this class, my compareTo method compares the rank first, then if there is a
tie, the suit will break the tie. The ordering of the suit follows alphabetical
order. The compareTo method returns -1, 0, or 1 depending on the result of the 
comparison. The toString method calls the getRank() and getSuit() method which
account for the special characters such as Ace, Jack, Queen, King for the ranks
and Clubs, Diamonds, Hearts, and Spades for the suits. toString returns both 
the rank and the suit name for a card. getIntRank() is a method that is called 
in the Game class, but it is in the Card class because it returns the rank of a
Card object's integer value. 