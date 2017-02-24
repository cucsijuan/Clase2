package com.utn.android.clase2;

import android.net.Uri;

/**
 * Created by lenovo on 23/02/2017.
 */

public class Persona {
    private String nombre;
    private String apellido;
    private String direccion;
    private String imagen;

    public Persona(String nomb, String apell, String dir) {

        setNombre(nomb);
        setApellido(apell);
        setDireccion(dir);

    }

    public Persona(String nomb, String apell, String dir, String img) {

        setNombre(nomb);
        setApellido(apell);
        setDireccion(dir);
        setImagen(img);

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
