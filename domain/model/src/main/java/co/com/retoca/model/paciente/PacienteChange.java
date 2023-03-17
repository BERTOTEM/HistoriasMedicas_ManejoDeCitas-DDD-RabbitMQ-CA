package co.com.retoca.model.paciente;

import co.com.retoca.model.paciente.events.*;
import co.com.retoca.model.generic.EventChange;
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
                    new Hora(event.getHora()),
                    new Correo(event.getCorreo()));
            paciente.cita.add(cita);
        });
        apply((PacienteActualizado event)->{
            paciente.pacienteId=paciente.getPacienteId();
            paciente.correo=paciente.getCorreo();
            paciente.nombre=paciente.getNombre();
            paciente.telefono=paciente.getTelefono();
            paciente.edad=paciente.getEdad();
        });
        apply((CitaActualizada event)->{
            Cita cita2 =new Cita(CitaId.of(event.getCitaId()),
                    new RevisionDeCitaMedica(event.getRevisionDeCitaMedica()),
                    new Duracion(event.getDuracion()),
                    new Hora(event.getHora()),
                    new Correo(event.getCorreo()));
            paciente.cita= new ArrayList<>();
            paciente.cita.add(cita2);
        });
       apply((PacienteEliminado event)->{

        });


    }

}
