package model;

import java.time.LocalDate;
import java.util.ArrayList;

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
    
	
}
