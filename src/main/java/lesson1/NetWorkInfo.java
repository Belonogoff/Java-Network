package lesson1;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.stream.Stream;

public class NetWorkInfo {
    Stream<NetworkInterface> networkInterfaces;
    StringBuilder sb = new StringBuilder();

    public void getNetworkInterfaces() {

        try {
            networkInterfaces = NetworkInterface.networkInterfaces();
            networkInterfaces.forEach(parentNetIf -> {
                printNetworkInterface(parentNetIf);
                parentNetIf.subInterfaces().forEach(this::printNetworkInterface);
            });
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public void printNetworkInterface(NetworkInterface networkInterface) {
        try {
            sb.append(networkInterface)
                    .append(" : ")
                    .append(networkInterface.isUp())
                    .append(" ")
                    .append(networkInterface.isVirtual())
                    .append(" ")
                    .append(networkInterface.isLoopback());
        } catch (SocketException e) {
            System.out.println(e.getMessage());
        }
        String info = sb.toString();
        System.out.println(info);
        sb.setLength(0);
    }
}
