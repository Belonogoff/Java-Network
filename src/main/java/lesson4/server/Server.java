import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) throws IOException {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        try(DatagramSocket socket = new DatagramSocket(9090)) {
            while (true) {
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength(), StandardCharsets.UTF_8);
                if ("stop".equalsIgnoreCase(received)) {
                    break;
                }
                DatagramPacket echo = new DatagramPacket(buffer, buffer.length, packet.getAddress(), packet.getPort());
                socket.send(echo);
            }
        }
    }
}
