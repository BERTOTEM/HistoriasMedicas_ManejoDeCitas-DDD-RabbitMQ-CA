package co.com.retoca.model.paciente.values;

import co.com.retoca.model.generic.ValueObject;

public class Telefono implements ValueObject<String> {

    private String telefono;

    public Telefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String value() {
        return telefono;
    }
}
