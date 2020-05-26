package com.example.p2ac13002;

public class Pais {
    private String codpais;
    private String nommpais;
    private String esdeAfrica;

    public Pais() {
    }

    public Pais(String codpais, String nommpais, String esdeAfrica) {
        this.codpais = codpais;
        this.nommpais = nommpais;
        this.esdeAfrica = esdeAfrica;
    }

    public String getCodpais() {
        return codpais;
    }

    public void setCodpais(String codpais) {
        this.codpais = codpais;
    }

    public String getNommpais() {
        return nommpais;
    }

    public void setNommpais(String nommpais) {
        this.nommpais = nommpais;
    }

    public String getEsdeAfrica() {
        return esdeAfrica;
    }

    public void setEsdeAfrica(String esdeAfrica) {
        this.esdeAfrica = esdeAfrica;
    }
}
