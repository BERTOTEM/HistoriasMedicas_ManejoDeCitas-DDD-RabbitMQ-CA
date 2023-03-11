package co.com.retoca.model.agenda.values;


import co.com.retoca.model.generic.Identity;

public class AgendaId extends Identity {
    public AgendaId(String uuid){super(uuid);
    }

    public AgendaId() {
    }

    public static  AgendaId of(String uuid){return  new AgendaId(uuid);}
}
