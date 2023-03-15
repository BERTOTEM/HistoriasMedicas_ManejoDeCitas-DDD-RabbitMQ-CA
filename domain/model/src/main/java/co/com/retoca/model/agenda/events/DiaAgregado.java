package co.com.retoca.model.agenda.events;



import co.com.retoca.model.generic.DomainEvent;

import java.util.List;

public class DiaAgregado extends DomainEvent {

    private String diaId;
    private List<String> disponibilidadHorarias;

    public DiaAgregado() {
        super("mazo.julian.diaAgregado");
    }
    public DiaAgregado(String diaId, List<String> disponibilidadHorarias) {
        super("mazo.julian.diaAgregado");
        this.diaId = diaId;
        this.disponibilidadHorarias = disponibilidadHorarias;
    }
    public String getDiaId() {
        return diaId;
    }
    public List<String> getDisponibilidadHorarias() {
        return disponibilidadHorarias;
    }

    @Override
    public String toString() {
        return "DiaAgregado{" +
                "diaId='" + diaId + '\'' +
                ", disponibilidadHorarias=" + disponibilidadHorarias +
                '}';
    }

    public void setDisponibilidadHorarias(List<String> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
    }


}
