package codexnaturalis.game;

import java.awt.Color;
import java.awt.Cursor;
import java.io.IOException;
import java.util.Random;

import codexnaturalis.card.Card;
import codexnaturalis.card.Coordinates;
import codexnaturalis.card.CursorCard;
import codexnaturalis.card.Deck;
import codexnaturalis.player.Player;
import fr.umlv.zen5.Application;
import fr.umlv.zen5.ScreenInfo;
import fr.umlv.zen5.Event.Action;
import fr.umlv.zen5.KeyboardKey;


public class CodexNaturalis {

	public static void main(String[] args) throws IOException {
		
	        Player p1 = new Player(1);
	        Board board = new Board();
	        Deck deck = DeckLoader.createDeck("src/codexnaturalis/game/deck.txt");
	        System.out.print(deck);

	        System.out.println(p1);
	        System.out.println();
	        Application.run(Color.BLACK, context -> {
	        	try {
					Menu.drawMenu(context);
				} catch (IOException e) {
					e.printStackTrace();
				}
	        	ScreenInfo screenInfo = context.getScreenInfo();
    			float screenWidth = screenInfo.getWidth();
    			float screenHeight = screenInfo.getHeight();
    			board.drawBoard(p1, context);
    			Card firstCard = deck.drawCard();
    			CursorCard cursorCard = new CursorCard();
    			Coordinates coordinatesFirstCard = new Coordinates((screenWidth/2) - firstCard.width()/2, (screenHeight/2) - firstCard.height()/2);
    			firstCard.draw(context, coordinatesFirstCard);
    			cursorCard.draw(context, coordinatesFirstCard);
                board.add(coordinatesFirstCard, firstCard);
                System.out.println(board);
	            // Boucle du jeu
	            while (true) {
	                var event = context.pollOrWaitEvent(10); // Récuperer un event 
	                if (event == null) {
	                    continue;
	                }
	                var action = event.getAction();
	                var key = event.getKey();
	                if (action == Action.POINTER_DOWN) { // Si clic de la souris
	                    if (!(deck.isEmpty())) { // Tant que le deck n'est pas vide
	                        Card randomCard = deck.drawCard();
	                        Coordinates coordinates = new Coordinates(event.getLocation().x, event.getLocation().y);
	                        board.add(coordinates, randomCard);
	                        randomCard.draw(context, coordinates);
	                    } else { // Sinon, on quitte le jeu
	                        System.out.println("Deck épuisé");
	                        context.exit(0); // On ferme la fenêtre
	                        return;
	                    }
	                } else if (key == KeyboardKey.RIGHT) { // Tests pour savoir si l'on veut bouger le plateau
	                    System.out.println(board.board());
	                    board.move(new Coordinates(25, 0));
	                    Menu.clearScreen(context);	// On efface tout
	                    for (Coordinates coordinates : board.board().keySet()) {
	                        board.board().get(coordinates).draw(context, coordinates); // Puis pour chaque carte on la redessine avec ses nouvelles coordonnées
	                    }
	                } else if (key == KeyboardKey.LEFT) {
	                    System.out.println(board.board());
	                    board.move(new Coordinates(-25, 0));
	                    Menu.clearScreen(context);	// On efface tout
	                    for (Coordinates coordinates : board.board().keySet()) {
	                        board.board().get(coordinates).draw(context, coordinates); // Puis pour chaque carte on la redessine avec ses nouvelles coordonnées
	                    }
	                } else if (key == KeyboardKey.UP) {
	                    System.out.println(board.board());
	                    board.move(new Coordinates(0, -25));
	                    Menu.clearScreen(context);	// On efface tout
	                    for (Coordinates coordinates : board.board().keySet()) {
	                        board.board().get(coordinates).draw(context, coordinates); // Puis pour chaque carte on la redessine avec ses nouvelles coordonnées
	                    }
	                } else if (key == KeyboardKey.DOWN) {
	                    System.out.println(board.board());
	                    board.move(new Coordinates(0, 25));
	                    Menu.clearScreen(context);	// On efface tout
	                    for (Coordinates coordinates : board.board().keySet()) {
	                        board.board().get(coordinates).draw(context, coordinates); // Puis pour chaque carte on la redessine avec ses nouvelles coordonnées
	                    }
	                } else if (key == KeyboardKey.A) {
	                	Card randomCard = deck.drawCard();
                        Coordinates coordinates = new Coordinates(event.getLocation().x, event.getLocation().y);
                        board.add(coordinates, randomCard);
                        randomCard.draw(context, coordinates);
	                } else if (key == KeyboardKey.Z) {
	                	Coordinates cursorCardCoordinates = cursorCard.move(coordinatesFirstCard, "Z");
	                	cursorCard.draw(context, cursorCardCoordinates);
	                } else if (key == KeyboardKey.Q) {
	                	Coordinates cursorCardCoordinates = cursorCard.move(coordinatesFirstCard, "Q");
	                	cursorCard.draw(context, cursorCardCoordinates);
	                } else if (key == KeyboardKey.D) {
	                	Coordinates cursorCardCoordinates = cursorCard.move(coordinatesFirstCard, "D");
	                	cursorCard.draw(context, cursorCardCoordinates);
	                } else if (key == KeyboardKey.S) {
	                	Coordinates cursorCardCoordinates = cursorCard.move(coordinatesFirstCard, "S");
	                	cursorCard.draw(context, cursorCardCoordinates);
	                }
	            }
	        });
	  }
}