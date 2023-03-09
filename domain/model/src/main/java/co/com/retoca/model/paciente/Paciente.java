package co.com.retoca.model.paciente;


import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.model.paciente.events.PacienteCreado;
import co.com.retoca.model.paciente.generic.AggregateRoot;
import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.*;

import java.util.List;
import java.util.Objects;

public class Paciente extends AggregateRoot<PacienteId> {
    protected PacienteId pacienteId;
    protected Correo correo;
    protected Nombre nombre;
    protected Telefono telefono;
    protected Edad edad;

    protected  Cita cita;

    public Paciente(PacienteId pacienteId, Correo correo, Nombre nombre, Telefono telefono, Edad edad) {
        super(pacienteId);
        appendChange(new PacienteCreado(correo.value(),nombre.value(),telefono.value(),edad.value())).apply();
    }

    public Paciente(PacienteId pacienteId) {
        super(pacienteId);
        subscribe(new PacienteChange(this));
    }

    public static Paciente from(PacienteId id, List<DomainEvent> events){
        Paciente paciente = new Paciente(id);
        events.forEach(event -> paciente.applyEvent(event));
        return paciente;
    }




    public Correo getCorreo() {
        return correo;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public Edad getEdad() {
        return edad;
    }

    public PacienteId getPacienteId() {
        return pacienteId;
    }


    public  void AgregarCita(CitaId citaId,RevisionDeCitaMedica revisionDeCitaMedica){
        Objects.requireNonNull(citaId);
        Objects.requireNonNull(revisionDeCitaMedica);
        appendChange(new CitaAgregada(citaId.value(), revisionDeCitaMedica.value())).apply();

    }
    public Cita getCita() {
        return cita;
    }
}


