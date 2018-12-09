package model;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    private List<Client> listeClients;
    private Membres membres;
    private Professionnels professionnels;

    /**
     * Crée une nouvelle liste de clients
     * @param membres La liste des membres
     * @param professionnels La listes des professionnels
     */
    public Clients(Membres membres, Professionnels professionnels) {
        this.membres = membres;
        this.professionnels = professionnels;
        this.listeClients = new ArrayList<Client>();

    }

    /**
     * @return La liste des clients
     */
    public List<Client> getListeClients() {
        this.listeClients.addAll(this.membres.getListeMembres());
        this.listeClients.addAll(this.professionnels.getListeProfessionnels());
        return listeClients;
    }

    /**
     * @param numeroClient Le numéro du client
     * @return l'objet Client correspondant
     */
    public Client getClient(Long numeroClient){
        return this.getListeClients()//TODO solve bug ou les membres osnt ajoute plusieurs fois ..
                .stream()
                .filter(client -> client.getNumeroClient().equals(numeroClient))
                .findAny()
                .orElse(null);
    }
    /**
     * @param email L'email du client
     * @return l'objet Client correspondant
     */
    public Client getClient(String email){
        return this.getListeClients()//TODO solve bug ou les membres osnt ajoute plusieurs fois ..
                .stream()
                .filter(client -> client.getMail().equals(email))
                .findAny()
                .orElse(null);
    }
}

