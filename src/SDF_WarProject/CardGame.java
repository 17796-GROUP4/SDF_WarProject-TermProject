/**
 * @author Yldraziw (Tyler W. Belair - 991561950)
 */
package SDF_WarProject;

import java.util.*;
import java.util.ArrayList;     //import ArrayList

/**
 * CardGame() - Main Method for War Project.
 */
public class CardGame {
    public static boolean playing = false; /*Main gameplay boolean checks if game is still active.*/
    public static boolean isPlaying = false; /*Menu boolean*/
    public static String isPlay; /*Scanner string for menu selection*/
    //TODO this doesn't seem particularly logical.
    public static Player cPlayer1 = new Player(""); /*Player class constructor, needed for name initialization.*/
    public static Player cPlayer2 = new Player(""); /*Player class constructor, needed for name initialization.*/

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        System.out.println("Please input a name for Player 1 and PLayer 2.");
        System.out.println("Welcome to Group 6's War!");
        System.out.println("The goal of the game is to see if you are the victor");
        warName();

        //TODO: Reconfigure try/catch state to look for non-string literal names for players.
            while (!isPlaying) {

                System.out.println("Are you ready to play the game? y/n");
                isPlay = sc.next();
                try {
                    switch (isPlay) {
                        case "y":
                            isPlaying = true;
                            playing = true;
                            playWar();
                            break;
                        case "n":
                            System.out.println("Quitting game..");
                            System.exit(0);
                            break;
                        default:
                            throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("I'm sorry but you're not allowed to type nothing");
                    System.out.println("Please input a valid response");
                    continue;
                }
            }
    }//end main

