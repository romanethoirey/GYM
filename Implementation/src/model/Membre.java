package model;

import exception.MauvaisFormatClientException;

public class Membre extends Client {

    public Integer status;

    public Membre(String numeroClient, String status) throws MauvaisFormatClientException {
            super(numeroClient);
        this.status = Integer.getInteger(status);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStringStatus(String status) {
        this.status = Integer.getInteger(status);
    }
}
