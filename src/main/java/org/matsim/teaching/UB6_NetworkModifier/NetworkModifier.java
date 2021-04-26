package org.matsim.teaching.UB6_NetworkModifier;

import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkWriter;
import org.matsim.core.network.NetworkUtils;
import org.matsim.core.network.io.MatsimNetworkReader;

import java.nio.file.Path;
import java.nio.file.Paths;

public class NetworkModifier {

    public static void main(String[] args) {
        Path inputNetwork = Paths.get(args[0]);
        Path outputNetwork = Paths.get(args[1]);

        Network network = NetworkUtils.createNetwork();
        new MatsimNetworkReader(network).readFile(inputNetwork.toString());
        //toString() 方法会返回一个“以文本方式表示”此对象的字符串

        network.getLinks().get(Id.createLinkId("4")).setCapacity(1);
        network.getLinks().get(Id.createLinkId("5")).setCapacity(1);
        network.getLinks().get(Id.createLinkId("6")).setCapacity(1);
        network.getLinks().get(Id.createLinkId("7")).setCapacity(1);
        network.getLinks().get(Id.createLinkId("8")).setCapacity(1);

        new NetworkWriter(network).write(outputNetwork.toString());
    }
}