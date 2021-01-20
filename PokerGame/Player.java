/* Grace Li
 * gl2676
 * Player class gives the user a hand, a bankroll, and stores they bet.
 * The Player class also has methods to allow the user to access 
 * different cards in their hand.
 * */

import java.util.ArrayList;

public class Player {
	
		
	private ArrayList<Card> hand; //the player's cards
	private double bankroll;
    private double bet;

	//constructor that initializes the bankroll to 100, bet to 0, 
    //and creates an arraylist called hand
	public Player(){		
	    hand = new ArrayList<Card>();
        bankroll = 100.00;
        bet=0.0;
	}
    
    //returns the card at a certain index
    public Card getCard(int index){
        Card specificCard = new Card(0,0);
        
        for(int i=0; i<hand.size(); i++){
            
            if(hand.get(i).compareTo(hand.get(index))==0){
                specificCard = hand.get(i);
            }
        }
        return specificCard;
    }
    
    //adds a card to the player's hand
	public void addCard(Card c){
        hand.add(c);
	}
    
    //removes a specific card in the hand
	public void removeCard(Card c){
	    for(int i=0; i<hand.size();i++){
            
            if(hand.get(i).compareTo(c)==0){
                hand.remove(i);
            }
        }
    }
    
    //stores how much the player wants to bet
    public void bets(double amt){
        bet = amt;
    }

    //adjust bankroll if player wins by substracting the bet value
    //and amount they win (bet values times the odds)
    public void winnings(double odds){
        bankroll += (odds * bet) -bet;
    }
    
    
    //return current balance of bankroll
    public double getBankroll(){
        return bankroll;
    }
    
    //prints the cards in the player's hand
    public void printHand(){
        for(int i=0; i<hand.size();i++){
            System.out.println(hand.get(i));
        }
    }
    
    //returns the player's hand
    public ArrayList<Card> getHand(){
        return hand;
    }
    
    //replaces the card at a certain index with a new card
    public void replaceCard(int index, Card replacement){
        hand.set(index, replacement); 
    }
    
    //removes all the cards in the player's hand
    public void clearHand(){
        hand.clear();
    }
    
}


