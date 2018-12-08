package model;

import java.time.LocalDate;
import java.util.ArrayList;

import exception.TropParticipantsException;

public class JourSeance {
	private LocalDate dateJour;
	private Long capacite;
	private Long code;
    private ArrayList<InscriptionSeance> listeInscriptionsSeance;
    private ArrayList<PresenceSeance> listePresencesSeance;
    
    
	public JourSeance(LocalDate dateJour, Long capacite, Long codeService, Long numSeance, Long codePro) {
		super();
		this.dateJour = dateJour;
		this.capacite = capacite;
		String numSeanceSt = Long.toString(numSeance);
		if(numSeanceSt.length()<2)
			numSeanceSt = '0'+numSeanceSt;
		String codeSt = Long.toString(codeService)+numSeanceSt+Long.toString(codePro);
		this.code = Long.parseLong(codeSt);
	}
    
	public LocalDate getDateJour() {
		return this.dateJour;
	}
	
	public Long getCode() {
		return this.code;
	}
	 public void addInscription(InscriptionSeance inscriptionSeance) throws TropParticipantsException{
	        if(this.listeInscriptionsSeance.size()<this.capacite){this.listeInscriptionsSeance.add(inscriptionSeance);}
	        else{throw new TropParticipantsException("La limite d'inscriptions est deja atteinte.");}
	    }

    public ArrayList<InscriptionSeance> getListeInscriptionsSeance() {
        return this.listeInscriptionsSeance;
    }

    public void addPresence(PresenceSeance presenceSeance){
        this.listePresencesSeance.add(presenceSeance);
    }

    public ArrayList<PresenceSeance> getListePresenceSeance() {
        return this.listePresencesSeance;
    }

    public boolean membreInscrit(Long numeroMembre){
        return this.listeInscriptionsSeance.stream().anyMatch(inscription->inscription.getNumeroMembre().equals(numeroMembre));
    }

}
