/* Grace Li
 * gl2676
 * Game class allows the user to play an interactive game, or deal themselves
 * a hand and check what type of hand they have
 * */

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Character;

public class Game {
	
	private Player p;
	private Deck cards;
    //you'll probably need some more
	
	//constructor takes in a command line argument, and convert's the 
	//user input into Card objects that are added to the player's hand, 
    //and creates a new Deck
	public Game(String[] testHand){
		p = new Player();
        int rank=0;
        
        for(int i=0; i<testHand.length; i++){
            
            if(testHand[i].length()<3){
                 rank = Character.getNumericValue(testHand[i].charAt(1)); 
            }
            else{
                String doubleDigits = testHand[i].substring(1,3);
                rank = Integer.valueOf(doubleDigits);
            }
            
            char suit = testHand[i].charAt(0);
            int suitValue = checkTestHandSuit(suit);
            
            Card addCard = new Card(suitValue, rank);
            
            p.addCard(addCard); 
        }
        
        cards = new Deck();
	}
    
    //converts the character input of the suit into an integer 
    private int checkTestHandSuit(char charSuit){
        int intSuit = 0;
        
        switch(charSuit){
            case 'c':
                intSuit = 1;
                break;
                
            case 'd':
                intSuit = 2;
                break;
            
            case 'h':
                intSuit = 3;
                break;
               
            case 's':
                intSuit = 4;
                break;
                
            default:
                intSuit = 0;
               
        }
        
        return intSuit;
    }
    
    //checks to see whether the user input is valid, 
    //no duplicate cards can be entered
    private boolean checkDuplicateInput(){
        for(int i=0; i<p.getHand().size(); i++){
            
            for(int j=i+1; j<p.getHand().size(); j++){
                
                if(p.getCard(i).compareTo(p.getCard(j))==0){
                    return false;
                }
            }
        }
        return true;
    }
	
    //constructor creates an interactive game
	public Game(){
        p = new Player();
        cards = new Deck();
	}
	
    //play method executes allows for both interactive and non-interactive games
	public void play(){
        boolean playAgain = true;
        
        if(p.getHand().size()==0){ 
            
            while(playAgain){
                
                betting();
                cards.shuffle();
                dealAndStore();
                p.printHand();
                exchangeCard();
                System.out.println("Your new hand is:");
                p.printHand();

                System.out.println(checkHand(p.getHand()));
                
                System.out.println("Your bankroll is " +p.getBankroll());
                
                p.clearHand();
                playAgain = continuePlaying();
            }
        }
        else{
            
            if(!checkDuplicateInput()){
                System.out.println("Do not enter the same card more than once");
            }
            
            else{
                p.printHand();
                System.out.println(checkHand(p.getHand()));
            }
        }
	}
    
    //asks the user for how much they want to bet and stores the value  in 
    //p.bets()
    private void betting(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("How much do you want to bet? Input a number 1-5");
        double userInput = input.nextInt();
        
        while(userInput>5 || userInput<0){
            System.out.println("Invalid Input: Please enter a a number 1-5");
            userInput = input.nextInt();
        }
        
        p.bets(userInput);
    }
    
    //asks the uses whether they want to continue playing
    private boolean continuePlaying(){
        Scanner input = new Scanner(System.in);
        
        System.out.println("Do you want to keep playing? 0/no 1/yes");
        int userInput = input.nextInt();
        
        if(userInput==1){
            return true;
        }
        else{
            return false;
        }
    }
    
    //deals the user 5 cards and adds each card to the player's hand
    private void dealAndStore(){
        for(int i=0; i<5; i++){
         
            Card deal1 = cards.deal();
            p.addCard(deal1);
           
        }
    }
    
    //the series of messages that are printed to the user to exchange cards
    private void getExchangeMessages(){
        System.out.println("To keep all your cards just enter 0.\n" + 
                          "For example: to exchange your first card enter 1.\n"+
                          "To exchange multiple cards, use spaces to separate" +
                          " each card you want to swap.");
    }
    
    //allows the user to exchange cards and replace 
    //the cards in their original hand
    private boolean exchangeCard(){
        Scanner input = new Scanner(System.in);
        
        getExchangeMessages();
        String userInput = input.nextLine();
        
        while(!evaluateExchanges(userInput)){
            System.out.println("You can't exchange the same card multiple " +
                               " times. Please re-enter the cards you" + 
                               " would like to switch");
            
            userInput = input.nextLine();
        }
        
        if(userInput.equals("0")){
            
            return false;
        }
        else{

            for(int i=0; i<=userInput.length(); i+=2){

                    char c1 = userInput.charAt(i);
                    int indexToRemove = Character.getNumericValue(c1)-1;
                    
                    Card newCard=cards.deal();
                    p.replaceCard(indexToRemove, newCard);
                
            }
        }
        
        return true;
    }
    
    //checks to see if the user's exchange requests are valid, the 
    //user cannot exchange the same card multiple times
    private boolean evaluateExchanges(String userinput){
        if((userinput.length()>9)){
            return false;
        }
        
        for(int i=0; i<userinput.length(); i+=2){
            char indexI = userinput.charAt(i);
            
            for(int j=i+2; j<userinput.length(); j+=2){
                char indexJ = userinput.charAt(j);
                
                if(Character.compare(indexJ, indexI)==0){
                    return false;
                }
            }
        }
        return true;
    }

