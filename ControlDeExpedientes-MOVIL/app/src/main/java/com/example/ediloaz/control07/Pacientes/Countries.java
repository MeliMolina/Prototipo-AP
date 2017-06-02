package com.example.ediloaz.control07.Pacientes;

/**
 * Created by Administrador on 28/05/2017.
 */

public class Countries {

    private int id;
    private String provincia, distrito, canton;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String pProvincia) {
        this.provincia = pProvincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String pCanton) {
        this.canton = pCanton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String pDistrito) {
        this.distrito = pDistrito;
    }

    public Countries(String pProvincia, String pCanton, String pDistrito) {
        this.provincia = pProvincia;
        this.canton = pCanton;
        this.distrito= pDistrito;
    }


    public Countries(int id, String pProvincia, String pCanton, String pDistrito) {
        this.id = id;
        this.provincia = pProvincia;
        this.canton = pCanton;
        this.distrito= pDistrito;
    }


}

