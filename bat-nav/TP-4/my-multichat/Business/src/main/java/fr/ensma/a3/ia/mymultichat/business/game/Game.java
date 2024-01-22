package fr.ensma.a3.ia.mymultichat.business.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Game {

	/*
	 * TODO: CannalType chat vs game (créer jeuServerEndPoint/jeuClientEndPoint?)
	 */

	private List<Player> joueurs;
	private Player currentPlayer;
	private int number;
	private EGameState state;

	public Game() {
		this.joueurs = new ArrayList<>();
		set();
	}

	public void start() {
		if (state == EGameState.WAITING) {
			currentPlayer = joueurs.get(0);
			this.state = EGameState.IN_GAME;
		}
	}

	public void set() {
		this.state = EGameState.WAITING;
		number = new Random().nextInt(100);
		System.out.println("GOAL:" + number);
	}

	public EResponseType guess(Player player, int guess) {
		currentPlayer = joueurs.get((joueurs.indexOf(currentPlayer) + 1) % joueurs.size());
		
		if (guess > number) {
			return EResponseType.ITS_LESS;
		} else if (guess < number) {
			return EResponseType.ITS_MORE;
		} else {
			set();
			return EResponseType.CORRECT;
		}
	}

	public void registerPlayer(Player player) {
		if (!joueurs.contains(player)) {
			joueurs.add(player);
		}
	}

	public void removePlayer(Player player) {
		if (joueurs.contains(player)) {
			joueurs.remove(player);
		}
	}

	public Optional<Player> getPlayer(String name) {
		Optional<Player> opt = Optional.empty();
		for (Player p : joueurs) {
			if (p.getName().equals(name))
				return Optional.of(p);
		}
		return opt;
	}

	public boolean isConnected(String name) {
		for (Player p : joueurs) {
			if (p.getName().equals(name))
				return true;
		}
		return false;
	}

	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}

	public boolean isWaitingState() {
		return state == EGameState.WAITING;
	}

	public int getGoal() {
		return number;
	}

}
