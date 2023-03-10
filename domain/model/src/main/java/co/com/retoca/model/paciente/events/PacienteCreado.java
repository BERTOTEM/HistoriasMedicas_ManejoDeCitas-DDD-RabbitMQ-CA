package co.com.retoca.model.paciente.events;


import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.model.paciente.values.Correo;
import co.com.retoca.model.paciente.values.Edad;
import co.com.retoca.model.paciente.values.Nombre;
import co.com.retoca.model.paciente.values.Telefono;

public class PacienteCreado  extends DomainEvent {


    private String correo;
    private String nombre;
    private String telefono;
    private String edad;

    public PacienteCreado() {
        super("mazo.julian.pacientecreado");
    }

    public PacienteCreado(String correo, String nombre, String telefono, String edad) {
        super("mazo.julian.pacientecreado");
        this.correo = correo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.edad = edad;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEdad() {
        return edad;
    }

    @Override
    public String toString() {
        return "PacienteCreado{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", edad='" + edad + '\'' +
                '}';
    }
}
