package model;

import java.util.ArrayList;
import java.util.List;

public class Clients {

    private List<Client> listeClients;
    private Membres membres;
    private Professionnels professionnels;

    /**
     * @param membres
     * @param professionnels
     */
    public Clients(Membres membres, Professionnels professionnels) {
        this.membres = membres;
        this.professionnels = professionnels;
        this.listeClients = new ArrayList<Client>();

    }

    /**
     * @return
     */
    public List<Client> getListeClients() {
        this.listeClients.addAll(this.membres.getListeMembres());
        this.listeClients.addAll(this.professionnels.getListeProfessionnels());
        return listeClients;
    }

    /**
     * @param numeroClient
     * @return
     */
    public Client getClient(Long numeroClient){
        return this.getListeClients()//TODO solve bug ou les membres osnt ajoute plusieurs fois ..
                .stream()
                .filter(client -> client.getNumeroClient().equals(numeroClient))
                .findAny()
                .orElse(null);
    }
}
