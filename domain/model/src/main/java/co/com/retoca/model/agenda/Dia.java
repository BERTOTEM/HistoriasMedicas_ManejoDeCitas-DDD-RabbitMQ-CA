package co.com.retoca.model.agenda;


import co.com.retoca.model.agenda.values.DiaId;
import co.com.retoca.model.agenda.values.DisponibilidadHoraria;
import co.com.retoca.model.generic.Entity;

import java.util.List;

public class Dia extends Entity<DiaId> {

    private List<DisponibilidadHoraria> disponibilidadHorarias;

    public Dia(DiaId id, List<DisponibilidadHoraria> disponibilidadHorarias) {
        super(id);
        this.disponibilidadHorarias = disponibilidadHorarias;
    }

    public List<DisponibilidadHoraria> getDisponibilidadHorarias() {
        return disponibilidadHorarias;
    }

    public void setDisponibilidadHorarias(List<DisponibilidadHoraria> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
    }

    @Override
    public String toString() {
        return "Dia{" +
                "disponibilidadHorarias=" + disponibilidadHorarias +
                '}';
    }


}
