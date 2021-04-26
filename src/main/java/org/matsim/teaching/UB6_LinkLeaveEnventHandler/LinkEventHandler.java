package org.matsim.teaching.UB6_LinkLeaveEnventHandler;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.LinkLeaveEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;
import org.matsim.api.core.v01.events.handler.LinkLeaveEventHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LinkEventHandler implements LinkLeaveEventHandler {

    private final BufferedWriter bufferedWriter;

    public LinkEventHandler(String outputFile) {
        try {
            FileWriter fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException ee) {
            throw new RuntimeException(ee);
        }
    }

    private double[] volumeLink6 = new double[200];

    private int getSlot(double time) {
        return (int) time/3600;
    }

    @Override
    public void handleEvent(LinkLeaveEvent event) {
        if (event.getLinkId().equals(Id.createLinkId("6"))) {
            int slot = getSlot(event.getTime());
            this.volumeLink6[slot]++;
        }
    }

    public void printResult() {
        try {
            bufferedWriter.write("Hour \t Volume");
            bufferedWriter.newLine();
            for (int i=0; i<200; i++) {
                double volume = this.volumeLink6[i];
                bufferedWriter.write(i + "\t" + volume);
                bufferedWriter.newLine();
                //System.out.println("Volume on link 6 from " + i + " to " + (i + 1) + " o'clock = " + volume);
            }
            bufferedWriter.close();//可以放try里面吗？
        } catch (IOException ee) {
            throw new RuntimeException(ee);
        }
    }
}