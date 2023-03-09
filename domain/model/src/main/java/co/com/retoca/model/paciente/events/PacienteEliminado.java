package co.com.reto.model.paciente.events;


import co.com.reto.model.paciente.generic.DomainEvent;
import co.com.reto.model.paciente.values.PacienteId;

public class PacienteEliminado extends DomainEvent {

    private PacienteId pacienteId;

    public PacienteEliminado() {
        super("mazo.julian.PacienteEliminado");
    }

    public PacienteEliminado(PacienteId pacienteId) {
        super("mazo.julian.PacienteEliminado");
        this.pacienteId = pacienteId;
    }

    public PacienteId getPacienteId() {
        return pacienteId;
    }
}
