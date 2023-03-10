package co.com.retoca.model.paciente;

import co.com.retoca.model.paciente.generic.Entity;
import co.com.retoca.model.paciente.values.CitaId;
import co.com.retoca.model.paciente.values.Duracion;
import co.com.retoca.model.paciente.values.Hora;
import co.com.retoca.model.paciente.values.RevisionDeCitaMedica;

public class Cita  extends Entity<CitaId> {

    private RevisionDeCitaMedica revisionDeCitaMedica;
    private Duracion duracion;
    private Hora hora   ;


    public Cita(CitaId id, RevisionDeCitaMedica revisionDeCitaMedica, Duracion duracion, Hora hora) {
        super(id);
        this.revisionDeCitaMedica = revisionDeCitaMedica;
        this.duracion = duracion;
        this.hora = hora;
    }
}
