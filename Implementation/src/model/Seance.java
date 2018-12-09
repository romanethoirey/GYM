package model;

import exception.MauvaisFormatSeanceException;
import exception.TropParticipantsException;
import service.GymService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Seance {
    private String titre;
    private LocalDateTime heureCreation;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private LocalTime heureSeance;
    private ArrayList<Boolean> recurHebdo;//liste de 7 elements, dimanche a samedi, 0 ou 1 s'il y a cours
    private Long capacite;
    private Long numeroProfessionnel;
    private Long code;
    private Double frais;//TODO couper le signe de $ a la fin du String
    private String commentaire;
    private ArrayList<JourSeance> listeJourSeance;

    /**
     * Crée un nouveau Service
     * @param titreSeance Le titre du Service
     * @param code Le code (3 chiffres) du Service
     * @param dateDebut La première date ou le service est proposé
     * @param dateFin La dernière date ou le service est proposé
     * @param heureSeance L'heure à laquelle le service est proposé
     * @param recurHebdo La récurrence hebdomadaire du service
     * @param capacite La capacité du service
     * @param numeroProfessionnel Le numéro du professionnel proposant le service
     * @param frais Les frais liés au service
     * @param commentaire divers commentaires
     * @param gymService Objet permettant d'avoir accès aux méthodes de services
     * @param seances La liste des Service à laquelle dont l'instance fait partie
     * @throws MauvaisFormatSeanceException si mauvais format
     */
    public Seance(String titreSeance, String code,String dateDebut, String dateFin, String heureSeance, ArrayList<Boolean> recurHebdo, String capacite, Long numeroProfessionnel, String frais, String commentaire, GymService gymService, Seances seances) throws MauvaisFormatSeanceException{
        this.titre = titreSeance;
        this.heureCreation = LocalDateTime.now();
        this.dateDebut = LocalDate.parse(dateDebut, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.dateFin = LocalDate.parse(dateFin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.heureSeance = LocalTime.parse(heureSeance, DateTimeFormatter.ofPattern("HH:mm"));
        

        if(recurHebdo.size() != 7 || // Si pas exactement 7 jours
           Integer.parseInt(capacite) > 30 || // si capacite de plus de 30 membres
           Float.valueOf(frais) > 100.00 || Float.valueOf(frais) < 0 ||//si valeur trop grande ou sous 0
           commentaire.length() > 100// si longueur du commentaire plus de 100
        ){throw new MauvaisFormatSeanceException("Mauvais format.");}//TODO message plus descriptif

        while(true){
            this.code = Long.parseLong(code);
            if(seances
                    .getListeSeances()
                    .stream()
                    .filter(seance->seance.code.equals(this.code)).count() == 0){ break;}//verification que le numero genere n'existe pas
        }
        this.recurHebdo = recurHebdo;
        this.capacite = Long.parseLong(capacite);
        this.numeroProfessionnel = numeroProfessionnel;
        this.frais = Double.parseDouble(frais);
        this.commentaire = commentaire;
        this.listeJourSeance = new ArrayList<JourSeance>();
        genererJour(gymService);
    }
    
    /**
     * Génère toutes les séances (JourSeance) en fonction des dates de début, de fin, et de la récurrence
     * @param gymService L'objet permettant d'avoir accès aux méthodes de service
     */
    private void genererJour(GymService gymService) {
    	LocalDate weekcounter = gymService.getFirstDayofWeek(this.dateDebut);
    	List<Integer> recurInt = getrecurInt();
    	LocalDate currentDate = weekcounter;
    	long count = 0;
    	while (currentDate.isBefore(this.dateFin) && count < 100){
	    	for(int i = 0; i < recurInt.size() && currentDate.isBefore(this.dateFin) && count < 100 ; i++) {
	    		//System.out.println("Date courante : " + currentDate + "+ " + recurInt.get(i)+ "  weekcounter : "+ weekcounter);
	    		currentDate = currentDate.plusDays((long)recurInt.get(i));
	    		if(currentDate.isAfter(this.dateDebut) && currentDate.isBefore(this.dateFin)) {
		    		String numProSt = Long.toString(this.numeroProfessionnel);
		    		numProSt = numProSt.substring(numProSt.length()-2, numProSt.length());
		    		JourSeance nouveauJour = new JourSeance(currentDate,this.capacite,this.code,count,Long.parseLong(numProSt));
		    		listeJourSeance.add(nouveauJour);
		    		count++;
	    		}
	    		currentDate = weekcounter;
	    	}
	    	weekcounter = weekcounter.plusDays(7);
    		currentDate = weekcounter;
    	}
    	if(count == 100)
    		System.out.println("Toutes les séances n'ont pas pu être créées (+ de 99), la dernière date disponible est : " + currentDate);
    }

    /**
     * @return Le code du Service
     */
    public Long getCode() {
        return this.code;
    }

    /**
     * @return Le titre du Service
     */
    public String getTitre() {
        return this.titre;
    }

    /**
     * @return Les frais liés au Service
     */
    public Double getFrais() {
        return this.frais;
    }

    /**
     * @return Le numéro du professionnel proposant le Service
     */
    public Long getNumeroProfessionnel() {
        return numeroProfessionnel;
    }

    /**
     * @return La liste de toutes les inscriptions à toutes les séances du Service
     */
    public ArrayList<InscriptionSeance> getListeInscriptionsSeance() {
    	ArrayList<InscriptionSeance> rep = new ArrayList<InscriptionSeance>();
    	ArrayList<InscriptionSeance> current = new ArrayList<InscriptionSeance>();
    	for(int i = 0; i < listeJourSeance.size(); i++) {
    		current = listeJourSeance.get(i).getInscriptionSeance();
    		for(int j = 0; j < current.size(); j++) {
    			rep.add(current.get(j));
    		}
    	}
        return rep;
    }
    
    /**
     * Crée une chaine de caractère représentant les différents jours ou sont données le Service
     * @param recurHebdo la récurrence hebdomadaire (Array de boolean)
     * @return La chaine de caractère représentant les différents jours ou sont données le Service
     */
    public String affichageJour(ArrayList<Boolean> recurHebdo) {
    	String list = "";
    	for(int i=0;i<recurHebdo.size();i++) {
    		if(recurHebdo.get(0)==true && list.contains("Dimanche")==false) {
    			list+="Dimanche, ";
    		}
    		if(recurHebdo.get(1)==true && list.contains("Lundi")==false) {
    			list+="Lundi, ";
    		}
    		if(recurHebdo.get(2)==true && list.contains("Mardi")==false) {
    			list+="Mardi, ";
    		}
    		if(recurHebdo.get(3)==true && list.contains("Mercredi")==false) {
    			list+="Mercredi, ";
    		}
    		if(recurHebdo.get(4)==true && list.contains("Jeudi")==false) {
    			list+="Jeudi, ";
    		}
    		if(recurHebdo.get(5)==true && list.contains("Vendredi")==false) {
    			list+="Vendredi, ";
    		}
    		if(recurHebdo.get(6)==true && list.contains("Samedi")==false) {
    			list+="Samedi ";
    		}
    	}
    	return list;
    }
    /**
     * @return La date de début du Service
     */
    public LocalDate getDateDebut() {
    	return this.dateDebut;
    }
    /**
     * @return La date de fin du Service
     */
    public LocalDate getDatefin() {
    	return this.dateFin;
    }
    
    /**
     * Crée un tableau d'entier représentant les jours ou il a récurrence (ex : mardi, jeudi = [2,4])
     * @return le tableau d'entier
     */
    public List<Integer> getrecurInt(){
    	List<Integer> rep = new ArrayList<Integer>();
    	for(int i = 0; i < 7; i ++) {
    		if(this.recurHebdo.get(i) == true)
    			rep.add(i);
    	}
    	return rep;
    }
    
    /**
     * @return La liste des séances proposées pour ce service
     */
    public List<JourSeance> getJourSeance(){
    	return this.listeJourSeance;
    }
    
    //affichage les 10 prochaines séances
    /**
     * Affiche les 10 prochaines séances
     * @param listeJour La liste des séances à traiter
     * @return Les 10 prochaines séances en fonction de la date actuelle
     */
    public List<String> affichageCodeSeance(List<JourSeance> listeJour) {
    	List<String> listeCode= new ArrayList<String>();
    	LocalDate datecourrante = LocalDate.now();
    	for(int i=0;i<listeJour.size();i++) {
    		System.out.println();
    		if(listeJour.get(i).getDateJour().isAfter(datecourrante)) {
    			listeCode.add("\n  Seance numéro :"+listeJour.get(i).getCode()
						+" du "+listeJour.get(i).getDateJour());
    			
    			if(listeCode.size()==10) {
    				return listeCode;
    			}	
    		}
    	}
    	listeCode.add("Il n'y a plus de séances disponibles");
    	return listeCode;
    }
    
    /**
     * @param i l'index de la séance
     * @return l'objet JourSéance représentant une séance proposé pour le Service
     */
    public JourSeance getJourSeance(int i) {
    	return listeJourSeance.get(i);
    }
    
    @Override
    public String toString() {
        return "" +
               titre + " "+code+'\n'+
                "  heureCreation: " + heureCreation +
                ", dateDebut: " + dateDebut +
                ", dateFin: " + dateFin +
                ", heureSeance: " + heureSeance + ",\n" +
                "  Récurrence: " + affichageJour(recurHebdo) + '\n' +
                "  Code des séances de la semaine:"+'\n'+
                "  "+affichageCodeSeance(listeJourSeance)+'\n'+
                "  capacite: " + capacite + '\n' +
                "  numeroProfessionnel: " + numeroProfessionnel + '\n' +
                "  frais: " + frais + "$\n" +
                "  Commentaire: " + commentaire + '\n';
    }
}
