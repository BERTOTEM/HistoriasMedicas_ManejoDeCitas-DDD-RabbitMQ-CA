package co.com.retoca.usecase.agregarcita;

import co.com.retoca.model.agenda.events.DiaAgregado;
import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.values.*;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.AgregarCitaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgregarCitaUseCase extends UseCaseForCommand<AgregarCitaCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AgregarCitaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<AgregarCitaCommand> agregarCitaCommandMono) {
        return agregarCitaCommandMono.flatMapMany
                (agregarCitaCommand -> {
                    return repository.findById(agregarCitaCommand.getPacienteId())
                            .collectList()
                            .flatMapMany(events -> {
                                return repository.esistsByFecha(agregarCitaCommand.getCitaId())
                                        .flatMapMany(existe -> {
                                            if (existe) {
                                                Paciente paciente = Paciente.from(PacienteId.of(agregarCitaCommand.getPacienteId()), events);
                                                paciente.agregarCita(CitaId.of(agregarCitaCommand.getCitaId()),
                                                        new RevisionDeCitaMedica(agregarCitaCommand.getRevisionDeCitaMedica()),
                                                        new Duracion(agregarCitaCommand.getDuracion()),
                                                        new Hora(agregarCitaCommand.getHora()),
                                                new Correo(agregarCitaCommand.getCorreo()));
                                                String fecha = agregarCitaCommand.getCitaId();
                                                String horaAnterior = agregarCitaCommand.getHora();
                                                String horaNueva = agregarCitaCommand.getHora() + " Reservado por: " + agregarCitaCommand.getPacienteId();
                                                return repository.findDyFecha(fecha, horaAnterior, horaNueva)
                                                        .flatMapMany(diaAgregado -> {
                                                            return Flux.fromIterable(paciente.getUncommittedChanges())
                                                                    .flatMap(event -> repository.saveEvent(event))
                                                                    .doOnNext(event -> bus.publish(event));
                                                        })
                                                        .switchIfEmpty(Mono.error(new RuntimeException("Horario ya reservado")));
                                            } else {
                                                return Mono.error(new RuntimeException("DIa no disponible"));
                                            }
                                        });
                            }).onErrorResume(error ->Flux.empty());
                });
    }
}
