package co.com.retoca.model.paciente;

import co.com.retoca.model.generic.Entity;
import co.com.retoca.model.paciente.values.*;

public class Cita  extends Entity<CitaId> {

    private RevisionDeCitaMedica revisionDeCitaMedica;
    private Duracion duracion;
    private Hora hora   ;
    private Correo correo   ;


    public Cita(CitaId id, RevisionDeCitaMedica revisionDeCitaMedica, Duracion duracion, Hora hora,Correo correo) {
        super(id);
        this.revisionDeCitaMedica = revisionDeCitaMedica;
        this.duracion = duracion;
        this.hora = hora;
        this.correo = correo;
    }
}
