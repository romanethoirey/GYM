package model;

import java.util.ArrayList;
import java.util.List;

public class Professionnels {
    private List<Professionnel> listeProfessionnels;

    /**
     * Crée une nouvelle liste de pro
     */
    public Professionnels() {
        this.listeProfessionnels = new ArrayList<Professionnel>();
    }

    /**
     * @return la liste des professionnels de GYM
     */
    public List<Professionnel> getListeProfessionnels() {
        return listeProfessionnels;
    }

    /**
     * Ajoute un professionnel à la liste
     * @param professionnel Le professionnel à ajouter
     */
    public void addListeProfessionnels(Professionnel professionnel) {
        listeProfessionnels.add(professionnel);
    }

    /**
     * Retour le professionnel dont le numéro est passé en paramètre
     * @param numeroProfessionnel Le numéro du professionnel à chercher
     * @return L'objet professionnel correspondant
     */
    public Professionnel getProfessionnel(Long numeroProfessionnel){
        return listeProfessionnels
                .stream()
                .filter(professionnel -> professionnel.getNumeroClient().equals(numeroProfessionnel))
                .findAny()
                .orElse(null);
    }
}