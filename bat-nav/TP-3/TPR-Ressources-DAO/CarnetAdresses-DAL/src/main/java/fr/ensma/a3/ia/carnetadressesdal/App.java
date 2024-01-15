package fr.ensma.a3.ia.carnetadressesdal;

import java.util.List;
import java.util.Optional;

import fr.ensma.a3.ia.carnetadressesdal.dao.IDao;
import fr.ensma.a3.ia.carnetadressesdal.dao.entity.AdresseEntity;
import fr.ensma.a3.ia.carnetadressesdal.dao.entity.PersonneEntity;
import fr.ensma.a3.ia.carnetadressesdal.dao.poi.AdressePoiDAO;
import fr.ensma.a3.ia.carnetadressesdal.dao.poi.PersonnePoiDAO;

/**
 * Test Dao
 *
 * @author Mikky Richard
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Éléments dans la base :");
        IDao<AdresseEntity> adrdao = new AdressePoiDAO();
        List<AdresseEntity> alladr = adrdao.getAll();
        for (AdresseEntity ad : alladr) {
            System.out.println(ad);
        }
        AdresseEntity adcherche = new AdresseEntity();
        adcherche.setNumRue(12);
        adcherche.setNomRue("rue toulent");
        adcherche.setCodePostal(91000);
        adcherche.setNomVille("JavaLand");
        System.out.println("Recherche par valeur de l'élément : " + adcherche);
        Optional<AdresseEntity> res = adrdao.getByValue(adcherche);
        if (res.isPresent()) {
            System.out.println(res.get());
        }
        System.out.println("Recherche par id de l'élément : id=1 ");
        res = adrdao.getById(1);
        if (res.isPresent()) {
            System.out.println(res.get());
        }
        AdresseEntity adrajout = new AdresseEntity();
        adrajout.setNumRue(10);
        adrajout.setNomRue("toumoche");
        adrajout.setCodePostal(97000);
        adrajout.setNomVille("PitonVille");
        System.out.println("Création de l'élément : " + adrajout);
        adrdao.create(adrajout);
        adrajout.setNomVille("PythonVille");
        System.out.println("Update du nom de la ville");
        adrdao.update(adrajout);
        System.out.println("Éléments dans la base :");
        alladr = adrdao.getAll();
        for (AdresseEntity ad : alladr) {
            System.out.println(ad);
        }
        System.out.println("Ajout d'un élément déjà en base :");
        adrdao.create(adrajout);
        System.out.println("Suppression de l'élément id = 3");
        adrdao.delete(adrdao.getById(3).get());
        System.out.println("Éléments dans la base :");
        alladr = adrdao.getAll();
        for (AdresseEntity ad : alladr) {
            System.out.println(ad);
        }
        
        System.out.println("\nÉléments dans la base :");
        IDao<PersonneEntity> persdao = new PersonnePoiDAO();
        List<PersonneEntity> allpers = persdao.getAll();
        for (PersonneEntity pers : allpers) {
            System.out.println(pers);
        }
        /*
        AdresseEntity adcherche = new AdresseEntity();
        adcherche.setNumRue(12);
        adcherche.setNomRue("rue toulent");
        adcherche.setCodePostal(91000);
        adcherche.setNomVille("JavaLand");
        System.out.println("Recherche par valeur de l'élément : " + adcherche);
        Optional<AdresseEntity> res = adrdao.getByValue(adcherche);
        if (res.isPresent()) {
            System.out.println(res.get());
        }
        System.out.println("Recherche par id de l'élément : id=1 ");
        res = adrdao.getById(1);
        if (res.isPresent()) {
            System.out.println(res.get());
        }
        AdresseEntity adrajout = new AdresseEntity();
        adrajout.setNumRue(10);
        adrajout.setNomRue("toumoche");
        adrajout.setCodePostal(97000);
        adrajout.setNomVille("PitonVille");
        System.out.println("Création de l'élément : " + adrajout);
        adrdao.create(adrajout);
        adrajout.setNomVille("PythonVille");
        System.out.println("Update du nom de la ville");
        adrdao.update(adrajout);
        System.out.println("Éléments dans la base :");
        alladr = adrdao.getAll();
        for (AdresseEntity ad : alladr) {
            System.out.println(ad);
        }
        System.out.println("Ajout d'un élément déjà en base :");
        adrdao.create(adrajout);
        System.out.println("Suppression de l'élément id = 3");
        adrdao.delete(adrdao.getById(3).get());
        System.out.println("Éléments dans la base :");
        alladr = adrdao.getAll();
        for (AdresseEntity ad : alladr) {
            System.out.println(ad);
        }*/
    }
}
