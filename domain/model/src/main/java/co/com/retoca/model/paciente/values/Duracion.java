package co.com.retoca.model.paciente.values;

import co.com.retoca.model.generic.ValueObject;

public class Duracion implements ValueObject<String> {

    private String duracion;

    public Duracion(String duracion) {
        this.duracion = duracion;
    }


    @Override
    public String value() {
        return duracion;
    }
}
