package fr.ensma.a3.ia.carnetadressesbusiness;

import fr.ensma.a3.ia.carnetadressesbusiness.carnet.Adresse;
import fr.ensma.a3.ia.carnetadressesbusiness.carnet.Carnet;
import fr.ensma.a3.ia.carnetadressesbusiness.carnet.Personne;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
    	Carnet carnet = new Carnet();
    	System.out.println(carnet);
    	
    	Personne tony = new Personne("Stark", "Tony", new Adresse(10880, "Malibu Point", "Malibu", 90265));
    	
    	System.out.println("Registering " + tony);
    	carnet.addPersonne(tony);
    	System.out.println(carnet);
    	
    	System.out.println("Updating " + tony);
    	Personne tonyUp = new Personne("STARK", "Tony", tony.getAdresse());
    	System.out.println("To: " + tonyUp);
    	carnet.updatePersonne(tony, tonyUp);
    	System.out.println(carnet);
    	
    	System.out.println("Removing " + tonyUp);
    	carnet.removePersonne(tonyUp);
    	System.out.println(carnet);
    }
}
