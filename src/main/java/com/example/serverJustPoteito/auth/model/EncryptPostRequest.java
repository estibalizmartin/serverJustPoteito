package com.example.serverJustPoteito.auth.model;

public class EncryptPostRequest {

    String clave;
    String mensaje;

    public EncryptPostRequest() {
    }

    public EncryptPostRequest(String clave, String mensaje) {
        this.clave = clave;
        this.mensaje = mensaje;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
