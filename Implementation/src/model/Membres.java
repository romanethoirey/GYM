package model;

import java.util.ArrayList;
import java.util.List;

public class Membres {

    private List<Membre> listeMembres;

    /**
     * 
     */
    public Membres() {
        this.listeMembres = new ArrayList<Membre>();
    }

    /**
     * @return
     */
    public List<Membre> getListeMembres() {
        return listeMembres;
    }

    /**
     * @param Membre
     */
    public void addListeMembres(Membre Membre) {
        listeMembres.add(Membre);
    }

    /**
     * @param numeroMembre
     * @return
     */
    public Membre getMembre(Long numeroMembre){
        return listeMembres
                .stream()
                .filter(membre -> membre.getNumeroClient().equals(numeroMembre))
                .findAny()
                .orElse(null);
    }


}