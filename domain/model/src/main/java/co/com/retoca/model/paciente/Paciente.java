package co.com.retoca.model.paciente;


import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.model.paciente.events.PacienteActualizado;
import co.com.retoca.model.paciente.events.PacienteCreado;
import co.com.retoca.model.paciente.events.PacienteEliminado;
import co.com.retoca.model.paciente.generic.AggregateRoot;
import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Paciente extends AggregateRoot<PacienteId> {
    protected PacienteId pacienteId;
    protected Correo correo;
    protected Nombre nombre;
    protected Telefono telefono;
    protected Edad edad;
    protected  List<Cita> cita;

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



    public  void agregarCita(CitaId citaId,RevisionDeCitaMedica revisionDeCitaMedica,Duracion duracion,Hora hora){
        Objects.requireNonNull(citaId);
        Objects.requireNonNull(revisionDeCitaMedica);
        Objects.requireNonNull(duracion);
        Objects.requireNonNull(hora);
        appendChange(new CitaAgregada(citaId.value(),revisionDeCitaMedica.value(),duracion.value(),hora.value())).apply();

    }

    public  void actualizarPaciente(PacienteId pacienteId, Correo correo, Nombre nombre, Telefono telefono, Edad edad){
        Objects.requireNonNull(pacienteId);
        Objects.requireNonNull(correo);
        Objects.requireNonNull(nombre);
        Objects.requireNonNull(telefono);
        Objects.requireNonNull(edad);
        appendChange(new PacienteActualizado(pacienteId.value(),correo.value(),nombre.value(),telefono.value(),edad.value())).apply();
    }

    public void eliminarPaciente(PacienteId pacienteId){
        Objects.requireNonNull(pacienteId);
        appendChange(new PacienteEliminado(pacienteId)).apply();
    }

    public PacienteId getPacienteId() {
        return pacienteId;
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
}


