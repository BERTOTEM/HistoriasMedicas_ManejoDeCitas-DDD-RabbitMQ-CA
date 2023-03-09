package co.com.retoca.api;

import co.com.retoca.model.paciente.generic.DomainEvent;
import co.com.retoca.usecase.agregarcita.AgregarCitaUseCase;
import co.com.retoca.usecase.crearpaciente.CrearPacienteUseCase;
import co.com.retoca.usecase.generic.commands.AgregarCitaCommand;
import co.com.retoca.usecase.generic.commands.CrearPacienteCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

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
}
