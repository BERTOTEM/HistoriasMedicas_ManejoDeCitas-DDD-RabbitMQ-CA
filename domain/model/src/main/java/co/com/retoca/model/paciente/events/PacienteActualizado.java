package co.com.retoca.model.paciente.events;


import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.*;

public class PacienteActualizado extends DomainEvent {
    private PacienteId pacienteId;
    private Correo correo;
    private Nombre nombre;
    private Telefono telefono;
    private Edad edad;


    public PacienteActualizado() {
        super("mazo.julian.pacienteActualizado");
    }

    public PacienteActualizado(PacienteId pacienteId, Correo correo, Nombre nombre, Telefono telefono, Edad edad) {
        super("mazo.julian.pacienteActualizado");
        this.pacienteId = pacienteId;
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
    }

    public PacienteId getPacienteId() {
        return pacienteId;
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

    public Correo getCorreo() {
        return correo;
    }
}
