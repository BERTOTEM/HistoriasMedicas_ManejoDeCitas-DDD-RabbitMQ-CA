package co.com.reto.model.paciente.values;

import co.com.reto.model.paciente.generic.ValueObject;

public class RevisionDeCitaMedica implements ValueObject<RevisionDeCitaMedica.InfoR> {
    private final String observaciones;
    private final String fecha;

    public RevisionDeCitaMedica(String observaciones, String fecha) {
        this.observaciones = observaciones;
        this.fecha = fecha;
    }
    public static RevisionDeCitaMedica of(String observaciones, String fecha) {
        return new RevisionDeCitaMedica(observaciones, fecha);
    }

    public RevisionDeCitaMedica.InfoR value(){
        return  new RevisionDeCitaMedica.InfoR() {
            @Override
            public String observaciones() {
                return observaciones;
            }

            @Override
            public String fecha() {
                return fecha;
            }
        };
    }

    public interface InfoR{
        String observaciones();
        String fecha();

    }


}
