package com.softcare.pockettrainer.adminbasededatos;

public class Usuario {
    private int idUsuario;
    private String meta;
    private String tipoCuerpo;
    private int idRutinasProgramadas;
    private int idHorario;
    private int exp;
    private int nivel;

    public Usuario(int idUsuario, String meta, String tipoCuerpo, int idRutinasProgramadas, int idHorario, int exp, int nivel) {
        this.idUsuario = idUsuario;
        this.meta = meta;
        this.tipoCuerpo = tipoCuerpo;
        this.idRutinasProgramadas = idRutinasProgramadas;
        this.idHorario = idHorario;
        this.exp = exp;
        this.nivel = nivel;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public String getTipoCuerpo() {
        return tipoCuerpo;
    }

    public void setTipoCuerpo(String tipoCuerpo) {
        this.tipoCuerpo = tipoCuerpo;
    }

    public int getIdRutinasProgramadas() {
        return idRutinasProgramadas;
    }

    public void setIdRutinasProgramadas(int idRutinasProgramadas) {
        this.idRutinasProgramadas = idRutinasProgramadas;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}


