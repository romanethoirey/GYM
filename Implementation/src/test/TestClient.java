package test;//test

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import org.junit.Test;

import exception.MauvaisFormatSeanceException;
import org.junit.Assert;
import model.Membre;
import model.Membres;
import model.Professionnel;
import model.Professionnels;
import model.Seance;
import model.Seances;
import model.Clients;
import service.GymService;


public class TestClient {
	  GymService gymService = new GymService();
	    Membres membres = new Membres();
	    Professionnels professionnels = new Professionnels();
	    Seances seances = new Seances();
	    Clients clients = new Clients(membres, professionnels);
		@SuppressWarnings("deprecation")
		
		@Test
		public void testNomMembre() throws MauvaisFormatSeanceException {
			Membre membre =new Membre("marc", "DufresneGagnon", "marc-antoine.dufresne.gagnon@umontreal.ca", GymService.Status.Valide, gymService, clients);
            
			Assert.assertTrue(membre.getNom().matches("[a-zA-Z]+"));// alors que ca doit pas etre valide
			
			
		}
		@Test
		public void testMailMembre() throws MauvaisFormatSeanceException {
			Membre membre =new Membre("marc", "DufresneGagnon", "marc-antoine.dufresne.gagnon@umontreal.ca", GymService.Status.Valide, gymService, clients);
            
			Assert.assertTrue(isValid(membre.getMail()));// alors que ca doit pas etre valide
			
			
		}
		
		
		
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
		@Test
		public void testPro()  {
			
            Professionnel pro = new Professionnel("Maxime", "Daigle", "maxime.daigle@umontreal.ca",GymService.Status.Valide,gymService, clients);
           
			
			Assert.assertTrue(pro.getStatus().equals(GymService.Status.Valide));// alors que ca doit pas etre valide
			
			
		}
}
