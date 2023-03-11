package co.com.retoca.model.paciente.values;


import co.com.retoca.model.generic.Identity;

public class PacienteId extends Identity {

    public PacienteId(String uuid){super(uuid);}

    public PacienteId(){
    }
    public static  PacienteId of(String uuid){return  new PacienteId(uuid);}
}