    /**
     * WarName - Method for initializing player ID's.
     * try/catch block implemented for error checking with user input
     */
    public static void warName(){
        String nameTemp1 = ""; /**Temporary variable for Player 1 name construction */
        String nameTemp2 = ""; /**Temporary variable for Player 2 name construction */
        boolean hasP1 = false; /**Boolean variable for If-statement check */
        boolean hasP2 = false; /**Boolean variable for If-statement check */
        Scanner sc = new Scanner(System.in);

        System.out.println("Before you begin you must input names for the players");

        while(!hasP1) {
            try {
                System.out.println("Please Input Player 1 name ");
                nameTemp1 = sc.next();
                if (nameTemp1.matches("^[a-zA-Z]*$") && !hasP1) {
                    cPlayer1.setPlayerID(nameTemp1);
                    System.out.println("Input Accepted");
                    System.out.println("Player 1 name is : " + cPlayer1.getPlayerID() + ".");
                    hasP1 = true;
                } else { throw new InputMismatchException(); }
            } catch (InputMismatchException e) { System.out.println("Please input a valid name (String-only)"); }
        }
        while(!hasP2){
                try {
                    System.out.println("Please Input Player 2 name");
                    nameTemp2 = sc.next();
                    if (nameTemp2.matches("^[a-zA-Z]*$") && !hasP2) {
                        cPlayer2.setPlayerID(nameTemp2);
                        System.out.println("Input Accepted");
                        System.out.println("Player 2 name is : " + cPlayer2.getPlayerID() + ".");
                        hasP2 = true;
                    } else {
                        throw new InputMismatchException();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please input a valid name (String-only)");
                }
            }
        }

    /**
     * hasPlayer1 - Boolean function for JUnit test case.
     * @param nameTemp1 - Boolean statement for JUnit test case.
     * @return - true if test is successful, false if not.
     */
    public static boolean hasPlayer1(String nameTemp1){
        if(nameTemp1.matches("^[a-zA-Z]*$")){
            return true;
        }
        return false;
    }

    /**
     * hasPlayer2 - Boolean function for JUnit test case.
     * @param nameTemp2 - Boolean statement for JUnit test case.
     * @return - true if test is successful, false if not.
     */
    public static boolean hasPlayer2(String nameTemp2){
        if(nameTemp2.matches("^[a-zA-Z]*$")){
            return true;
        }
        return false;
    }

    /**
     * Main Gameplay Method.
     * Creates new cardDeck from class Card as new dynamic ArrayList.
     * Adds card suits and card ranks for gameplay
     * Shuffles dynamic list.
     *
     * Creates 2 new dyanmic ArrayLists to be used as player decks.
     * Creates sublists from initial ArrayList for each player deck.
     *
     * GamePlay begins as each player pops a card off the top of their respective ArrayList's
     * If statements evaluate if the popped card from each player deck will become single round or a war round
     * If war has been declared, players pop 3 cards off their deck, 4th card is displayed and gameplay is manipulated
     * If either player decks cannot initiate war after it has been declared the game is over
     * else
     * Gameplay completed if either players deck.size reach 0
     */
    public static void playWar(){
        List<Card> cardDeck = new ArrayList<Card>(); //create an ArrayList "cardDeck"

        for(int x=0; x<4; x++){          //0-3 for suit (4 suits)
            for(int y=2; y<15; y++){     //2-14 for rank (13 ranks)
                cardDeck.add(new Card(x,y)); //create new card and add into the deck
            } //end rank for
        }//end suit for

        Collections.shuffle(cardDeck, new Random()); //shuffle the deck randomly

        //creating 2 decks, each for player1/player2
        LinkedList<Card> deck1 = new LinkedList<Card>();
        LinkedList<Card> deck2 = new LinkedList<Card>();

        deck1.addAll(cardDeck.subList(0, 25));              //26 cards for p1
        deck2.addAll(cardDeck.subList(26, cardDeck.size()));//26 cards for p2

        do{
            Card p1card = deck1.pop();  //each player place one card face up
            Card p2card = deck2.pop();

            //display the face up card
            System.out.println(cPlayer1.getPlayerID() + " card is " + p1card.toString());
            System.out.println(cPlayer2.getPlayerID() + " card is " + p2card.toString());

            //rank comparison between two cards
            if(p1card.getCardRank() > p2card.getCardRank()){//if player 1 win
                deck1.addLast(p1card);  //higher rank wins both cards and
                deck1.addLast(p2card);  //places them at the bottom of his deck.
                System.out.println(cPlayer1.getPlayerID() + " wins the round");
            }//end if

            else if(p1card.getCardRank() < p2card.getCardRank()){//if player 2 win
                deck2.addLast(p1card);
                deck2.addLast(p2card);
                System.out.println(cPlayer2.getPlayerID() + " wins the round");
            }//end else if

            else { //war happens when both cards' rank matched
                System.out.println("War has been declared!");

                //creating war cards
                List<Card> war1 = new ArrayList<Card>();
                List<Card> war2 = new ArrayList<Card>();

                //checking do players have enough (4)cards to stay in game
                for(int x=0; x<3; x++){
                    //either one player runs out of card is game over
                    if(deck1.isEmpty() || deck2.isEmpty() ){
                        break;
                    }else{
                        war1.add(deck1.pop());  //place additional card for war
                        war2.add(deck2.pop());
                        System.out.println("war card for " + cPlayer1.getPlayerID() + " is " + war1.get(x).toString() + " ."
                                + "\nwar card for " + cPlayer2.getPlayerID() + " is " + war2.get(x).toString() + " .");
                    }
                }//end for

                //only compare result when both players have enough cards for war
                if(war1.size() == 3 && war2.size() == 3 ){
                    //display the war cards from each player
                    System.out.println(cPlayer1.getPlayerID() + " war card is " + war1.get(0).toString());
                    System.out.println(cPlayer2.getPlayerID() + " war card is " + war2.get(0).toString());

                    //if player 1 wins the war round
                    if(war1.get(0).getCardRank() > war2.get(0).getCardRank()){
                        deck1.addAll(war1); //player1 get all 10 cards
                        deck1.addAll(war2);
                        System.out.println(cPlayer1.getPlayerID() + " wins the war round");
                    }//end if
                    //otherwise player 2 wins the war round
                    else{
                        deck2.addAll(war1); //player2 get all 10 cards
                        deck2.addAll(war2);
                        System.out.println(cPlayer2.getPlayerID() + " wins the war round");
                    }//end else
                }//end if

            }//end war round else

            //game is processed as completed if either player's deck is size 0(ran out of cards)
            if(deck1.isEmpty() ){
                System.out.println("game over\n " + cPlayer1.getPlayerID() + " wins the game");
                break;
            }
            else if(deck2.isEmpty()){
                System.out.println("game over\n " + cPlayer2.getPlayerID() + " wins the game");
                break;
            }
        } while(playing);
    }
}//end CardGame class