 /* Grace Li
 * gl2676
 * Card implements Comparable<Card>
 * Card has a compareTo, toString, and getIntRank method that allows sorting 
 * the cards printing cards, and evaluating the numeric value of the cards 
 * */

public class Card implements Comparable<Card>{
	
	private int suit; //use integers 1-4 to encode the suit
	private int rank; //use integers 1-13 to encode the rank
	
    //constructor that takes in two values and initalizes them to suit/rank
	public Card(int s, int r){
		suit = s;
        rank = r;
	}
	
    //compares two cards and returns 1, 0, -1, depending on the ordering of the 
	public int compareTo(Card c){
        
        int resultOfComp=0;
        
        if(rank>c.rank){
            resultOfComp=1;
        }
        else if(rank<c.rank){
            resultOfComp=-1;
        }
        else{
            if(suit>c.suit){
                resultOfComp=1;
            }
            else if (suit<c.suit){
                resultOfComp=-1;
            }
            
        }
        return resultOfComp;
	}
    
    //return the string name of special ranks 
    private String getRank(){
        String rankName="";
        
        if(rank==1){
            rankName="Ace";
        }
        else if(rank==11){
            rankName="Jack";
        }
        else if(rank==12){
            rankName="Queen";
        }
        else if(rank==13){
            rankName="King";
        }
        else{
            rankName=Integer.toString(rank);
        }
        
        return rankName;
    }
	
    //print card values prettily
	public String toString(){
		
        String cardDescription= getRank() +"\t"+ getSuit();
        return cardDescription;
	}
    
    
    //return the string name of a card's suit
    public String getSuit(){
        String suitName = "";
        
        switch(suit){
            case 1: 
                suitName = "Clubs";
                break;
                
            case 2:
                suitName = "Diamonds";
                break;
                
            case 3:
                suitName = "Hearts";
                break;
                
            case 4:
                suitName = "Spades";
                break;
                
            default:
                suitName = "Bad Input";
        }
        
        return suitName;
    }
    
    //return the integer value of a card's rank
    public int getIntRank(){
        
        return rank;
        
    }

}
