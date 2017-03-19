package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 3/11/2017.
 */
public abstract class AgendaManager {
    
    protected List<Agenda> agendas;

    public AgendaManager() {
        agendas = new ArrayList<>();
    }

    public void addAgenda(Agenda agenda) {
        boolean isOverlapping = false;
        Agenda overlappingEvent = null;

        for (Agenda a : agendas) {
            if (Checker.isDateOverlapping(a.getStartDate(), a.getEndDate(),
                    agenda.getStartDate(), agenda.getEndDate()))
                isOverlapping = true;
            overlappingEvent = a;
            break;
        }

        if (isOverlapping)
            System.out.println("ERROR: Input agenda: \n" + agenda + " \noverlaps with existing agenda: \n" + overlappingEvent);
        else
            agendas.add(agenda);
    }
}
