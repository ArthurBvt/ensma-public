package fr.ensma.a3.ia.carnetadressesbusiness.carnet;

import java.util.ArrayList;

import fr.ensma.a3.ia.carnetadressesbusiness.dal.DataAccess;

public class Carnet {

	private ArrayList<Personne> carnet;
	
	public Carnet() {
		reload();
	}
	
	public void addPersonne(Personne personne) {
		DataAccess.writePersonne(personne);
		reload();
	}
	
	public void removePersonne(Personne personne) {
		DataAccess.deletePersonne(personne);
		reload();
	}
	
	public void updatePersonne(Personne personne, Personne updatedPersonne) {
		DataAccess.updatePersonne(personne, updatedPersonne);
		reload();		
	}
	
	private void reload() {
		carnet = DataAccess.loadCarnet();
	}
	
	@Override
	public String toString() {
		String s = "Carnet de contacts:\n";
		
		for(Personne p : carnet)
			s += "   - " + p.toString() + "\n";
		
		return s;
	}
	
}
