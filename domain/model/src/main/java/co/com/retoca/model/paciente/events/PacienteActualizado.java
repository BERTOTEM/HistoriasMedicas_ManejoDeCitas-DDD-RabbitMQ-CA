package co.com.retoca.model.paciente.events;


import co.com.retoca.model.generic.DomainEvent;

public class PacienteActualizado extends DomainEvent {
    private String pacienteId;
    private String correo;
    private String nombre;
    private String telefono;
    private String edad;


    public PacienteActualizado() {
        super("mazo.julian.pacienteActualizado");
    }

    public PacienteActualizado(String pacienteId, String correo, String nombre, String telefono, String edad) {
        super("mazo.julian.pacienteActualizado");
        this.pacienteId = pacienteId;
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
    }

    public String getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(String pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
}
