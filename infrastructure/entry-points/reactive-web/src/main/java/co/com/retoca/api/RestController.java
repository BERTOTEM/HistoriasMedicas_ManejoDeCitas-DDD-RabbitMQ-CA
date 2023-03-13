package co.com.retoca.api;

import co.com.retoca.model.agenda.events.DiaAgregado;
import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.model.paciente.Paciente;
import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.usecase.actualizarpaciente.ActualizarPacienteUseCase;
import co.com.retoca.usecase.agregarcita.AgregarCitaUseCase;
import co.com.retoca.usecase.agregardia.AgregarDiaUseCase;
import co.com.retoca.usecase.crearagenda.CrearAgendaUseCase;
import co.com.retoca.usecase.crearpaciente.CrearPacienteUseCase;
import co.com.retoca.usecase.generic.commands.*;
import co.com.retoca.usecase.historialpaciente.HistorialPacienteUseCase;
import co.com.retoca.usecase.veragenda.VerAgendaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class RestController {
    @Bean
    public RouterFunction<ServerResponse> crearPaciente(CrearPacienteUseCase crearPacienteUseCase){

        return route(
                POST("/create/Paciente").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(crearPacienteUseCase
                                        .apply(request.bodyToMono(CrearPacienteCommand.class)),
                                DomainEvent.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> agregarCita(AgregarCitaUseCase agregarCitaUseCase){

        return route(
                POST("/create/cita").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(agregarCitaUseCase
                                        .apply(request.bodyToMono(AgregarCitaCommand.class)),
                                DomainEvent.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> actulizarPaciente(ActualizarPacienteUseCase actualizarPacienteUseCase){

        return route(
                PUT("/actualizar/paciente").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(actualizarPacienteUseCase
                                        .apply(request.bodyToMono(ActualizarPacienteCommand.class)),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearAgenda(CrearAgendaUseCase crearAgendaUseCase){

        return route(
                POST("/create/agenda").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(crearAgendaUseCase
                                        .apply(request.bodyToMono(CrearAgendaCommand.class)),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> AgregarDia(AgregarDiaUseCase agregarDiaUseCase){

        return route(
                POST("/create/dia").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(agregarDiaUseCase
                                        .apply(request.bodyToMono(AgregarDiaCommand.class)),
                                DomainEvent.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> obtenerHistorial(HistorialPacienteUseCase historialPacienteUseCase){

        return route( GET("/historial/{aggregateRootId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(historialPacienteUseCase
                                        .apply(request.pathVariable("aggregateRootId")),
                                DomainEvent.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> ObtenerAgenda(VerAgendaUseCase verAgendaUseCase){

        return route( GET("/Agenda/{aggregateRootId}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(verAgendaUseCase
                                        .apply(request.pathVariable("aggregateRootId")),
                                DiaAgregado.class))
        );
    }
}
