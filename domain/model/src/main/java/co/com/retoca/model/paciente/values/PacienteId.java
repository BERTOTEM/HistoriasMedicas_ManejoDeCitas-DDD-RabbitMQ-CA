package co.com.reto.model.paciente.values;

import co.com.reto.model.paciente.generic.Identity;

public class PacienteId extends Identity {

    private PacienteId(String uuid){super(uuid);}

    public PacienteId(){

    }
    public static  PacienteId of(String uuid){return  new PacienteId(uuid);}
}
