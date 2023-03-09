package co.com.retoca.model.paciente;

import co.com.retoca.model.paciente.events.CitaAgregada;
import co.com.retoca.model.paciente.events.PacienteActualizado;
import co.com.retoca.model.paciente.events.PacienteCreado;
import co.com.retoca.model.paciente.events.PacienteEliminado;
import co.com.retoca.model.paciente.generic.EventChange;
import co.com.retoca.model.paciente.values.*;

public class PacienteChange extends EventChange {

    public PacienteChange(Paciente paciente){
        apply((PacienteCreado event)->{
            paciente.correo=new Correo(event.getCorreo());
            paciente.nombre=new Nombre(event.getNombre());
            paciente.telefono=new Telefono(event.getTelefono());
            paciente.edad=new Edad(event.getEdad());
            paciente.cita= paciente.getCita();
        });
        apply((CitaAgregada event)->{
            Cita cita =new Cita(CitaId.of(event.getCitaId()),
                    new RevisionDeCitaMedica(event.getRevisionDeCitaMedica()));
            paciente.cita=cita;

        });
        apply((PacienteActualizado event)->{
            paciente.pacienteId=event.getPacienteId();
            paciente.correo=new Correo(event.getCorreo().toString());
            paciente.nombre=new Nombre(event.getNombre().toString());
            paciente.telefono=new Telefono(event.getTelefono().toString());
            paciente.edad=new Edad(event.getEdad().toString());
        });
       apply((PacienteEliminado event)->{
           paciente.pacienteId=event.getPacienteId();
        });

    }

}
