package model;

import java.util.ArrayList;
import java.util.List;

public class Membres {

    private List<Membre> listeMembres;

    /**
     * Crée une nouvelle liste de membre
     */
    public Membres() {
        this.listeMembres = new ArrayList<Membre>();
    }

    /**
     * @return la liste des membres
     */
    public List<Membre> getListeMembres() {
        return listeMembres;
    }

    /**
     * Ajoute un nouveau membre à la liste
     * @param Membre le membre à ajouter
     */
    public void addListeMembres(Membre Membre) {
        listeMembres.add(Membre);
    }

    /**
     * Retourne un membre en fonction de son numéro 
     * @param numeroMembre Le numéro à chercher
     * @return L'objet membre correspondant
     */
    public Membre getMembre(Long numeroMembre){
        return listeMembres
                .stream()
                .filter(membre -> membre.getNumeroClient().equals(numeroMembre))
                .findAny()
                .orElse(null);
    }


}