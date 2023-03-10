package co.com.retoca.usecase.generic.commands;

import co.com.retoca.model.paciente.values.CitaId;
import co.com.retoca.model.paciente.values.RevisionDeCitaMedica;
import co.com.retoca.usecase.generic.Command;

public class AgregarCitaCommand  extends Command {

    private String pacienteId;
    private String citaId;
    private String revisionDeCitaMedica;
    private String duracion;
    private String hora   ;

    public AgregarCitaCommand() {
    }

    public AgregarCitaCommand(String pacienteId, String citaId, String revisionDeCitaMedica, String duracion, String hora) {
        this.pacienteId = pacienteId;
        this.citaId = citaId;
        this.revisionDeCitaMedica = revisionDeCitaMedica;
        this.duracion = duracion;
        this.hora = hora;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getRevisionDeCitaMedica() {
        return revisionDeCitaMedica;
    }

    public void setRevisionDeCitaMedica(String revisionDeCitaMedica) {
        this.revisionDeCitaMedica = revisionDeCitaMedica;
    }

    public String getCitaId() {
        return citaId;
    }

    public void setCitaId(String citaId) {
        this.citaId = citaId;
    }

    public String getDuracion() {
        return duracion;
    }

    public String getHora() {
        return hora;
    }
}
