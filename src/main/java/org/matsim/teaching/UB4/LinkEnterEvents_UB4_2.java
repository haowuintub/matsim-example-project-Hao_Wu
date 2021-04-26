package org.matsim.teaching.UB4;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.events.LinkEnterEvent;
import org.matsim.api.core.v01.events.handler.LinkEnterEventHandler;

import java.io.*;

public class LinkEnterEvents_UB4_2 implements LinkEnterEventHandler {

    int count = 0;

//    File outputFile = new File("src/main/java/org.matsim/teaching/UB4/output.txt"); // 为啥这个地址不行?
    File outputFile = new File("output/output.txt"); // Insert Path to Output File Here!

    @Override
    public void handleEvent(LinkEnterEvent linkEnterEvent) {
        if(linkEnterEvent.getLinkId().equals(Id.createLinkId(6))){
            System.out.println(" -- linkId: " + linkEnterEvent.getLinkId());
            count++;
            System.out.println(" -- Sum: " + count);


            try (
                    // note resources in round brackets, not curly!
//                BufferedReader in = new BufferedReader(new FileReader(inputFile));
                    BufferedWriter out = new BufferedWriter(new FileWriter(outputFile));
            ) {

                // !!添加!!从outputFile中的第一个空行开始写

                out.write(" -- linkId: " + linkEnterEvent.getLinkId()+ "\n");
                out.write(" -- Sum: " + count + "\n");

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

}


