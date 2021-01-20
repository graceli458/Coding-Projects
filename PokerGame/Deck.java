/* Grace Li
 * gl2676
 * Deck creates a deck with 52 card objects with methods that allow for
 * shuffling, dealing, and return
 * */

public class Deck {
	
	private Card[] cards; 
	private int top; //the index of the top of the deck

	//constructor that creates a deck of 52 cards with values of Ace-King
    //for all 4 suits. Sets the top index to 0
	public Deck(){
        int index=0;
        cards = new Card[52];
        
        for(int suit=1; suit<=4; suit++){
            
            for(int rank=1; rank<=13; rank++){
                cards[index]= new Card(suit, rank);
                index++;
            }
        }
        top=0;
	}
	
    //shuffles the deck by switching two cards in the deck, 10000 times
	public void shuffle(){
		for(int i=0; i<10000; i++){
            
            int pos1 = (int)(Math.random()*52);
            int pos2 = (int)(Math.random()*52);
           
            Card one = cards[pos1];
            Card two = cards[pos2];
            
            cards[pos1] = two;
            cards[pos2] = one;
            
        }
        top=0;
	}
	
    //returns the card at the top of the deck and shuffles the deck if 
    //it runs out of cards
	public Card deal(){
        
		if(top<52){
            top++;
            return cards[top-1];
        }
        else{
            shuffle();
            top++;
            return cards[top-1];
        }
	}

}
