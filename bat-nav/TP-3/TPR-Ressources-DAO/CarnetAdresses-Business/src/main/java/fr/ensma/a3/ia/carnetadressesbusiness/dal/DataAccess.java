package fr.ensma.a3.ia.carnetadressesbusiness.dal;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import fr.ensma.a3.ia.carnetadressesbusiness.carnet.Adresse;
import fr.ensma.a3.ia.carnetadressesbusiness.carnet.Personne;
import fr.ensma.a3.ia.carnetadressesdal.dao.IDao;
import fr.ensma.a3.ia.carnetadressesdal.dao.entity.AdresseEntity;
import fr.ensma.a3.ia.carnetadressesdal.dao.entity.PersonneEntity;
import fr.ensma.a3.ia.carnetadressesdal.dao.poi.AdressePoiDAO;
import fr.ensma.a3.ia.carnetadressesdal.dao.poi.PersonnePoiDAO;

public class DataAccess {

	private static Logger LOGGER = Logger.getLogger(DataAccess.class.getName());
	
	public static ArrayList<Personne> loadCarnet(){
		ArrayList<Personne> carnet = new ArrayList<>();
		IDao<PersonneEntity> persdao = new PersonnePoiDAO();
		IDao<AdresseEntity> adrdao = new AdressePoiDAO();
		
		/*
		Dao<PersonneEntity> persdao = new PersonneSqlDAO();
		IDao<AdresseEntity> adrdao = new AdresseSqlDAO();
		*/
		
		for (PersonneEntity pe : persdao.getAll()) {
			Optional<AdresseEntity> adrEntityOpt = adrdao.getById(pe.getAdressePers_FK());
			Adresse adresse;
			if(!adrEntityOpt.isEmpty()) {
				AdresseEntity adrEntity = adrEntityOpt.get();
				adresse = new Adresse(adrEntity.getNumRue(), adrEntity.getNomRue(), adrEntity.getNomVille(), adrEntity.getCodePostal());
			} else {
				adresse = new Adresse(0, "NaN", "NaN", 0);
			}
			Personne p = new Personne(pe.getNomPers(), pe.getPrenomPers(), adresse);
			
			carnet.add(p);
		}
		return carnet;
	}
	
	public static void writePersonne(Personne personne) {
		IDao<AdresseEntity> adrdao = new AdressePoiDAO();
		adrdao.create(convertAdresseToEntity(personne.getAdresse()));
		
		IDao<PersonneEntity> persdao = new PersonnePoiDAO();
		persdao.create(convertPersonneToEntity(personne));
	}
	
	public static void deletePersonne(Personne personne) {
		try {
			IDao<PersonneEntity> persdao = new PersonnePoiDAO();
			PersonneEntity persEntity = persdao.getByValue(convertPersonneToEntity(personne)).get();
			persdao.delete(persEntity);
			
			IDao<AdresseEntity> adrdao = new AdressePoiDAO();
			AdresseEntity adrEntity = adrdao.getByValue(convertAdresseToEntity(personne.getAdresse())).get();
			int c = 0;
			for (PersonneEntity pe : persdao.getAll()) {
				if (pe.getAdressePers_FK() == adrEntity.getIdAdr())
					c++;
			}
			if (c == 0) {
				adrdao.delete(adrEntity);
			}
		} catch (NoSuchElementException e) {
			LOGGER.log(Level.INFO, "Element absent du carnet ...");
		}
	}
	
	public static void updatePersonne(Personne personne, Personne updatedPersonne) {
		try {
			IDao<PersonneEntity> persdao = new PersonnePoiDAO();
			PersonneEntity persEntity = convertPersonneToEntity(updatedPersonne);
			persEntity.setIdPers(persdao.getByValue(convertPersonneToEntity(personne)).get().getIdPers());
			persdao.update(persEntity);
			
			IDao<AdresseEntity> adrdao = new AdressePoiDAO();
			AdresseEntity adrEntity = convertAdresseToEntity(updatedPersonne.getAdresse());
			adrEntity.setIdAdr(adrdao.getByValue(convertAdresseToEntity(personne.getAdresse())).get().getIdAdr());
			adrdao.update(adrEntity);
		} catch (NoSuchElementException e) {
			LOGGER.log(Level.INFO, "Element absent du carnet ...");
		}
	}
	
	private static AdresseEntity convertAdresseToEntity(Adresse adr) {
		AdresseEntity adrEntity = new AdresseEntity();
		adrEntity.setNumRue(adr.getNumRue());
		adrEntity.setNomRue(adr.getNomRue());
		adrEntity.setNomVille(adr.getNomVille());
		adrEntity.setCodePostal(adr.getCodePostal());
		return adrEntity;
	}
	
	private static PersonneEntity convertPersonneToEntity(Personne personne) {
		AdresseEntity adrEntity = convertAdresseToEntity(personne.getAdresse());
		IDao<AdresseEntity> adrdao = new AdressePoiDAO();
		adrEntity.setIdAdr(adrdao.getByValue(adrEntity).get().getIdAdr());
		
		PersonneEntity persEntity = new PersonneEntity();
		persEntity.setNomPers(personne.getNomPers());
		persEntity.setPrenomPers(personne.getPrenomPers());
		persEntity.setAdressePers_FK(adrEntity.getIdAdr());
		
		return persEntity;
	}
}
