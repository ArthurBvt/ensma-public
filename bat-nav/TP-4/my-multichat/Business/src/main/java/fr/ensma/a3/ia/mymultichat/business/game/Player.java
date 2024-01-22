package fr.ensma.a3.ia.mymultichat.business.game;

public class Player {

	private String name;
	
	public Player(final String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ((Player) obj).getName().equals(name);
	}
	
}
