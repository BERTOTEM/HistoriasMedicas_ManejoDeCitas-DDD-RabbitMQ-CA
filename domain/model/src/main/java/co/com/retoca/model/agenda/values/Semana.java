package co.com.retoca.model.agenda.values;

import co.com.retoca.model.generic.ValueObject;

import java.util.Objects;

public class Semana implements ValueObject<String> {
    private String semana;

    public Semana(String semana) {
        this.semana = Objects.requireNonNull(semana);
    }

    @Override
    public String value() {
        return semana;
    }
}
