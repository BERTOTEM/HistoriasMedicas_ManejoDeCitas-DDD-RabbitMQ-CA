package co.com.retoca.usecase.generic.commands;

import co.com.retoca.model.agenda.values.DisponibilidadHoraria;
import co.com.retoca.usecase.generic.Command;

import java.util.List;

public class AgregarDiaCommand extends Command {
    private String agendaID;
    private String diaId;
    private List<String> disponibilidadHorarias;

    public AgregarDiaCommand() {
    }

    public AgregarDiaCommand(String agendaID, String diaId, List<String> disponibilidadHorarias) {
        this.agendaID = agendaID;
        this.diaId = diaId;
        this.disponibilidadHorarias = disponibilidadHorarias;
    }

    public String getAgendaID() {
        return agendaID;
    }

    public void setAgendaID(String agendaID) {
        this.agendaID = agendaID;
    }

    public String getDiaId() {
        return diaId;
    }

    public void setDiaId(String diaId) {
        this.diaId = diaId;
    }

    public List<String> getDisponibilidadHorarias() {
        return disponibilidadHorarias;
    }

    public void setDisponibilidadHorarias(List<String> disponibilidadHorarias) {
        this.disponibilidadHorarias = disponibilidadHorarias;
    }
}