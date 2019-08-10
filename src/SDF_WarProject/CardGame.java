/**
 * @author Yldraziw (Tyler W. Belair - 991561950)
 */
package SDF_WarProject;

import java.util.*;
import java.util.ArrayList;     //import ArrayList
import java.io.IOException;


public class CardGame {
    public static boolean playing = false;
    public static boolean isPlaying = false;
    public static boolean isNaming = true;
    public static String isPlay;
    //TODO this doesn't seem particularly logical.
    public static Player cPlayer1 = new Player("");
    public static Player cPlayer2 = new Player("");

    public static void main(String[] args) {
        String nameTemp1 = "";
        String nameTemp2 = "";
        Scanner sc = new Scanner(System.in);


        System.out.println("Please input a name for Player 1 and PLayer 2.");
        System.out.println("Welcome to Group 6's War!");
        System.out.println("The goal of the game is to see if you are the victor");

        //TODO: Reconfigure try/catch state to look for non-string literal names for players.
        while(isNaming) {
            try {
                System.out.println("Please Input Player 1 name (String-only)");
                nameTemp1 = sc.next();
                if (nameTemp1.matches("^[a-zA-Z]*$")) {
                    cPlayer1.setPlayerID(nameTemp1);
                    System.out.println("Input Accepted");
                    System.out.println("Player 1 name is : " + cPlayer1.getPlayerID() + ".");

                } else {
                    throw new InputMismatchException();

                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid name");
                continue;
            }

            try {
                System.out.println("Please Input Player 2 name (String-only)");
                nameTemp2 = sc.next();
                if (nameTemp2.matches("^[a-zA-Z]*$")) {
                    cPlayer2.setPlayerID(nameTemp2);
                    System.out.println("Input Accepted");
                    System.out.println("Player 2 name is : " + cPlayer2.getPlayerID() + ".");
                } else {
                    throw new InputMismatchException();
                }
            } catch (InputMismatchException e) {
                System.out.println("Please input a valid name");
                continue;
            }
            isNaming = false;
        }

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
            if(p1card.getCard() > p2card.getCard()){//if player 1 win
                deck1.addLast(p1card);  //higher rank wins both cards and
                deck1.addLast(p2card);  //places them at the bottom of his deck.
                System.out.println(cPlayer1.getPlayerID() + " wins the round");
            }//end if

            else if(p1card.getCard() < p2card.getCard()){//if player 2 win
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
                    if(war1.get(0).getCard() > war2.get(0).getCard()){
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

//TODO: Extend the abstractions made in this class to incorporate a turn-based system for the card game War.
//TODO: Typical rules for war as follows:
//TODO:
//Append new hierarchy structure system to following code as it is in a mess of patch-worked ideas and topology's
//Configure new collections array list for deck shuffling
//Configure new menu system to allow user to input string names or int ID number as associated player names
//Menu system must also incorporate a "total game" score rating
//Menu system must also append round options such as "continue playing" or "new game" etc
//Threaten the dozen if-statements present and rewrite them to conform to a properly structured and logical code hierarchy.

