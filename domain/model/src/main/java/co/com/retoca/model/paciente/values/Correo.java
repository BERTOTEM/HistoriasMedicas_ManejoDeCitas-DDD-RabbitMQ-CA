package co.com.reto.model.paciente.values;

import co.com.reto.model.paciente.generic.ValueObject;

import java.util.Objects;

public class Correo implements ValueObject<String> {
    private String correo;

    public Correo(String correo) {
        this.correo = Objects.requireNonNull(correo);
    }

    @Override
    public String value() {
        return correo;
    }
}
