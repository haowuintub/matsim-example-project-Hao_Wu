package org.matsim.teaching.UB6_LinkLeaveEnventHandler;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunEventsHandler {

    public static void main(String[] args) {

        String inputFile = "output-reduced/output_events.xml.gz";
        String outputFile = "output-reduced/link6volumes_LinkLeaveEnventHandler.txt";

        EventsManager eventsManager = EventsUtils.createEventsManager();

        // SimpleEventHandler eventHandler = new SimpleEventHandler();
        LinkEventHandler eventHandler = new LinkEventHandler(outputFile);
        eventsManager.addHandler(eventHandler);

        MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager);
        eventsReader.readFile(inputFile);

        eventHandler.printResult();
    }
}