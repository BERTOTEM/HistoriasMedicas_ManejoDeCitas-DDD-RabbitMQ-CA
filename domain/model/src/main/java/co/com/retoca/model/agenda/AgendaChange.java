package co.com.retoca.model.agenda;

import co.com.retoca.model.agenda.events.AgendaCreada;
import co.com.retoca.model.agenda.events.DiaAgregado;

import co.com.retoca.model.agenda.values.DiaId;
import co.com.retoca.model.agenda.values.DisponibilidadHoraria;
import co.com.retoca.model.agenda.values.Semana;
import co.com.retoca.model.generic.EventChange;


import java.util.ArrayList;
import java.util.List;

public class AgendaChange   extends EventChange {

    public AgendaChange(Agenda agenda){
        apply((AgendaCreada event) ->{
            agenda.semana=new Semana(event.getSemana());
            agenda.dia=new ArrayList<>();

        });
        apply((DiaAgregado event)->{
            Dia dia=new Dia(DiaId.of(event.getDiaId()),
                    new ArrayList<DisponibilidadHoraria>());
            agenda.dia.add(dia);
        });
    }
}
