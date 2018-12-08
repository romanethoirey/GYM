package model;

import java.util.ArrayList;
import java.util.List;

public class Professionnels {
    private List<Professionnel> listeProfessionnels;

    /**
     * 
     */
    public Professionnels() {
        this.listeProfessionnels = new ArrayList<Professionnel>();
    }

    /**
     * @return
     */
    public List<Professionnel> getListeProfessionnels() {
        return listeProfessionnels;
    }

    /**
     * @param professionnel
     */
    public void addListeProfessionnels(Professionnel professionnel) {
        listeProfessionnels.add(professionnel);
    }

    /**
     * @param numeroProfessionnel
     * @return
     */
    public Professionnel getProfessionnel(Long numeroProfessionnel){
        return listeProfessionnels
                .stream()
                .filter(professionnel -> professionnel.getNumeroClient().equals(numeroProfessionnel))
                .findAny()
                .orElse(null);
    }
}