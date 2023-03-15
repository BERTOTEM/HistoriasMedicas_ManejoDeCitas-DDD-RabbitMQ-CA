package co.com.retoca.model.agenda;
import co.com.retoca.model.agenda.events.AgendaCreada;
import co.com.retoca.model.agenda.events.DiaAgregado;

import co.com.retoca.model.agenda.values.AgendaId;
import co.com.retoca.model.agenda.values.DiaId;
import co.com.retoca.model.agenda.values.DisponibilidadHoraria;
import co.com.retoca.model.agenda.values.Semana;
import co.com.retoca.model.generic.AggregateRoot;
import co.com.retoca.model.generic.DomainEvent;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Agenda  extends AggregateRoot<AgendaId> {
    protected AgendaId agendaId;
    protected Semana semana;
    protected List<Dia> dia;

    public Agenda(AgendaId agendaId, Semana semana) {
        super(agendaId);
        appendChange(new AgendaCreada(semana.value())).apply();
    }

    public Agenda(AgendaId agendaId) {
        super(agendaId);
        subscribe(new AgendaChange(this));
    }
    public static Agenda from(AgendaId id, List<DomainEvent> events){
        Agenda agenda = new Agenda(id);
        events.forEach(event -> agenda.applyEvent(event));
        return agenda;
    }

    public  void agregarDia(DiaId diaId, List<String> disponibilidadHorarias){
        Objects.requireNonNull(diaId);
        Objects.requireNonNull(disponibilidadHorarias);

        appendChange(new DiaAgregado(diaId.value(), disponibilidadHorarias)).apply();
    }
    public AgendaId getAgendaId() {
        return agendaId;
    }
    public Semana getSemana() {
        return semana;
    }
    public List<Dia> getDia() {
        return dia;
    }
}
