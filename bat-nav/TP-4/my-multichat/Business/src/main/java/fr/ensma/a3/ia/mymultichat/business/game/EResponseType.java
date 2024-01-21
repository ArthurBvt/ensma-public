package fr.ensma.a3.ia.mymultichat.business.game;

public enum EResponseType {

	NOT_YOUR_TURN("Ce n'est pas ton tour, petit malin"),
	LESS("C'est moins!"),
	MORE("C'est plus!"),
	CORRECT("Bonne réponse, chacal !");
	
	private String s;
	private EResponseType(String s) {
		this.s = s;
	}
	
	@Override
	public String toString() {
		return s;
	}
}
