package com.softcare.pockettrainer.adminbasededatos;

import java.util.Date;

public class Horario {

    private int idHorario;
    private Date lunesHorarioDisponible;
    private Date martesHorarioDisponible;
    private Date miercolesHorarioDisponible;
    private Date juevesHorarioDisponible;
    private Date viernesHorarioDisponible;
    private Date sabadoHorarioDisponible;
    private Date domingoHorarioDisponible;

    public Horario(int idHorario, Date lunesHorarioDisponible, Date martesHorarioDisponible, Date miercolesHorarioDisponible, Date juevesHorarioDisponible, Date viernesHorarioDisponible, Date sabadoHorarioDisponible, Date domingoHorarioDisponible) {
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

    public Date getLunesHorarioDisponible() {
        return lunesHorarioDisponible;
    }

    public void setLunesHorarioDisponible(Date lunesHorarioDisponible) {
        this.lunesHorarioDisponible = lunesHorarioDisponible;
    }

    public Date getMartesHorarioDisponible() {
        return martesHorarioDisponible;
    }

    public void setMartesHorarioDisponible(Date martesHorarioDisponible) {
        this.martesHorarioDisponible = martesHorarioDisponible;
    }

    public Date getMiercolesHorarioDisponible() {
        return miercolesHorarioDisponible;
    }

    public void setMiercolesHorarioDisponible(Date miercolesHorarioDisponible) {
        this.miercolesHorarioDisponible = miercolesHorarioDisponible;
    }

    public Date getJuevesHorarioDisponible() {
        return juevesHorarioDisponible;
    }

    public void setJuevesHorarioDisponible(Date juevesHorarioDisponible) {
        this.juevesHorarioDisponible = juevesHorarioDisponible;
    }

    public Date getViernesHorarioDisponible() {
        return viernesHorarioDisponible;
    }

    public void setViernesHorarioDisponible(Date viernesHorarioDisponible) {
        this.viernesHorarioDisponible = viernesHorarioDisponible;
    }

    public Date getSabadoHorarioDisponible() {
        return sabadoHorarioDisponible;
    }

    public void setSabadoHorarioDisponible(Date sabadoHorarioDisponible) {
        this.sabadoHorarioDisponible = sabadoHorarioDisponible;
    }

    public Date getDomingoHorarioDisponible() {
        return domingoHorarioDisponible;
    }

    public void setDomingoHorarioDisponible(Date domingoHorarioDisponible) {
        this.domingoHorarioDisponible = domingoHorarioDisponible;
    }
}
