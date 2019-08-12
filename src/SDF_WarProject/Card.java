package SDF_WarProject;

/**
 * Card Class
 * Returns combined string method of Card constructor.
 * Suit + Rank = new Card(suit,rank);
 */
class Card {
    private int rank; //initialize the rank (2,3,4...King, Ace)
    private int suit; //initialize the suit (spades, hearts...)

    /**
     *
     * @param suit - suit variable for card class
     * @param rank - rank variable for card class
     */
    //constructor
    public Card(int suit, int rank){
        this.rank = rank;
        this.suit = suit;
    }//end construcor

    //Accessor method
    public int getCardRank(){
        return rank;
    }//end getCard

    /**
     *
     * @param rank - rank parameter is given to the card rank, used in both the dynamic arrayList of CardGame
     *             and in the toString switch statement
     */
    //Mutator method
    public void setCardRank(int rank){
        this.rank = rank;
    }//end setCard

    public int getCardSuit(){
        return suit;
    }

    /**
     *
     * @param suit parameter is given to the card suit, used in both the dynamic arrayList of CardGame
     *      *             and in the toString switch statement
     */
    public void setCardSuit(int suit){
        this.suit = suit;
    }

    /**
     * toString - Method returns an appended variable called "displayCard" which is a string combination built using
     * StringBuilder.
     * @return - total displayCard string is returned using StringBuilder.
     */
    @Override
    public String toString(){
        //combine rank and suit together into a single string(ex: Ace of Diamonds)

        //suing StringBuilder for modifiability later on
        StringBuilder displayCard = new StringBuilder();

        //personal choice to use switch
        switch(rank){
            //since rank is int type, now match int 11 to String jack...14 to Ace
            case 11:
                displayCard.append("Jack");
                break;
            case 12:
                displayCard.append("Queen");
                break;
            case 13:
                displayCard.append("King");
                break;
            case 14:
                displayCard.append("Ace");
                break;
            default:
                displayCard.append(rank); //number from 2 to 10 does not need to modify
                break;
        }//end rank switch

        displayCard.append(" of "); //setting the format of the output

        switch(suit){
            case 0:
                displayCard.append("Spades");
                break;
            case 1:
                displayCard.append("Hearts");
                break;
            case 2:
                displayCard.append("Clubs");
                break;
            case 3:
                displayCard.append("Diamonds");
                break;
            default: //anything else, do nothing
                break;
        }//end suit switch

        //return the result of an entire combined string
        return displayCard.toString();
    }//end toString

}//end Card Class