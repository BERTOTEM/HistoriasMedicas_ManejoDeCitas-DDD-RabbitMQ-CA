package co.com.retoca.model.agenda.values;

import co.com.retoca.model.generic.ValueObject;

public class DisponibilidadHoraria implements ValueObject<String> {
    private String disponibilidadHoraria;

    public DisponibilidadHoraria(String disponibilidadHoraria) {
        this.disponibilidadHoraria = disponibilidadHoraria;
    }


    @Override
    public String value() {
        return disponibilidadHoraria;
    }

    @Override
    public String toString() {
        return "DisponibilidadHoraria{" +
                "disponibilidadHoraria='" + disponibilidadHoraria + '\'' +
                '}';
    }
}
