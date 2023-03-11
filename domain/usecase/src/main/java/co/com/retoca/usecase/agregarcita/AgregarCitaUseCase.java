package co.com.retoca.usecase.agregarcita;

import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.values.*;
import co.com.retoca.usecase.generic.UseCaseForCommand;
import co.com.retoca.usecase.generic.commands.AgregarCitaCommand;
import co.com.retoca.usecase.generic.gateways.DomainEventRepository;
import co.com.retoca.usecase.generic.gateways.EventBus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class AgregarCitaUseCase  extends UseCaseForCommand<AgregarCitaCommand> {

    private final DomainEventRepository repository;
    private final EventBus bus;

    public AgregarCitaUseCase(DomainEventRepository repository, EventBus bus) {
        this.repository = repository;
        this.bus = bus;
    }


    @Override
    public Flux<DomainEvent> apply(Mono<AgregarCitaCommand> agregarCitaCommandMono) {
        return agregarCitaCommandMono.flatMapMany
                (agregarCitaCommand ->repository.findById(agregarCitaCommand.getPacienteId())
                .collectList()
                .flatMapIterable(events ->{
                    if (){
                    Paciente paciente =Paciente.from(PacienteId.of(agregarCitaCommand.getPacienteId()),events);
                    paciente.agregarCita(CitaId.of(agregarCitaCommand.getCitaId()),
                            new RevisionDeCitaMedica(agregarCitaCommand.getRevisionDeCitaMedica()),
                            new Duracion(agregarCitaCommand.getDuracion()),
                            new Hora(agregarCitaCommand.getHora()));
                    return Flux.fromIterable(paciente.getUncommittedChanges())
                            .flatMap(event -> repository.saveEvent(event))
                            .flatMap(domainEvent -> repository.save(domainEvent))
                            .doOnNext(event -> bus.publish(event));

                    }
                    else{
                        return Mono.error(new RuntimeException("hora no disponible"));
                    }
                }).onErrorResume(error ->Flux.empty()));
    }
}
