package co.com.retoca.usecase.generic.commands;

import co.com.retoca.usecase.generic.Command;

public class ActualizarCitaCommand extends Command {

    private String pacienteId;
    private String citaId;
    private String revisionDeCitaMedica;
    private String duracion;
    private String hora   ;
    private String correo;


    public ActualizarCitaCommand() {
    }

    public ActualizarCitaCommand(String pacienteId, String citaId, String revisionDeCitaMedica, String duracion, String hora,String correo) {
        this.pacienteId = pacienteId;
        this.citaId = citaId;
        this.revisionDeCitaMedica = revisionDeCitaMedica;
        this.duracion = duracion;
        this.hora = hora;
        this.correo = correo;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getCitaId() {
        return citaId;
    }

    public void setCitaId(String citaId) {
        this.citaId = citaId;
    }

    public String getRevisionDeCitaMedica() {
        return revisionDeCitaMedica;
    }

    public void setRevisionDeCitaMedica(String revisionDeCitaMedica) {
        this.revisionDeCitaMedica = revisionDeCitaMedica;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getCorreo() {
        return correo;
    }
}
