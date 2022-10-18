package com.softcare.pockettrainer.adminbasededatos;

public class RutinaProgramada {
    private int id_rutina_programada,
    id_rutina_lunes,
    id_rutina_martes,
    id_rutina_miercoles,
    id_rutina_jueves,
    id_rutina_viernes,
    id_rutina_sabado,
    id_rutina_domingo;

    public RutinaProgramada(int id_rutina_programada, int id_rutina_lunes, int id_rutina_martes, int id_rutina_miercoles, int id_rutina_jueves, int id_rutina_viernes, int id_rutina_sabado, int id_rutina_domingo) {
        this.id_rutina_programada = id_rutina_programada;
        this.id_rutina_lunes = id_rutina_lunes;
        this.id_rutina_martes = id_rutina_martes;
        this.id_rutina_miercoles = id_rutina_miercoles;
        this.id_rutina_jueves = id_rutina_jueves;
        this.id_rutina_viernes = id_rutina_viernes;
        this.id_rutina_sabado = id_rutina_sabado;
        this.id_rutina_domingo = id_rutina_domingo;
    }

    public int getId_rutina_programada() {
        return id_rutina_programada;
    }

    public void setId_rutina_programada(int id_rutina_programada) {
        this.id_rutina_programada = id_rutina_programada;
    }

    public int getId_rutina_lunes() {
        return id_rutina_lunes;
    }

    public void setId_rutina_lunes(int id_rutina_lunes) {
        this.id_rutina_lunes = id_rutina_lunes;
    }

    public int getId_rutina_martes() {
        return id_rutina_martes;
    }

    public void setId_rutina_martes(int id_rutina_martes) {
        this.id_rutina_martes = id_rutina_martes;
    }

    public int getId_rutina_miercoles() {
        return id_rutina_miercoles;
    }

    public void setId_rutina_miercoles(int id_rutina_miercoles) {
        this.id_rutina_miercoles = id_rutina_miercoles;
    }

    public int getId_rutina_jueves() {
        return id_rutina_jueves;
    }

    public void setId_rutina_jueves(int id_rutina_jueves) {
        this.id_rutina_jueves = id_rutina_jueves;
    }

    public int getId_rutina_viernes() {
        return id_rutina_viernes;
    }

    public void setId_rutina_viernes(int id_rutina_viernes) {
        this.id_rutina_viernes = id_rutina_viernes;
    }

    public int getId_rutina_sabado() {
        return id_rutina_sabado;
    }

    public void setId_rutina_sabado(int id_rutina_sabado) {
        this.id_rutina_sabado = id_rutina_sabado;
    }

    public int getId_rutina_domingo() {
        return id_rutina_domingo;
    }

    public void setId_rutina_domingo(int id_rutina_domingo) {
        this.id_rutina_domingo = id_rutina_domingo;
    }
}
