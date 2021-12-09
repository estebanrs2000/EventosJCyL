package com.example.protectodam;

public class Evento {

    private String titulo;
    private String OrgResp;
    private String fechaIni;
    private String horaIni;
    private String horaFin;
    private String lugar;
    private String coordenadas;
    private String localidad;
    private String precio;
    private String destinatarios;
    private String imagen;

    public Evento() {
    }

    public Evento(String titulo, String fecha, String localidad, String precio, String destinatarios, String imagenEvento) {
        this.titulo = titulo;
        this.fechaIni = fecha;
        this.localidad = localidad;
        this.precio = precio;
        this.destinatarios = destinatarios;
        this.imagen = imagenEvento;
    }

    public Evento(String titulo, String fecha, String localidad, String horaIni, String horaFin, String precio, String destinatarios, String lugar, String imagenEvento, String coordenadas) {
        this.titulo = titulo;
        this.fechaIni = fecha;
        this.localidad = localidad;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.precio = precio;
        this.destinatarios = destinatarios;
        this.lugar = lugar;
        this.imagen = imagenEvento;
        this.coordenadas = coordenadas;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getOrgResp() {
        return OrgResp;
    }

    public void setOrgResp(String orgResp) {
        OrgResp = orgResp;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(String destinatarios) {
        this.destinatarios = destinatarios;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return titulo;
    }

    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }
}
