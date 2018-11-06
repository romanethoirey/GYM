package model;

import exception.MauvaisFormatClientException;
import exception.MauvaisFormatMembreException;

public class Membre extends Client {

    public String status;

    public Membre(String numeroClient, String status) throws MauvaisFormatClientException {
            super(numeroClient);

        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
