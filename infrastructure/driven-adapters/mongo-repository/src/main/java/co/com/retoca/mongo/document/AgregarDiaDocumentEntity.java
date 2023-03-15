package co.com.retoca.mongo.document;

import co.com.retoca.model.agenda.Dia;
import co.com.retoca.model.agenda.values.DisponibilidadHoraria;
import co.com.retoca.usecase.generic.commands.AgregarDiaCommand;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "diaAgregado")
public class AgregarDiaDocumentEntity {
    @Id
    private String id;

    private String agendaID;

    private String diaId;

    private List<String> disponibilidadHorarias ;

    public AgregarDiaDocumentEntity(AgregarDiaCommand command) {
        this.agendaID = command.getAgendaID();
        this.diaId = command.getDiaId();


        this.disponibilidadHorarias = command.getDisponibilidadHorarias();

    }

    public AgregarDiaCommand toDomain() {
        return new AgregarDiaCommand(this.agendaID, this.diaId, this.disponibilidadHorarias);
    }
}
