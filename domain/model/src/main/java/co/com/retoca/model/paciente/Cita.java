package co.com.retoca.model.paciente;

import co.com.retoca.model.paciente.generic.Entity;
import co.com.retoca.model.paciente.values.CitaId;
import co.com.retoca.model.paciente.values.RevisionDeCitaMedica;

public class Cita  extends Entity<CitaId> {

    private RevisionDeCitaMedica revisionDeCitaMedica;

    public Cita(CitaId citaId, RevisionDeCitaMedica revisionDeCitaMedica) {
        super(citaId);
        this.revisionDeCitaMedica = revisionDeCitaMedica;
    }

}
