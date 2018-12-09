package model;

import java.util.ArrayList;
import java.util.List;

public class Seances {

    private List<Seance> listeSeances;

    /**
     * Crée une nouvelle liste de Service
     */
    public Seances() {
        this.listeSeances = new ArrayList<Seance>();
    }

    /**
     * @return retourne la liste de Service
     */
    public List<Seance> getListeSeances() {
        return listeSeances;
    }

    /**
     * Ajoute un nouveau Service à la liste
     * @param seance le Service à ajouter
     */
    public void addListeSeances(Seance seance) {
        listeSeances.add(seance);
    }

    /**
     * Retourne la séance de code numeroSeance
     * @param numeroSeance Le code de la séance à chercher
     * @return l'objet Seance correspondant
     */
    public Seance getSceance(long numeroSeance){
        return this.listeSeances
                .stream()
                .filter(sceance -> sceance.getCode().equals(numeroSeance))
                .findAny()
                .orElse(null);
    }
}