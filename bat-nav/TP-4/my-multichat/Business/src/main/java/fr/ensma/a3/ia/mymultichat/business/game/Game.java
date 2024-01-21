package fr.ensma.a3.ia.mymultichat.business.game;

import java.util.ArrayList;
import java.util.List;

public class Game {

	/*
	 * TODO:
	 * CannalType chat vs game (créer jeuServerEndPoint/jeuClientEndPoint?)
	 */
	
	private List<Player> joueurs;
	private Player currentPayer;
	private int number;
	private EGameState state;
	
	public Game() {
		this.joueurs = new ArrayList<>();
		
		this.state = EGameState.WAITING;
	}
	
	public void start() {
		if (state == EGameState.WAITING)
			this.state = EGameState.IN_GAME;
	}
	
	public void set() {
		this.state = EGameState.WAITING;
		number = (int) Math.random() * 100;
	}
	
	public EResponseType guess(Player player, int guess) {
		if(currentPayer.equals(player)) {
			if (guess > number) {
				return EResponseType.MORE;
			} else if (guess < number) {
				return EResponseType.LESS;
			} else {
				//partie gagnée
				set();
				return EResponseType.CORRECT;
			}
		} else {
			return EResponseType.NOT_YOUR_TURN;
		}
	}
	
	public void registerPlayer(Player player) {
		
	}
	
	public void removePlayer(Player player) {
		
	}
	
	public boolean isConnected(Player player) {
		return joueurs.contains(player);
	}
}
