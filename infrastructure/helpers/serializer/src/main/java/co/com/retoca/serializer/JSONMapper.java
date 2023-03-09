package co.com.retoca.serializer;

import org.springframework.context.annotation.Configuration;


public interface JSONMapper {
    String writeToJson(Object object);
    Object readFromJson(String json, Class<?> clazz);
}
