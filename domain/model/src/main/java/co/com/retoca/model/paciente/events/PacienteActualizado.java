package co.com.reto.model.paciente.events;

import co.com.reto.model.paciente.generic.DomainEvent;
import co.com.reto.model.paciente.values.Correo;
import co.com.reto.model.paciente.values.InformacionPaciente;
import co.com.reto.model.paciente.values.PacienteId;

public class PacienteActualizado extends DomainEvent {
    private PacienteId pacienteId;

    private InformacionPaciente informacionPaciente;
    private Correo correo;

    public PacienteActualizado() {
        super("mazo.julian.PacienteActualizado");
    }

    public PacienteActualizado(PacienteId pacienteId, InformacionPaciente informacionPaciente, Correo correo) {
        super("mazo.julian.PacienteActualizado");
        this.pacienteId = pacienteId;
        this.informacionPaciente = informacionPaciente;
        this.correo = correo;
    }

    public PacienteId getPacienteId() {
        return pacienteId;
    }

    public InformacionPaciente getInformacionPaciente() {
        return informacionPaciente;
    }

    public Correo getCorreo() {
        return correo;
    }
}
