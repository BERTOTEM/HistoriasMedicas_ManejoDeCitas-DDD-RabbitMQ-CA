package co.com.retoca.model.paciente.values;


import co.com.retoca.model.paciente.generic.ValueObject;

public class RevisionDeCitaMedica implements ValueObject<String> {
     private String revisionDeCitaMedica;

    public RevisionDeCitaMedica(String revisionDeCitaMedica) {
        this.revisionDeCitaMedica = revisionDeCitaMedica;
    }

    @Override
    public String value() {
        return revisionDeCitaMedica;
    }
}
