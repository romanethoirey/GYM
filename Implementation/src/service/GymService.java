package service;

import model.Client;
import model.Seance;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class GymService {

    public final static Integer NOMBRE_CAS_DUTILISATION=9;//+1, pour quitter le logiciel //TODO sortir constantes, les mettre dans un fichier de constantes de systeme
    public final static String TITRE_FICHIER_TEF = "GYM_fichier_TEF";
    public final static String TITRE_RAPPORT_SYNTHESE = "GYM_Rapport_Synthese";

    public enum Status{Suspendu, Valide, Inexistant}//Status possible d'un membre

    //////////INPUT//////////

    /**
     * @return
     */
    public Integer intUserInput(){
            Scanner sc = new Scanner(System.in);
            try{
                return sc.nextInt();
            }catch (InputMismatchException e){
                return -1;
            }
    }

    /**
     * @return
     */
    public String stringUserInput(){
        Scanner sc = new Scanner(System.in); // scanner pour userinput
        try{
            return sc.nextLine();
        }catch (InputMismatchException e){//TODO Faire le bon exception handling
            return "";
        }
    }

    /**
     * @param attribut
     * @return
     */
    public String informationPersonnellesInput(String attribut){
        while(true){//boucle jusqu'a entree valide
            printInformationPersonnellesInput(attribut);
            String input = stringUserInput();
            if(attribut.equals("email")&& !isValid(input) ) {printEntreeErronee();}else
            {if( !input.isEmpty() || attribut.equals("commentaire")){
            	return input;//dans le range des cas d'utilisation
                }else printEntreeErronee();
            }
        }
    }
    String DateDebut="";
    String DateDefin="";
    /**
     * @param attribut
     * @return
     */
    public String informationSeanceInput(String attribut) {
        while(true){//boucle jusqu'a entree valide
            printSeanceInput(attribut);
            String input = stringUserInput();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            
            if(attribut.equals("date de debut (JJ-MM-AAAA)") ) DateDebut=input;
           
            
            try {
            	if(attribut.equals("code de la séance de 3 chiffres")&& !(VerifCode(input))) {System.out.println("verifier le code");}else
				if(attribut.equals("date de fin (JJ-MM-AAAA)")&& !((formatter.parse(DateDebut).compareTo(formatter.parse(input)))<0)) {System.out.println("verifier la date");}else
				{if( !input.isEmpty() || attribut.equals("commentaire")){
					return input;//dans le range des cas d'utilisation
				    }else printEntreeErronee();
      }
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				printEntreeErronee();
			}}
    }
    /**
     * @param s
     * @return
     */
    public boolean VerifCode(String s){
    	try {
			Integer.parseInt(s);
		} catch (NumberFormatException e){
			return false;
		}
 
		return (s.length()==3);
    	
    }
    
    /**
     * @param max
     * @return
     */
    public Integer menuUserInput(Integer max){
        while(true){//boucle jusqu'a entree valide
            printSelectionMenu();
            Integer input = intUserInput();
            if( 0 < input && input <= max ){return input;}//dans le range des cas d'utilisation
            printEntreeErronee();
        }
    }

    /**
     * @return
     */
    public String yesNoInput(){
        while(true){//boucle jusqu'a entree valide
            String input = stringUserInput();
            if( input.equals("y") || input.equals("n") ){return input;}//dans le range des cas d'utilisation
            printEntreeErronee();
        }
    }

    //////////PRINT//////////

    public void printMenuPrincipal() {
        System.out.println("" +
                "1) Création un nouveau compte au gym.\n" +
                "2) Connexion.\n" +
                "3) Gestion.\n\n" +
                "4) Déconnexion du logiciel.\n");
    }
    
    public void printMenuMembre() {
    	System.out.println(""+
    			"1) Accès au Gym.\n"+
    			"2) Paiement des frais d'adhésion.\n"+
    			"3) Inscription à une séance.\n"+
    			"4) Présentation de son QR code pour confirmation à une séance.\n\n"+
    			"5) Déconnexion");
    }
    
    public void printMenuProfessionnel() {
    	System.out.println(""+
    			"1) Consultation des incrits.\n"+
    			"2) Création un service.\n"+
    			"3) Confirmation présence des clients à une séance.\n\n"+
    			"4) Déconnexion");
    }
    
    public void printMenuGestion() {
    	System.out.println(""+
    			"1) Création du TEF.\n"+
    			"2) Création du rapport de synthèse.\n\n"+
    			"3) Déconnexion");
    }
    
    public void printDeconnexion() {
    	System.out.println("\nEtes vous sure de vouloir vous déconnectez ? (y/n)");
    }

    public void printTypeClient() {
        System.out.println("" +
                "Vous êtes un :\n"+
                "1) Un membre.\n" +
                "2) Un professionnel.\n");
    }
    
    public void printNomCodeClient(Client c) {
    	System.out.println("Bonjour, "+ c.getPrenom()+" "+c.getNom()+" "+c.getNumeroClient()+'\n');
    }

    public void printEntreeErronee(){
        System.out.println("\nEntree Erronee."); 
    }

    public void printMembreNonInscrit(){
        System.out.println("\nLe membre n'est pas inscrit a cette seance, acces refuse."); 
    }

    public void printOperationAnnule(){
        System.out.println("\nOperation Annule."); 
    }

    public void printOperationComplete(){
        System.out.println("\nOperation Complete."); 
    }

    public void printPaiementInput(){
        System.out.println("\nPaiement complete ? (y/n)");
    }

    public void printChoixInscription(){
        System.out.println("\nLe client veut-il s'inscrire ? (y/n)");
    }

    public void printContinuerOuFin(){
        System.out.println("\nVoulez vous retourner au menu principal (sinon, la session sera terminee) ? (y/n)");
    }

    public void printMockData(){
        System.out.println("\nVoulez vous initialiser le logiciel en mode demo (creation de mock data) ? (y/n)");
    }

    public void printOuvertureTourniquet(){
        System.out.println("\nVeuillez ouvrir le tourniquet.");
    }

    public void printAucuneSeances(){
        System.out.println("\nAucunes seances.");
    }

    private void printSelectionMenu(){ System.out.println("\nVeuiller selectionner le numero d'une des options ci-dessus.");}

    private void printInformationPersonnellesInput(String attribut){System.out.println("\nVeuiller entrer le " + attribut + " du client.");}

    public void printNumeroNouceauClient(Long attribut){System.out.println("\nLe numero d'identification du client est :  " + attribut+ ".");}

    public void printInfosSeance(Seance seance){System.out.println("\n"+seance.toString());}

    public void printSeanceInput(String attribut){System.out.println("\nVeuiller entrer le " + attribut + " de la seance.");}

    public void printFraisSeance(Double attribut){System.out.println("\nLe prix de la Seance est de " + attribut + " $.");}

    public void printStatusClient(Status status){System.out.println("\nLe client avec le numero d'identification entre est " + status.toString() );}

    public void printInfosInscriptionsSeance(Seance seance){
        System.out.println("Titre : "+seance.getTitre());
        seance.getListeInscriptionsSeance().stream().forEach(inscription ->System.out.println(inscription.toString()));
    }

    //////////AUTRES//////////

    /**
     * @param n
     * @return
     */
    public Long randomLongLengthN(int n){
        return (long)(Math.random() * (long) Math.pow(10, n - 1) * 9) + (long) Math.pow(10, n - 1);
    }

    /**
     * @param string
     * @return
     */
    public ArrayList<Boolean> boolStringToArray(String string){
        ArrayList<Boolean> boolArray = new ArrayList<Boolean>();

        for(int i=0; i<string.length();i++){
            if(string.charAt(i) == '0'){
                boolArray.add(false);
            }else if(string.charAt(i) == '1'){
                boolArray.add(true);
            }
        }
        return boolArray;
    }

    /**
     * @param titre
     * @param contenu
     */
    public void creationFichierTexte(String titre, List<String> contenu){
        try {
            Path file = Paths.get(titre + ".txt");
            Files.write(file, contenu, Charset.forName("UTF-8"));
        }catch(java.io.IOException e){
            printOperationAnnule();
            System.out.println(e.getStackTrace());
        }
    }

    /**
     * @param valeur
     * @return
     */
    public Double arrondirDoubleDeuxDecimals(Double valeur){
       return Math.floor(valeur * 100) / 100;
    }
    
    /**
     * @param email
     * @return
     */
    public static boolean isValid(String email) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    } 
    
    /**
     * @param currentDate
     * @return
     */
    public LocalDate getFirstDayofWeek(LocalDate currentDate) {
    	return currentDate.plusDays(currentDate.getDayOfWeek().compareTo(DayOfWeek.SUNDAY)-1);
    }

}


