package fr.ensma.a3.ia.carnetadressesbusiness.carnet;

public class Personne {

	private String nomPers;
    private String prenomPers;
    private Adresse adresse;
	
	public Personne(String nomPers, String prenomPers, Adresse adresse) {
		this.nomPers = nomPers;
		this.prenomPers = prenomPers;
		this.adresse = adresse;
	}
	
	public String getNomPers() {
		return nomPers;
	}

	public void setNomPers(final String nomPers) {
		this.nomPers = nomPers;
	}

	public String getPrenomPers() {
		return prenomPers;
	}

	public void setPrenomPers(final String prenomPers) {
		this.prenomPers = prenomPers;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(final Adresse adr) {
		this.adresse = adr;
	}
    

    @Override
    public String toString() {
        return nomPers + " " + prenomPers + " - " + adresse.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Personne)) {
            return false;
        } else {
        	Personne pers = (Personne) obj;
            if ((pers.getNomPers().compareTo(nomPers) == 0)
            		&& (pers.getPrenomPers().compareTo(prenomPers) == 0)
                    && (pers.getAdresse().equals(adresse))) {
                return true;
            } else {
                return false;
            }
        }
    }
}
