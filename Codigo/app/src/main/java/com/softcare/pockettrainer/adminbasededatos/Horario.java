package com.softcare.pockettrainer.adminbasededatos;


public class Horario {

    private int idHorario;
    private String lunesHorarioDisponible;
    private String martesHorarioDisponible;
    private String miercolesHorarioDisponible;
    private String juevesHorarioDisponible;
    private String viernesHorarioDisponible;
    private String sabadoHorarioDisponible;
    private String domingoHorarioDisponible;

    public Horario(int idHorario, String lunesHorarioDisponible, String martesHorarioDisponible, String miercolesHorarioDisponible, String juevesHorarioDisponible, String viernesHorarioDisponible, String sabadoHorarioDisponible, String domingoHorarioDisponible) {
        this.idHorario = idHorario;
        this.lunesHorarioDisponible = lunesHorarioDisponible;
        this.martesHorarioDisponible = martesHorarioDisponible;
        this.miercolesHorarioDisponible = miercolesHorarioDisponible;
        this.juevesHorarioDisponible = juevesHorarioDisponible;
        this.viernesHorarioDisponible = viernesHorarioDisponible;
        this.sabadoHorarioDisponible = sabadoHorarioDisponible;
        this.domingoHorarioDisponible = domingoHorarioDisponible;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public String getLunesHorarioDisponible() {
        return lunesHorarioDisponible;
    }

    public void setLunesHorarioDisponible(String lunesHorarioDisponible) {
        this.lunesHorarioDisponible = lunesHorarioDisponible;
    }

    public String getMartesHorarioDisponible() {
        return martesHorarioDisponible;
    }

    public void setMartesHorarioDisponible(String martesHorarioDisponible) {
        this.martesHorarioDisponible = martesHorarioDisponible;
    }

    public String getMiercolesHorarioDisponible() {
        return miercolesHorarioDisponible;
    }

    public void setMiercolesHorarioDisponible(String miercolesHorarioDisponible) {
        this.miercolesHorarioDisponible = miercolesHorarioDisponible;
    }

    public String getJuevesHorarioDisponible() {
        return juevesHorarioDisponible;
    }

    public void setJuevesHorarioDisponible(String juevesHorarioDisponible) {
        this.juevesHorarioDisponible = juevesHorarioDisponible;
    }

    public String getViernesHorarioDisponible() {
        return viernesHorarioDisponible;
    }

    public void setViernesHorarioDisponible(String viernesHorarioDisponible) {
        this.viernesHorarioDisponible = viernesHorarioDisponible;
    }

    public String getSabadoHorarioDisponible() {
        return sabadoHorarioDisponible;
    }

    public void setSabadoHorarioDisponible(String sabadoHorarioDisponible) {
        this.sabadoHorarioDisponible = sabadoHorarioDisponible;
    }

    public String getDomingoHorarioDisponible() {
        return domingoHorarioDisponible;
    }

    public void setDomingoHorarioDisponible(String domingoHorarioDisponible) {
        this.domingoHorarioDisponible = domingoHorarioDisponible;
    }
}
