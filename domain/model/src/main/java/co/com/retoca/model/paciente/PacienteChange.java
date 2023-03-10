package co.com.retoca.model.paciente;

import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.model.paciente.events.PacienteActualizado;
import co.com.retoca.model.paciente.events.PacienteCreado;
import co.com.retoca.model.paciente.events.PacienteEliminado;
import co.com.retoca.model.paciente.generic.EventChange;
import co.com.retoca.model.paciente.values.*;

import java.util.ArrayList;

public class PacienteChange extends EventChange {

    public PacienteChange(Paciente paciente){
        apply((PacienteCreado event)->{
            paciente.correo=new Correo(event.getCorreo());
            paciente.nombre=new Nombre(event.getNombre());
            paciente.telefono=new Telefono(event.getTelefono());
            paciente.edad=new Edad(event.getEdad());
            paciente.cita= new ArrayList<>();
        });
        apply((CitaAgregada event)->{
            Cita cita =new Cita(CitaId.of(event.getCitaId()),
                    new RevisionDeCitaMedica(event.getRevisionDeCitaMedica()),
                    new Duracion(event.getDuracion()),
                    new Hora(event.getHora()));
            paciente.cita.add(cita);
        });
        apply((PacienteActualizado event)->{
            paciente.pacienteId=paciente.getPacienteId();
            paciente.correo=paciente.getCorreo();
            paciente.nombre=paciente.getNombre();
            paciente.telefono=paciente.getTelefono();
            paciente.edad=paciente.getEdad();
        });
       apply((PacienteEliminado event)->{

        });

    }

}
