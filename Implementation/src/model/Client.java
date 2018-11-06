package model;

import exception.MauvaisFormatClientException;

public abstract class Client {//TODO informations personnelles

    private Integer numero;// unique a chaque Client

    public Client(String numero) throws MauvaisFormatClientException {
        if(numero.length() != 9){throw new MauvaisFormatClientException("Numero de client de mauvaise longueur"); }
        this.numero = Integer.getInteger(numero);
    }

    public Integer getNumeroClient() {
        return this.numero;
    }
}
