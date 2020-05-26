package com.example.firebase.model;

public class Persona {
    private String uid;
    private String Nombre;
    private String Apellidos;
    private String Correo;
    private String Password;

    public Persona(String uid, String nombre, String apellidos, String correo, String password) {
        this.uid = uid;
        Nombre = nombre;
        Apellidos = apellidos;
        Correo = correo;
        Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



    public Persona() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
