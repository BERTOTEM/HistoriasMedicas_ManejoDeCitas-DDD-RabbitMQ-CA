package co.com.retoca.model.paciente.events;

import co.com.retoca.model.generic.DomainEvent;

public class CitaActualizada extends DomainEvent {

    private String citaId;
    private String revisionDeCitaMedica;

    private String duracion;
    private String hora   ;
    private String correo   ;

    public CitaActualizada() {
        super("mazo.julian.CitaActualizada");
    }

    public CitaActualizada(String citaId, String revisionDeCitaMedica, String duracion, String hora, String correo) {
        super("mazo.julian.CitaActualizada");
        this.citaId = citaId;
        this.revisionDeCitaMedica = revisionDeCitaMedica;
        this.duracion = duracion;
        this.hora = hora;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    @Override
    public String toString() {
        return "CitaActualizada{" +
                "citaId='" + citaId + '\'' +
                ", revisionDeCitaMedica='" + revisionDeCitaMedica + '\'' +
                ", duracion='" + duracion + '\'' +
                ", hora='" + hora + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
