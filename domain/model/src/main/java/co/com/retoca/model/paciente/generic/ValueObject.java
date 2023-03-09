package co.com.retoca.model.paciente.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
