package co.com.retoca.model.paciente.values;

import co.com.retoca.model.paciente.generic.ValueObject;

public class Hora implements ValueObject<String> {

    private String hora;

    public Hora(String hora) {
        this.hora = hora;
    }


    @Override
    public String value() {
        return hora;
    }
}
