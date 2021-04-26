package org.matsim.teaching.UB4;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunEventsHandler_UB4_2 {

    public static void main(String[] args) {

        String inputFile = "output/output_events.xml.gz";

        EventsManager eventsManager = EventsUtils.createEventsManager();

        LinkEnterEvents_UB4_2 eventHandler = new LinkEnterEvents_UB4_2();
        eventsManager.addHandler(eventHandler);

        MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager);
        eventsReader.readFile(inputFile);
    }
}