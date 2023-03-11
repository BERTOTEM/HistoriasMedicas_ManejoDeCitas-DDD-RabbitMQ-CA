package co.com.retoca.model.generic;

import java.io.Serializable;

public interface ValueObject<T> extends Serializable {
    T value();
}
