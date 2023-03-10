package co.com.retoca.model.paciente.events;

import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.CitaId;
import co.com.retoca.model.paciente.values.Duracion;
import co.com.retoca.model.paciente.values.Hora;
import co.com.retoca.model.paciente.values.RevisionDeCitaMedica;

public class CitaAgregada  extends DomainEvent {


    private String citaId;
    private String revisionDeCitaMedica;

    private String duracion;
    private String hora   ;

    public CitaAgregada() {
        super("mazo.julian.CitaAgregada");
    }

    public CitaAgregada(String citaId, String revisionDeCitaMedica, String duracion, String hora) {
        super("mazo.julian.CitaAgregada");
        this.citaId = citaId;
        this.revisionDeCitaMedica = revisionDeCitaMedica;
        this.duracion = duracion;
        this.hora = hora;
    }

    public String getCitaId() {
        return citaId;
    }

    public String getRevisionDeCitaMedica() {
        return revisionDeCitaMedica;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getHora() {
        return hora;
    }

    @Override
    public String toString() {
        return "CitaAgregada{" +
                "citaId='" + citaId + '\'' +
                ", revisionDeCitaMedica='" + revisionDeCitaMedica + '\'' +
                ", duracion='" + duracion + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
