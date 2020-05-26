package com.example.p2ac13002;

public class Especie {
    private String codespecie;
    private String nomespecie;
    private float peligroextincion;
    private int recuperados;
    private int muertos;

    public Especie() {
    }

    public Especie(String codespecie, String nomespecie, int peligroextincion, int recuperados, int muertos) {
        this.codespecie = codespecie;
        this.nomespecie = nomespecie;
        this.peligroextincion = peligroextincion;
        this.recuperados = recuperados;
        this.muertos = muertos;
    }

    public String getCodespecie() {
        return codespecie;
    }

    public void setCodespecie(String codespecie) {
        this.codespecie = codespecie;
    }

    public String getNomespecie() {
        return nomespecie;
    }

    public void setNomespecie(String nomespecie) {
        this.nomespecie = nomespecie;
    }

    public float getPeligroextincion() {
        return peligroextincion;
    }

    public void setPeligroextincion(float peligroextincion) {
        this.peligroextincion = peligroextincion;
    }

    public int getRecuperados() {
        return recuperados;
    }

    public void setRecuperados(int recuperados) {
        this.recuperados = recuperados;
    }

    public int getMuertos() {
        return muertos;
    }

    public void setMuertos(int muertos) {
        this.muertos = muertos;
    }
}
