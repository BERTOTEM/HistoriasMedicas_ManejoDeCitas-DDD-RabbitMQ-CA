package co.com.retoca.model.agenda.values;


import co.com.retoca.model.generic.Identity;

public class DiaId  extends Identity {
    private DiaId(String uuid){super(uuid);}

    public DiaId() {
    }
    public static DiaId of(String uuid){return new DiaId(uuid);}
}
