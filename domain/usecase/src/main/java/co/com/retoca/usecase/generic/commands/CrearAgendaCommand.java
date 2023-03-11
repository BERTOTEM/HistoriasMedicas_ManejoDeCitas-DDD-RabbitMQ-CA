package co.com.retoca.usecase.generic.commands;

import co.com.retoca.usecase.generic.Command;

public class CrearAgendaCommand extends Command {
    private String angendaId;
    private String semana;

    public CrearAgendaCommand() {
    }

    public CrearAgendaCommand(String angendaId, String semana) {
        this.angendaId = angendaId;
        this.semana = semana;
    }

    public String getAngendaId() {
        return angendaId;
    }

    public String getSemana() {
        return semana;
    }

    public void setAngendaId(String angendaId) {
        this.angendaId = angendaId;
    }

    public void setSemana(String semana) {
        this.semana = semana;
    }
}
