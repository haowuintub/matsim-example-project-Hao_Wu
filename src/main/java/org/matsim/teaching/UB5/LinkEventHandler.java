package org.matsim.teaching.UB5;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LinkEventHandler implements LinkEnterEventHandler {

    private final BufferedWriter bufferedWriter;

    public LinkEventHandler(String outputFile) {
        try {
            //可以放printResult()里面
            FileWriter fileWriter = new FileWriter(outputFile);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException ee) {
            throw new RuntimeException(ee);
        }
    }

    private double[] volumeLink6 = new double[200];//为啥是double？

    private int getSlot(double time) {
        return (int) time/3600;
    }

    @Override
    public void handleEvent(LinkEnterEvent event) {
        if (event.getLinkId().equals(Id.createLinkId("6"))) {
            int slot = getSlot(event.getTime());
            this.volumeLink6[slot]++;//Array数组volumeLink6里第slot个位置的数字＋1
        }
    }

    public void printResult() {
        try {
            bufferedWriter.write("Hour \t Volume");
            bufferedWriter.newLine();
            for (int i=0; i<200; i++) {
                double volume = this.volumeLink6[i];//为啥是double？
                bufferedWriter.write(i + "\t" + volume);
                bufferedWriter.newLine();
                //System.out.println("Volume on link 6 from " + i + " to " + (i + 1) + " o'clock = " + volume);
            }
            bufferedWriter.close();//不可以放try里面
        } catch (IOException ee) {
            throw new RuntimeException(ee);
        }
    }




    //不是这个类里需要的，只是Notiz（Array循环）
    public void printArray() {
        boolean first = true;
        System.out.println("[");
        for(double element: volumeLink6){
            if(first){
                System.out.println(element);
                //first = !first;
                first = !first;
            }
            System.out.println(", ");
            System.out.println(element);
        }
        System.out.println("]");
    }




}