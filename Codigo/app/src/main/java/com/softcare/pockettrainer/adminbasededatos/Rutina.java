package com.softcare.pockettrainer.adminbasededatos;

public class Rutina {

    private int idRutina;
    private String parteCuerpo;
    private boolean completada;
    private int puntosEXP;

    public Rutina(int idRutina, String parteCuerpo, boolean completada, int puntosEXP) {
        this.idRutina = idRutina;
        this.parteCuerpo = parteCuerpo;
        this.completada = completada;
        this.puntosEXP = puntosEXP;
    }

    public int getIdRutina() {
        return idRutina;
    }

    public void setIdRutina(int idRutina) {
        this.idRutina = idRutina;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(String parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public int getPuntosEXP() {
        return puntosEXP;
    }

    public void setPuntosEXP(int puntosEXP) {
        this.puntosEXP = puntosEXP;
    }
}
