package co.com.retoca.model.paciente.events;

import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.CitaId;
import co.com.retoca.model.paciente.values.RevisionDeCitaMedica;

public class CitaAgregada  extends DomainEvent {


    private String citaId;
    private String revisionDeCitaMedica;

    public CitaAgregada(String type) {
        super("mazo.julian.citaAgregada");
    }

    public CitaAgregada(String citaId, String revisionDeCitaMedica) {
        super("mazo.julian.citaAgregada");
        this.citaId = citaId;
        this.revisionDeCitaMedica = revisionDeCitaMedica;
    }

    public String getCitaId() {
        return citaId;
    }

    public String getRevisionDeCitaMedica() {
        return revisionDeCitaMedica;
    }
}
