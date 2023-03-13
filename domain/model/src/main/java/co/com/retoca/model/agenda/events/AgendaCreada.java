package co.com.retoca.model.agenda.events;



import co.com.retoca.model.generic.DomainEvent;

import java.util.List;

public class AgendaCreada extends DomainEvent {

    private String semana;
    public AgendaCreada() {
        super("mazo.julian.agendaCreada");
    }

    public AgendaCreada(String semana) {
        super("mazo.julian.agendaCreada");
        this.semana = semana;
    }

    public String getSemana() {
        return semana;
    }

    @Override
    public String toString() {
        return "AgendaCreada{" +
                "semana=" + semana +
                '}';
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }
}
