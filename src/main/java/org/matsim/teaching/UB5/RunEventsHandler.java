package org.matsim.teaching.UB5;

import org.matsim.core.api.experimental.events.EventsManager;
import org.matsim.core.events.EventsUtils;
import org.matsim.core.events.MatsimEventsReader;

public class RunEventsHandler {

    public static void main(String[] args) {

        String inputFile = "output100/output_events.xml.gz";
        String outputFile = "output100/link6volumes.txt";

        EventsManager eventsManager = EventsUtils.createEventsManager();

        // SimpleEventHandler eventHandler = new SimpleEventHandler();
        LinkEventHandler eventHandler = new LinkEventHandler(outputFile);//先指定link6volumes.txt的输出路径
        eventsManager.addHandler(eventHandler);

        MatsimEventsReader eventsReader = new MatsimEventsReader(eventsManager);
        eventsReader.readFile(inputFile);

        eventHandler.printResult();//输出link6volumes.txt
    }
}