	//checks what type of hand the player has and 
	//calculates the winnings accordingly
	private String checkHand(ArrayList<Card> hand){
		String winningHand = " ";
        
        Collections.sort(hand);
        if(checkRoyalFlush(hand)){
            
            winningHand = "You have a Royal Flush.";
            p.winnings(250.00);
            return winningHand;
        }
        else if(checkStraightFlush(hand)){
            
            winningHand = "You have a Straight Flush";
            p.winnings(50.00);
            return winningHand;
        }
        else if(checkFourOfAKind(hand)){
            
            winningHand = "You have a Four of a Kind";
            p.winnings(25.00);
            return winningHand;
        }
        else if(checkFullHouse(hand)){
            
            winningHand = "You have a Full House";
            p.winnings(6.00);
            return winningHand;
        }
        else if(checkFlush(hand)){
            
            winningHand = "You have a Flush";
            p.winnings(5.00);
            return winningHand;
        }
        else if(checkStraight(hand)){
            
            winningHand = "You have a Straight";
            p.winnings(4.00);
            return winningHand;
        }
        else if(checkThreeOfAKind(hand)){
            
            winningHand = "You have a Three of a Kind";
            p.winnings(3.00);
            return winningHand;
        }
        else if(checkTwoPair(hand)){
            
            winningHand = "You have a Two Pair";
            p.winnings(2.00);
            return winningHand;
        }
        else if(checkOnePair(hand)){
            
            winningHand = "You have a One Pair";
            p.winnings(1.00);
            return winningHand;
        }
        else{
            
            winningHand = "High Card";
            p.winnings(0.00);
            return winningHand;
        }
	}
    
    
    //checks whether there is at least two cards with the same rank
    private boolean checkOnePair(ArrayList<Card> hand){
        for(int i=1; i<hand.size(); i++){
            
            if(hand.get(i).getIntRank()==hand.get(i-1).getIntRank()){
                return true;
            }  
        }
         return false;
    }
    
    //checks whether there are at least two pairs of cards with the same rank
    private boolean checkTwoPair(ArrayList<Card> hand){
        int countingPairs = 0;
        
        for(int i=1; i<hand.size(); i++){
            
            if(hand.get(i).getIntRank()==hand.get(i-1).getIntRank()){
                countingPairs++;
            }
        }
        
        if(countingPairs==2){
            return true;
        }
        return false;
    }
    
    //checks to see whether there are three cards with the same rank
    private boolean checkThreeOfAKind(ArrayList<Card> hand){
        for(int i=0; i<3; i++){
            
            if(hand.get(i).getIntRank()==hand.get(i+2).getIntRank()){
                return true;
            }
        }
        return false;
    }
    
   //checks to see whether the player's hand has 5 consecutive cards
   private boolean checkStraight(ArrayList<Card> hand){ 
       int firstRank = hand.get(0).getIntRank();
       
       for(int i=1; i<hand.size(); i++){
           
           if(firstRank==1){
               
               int test1 = hand.get(i).getIntRank()-i;
               int test2 = hand.get(i).getIntRank();
               
               if(test1 != 9 && firstRank+i != test2){
                   return false;
               }
           }
           else{
               
               if(firstRank+i!=hand.get(i).getIntRank()){
                   return false;
               }
           }
       }
       return true;
    }
    
    //checks whether there are 5 cards with the same suit
    private boolean checkFlush(ArrayList<Card> hand){
        String firstSuit = hand.get(0).getSuit();
        
        for(int i=1; i<hand.size(); i++){
            
            if(!firstSuit.equals(hand.get(i).getSuit())){
                return false;
            }
        }
        return true;
    }
    
    //check this
    //checks whether there is a three of a kind and a double in ranks
    private boolean checkFullHouse(ArrayList<Card> hand){
        
        if(hand.get(0).getIntRank()==hand.get(2).getIntRank()){
            
            if(hand.get(3).getIntRank()==hand.get(4).getIntRank()){
                return true;
            }
            return false;
        }
        
        if(hand.get(0).getIntRank()==hand.get(1).getIntRank()){
            if(hand.get(2).getIntRank()==hand.get(4).getIntRank()){
                return true;
            }
            return false;
        }
        
        return false;
    }
    
    //checks whether there are four of the cards with the same rank
    private boolean checkFourOfAKind(ArrayList<Card> hand){
        if(hand.get(0).getIntRank()==hand.get(3).getIntRank()){
            return true;
        }
        else if(hand.get(1).getIntRank()==hand.get(4).getIntRank()){
            return true;
        }
        else{
            return false;
        }  
    }
    
    //checks whether there is a straight and a flush
    private boolean checkStraightFlush(ArrayList<Card> hand){
        return checkStraight(hand) && checkFlush(hand); 
    }
    
    //checks whether the hand has an Ace, 10, King, Queen, King in the same suit
    private boolean checkRoyalFlush(ArrayList<Card> hand){
        String firstSuit = hand.get(0).getSuit();
        int firstRank = hand.get(0).getIntRank();
        
        if(firstRank!=1){
            return false;
        }
        
        for(int j=1; j<hand.size(); j++){
            
            if(hand.get(j).getIntRank()!=j+9){
                return false;
            }
            if(!firstSuit.equals(hand.get(j).getSuit())){
                return false;
            }
        }
        return true; 
    }
}
