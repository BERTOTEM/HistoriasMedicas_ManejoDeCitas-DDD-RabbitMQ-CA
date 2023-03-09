package co.com.reto.model.paciente.events;

import co.com.reto.model.paciente.generic.DomainEvent;
import co.com.reto.model.paciente.values.Correo;
import co.com.reto.model.paciente.values.InformacionPaciente;

public class PacienteCreado  extends DomainEvent {

    private  InformacionPaciente informacionPaciente;
    private  Correo correo;

    public PacienteCreado() {
        super("mazo.julian.pacientecreado");
    }

    public PacienteCreado(InformacionPaciente informacionPaciente, Correo correo) {
        super("mazo.julian.pacientecreado");
        this.informacionPaciente = informacionPaciente;
        this.correo = correo;
    }

    public InformacionPaciente getInformacionPaciente() {
        return informacionPaciente;
    }

    public Correo getCorreo() {
        return correo;
    }
}
