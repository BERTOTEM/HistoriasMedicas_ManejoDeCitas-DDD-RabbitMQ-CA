package co.com.retoca.mongo.data;



import co.com.retoca.model.generic.DomainEvent;
import co.com.retoca.serializer.JSONMapper;

import java.util.Date;

public class StoredEvent {
    private String eventBody;
    private String aggregateRootId;
    private Date occurredOn;
    private String typeName;

    public StoredEvent() {
    }


    public StoredEvent(String typeName, Date occurredOn, String eventBody) {
        this.setEventBody(eventBody);
        this.setOccurredOn(occurredOn);
        this.setTypeName(typeName);
    }


    public static String wrapEvent(DomainEvent domainEvent, JSONMapper eventSerializer){
        return eventSerializer.writeToJson(domainEvent);
    }


    public String getEventBody() {
        return eventBody;
    }


    public void setEventBody(String eventBody) {
        this.eventBody = eventBody;
    }


    public Date getOccurredOn() {
        return occurredOn;
    }


    public void setOccurredOn(Date occurredOn) {
        this.occurredOn = occurredOn;
    }


    public String getTypeName() {
        return typeName;
    }


    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getAggregateRootId() {
        return aggregateRootId;
    }

    public void setAggregateRootId(String aggregateRootId) {
        this.aggregateRootId = aggregateRootId;
    }


    public DomainEvent deserializeEvent(JSONMapper eventSerializer) {
        try{
            return (DomainEvent) eventSerializer
                    .readFromJson(this.getEventBody(), Class.forName(this.getTypeName()));
        }catch (ClassNotFoundException e){
            System.out.println(e);
            return null;
        }

    }


    public interface EventSerializer {
        <T extends DomainEvent> T deserialize(String aSerialization, final Class<?> aType);

        String serialize(DomainEvent object);
    }
}
