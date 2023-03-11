package co.com.retoca.model.paciente.values;

import co.com.retoca.model.generic.Identity;

public class CitaId extends Identity {

    private  CitaId(String uuid){super(uuid);}

    public CitaId(){

    }
    public static CitaId of(String uuid){return new CitaId(uuid);}
}
