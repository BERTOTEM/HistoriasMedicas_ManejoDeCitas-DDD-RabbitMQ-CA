package co.com.retoca.model.paciente.values;

import co.com.retoca.model.generic.ValueObject;

public class Edad implements ValueObject<String> {

    private String edad;

    public Edad(String edad) {
        this.edad = edad;
    }

    @Override
    public String value() {
        return edad;
    }
}
