package co.com.retoca.model.paciente.values;

import co.com.retoca.model.generic.ValueObject;

public class Nombre implements ValueObject<String> {
    private  String nombre;

    public Nombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String value() {
        return nombre;
    }
}
