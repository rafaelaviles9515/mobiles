package com.example.p2ac13002;

public class Animal {
    private String codespecie;
    private String codpais;
    private String codanimal;
    private float edadanimal;
    private String estaVivo;

    public Animal() {
    }

    public Animal(String codespecie, String codpais, String codanimal, float edadanimal, String estaVivo) {
        this.codespecie = codespecie;
        this.codpais = codpais;
        this.codanimal = codanimal;
        this.edadanimal = edadanimal;
        this.estaVivo = estaVivo;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "codespecie='" + codespecie + '\'' +
                ", codpais='" + codpais + '\'' +
                ", codanimal='" + codanimal + '\'' +
                ", edadanimal=" + edadanimal +
                ", estaVivo='" + estaVivo + '\'' +
                '}';
    }

    public String getCodespecie() {
        return codespecie;
    }

    public void setCodespecie(String codespecie) {
        this.codespecie = codespecie;
    }

    public String getCodpais() {
        return codpais;
    }

    public void setCodpais(String codpais) {
        this.codpais = codpais;
    }

    public String getCodanimal() {
        return codanimal;
    }

    public void setCodanimal(String codanimal) {
        this.codanimal = codanimal;
    }

    public float getEdadanimal() {
        return edadanimal;
    }

    public void setEdadanimal(float edadanimal) {
        this.edadanimal = edadanimal;
    }

    public String getEstaVivo() {
        return estaVivo;
    }

    public void setEstaVivo(String estaVivo) {
        this.estaVivo = estaVivo;
    }
}
