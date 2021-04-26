package org.matsim.teaching.UB4;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.PersonArrivalEvent;
import org.matsim.api.core.v01.events.PersonDepartureEvent;
import org.matsim.api.core.v01.events.handler.PersonArrivalEventHandler;
import org.matsim.api.core.v01.events.handler.PersonDepartureEventHandler;
import org.matsim.api.core.v01.population.Person;

import java.util.HashMap;
import java.util.Map;

public class SimpleEventHandler_UB4_1 implements PersonDepartureEventHandler, PersonArrivalEventHandler {

    Map<Id<Person>, Double> departureTimeByPersonMap = new HashMap<>();

    @Override
    public void handleEvent(PersonDepartureEvent event) {
/*        System.out.println("Departure event; time " + event.getTime() + " -- linkId: " + event.getLinkId()
        + " personId: " + event.getPersonId());*/

        departureTimeByPersonMap.put(event.getPersonId(),event.getTime());

    }

    @Override
    public void handleEvent(PersonArrivalEvent event) {
/*        System.out.println("Arrival event; time " + event.getTime() + " -- linkId: " + event.getLinkId()
                + " personId: " + event.getPersonId());*/

        Double travelTime = event.getTime() -  departureTimeByPersonMap.get(event.getPersonId());
        System.out.println("Travel event; time " + travelTime + " -- linkId: " + event.getLinkId());

    }
}


