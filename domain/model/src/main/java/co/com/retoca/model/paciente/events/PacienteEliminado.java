package co.com.retoca.model.paciente.events;


import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.PacienteId;

;

public class PacienteEliminado extends DomainEvent {

    private PacienteId pacienteId;

    public PacienteEliminado() {
        super("mazo.julian.pacienteEliminado");
    }

    public PacienteEliminado(PacienteId pacienteId) {
        super("mazo.julian.pacienteEliminado");
        this.pacienteId = pacienteId;
    }

    public PacienteId getPacienteId() {
        return pacienteId;
    }
}
