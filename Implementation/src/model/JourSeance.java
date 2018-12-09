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
    
    
	/**
	 * Construit une instance de JourSeance
	 * @param dateJour la date à laquelle la séance se produire
	 * @param capacite la capacité max de participants
	 * @param codeService le code du Service proposé lors de la séance
	 * @param numSeance le numéro de la séance dans la liste des séances du service
	 * @param codePro le code du professionnel proposant la séance
	 */
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
    
	/**
	 * @return la date de la séance
	 */
	public LocalDate getDateJour() {
		return this.dateJour;
	}
	
	/**
	 * @return le code de la séance
	 */
	public Long getCode() {
		return this.code;
	}
	
	 /**
	 * @param inscriptionSeance L'enregistrement à ajouter
	 * @throws TropParticipantsException si la capacité est atteinte
	 */
	public void addInscription(InscriptionSeance inscriptionSeance) throws TropParticipantsException{
	        if(this.listeInscriptionsSeance.size()<this.capacite){this.listeInscriptionsSeance.add(inscriptionSeance);}
	        else{throw new TropParticipantsException("La limite d'inscriptions est deja atteinte.");}
	    }

    /**
     * @return la liste des enregistrement d'inscriptions
     */
    public ArrayList<InscriptionSeance> getListeInscriptionsSeance() {
        return this.listeInscriptionsSeance;
    }

    /**
     * @param presenceSeance La confirmation de présence à ajouter
     */
    public void addPresence(PresenceSeance presenceSeance){
        this.listePresencesSeance.add(presenceSeance);
    }

    /**
     * @return La liste des confirmations de présence
     */
    public ArrayList<PresenceSeance> getListePresenceSeance() {
        return this.listePresencesSeance;
    }
    
    /**
     * @return la liste des inscriptions à la séance
     */
    public ArrayList<InscriptionSeance> getInscriptionSeance(){
    	return this.listeInscriptionsSeance;
    }
    

    /**
     * Vérifie si le membre est bien inscrit à la séance
     * @param numeroMembre Le numéro du membre à vérifier
     * @return true s'il est inscrit
     */
    public boolean membreInscrit(Long numeroMembre){
        return this.listeInscriptionsSeance.stream().anyMatch(inscription->inscription.getNumeroMembre().equals(numeroMembre));
    }

}
