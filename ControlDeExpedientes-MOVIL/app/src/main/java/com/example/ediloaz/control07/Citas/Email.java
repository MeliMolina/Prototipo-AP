package com.example.ediloaz.control07.Citas;

/**
 * Created by Administrador on 05/06/2017.
 */

import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Pacientes.Paciente;

import com.example.ediloaz.control07.Medicos.Medico;
import com.example.ediloaz.control07.Pacientes.Paciente;

/**
 * Created by Dell on 16/1/2017.
 */

public class Email {

    private String email;
    private int id,paciente_id;

    public Email(int pId, String pEmail, int pPaciente_id) {
        this.id = pId;
        this.email= pEmail;
        this.paciente_id = pPaciente_id;
    }
    public Email(int pId, String pEmail) {
        this.id = pId;
        this.email= pEmail;

    }

    public void setEmail(String pEmail){
        this.email = pEmail;

    }
    public String GetEmail(){
        return email;
    }
    public void setId(int pId){
        this.id = pId;
    }
    public int GetId(){
        return id;
    }

    public void setPaciente_id(int pPaciente_id){
        this.paciente_id = pPaciente_id;
    }

    public int GetPaciente_id(){
        return paciente_id;
    }




}
