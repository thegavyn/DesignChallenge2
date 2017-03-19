package model;

import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mina on 3/13/2017.
 */
public class AgendaUtility {

    public static List sortAgendaByTime(List<Agenda> agendas) {
        List<Agenda> agendasCopy = new ArrayList<>(agendas);
        List<Agenda> sortedAgendas = new ArrayList<>();
        int size = agendas.size();

        while (sortedAgendas.size() < size) {
            Agenda min = agendasCopy.get(0);

            for (int i = 0; i < agendasCopy.size(); i++) {
                if (agendasCopy.get(i).getStartDate().toLocalTime().isBefore(min.getStartDate().toLocalTime())) {
                    min = agendasCopy.get(i);
                }
            }

            sortedAgendas.add(min);
            agendasCopy.remove(min);
			
			//for (Agenda agenda : sortedAgendas) {
			//	System.out.println(agenda);
			//}
			//System.out.println();
			//for (Agenda agenda : agendasCopy) {
			//	System.out.println(agenda);
			//}
			//System.out.println();
			//System.out.println();
        }

        return sortedAgendas;
    }
	
	public static void checkForFinishedEvents(List<Agenda> agenda, LocalDate currentDate) {
		for (Agenda a : agenda) {
			if (a instanceof Event)
				if (a.getStartDate().toLocalDate().isBefore(currentDate)) {
					a.markAsFinished();
				}
		}
	}

	public static List sortAgendaByDateTime(List<Agenda> agendas) {
        List<Agenda> agendasCopy = new ArrayList<>(agendas);
        List<Agenda> sortedAgendas = new ArrayList<>();
        int size = agendas.size();

        while (sortedAgendas.size() < size) {
            Agenda min = agendasCopy.get(0);

            for (int i = 0; i < agendasCopy.size(); i++) {
                if (agendasCopy.get(i).getStartDate().isBefore(min.getStartDate())) {
                    min = agendasCopy.get(i);
                }
            }

            sortedAgendas.add(min);
            agendasCopy.remove(min);
			
			//for (Agenda agenda : sortedAgendas) {
			//	System.out.println(agenda);
			//}
			//System.out.println();
			//for (Agenda agenda : agendasCopy) {
			//	System.out.println(agenda);
			//}
			//System.out.println();
			//System.out.println();
        }

        return sortedAgendas;
    }
	
	

    public static void markFinishedEvents(List<Event> events, LocalDate currentDate) {
        for (Event e : events) {
            if (e.getEndDate().toLocalDate().isBefore(currentDate)) {

            }
        }
    }

    public static List getCertainAgendaTypes(List<Agenda> agendas, String agendaType) {
        List<Agenda> newAgendas = new ArrayList<>();

        switch (agendaType) {
            case "Event":
                for (Agenda a : agendas)
                    if (a instanceof Event)
                        newAgendas.add(a);
                break;
            case "Task":
                for (Agenda a : agendas)
                    if (a instanceof Task)
                        newAgendas.add(a);
                break;
        }

        return newAgendas;
    }

    public static Color convertColor(java.awt.Color color) {
        return Color.rgb(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() / 255.0);
    }

    public static List getCertainDateAgendas(List<Agenda> agendas, LocalDate certainDate) {
        List<Agenda> todayAgendas = new ArrayList<>();

        for (Agenda a : agendas) {
            if (a.getStartDate().toLocalDate().equals(certainDate))
                todayAgendas.add(a);
        }

        return todayAgendas;
    }

}
